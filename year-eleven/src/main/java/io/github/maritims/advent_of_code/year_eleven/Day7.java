package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.geometry.Grid2D;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Day7 extends PuzzleSolver<Integer, Long> {
    @Override
    public Integer solveFirstPart() {
        var lines = loadInput();
        var grid = new Grid2D<>(
                lines.getFirst().length(),
                lines.size(),
                (rowNum, colNum) -> lines.get(rowNum).charAt(colNum)
        );

        var beamColumns = new boolean[grid.getCols()];
        var timesSplit = new AtomicInteger(0);
        grid.walkGrid((row, col, value, surroundingPoints) -> {
            if (value == 'S') {
                beamColumns[col] = true;
            } else if (value == '^' && beamColumns[col]) {
                beamColumns[col] = false;
                beamColumns[col - 1] = true;
                beamColumns[col + 1] = true;
                timesSplit.addAndGet(1);
            }
        });

        return timesSplit.get();
    }

    @Override
    public Long solveSecondPart() {
        var lines = loadInput();
        var grid = new Grid2D<>(
                lines.getFirst().length(),
                lines.size(),
                (rowNum, colNum) -> lines.get(rowNum).charAt(colNum)
        );

        var beamColumnsAcrossEveryTimeline = new long[grid.getCols()];
        grid.walkGrid((row, col, value, surroundingPoints) -> {
            if (value == 'S') {
                // We've encountered the emitter. There's currently only one beam in this column across every timeline.
                beamColumnsAcrossEveryTimeline[col] = 1;
            } else if (value == '^') {
                // We've encountered a splitter.
                // Split the beam and increase the number of beams in the columns on either side across all timelines.
                beamColumnsAcrossEveryTimeline[col - 1] += beamColumnsAcrossEveryTimeline[col];
                beamColumnsAcrossEveryTimeline[col + 1] += beamColumnsAcrossEveryTimeline[col];

                // The original column no longer has a beam in any timeline as it was split.
                beamColumnsAcrossEveryTimeline[col] = 0;
            }
        });

        return Arrays.stream(beamColumnsAcrossEveryTimeline).sum();
    }
}
