package io.github.maritims.advent_of_code.common.geometry;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Grid2D<T> {
    public static final Point2D[] CARDINAL_DIRECTIONS = {
            new Point2D(-1, 0),  // North
            new Point2D(0, 1),   // East
            new Point2D(1, 0),   // South
            new Point2D(0, -1),  // West
            new Point2D(-1, 1),  // NorthEast
            new Point2D(1, 1),   // SouthEast
            new Point2D(1, -1),  // SouthWest
            new Point2D(-1, -1)  // NorthWest
    };

    @FunctionalInterface
    public interface GridWalker<T> {
        void visit(int row, int col, T value, List<Point2D> surroundingPoints);
    }

    private final List<List<T>> grid;
    private final int rows;
    private final int cols;

    public Grid2D(int cols, int rows, Supplier<T> supplier) {
        this.rows = rows;
        this.cols = cols;

        grid = new ArrayList<>(rows);
        for(var rowNum = 0; rowNum < rows; rowNum++) {
            var row = new ArrayList<T>();
            for(var colNum = 0; colNum < cols; colNum++) {
                row.add(supplier.get());
            }
            grid.add(row);
        }
    }

    public Grid2D(int cols, int rows, BiFunction<Integer, Integer, T> supplier) {
        this.rows = rows;
        this.cols = cols;

        grid = new ArrayList<>(rows);
        for(var rowNum = 0; rowNum < rows; rowNum++) {
            var row = new ArrayList<T>();
            for(var colNum = 0; colNum < cols; colNum++) {
                row.add(supplier.apply(rowNum, colNum));
            }
            grid.add(row);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public T get(int row, int col) {
        return grid.get(row).get(col);
    }

    public T get(Point2D point2D) {
        return get(point2D.row(), point2D.col());
    }

    public Grid2D<T> set(int row, int col, T value) {
        grid.get(row).set(col, value);
        return this;
    }

    private List<Point2D> getSurroundingPoints(int row, int col) {
        List<Point2D> points = new ArrayList<>();
        for (Point2D direction : CARDINAL_DIRECTIONS) {
            int newRow = row + direction.row();
            int newCol = col + direction.col();
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                points.add(new Point2D(newCol, newRow));
            }
        }
        return points;
    }

    public void walkGrid(GridWalker<T> walker) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                walker.visit(row, col, get(row, col), getSurroundingPoints(row, col));
            }
        }
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for(var row : grid) {
            for(var col : row) {
                sb.append(col).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
