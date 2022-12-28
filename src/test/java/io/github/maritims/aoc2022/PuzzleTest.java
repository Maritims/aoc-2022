package io.github.maritims.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class PuzzleTest<TPartOneOutput, TPartTwoOutput, T extends Puzzle<TPartOneOutput, TPartTwoOutput>> {
    private final Class<T> clazz;
    protected T puzzle;

    public PuzzleTest(Class<T> clazz) {
        this.clazz = clazz;
    }

    @BeforeEach
    public void setUp() throws InstantiationException, IllegalAccessException {
        puzzle = clazz.newInstance();
    }

    @ParameterizedTest
    @MethodSource
    public void solvePartOne(String filePath, TPartOneOutput expectedResult) throws IOException {
        assertEquals(expectedResult, puzzle.solvePartOne(filePath));
    }

    public abstract Stream<Arguments> solvePartOne();

    @ParameterizedTest
    @MethodSource
    public void solvePartTwo(String filePath, TPartTwoOutput expectedResult) throws IOException {
        assertEquals(expectedResult, puzzle.solvePartTwo(filePath));
    }

    public abstract Stream<Arguments> solvePartTwo();
}
