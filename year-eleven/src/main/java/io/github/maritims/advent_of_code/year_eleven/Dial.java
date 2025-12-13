package io.github.maritims.advent_of_code.year_eleven;

import java.util.regex.Pattern;

public record Dial(int position, int zeroHits, int zeroPositions) {
    private static final int     MIN                 = 0;
    private static final int     MAX                 = 99;
    private static final int     SIZE                = MAX + 1;
    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("^([LR])(\\d+)$");

    public Dial {
        if (position < MIN || position > MAX) {
            throw new IllegalArgumentException("Position must be between " + MIN + " and " + MAX);
        }
    }

    public Dial(int position) {
        this(position, 0, 0);
    }

    public static int countZeroHits(int position, int steps, char direction) {
        if (position < MIN || position > MAX) {
            throw new IllegalArgumentException("Position must be between " + MIN + " and " + MAX);
        }
        if (steps <= 0) {
            throw new IllegalArgumentException("Steps must be greater than zero");
        }

        int distanceToFirstZeroHit;

        if (direction == 'R') {
            // Moving clockwise. The first zero is encountered once we reach SIZE + 1, therefore, we just subtract position from SIZE to determine the distance.
            distanceToFirstZeroHit = SIZE - position;
        } else if (direction == 'L') {
            // Moving counter-clockwise. The first zero is encountered once we reach 0. Therefore, the distance is just the position.
            distanceToFirstZeroHit = position == 0 ? SIZE : position;
        } else {
            throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        var totalSteps = steps < distanceToFirstZeroHit ? 0 : 1;
        if (totalSteps == 0) {
            // We're not moving far enough to hit the first zero.
            return 0;
        }

        // We're moving far enough to hit the first zero. Get the remaining steps.
        var remainingSteps = steps - distanceToFirstZeroHit;
        totalSteps += (remainingSteps / SIZE);
        return totalSteps;
    }

    public Dial turnClockwiseOnce() {
        var newPosition = position + 1;
        if (newPosition > MAX) {
            newPosition = MIN;
        }
        return new Dial(newPosition, 0, 0);
    }

    public Dial turnClockwise(int steps) {
        var newPosition = (position + steps) % SIZE;
        return new Dial(newPosition, zeroHits + countZeroHits(position, steps, 'R'), zeroPositions + (newPosition == 0 ? 1 : 0));
    }

    public Dial turnCounterClockwiseOnce() {
        var newPosition = position - 1;
        if (newPosition < MIN) {
            newPosition = MAX;
        }
        return new Dial(newPosition, 0, zeroPositions + (newPosition == 0 ? 1 : 0));
    }

    public Dial turnCounterClockwise(int steps) {
        var rawPosition               = position - steps;
        var potentialNegativePosition = rawPosition % SIZE;
        var correctedPositivePosition = (potentialNegativePosition + SIZE) % SIZE;
        return new Dial(correctedPositivePosition, zeroHits + countZeroHits(position, steps, 'L'), zeroPositions + (correctedPositivePosition == 0 ? 1 : 0));
    }

    public Dial turnByInstruction(String instruction) {
        if (instruction == null || instruction.isBlank()) {
            throw new IllegalArgumentException("Instruction must not be null or blank");
        }

        var matcher = INSTRUCTION_PATTERN.matcher(instruction);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid instruction: " + instruction);
        }

        var direction = matcher.group(1);
        var steps     = Integer.parseInt(matcher.group(2));

        return switch (direction) {
            case "L" -> turnCounterClockwise(steps);
            case "R" -> turnClockwise(steps);
            default -> throw new IllegalArgumentException("Invalid instruction: " + instruction);
        };
    }
}
