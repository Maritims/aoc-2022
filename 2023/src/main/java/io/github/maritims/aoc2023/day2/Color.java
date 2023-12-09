package io.github.maritims.aoc2023.day2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Color {
    Red("red"),
    Green("green"),
    Blue("blue");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    private static final List<Color> ALL_VALUES = Arrays.stream(Color.values()).collect(Collectors.toList());

    public static Optional<Color> fromString(String color) {
        return ALL_VALUES.stream()
            .filter(foo -> color.equalsIgnoreCase(foo.color))
            .findFirst();
    }
}
