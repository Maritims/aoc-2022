package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TwelfthPuzzleTest extends PuzzleTest<Integer, Integer, TwelfthPuzzle> {
    public TwelfthPuzzleTest() {
        super(TwelfthPuzzle.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("twelfth/example.txt", 31)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return null;
    }
}
