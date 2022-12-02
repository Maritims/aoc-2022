package io.github.maritims.aoc2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecondPuzzleTest {
    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("second/example.txt", 15),
                Arguments.arguments("second/input.txt", 13565)
        );
    }

    public static Stream<Arguments> solvePartTwo() {
        return Stream.of(
                Arguments.arguments("second/example.txt", 12),
                Arguments.arguments("second/input.txt", 12424)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solvePartOne(String filePath, int expectedResult) {
        assertEquals(expectedResult, new SecondPuzzle(filePath).solvePartOne());
    }

    @ParameterizedTest
    @MethodSource
    void solvePartTwo(String filePath, int expectedResult) {
        assertEquals(expectedResult, new SecondPuzzle(filePath).solvePartTwo());
    }
}