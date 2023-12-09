package io.github.maritims.aoc2023.day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day5Test {

    public static Stream<Arguments> solve() {
        return Stream.of(
            arguments("day5_sample.txt", 35L),
            arguments("day5_actual.txt", 621354867L)
            /*arguments("day5_sample.txt", true, BigInteger.valueOf(46)),
            arguments("day5_actual.txt", true, BigInteger.ZERO)*/
        );
    }

    @ParameterizedTest
    @MethodSource
    void solve(String fileName, long expectedResult) throws IOException {
        assertEquals(expectedResult, new Day5().solvePartOne(fileName));
    }
}