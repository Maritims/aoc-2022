package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day2Test {
    Day2 sut;

    @BeforeEach
    void setUp() {
        sut = spy(new Day2());
    }

    @Test
    void solveFirstPart_withSampleInput() {
        var input = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                    "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                    "824824821-824824827,2121212118-2121212124";
        doReturn(List.of(input)).when(sut).loadInput();
        assertEquals(1227775554, sut.solveFirstPart());
    }

    @Test
    void solveFirstPart() {
        assertEquals(18595663903L, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart_withSampleInput() {
        var input = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                    "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                    "824824821-824824827,2121212118-2121212124";
        doReturn(List.of(input)).when(sut).loadInput();
        assertEquals(4174379265L, sut.solveSecondPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(19058204438L, sut.solveSecondPart());
    }
}