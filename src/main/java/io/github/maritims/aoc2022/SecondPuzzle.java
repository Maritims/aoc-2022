package io.github.maritims.aoc2022;

public class SecondPuzzle extends Puzzle<Integer> {
    /**
     * <p>This method takes an {@code int} representing the original move, and returns the simplified version.</p>
     * <br>
     * <p>The simplified version is calculated by performing a bitwise AND operation with the value 3.</p>
     * @param originalMove ASCII code for a character representing the original move.
     * @return ∈ {1, 2, 3}
     */
    public int getSimplifiedMove(int originalMove) {
        return originalMove & 3;
    }

    @Override
    public Integer solvePartOne(String filePath) {
        int points = 0;
        for(String round : getFileContent(filePath)) {
            String[] moves = round.split(" ");
            int originalPlayerMove = moves[1].charAt(0);
            int simplifiedEnemyMove = getSimplifiedMove(moves[0].charAt(0));
            int simplifiedPlayerMove = getSimplifiedMove(originalPlayerMove) + 1; // Add 1 to simplify ternary operations -> (1, 2, 3).

            // 1 (Enemy rock)       Mod 3 = 1 (Player paper)
            // 2 (Enemy paper)      Mod 3 = 2 (Player scissors)
            // 3 (Enemy scissors)   Mod 3 = 0 (Player rock)
            // Utilise zero multiplication for simplicity when calculating points.
            int pointsForVictory = (simplifiedEnemyMove % 3 == (simplifiedPlayerMove - 1) ? 1 : 0) * 6;
            int pointsForDraw = (simplifiedEnemyMove == simplifiedPlayerMove ? 1 : 0) * 3;
            int pointsForLoss = (originalPlayerMove & 3) + 1;
            points += pointsForVictory + pointsForDraw + pointsForLoss;
        }
        return points;
    }

    /**
     * <p>This method takes an {@code int} representing the original outcome, performs a bitwise AND operation, adds 1 and returns the outcome.</p>
     * <br>
     * <p>Output of AND operation ∈ {0, 1, 2}.</p>
     * <br>
     * <p>The 1 is added to simplify Mod operations with the result of {@link #getSimplifiedMove(int)}.</p>
     * @param originalOutcome ASCII code for a character representing the outcome.
     * @return ∈ {1, 2, 3}
     */
    public int getSimplifiedOutcome(int originalOutcome) {
        return (originalOutcome & 3) + 1;
    }

    /**
     * <p>This method takes two {@code int} representing a desired outcome and an enemy move, and returns the required player move to achieve this outcome.</p>
     * <br>
     * <p>The required player move is calculated by adding the outcome, the enemy move and performing Mod 3 on the result.</p>
     * @param simplifiedOutcome Desired outcome where the value ∈ {1, 2, 3}
     * @param simplifiedEnemyMove Enemy move where the value ∈ {1, 2, 3}
     * @return ∈ {0, 1, 2}
     */
    public int getRequiredPlayerMoveForOutcome(int simplifiedOutcome, int simplifiedEnemyMove) {
        return (simplifiedOutcome + simplifiedEnemyMove) % 3;
    }

    /**
     * <p>This method takes two {@code int} representing a simplified move and a simplified outcome, calculates the points for the move and outcome and returns the sum.</p>
     * <br>
     * <p>The points for the move are calculated by adding 1 to the move as the possible points for player moves ∈ {1, 2, 3}.</p>
     * <br>
     * <p>The points for the outcome are calculated by subtracting 1 from the outcome and multiplying by 3 as points for outcomes are either 0 (loss), 1 (draw) or 3 (win).</p>
     * @param simplifiedMove Player move where the value ∈ {0, 1, 2}
     * @param simplifiedOutcome Desired outcome where the value ∈ {1, 2, 3}
     * @return The sum of the points for the move and the points for the outcome.
     */
    public int getPoints(int simplifiedMove, int simplifiedOutcome) {
        int pointsForMove = simplifiedMove + 1;
        int pointsForOutcome = (simplifiedOutcome - 1) * 3;
        return pointsForMove + pointsForOutcome;
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        int points = 0;
        for(String round : getFileContent(filePath)) {
            String[] moves = round.split(" ");
            int simplifiedEnemyMove = getSimplifiedMove(moves[0].charAt(0));
            int simplifiedOutcome = getSimplifiedOutcome(moves[1].charAt(0));
            int simplifiedPlayerMoveForOutcome = getRequiredPlayerMoveForOutcome(simplifiedOutcome, simplifiedEnemyMove);
            points += getPoints(simplifiedPlayerMoveForOutcome, simplifiedOutcome);
        }
        return points;
    }
}
