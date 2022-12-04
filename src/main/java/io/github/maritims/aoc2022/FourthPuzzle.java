package io.github.maritims.aoc2022;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FourthPuzzle extends Puzzle {
    public FourthPuzzle(String fileName) {
        super(fileName);
    }

    public Point getPoint(String[] assignments) {
        return new Point(Integer.parseInt(assignments[0]), Integer.parseInt(assignments[1]));
    }

    public List<Point> getPoints(String pairs) {
        return Arrays.stream(pairs.split(","))
                .map(assignmentPair -> assignmentPair.split("-"))
                .map(this::getPoint)
                .collect(Collectors.toList());
    }

    public boolean isOverlapping(Point start, Point end) {
        return start.x <= end.x && start.y >= end.y;
    }

    public boolean isOverlapping(List<Point> points) {
        return isOverlapping(points.get(0), points.get(1)) || isOverlapping(points.get(1), points.get(0));
    }

    @Override
    public Integer solvePartOne() {
        return (int) getFileContent().stream()
                .map(this::getPoints)
                .filter(this::isOverlapping)
                .count();
    }

    @Override
    public Integer solvePartTwo() {
        return null;
    }
}
