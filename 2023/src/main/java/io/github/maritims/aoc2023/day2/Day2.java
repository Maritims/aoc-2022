package io.github.maritims.aoc2023.day2;

import io.github.maritims.aoc2023.day2.Color;
import io.github.maritims.aoc2023.day2.Game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Day2 {
    private final Map<Color, Integer> totalCubes;
    private final boolean             solveForPowerOfMinimumCubesForPlayability;

    public Day2(Map<Color, Integer> totalCubes, boolean solveForPowerOfMinimumCubesForPlayability) {
        this.totalCubes                                = totalCubes;
        this.solveForPowerOfMinimumCubesForPlayability = solveForPowerOfMinimumCubesForPlayability;
    }

    public int solve(String fileName) throws IOException {
        List<String> serializedGames = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        int          sum             = 0;

        for (String serializedGame : serializedGames) {
            Game game = new Game(serializedGame, totalCubes);
            if(solveForPowerOfMinimumCubesForPlayability) {
                sum += game.getPowerOfMinimumCubesForPlayability();
            } else {
                sum += game.isPossible() ? game.getId() : 0;
            }
        }

        return sum;
    }
}