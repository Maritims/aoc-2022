package io.github.maritims.aoc2022;

import io.github.maritims.lib.Point;
import io.github.maritims.lib.Tuple2;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day15 {
    private List<Tuple2<Point, Point>> getTuples(String filePath) {
        List<Tuple2<Point, Point>> tuples = getFileContent(filePath).stream()
                .map(line -> {
                    Matcher matcher = Pattern.compile("x=([0-9\\-]+),\\sy=([0-9\\-]+)").matcher(line);
                    matcher.find();
                    Point location = new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                    matcher.find();
                    Point beacon = new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                    return new Tuple2<>(location, beacon);
                })
                .collect(Collectors.toList());
        return tuples;
    }

    public Integer solvePartOne(String filePath, int lineToTest) throws IOException {
        List<Tuple2<Point, Point>> tuples = getTuples(filePath);

        // On a specific line how many tiles cannot possibly contain a beacon?
        LinkedHashSet<Integer> impossibleXes = new LinkedHashSet<>();
        LinkedHashSet<Integer> beaconXes = new LinkedHashSet<>();
        for(Tuple2<Point, Point> tuple : tuples) {
            // We need to know how many beacons there are on the line we're testing.
            if(tuple.getItem2().getY() == lineToTest) {
                beaconXes.add(tuple.getItem2().getX());
            }

            // What's the distance between the sensor and the beacon?
            int distance = Math.abs(tuple.getItem2().getX() - tuple.getItem1().getX()) + Math.abs(tuple.getItem2().getY() - tuple.getItem1().getY());

            // The tile is possible if it's further away than the sensor-beacon distance along any axis.
            int tileDistanceY = Math.abs(lineToTest - tuple.getItem1().getY());
            if(tileDistanceY > distance) {
                continue;
            }

            // The current sensor is of course on an impossible tile.
            impossibleXes.add(tuple.getItem1().getX());

            // Check all tiles to either side up to tileDistanceX.
            int tileDistanceX = distance - tileDistanceY;
            IntStream.rangeClosed(tuple.getItem1().getX() - tileDistanceX, tuple.getItem1().getX()).forEach(impossibleXes::add);
            IntStream.rangeClosed(tuple.getItem1().getX(), tuple.getItem1().getX() + tileDistanceX).forEach(impossibleXes::add);
        }

        return impossibleXes.size() - beaconXes.size();
    }

    public Integer solvePartTwo(String filePath, int maxY) throws IOException {
        List<Tuple2<Point, Point>> sensorBeaconPairs = getTuples(filePath);
        // Find the only possible position for the distress beacon.
        // A position is possible if it's not covered by a sensor.
        // A position is covered by a sensor if it's Manhattan distance is less than or equal to the current sensor's distance to its beacon.
        // We cannot go below 0 or above max Y.

        List<List<Tuple2<Integer, Integer>>> rows = new LinkedList<>();
        for(int y = 0; y <= maxY; y++) {
            rows.add(new LinkedList<>());
        }

        for(Tuple2<Point, Point> sensorBeaconPair : sensorBeaconPairs) {
            // The sensor range, i.e. its Manhattan distance to its beacon.
            int distance = Math.abs(sensorBeaconPair.getItem2().getX() - sensorBeaconPair.getItem1().getX()) + Math.abs(sensorBeaconPair.getItem2().getY() - sensorBeaconPair.getItem1().getY());
            // The lowest Y covered by the sensor range.
            int minCoveredY = Math.max(0, sensorBeaconPair.getItem1().getY() - distance);
            // The highest Y covered by the sensor range.
            int maxCoveredY = Math.min(maxY, sensorBeaconPair.getItem1().getY() + distance);

            for (int y = minCoveredY; y <= maxCoveredY; y++) {
                // A tile with a distance less than or equal to the current sensor-beacon distance is already covered.
                int tileDistanceY = Math.abs(y - sensorBeaconPair.getItem1().getY());

                // Every tile to the left or right of the sensor with a gap of up to tileDistanceX is covered by the sensor.
                int tileDistanceX = Math.abs(distance - tileDistanceY);
                int minX = Math.max(0, sensorBeaconPair.getItem1().getX() - tileDistanceX);
                int maxX = Math.min(20, sensorBeaconPair.getItem1().getX() + tileDistanceX);

                // Store the range from the left-most covered x to the right-most covered x.
                rows.get(y).add(new Tuple2<>(minX, maxX));
            }
        }

        // Compare by the lower x.
        Comparator<Tuple2<Integer, Integer>> comparator = Comparator.comparing(Tuple2::getItem1);
        // ..and then by the upper x.
        comparator = comparator.thenComparing(Tuple2::getItem2);

        // Sort the ranges representing the covered tilesN.
        for(int y = 0; y < rows.size(); y++) {
            rows.set(y, rows.get(y)
                    .stream()
                    .sorted(comparator)
                    .collect(Collectors.toList()));
            System.out.println(y + ": " + rows.get(y));
        }

        // The point we want is the one that's not covered by any sensor.

        return null;
    }
}
