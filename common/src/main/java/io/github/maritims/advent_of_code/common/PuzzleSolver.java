package io.github.maritims.advent_of_code.common;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class PuzzleSolver<T1, T2> {
    private static final Pattern CLASS_NAME_PATTERN = Pattern.compile("^Day(\\d+)$");

    private final int day;
    protected ArrayList<String> input;

    public PuzzleSolver() {
        var matcher = CLASS_NAME_PATTERN.matcher(getClass().getSimpleName());
        if (!matcher.matches()) {
            throw new IllegalStateException("Class name must be in format 'Day<number>', got: " + getClass().getSimpleName() + " instead");
        }

        this.day = Integer.parseInt(matcher.group(1));
    }

    public ArrayList<String> loadInput() {
        if (input == null) {
            try (var file = getClass().getClassLoader().getResourceAsStream(day + ".txt")) {
                if (file == null) {
                    throw new FileNotFoundException("You've forgotten to add puzzle input for day " + day + ". Please add it to resources/<number>.txt, replacing <number> with the actual day number.");
                }

                try (var bufferedReader = new BufferedReader(new InputStreamReader(file))) {
                    input = new ArrayList<>(bufferedReader.lines().toList());
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return input;
    }

    protected InputStream loadInputStream() throws FileNotFoundException {
        var inputStream = getClass().getClassLoader().getResourceAsStream(day + ".txt");
        if (inputStream == null) {
            throw new FileNotFoundException("You've forgotten to add puzzle input for day " + day);
        }
        return inputStream;
    }

    public abstract T1 solveFirstPart();

    public abstract T2 solveSecondPart();
}
