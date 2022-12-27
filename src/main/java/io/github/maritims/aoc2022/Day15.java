package io.github.maritims.aoc2022;

import io.github.maritims.lib.Point;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.fill;
import static java.util.Arrays.stream;

public class Day15 extends Puzzle<Integer, Integer> {
    static class Sensor {
        private final Point location;
        private final Point beacon;

        Sensor(Point location, Point beacon) {
            this.location = location;
            this.beacon = beacon;
        }

        public Point getLocation() {
            return location;
        }

        public Point getBeacon() {
            return beacon;
        }

        public int getManhattanDistance(int x, int y) {
            return Math.abs(location.getX() - x) + Math.abs(location.getY() - y);
        }

        public int getManhattanDistance() {
            return getManhattanDistance(beacon.getX(), beacon.getY());
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static Sensor getSensor(String line) {
        Matcher matcher = Pattern.compile("x=([0-9\\-]+),\\sy=([0-9\\-]+)").matcher(line);
        matcher.find();
        Point location = new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        matcher.find();
        Point beacon = new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        return new Sensor(location, beacon);
    }

    @Override
    public Integer solvePartOne(String filePath) throws IOException {
        List<Sensor> sensors = getFileContent(filePath).stream()
                .map(Day15::getSensor)
                .collect(Collectors.toList());

        int minX = sensors.stream().map(sensor -> Math.min(sensor.getLocation().getX(), sensor.getBeacon().getX())).min(Integer::compareTo).orElse(0);
        int maxX = sensors.stream().map(sensor -> Math.max(sensor.getLocation().getX(), sensor.getBeacon().getX())).max(Integer::compareTo).orElse(0);
        int minY = sensors.stream().map(sensor -> Math.min(sensor.getLocation().getY(), sensor.getBeacon().getY())).min(Integer::compareTo).orElse(0);
        int maxY = sensors.stream().map(sensor -> Math.max(sensor.getLocation().getY(), sensor.getBeacon().getY())).max(Integer::compareTo).orElse(0);

        Character[][] grid = new Character[(maxY - minY) + 1][(maxX - minX) + 1];
        stream(grid).forEach(row -> fill(row, '.'));
        sensors.forEach(sensor -> {
            grid[sensor.getLocation().getY() - minY][sensor.getLocation().getX() - minX] = 'S';
            grid[sensor.getBeacon().getY() - minY][sensor.getBeacon().getX() - minX] = 'B';
        });

        for(Sensor sensor : sensors) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    int x = col + minX;
                    int d = sensor.getManhattanDistance(x, row);
                    if (grid[row][col] == '.' && sensor.getManhattanDistance() >= d) {
                        grid[row][col] = '#';
                    }
                }
            }
        }

        render(grid);

        int sum = 0;
        for(int col = 0; col < grid[10 + minY].length; col++) {
            if(grid[10 + minY][col] == '#') {
                sum++;
            }
        }

        return sum;
    }

    @Override
    public Integer solvePartTwo(String filePath) throws IOException {
        return null;
    }
}
