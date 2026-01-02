package io.github.maritims.advent_of_code.common.geometry.visualization;

import io.github.maritims.advent_of_code.common.geometry.Point2D;
import io.github.maritims.advent_of_code.common.geometry.Polygon;
import io.github.maritims.advent_of_code.common.geometry.Rectangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PolygonPackerTest {
    static final Polygon       polygon0 = new Polygon(
            new Point2D(0, 0),
            new Point2D(1, 0),
            new Point2D(2, 0),
            new Point2D(0, 1),
            new Point2D(1, 1),
            new Point2D(0, 2),
            new Point2D(1, 2)
    );
    static final Polygon       polygon1 = new Polygon(
            new Point2D(0, 0),
            new Point2D(1, 0),
            new Point2D(2, 0),
            new Point2D(0, 1),
            new Point2D(1, 1),
            new Point2D(1, 2),
            new Point2D(2, 2)
    );
    static final Polygon       polygon2 = new Polygon(
            new Point2D(1, 0),
            new Point2D(2, 0),
            new Point2D(0, 1),
            new Point2D(1, 1),
            new Point2D(2, 1),
            new Point2D(0, 2),
            new Point2D(1, 2)
    );
    static final Polygon       polygon3 = new Polygon(
            new Point2D(0, 0),
            new Point2D(1, 0),
            new Point2D(0, 1),
            new Point2D(1, 1),
            new Point2D(2, 1),
            new Point2D(0, 2),
            new Point2D(1, 2)
    );
    static final Polygon       polygon4 = new Polygon(
            new Point2D(0, 0),
            new Point2D(1, 0),
            new Point2D(2, 0),
            new Point2D(0, 1),
            new Point2D(0, 2),
            new Point2D(1, 2),
            new Point2D(2, 2)
    );
    static final Polygon       polygon5 = new Polygon(
            new Point2D(0, 0),
            new Point2D(1, 0),
            new Point2D(2, 0),
            new Point2D(1, 1),
            new Point2D(0, 2),
            new Point2D(1, 2),
            new Point2D(2, 2)
    );
    static final List<Polygon> polygons = List.of(polygon0, polygon1, polygon2, polygon3, polygon4, polygon5);

    public static Stream<Arguments> pack() {

        return Stream.of(
                Arguments.of(polygons, List.of(0, 0, 0, 0, 2, 0), PolygonVisualizer.createGrid(4, 4, '.'), """
                        AAA.
                        ABAB
                        ABAB
                        .BBB
                        """),
                Arguments.of(polygons, List.of(1, 0, 1, 0, 2, 2), PolygonVisualizer.createGrid(5, 12, '.'), """
                        ....AAAFFE.E
                        .BBBAAFFFEEE
                        DDDBAAFFCECE
                        DBBB....CCC.
                        DDD.....C.C.
                        """),
                Arguments.of(polygons, List.of(1, 0, 1, 0, 3, 2), PolygonVisualizer.createGrid(5, 12, '.'), null)
        );
    }

    static Map<Integer, List<Polygon>> setUpPieces(List<Integer> quantities) {
        var pieces = new HashMap<Integer, List<Polygon>>();

        for (var id = 0; id < quantities.size(); id++) {
            var quantity = quantities.get(id);
            if (quantity > 0) {
                var piece = polygons.get(id);
                for (var j = 0; j < quantity; j++) {
                    pieces.computeIfAbsent(id, k -> new ArrayList<>()).add(piece);
                }
            }
        }

        return pieces;
    }

    @Test
    void generateUniqueOrientations() {
        var polygon = new Polygon(List.of(
                new Point2D(0, 0),
                new Point2D(1, 0),
                new Point2D(2, 0),
                new Point2D(3, 0),
                new Point2D(0, 1),
                new Point2D(0, 2)
        ));
        var sut                = new PolygonPacker(new Rectangle(new Point2D(0, 0), new Point2D(10, 10)), List.of(polygon));
        var uniqueOrientations = sut.generateUniqueOrientations(polygon);
        var grid               = PolygonVisualizer.createGrid(4, 4, '.');

        assertNotNull(uniqueOrientations);
        assertFalse(uniqueOrientations.isEmpty());

        for (var uniqueOrientation : uniqueOrientations) {
            PolygonVisualizer.drawOnGrid(uniqueOrientation, grid);
        }

        var bitmap = PolygonVisualizer.drawGrid(grid);
        System.out.println(bitmap);
    }

/*    @ParameterizedTest
    @MethodSource
    void pack(List<Polygon> polygons, List<Integer> quantities, char[][] bitmapGrid, String expectedResult) {
        var grid   = new Rectangle(new Point2D(0, 0), new Point2D(bitmapGrid[0].length, bitmapGrid.length));
        var pieces = new HashMap<Integer, List<Polygon>>();

        for (var id = 0; id < quantities.size(); id++) {
            var quantity = quantities.get(id);
            if (quantity > 0) {
                var piece = polygons.get(id);
                for (var j = 0; j < quantity; j++) {
                    pieces.computeIfAbsent(id, k -> new ArrayList<>()).add(piece);
                }
            }
        }

        var sut    = new PolygonPacker(grid, pieces.values().stream().flatMap(Collection::stream).toList());
        var result = sut.pack();
        assertNotNull(result, "The pieces can't be packed within the given grid");

        for (var i = 0; i < result.size(); i++) {
            var placed = result.get(i);
            PolygonVisualizer.drawOnGrid(List.of(placed), bitmapGrid, (char) (i + '0'));
        }

        var bitmap = PolygonVisualizer.drawGrid(bitmapGrid);
        System.out.println(bitmap);
    }*/

    @Test
    void couldFit() {
        var pieces = setUpPieces(List.of(1, 0, 1, 0, 3, 2)).entrySet().stream().flatMap(entry -> entry.getValue().stream()).toList();
        var grid   = new Rectangle(new Point2D(0, 0), new Point2D(12, 5));
        var sut    = new PolygonPacker(grid, pieces);
        var result = sut.couldFit();
        assertFalse(result);
    }
}