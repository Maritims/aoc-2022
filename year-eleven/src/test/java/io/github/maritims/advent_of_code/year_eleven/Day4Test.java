package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day4Test {
    Day4 sut;

    @BeforeEach
    void setUp() {
        sut = spy(new Day4());
    }

    @Test
    void solveFirstPart_withSampleInput() {
        doReturn(Arrays.stream("""
                                       ..@@.@@@@.
                                       @@@.@.@.@@
                                       @@@@@.@.@@
                                       @.@@@@..@.
                                       @@.@@@@.@@
                                       .@@@@@@@.@
                                       .@.@.@.@@@
                                       @.@@@.@@@@
                                       .@@@@@@@@.
                                       @.@.@@@.@.
                                       
                                       """.split("\n")).toList()
        ).when(sut).loadInput();
        assertEquals(13, sut.solveFirstPart());
    }

    @Test
    void solveFirstPart() {
        assertEquals(100, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart() {
    }
}