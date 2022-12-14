package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class EleventhPuzzleTest extends PuzzleTest<Integer, Integer, EleventhPuzzle> {
    public EleventhPuzzleTest() {
        super(EleventhPuzzle.class);
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
        return null;
    }
}
