package io.github.maritims.aoc2023.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day11Test {

    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
            arguments(true, 2, 374),
            arguments(false, 2, 9445168)
        );
    }

    public static Stream<Arguments> solvePartTwo() {
        return Stream.of(
            arguments(true, 10, 1030),
            arguments(true, 100, 8410),
            arguments(false, 1000000, 742305960572L)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solvePartOne(boolean useSampleData, int multiplier, int expectedResult) {
        assertEquals(expectedResult, new Day11(useSampleData, multiplier).solvePartOne());
    }

    @ParameterizedTest
    @MethodSource
    void solvePartTwo(boolean useSampleData, int multiplier, long expectedResult) {
        assertEquals(expectedResult, new Day11(useSampleData, multiplier).solvePartTwo());
    }
}