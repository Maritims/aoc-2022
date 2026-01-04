package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;

import java.util.List;

public class Day14 extends PuzzleSolver<Long, Long> {
    private final int                 runtimeInSeconds;
    private       List<ReindeerState> reindeerStates;

    public Day14(int runtimeInSeconds) {
        this.runtimeInSeconds = runtimeInSeconds;
    }

    List<ReindeerState> solveBothParts() {
        if (reindeerStates == null) {
            var reindeer = loadInput()
                    .stream()
                    .map(Reindeer::parseReindeer)
                    .toList();
            reindeerStates = reindeer.stream()
                    .map(ReindeerState::new)
                    .toList();

            for (var second = 0; second < runtimeInSeconds; second++) {
                reindeerStates.forEach(ReindeerState::tick);
                var longestTravelledDistance = reindeerStates.stream().mapToLong(ReindeerState::getTravelledDistance).max().orElseThrow();
                reindeerStates.stream().filter(reindeerState -> reindeerState.getTravelledDistance() == longestTravelledDistance).forEach(ReindeerState::addPoint);
            }
        }

        return reindeerStates;
    }

    @Override
    public Long solveFirstPart() {
        return solveBothParts()
                .stream()
                .mapToLong(ReindeerState::getTravelledDistance)
                .max()
                .orElseThrow();
    }

    @Override
    public Long solveSecondPart() {
        return solveBothParts()
                .stream()
                .mapToLong(ReindeerState::getPoints)
                .max()
                .orElseThrow();
    }
}
