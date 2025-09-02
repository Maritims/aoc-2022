package io.github.maritims.advent_of_code.year_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {
    @Test
    void solveFirstPart() {
        assertEquals(1588178, new Day2().solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(3783758, new Day2().solveSecondPart());
    }
}