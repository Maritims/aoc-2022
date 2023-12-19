package io.github.maritims.aoc2023.day8;

public class Ghost {
    private final String startingPosition;
    private       int    requiredCycles = 0;

    public Ghost(String startingPosition) {
        this.startingPosition = startingPosition;
    }

    public String getStartingPosition() {
        return startingPosition;
    }

    public int getRequiredCycles() {
        return requiredCycles;
    }

    public void setRequiredCycles(int requiredCycles) {
        this.requiredCycles = requiredCycles;
    }

    @Override
    public String toString() {
        return "Ghost{" +
            "startingPosition='" + startingPosition + '\'' +
            ", requiredCycles=" + requiredCycles +
            '}';
    }
}