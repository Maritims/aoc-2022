package io.github.maritims.aoc2022.puzzles.first;

public class Elf {
    private final Integer calories;

    public Elf(Integer calories) {
        this.calories = calories;
    }

    public Integer getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return getCalories().toString();
    }
}
