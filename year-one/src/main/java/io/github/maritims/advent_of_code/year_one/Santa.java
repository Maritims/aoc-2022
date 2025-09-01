package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.geometry.Direction;
import io.github.maritims.advent_of_code.common.geometry.Point2D;

import java.util.HashMap;
import java.util.Map;

/**
 * A Santa which can both walk up and down in buildings and deliver presents up, down, left and right.
 * This Santa can clone itself to deliver presents faster.
 */
public class Santa {
    public record WalkResult(int currentFloor, Integer positionOfInstructionLeadingToTheBasement) {
    }

    /**
     * Make Santa walk until he runs out of instructions.
     */
    public WalkResult walk(char[] instructions) {
        var     floor                                     = 0;
        Integer positionOfInstructionLeadingToTheBasement = null;

        for (var i = 0; i < instructions.length; i++) {
            char instruction = instructions[i];
            if (floor == -1 && positionOfInstructionLeadingToTheBasement == null) {
                positionOfInstructionLeadingToTheBasement = i;
            }

            floor = switch (Direction.fromInstruction(instruction)) {
                case UP -> floor + 1;
                case DOWN -> floor - 1;
                default -> throw new IllegalArgumentException("Invalid instruction: " + instruction);
            };
        }

        return new WalkResult(floor, positionOfInstructionLeadingToTheBasement);
    }

    /**
     * Make Santa deliver presents until he runs out of instructions. Supports Santa cloning himself.
     *
     * @param instructions   The instructions telling all the Santas where to deliver presents.
     * @param numberOfSantas The number of Santas delivering presents at the same time.
     * @return The houses visited by all the Santas.
     */
    public Map<Point2D, Integer> deliverPresents(char[] instructions, int numberOfSantas) {
        var visitedHouses = new HashMap<>(Map.of(new Point2D(0, 0), 1));
        var santas        = new Point2D[numberOfSantas];
        for (var i = 0; i < numberOfSantas; i++) {
            santas[i] = new Point2D(0, 0);
            visitedHouses.put(santas[i], 1);
        }

        for (var i = 0; i < instructions.length; i++) {
            var instruction = instructions[i];
            var direction   = Direction.fromInstruction(instruction);
            var j           = numberOfSantas > 1 && (i % 2) == 0 ? 1 : 0;
            santas[j] = santas[j].relativeTo(direction, 1);
            visitedHouses.put(santas[j], visitedHouses.getOrDefault(santas[j], 0) + 1);
        }

        return visitedHouses;
    }
}