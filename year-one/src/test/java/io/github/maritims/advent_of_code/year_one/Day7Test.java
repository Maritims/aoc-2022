package io.github.maritims.advent_of_code.year_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {
    @Test
    void solveFirstPart() {
        assertEquals(16076, new Day7().solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(2797, new Day7().solveSecondPart());
    }
}