package io.github.maritims.aoc2023;

import io.github.maritims.toolbox.Day;
import io.github.maritims.toolbox.MathUtil;
import io.github.maritims.toolbox.geometry.Grid;
import io.github.maritims.toolbox.geometry.PicksTheorem;
import io.github.maritims.toolbox.geometry.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Day10 extends Day {
    private static final Map<Character, List<Point>> OFFSETS = Map.of(
        '|', List.of(
            Point.of(1, 0), // North
            Point.of(-1, 0) // South
        ),
        '-', List.of(
            Point.of(0, 1), // East
            Point.of(0, -1) // West
        ),
        'L', List.of(
            Point.of(-1, 0), // North
            Point.of(0, 1) // East
        ),
        'J', List.of(
            Point.of(-1, 0), // North
            Point.of(0, -1) // West
        ),
        '7', List.of(
            Point.of(1, 0), // South
            Point.of(0, -1) // West
        ),
        'F', List.of(
            Point.of(1, 0), // South
            Point.of(0, 1) // East
        )
    );

    protected Day10(boolean useSampleData) {
        super(10, useSampleData);
    }

    /**
     * Use {@link Day10#OFFSETS} to determine all possible moves from the given {@link Point}.
     * This method can never return more than two points as no pipe symbol is mapped to more than two offsets.
     */
    protected List<Point> getPossibleMoves(Grid<Character> grid, Point point) {
        var pipe = grid.getTile(point);
        return OFFSETS.get(pipe)
            .stream()
            .map(offset -> Grid.add(point, offset))
            .toList();
    }

    protected List<Point> getConnectedNeighbours(Grid<Character> grid, Point point) {
        var neighbours = grid.getNeighbours(point);

        var connectedNeighbours = new ArrayList<Point>();
        for (var neighbour : neighbours) {
            var pipe = grid.getTile(neighbour);
            if (pipe == '.') {
                // We're not interested in ground.
                continue;
            }

            // If the starting point is one of the possible moves from the current neighbour it means this neighbour is connected to the starting point via a compatible pipe.
            var possibleMoves = getPossibleMoves(grid, neighbour);
            if (possibleMoves.contains(point)) {
                connectedNeighbours.add(neighbour);
            }
        }
        return connectedNeighbours;
    }

    protected List<Point> getPathThroughLoop(List<String> lines) {
        var startingPoint = new AtomicReference<Point>();
        var grid = Grid.parse(
            lines,
            (character) -> character,
            (character, point) -> {
                if (character == 'S') {
                    startingPoint.set(point);
                }
            }
        );

        // Get neighbours of the starting point connected via a compatible pipe.
        // It doesn't matter if we pick the first or second one. It's a loop, so the middle is the point farthest from the starting point no matter which direction we go in.
        var current = getConnectedNeighbours(grid, startingPoint.get()).get(0);

        // Walk through the first connected compatible pipe and find the connected compatible pipes of that pipe.
        // If either of the next pipes is the starting pipe, break the loop.
        var points = new ArrayList<>(List.of(startingPoint.get()));
        while (true) {
            // This is where we just came from.
            var last = points.get(points.size() - 1);
            points.add(current);

            // The two possible moves from the current position will either be backward in the direction we came from or forward.
            // To prevent turning around we keep track of the previous position in the "last" variable.
            var possibleMoves = getPossibleMoves(grid, current);

            // We've completed the loop if either of the next possible moves is the starting point and we didn't just come from there.
            if (possibleMoves.contains(startingPoint.get()) && !last.equals(startingPoint.get())) {
                // The furthest point from the starting point is the center of the loop.
                break;
            }

            // We don't want to go backward, so the new value of "current" will be that of the possible moves which we didn't just come from.
            current = possibleMoves.get(1).equals(last) ? possibleMoves.get(0) : possibleMoves.get(1);
        }

        return points;
    }

    @Override
    public long solvePartOne() {
        var lines  = readAllLines();
        var points = getPathThroughLoop(lines);
        // This is how far we had to go to get to the middle of the loop.
        return (int) Math.ceil((double) points.size() / 2);
    }

    @Override
    public long solvePartTwo() {
        var lines      = useSampleData ? readAllLines("day10_part2_sample.txt") : readAllLines();
        var points     = getPathThroughLoop(lines);
        var areaOfLoop = MathUtil.shoelace(points);
        return PicksTheorem.getInteriorPoints(areaOfLoop, points.size());
    }
}