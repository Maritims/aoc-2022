package io.github.maritims.advent_of_code.year_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {
    @Test
    void solveFirstPart() {
        assertEquals(280, new Day1().solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(1797, new Day1().solveSecondPart());
    }
}