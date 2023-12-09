package io.github.maritims.aoc2022;

import java.awt.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day9 extends Puzzle<Integer, Integer> {
    public static class Move {
        private final char direction;
        private final int steps;

        private Move(char direction, int steps) {
            this.direction = direction;
            this.steps = steps;
        }

        public Move(String move) {
            this.direction = move.charAt(0);
            this.steps = Integer.parseInt(move.split(" ")[1]);
        }

        public char getDirection() {
            return direction;
        }

        public int getSteps() {
            return steps;
        }

        public boolean isDirection(char direction) {
            return direction == this.direction;
        }

        public static Move up(int steps) {
            return new Move('U', steps);
        }

        public static Move down(int steps) {
            return new Move('D', steps);
        }

        public static Move right(int steps) {
            return new Move('R', steps);
        }

        public static Move left(int steps) {
            return new Move('L', steps);
        }
    }

    static class Rope {
        private final LinkedList<Point> knots = new LinkedList<>();
        private final LinkedHashSet<String> visitedByTrackedKnot = new LinkedHashSet<>();

        public Rope(int knots) {
            for(int i = 0; i < knots; i++) {
                this.knots.add(new Point(0, 0));
            }
        }

        public Point getHead() {
            return knots.get(0);
        }

        public LinkedList<Point> getKnots() {
            return knots;
        }

        public LinkedHashSet<String> getVisitedByTrackedKnot() {
            return visitedByTrackedKnot;
        }

        public Rope move(int knotToTrack, Move... moves) {
            for(Move move : moves) {
                for (int step = 0; step < move.getSteps(); step++) {
                    switch (move.getDirection()) {
                        case 'U':
                        case 'D':
                            getHead().y += move.isDirection('U') ? 1 : -1;
                            break;
                        case 'R':
                        case 'L':
                            getHead().x += move.isDirection('R') ? 1 : -1;
                            break;
                    }

                    // Is it time to move the knots?
                    for(int i = 1; i < knots.size(); i++) {
                        Point previousKnot = knots.get(i - 1);
                        int deltaX = previousKnot.x - knots.get(i).x;
                        int deltaY = previousKnot.y - knots.get(i).y;

                        // Condition 1: The previous knot is moving horizontally.
                        // Condition 2: The previous knot is moving diagonally. The knot is now one step to the left or right of the previous knot, but more than one step above or below it.
                        if (Math.abs(deltaX) > 1 || Math.abs(deltaX) == 1 && Math.abs(deltaY) > 1) {
                            knots.get(i).x += deltaX > 0 ? 1 : -1;
                        }

                        // Condition 1: The previous knot is moving vertically.
                        // Condition 2: The previous knot is moving diagonally. The knot is now one step above or below the previous knot, but more than one step to the left or right of it.
                        if (Math.abs(deltaY) > 1 || Math.abs(deltaY) == 1 && Math.abs(deltaX) > 1) {
                            knots.get(i).y += deltaY > 0 ? 1 : -1;
                        }

                        if(knotToTrack == i + 1) {
                            visitedByTrackedKnot.add(knots.get(i).x + "," + knots.get(i).y);
                        }
                    }
                }
            }
            return this;
        }
    }

    @Override
    public Integer solvePartOne(String filePath) {
        Move[] moves = getFileContent(filePath).stream()
                .map(Move::new)
                .toArray(Move[]::new);
        Rope rope = new Rope(2).move(2, moves);
        return rope.getVisitedByTrackedKnot().size();
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        Move[] moves = getFileContent(filePath).stream()
                .map(Move::new)
                .toArray(Move[]::new);
        Rope rope = new Rope(10).move(10, moves);
        return rope.getVisitedByTrackedKnot().size();
    }
}
