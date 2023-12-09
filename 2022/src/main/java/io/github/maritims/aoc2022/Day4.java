package io.github.maritims.aoc2022;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day4 extends Puzzle<Integer, Integer> {
    public int[] getRange(String[] assignments) {
        return new int[] { Integer.parseInt(assignments[0]), Integer.parseInt(assignments[1]) };
    }

    public List<int[]> getRanges(String pairs) {
        return Arrays.stream(pairs.split(","))
                .map(pair -> pair.split("-"))
                .map(this::getRange)
                .collect(Collectors.toList());
    }

    public boolean isOverlapping(int[] x, int[] y) {
        return x[0] <= y[0] && x[1] >= y[1];
    }

    public boolean isOverlapping(List<int[]> ranges) {
        return isOverlapping(ranges.get(0), ranges.get(1)) || isOverlapping(ranges.get(1), ranges.get(0));
    }

    public boolean isIntersecting(int[] x, int[] y) {
        return (x[0] >= y[0] && x[0] <= y[1]) || (x[1] >= y[0] && x[1] <= y[1]);
    }

    public boolean isIntersecting(List<int[]> ranges) {
        return isIntersecting(ranges.get(0), ranges.get(1)) || isIntersecting(ranges.get(1), ranges.get(0));
    }

    @Override
    public Integer solvePartOne(String filePath) {
        return (int) getFileContent(filePath).stream()
                .map(this::getRanges)
                .filter(this::isOverlapping)
                .count();
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return (int) getFileContent(filePath).stream()
                .map(this::getRanges)
                .filter(this::isIntersecting)
                .count();
    }
}
