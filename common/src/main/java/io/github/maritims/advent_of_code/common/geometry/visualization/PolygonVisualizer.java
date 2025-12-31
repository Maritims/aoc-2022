package io.github.maritims.advent_of_code.common.geometry.visualization;

import io.github.maritims.advent_of_code.common.geometry.Point2D;
import io.github.maritims.advent_of_code.common.geometry.Polygon;
import io.github.maritims.advent_of_code.common.geometry.Rectangle;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PolygonVisualizer {
    public static String drawBitmap(Collection<Polygon> polygons, Rectangle grid, char fullPixel, char emptyPixel) {
        if (polygons == null || polygons.isEmpty()) {
            throw new IllegalArgumentException("polygon cannot be null or empty");
        }

        var occupiedCells = polygons.stream()
                .flatMap(point -> point.getVertices().stream())
                .collect(Collectors.toSet());
        var minCol        = grid.getTopLeft().col();
        var maxCol        = grid.getTopRight().col();
        var minRow        = grid.getTopLeft().row();
        var maxRow        = grid.getBottomRight().row();

        var sb = new StringBuilder();
        for (var row = minRow; row <= maxRow; row++) {
            for (var col = minCol; col <= maxCol; col++) {
                sb.append(occupiedCells.contains(new Point2D(col, row)) ? fullPixel : emptyPixel);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static String drawBitmap(Collection<Polygon> polygons, Rectangle grid) {
        return drawBitmap(polygons, grid, '#', '.');
    }

    public static String drawBitmap(Polygon polygon, Rectangle grid, char fullPixel, char emptyPixel) {
        return drawBitmap(List.of(polygon), grid, fullPixel, emptyPixel);
    }

    public static String drawBitmap(Polygon polygon, Rectangle grid) {
        return drawBitmap(List.of(polygon), grid);
    }

    public static String drawBitmap(Polygon polygon, char fullPixel, char emptyPixel) {
        var minCol        = Double.MAX_VALUE;
        var maxCol        = Double.MIN_VALUE;
        var minRow        = Double.MAX_VALUE;
        var maxRow        = Double.MIN_VALUE;

        for (var point : polygon.getVertices()) {
            minCol = Math.min(minCol, point.col());
            maxCol = Math.max(maxCol, point.col());
            minRow = Math.min(minRow, point.row());
            maxRow = Math.max(maxRow, point.row());
        }

        var grid = new Rectangle(new Point2D(minCol, minRow), new Point2D(maxCol, maxRow));
        return drawBitmap(List.of(polygon), grid, fullPixel, emptyPixel);
    }

    public static String drawBitmap(Polygon polygon) {
        return drawBitmap(polygon, '#', '.');
    }
}
