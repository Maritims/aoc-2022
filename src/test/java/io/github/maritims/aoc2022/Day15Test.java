package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class Day15Test extends PuzzleTest<Integer, Integer, Day15> {
    public Day15Test() {
        super(Day15.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("15/example.txt", 1)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return null;
    }
}
