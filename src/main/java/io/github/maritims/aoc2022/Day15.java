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
        LinkedHashSet<String> impossibleTiles = new LinkedHashSet<>();
        for(Tuple2<Point, Point> tuple : tuples) {
            // Is this sensor on the line we're testing?
            if(tuple.getItem1().getY() == 10) {
                impossibleTiles.add(tuple.getItem1().toString());
            }

            // Is this beacon on the line we're testing?
            if(tuple.getItem2().getY() == 10) {
                impossibleTiles.add(tuple.getItem2().toString());
            }

            // A tile is too close if distanceToTile is less than or equal to distanceToBeacon
            int distanceToBeacon = getManhattanDistance(tuple.getItem1(), tuple.getItem2());

            // How do we find all the tiles to test?
        }

        System.out.println(impossibleTiles);
        return impossibleTiles.size();
    }

    @Override
    public Integer solvePartTwo(String filePath) throws IOException {
        return null;
    }
}
