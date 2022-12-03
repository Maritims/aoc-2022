package io.github.maritims.aoc2022;

public class SecondPuzzle extends Puzzle {
    public SecondPuzzle(String fileName) {
        super(fileName);
    }

    public Integer solvePartOne() {
        int points = 0;
        for(String round : getFileContent()) {
            String[] moves = round.split(" ");
            int originalEnemyMove = moves[0].charAt(0);
            int originalPlayerMove = moves[1].charAt(0);

            // (A, B, C) & 3 -> (65, 66, 67) & 3 -> (1, 2, 3) -> Subtract 1 to use wrapping -> (0, 1, 2).
            int simplifiedEnemyMove = originalEnemyMove & 3;

            // (X, Y, Z) & 3 -> (88, 89, 90) & 3 -> (0, 1, 2).
            int simplifiedPlayerMove = originalPlayerMove & 3;

            // 1 (Enemy rock)       Mod 3 = 1 (Player paper)
            // 2 (Enemy paper)      Mod 3 = 2 (Player scissors)
            // 3 (Enemy scissors )  Mod 3 = 0 (Player rock)
            // Utilise zero multiplication for simplicity when calculating points.
            int pointsForVictory = (simplifiedEnemyMove % 3 == simplifiedPlayerMove ? 1 : 0) * 6;
            int pointsForDraw = (simplifiedEnemyMove - 1 == simplifiedPlayerMove ? 1 : 0) * 3;
            int pointsForLoss = (originalPlayerMove & 3) + 1;
            points += pointsForVictory + pointsForDraw + pointsForLoss;
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
