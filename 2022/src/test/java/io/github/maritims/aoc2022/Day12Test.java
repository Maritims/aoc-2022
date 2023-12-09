package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class Day12Test extends PuzzleTest<Integer, Integer, Day12> {
    public Day12Test() {
        super(Day12.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("twelfth/example.txt", 31),
                Arguments.arguments("twelfth/input.txt", 339)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return Stream.of(
                Arguments.arguments("twelfth/example.txt", 29),
                Arguments.arguments("twelfth/input.txt", 332)
        );
    }
}
