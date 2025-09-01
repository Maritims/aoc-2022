package io.github.maritims.advent_of_code.year_one;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

class Day5Test {

    public static Stream<Arguments> isNiceInPartOne() {
        return Stream.of(
            arguments("ugknbfddgicrmopn", true),
            arguments("aaa", true),
            arguments("jchzalrnumimnmhp", false),
            arguments("haegwjzuvuyypxyu", false),
            arguments("dvszwmarrgswjxmb", false)
        );
    }

    public static Stream<Arguments> isNiceInPartTwo() {
        return Stream.of(
            arguments("qjhvhtzxzqqjkmpb", true),
            arguments("xxyxx", true),
            arguments("uurcxstgmygtbstg", false),
            arguments("ieodomkazucvgmuy", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void isNiceInPartOne(String line, boolean expectedResult) {
        assertEquals(expectedResult, new Day5().isNiceInPartOne(line));
    }

    @Test
    void solvePartOne() throws IOException {
        assertEquals(258, new Day5().solvePartOne("5.txt"));
    }

    @ParameterizedTest
    @MethodSource
    void isNiceInPartTwo(String line, boolean expectedResult) {
        assertEquals(expectedResult, new Day5().isNiceInPartTwo(line));
    }

    @Test
    void solvePartTwo() throws IOException {
        assertEquals(53, new Day5().solvePartTwo("5.txt"));
    }
}