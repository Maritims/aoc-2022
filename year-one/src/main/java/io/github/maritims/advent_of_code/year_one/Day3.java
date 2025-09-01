package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;

public class Day3 extends PuzzleSolver<Integer, Integer> {
    public Integer solveFirstPart() {
        loadInput();
        return new Santa().deliverPresents(loadInput().get(0).toCharArray(), 1).size();
    }

    public Integer solveSecondPart() {
        loadInput();
        return new Santa().deliverPresents(loadInput().get(0).toCharArray(), 2).size();
    }
}