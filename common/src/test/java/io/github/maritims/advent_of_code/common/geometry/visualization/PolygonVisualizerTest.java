package io.github.maritims.advent_of_code.common.geometry.visualization;

import io.github.maritims.advent_of_code.common.geometry.Point2D;
import io.github.maritims.advent_of_code.common.geometry.Polygon;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PolygonVisualizerTest {

    public static Stream<Arguments> drawBitmap() {
        return Stream.of(
                Arguments.of(
                        new Polygon(
                                new Point2D(0, 0),
                                new Point2D(1, 0),
                                new Point2D(2, 0),
                                new Point2D(0, 1),
                                new Point2D(1, 1),
                                new Point2D(0, 2),
                                new Point2D(1, 2)
                        ),
                        """
                                ###
                                ##.
                                ##.
                                """
                ),
                Arguments.of(
                        new Polygon(
                                new Point2D(0, 0),
                                new Point2D(1, 0),
                                new Point2D(2, 0),
                                new Point2D(0, 1),
                                new Point2D(1, 1),
                                new Point2D(1, 2),
                                new Point2D(2, 2)
                        ),
                        """
                                ###
                                ##.
                                .##
                                """
                ),
                Arguments.of(
                        new Polygon(
                                new Point2D(1, 0),
                                new Point2D(2, 0),
                                new Point2D(0, 1),
                                new Point2D(1, 1),
                                new Point2D(2, 1),
                                new Point2D(0, 2),
                                new Point2D(1, 2)
                        ),
                        """
                                .##
                                ###
                                ##.
                                """
                ),
                Arguments.of(
                        new Polygon(
                                new Point2D(0, 0),
                                new Point2D(1, 0),
                                new Point2D(0, 1),
                                new Point2D(1, 1),
                                new Point2D(2, 1),
                                new Point2D(0, 2),
                                new Point2D(1, 2)
                        ),
                        """
                                ##.
                                ###
                                ##.
                                """
                ),
                Arguments.of(
                        new Polygon(
                                new Point2D(0, 0),
                                new Point2D(1, 0),
                                new Point2D(2, 0),
                                new Point2D(0, 1),
                                new Point2D(0, 2),
                                new Point2D(1, 2),
                                new Point2D(2, 2)
                        ),
                        """
                                ###
                                #..
                                ###
                                """
                ),
                Arguments.of(
                        new Polygon(
                                new Point2D(0, 0),
                                new Point2D(1, 0),
                                new Point2D(2, 0),
                                new Point2D(1, 1),
                                new Point2D(0, 2),
                                new Point2D(1, 2),
                                new Point2D(2, 2)
                        ),
                        """
                                ###
                                .#.
                                ###
                                """
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void drawBitmap(Polygon polygon, String expectedResult) {
        var result = PolygonVisualizer.drawBitmap(polygon);
        assertEquals(expectedResult, result);
        System.out.println(result);
    }
}