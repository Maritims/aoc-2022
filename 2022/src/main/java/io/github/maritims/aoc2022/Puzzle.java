package io.github.maritims.aoc2022;

import io.github.maritims.lib.GridRenderer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class Puzzle<TPartOneOutput, TPartTwoOutput> {
    protected final List<List<String>> splitListToLists(List<String> input) {
        List<List<String>> listOfLists = new ArrayList<>();
        int previous = 0;

        for(int current = 0; current < input.size(); current++) {
            if("".equals(input.get(current))) {
                listOfLists.add(input.subList(previous, current));
                previous = current + 1;
            }
        }

        // Get the last part of the original list.
        listOfLists.add(input.subList(previous, input.size()));
        return listOfLists;
    }

    public abstract TPartOneOutput solvePartOne(String filePath) throws IOException;

    public abstract TPartTwoOutput solvePartTwo(String filePath) throws IOException;

    protected boolean isLogEnabled = false;

    protected final void log(String message) {
        if(isLogEnabled) {
            System.out.println(message);
        }
    }

    protected final void log(String message, boolean enableLogging) {
        isLogEnabled = enableLogging;
        log(message);
    }

    protected final void render(Character[][] grid) throws IOException {
        StringWriter sw = new StringWriter();
        new GridRenderer<Character>().render(grid, sw, (element, writer) -> {
            try {
                writer.write(element);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        log(sw.toString(), true);
    }
}
