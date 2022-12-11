package io.github.maritims.aoc2022;

import java.awt.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class NinthPuzzle extends Puzzle<Integer> {
    static class Move {
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
        private final Point head = new Point(0, 0);
        private final LinkedList<Point> knots = new LinkedList<>();
        private final LinkedHashSet<String> visitedByTrackedKnot = new LinkedHashSet<>();

        public Rope(int knots) {
            for(int i = 0; i < knots; i++) {
                this.knots.add(new Point(0, 0));
            }
        }

        public Rope() {
            this(1);
        }

        public Point getHead() {
            return head;
        }

        public LinkedList<Point> getKnots() {
            return knots;
        }

        public LinkedHashSet<String> getVisitedByTrackedKnot() {
            return visitedByTrackedKnot;
        }

        public Rope move(int tailToTrack, Move... moves) {
            for(Move move : moves) {
                for (int step = 0; step < move.getSteps(); step++) {
                    switch (move.getDirection()) {
                        case 'U':
                        case 'D':
                            head.y += move.isDirection('U') ? 1 : -1;
                            break;
                        case 'R':
                        case 'L':
                            head.x += move.isDirection('R') ? 1 : -1;
                            break;
                    }

                    // Is it time to move the tails?
                    for(int i = 0; i < knots.size(); i++) {
                        Point reference = i == 0 ? head : knots.get(i - 1);
                        int deltaX = reference.x - knots.get(i).x;
                        int deltaY = reference.y - knots.get(i).y;

                        // Condition 1: The head is moving horizontally.
                        // Condition 2: The head is moving diagonally. The tail is now one step to the left or right of the head, but more than one step above or below it.
                        if (Math.abs(deltaX) > 1 || Math.abs(deltaX) == 1 && Math.abs(deltaY) > 1) {
                            knots.get(i).x += deltaX > 0 ? 1 : -1;
                        }

                        // Condition 1: The head is moving vertically.
                        // Condition 2: The head is moving diagonally. The tail is now one step above or below the head, but more than one step to the left or right of it.
                        if (Math.abs(deltaY) > 1 || Math.abs(deltaY) == 1 && Math.abs(deltaX) > 1) {
                            knots.get(i).y += deltaY > 0 ? 1 : -1;
                        }

                        if(tailToTrack == i + 1) {
                            visitedByTrackedKnot.add(knots.get(i).x + "," + knots.get(i).y);
                        }
                    }

/*                    for(Point tail : tails) {
                        int deltaX = head.x - tail.x;
                        int deltaY = head.y - tail.y;

                        // Condition 1: The head is moving horizontally.
                        // Condition 2: The head is moving diagonally. The tail is now one step to the left or right of the head, but more than one step above or below it.
                        if (Math.abs(deltaX) > 1 || Math.abs(deltaX) == 1 && Math.abs(deltaY) > 1) {
                            tail.x += deltaX > 0 ? 1 : -1;
                        }

                        // Condition 1: The head is moving vertically.
                        // Condition 2: The head is moving diagonally. The tail is now one step above or below the head, but more than one step to the left or right of it.
                        if (Math.abs(deltaY) > 1 || Math.abs(deltaY) == 1 && Math.abs(deltaX) > 1) {
                            tail.y += deltaY > 0 ? 1 : -1;
                        }

                        visitedByTail.add(tail.x + "," + tail.y);
                    }*/
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
        Rope rope = new Rope().move(1, moves);
        return rope.getVisitedByTrackedKnot().size();
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        Move[] moves = getFileContent(filePath).stream()
                .map(Move::new)
                .toArray(Move[]::new);
        Rope rope = new Rope(9).move(9, moves);
        return rope.getVisitedByTrackedKnot().size();
    }
}
