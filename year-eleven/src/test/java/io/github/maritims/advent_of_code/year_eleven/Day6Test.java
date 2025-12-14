package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day6Test {
    Day6 sut;

    @BeforeEach
    void setUp() {
        sut = spy(new Day6());
    }

    @Test
    void solveFirstPart_withSampleInput() {
        doReturn(Arrays.stream("""
                123 328  51 64\s
                 45 64  387 23\s
                  6 98  215 314
                *   +   *   +
                """.split("\n")).toList()).when(sut).loadInput();
        assertEquals(4277556, sut.solveFirstPart());
    }

    @Test
    void solveFirstPart() {
        assertEquals(5335495999141L, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart_withSampleInput() {
        doReturn(Arrays.stream("""
                123 328  51 64\s
                 45 64  387 23\s
                  6 98  215 314
                *   +   *   +
                """.split("\n")).toList()).when(sut).loadInput();
        assertEquals(0, sut.solveSecondPart());
    }
}