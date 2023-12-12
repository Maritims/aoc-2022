package io.github.maritims.aoc2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day6Test {

    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
            arguments("day6_sample.txt", 288),
            arguments("day6_actual.txt", 0)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solvePartOne(String fileName, int expectedResult) throws IOException {
        assertEquals(expectedResult, new Day6().solvePartOne(fileName));
    }
}