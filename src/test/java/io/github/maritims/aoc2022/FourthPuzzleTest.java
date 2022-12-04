package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class FourthPuzzleTest extends PuzzleTest<FourthPuzzle> {
    public FourthPuzzleTest() {
        super(FourthPuzzle.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(Arguments.arguments("fourth/example.txt", 2), Arguments.arguments("fourth/input.txt", 580));
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return Stream.of(Arguments.arguments("fourth/example.txt", 4), Arguments.arguments("fourth/input.txt", 895));
    }
}
