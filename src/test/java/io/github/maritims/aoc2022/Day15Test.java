package io.github.maritims.aoc2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test {
    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("15/example.txt", 10, 26),
                Arguments.arguments("15/input.txt", 2000000, 4961647)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void solvePartOne(String filePath, int lineToTest, int expectedResult) throws IOException {
        // arrange
        Day15 sut = new Day15(lineToTest);

        // act
        int result = sut.solvePartOne(filePath);

        // assert
        assertEquals(expectedResult, result);
    }
}
