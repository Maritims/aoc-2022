package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.testing.DayTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

class Day14Test extends DayTest<Day14> {

    @Test
    void solveFirstPart_withSampleInput() {
        var sut = spy(new Day14(1000));
        mockLoadInput(sut, """
                Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
                Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
                """);
        assertEquals(1120, sut.solveFirstPart());
    }

    @Test
    void solveFirstPart() {
        var sut = new Day14(2503);
        assertEquals(2640, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart_withSampleInput() {
        var sut = spy(new Day14(1000));
        mockLoadInput(sut, """
                Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
                Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
                """);
        assertEquals(689, sut.solveSecondPart());
    }

    @Test
    void solveSecondPart() {
        var sut = new Day14(2503);
        assertEquals(1102, sut.solveSecondPart());
    }
}