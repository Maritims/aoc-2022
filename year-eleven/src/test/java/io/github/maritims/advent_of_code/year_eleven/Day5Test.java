package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day5Test {
    Day5 sut;

    @BeforeEach
    void setUp() {
        sut = spy(new Day5());
    }

    @Test
    void solveFirstPart_withSampleInput() {
        doReturn(Arrays.stream("""
                3-5
                10-14
                16-20
                12-18
                
                1
                5
                8
                11
                17
                32
                """.split("\n")).toList()).when(sut).loadInput();
        assertEquals(3, sut.solveFirstPart());
    }

    @Test
    void solveFirstPart() {
        assertEquals(664, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart_withSampleInput() {
        doReturn(Arrays.stream("""
                3-5
                10-14
                16-20
                12-18""".split("\n")).toList()).when(sut).loadInput();
        assertEquals(14, sut.solveSecondPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(1968, sut.solveSecondPart());
    }
}