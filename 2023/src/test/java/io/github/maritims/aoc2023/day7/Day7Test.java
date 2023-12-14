package io.github.maritims.aoc2023.day7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day7Test {

    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
            arguments("day7_sample.txt", 6440),
            arguments("day7_actual.txt", 250370104)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solvePartOne(String fileName, int expectedResult) throws IOException {
        assertEquals(expectedResult, new Day7().solvePartOne(fileName));
    }
}