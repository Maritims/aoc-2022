package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TenthPuzzleTest extends PuzzleTest<Integer, TenthPuzzle> {
    public TenthPuzzleTest() {
        super(TenthPuzzle.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("tenth/example.txt", 13140),
                Arguments.arguments("tenth/input.txt", 15880)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return null;
    }
}
