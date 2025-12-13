package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.util.StringUtils;

public class Day3 extends PuzzleSolver<Integer, Integer> {
    int findMax(String str) {
        var ints = StringUtils.toIntArray(str);
        var winner = 0;
        var runnerUp = 0;
        for (int i : ints) {
            if (i > winner) {
                winner = i;
            } else if(i > runnerUp) {
                runnerUp = i;
            }
        }
        return winner * 10 + runnerUp;
    }

    @Override
    public Integer solveFirstPart() {
        return 0;
    }

    @Override
    public Integer solveSecondPart() {
        return 0;
    }
}
