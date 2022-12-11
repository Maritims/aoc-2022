package io.github.maritims.aoc2022;

import java.awt.*;
import java.util.LinkedHashSet;

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
        private final Point tail = new Point(0, 0);
        private final LinkedHashSet<String> visitedByTail = new LinkedHashSet<>();

        public Point getHead() {
            return head;
        }

        public Point getTail() {
            return tail;
        }

        public LinkedHashSet<String> getVisitedByTail() {
            return visitedByTail;
        }

        public Rope move(Move... moves) {
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

                    // Is it time to move the tail?
                    int deltaX = head.x - tail.x;
                    if(Math.abs(deltaX) > 1) {
                        tail.x += deltaX > 0 ? 1 : -1;
                    }

                    int deltaY = head.y - tail.y;
                    if(Math.abs(deltaY) > 1) {
                        tail.y += deltaY > 0 ? 1 : -1;
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
        Rope rope = new Rope().move(moves);
        return rope.getVisitedByTail().size();
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}
