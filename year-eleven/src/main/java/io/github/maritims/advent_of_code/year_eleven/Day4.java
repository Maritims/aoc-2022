package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.geometry.Grid2D;

import java.util.concurrent.atomic.AtomicInteger;

public class Day4 extends PuzzleSolver<Integer, Integer> {
    @Override
    public Integer solveFirstPart() {
        var input = loadInput();
        var grid  = new Grid2D<>(input.get(0).length(), input.size(), () -> '.');

        for (var row = 0; row < input.size(); row++) {
            for (var col = 0; col < input.get(row).length(); col++) {
                var cell = input.get(row).charAt(col);
                if (cell == '@') {
                    grid.set(row, col, cell);
                }
            }
        }

        var accessiblePaperRolls = new AtomicInteger(0);
        grid.walkGrid((row, col, value, surroundingPoints) -> {
            if (value == '@') {
                var occupiedSurroundingPoints = surroundingPoints.stream()
                        .filter(point -> grid.get(point) == '@')
                        .count();
                if (occupiedSurroundingPoints < 4) {
                    accessiblePaperRolls.incrementAndGet();
                }
            }
        });

        return accessiblePaperRolls.get();
    }

    @Override
    public Integer solveSecondPart() {
        return 0;
    }
}
