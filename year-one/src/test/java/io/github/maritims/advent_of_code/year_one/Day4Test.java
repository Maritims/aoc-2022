package io.github.maritims.advent_of_code.year_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {
    @Test
    void solveFirstPart() {
        assertEquals(282749, new Day4().solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(9962624, new Day4().solveSecondPart());
    }
}