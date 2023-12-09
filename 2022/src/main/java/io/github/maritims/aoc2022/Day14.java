package io.github.maritims.aoc2022;

import io.github.maritims.lib.Point;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.maritims.lib.FileHelper.getFileContent;
import static java.util.Arrays.*;

public class Day14 extends Puzzle<Long, Long> {
    static class Line {
        private final Point[] points;

        public Line(List<Point> points) {
            this.points = points.toArray(new Point[0]);
        }

        public Point[] getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return getPoints()[0] + " -> " + getPoints()[getPoints().length - 1];
        }

        public static Line fromString(String s) {
            return new Line(stream(s.split(" -> "))
                    .map(point -> point.split(","))
                    .map(parts -> new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])))
                    .collect(Collectors.toList()));
        }
    }

    private static Point getMax(List<Line> lines) {
        Point max = new Point(0, 0);
        for(Line line : lines) {
            for(Point point : line.getPoints()) {
                if(point.getX() > max.getX()) {
                    max = new Point(point.getX(), max.getY());
                }
                if(point.getY() > max.getY()) {
                    max = new Point(max.getX(), point.getY());
                }
            }
        }
        return max;
    }

    private static Character[][] buildGrid(List<Line> lines) {
        Point max = getMax(lines);
        Character[][] grid = new Character[max.getY() + 1][max.getX() + 1];
        stream(grid).forEach(row -> fill(row, '.'));
        grid[0][500] = '+';

        for(Line line : lines) {
            Point source = null;
            for(int i = 0; i < line.getPoints().length; i++) {
                if(i == 0) {
                    source = line.getPoints()[i];
                    continue;
                }

                Point destination = line.getPoints()[i];
                if(source.getX().equals(destination.getX())) {
                    int y1 = Math.min(source.getY(), destination.getY());
                    int y2 = Math.max(source.getY(), destination.getY());
                    for(int y = y1; y <= y2; y++) {
                        grid[y][source.getX()] = '#';
                    }
                } else if(source.getY().equals(destination.getY())) {
                    int x1 = Math.min(source.getX(), destination.getX());
                    int x2 = Math.max(source.getX(), destination.getX());
                    for(int x = x1; x <= x2; x++) {
                        grid[source.getY()][x] = '#';
                    }
                }
                source = destination;
            }
        }

        return grid;
    }

    private void dropSand(Character[][] grid) {
        // Main sand loop.
        while(true) {
            // Our starting point: The origin of the sand entering the cave.
            // Resets on every outer loop iteration to avoid getting stuck in the inner loop forever.
            int x = 500;
            int y = 0;

            while (true) {
                // We're done if we're about to go outside the grid.
                if(y == grid.length - 1) {
                    return;
                }

                if(x == grid[0].length - 1) {
                    return;
                }

                // Can we go straight down?
                if (grid[y + 1][x] == '.') {
                    y++;
                }
                // Can we go down to the left?
                else if (grid[y + 1][x - 1] == '.') {
                    y++;
                    x--;
                }
                // Can we go down to the right?
                else if (grid[y + 1][x + 1] == '.') {
                    y++;
                    x++;
                }
                // Drop sand where we are.
                else {
                    grid[y][x] = 'o';
                    // The sand has been dropped. Break out of the inner loop to go again.
                    break;
                }
            }

            if(x == 500 && y == 0 || grid[0][500] == 'o') {
                // Nothing happened in the inner loop, or the sand has reached its origin point. It must mean we're done.
                break;
            }
        }
    }

    @Override
    public Long solvePartOne(String filePath) throws IOException {
        List<Line> lines = getFileContent(filePath).stream()
                .map(Line::fromString)
                .collect(Collectors.toList());

        Character[][] grid = buildGrid(lines);
        dropSand(grid);
        render(grid);

        return stream(grid).mapToLong(line -> stream(line)
                .filter(tile -> tile == 'o')
                .count()).sum();
    }

    @Override
    public Long solvePartTwo(String filePath) throws IOException {
        List<Line> lines = getFileContent(filePath).stream()
                .map(Line::fromString)
                .collect(Collectors.toList());
        Point max = getMax(lines);
        lines.add(new Line(asList(new Point(0, max.getY() + 2), new Point(700, max.getY() + 2))));

        Character[][] grid = buildGrid(lines);
        dropSand(grid);
        render(grid);

        return stream(grid).mapToLong(line -> stream(line)
                .filter(tile -> tile == 'o')
                .count()).sum();
    }
}
