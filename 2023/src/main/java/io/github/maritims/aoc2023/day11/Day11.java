package io.github.maritims.aoc2023.day11;

import io.github.maritims.toolbox.Day;
import io.github.maritims.toolbox.geometry.Grid;

public class Day11 extends Day {
    protected Day11(boolean useSampleData) {
        super(11, useSampleData);
    }

    protected Grid<Character> getExpandedGrid() {
        var lines = readAllLines();
        var grid = Grid.parse(
            lines,
            (character) -> character,
            null
        );

        var emptyColumns = grid.findEmptyColumns();
        var columnOffset = 0;
        for(var emptyColumn : emptyColumns) {
            var colNum = emptyColumn + columnOffset;
            grid.addColumn(colNum + 1, () -> '.');
            columnOffset++;
        }

        var emptyRows = grid.findEmptyRows();
        var rowOffset = 0;
        for(var emptyRow : emptyRows) {
            var rowNum = emptyRow + rowOffset;
            grid.addRow(rowNum + 1, () -> '.');
            rowOffset++;
        }

        return grid;
    }

    @Override
    public long solvePartOne() {
        var grid = getExpandedGrid();

        return 0;
    }

    @Override
    public long solvePartTwo() {
        return 0;
    }
}
