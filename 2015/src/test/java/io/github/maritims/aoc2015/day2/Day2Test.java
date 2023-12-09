package io.github.maritims.aoc2015.day2;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @Test
    void solvePartOne() throws IOException {
        assertEquals(1588178, new Day2().solvePartOne("day2.txt"));
    }

    @Test
    void solvePartTwo() throws IOException {
        assertEquals(3783758, new Day2().solvePartTwo("day2.txt"));;
    }
}