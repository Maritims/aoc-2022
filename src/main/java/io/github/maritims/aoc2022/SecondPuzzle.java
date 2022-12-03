package io.github.maritims.aoc2022;

public class SecondPuzzle extends Puzzle {
    public SecondPuzzle(String fileName) {
        super(fileName);
    }

    public Integer solvePartOne() {
        int points = 0;
        for(String round : getFileContent()) {
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

    public Integer solvePartTwo() {
        int points = 0;
        for(String round : getFileContent()) {
            String[] moves = round.split(" ");
            int enemy = moves[0].charAt(0);
            int you = enemy;
            int outcome = moves[1].charAt(0);
            if(outcome == 88) {
                you = enemy == 65 ? enemy + 2 : enemy - 1;
            } else if(outcome == 89) {
                points += 3;
            } else if(outcome == 90) {
                you = enemy == 67 ? enemy - 2 : enemy + 1;
                points += 6;
            }
            points += you - 64;
        }
        return points;
    }
}
