package io.github.maritims.aoc2022;

import io.github.maritims.lib.Point;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.fill;
import static java.util.Arrays.stream;

public class Day15 extends Puzzle<Integer, Integer> {
    static class Sensor {
        private final Point location;

        Sensor(Point location) {
            this.location = location;
        }

        public Point getLocation() {
            return location;
        }
    }

    private static final Pattern pattern = Pattern.compile("x=([0-9\\-]+),\\sy=([0-9\\-]+)");

    private List<Point> getSensorLocations(String line) {
        List<Point> points = new LinkedList<>();
        Matcher matcher = pattern.matcher(line);
        while(matcher.find()) {
            points.add(new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
        }
        return points;
    }

    @Override
    public Integer solvePartOne(String filePath) throws IOException {
        List<String> lines = getFileContent(filePath);
        List<Point> sensors = lines.stream()
                .map(this::getSensorLocations)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        int maxX = 0;
        int maxY = 0;
        for(Point sensorLocation : sensors) {
            if(sensorLocation.getX() > maxX) {
                maxX = sensorLocation.getX();
            }
            if(sensorLocation.getY() > maxY) {
                maxY = sensorLocation.getY();
            }
        }

        Character[][] grid = new Character[maxY + 1][maxX + 1];
        stream(grid).forEach(row -> fill(row, '.'));
        render(grid);

        log(sensors.toString(), true);

        return null;
    }

    @Override
    public Integer solvePartTwo(String filePath) throws IOException {
        return null;
    }
}
