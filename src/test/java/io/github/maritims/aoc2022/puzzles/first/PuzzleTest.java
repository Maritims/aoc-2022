package io.github.maritims.aoc2022.puzzles.first;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PuzzleTest {
    public static Stream<Arguments> getCalories() {
        return of(arguments("first/example.txt", 5));
    }

    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("first/example.txt", 24000),
                Arguments.arguments("first/input.txt", 67016)
        );
    }

    public static Stream<Arguments> solvePartTwo() {
        return Stream.of(
                Arguments.arguments("first/example.txt", 45000),
                Arguments.arguments("first/input.txt", 200116)
        );
    }

    @ParameterizedTest()
    @MethodSource
    public void getCalories(String filePath, int expectedResult) {
        assertEquals(expectedResult, new Puzzle(filePath).getCalories().size());
    }

    @ParameterizedTest
    @MethodSource
    public void solvePartOne(String filePath, int expectedResult) {
        assertEquals(expectedResult, new Puzzle(filePath).solvePartOne());
    }

    @ParameterizedTest
    @MethodSource
    public void solvePartTwo(String filePath, int expectedResult) {
        assertEquals(expectedResult, new Puzzle(filePath).solvePartTwo());
    }
}