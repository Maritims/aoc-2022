package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class Day2Test extends PuzzleTest<Integer, Integer, Day2> {
    public Day2Test() {
        super(Day2.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(Arguments.arguments("second/example.txt", 15), Arguments.arguments("second/input.txt", 13565));
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return Stream.of(Arguments.arguments("second/example.txt", 12), Arguments.arguments("second/input.txt", 12424));
    }
}
