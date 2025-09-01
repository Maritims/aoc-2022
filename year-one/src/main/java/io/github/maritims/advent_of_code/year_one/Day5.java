package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.StringUtils;

public class Day5 extends PuzzleSolver<Integer, Integer> {
    public Integer solveFirstPart() {
        return (int) loadInput()
                .stream()
                .filter(line -> StringUtils.numberOfVowels(line) >= 3 && StringUtils.hasLettersFollowingEachOther(line) && StringUtils.hasNeitherOfThesePatterns(line, "ab", "cd", "pq", "xy"))
                .count();
    }

    public Integer solveSecondPart() {
        return (int) loadInput()
                .stream()
                .filter(line -> StringUtils.hasRepeatedPairWithAnythingInBetween(line) && StringUtils.hasRepeatingLetterWithSingleDelimiter(line))
                .count();
    }
}
