package io.github.maritims.advent_of_code.year_eleven;

public record Battery(int joltage, boolean poweredOn) {
    public Battery powerOn() {
        return new Battery(joltage, true);
    }

    public Battery powerOff() {
        return new Battery(joltage, false);
    }
}
