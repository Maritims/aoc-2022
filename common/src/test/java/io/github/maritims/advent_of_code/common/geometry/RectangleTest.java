package io.github.maritims.advent_of_code.common.geometry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    public static Stream<Arguments> getCenter() {
        return Stream.of(
                Arguments.of(new Rectangle(new Point2D(0, 0), new Point2D(10, 10)), new Point2D(5, 5)),
                Arguments.of(new Rectangle(new Point2D(0, 0), new Point2D(10, 5)), new Point2D(5, 2.5))
        );
    }

    @Test
    void shouldNormalizePoints() {
        // arrange
        var p1 = new Point2D(9, 5);
        var p2 = new Point2D(2, 3);

        // act
        var rectangle = new Rectangle(p1, p2);

        // assert
        assertEquals(p2, rectangle.getTopLeft());
        assertEquals(p1, rectangle.getBottomRight());
    }

    @ParameterizedTest
    @MethodSource
    void getCenter(Rectangle rectangle, Point2D expectedResult) {
        assertEquals(rectangle.getCenter(), expectedResult);
    }
}