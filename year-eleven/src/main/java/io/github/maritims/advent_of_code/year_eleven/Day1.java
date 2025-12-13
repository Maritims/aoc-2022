package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;

public class Day1 extends PuzzleSolver<Integer, Integer> {
    @Override
    public Integer solveFirstPart() {
        loadInput();
        var dial = new Dial(50);
        var count = 0;
        for(var line : input) {
            dial = dial.turnByInstruction(line);
            if (dial.position() == 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Integer solveSecondPart() {
        var input = loadInput();
        var dial = new Dial(50);
        for(var line : input) {
            dial = dial.turnByInstruction(line);
        }
        return dial.zeroHits();
    }
}
