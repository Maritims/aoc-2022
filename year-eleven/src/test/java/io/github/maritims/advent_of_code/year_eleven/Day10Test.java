package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.testing.DayTest;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test extends DayTest<Day10> {
    @Spy
    protected Day10 sut;

    @Test
    void solveFirstPart_withSampleInput() {
        mockLoadInput(sut, """
                [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
                [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
                [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
                """);
        assertEquals(7, sut.solveFirstPart());
    }

    @Test
    void solveFirstPart() {
        assertEquals(507, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart_withSampleInput() {
        mockLoadInput(sut, """
                [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
                [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
                [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
                """);
        assertEquals(33, sut.solveSecondPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(0, sut.solveSecondPart());
    }
}
