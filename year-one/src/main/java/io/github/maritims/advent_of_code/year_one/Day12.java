package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;

import java.io.FileNotFoundException;

public class Day12 extends PuzzleSolver<Integer, Integer> {
    @Override
    public Integer solveFirstPart() {
        try {
            return new AccountingElf(new AccountingElf.ConsiderAllLedgers()).balance(loadInputStream());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer solveSecondPart() {
        try {
            return new AccountingElf(new AccountingElf.ExcludeRedLedgers()).balance(loadInputStream());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
