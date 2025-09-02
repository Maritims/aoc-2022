package io.github.maritims.advent_of_code.year_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {
    @Test
    void solveFirstPart() {
        assertEquals(2592, new Day3().solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(2360, new Day3().solveSecondPart());
    }
}