package io.github.maritims.advent_of_code.common.geometry.visualization;

import io.github.maritims.advent_of_code.common.geometry.Point2D;
import io.github.maritims.advent_of_code.common.geometry.Polygon;

import java.util.HashSet;

public class PolygonVisualizer {
    public static String drawBitmap(Polygon polygon, char fullPixel, char emptyPixel) {
        if (polygon == null) {
            throw new IllegalArgumentException("polygon cannot be null");
        }
        if (polygon.getVertices() == null || polygon.getVertices().isEmpty()) {
            throw new IllegalArgumentException("polygon.vertices cannot be null or empty");
        }

        var occupiedCells = new HashSet<Point2D>();
        var minCol        = Double.MAX_VALUE;
        var maxCol        = Double.MIN_VALUE;
        var minRow        = Double.MAX_VALUE;
        var maxRow        = Double.MIN_VALUE;

        for (var point : polygon.getVertices()) {
            occupiedCells.add(point);
            minCol = Math.min(minCol, point.col());
            maxCol = Math.max(maxCol, point.col());
            minRow = Math.min(minRow, point.row());
            maxRow = Math.max(maxRow, point.row());
        }

        var sb = new StringBuilder();
        for (var row = minRow; row <= maxRow; row++) {
            for (var col = minCol; col <= maxCol; col++) {
                sb.append(occupiedCells.contains(new Point2D(col, row)) ? fullPixel : emptyPixel);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static String drawBitmap(Polygon polygon) {
        return drawBitmap(polygon, '#', '.');
    }
}
