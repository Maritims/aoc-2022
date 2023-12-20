package io.github.maritims.aoc2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.*;

class Day10Test {

    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
            arguments(true, 4),
            arguments(false, 6831)
        );
    }

    public static Stream<Arguments> solvePartTwo() {
        return Stream.of(
            arguments(true, 10),
            arguments(false, 305)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solvePartOne(boolean useSampleData, int expectedResult) {
        assertEquals(expectedResult, new Day10(useSampleData).solvePartOne());
    }

    @ParameterizedTest
    @MethodSource
    void solvePartTwo(boolean useSampleData, int expectedResult) {
        assertEquals(expectedResult, new Day10(useSampleData).solvePartTwo());
    }
}