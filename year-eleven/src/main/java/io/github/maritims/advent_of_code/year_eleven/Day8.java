package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.geometry.Point3D;
import io.github.maritims.advent_of_code.common.graph.NearestNeighbour;

public class Day8 extends PuzzleSolver<Integer, Integer> {
    @Override
    public Integer solveFirstPart() {
        var lines = loadInput();
        var points = Point3D.fromStrings(lines);
        var sortedPoints = NearestNeighbour.sort(points, Point3D::getEuclideanDistance, Double::compareTo);
        return 0;
    }

    @Override
    public Integer solveSecondPart() {
        return 0;
    }
}
