package io.github.maritims.advent_of_code.common;

public enum StateAction {
    Enable,
    Disable,
    Toggle;

    public static StateAction fromString(String value) {
        return switch (value.toLowerCase()) {
            case "enable", "on" -> Enable;
            case "disable", "off" -> Disable;
            case "toggle" -> Toggle;
            default -> throw new IllegalArgumentException("Invalid value: " + value);
        };
    }
}
