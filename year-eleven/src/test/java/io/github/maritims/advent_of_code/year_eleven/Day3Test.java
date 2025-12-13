package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

class Day3Test {
    Day3 sut;

    public static Stream<Arguments> findMaximumJoltage() {
        return Stream.of(
                Arguments.of("987654321111111", 2, 98),
                Arguments.of("987654321111111", 12, 987654321111L),
                Arguments.of("811111111111119", 2, 89),
                Arguments.of("811111111111119", 12, 811111111119L),
                Arguments.of("234234234234278", 2, 78),
                Arguments.of("234234234234278", 12, 434234234278L),
                Arguments.of("818181911112111", 2, 92),
                Arguments.of("818181911112111", 12, 888911112111L)
        );
    }

    @BeforeEach
    void setUp() {
        sut = spy(new Day3());
    }

    @ParameterizedTest
    @MethodSource
    void findMaximumJoltage(String str, int simultaneousBatteries, long expectedResult) {
        assertEquals(expectedResult, sut.findMaximumJoltage(str, simultaneousBatteries));
    }

    @Test
    void solveFirstPart() {
        assertEquals(17095, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(168794698570517L, sut.solveSecondPart());
    }
}