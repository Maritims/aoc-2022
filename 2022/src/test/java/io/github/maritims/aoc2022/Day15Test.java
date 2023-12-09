package io.github.maritims.aoc2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test {
    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("15/example.txt", 10, 26),
                Arguments.arguments("15/input.txt", 2000000, 4961647)
        );
    }

    public static Stream<Arguments> solvePartTwo() {
        return Stream.of(
                Arguments.arguments("15/example.txt", 20, 56000011L),
                Arguments.arguments("15/input.txt", 4000000, 12274327017867L)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void solvePartOne(String filePath, int lineToTest, long expectedResult) {
        // arrange
        Day15 sut = new Day15();

        // act
        int result = sut.solvePartOne(filePath, lineToTest);

        // assert
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource
    public void solvePartTwo(String filePath, int maxY, int expectedResult) {
        // arrange
        Day15 sut = new Day15();

        // act
        Long result = sut.solvePartTwo(filePath, maxY);

        // assert
        assertEquals(expectedResult, result);
    }
}
