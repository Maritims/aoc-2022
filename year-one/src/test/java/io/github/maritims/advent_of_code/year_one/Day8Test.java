package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.util.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {
    public static Stream<Arguments> getLength() {
        return Stream.of(
                Arguments.of("", new Day8.Lengths(0, 0)),
                Arguments.of("\"abc\"", new Day8.Lengths(5, 3)),
                Arguments.of("\"aaa\\\"aaa\"", new Day8.Lengths(10, 7)),
                Arguments.of("\"\\x27\"", new Day8.Lengths(6, 1)),
                Arguments.of("\"\\x9a\"", new Day8.Lengths(6, 1))
        );
    }

    @ParameterizedTest
    @MethodSource
    void getLength(String s, Day8.Lengths expectedResult) {
        var result = Day8.Lengths.of(s, StringUtils::unescapeString);
        assertEquals(expectedResult, result);
    }


    @Test
    void solveFirstPart() {
        assertEquals(1333, new Day8().solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(2046, new Day8().solveSecondPart());
    }
}