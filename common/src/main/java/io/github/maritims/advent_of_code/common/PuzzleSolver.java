package io.github.maritims.advent_of_code.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

public abstract class PuzzleSolver<T1, T2> {
    private static final Pattern CLASS_NAME_PATTERN = Pattern.compile("^Day(\\d+)$");

    private final int          day;
    protected     List<String> input;

    public PuzzleSolver() {
        var matcher = CLASS_NAME_PATTERN.matcher(getClass().getSimpleName());
        if (!matcher.matches()) {
            throw new IllegalStateException("Class name must be in format 'Day<number>', got: " + getClass().getSimpleName() + " instead");
        }

        this.day = Integer.parseInt(matcher.group(1));
    }

    protected List<String> loadInput() {
        if (input == null) {
            try (var file = getClass().getClassLoader().getResourceAsStream(day + ".txt")) {
                if (file == null) {
                    throw new FileNotFoundException("You've forgotten to add puzzle input for day " + day);
                }

                try (var bufferedReader = new BufferedReader(new InputStreamReader(file))) {
                    input = bufferedReader.lines().toList();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return input;
    }

    public abstract T1 solveFirstPart();

    public abstract T2 solveSecondPart();
}
