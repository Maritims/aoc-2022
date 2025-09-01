package io.github.maritims.advent_of_code.common.geometry;

import io.github.maritims.advent_of_code.common.tuples.Tuple2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ShoelaceFormulaTest {

    public static Stream<Arguments> getLeftAndRightProducts() {
        return Stream.of(
            arguments(
                List.of(
                    Point.of(4, 10),
                    Point.of(9, 7),
                    Point.of(11, 2),
                    Point.of(2, 2),
                    Point.of(4, 10)
                ),
                new Tuple2<>(
                    List.of(28, 18, 22, 20),
                    List.of(90, 77, 4, 8)
                )
            )
        );
    }

    public static Stream<Arguments> getSum() {
        return Stream.of(
            arguments(List.of(28, 18, 22, 20), 88),
            arguments(List.of(90, 77, 4, 8), 179)
        );
    }

    public static Stream<Arguments> calculate() {
        return Stream.of(
            arguments(
                List.of(
                    Point.of(4, 10),
                    Point.of(9, 7),
                    Point.of(11, 2),
                    Point.of(2, 2),
                    Point.of(4, 10)
                ),
                45.5
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getLeftAndRightProducts(List<Point> points, Tuple2<List<Integer>, List<Integer>> expectedResult) {
        assertEquals(expectedResult, new ShoelaceFormula().getLeftAndRightProducts(points));
    }

    @ParameterizedTest
    @MethodSource
    void getSum(List<Integer> products, int expectedResult) {
        assertEquals(expectedResult, new ShoelaceFormula().getSum(products));
    }

    @ParameterizedTest
    @MethodSource
    void calculate(List<Point> points, double expectedResult) {
        assertEquals(expectedResult, new ShoelaceFormula().calculate(points));
    }
}