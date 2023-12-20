package io.github.maritims.toolbox.geometry;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PicksTheoremTest {

    public static Stream<Arguments> getArea() {
        return Stream.of(
            arguments(7, 8, 10)
        );
    }

    public static Stream<Arguments> getInteriorPoints() {
        return Stream.of(
            arguments(10, 8, 7)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getArea(int i, int b, int expectedResult) {
        assertEquals(expectedResult, PicksTheorem.getArea(i, b));
    }

    @ParameterizedTest
    @MethodSource
    void getInteriorPoints(double A, int b, int expectedResult) {
        assertEquals(expectedResult, PicksTheorem.getInteriorPoints(A, b));
    }
}