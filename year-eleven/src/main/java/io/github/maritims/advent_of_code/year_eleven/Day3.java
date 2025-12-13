package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.DynamicProgramming;
import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.util.StringUtils;

import java.util.Arrays;
import java.util.Comparator;

public class Day3 extends PuzzleSolver<Long, Long> {
    public long findMaximumJoltage(String input, int simultaneousBatteries) {
        var joltages   = Arrays.stream(StringUtils.toIntArray(input))
                .mapToObj(Long::valueOf)
                .toArray(Long[]::new);
        return DynamicProgramming.findOptimalResult(
                joltages,
                simultaneousBatteries,
                (dp, currentJoltage) -> dp * 10 + currentJoltage,
                () -> 0L,
                () -> 0L,
                () -> -1L,
                Comparator.naturalOrder()
        );
    }

    @Override
    public Long solveFirstPart() {
        return loadInput()
                .stream()
                .mapToLong(str -> findMaximumJoltage(str, 2))
                .sum();
    }

    @Override
    public Long solveSecondPart() {
        return loadInput()
                .stream()
                .mapToLong(str -> findMaximumJoltage(str, 12))
                .sum();
    }
}
