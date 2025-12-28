package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.geometry.Point3D;
import io.github.maritims.advent_of_code.common.graph.DisjointSetUnion;
import io.github.maritims.advent_of_code.common.graph.Edge2;
import io.github.maritims.advent_of_code.common.graph.KDTree;

import java.util.ArrayList;
import java.util.Comparator;

public class Day8 extends PuzzleSolver<Integer, Integer> {
    private final int connectionsToEstablish;

    public Day8(int connectionsToEstablish) {
        if(connectionsToEstablish < 1) {
            throw new IllegalArgumentException("connectionsToEstablish must be at least 1.");
        }
        this.connectionsToEstablish = connectionsToEstablish;
    }

    @Override
    public Integer solveFirstPart() {
        var points = new ArrayList<>(Point3D.fromStrings(loadInput()));
        var tree = new KDTree<>(points, Point3D::getCoordinate, Point3D::getSquaredDistance);

        var shortestConnections = points.stream()
                .flatMap(point -> tree.findNearest(point, 10) // We'll most likely find the nearest neighbours very nearby in the tree. If we look at less than 10 we might end up missing some of the shortest connections.
                        .stream()
                        .map(neighbour -> new Edge2<>(point, neighbour.element(), neighbour.squaredDistance()))
                )
                .filter(element -> element.from().hashCode() < element.to().hashCode())
                .distinct()
                .sorted(Comparator.comparingDouble(Edge2::weight))
                .limit(connectionsToEstablish)
                .toList();

        // Establish circuits for the shortest connections.
        var circuits = new DisjointSetUnion<>(points);
        shortestConnections.forEach(circuit -> circuits.union(circuit.from(), circuit.to()));

        return circuits.getSizes()
                .stream()
                .sorted(Comparator.comparingInt(Integer::intValue).reversed())
                .limit(3) // Multiply the sizes of the three largest circuits.
                .reduce((o1, o2) -> o1 * o2)
                .orElseThrow();
    }

    @Override
    public Integer solveSecondPart() {
        return 0;
    }
}
