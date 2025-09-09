package io.github.maritims.advent_of_code.year_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {
    @Test
    void solveFirstPart() {
        assertEquals(191164, new Day12().solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(87842, new Day12().solveSecondPart());
    }
}