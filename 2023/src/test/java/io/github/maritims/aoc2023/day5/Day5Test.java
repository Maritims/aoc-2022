package io.github.maritims.aoc2023.day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day5Test {

    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
            arguments("day5_sample.txt", 35L),
            arguments("day5_actual.txt", 621354867L)
        );
    }

    public static Stream<Arguments> solvePartTwo() {
        return Stream.of(
            arguments("day5_sample.txt", 46),
            arguments("day5_actual.txt", 15880236)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solvePartOne(String fileName, long expectedResult) throws IOException {
        assertEquals(expectedResult, new Day5().solvePartOne(fileName));
    }

    @ParameterizedTest
    @MethodSource
    void solvePartTwo(String fileName, long expectedResult) throws IOException {
        assertEquals(expectedResult, new Day5().solvePartTwo(fileName));
    }
}