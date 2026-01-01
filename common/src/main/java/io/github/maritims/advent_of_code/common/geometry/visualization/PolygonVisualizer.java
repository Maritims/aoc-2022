package io.github.maritims.advent_of_code.common.geometry.visualization;

import io.github.maritims.advent_of_code.common.geometry.Point2D;
import io.github.maritims.advent_of_code.common.geometry.Polygon;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PolygonVisualizer {
    public static void drawOnGrid(Collection<Polygon> polygons, char[][] grid, char fullPixel) {
        if (polygons == null || polygons.isEmpty()) {
            throw new IllegalArgumentException("polygon cannot be null or empty");
        }

        var occupiedCells = polygons.stream()
                .flatMap(point -> point.getVertices().stream())
                .collect(Collectors.toSet());
        var minCol = 0;
        var maxCol = grid[0].length;
        var minRow = 0;
        var maxRow = grid.length;

        for (var row = minRow; row <= maxRow; row++) {
            for (var col = minCol; col <= maxCol; col++) {
                if (occupiedCells.contains(new Point2D(col, row))) {
                    grid[row][col] = fullPixel;
                }
            }
        }
    }

    public static void drawOnGrid(Polygon polygon, char[][] grid) {
        drawOnGrid(List.of(polygon), grid, '#');
    }

    public static void drawOnGrid(Polygon polygon, char fullPixel) {
        var minCol = Double.MAX_VALUE;
        var maxCol = Double.MIN_VALUE;
        var minRow = Double.MAX_VALUE;
        var maxRow = Double.MIN_VALUE;

        for (var point : polygon.getVertices()) {
            minCol = Math.min(minCol, point.col());
            maxCol = Math.max(maxCol, point.col());
            minRow = Math.min(minRow, point.row());
            maxRow = Math.max(maxRow, point.row());
        }

        var grid = new char[(int) maxRow][(int) maxCol];
        drawOnGrid(List.of(polygon), grid, fullPixel);
    }

    public static void drawOnGrid(Polygon polygon) {
        drawOnGrid(polygon, '#');
    }

    public static char[][] createGrid(int rows, int cols, char emptyCell) {
        var grid = new char[rows][cols];
        for(var row : grid) {
            Arrays.fill(row, emptyCell);
        }
        return grid;
    }

    public static String drawGrid(char[][] grid) {
        var sb = new StringBuilder();
        for (var row : grid) {
            for (var col : row) {
                sb.append(col);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
