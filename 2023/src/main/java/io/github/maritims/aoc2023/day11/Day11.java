package io.github.maritims.aoc2023.day11;

import io.github.maritims.toolbox.Day;
import io.github.maritims.toolbox.SetUtil;
import io.github.maritims.toolbox.geometry.Grid;
import io.github.maritims.toolbox.geometry.Point;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
        var galaxies = new ArrayList<>(grid.findPoints((c) -> c.equals('#')));
        var steps = new ArrayList<Integer>();

        for(var i = 0; i < galaxies.size(); i++) {
            var source = galaxies.get(i);

            for(var j = 0; j < galaxies.size(); j++) {
                if (i == j) {
                    // We obviously don't need to know the number of steps from a galaxy to itself.
                    continue;
                }
                var destination = galaxies.get(j);

                var n                       = Math.abs(destination.column() - source.column());
                var m                       = Math.abs(destination.row() - source.row());
                var fromSourceToDestination = n + m;
                steps.add(fromSourceToDestination);
            }
        }

        return steps.stream().reduce(0, Integer::sum) / 2;
    }

    @Override
    public long solvePartTwo() {
        return 0;
    }
}
