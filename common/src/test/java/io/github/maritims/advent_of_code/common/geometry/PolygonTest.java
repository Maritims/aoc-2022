package io.github.maritims.advent_of_code.common.geometry;

import io.github.maritims.advent_of_code.common.geometry.visualization.PolygonVisualizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.w3c.dom.css.Rect;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PolygonTest {

    Polygon sut;

    public static Stream<Arguments> isInBoundingBox() {
        return Stream.of(
                Arguments.of(new Point2D(7, 3), new Point2D(11, 1), true),
                Arguments.of(new Point2D(9, 5), new Point2D(2, 3), true)
        );
    }

    public static Stream<Arguments> isInPolygon() {
        return Stream.concat(
                        new Rectangle(new Point2D(7, 3), new Point2D(11, 1)).getVertices().stream(),
                        new Rectangle(new Point2D(9, 5), new Point2D(2, 3)).getVertices().stream()
                )
                .map(p -> Arguments.of(p, true));
    }

    public static Stream<Arguments> isConvex() {
        return Stream.of(
                Arguments.of(new Polygon(List.of(
                        new Point2D(7, 1),
                        new Point2D(11, 1),
                        new Point2D(11, 7),
                        new Point2D(9, 7),
                        new Point2D(9, 5),
                        new Point2D(2, 5),
                        new Point2D(2, 3),
                        new Point2D(7, 3)
                )), false),
                Arguments.of(
                        new Polygon(List.of(
                                new Point2D(7, 1),
                                new Point2D(11, 1),
                                new Point2D(11, 7),
                                new Point2D(7, 7),
                                new Point2D(7, 5),
                                new Point2D(2, 5),
                                new Point2D(2, 3),
                                new Point2D(7, 3)
                        )), false),
                Arguments.of(new Polygon(List.of(
                        new Point2D(0, 0),
                        new Point2D(15, 0),
                        new Point2D(15, 10),
                        new Point2D(0, 10)
                )), true)
        );
    }

    @BeforeEach
    void setUp() {
        sut = new Polygon(List.of(
                new Point2D(7, 1),
                new Point2D(11, 1),
                new Point2D(11, 7),
                new Point2D(9, 7),
                new Point2D(9, 5),
                new Point2D(2, 5),
                new Point2D(2, 3),
                new Point2D(7, 3)
        ));
    }

    @ParameterizedTest
    @MethodSource
    void isInBoundingBox(Point2D p1, Point2D p2, boolean expectedResult) {
        var rectangle = new Rectangle(p1, p2);
        assertEquals(expectedResult, sut.isInBoundingBox(rectangle));
    }

    @ParameterizedTest
    @MethodSource
    void isInPolygon(Point2D p, boolean expectedResult) {
        assertEquals(
                expectedResult,
                sut.isInPolygon(p),
                () -> String.format(
                        "(%.0f,%.0f) is not contained in the polygon {%s}",
                        p.col(),
                        p.row(),
                        sut.getVertices()
                                .stream()
                                .map(v -> String.format("(%.0f,%.0f)", v.col(), v.row()))
                                .collect(Collectors.joining(", "))
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void isConvex(Polygon sut, boolean expectedResult) {
        assertEquals(expectedResult, sut.isConvex());
    }

    @Test
    void translate() {
        var polygon = new Polygon(
                new Point2D(0, 0),
                new Point2D(1, 0),
                new Point2D(2, 0),
                new Point2D(0, 1),
                new Point2D(0, 2),
                new Point2D(1, 2),
                new Point2D(2, 2)
        );
        var grid = new Rectangle(new Point2D(0, 0), new Point2D(6, 6));
        var dx = 2;
        var dy = 2;

        var result = polygon.translate(dx, dy);
        var bitmap = PolygonVisualizer.drawBitmap(result, grid);

        assertEquals(bitmap, """
                .......
                .......
                ..###..
                ..#....
                ..###..
                .......
                .......
                """);
    }
}