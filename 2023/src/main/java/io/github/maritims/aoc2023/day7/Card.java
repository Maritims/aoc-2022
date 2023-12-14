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
        return 100 - value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return value == card.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
