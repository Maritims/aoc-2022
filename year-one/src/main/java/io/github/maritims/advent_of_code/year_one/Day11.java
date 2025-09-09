package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.util.StringUtils;

public class Day11 extends PuzzleSolver<String, String> {
    String createSecurePassword(String password) {
        var isSecure = false;
        while(!isSecure) {
            password = StringUtils.incrementString(password);
            isSecure = StringUtils.hasIncreasingStraightSequence(password) && StringUtils.hasNonOverlappingPairs(password) && StringUtils.hasNoneOfTheseLetters(password, 'i', 'l', 'o');
        }
        return password;
    }

    @Override
    public String solveFirstPart() {
        var input = "hepxcrrq";
        return createSecurePassword(input);
    }

    @Override
    public String solveSecondPart() {
        var input = "hepxxyzz";
        return createSecurePassword(input);
    }
}
