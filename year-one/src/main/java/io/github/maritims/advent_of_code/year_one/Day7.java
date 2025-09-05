package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.Instruction;
import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.graph.DepthFirstSearch;
import io.github.maritims.advent_of_code.common.graph.NeighbourProvider;
import io.github.maritims.advent_of_code.common.logical.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.github.maritims.advent_of_code.common.util.ArgumentExceptionUtil.throwIfNull;

public class Day7 extends PuzzleSolver<Integer, Integer> {
    static class Wires implements NeighbourProvider<String> {
        private final Map<String, Instruction<String[], Operation>> instructions;

        Wires(Map<String, Instruction<String[], Operation>> instructions) {
            this.instructions = instructions;
        }

        @Override
        public Iterable<String> getNeighbours(String wire) {
            return Optional.ofNullable(instructions.get(wire))
                    .map(Instruction::subject)
                    .map(List::of)
                    .orElse(Collections.emptyList());
        }
    }

    static class Circuit implements Function<String, Integer> {
        private final Map<String, Instruction<String[], Operation>> instructions;
        private final DepthFirstSearch<String, Integer>             dfs;

        Circuit(Map<String, Instruction<String[], Operation>> instructions, DepthFirstSearch<String, Integer> dfs) {
            this.instructions = throwIfNull(instructions, "instructions");
            this.dfs          = throwIfNull(dfs, "dfs");
        }

        @Override
        public Integer apply(String wire) {
            return Optional.ofNullable(instructions.get(wire))
                    .map(instruction -> {
                        if (instruction.verb() instanceof UnaryOperation uo) {
                            var expression = dfs.evaluate(instruction.subject()[0], this);
                            return uo.evaluate(expression);
                        } else if (instruction.verb() instanceof BinaryOperation bo) {
                            var left  = dfs.evaluate(instruction.subject()[0], this);
                            var right = dfs.evaluate(instruction.subject()[1], this);
                            return bo.evaluate(left, right);
                        } else {
                            throw new IllegalStateException("What the heck is this!?");
                        }
                    })
                    .orElseGet(() -> Integer.parseInt(wire));
        }
    }

    static Map<String, Instruction<String[], Operation>> fromLines(List<String> lines) {
        return lines.stream()
                .map(line -> {
                    var tokens            = line.split(" -> ");
                    var instructionTokens = tokens[0].split(" ");

                    Instruction<String[], Operation> instruction = switch (instructionTokens.length) {
                        case 1 -> new Instruction<>(new String[]{instructionTokens[0]}, new Assign());
                        case 2 -> new Instruction<>(new String[]{instructionTokens[1]}, new Not());
                        case 3 -> switch (instructionTokens[1]) {
                            case "AND" -> new Instruction<>(new String[]{instructionTokens[0], instructionTokens[2]}, new And());
                            case "OR" -> new Instruction<>(new String[]{instructionTokens[0], instructionTokens[2]}, new Or());
                            case "RSHIFT" -> new Instruction<>(new String[]{instructionTokens[0], instructionTokens[2]}, new RShift());
                            case "LSHIFT" -> new Instruction<>(new String[]{instructionTokens[0], instructionTokens[2]}, new LShift());
                            default -> throw new IllegalStateException("Unexpected value: " + instructionTokens[1]);
                        };
                        default -> throw new IllegalStateException("Unexpected value: " + instructionTokens.length);
                    };

                    return Map.entry(tokens[1], instruction);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Integer solveFirstPart() {
        var instructions = fromLines(loadInput());
        var dfs          = new DepthFirstSearch<String, Integer>(new Wires(instructions));
        return dfs.evaluate("a", new Circuit(instructions, dfs));
    }

    @Override
    public Integer solveSecondPart() {
        var instructions = fromLines(loadInput());
        var dfs          = new DepthFirstSearch<String, Integer>(new Wires(instructions));
        var a            = dfs.evaluate("a", new Circuit(instructions, dfs));

        instructions.put("b", new Instruction<>(new String[]{String.valueOf(a)}, new Assign()));
        return dfs.reset()
                .setNeighbourProvider(new Wires(instructions))
                .evaluate("a", new Circuit(instructions, dfs));
    }
}