package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class Day1Test {
    Day1 sut;

    @BeforeEach
    void setUp() {
        sut = spy(new Day1());
        sut.loadInput();
    }

    @Test
    void solveFirstPart() {
        assertEquals(1089, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart_withSampleInput() {
        assertEquals(6530, sut.solveSecondPart());
    }
}