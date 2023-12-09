package io.github.maritims.aoc2015.day3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day3Test {

    public static Stream<Arguments> deliverPresents() {
        return Stream.of(
            arguments(">", 1, 2),
            arguments("^>v<", 1, 4),
            arguments("^v^v^v^v^v", 1, 2),
            arguments("^v", 2, 3),
            arguments("^>v<", 2, 3),
            arguments("^v^v^v^v^v", 2, 11)
        );
    }

    public static Stream<Arguments> solve() {
        return Stream.of(
            arguments("day3.txt", 1, 2592),
            arguments("day3.txt", 2, 2360)
        );
    }

    @ParameterizedTest
    @MethodSource
    void deliverPresents(String instructions, int santas, int expectedResult) {
        assertEquals(expectedResult, new Day3(santas).deliverPresents(instructions).size());
    }

    @ParameterizedTest
    @MethodSource
    void solve(String fileName, int santas, int expectedResult) throws IOException {
        assertEquals(expectedResult, new Day3(santas).solve(fileName));
    }
}