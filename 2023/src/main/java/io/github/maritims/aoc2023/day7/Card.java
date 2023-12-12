package io.github.maritims.aoc2023.day7;

public class Card {
    private final char value;

    public Card(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    /**
     * The worth of a card is based on its ASCII value.
     * The value A with ASCII code 65 is worth most of all values, while the value 2 with ASCII code 50 is worth the least of all values.
     * @return The value cast to an int.
     */
    public Integer getWorth() {
        return (int) value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
