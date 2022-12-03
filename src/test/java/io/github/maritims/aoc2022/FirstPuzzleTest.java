package io.github.maritims.aoc2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FirstPuzzleTest {
    public static Stream<Arguments> getCalories() {
        return of(arguments("first/example.txt", 5));
    }

    @ParameterizedTest
    @MethodSource
    public void getCalories(String filePath, int expectedResult) {
        assertEquals(expectedResult, new FirstPuzzle(filePath).getCalories().size());
    }
}