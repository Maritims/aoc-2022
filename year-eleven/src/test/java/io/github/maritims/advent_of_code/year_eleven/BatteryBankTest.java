package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BatteryBankTest {
    public static Stream<Arguments> maximumJoltage() {
        return Stream.of(
                Arguments.of("987654321111111", 98)
        );
    }

    @ParameterizedTest
    @MethodSource
    void maximumJoltage(String joltageRatings, int expectedResult) {
        var sut = BatteryBank.ofJoltageRatings(joltageRatings);
        assertEquals(expectedResult, sut.maximumJoltage());
    }
}