package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class Day11Test extends PuzzleTest<Integer, Long, Day11> {
    public Day11Test() {
        super(Day11.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("eleventh/example.txt", 10605),
                Arguments.arguments("eleventh/input.txt", 58794)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return Stream.of(
                Arguments.arguments("eleventh/example.txt", 2713310158L),
                Arguments.arguments("eleventh/input.txt", 20151213744L)
        );
    }
}
