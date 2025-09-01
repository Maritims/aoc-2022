package io.github.maritims.advent_of_code.common.geometry;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public static Direction fromInstruction(char instruction) {
        return switch (instruction) {
            case '(', '^' -> Direction.UP;
            case '<' -> Direction.LEFT;
            case '>' -> Direction.RIGHT;
            case ')', 'v' -> Direction.DOWN;
            default -> throw new IllegalArgumentException("Invalid instruction: " + instruction);
        };
    }
}
