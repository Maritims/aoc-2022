package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.testing.DayTest;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test extends DayTest<Day11> {
    @Spy
    Day11 sut;

    @Test
    public void solveFirstPart_withSampleInput() {
        mockLoadInput(sut, """
                aaa: you hhh
                you: bbb ccc
                bbb: ddd eee
                ccc: ddd eee fff
                ddd: ggg
                eee: out
                fff: out
                ggg: out
                hhh: ccc fff iii
                iii: out
                """);
        assertEquals(5, sut.solveFirstPart());
    }

    @Test
    public void solveFirstPart() {
        assertEquals(662, sut.solveFirstPart());
    }

    @Test
    public void solveSecondPart_withSampleInput() {
        mockLoadInput(sut, """
                svr: aaa bbb
                aaa: fft
                fft: ccc
                bbb: tty
                tty: ccc
                ccc: ddd eee
                ddd: hub
                hub: fff
                eee: dac
                dac: fff
                fff: ggg hhh
                ggg: out
                hhh: out
                """);
        assertEquals(2, sut.solveSecondPart());
    }

    @Test
    public void solveSecondPart() {
        assertEquals(429399933071120L, sut.solveSecondPart());
    }
}
