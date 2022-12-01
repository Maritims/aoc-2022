package io.github.maritims.aoc2022.puzzles;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DailyPuzzle {
    protected final List<String> getFileContent(String fileName) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        return is == null ? Collections.emptyList() : new BufferedReader(new InputStreamReader(is))
                .lines()
                .collect(Collectors.toList());
    }

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
}
