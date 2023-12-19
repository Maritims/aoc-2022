package io.github.maritims.aoc2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
            Arguments.arguments(true, 4),
            Arguments.arguments(false, 6831)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solvePartOne(boolean useSampleData, int expectedResult) {
        assertEquals(expectedResult, new Day10(useSampleData).solvePartOne());
    }
}