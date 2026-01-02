package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.testing.DayTest;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test extends DayTest<Day12> {
    @Spy
    Day12 sut;

    @Test
    void solveFirstPart_withSampleInput() {
        mockLoadInput(sut, """
                0:
                ###
                ##.
                ##.
                
                1:
                ###
                ##.
                .##
                
                2:
                .##
                ###
                ##.
                
                3:
                ##.
                ###
                ##.
                
                4:
                ###
                #..
                ###
                
                5:
                ###
                .#.
                ###
                
                4x4: 0 0 0 0 2 0
                12x5: 1 0 1 0 2 2
                12x5: 1 0 1 0 3 2
                """);
        assertEquals(2, sut.solveFirstPart());
    }

    @Test
    void solveFirstPart() {
        assertEquals(0, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart() {
    }
}