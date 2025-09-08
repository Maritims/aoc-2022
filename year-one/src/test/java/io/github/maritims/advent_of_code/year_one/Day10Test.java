package io.github.maritims.advent_of_code.year_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {
    @Test
    void solveFirstPart() {
        var result = new Day10().solveFirstPart();
        assertEquals(360154, result);
    }

    @Test
    void solveSecondPart() {
        assertEquals(5103798, new Day10().solveSecondPart());
    }
}