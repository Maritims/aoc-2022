package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.geometry.Point3D;
import io.github.maritims.advent_of_code.common.graph.DisjointSetUnion;
import io.github.maritims.advent_of_code.common.graph.Edge2;
import io.github.maritims.advent_of_code.common.graph.KDTree;
import io.github.maritims.advent_of_code.common.graph.MinimumSpanningForest;

import java.util.*;

public class Day8 extends PuzzleSolver<Integer, Double> {
    private final long connectionsToEstablish;

    public Day8(long connectionsToEstablish) {
        if (connectionsToEstablish < 1) {
            throw new IllegalArgumentException("connectionsToEstablish must be at least 1.");
        }
        this.connectionsToEstablish = connectionsToEstablish;
    }

    private static void establishShortestConnections(
            List<Point3D> points,
            KDTree<Point3D> tree,
            DisjointSetUnion<Point3D> dsu,
            long limit
    ) {
        var visited = new HashSet<Point3D>();
        points.stream()
                .flatMap(point -> tree.findNearestUnvisited(point, 500, visited).stream()
                        .map(neighbour -> new Edge2<>(point, neighbour.element(), neighbour.squaredDistance()))
                )
                .filter(edge -> edge.from().hashCode() < edge.to().hashCode())
                .distinct()
                .sorted(Comparator.comparingDouble(Edge2::weight))
                .limit(limit)
                .forEach(edge -> dsu.union(edge.from(), edge.to()));
    }

    @Override
    public Integer solveFirstPart() {
        var points = new ArrayList<>(Point3D.fromStrings(loadInput()));
        var tree = new KDTree<>(points, Point3D::getCoordinate, Point3D::getSquaredDistance);
        var dsu = new DisjointSetUnion<>(points);

        establishShortestConnections(points, tree, dsu, connectionsToEstablish);

        return dsu.getSizes()
                .stream()
                .sorted(Comparator.comparingInt(Integer::intValue).reversed())
                .limit(3) // Multiply the sizes of the three largest circuits.
                .reduce((o1, o2) -> o1 * o2)
                .orElseThrow();
    }

    @Override
    public Double solveSecondPart() {
        var points = new ArrayList<>(Point3D.fromStrings(loadInput()));
        return Optional.of(MinimumSpanningForest.of(points, Point3D::getCoordinate, Point3D::getSquaredDistance))
                .map(List::getLast)
                .map(lastEdge -> lastEdge.from().x() * lastEdge.to().x())
                .orElseThrow();
    }
}
