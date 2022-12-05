package io.github.maritims.aoc2022;

public class SecondPuzzle extends Puzzle<Integer> {

    static class Rochambeau {
        /**
         * Converts the original ASCII code by performing a bitwise AND operation with the value 3.
         * @param asciiCode ASCII code for a character representing the move. Expects either A, B, C, X, Y or Z.
         * @return ∈ {1, 2, 3}
         */
        private static Integer getNormalisedCode(int asciiCode) {
            return asciiCode & 3;
        }

        /**
         * Plays a game of Rochambeau and returns the points from the player's perspective.
         * @param enemyMove ∈ {1 (Rock), 2 (Paper), 3 (Scissors)}
         * @param playerMove ∈ {1 (Paper), 2 (Scissors), 0 (Rock)}
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

        public static Integer playForOutcome(int enemyMove, int outcome) {
            return play(enemyMove, getMoveForOutcome(enemyMove, outcome));
        }

        /**
         * Gets the required player move for the desired outcome.
         * @param enemyMove ASCII code representing the enemy move ∈ {A, B, C, X, Y, Z}
         * @param outcome ASCII code representing the desired outcome ∈ {A, B, C, X, Y, Z}
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
