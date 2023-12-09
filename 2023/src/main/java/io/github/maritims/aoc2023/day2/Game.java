package io.github.maritims.aoc2023.day2;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Game {
    private static final Pattern gameIdPattern = Pattern.compile("^Game (\\d+)");

    private final String              gameInput;
    private final Map<Color, Integer> totalCubes;

    private Integer     gameId;
    private List<Round> rounds;

    public Game(String gameInput, Map<Color, Integer> totalCubes) {
        this.gameInput  = gameInput;
        this.totalCubes = totalCubes;
    }

    public int getId() {
        if (gameId == null) {
            gameId = gameIdPattern.matcher(gameInput)
                .results()
                .findFirst()
                .map(matchResult -> Integer.parseInt(matchResult.group(1)))
                .orElseThrow();
        }
        return gameId;
    }

    protected List<Round> getRounds() {
        if (rounds == null) {
            rounds = new ArrayList<>();
            String[] serializedRounds = gameInput.substring(gameInput.indexOf(":") + 1).split(";");

            for (String serializedRound : serializedRounds) {
                String[] cubes = serializedRound.split("s?,");

                Map<Color, Integer> playedCubes = Arrays.stream(cubes)
                    .map(cube -> cube.trim().split(" "))
                    .collect(Collectors.toMap(parts -> Color.fromString(parts[1]).orElseThrow(), parts -> Integer.parseInt(parts[0])));
                rounds.add(new Round(playedCubes));
            }
        }

        return rounds;
    }

    public boolean isPossible() {
        return getRounds().stream()
            .flatMap(round -> round.getCubes()
                .entrySet()
                .stream())
            .noneMatch(cubes -> cubes.getValue() > totalCubes.get(cubes.getKey()));
    }

    public Map<Color, Integer> getMinimumCubesForPlayability() {
        Map<Color, Integer> minimumCubesForPlayability = new HashMap<>();

        for (Round round : getRounds()) {
            for (Map.Entry<Color, Integer> cubes : round.getCubes().entrySet()) {
                if (!minimumCubesForPlayability.containsKey(cubes.getKey()) || cubes.getValue() > minimumCubesForPlayability.get(cubes.getKey())) {
                    minimumCubesForPlayability.put(cubes.getKey(), cubes.getValue());
                }
            }
        }

        return minimumCubesForPlayability;
    }

    public int getPowerOfMinimumCubesForPlayability() {
        return getMinimumCubesForPlayability().values()
            .stream()
            .reduce(1, (a, b) -> a * b);
    }
}