package io.github.maritims.aoc2015.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1 {
    protected Result convertInstructionsToFloor(String instructions) {
        int currentFloor = 0;
        Integer firstEnteredBasementAtPosition = null;

        for(int i = 0; i < instructions.length(); i++) {
            char instruction = instructions.charAt(i);
            if(instruction == '(') {
                currentFloor++;
            } else if(instruction == ')') {
                currentFloor--;
                if(firstEnteredBasementAtPosition == null && currentFloor == -1) {
                    // The first character in the instructions has position 1, the second character has position 2, and so on.
                    firstEnteredBasementAtPosition = i + 1;
                }
            }
        }

        return new Result(firstEnteredBasementAtPosition, currentFloor);
    }

    public int solvePartOne(String fileName) throws IOException {
        String instructions = Files.readString(Paths.get("src", "main", "resources", fileName));
        return convertInstructionsToFloor(instructions).getFinalFloor();
    }

    public int solvePartTwo(String fileName) throws IOException {
        String instructions = Files.readString(Paths.get("src", "main", "resources", fileName));
        return convertInstructionsToFloor(instructions).getEnteredBasementAtPosition();
    }
}
