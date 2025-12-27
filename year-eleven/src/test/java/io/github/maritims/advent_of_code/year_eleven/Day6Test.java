package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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
                """.split("\n")).collect(Collectors.toCollection(ArrayList::new))).when(sut).loadInput();
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
                """.split("\n")).collect(Collectors.toCollection(ArrayList::new))).when(sut).loadInput();
        assertEquals(3263827, sut.solveSecondPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(10142723156431L, sut.solveSecondPart());
    }
}