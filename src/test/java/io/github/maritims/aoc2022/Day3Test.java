package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class Day3Test extends PuzzleTest<Integer, Integer, Day3> {
    public Day3Test() {
        super(Day3.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(Arguments.arguments("third/example.txt", 157), Arguments.arguments("third/input.txt", 7746));
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return Stream.of(Arguments.arguments("third/example.txt", 70), Arguments.arguments("third/input.txt", 2604));
    }
}
