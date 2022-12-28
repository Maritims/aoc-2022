package io.github.maritims.aoc2022;

import io.github.maritims.lib.Point;
import io.github.maritims.lib.Tuple2;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day15 extends Puzzle<Integer, Integer> {
    private final int lineToTest;

    public Day15(int lineToTest) {
        this.lineToTest = lineToTest;
    }

    @Override
    public Integer solvePartOne(String filePath) throws IOException {
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

    @Override
    public Integer solvePartTwo(String filePath) throws IOException {
        return null;
    }
}
