package io.github.maritims.aoc2022;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EightPuzzleTest extends PuzzleTest<Integer, EightPuzzle> {

    public EightPuzzleTest() {
        super(EightPuzzle.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("eight/example.txt", 21),
                Arguments.arguments("eight/input.txt", 1711)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return null;
    }

    public Stream<Arguments> getVisible() {
        return Stream.of(
                Arguments.arguments(new boolean[5], "65332", new boolean[] { true, false, false, false, false }),
                Arguments.arguments(new boolean[5], "23356", new boolean[] { true, true, false, true, true })
        );
    }

    public Stream<Arguments> getVisibleFromEitherSide() {
        return Stream.of(
                Arguments.arguments(new boolean[5], "65332", new boolean[] { true, true, false, true, true }),
                Arguments.arguments(new boolean[5], "35353", new boolean[] { true, true, false, true, true })
        );
    }

    @ParameterizedTest
    @MethodSource
    public void getVisible(boolean[] gridLine, String treeLine, boolean[] expectedResult) {
        assertArrayEquals(expectedResult, new EightPuzzle().getVisibleFromTheStart(gridLine, treeLine));
    }

    @ParameterizedTest
    @MethodSource
    public void getVisibleFromEitherSide(boolean[] gridLine, String treeLine, boolean[] expectedResult) {
        assertArrayEquals(expectedResult, new EightPuzzle().getVisibleFromEitherSide(gridLine, treeLine));
    }

    @Test
    public void getNormalizedStringColumn() {
        EightPuzzle sut = new EightPuzzle();
        List<String> fileContent = sut.getFileContent("eight/example.txt");
        assertEquals("32633", sut.getRotatedColumn(fileContent, 0));
    }

    @Test
    public void getNormalizedBooleanColumn() {
        EightPuzzle sut = new EightPuzzle();
        boolean[][] grid = new boolean[][] {
                new boolean[] { true, true, true },
                new boolean[] { false, true, false },
                new boolean[] { true, true, false }
        };
        assertArrayEquals(new boolean[] { true, false, true }, sut.getRotatedColumn(grid, 0));
    }
}