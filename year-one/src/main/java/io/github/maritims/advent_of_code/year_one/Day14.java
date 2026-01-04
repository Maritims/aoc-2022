package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;

import java.util.List;

public class Day14 extends PuzzleSolver<Long, Long> {
    private final int runtimeInSeconds;

    public Day14(int runtimeInSeconds) {
        this.runtimeInSeconds = runtimeInSeconds;
    }

    List<ReindeerState> play() {
        var reindeer = loadInput()
                .stream()
                .map(Reindeer::parseReindeer)
                .toList();
        var reindeerStates = reindeer.stream()
                .map(ReindeerState::new)
                .toList();

        for(var second = 0; second < runtimeInSeconds; second++) {
            reindeerStates.forEach(ReindeerState::tick);
            var longestTravelledDistance = reindeerStates.stream().mapToLong(ReindeerState::getTravelledDistance).max().orElseThrow();
            reindeerStates.stream().filter(reindeerState -> reindeerState.getTravelledDistance() == longestTravelledDistance).forEach(ReindeerState::addPoint);
        }

        return reindeerStates;
    }

    @Override
    public Long solveFirstPart() {
        return play()
                .stream()
                .mapToLong(ReindeerState::getTravelledDistance)
                .max()
                .orElseThrow();
    }

    @Override
    public Long solveSecondPart() {
        return play()
                .stream()
                .mapToLong(ReindeerState::getPoints)
                .max()
                .orElseThrow();
    }
}
