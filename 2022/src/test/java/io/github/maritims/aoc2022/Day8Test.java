package io.github.maritims.aoc2022;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static io.github.maritims.lib.FileHelper.getFileContent;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test extends PuzzleTest<Integer, Integer, Day8> {

    public Day8Test() {
        super(Day8.class);
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
        return Stream.of(
                Arguments.arguments("eight/example.txt", 8),
                Arguments.arguments("eight/input.txt", 301392)
        );
    }

    public Stream<Arguments> getVisibleFromEitherSide() {
        return Stream.of(
                Arguments.arguments(new Boolean[5], "65332", new Boolean[] { true, true, null, true, true }),
                Arguments.arguments(new Boolean[5], "35353", new Boolean[] { true, true, null, true, true })
        );
    }

    @ParameterizedTest
    @MethodSource
    public void getVisibleFromEitherSide(Boolean[] gridLine, String treeLine, Boolean[] expectedResult) {
        assertArrayEquals(expectedResult, new Day8().getVisibleFromEitherSide(gridLine, treeLine));
    }

    @Test
    public void getNormalizedStringColumn() {
        Day8 sut = new Day8();
        List<String> fileContent = getFileContent("eight/example.txt");
        assertEquals("32633", sut.getRotatedColumn(fileContent, 0));
    }

    @Test
    public void getNormalizedBooleanColumn() {
        Day8 sut = new Day8();
        Boolean[][] grid = new Boolean[][] {
                new Boolean[] { true, true, true },
                new Boolean[] { false, true, false },
                new Boolean[] { true, true, false }
        };
        assertArrayEquals(new Boolean[] { true, false, true }, sut.getRotatedColumn(grid, 0));
    }

    public Stream<Arguments> getUpwardsFacingScenicScore() {
        return Stream.of(
                Arguments.arguments(0, 0, 0),
                Arguments.arguments(1, 1, 1),
                Arguments.arguments(2, 2, 1),
                Arguments.arguments(3, 3, 3),
                Arguments.arguments(2, 3, 2)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void getUpwardsFacingScenicScore(int row, int col, int expectedResult) {
        Day8 sut = new Day8();
        List<String> lines = getFileContent("eight/example.txt");
        int[][] grid = sut.getGrid(lines);
        int scenicScore = sut.getUpwardsFacingScenicScore(grid, row, col);
        assertEquals(expectedResult, scenicScore);
    }

    public Stream<Arguments> getDownwardsFacingScenicScore() {
        return Stream.of(
                Arguments.arguments(0, 0, 2),
                Arguments.arguments(0, 1, 1),
                Arguments.arguments(0, 2, 1),
                Arguments.arguments(0, 3, 4)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void getDownwardsFacingScenicScore(int row, int col, int expectedResult) {
        Day8 sut = new Day8();
        List<String> lines = getFileContent("eight/example.txt");
        int[][] grid = sut.getGrid(lines);
        int scenicScore = sut.getDownwardsFacingScenicScore(grid, row, col);
        assertEquals(expectedResult, scenicScore);
    }

    public Stream<Arguments> getScenicScore() {
        return Stream.of(
                Arguments.arguments(1, 2, 4),
                Arguments.arguments(3, 2, 8)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void getScenicScore(int row, int col, int expectedResult) {
        Day8 sut = new Day8();
        List<String> lines = getFileContent("eight/example.txt");
        int[][] grid = sut.getGrid(lines);
        assertEquals(expectedResult, sut.getScenicScore(grid, row, col));
    }
}