package io.github.maritims.advent_of_code.common.graph;

import io.github.maritims.advent_of_code.common.geometry.Axis;

import java.util.*;

public class KDTree<T> {
    @FunctionalInterface
    public interface CoordinateProvider<T> {
        double get(T element, Axis axis);
    }

    @FunctionalInterface
    public interface DistanceCalculator<T> {
        double calculateSquared(T a, T b);
    }

    private record Node<T>(T element, Node<T> left, Node<T> right, Axis axis) {
        public Node {
            Objects.requireNonNull(element, "element cannot be null");
            Objects.requireNonNull(axis, "axis cannot be null");
        }
    }

    public record Neighbour<T>(T element, double squaredDistance) {
        public Neighbour {
            Objects.requireNonNull(element, "element cannot be null");
        }
    }

    private final Node<T> root;
    private final CoordinateProvider<T> coordinateProvider;
    private final DistanceCalculator<T> distanceCalculator;

    public KDTree(List<T> elements, CoordinateProvider<T> coordinateProvider, DistanceCalculator<T> distanceCalculator) {
        if (elements == null || elements.isEmpty()) {
            throw new IllegalArgumentException("elements cannot be null or empty");
        }

        this.coordinateProvider = Objects.requireNonNull(coordinateProvider, "coordinateProvider cannot be null");
        this.distanceCalculator = Objects.requireNonNull(distanceCalculator, "distanceCalculator cannot be null");

        // Prevent mutation of the original list.
        this.root = buildTree(new ArrayList<>(elements), 0);
    }

    private Node<T> buildTree(List<T> elements, int depth) {
        Objects.requireNonNull(elements, "elements cannot be null");

        if (elements.isEmpty()) {
            return null;
        }

        var axis = Axis.values()[depth % Axis.values().length];
        var medianIndex = elements.size() / 2;

        // Use the QuickSelect utility to position the median element correctly on the current axis
        QuickSelect.select(elements, medianIndex, Comparator.comparingDouble(node -> coordinateProvider.get(node, axis)));

        return new Node<>(
                elements.get(medianIndex),
                buildTree(elements.subList(0, medianIndex), depth + 1),
                buildTree(elements.subList(medianIndex + 1, elements.size()), depth + 1),
                axis
        );
    }

    public List<Neighbour<T>> findNearestUnvisited(T target, int k, Set<T> visited) {
        var priorityQueue = new PriorityQueue<>(Comparator.comparingDouble((Neighbour<T> o) -> o.squaredDistance).reversed());

        search(root, target, k, visited, priorityQueue);

        var result = new ArrayList<>(priorityQueue);
        result.sort(Comparator.comparingDouble(Neighbour::squaredDistance));

        return result;
    }

    private void search(Node<T> node, T target, int k, Set<T> visited, PriorityQueue<Neighbour<T>> priorityQueue) {
        if(node == null) {
            return;
        }

        var distanceSquared = distanceCalculator.calculateSquared(target, node.element);

        // Is this node a candidate?
        if(!node.element.equals(target) && !visited.contains(node.element)) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(new Neighbour<>(node.element, distanceSquared));
            } else if (distanceSquared < priorityQueue.peek().squaredDistance) {
                priorityQueue.poll(); // Remove the furthest neighbour.
                priorityQueue.add(new Neighbour<>(node.element, distanceSquared)); // Add this one instead, which is closer.
            }
        }

        var diff = coordinateProvider.get(target, node.axis) - coordinateProvider.get(node.element, node.axis);
        var near = diff < 0 ? node.left : node.right;
        var far = diff < 0 ? node.right : node.left;

        search(near, target, k, visited, priorityQueue);

        var planeDistanceSquared = diff * diff;
        if (priorityQueue.size() < k || planeDistanceSquared < priorityQueue.peek().squaredDistance) {
            search(far, target, k, visited, priorityQueue);
        }
    }
}
