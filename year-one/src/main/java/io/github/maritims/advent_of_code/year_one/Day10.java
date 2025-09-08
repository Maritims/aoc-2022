package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.util.StringUtils;

public class Day10 extends PuzzleSolver<Integer, Integer> {
    @Override
    public Integer solveFirstPart() {
        var input = "1113122113";
        for (var i = 0; i < 40; i++) {
            input = StringUtils.lookAndSay(input);
        }
        return input.length();
    }

    @Override
    public Integer solveSecondPart() {
        var input = "1113122113";
        for (var i = 0; i < 50; i++) {
            input = StringUtils.lookAndSay(input);
        }
        return input.length();
    }
}
