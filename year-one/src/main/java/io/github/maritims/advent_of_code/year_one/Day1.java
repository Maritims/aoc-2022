package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;

public class Day1 extends PuzzleSolver<Integer, Integer> {
    public Integer solveFirstPart() {
        loadInput();
        return new Santa().walk(input.getFirst().toCharArray()).currentFloor();
    }

    public Integer solveSecondPart() {
        loadInput();
        return new Santa().walk(input.getFirst().toCharArray()).positionOfInstructionLeadingToTheBasement();
    }
}
