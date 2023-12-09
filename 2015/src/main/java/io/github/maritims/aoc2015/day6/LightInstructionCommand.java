package io.github.maritims.aoc2015.day6;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum LightInstructionCommand {
    TurnOn("turn on"),
    TurnOff("turn off"),
    Toggle("toggle");

    private final String value;

    LightInstructionCommand(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static final List<LightInstructionCommand> list = Arrays.stream(values()).toList();

    public static Optional<LightInstructionCommand> fromString(String value) {
        return list.stream()
            .filter(element -> value.equalsIgnoreCase(element.getValue()))
            .findFirst();
    }
}
