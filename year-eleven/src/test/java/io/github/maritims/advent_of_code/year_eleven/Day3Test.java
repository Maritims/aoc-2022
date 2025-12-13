package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class Day3Test {
    Day3 sut;

    public static Stream<Arguments> findMax() {
        return Stream.of(
                Arguments.of("987654321111111", 98),
                Arguments.of("811111111111119", 89)
        );
    }

    @BeforeEach
    void setUp() {
        sut = spy(new Day3());
    }

    @ParameterizedTest
    @MethodSource
    void findMax(String str, int expectedResult) {
        assertEquals(expectedResult, sut.findMax(str));
    }
}