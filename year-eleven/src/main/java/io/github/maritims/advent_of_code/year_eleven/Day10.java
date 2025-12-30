package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;

public class Day10 extends PuzzleSolver<Long, Long> {
    @Override
    public Long solveFirstPart() {
        return loadInput()
                .stream()
                .map(FactoryMachine::parseFactoryMachine)
                .mapToLong(FactoryMachine::powerOn)
                .sum();
    }

    @Override
    public Long solveSecondPart() {
        return loadInput()
                .stream()
                .map(FactoryMachine::parseFactoryMachine)
                .peek(FactoryMachine::pullLever)
                .mapToLong(FactoryMachine::tuneJoltageCounters)
                .sum();
    }
}
