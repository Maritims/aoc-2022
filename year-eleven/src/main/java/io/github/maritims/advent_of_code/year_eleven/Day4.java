package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.geometry.Grid2D;
import io.github.maritims.advent_of_code.common.geometry.Point2D;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day4 extends PuzzleSolver<Integer, Integer> {
    Grid2D<Character> createGrid(List<String> input) {
        var grid = new Grid2D<>(input.get(0).length(), input.size(), () -> '.');

        for (var row = 0; row < input.size(); row++) {
            for (var col = 0; col < input.get(row).length(); col++) {
                var cell = input.get(row).charAt(col);
                if (cell == '@') {
                    grid.set(row, col, cell);
                }
            }
        }

        return grid;
    }

    int walkGrid(Grid2D<Character> grid, boolean clearAccessibleCells) {
        var accessiblePaperRolls = new AtomicInteger(0);
        var cellsToClear         = new HashSet<Point2D>();

        grid.walkGrid((row, col, value, surroundingPoints) -> {
            if (value == '@') {
                var occupiedSurroundingPoints = surroundingPoints.stream()
                        .filter(point -> grid.get(point) == '@')
                        .count();
                if (occupiedSurroundingPoints < 4) {
                    accessiblePaperRolls.incrementAndGet();
                    if (clearAccessibleCells) {
                        cellsToClear.add(new Point2D(col, row));
                    }
                }
            }
        });

        if (clearAccessibleCells) {
            cellsToClear.forEach(cellToClear -> grid.set(cellToClear.row(), cellToClear.col(), '.'));
        }

        return accessiblePaperRolls.get();
    }

    @Override
    public Integer solveFirstPart() {
        var input = loadInput();
        var grid  = createGrid(input);
        return walkGrid(grid, false);
    }

    @Override
    public Integer solveSecondPart() {
        var input  = loadInput();
        var grid   = createGrid(input);
        var walked = 0;
        var sum    = 0;

        while (((walked = walkGrid(grid, true)) > 0)) {
            sum += walked;
        }

        return sum;
    }
}
