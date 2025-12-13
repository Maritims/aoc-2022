package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DialTest {

    public static Stream<Arguments> countZeroHits() {
        return Stream.of(
                Arguments.of(50, 68, 'L', 1),   // newPosition: 82
                Arguments.of(82, 30, 'L', 0),   // newPosition: 52
                Arguments.of(52, 48, 'R', 1),   // newPosition: 0
                Arguments.of(0, 5, 'L', 0),     // newPosition: 95
                Arguments.of(95, 60, 'R', 1),   // newPosition: 55
                Arguments.of(55, 55, 'L', 1),   // newPosition: 0
                Arguments.of(0, 99, 'L', 0),    // newPosition: 99
                Arguments.of(99, 99, 'L', 1),   // newPosition: 99
                Arguments.of(0, 14, 'R', 0),    // newPosition: 14
                Arguments.of(14, 82, 'L', 1)    // newPosition: 32
        );
    }

    @Test
    void turnClockwiseOnce_shouldIncrementPositionByOne() {
        assertEquals(51, new Dial(50).turnClockwiseOnce().position());
    }

    @Test
    void turnClockwiseOnce_shouldWrapAround() {
        assertEquals(0, new Dial(99).turnClockwiseOnce().position());
    }

    @Test
    void turnCounterClockwiseOnce_shouldWrapAround() {
        assertEquals(99, new Dial(0).turnCounterClockwiseOnce().position());
    }

    @Test
    void turnClockwise_shouldIncrementPositionBySteps() {
        assertEquals(52, new Dial(50).turnClockwise(2).position());
    }

    @Test
    void turnCounterClockwise_shouldDecrementPositionBySteps() {
        assertEquals(50, new Dial(52).turnCounterClockwise(2).position());
    }

    @ParameterizedTest
    @MethodSource
    void countZeroHits(int position, int steps, char direction, int expectedCount) {
        assertEquals(expectedCount, Dial.countZeroHits(position, steps, direction));
    }
}