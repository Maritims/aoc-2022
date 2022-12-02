package io.github.maritims.aoc2022;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SecondPuzzle extends Puzzle {
    protected SecondPuzzle(String fileName) {
        super(fileName);
    }

    public Integer solvePartOne() {
        List<String> rounds = getFileContent(fileName);
        int points = 0;
        for(String round : rounds) {
            String[] moves = round.split(" ");
            int enemy = moves[0].charAt(0);
            int you = moves[1].charAt(0);
            int outcome = Math.abs(enemy - you);
            if(outcome == 23) {
                points += 3;
            } else if(outcome == 21 || outcome == 24) {
                points += 6;
            }
            points += you - 87;
        }
        return points;
    }
}
