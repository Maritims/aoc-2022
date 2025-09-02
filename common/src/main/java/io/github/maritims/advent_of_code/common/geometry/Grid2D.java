package io.github.maritims.advent_of_code.common.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Grid2D<T> {
    private final List<List<T>> grid;

    public Grid2D(int cols, int rows, Supplier<T> supplier) {
        grid = new ArrayList<>(rows);
        for(var rowNum = 0; rowNum < rows; rowNum++) {
            var row = new ArrayList<T>();
            for(var colNum = 0; colNum < cols; colNum++) {
                row.add(supplier.get());
            }
            grid.add(row);
        }
    }

    public T get(int row, int col) {
        return grid.get(row).get(col);
    }

    public Stream<T> stream() {
        return grid.stream().flatMap(List::stream);
    }
}
