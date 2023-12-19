package io.github.maritims.aoc2023.day8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day8Test {

    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
            arguments(true, 6),
            arguments(false, 19637)
        );
    };

    public static Stream<Arguments> solvePartTwo() {
        return Stream.of(
            arguments(true, 6),
            arguments(false, 8811050362409L)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solvePartOne(boolean useSampleData, int expectedResult) {
        assertEquals(expectedResult, new Day8(useSampleData).solvePartOne());
    }

    @ParameterizedTest
    @MethodSource
    void solvePartTwo(boolean useSampleData, long expectedResult) {
        assertEquals(expectedResult, new Day8(useSampleData).solvePartTwo());
    }
}