package io.github.maritims.aoc2023.day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PiecewiseFunctionTest {

    public static Stream<Arguments> evaluate() {
        return Stream.of(
            arguments(79.0, List.of(
                new Piece(Double.NEGATIVE_INFINITY, 49.0, 0.0),
                new Piece(50.0, 97.0, 2.0),
                new Piece(98.0, 99.0, -48.0),
                new Piece(100.0, Double.POSITIVE_INFINITY, 0)
            ), 81.0)
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluate(double x, List<Piece> pieces, double expectedResult) {
        assertEquals(expectedResult, new PiecewiseFunction(pieces).evaluate(x));
    }
}