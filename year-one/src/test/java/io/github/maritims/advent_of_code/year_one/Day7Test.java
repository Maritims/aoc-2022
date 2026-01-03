package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.testing.DayTest;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test extends DayTest<Day7> {
    @Spy
    Day7 sut;

    @Test
    void solveFirstPart() {
        assertEquals(16076, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(2797, sut.solveSecondPart());
    }
}