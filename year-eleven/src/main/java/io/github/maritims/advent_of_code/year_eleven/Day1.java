package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;

public class Day1 extends PuzzleSolver<Integer, Integer> {
    @Override
    public Integer solveFirstPart() {
        return loadInput()
                .stream()
                .reduce(new Dial(50), Dial::turnByInstruction, (d1, d2) -> d1)
                .zeroPositions();
    }

    @Override
    public Integer solveSecondPart() {
        return loadInput()
                .stream()
                .reduce(new Dial(50), Dial::turnByInstruction, (d1, d2) -> d1)
                .zeroHits();
    }
}
