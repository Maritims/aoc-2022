package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class Day14Test extends PuzzleTest<Long, Long, Day14> {
    public Day14Test() {
        super(Day14.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("14/example.txt", 24L),
                Arguments.arguments("14/input.txt", 737L)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return Stream.of(
                Arguments.arguments("14/example.txt", 93L),
                Arguments.arguments("14/input.txt", 28145L)
        );
    }
}
