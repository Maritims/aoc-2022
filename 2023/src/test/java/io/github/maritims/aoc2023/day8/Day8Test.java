package io.github.maritims.aoc2023.day8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

class Day8Test {

    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
            arguments(true, 6),
            arguments(false, 19637)
        );
    };

    @ParameterizedTest
    @MethodSource
    void solvePartOne(boolean useSampleData, int expectedResult) {
        assertEquals(expectedResult, new Day8(useSampleData).solvePartOne());
    }

    @Test
    void solvePartTwo() {
    }
}