package io.github.maritims.advent_of_code.common.graph;

import io.github.maritims.advent_of_code.common.geometry.Axis;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

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

    public List<Neighbour<T>> findNearest(T target, int k) {
        return search(root, target, k)
                .sorted(Comparator.comparingDouble(Neighbour::squaredDistance))
                .limit(k)
                .toList();
    }

    private Stream<Neighbour<T>> search(Node<T> node, T target, int k) {
        if(node == null) {
            return Stream.empty();
        }

        var distance = distanceCalculator.calculateSquared(target, node.element);
        var currentNeighbour = node.element.equals(target) ? Stream.<Neighbour<T>>empty() : Stream.of(new Neighbour<>(node.element, distance));

        // Determine which way to branch by comparing the target and source coordinate on the current axis.
        var axis = node.axis;
        var targetCoordinate = coordinateProvider.get(target, axis);
        var sourceCoordinate = coordinateProvider.get(node.element, axis);
        var coordinateDifference = targetCoordinate - sourceCoordinate;

        var near = coordinateDifference < 0 ? node.left : node.right;
        var far = coordinateDifference < 0 ? node.right : node.left;

        var nearResults = Stream.concat(currentNeighbour, search(near, target, k)).toList();

        return shouldSearchFarSide(nearResults, coordinateDifference, k) ?
                Stream.concat(nearResults.stream(), search(far, target, k)) :
                nearResults.stream();
    }

    private boolean shouldSearchFarSide(List<Neighbour<T>> currentBestList, double diff, int k) {
        if (currentBestList.size() < k) {
            return true;
        }

        var maxSquaredDistance = currentBestList.stream()
                .mapToDouble(Neighbour::squaredDistance)
                .max()
                .orElse(Double.MAX_VALUE);

        return (diff * diff) < maxSquaredDistance;
    }
}
