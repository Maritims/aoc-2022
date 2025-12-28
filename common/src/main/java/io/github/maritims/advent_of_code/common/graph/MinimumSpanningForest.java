package io.github.maritims.advent_of_code.common.graph;

import java.util.*;

/**
 * A utility class implementing <a href="https://en.wikipedia.org/wiki/Kruskal's_algorithm"></a>Kruskal's Algorithm</a>.
 * <p>The algorithm performs the following steps:
 * <ul>
 *     <li>Create a forest (a set of trees) initially consisting of a separate single-vertex tree for each vertex in the input graph.</li>
 *     <li>Sort the graph edges by weight.</li>
 *     <li>
 *         Loop through the edges of the graph, in ascending sorted order by their weight. For each edge:
 *         <ul>
 *             <li>Test whether adding the edge to the current forest would create a cycle.</li>
 *             <li>If not, add the edge to the forest, combining two trees into a single tree.</li>
 *         </ul>
 *     </li>
 * </ul>
 * At the termination of the algorithm, the forest forms a minimum spanning forest of the graph. If the graph is connected, the forest has a single component and forms a minimum spanning tree.
 * </p>
 */
public final class MinimumSpanningForest {
    private MinimumSpanningForest() {}

    public static <T> List<Edge2<T>> of(
            List<T> elements,
            KDTree.CoordinateProvider<T> coordinateProvider,
            KDTree.DistanceCalculator<T> distanceCalculator
    ) {
        var tree = new KDTree<>(elements, coordinateProvider, distanceCalculator);

        // Step 1: Create a forest.
        var dsu = new DisjointSetUnion<>(elements);

        // Step 2: Sort the graph edges by weight, but without sorting it ourselves. We use a priority queue which gives us this behaviour every time we call poll().
        var allPotentialEdges = new PriorityQueue<Edge2<T>>(Comparator.comparingDouble(Edge2::weight));
        elements.stream()
                .flatMap(point -> tree.findNearestUnvisited(point, 100, Collections.emptySet())
                        .stream()
                        .map(neighbour -> new Edge2<>(point, neighbour.element(), neighbour.squaredDistance()))
                )
                .forEach(allPotentialEdges::add);

        var mstEdges = new ArrayList<Edge2<T>>();

        // Step 3: Loop through the edges of the graph, in ascending sorted order by their weight. For each edge:
        // - Test whether adding the edge to the current forest would create a cycle.
        // - If not, add the edge to the forest, combining two trees into a single tree.
        while (!allPotentialEdges.isEmpty() && dsu.getNumberOfSets() > 1) {
            var edge = allPotentialEdges.poll();

            if (!dsu.find(edge.from()).equals(dsu.find(edge.to()))) {
                dsu.union(edge.from(), edge.to());
                mstEdges.add(edge);
            }
        }

        if (dsu.getNumberOfSets() > 1) {
            // This is a clear indicator that the 100-neighbor limit was too low
            // to connect the "islands" in your real dataset.
            System.err.println("Warning: Result is fragmented into " + dsu.getNumberOfSets() + " circuits.");
        }

        // At the termination of the algorithm, the forest forms a minimum spanning forest of the graph.
        // If the graph is connected, the forest has a single component and forms a minimum spanning tree.
        return mstEdges;
    }
}
