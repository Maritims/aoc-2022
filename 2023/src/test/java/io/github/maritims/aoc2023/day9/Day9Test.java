package io.github.maritims.aoc2023.day9;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day9Test {

    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
            arguments(true, 114),
            arguments(false, 2105961943)
        );
    }

    public static Stream<Arguments> solvePartTwo() {
        return Stream.of(
            arguments(true, 2),
            arguments(false, 0)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solvePartOne(boolean useSampleData, int expectedResult) {
        assertEquals(expectedResult, new Day9(useSampleData).solvePartOne());
    }

    @ParameterizedTest
    @MethodSource
    void solvePartTwo(boolean useSampleData, int expectedResult) {
        assertEquals(expectedResult, new Day9(useSampleData).solvePartTwo());
    }
}