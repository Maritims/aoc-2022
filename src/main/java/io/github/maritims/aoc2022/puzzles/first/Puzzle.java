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

    protected List<Elf> getElves() {
        List<String> allCalories = getFileContent(fileName);
        return splitListToLists(allCalories).stream()
                .map(caloriesPerElf -> caloriesPerElf.stream().mapToInt(Integer::parseInt).sum())
                .map(Elf::new)
                .collect(Collectors.toList());
    }

    public Integer solvePartOne() {
        return getElves().stream()
                .max(Comparator.comparing(Elf::getCalories))
                .map(Elf::getCalories)
                .orElse(0);
    }

    public Integer solvePartTwo() {
        List<Elf> sortedElves = getElves().stream()
                .sorted((elf1, elf2) -> Integer.compare(elf2.getCalories(), elf1.getCalories()))
                .collect(Collectors.toList());
        return sortedElves.stream()
                .limit(3)
                .mapToInt(Elf::getCalories).sum();
    }
}