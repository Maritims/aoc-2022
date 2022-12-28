package io.github.maritims.aoc2022;

import io.github.maritims.lib.Point;
import io.github.maritims.lib.Tuple2;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.fill;
import static java.util.Arrays.stream;

public class Day15 extends Puzzle<Integer, Integer> {
    public int getManhattanDistance(Point p1, Point p2) {
        return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
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
        for(Tuple2<Point, Point> tuple : tuples) {
            // Is this sensor on the line we're testing?
            if(tuple.getItem1().getY() == 10) {
                impossibleXes.add(tuple.getItem2().getX());
            }

            // A tile is too close if distanceToTile is less than or equal to distanceToBeacon
            int distance = Math.abs(tuple.getItem2().getX() - tuple.getItem1().getX()) + Math.abs(tuple.getItem2().getY() - tuple.getItem1().getY());
            int tileDistanceY = Math.abs(10 - tuple.getItem1().getY());

            // The tile is possible if it's further away than the sensor-beacon distance along any axis.
            if(tileDistanceY > distance) {
                continue;
            }

            // Check all tiles to the left and right but no further to either side than tileDistanceX.
            int tileDistanceX = distance - tileDistanceY;

            // The current sensor is of course on an impossible tile.
            impossibleXes.add(tuple.getItem1().getX());

            // Any tile to the left is impossible.
            IntStream.of(tuple.getItem1().getX() - tileDistanceX, tuple.getItem1().getX()).forEach(impossibleXes::add);

            // Any tile to the right is impossible.
            IntStream.of(tuple.getItem1().getX(), tuple.getItem1().getX() + tileDistanceX).forEach(impossibleXes::add);
        }

        System.out.println(impossibleXes);
        return impossibleXes.size();
    }

    @Override
    public Integer solvePartTwo(String filePath) throws IOException {
        return null;
    }
}
