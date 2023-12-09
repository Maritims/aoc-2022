package io.github.maritims.aoc2015.day1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day1Test {

    public static Stream<Arguments> convertInstructionsToFloor() {
        return Stream.of(
            arguments("(())", new Result(null, 0)),
            arguments("()()", new Result(null, 0)),
            arguments("(((", new Result(null, 3)),
            arguments("(()(()(", new Result(null, 3)),
            arguments("))(((((", new Result(1, 3)),
            arguments("())", new Result(3, -1)),
            arguments("))(", new Result(1, -1)),
            arguments(")))", new Result(1, -3)),
            arguments(")())())", new Result(1, -3)),
            arguments(")", new Result(1, -1)),
            arguments("()())", new Result(5, -1))
        );
    }

    @ParameterizedTest
    @MethodSource
    void convertInstructionsToFloor(String instructions, Result expectedResult) {
        assertEquals(expectedResult, new Day1().convertInstructionsToFloor(instructions));
    }

    @Test
    void solvePartOne() throws IOException {
        assertEquals(280, new Day1().solvePartOne("day1.txt"));
    }

    @Test
    void solvePartTwo() throws IOException {
        assertEquals(1797, new Day1().solvePartTwo("day1.txt"));
    }
}