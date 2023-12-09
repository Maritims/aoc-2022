package io.github.maritims.aoc2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day1Test {
    public static Stream<Arguments> solve() {
        return Stream.of(
            arguments(false, "day1_part1_sample.txt", 142),
            arguments(false, "day1_actual.txt", 54708),
            arguments(true, "day1_part2_sample.txt", 281),
            arguments(true, "day1_actual.txt", 54087)
        );
    }

    public static Stream<Arguments> convertLineToNumber() {
        return Stream.of(
            arguments(true, "two1nine", 29),
            arguments(true, "eightwothree", 83),
            arguments(true, "abcone2threexyz", 13),
            arguments(true, "xtwone3four", 24),
            arguments(true, "4nineeightseven2", 42),
            arguments(true, "zoneight234", 14),
            arguments(true, "7pqrstsixteen", 76),
            arguments(true, "pttdtmhg4", 44),
            arguments(true, "three28jxdmlqfmc619eightwol", 32)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solve(boolean supportDigitWords, String fileName, int expectedResult) throws IOException {
        assertEquals(expectedResult, new Day1(supportDigitWords, false).solve(fileName));
    }

    @ParameterizedTest
    @MethodSource
    void convertLineToNumber(boolean supportDigitWords, String line, int expectedResult) {
        assertEquals(expectedResult, new Day1(supportDigitWords, false).convertLineToNumber(line));
    }
}