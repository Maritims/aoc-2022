package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.testing.DayTest;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test extends DayTest<Day9> {

    @Spy
    protected Day9 sut;

    @Test
    void solveFirstPart_withSampleInput() {
        mockLoadInput(sut, """
                7,1
                11,1
                11,7
                9,7
                9,5
                2,5
                2,3
                7,3
                """);
        var result = sut.solveFirstPart();
        assertEquals(50, result);
    }

    @Test
    void solveFirstPart() {
        var result = sut.solveFirstPart();
        assertEquals(4755429952L, result);
    }

    @Test
    void solveSecondPart_withSampleInput() {
        mockLoadInput(sut, """
                7,1
                11,1
                11,7
                9,7
                9,5
                2,5
                2,3
                7,3
                """);
        var result = sut.solveSecondPart();
        assertEquals(24, result);
    }

    @Test
    void solveSecondPart() {
        var result = sut.solveSecondPart();
        assertEquals(1429596008L, result);
    }
}