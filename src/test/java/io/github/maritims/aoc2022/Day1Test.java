package io.github.maritims.aoc2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day1Test extends PuzzleTest<Integer, Integer, Day1> {
    public Day1Test() {
        super(Day1.class);
    }

    public static Stream<Arguments> getCalories() {
        return of(arguments("first/example.txt", 5));
    }

    @ParameterizedTest
    @MethodSource
    public void getCalories(String filePath, int expectedResult) {
        assertEquals(expectedResult, new Day1().getCalories(filePath).size());
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return of(arguments("first/example.txt", 24000), arguments("first/input.txt", 67016));
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return of(arguments("first/example.txt", 45000), arguments("first/input.txt", 200116));
    }
}