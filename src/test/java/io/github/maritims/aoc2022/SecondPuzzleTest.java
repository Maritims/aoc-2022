package io.github.maritims.aoc2022;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SecondPuzzleTest {
    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("second/example.txt", 15),
                Arguments.arguments("second/input.txt", 0)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solvePartOne(String filePath, int expectedResult) {
        assertEquals(expectedResult, new SecondPuzzle(filePath).solvePartOne());
    }
}