package io.github.maritims.aoc2022;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day2 extends Puzzle<Integer, Integer> {

    static class Rochambeau {
        /**
         * Converts the original ASCII code by performing a bitwise AND operation with the value 3.
         * @param asciiCode ASCII code for a character representing moves and outcomes ∈ {A, B, C, X, Y, Z}
         * @return {A, B, C} = {1, 2, 3} and {X, Y, Z} = {0, 1, 2}
         */
        private static Integer getNormalisedCode(int asciiCode) {
            return asciiCode & 3;
        }

        /**
         * Plays a game of Rochambeau and returns the points from the player's perspective.
         * @param enemyMove ∈ {A = 65 = Rock, B = 66 = Paper, C = 67 = Scissors}
         * @param playerMove ∈ {X = 88 = Paper, Y = 89 = Scissors, Z = 90 = Rock}
         * @return The result of the game in points. 6 (victory), 3 (draw), 0 (loss) plus 1 (rock), 2 (paper) or 3 (scissors).
         */
        public static Integer play(int enemyMove, int playerMove) {
            playerMove = getNormalisedCode(playerMove) + 1;
            enemyMove = getNormalisedCode(enemyMove);
            // 1 (Enemy rock)       Mod 3 = 1 (Player paper)
            // 2 (Enemy paper)      Mod 3 = 2 (Player scissors)
            // 3 (Enemy scissors)   Mod 3 = 0 (Player rock)
            // Utilise zero multiplication for simplicity when calculating points.
            int pointsForVictory = (enemyMove % 3 == (playerMove - 1) ? 1 : 0) * 6;
            int pointsForDraw = (enemyMove == playerMove ? 1 : 0) * 3;
            int pointsForLoss = playerMove;
            return pointsForVictory + pointsForDraw + pointsForLoss;
        }

        /**
         * Plays a game of Rochambeau and returns the points from the player's perspective, but forces the desired outcome.
         * @param enemyMove ∈ {A = 65 = Rock, B = 66 = Paper, C = 67 = Scissors}
         * @param outcome ∈ {X = 88 = Loss, Y = 89 = Draw, Z = 90 = Victory}
         */
        public static Integer playForOutcome(int enemyMove, int outcome) {
            return play(enemyMove, getMoveForOutcome(enemyMove, outcome));
        }

        /**
         * Gets the required player move for the desired outcome.
         * @param enemyMove ASCII code representing the enemy move ∈ {A = 65, B = 66, C = 67}
         * @param outcome ASCII code representing the desired outcome ∈ {X = 88, Y = 89, Z = 90}
         * @return Required player move ∈ {0, 1, 2}
         */
        public static Integer getMoveForOutcome(int enemyMove, int outcome) {
            enemyMove = getNormalisedCode(enemyMove);
            outcome = getNormalisedCode(outcome) + 1;
            return (outcome + enemyMove) % 3;
        }
    }

    @Override
    public Integer solvePartOne(String filePath) {
        return getFileContent(filePath).stream()
                .map(round -> round.split(" "))
                .mapToInt(moves -> Rochambeau.play(moves[0].charAt(0), moves[1].charAt(0)))
                .sum();
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return getFileContent(filePath).stream()
                .map(round -> round.split(" "))
                .mapToInt(moves -> Rochambeau.playForOutcome(moves[0].charAt(0), moves[1].charAt(0)))
                .sum();
    }
}
