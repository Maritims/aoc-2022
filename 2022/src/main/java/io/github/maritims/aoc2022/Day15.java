package io.github.maritims.aoc2022;

import io.github.maritims.lib.Point;
import io.github.maritims.lib.Tuple2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day15 {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private List<Tuple2<Point, Point>> getTuples(String filePath) {
        return getFileContent(filePath).stream()
                .map(line -> {
                    Matcher matcher = Pattern.compile("x=([0-9\\-]+),\\sy=([0-9\\-]+)").matcher(line);
                    matcher.find();
                    Point location = new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                    matcher.find();
                    Point beacon = new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                    return new Tuple2<>(location, beacon);
                })
                .collect(Collectors.toList());
    }

    public Integer solvePartOne(String filePath, int lineToTest) {
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

    public Long solvePartTwo(String filePath, int maxY) {
        List<Tuple2<Point, Point>> sensorBeaconPairs = getTuples(filePath);
        // Find the only possible position for the distress beacon.
        // A position is possible if it's not covered by a sensor.
        // A position is covered by a sensor if it's Manhattan distance is less than or equal to the current sensor's distance to its beacon.
        // We cannot go below 0 or above max Y.

        List<List<Tuple2<Integer, Integer>>> rows = new ArrayList<>();
        for(int y = 0; y <= maxY; y++) {
            rows.add(new ArrayList<>());
        }

        long t0 = System.nanoTime();
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
                int maxX = Math.min(maxY, sensorBeaconPair.getItem1().getX() + tileDistanceX);

                // Store the range from the left-most covered x to the right-most covered x.
                rows.get(y).add(new Tuple2<>(minX, maxX));
            }
        }
        long seconds = (System.nanoTime() - t0) / 1000000000;
        System.out.println("Processed " + sensorBeaconPairs.size() + " sensors in " + seconds + " seconds");

        Comparator<Tuple2<Integer, Integer>> comparator = Comparator.comparing(Tuple2::getItem1);
        comparator = comparator.thenComparing(Tuple2::getItem2);
        for(int y = 0; y < rows.size(); y++) {
            // Find the X which is not covered by any range.
            List<Tuple2<Integer, Integer>> ranges = rows.get(y)
                    .stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());

            Iterator<Tuple2<Integer, Integer>> iterator = ranges.iterator();
            Tuple2<Integer, Integer> previous = iterator.next();
            while(iterator.hasNext()) {
                Tuple2<Integer, Integer> current = iterator.next();
                // If the current range is contained in the previous range we must skip the current range.
                if(current.getItem1() > previous.getItem1() && current.getItem2() < previous.getItem2()) {
                    continue;
                }

                // If the current x1 is greater than the previous x2 + 1 we've found the needle in our haystack.
                if(current.getItem1() > previous.getItem2() + 1) {
                    long frequency = (previous.getItem2() + 1) * 4000000L + y;
                    System.out.println("Located frequency between (" + previous + ") and (" + current + "): " + frequency);
                    return frequency;
                }
                previous = current;
            }
        }

        return null;
    }
}
