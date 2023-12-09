package io.github.maritims.aoc2023.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

class Day2Test {
    public static Stream<Arguments> solve() {
        return Stream.of(
            Arguments.arguments("day2_sample.txt", false, 8),
            Arguments.arguments("day2_actual.txt", false, 2348),
            Arguments.arguments("day2_sample.txt", true, 2286),
            Arguments.arguments("day2_actual.txt", true, 76008)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solve(String fileName, boolean solveForPowerOfMinimumCubesForPlayability, int expectedResult) throws IOException {
        Assertions.assertEquals(expectedResult, new Day2(Map.of(Color.Red, 12, Color.Green, 13, Color.Blue, 14), solveForPowerOfMinimumCubesForPlayability).solve(fileName));
    }
}