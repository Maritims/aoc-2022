package io.github.maritims.advent_of_code.common.geometry.visualization;

import io.github.maritims.advent_of_code.common.geometry.Point2D;
import io.github.maritims.advent_of_code.common.geometry.Polygon;
import io.github.maritims.advent_of_code.common.geometry.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PolygonPackerTest {

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
}