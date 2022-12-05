package io.github.maritims.aoc2022;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FirstPuzzle extends Puzzle<Integer> {
    protected final List<List<String>> splitListToLists(List<String> input) {
        List<List<String>> listOfLists = new ArrayList<>();
        int previous = 0;

        for(int current = 0; current < input.size(); current++) {
            if("".equals(input.get(current))) {
                listOfLists.add(input.subList(previous, current));
                previous = current + 1;
            }
        }

        // Get the last part of the original list.
        listOfLists.add(input.subList(previous, input.size()));
        return listOfLists;
    }

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