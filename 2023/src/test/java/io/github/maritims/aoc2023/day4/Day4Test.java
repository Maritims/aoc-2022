package io.github.maritims.aoc2023.day4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day4Test {
    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
            arguments("day4_sample.txt", 13),
            arguments("day4_actual.txt", 21138)
        );
    }

    public static Stream<Arguments> solvePartTwo() {
        return Stream.of(
            arguments("day4_sample.txt", 30),
            arguments("day4_actual.txt", 7185540)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solvePartOne(String fileName, int expectedResult) throws IOException {
        assertEquals(expectedResult, new Day4(fileName).solvePartOne());
    }

    @ParameterizedTest
    @MethodSource
    void solvePartTwo(String fileName, int expectedResult) throws IOException {
        assertEquals(expectedResult, new Day4(fileName).solvePartTwo());
    }
}