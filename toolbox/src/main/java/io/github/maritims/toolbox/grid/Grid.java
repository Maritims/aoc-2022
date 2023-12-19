package io.github.maritims.toolbox.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Grid<T> {
    private final T[][] array;
    private final int   rows;
    private final int   columns;

    /**
     * Generate a two-dimensional representation of the given lines of {@link String}.
     *
     * @param lines        Lines to represent.
     * @param arrayFactory Factory for creating the underlying array of the given generic type.
     * @param converter    Converter for converting each {@link Character} in every line to an instance of the given generic type.
     * @param consumer     Consumer for facilitating the application of external logic for every converted {@link Character}.
     * @param <T>          The type of the underlying supporting array.
     * @return A two-dimensional grid.
     */
    public static <T> Grid<T> parse(
        List<String> lines,
        BiFunction<Integer, Integer, T[][]> arrayFactory,
        Function<Character, T> converter,
        BiConsumer<T, Point> consumer
    ) {
        var rows  = lines.size();
        var cols  = lines.get(0).length();
        var array = arrayFactory.apply(rows, cols);

        for (var row = 0; row < rows; row++) {
            for (var col = 0; col < cols; col++) {
                var c = lines.get(row).charAt(col);
                var o = converter.apply(c);

                if (consumer != null) {
                    consumer.accept(o, Point.of(row, col));
                }

                array[row][col] = converter.apply(c);
            }
        }

        return new Grid<>(array);
    }

    public static Point add(Point p1, Point p2) {
        return Point.of(p1.row() + p2.row(), p1.column() + p2.column());
    }

    public Grid(T[][] array) {
        this.array   = array;
        this.rows    = array.length;
        this.columns = array[0].length;
    }

    /**
     * Find the four points which are directly above, to the left, to the right and below the given source point.
     */
    public List<Point> getNeighbours(Point source) {
        if (source.row() > rows || source.column() > columns) {
            throw new IndexOutOfBoundsException("The point " + source + " is not within the grid");
        }

        var neighbours = new ArrayList<Point>();

        if (source.row() > 0) {
            // There's a point directly above this one.
            neighbours.add(Point.of(source.row() - 1, source.column()));
        }

        if (source.column() > 0) {
            // There's a point directly to the left of this one.
            neighbours.add(Point.of(source.row(), source.column() - 1));
        }

        if (source.column() < columns - 1) {
            // There's a point directly to the right of this one.
            neighbours.add(Point.of(source.row(), source.column() + 1));
        }

        if (source.row() < rows - 1) {
            // There's a point directly below this one.
            neighbours.add(Point.of(source.row() + 1, source.column()));
        }

        return neighbours;
    }

    public T get(Point point) {
        return array[point.row()][point.column()];
    }
}