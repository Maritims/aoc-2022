package io.github.maritims.aoc2022;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day1 extends Puzzle<Integer, Integer> {
    protected List<Integer> getCalories(String filePath) {
        return splitListToLists(getFileContent(filePath)).stream()
                .map(caloriesPerElf -> caloriesPerElf.stream().mapToInt(Integer::parseInt).sum())
                .collect(Collectors.toList());
    }

    @Override
    public Integer solvePartOne(String filePath) {
        return getCalories(filePath).stream()
                .max(Comparator.comparingInt(calories -> calories))
                .orElse(0);
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return getCalories(filePath).stream()
                .sorted((c1, c2) -> Integer.compare(c2, c1))
                .limit(3)
                .mapToInt(calories -> calories)
                .sum();
    }
}