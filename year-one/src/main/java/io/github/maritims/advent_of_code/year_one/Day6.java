package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.Instruction;
import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.StateAction;
import io.github.maritims.advent_of_code.common.geometry.Grid2D;
import io.github.maritims.advent_of_code.common.geometry.Line2D;
import io.github.maritims.advent_of_code.common.geometry.Point2D;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class Day6 extends PuzzleSolver<Integer, Integer> {
    private static final Pattern STATE_ACTION_PATTERN = Pattern.compile("^(?:turn )?(on|off|toggle)?\\s?(\\d+),(\\d+) through (\\d+),(\\d+)$");

    static class InstructionBuilder {
        private InstructionBuilder() {
        }

        public static Instruction<Line2D, StateAction> buildFromString(String instruction) {
            var matcher = STATE_ACTION_PATTERN.matcher(instruction);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid instruction: " + instruction);
            }

            var stateAction = StateAction.fromString(matcher.group(1));
            var startColumn = Integer.parseInt(matcher.group(2));
            var startRow    = Integer.parseInt(matcher.group(3));
            var endColumn   = Integer.parseInt(matcher.group(4));
            var endRow      = Integer.parseInt(matcher.group(5));

            var line = new Line2D(new Point2D(startColumn, startRow), new Point2D(endColumn, endRow));
            return new Instruction<>(line, stateAction);
        }
    }

    @Override
    public Integer solveFirstPart() {
        var grid = new Grid2D<Light>(1000, 1000, SimpleLight::new);

        loadInput()
                .forEach(line -> {
                    var instruction = InstructionBuilder.buildFromString(line);
                    for (var row = instruction.subject().getStart().row(); row <= instruction.subject().getEnd().row(); row++) {
                        for (var col = instruction.subject().getStart().col(); col <= instruction.subject().getEnd().col(); col++) {
                            grid.get(col, row).setState(instruction.verb());
                        }
                    }
                });

        var activeLights = new AtomicInteger(0);
        grid.walkGrid((row, col, value, surroundingPoints) -> {
            if(value.isOn()) {
                activeLights.incrementAndGet();
            }
        });
        return activeLights.get();
    }

    @Override
    public Integer solveSecondPart() {
        var grid = new Grid2D<DimmableLight>(1000, 1000, DimmerLight::new);

        loadInput()
                .forEach(line -> {
                    var instruction = InstructionBuilder.buildFromString(line);
                    for (var row = instruction.subject().getStart().row(); row <= instruction.subject().getEnd().row(); row++) {
                        for (var col = instruction.subject().getStart().col(); col <= instruction.subject().getEnd().col(); col++) {
                            grid.get(col, row).setState(instruction.verb());
                        }
                    }
                });

        var totalBrightness = new AtomicInteger(0);
        grid.walkGrid((row, col, value, surroundingPoints) -> totalBrightness.addAndGet(value.getBrightness()));
        return totalBrightness.get();
    }
}