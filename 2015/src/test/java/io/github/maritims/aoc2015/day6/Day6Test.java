package io.github.maritims.aoc2015.day6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.*;
import static org.mockito.Mockito.mock;

class Day6Test {
    public static Stream<Arguments> solve() {
        return Stream.of(
            arguments(false, 377891),
            arguments(true, 14110788)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solve(boolean isDimmable, int expectedResult) throws IOException {
        assertEquals(expectedResult, new Day6(isDimmable).solve("day6.txt"));
    }
}