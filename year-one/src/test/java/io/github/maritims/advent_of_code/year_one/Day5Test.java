package io.github.maritims.advent_of_code.year_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {
    @Test
    void solveFirstPart() {
        assertEquals(258, new Day5().solveFirstPart());
    }

    @Test
    void solvePartTwo() {
        assertEquals(53, new Day5().solveSecondPart());
    }
}