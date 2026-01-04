package io.github.maritims.advent_of_code.year_one;

public record Reindeer(String name, int restTimeInSeconds, int velocityInKilometersPerSecond, int staminaInSeconds) {
    public static Reindeer parseReindeer(String input) {
        var parts                         = input.split(" ");
        var name                          = parts[0];
        var restTimeInSeconds             = Integer.parseInt(parts[parts.length - 2]);
        var velocityInKilometersPerSecond = Integer.parseInt(parts[3]);
        var staminaInSeconds              = Integer.parseInt(parts[6]);

        return new Reindeer(name, restTimeInSeconds, velocityInKilometersPerSecond, staminaInSeconds);
    }
}
