package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.util.StringUtils;

import java.util.Arrays;
import java.util.stream.LongStream;

public class Day2 extends PuzzleSolver<Long, Long> {
    private Long solve(boolean greedy) {
        return loadInput()
                .stream()
                .flatMap(str -> Arrays.stream(str.split(",")))
                .mapToLong(line -> {
                    var parts          = line.split("-");
                    var startInclusive = Long.parseLong(parts[0]);
                    var endInclusive   = Long.parseLong(parts[1]);
                    return LongStream.rangeClosed(startInclusive, endInclusive)
                            .filter(i -> StringUtils.hasRepeatingSequenceOfDigits(String.valueOf(i), greedy))
                            .sum();
                })
                .sum();
    }

    @Override
    public Long solveFirstPart() {
        return solve(true);
    }

    @Override
    public Long solveSecondPart() {
        return solve(false);
    }
}
