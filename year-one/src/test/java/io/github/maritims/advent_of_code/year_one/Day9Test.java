package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.testing.DayTest;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test extends DayTest<Day9> {
    @Spy
    Day9 sut;

    @Test
    void solveFirstPart_withSampleInput() {
        mockLoadInput(sut, """
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
                """);
        assertEquals(605, sut.solveFirstPart());
    }

    @Test
    void solveFirstPart() {
        assertEquals(251, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(898, sut.solveSecondPart());
    }
}