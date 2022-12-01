package io.github.maritims.aoc2022.puzzles.first;

import io.github.maritims.aoc2022.puzzles.DailyPuzzle;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle extends DailyPuzzle {
    private final String fileName;

    public Puzzle(String fileName) {
        this.fileName = fileName;
    }

    protected List<Integer> getCalories() {
        List<String> allCalories = getFileContent(fileName);
        return splitListToLists(allCalories).stream()
                .map(caloriesPerElf -> caloriesPerElf.stream().mapToInt(Integer::parseInt).sum())
                .collect(Collectors.toList());
    }

    public Integer solvePartOne() {
        return getCalories().stream()
                .max(Comparator.comparingInt(calories -> calories))
                .orElse(0);
    }

    public Integer solvePartTwo() {
        List<Integer> sortedCalories = getCalories().stream()
                .sorted((c1, c2) -> Integer.compare(c2, c1))
                .collect(Collectors.toList());
        return sortedCalories.stream()
                .limit(3)
                .mapToInt(calories -> calories)
                .sum();
    }
}