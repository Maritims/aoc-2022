package io.github.maritims.aoc2023.day12;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

class Day12Test {
    public static Stream<Arguments> get() {
        return Stream.of(
            arguments("???.###", new ArrayList<>(List.of(1, 1, 3)), "#.#.###")
        );
    }

    @ParameterizedTest
    @MethodSource
    void get(String line, List<Integer> groups, String expectedResult) {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> assertEquals(expectedResult, new Day12(true).get(line, groups)));
    }
}