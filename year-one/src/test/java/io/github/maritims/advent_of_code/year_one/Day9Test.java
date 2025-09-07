package io.github.maritims.advent_of_code.year_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {
    @Test
    void solveFirstPart() {
        assertEquals(251, new Day9().solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(898, new Day9().solveSecondPart());
    }
}