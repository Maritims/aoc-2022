package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.geometry.Direction;
import io.github.maritims.advent_of_code.common.geometry.Point2D;

import java.util.HashMap;
import java.util.Map;

public class Santa {
    private int     currentFloor = 0;
    private Integer positionOfInstructionLeadingToTheBasement;

    public int currentFloor() {
        return currentFloor;
    }

    public int positionOfInstructionLeadingToTheBasement() {
        if (positionOfInstructionLeadingToTheBasement == null) {
            throw new IllegalStateException("Santa has not reached the basement yet.");
        }

        return positionOfInstructionLeadingToTheBasement;
    }

    private void goUpstairs() {
        currentFloor++;
    }

    private void goDownstairs() {
        currentFloor--;
    }

    private void followDirection(Direction direction) {
        if (direction == Direction.UP) {
            goUpstairs();
        } else {
            goDownstairs();
        }
    }

    /**
     * Make Santa walk until he runs out of instructions.
     */
    public Santa walk(char[] instructions) {
        for (var i = 0; i < instructions.length; i++) {
            char instruction = instructions[i];
            if (currentFloor == -1 && positionOfInstructionLeadingToTheBasement == null) {
                positionOfInstructionLeadingToTheBasement = i;
            }
            followDirection(Direction.fromInstruction(instruction));
        }
        return this;
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