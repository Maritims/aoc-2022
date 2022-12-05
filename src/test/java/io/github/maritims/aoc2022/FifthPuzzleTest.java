package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

class FifthPuzzleTest extends PuzzleTest<String, FifthPuzzle> {

    public FifthPuzzleTest() {
        super(FifthPuzzle.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(Arguments.arguments("fifth/example.txt", "CMZ"), Arguments.arguments("fifth/input.txt", "TLNGFGMFN"));
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return null;
    }
}