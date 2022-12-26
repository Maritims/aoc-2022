package io.github.maritims.aoc2022;

import io.github.maritims.lib.GridRenderer;
import io.github.maritims.lib.Point;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.fill;
import static java.util.Arrays.stream;

public class Day14 extends Puzzle<Integer, Integer> {
    static class Line {
        private final Point[] points;

        public Line(List<Point> points) {
            this.points = points.toArray(new Point[0]);
        }

        public Point[] getPoints() {
            return points;
        }

        public static List<Point> getPointsBetween(Point source, Point destination) {
            List<Point> pointsBetween = new LinkedList<>();
            if(source.getX().equals(destination.getX())) {
                int y1 = Math.min(source.getY(), destination.getY());
                int y2 = Math.max(source.getY(), destination.getY());
                for(int y = y1; y <= y2; y++) {
                    pointsBetween.add(new Point(source.getX(), y));
                }
            } else if(source.getY().equals(destination.getY())) {
                int x1 = Math.min(source.getX(), destination.getX());
                int x2 = Math.max(source.getX(), destination.getX());
                for(int x = x1; x <= x2; x++) {
                    pointsBetween.add(new Point(x, source.getY()));
                }
            }
            return pointsBetween;
        }

        @Override
        public String toString() {
            return getPoints()[0] + " -> " + getPoints()[getPoints().length - 1];
        }
    }

    Line getLine(String s) {
        return new Line(stream(s.split(" -> "))
                .map(point -> point.split(","))
                .map(parts -> new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])))
                .collect(Collectors.toList()));
    }

    @Override
    public Integer solvePartOne(String filePath) throws IOException {
        List<Line> lines = getFileContent(filePath).stream()
                .map(this::getLine)
                .collect(Collectors.toList());

        int minX = 0;
        int maxX = 0;
        int maxY = 0;
        for(Line line : lines) {
            for(Point point : line.getPoints()) {
                if(minX == 0 || minX > point.getX()) {
                    minX = point.getX();
                }
                if(point.getX() > maxX) {
                    maxX = point.getX();
                }
                if(point.getY() > maxY) {
                    maxY = point.getY();
                }
            }
        }

        Character[][] grid = new Character[maxY + 1][maxX + 1];
        stream(grid).forEach(row -> fill(row, '.'));
        grid[0][500] = '+';

        for(Line line : lines) {
            Point p1 = null;
            for(int i = 0; i < line.getPoints().length; i++) {
                if(i == 0) {
                    p1 = line.getPoints()[i];
                    continue;
                }

                Point p2 = line.getPoints()[i];
                List<Point> pointsBetween = Line.getPointsBetween(p1, p2);
                for(Point pointBetween : pointsBetween) {
                    try {
                        grid[pointBetween.getY()][pointBetween.getX()] = '#';
                    } catch(ArrayIndexOutOfBoundsException e) {
                        throw new RuntimeException(e);
                    }
                }
                p1 = p2;
            }
        }

        StringWriter sw = new StringWriter();
        new GridRenderer<Character>().render(grid, sw, (element, writer) -> {
            try {
                writer.write(element);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        log(sw.toString(), true);

        return null;
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}
