package io.github.maritims.aoc2023.day7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

class Day7Test {

    public static Stream<Arguments> solve() {
        return Stream.of(
            arguments("day7_sample.txt", 0)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solve(String fileName, int expectedResult) throws IOException {
        assertEquals(expectedResult, new Day7().solve(fileName));
    }
}