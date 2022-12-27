package io.github.maritims.aoc2022;

import io.github.maritims.lib.Point;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.maritims.aoc2022.Day15.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test extends PuzzleTest<Integer, Integer, Day15> {
    public Day15Test() {
        super(Day15.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("15/example.txt", 26),
                Arguments.arguments("15/input.txt", 26)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return null;
    }

    public Stream<Arguments> getManhattanDistance() {
        return Stream.of(
                Arguments.arguments(8, 7, 2, 10, 9),
                Arguments.arguments(8, 7, 8, -2, 9),
                Arguments.arguments(8, 7, -1, 7, 9)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void getManhattanDistance(int x1, int y1, int x2, int y2, int expectedResult) {
        // arrange
        Sensor sensor = new Sensor(new Point(x1, y1), new Point(x2, y2));

        // act
        int result = sensor.getManhattanDistance();

        // assert
        assertEquals(expectedResult, result);
    }
}
