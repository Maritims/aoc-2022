package io.github.maritims.advent_of_code.common.graph;

import java.util.*;

public class MinimumSpanningTree {
    private MinimumSpanningTree() {}

    public static <T> List<Edge2<T>> calculate(
            List<T> elements,
            KDTree.CoordinateProvider<T> coordinateProvider,
            KDTree.DistanceCalculator<T> distanceCalculator
    ) {
        if (elements == null || elements.size() < 2) {
            return List.of();
        }
        if (coordinateProvider == null) {
            throw new IllegalArgumentException("coordinateProvider cannot be null");
        }
        if (distanceCalculator == null) {
            throw new IllegalArgumentException("distanceCalculator cannot be null");
        }

        var kdTree = new KDTree<>(elements, coordinateProvider, distanceCalculator);
        var minimumSpanningTree = new ArrayList<Edge2<T>>();
        var visited = new HashSet<T>();

        var priorityQueue = new PriorityQueue<Edge2<T>>(Comparator.comparingDouble(Edge2::weight));

        var start = elements.getFirst();
        visited.add(start);
        expandFrontier(start, kdTree, visited, priorityQueue);

        // Keep going to places we haven't been to.
        while(visited.size() < elements.size() && !priorityQueue.isEmpty()) {
            var edge = priorityQueue.poll();

            // Have we been here before?
            if (visited.contains(edge.to())) {
                continue;
            }

            // We've been here now!
            visited.add(edge.to());
            minimumSpanningTree.add(edge);

            // Check out the neighbourhood.
            expandFrontier(edge.to(), kdTree, visited, priorityQueue);
        }

        return minimumSpanningTree;
    }

    private static <T> void expandFrontier(
            T element,
            KDTree<T> tree,
            Set<T> visited,
            PriorityQueue<Edge2<T>> priorityQueue
    ) {
        tree.findNearest(element, 10)
                .stream()
                .filter(neighbour -> !visited.contains(neighbour.element())) // Have we been here before?
                .forEach(neighbour -> priorityQueue.add(new Edge2<>(element, neighbour.element(), neighbour.squaredDistance())));
    }
}
