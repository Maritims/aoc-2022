package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.testing.DayTest;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test extends DayTest<Day13> {
    @Spy
    Day13 sut;

    @Test
    void solveFirstPart_withSampleInput() {
        mockLoadInput(sut, """
                Alice would gain 54 happiness units by sitting next to Bob.
                Alice would lose 79 happiness units by sitting next to Carol.
                Alice would lose 2 happiness units by sitting next to David.
                Bob would gain 83 happiness units by sitting next to Alice.
                Bob would lose 7 happiness units by sitting next to Carol.
                Bob would lose 63 happiness units by sitting next to David.
                Carol would lose 62 happiness units by sitting next to Alice.
                Carol would gain 60 happiness units by sitting next to Bob.
                Carol would gain 55 happiness units by sitting next to David.
                David would gain 46 happiness units by sitting next to Alice.
                David would lose 7 happiness units by sitting next to Bob.
                David would gain 41 happiness units by sitting next to Carol.
                """);
        assertEquals(330, sut.solveFirstPart());
    }

    @Test
    void solveFirstPart() {
        assertEquals(664, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(640, sut.solveSecondPart());
    }
}