package io.github.maritims.aoc2023.day11;

import io.github.maritims.toolbox.Day;
import io.github.maritims.toolbox.MathUtil;
import io.github.maritims.toolbox.geometry.Grid;
import io.github.maritims.toolbox.geometry.Point;
import io.github.maritims.toolbox.tuples.Tuple2;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;

public class Day11 extends Day {
    private final int multiplier;

    protected Day11(boolean useSampleData, int offset) {
        super(11, useSampleData);
        this.multiplier = offset;
    }

    /**
     * Counts lines (rows or columns) between the source and destination.
     *
     * @param emptyLines  The numbers off the empty lines.
     * @param source      Source point.
     * @param destination Destination point.
     * @param countRows   A boolean indicating whether to count rows (if true), or columns if false.
     * @return Number of lines between the source and destination.
     */
    protected int getLinesBetween(Set<Integer> emptyLines, Point source, Point destination, boolean countRows) {
        var                      dimensions             = countRows ? new Tuple2<>(source.row(), destination.row()) : new Tuple2<>(source.column(), destination.column());
        final Predicate<Integer> isBetweenWhenGoingUp   = (emptyLine) -> dimensions.item1() < dimensions.item2() && emptyLine >= dimensions.item1() && emptyLine <= dimensions.item2();
        final Predicate<Integer> isBetweenWhenGoingDown = (emptyLine) -> dimensions.item1() > dimensions.item2() && emptyLine <= dimensions.item1() && emptyLine >= dimensions.item2();

        return (int) emptyLines.stream()
            .filter(emptyRow -> isBetweenWhenGoingUp.test(emptyRow) || isBetweenWhenGoingDown.test(emptyRow))
            .count();
    }

    protected long getSumOfShortestPathBetweenAllGalaxies() {
        var lines        = readAllLines();
        var grid         = Grid.parse(lines, (character) -> character, null);
        var galaxies     = new ArrayList<>(grid.findPoints((c) -> c.equals('#')));
        var emptyColumns = grid.findEmptyColumns();
        var emptyRows    = grid.findEmptyRows();
        var steps        = new ArrayList<Long>();

        for (var i = 0; i < galaxies.size(); i++) {
            var source = galaxies.get(i);

            for (var j = 0; j < galaxies.size(); j++) {
                if (i == j) {
                    // We obviously don't need to know the number of steps from a galaxy to itself.
                    continue;
                }

                var destination       = galaxies.get(j);

                // Expansion of the grid is only necessary between the source and destination and nowhere else.
                // Expansion can be simulated by finding the number of empty lines between the source and destination and multiplying the result with an expansion multiplier minus 1.
                var emptyRowsBetween  = getLinesBetween(emptyRows, source, destination, true) * (multiplier - 1);
                var emptyColsBetween  = getLinesBetween(emptyColumns, source, destination, false) * (multiplier - 1);
                var manhattanDistance = MathUtil.getManhattanDistance(source, destination) + emptyRowsBetween + emptyColsBetween;
                steps.add((long) manhattanDistance);
            }
        }

        return steps.stream().reduce(0L, Long::sum) / 2;
    }

    @Override
    public long solvePartOne() {
        return getSumOfShortestPathBetweenAllGalaxies();
    }

    @Override
    public long solvePartTwo() {
        return getSumOfShortestPathBetweenAllGalaxies();
    }
}