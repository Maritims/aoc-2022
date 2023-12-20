package io.github.maritims.toolbox.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid<T> {
    protected final List<List<T>> list;

    /**
     * Generate a two-dimensional representation of the given lines of {@link String}.
     *
     * @param lines     Lines to represent.
     * @param converter Converter for converting each {@link Character} in every line to an instance of the given generic type.
     * @param consumer  Consumer for facilitating the application of external logic for every converted {@link Character}.
     * @param <T>       The type of the underlying supporting array.
     * @return A two-dimensional grid.
     */
    public static <T> Grid<T> parse(
        List<String> lines,
        Function<Character, T> converter,
        BiConsumer<T, Point> consumer
    ) {
        var rows = lines.size();
        var cols = lines.get(0).length();
        var list = new ArrayList<List<T>>();

        for (var rowNum = 0; rowNum < rows; rowNum++) {
            var row = new ArrayList<T>();
            for (var colNum = 0; colNum < cols; colNum++) {
                var c = lines.get(rowNum).charAt(colNum);
                var o = converter.apply(c);

                if (consumer != null) {
                    consumer.accept(o, Point.of(rowNum, colNum));
                }

                row.add(converter.apply(c));
            }
            list.add(row);
        }

        return new Grid<>(list);
    }

    public static <T> Grid<T> parse(String text, Function<Character, T> converter, BiConsumer<T, Point> consumer) {
        var lines = Arrays.stream(text.split("\n")).toList();
        return parse(lines, converter, consumer);
    }

    public static Point add(Point p1, Point p2) {
        return Point.of(p1.row() + p2.row(), p1.column() + p2.column());
    }

    public Grid(List<List<T>> list) {
        this.list    = list;
    }

    public int getNumberOfRows() {
        return list.size();
    }

    public int getNumberOfColumns() {
        return list.isEmpty() ? 0 : list.get(0).size();
    }

    public void addRow(int index, Supplier<T> supplier) {
        var row = IntStream.range(0, getNumberOfColumns()).mapToObj(i -> supplier.get()).toList();
        list.add(index, row);
    }

    public void addColumn(int index, Supplier<T> supplier) {
        for(var rowNum = 0; rowNum < getNumberOfRows(); rowNum++) {
            list.get(rowNum).add(index, supplier.get());
        }
    }

    /**
     * Find the four points which are directly above, to the left, to the right and below the given source point.
     */
    public List<Point> getNeighbours(Point source) {
        if (source.row() > getNumberOfRows() || source.column() > getNumberOfColumns()) {
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

        if (source.column() < getNumberOfColumns() - 1) {
            // There's a point directly to the right of this one.
            neighbours.add(Point.of(source.row(), source.column() + 1));
        }

        if (source.row() < getNumberOfRows() - 1) {
            // There's a point directly below this one.
            neighbours.add(Point.of(source.row() + 1, source.column()));
        }

        return neighbours;
    }

    public T getTile(Point point) {
        return list.get(point.row()).get(point.column());
    }

    public List<T> getRow(int rowNum) {
        return list.get(rowNum);
    }

    public List<T> getColumn(int colNum) {
        return IntStream.range(0, getNumberOfRows())
            .mapToObj(rowNum -> getTile(Point.of(rowNum, colNum)))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Integer> findEmptyRows() {
        return IntStream.range(0, getNumberOfRows())
            .filter(rowNum -> list.get(rowNum).stream().allMatch(c -> c.equals('.')))
            .boxed()
            .toList();
    }

    public List<Integer> findEmptyColumns() {
        var emptyColumns = new ArrayList<Integer>();
        for(var colNum = 0; colNum < getNumberOfColumns(); colNum++) {
            var isEmptyColumn = true;
            for(var rowNum = 0; rowNum < getNumberOfRows(); rowNum++) {
                if (!list.get(rowNum).get(colNum).equals('.')) {
                    isEmptyColumn = false;
                    break;
                }
            }
            if(isEmptyColumn) emptyColumns.add(colNum);
        }
        return emptyColumns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(var row : list) {
            var str = row.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
            sb.append(str).append("\n");
        }
        return sb.toString();
    }
}