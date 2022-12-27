package io.github.maritims.aoc2022;

import io.github.maritims.lib.Point;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.fill;
import static java.util.Arrays.stream;

public class Day15 extends Puzzle<Integer, Integer> {
    static class PointWithType extends Point {
        private final Character type;

        public PointWithType(int x, int y, Character type) {
            super(x, y);
            this.type = type;
        }

        public Character getType() {
            return type;
        }

        public int getManhattanDistance(Point destination) {
            return Math.abs(destination.getX() - getX()) + Math.abs(destination.getY() - getY());
        }
    }

    private static List<PointWithType> getPoints(String line) {
        List<PointWithType> sensors = new LinkedList<>();
        Matcher matcher = Pattern.compile("x=([0-9\\-]+),\\sy=([0-9\\-]+)").matcher(line);

        matcher.find();
        sensors.add(new PointWithType(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), 'S'));

        matcher.find();
        sensors.add(new PointWithType(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), 'B'));

        return sensors;
    }

    @Override
    public Integer solvePartOne(String filePath) throws IOException {
        List<String> lines = getFileContent(filePath);
        List<PointWithType> points = lines.stream()
                .map(Day15::getPoints)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        int minX = points.stream().min(Comparator.comparing(Point::getX)).map(Point::getX).orElse(0);
        int maxX = points.stream().max(Comparator.comparing(Point::getX)).map(Point::getX).orElse(0);
        int minY = points.stream().min(Comparator.comparing(Point::getY)).map(Point::getY).orElse(0);
        int maxY = points.stream().max(Comparator.comparing(Point::getY)).map(Point::getY).orElse(0);

        Character[][] grid = new Character[(maxY - minY) + 1][(maxX - minX) + 1];
        stream(grid).forEach(row -> fill(row, '.'));
        points.forEach(point -> grid[point.getY() - minY][point.getX() - minX] = point.getType());

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[row].length; col++) {
                // Can this tile be a beacon?
            }
        }

        render(grid);

        return null;
    }

    @Override
    public Integer solvePartTwo(String filePath) throws IOException {
        return null;
    }
}
