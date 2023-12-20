package io.github.maritims.toolbox.geometry;

public record Point(int row, int column) {
    public static Point of(int row, int col) {
        return new Point(row, col);
    }

    @Override
    public String toString() {
        return "(" + row + "," + column + ")";
    }
}
