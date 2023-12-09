package io.github.maritims.aoc2022;

import java.util.LinkedList;
import java.util.List;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day6 extends Puzzle<Integer, Integer> {
    private static Integer getMarker(String dataStreamBuffer, int markerSize) {
        List<Character> potentialMarker = new LinkedList<>();
        for(int i = 0; i < dataStreamBuffer.length(); i++) {
            potentialMarker.add(dataStreamBuffer.charAt(i));
            if(potentialMarker.stream().distinct().count() == markerSize) {
                return i + 1;
            }
            if(potentialMarker.size() == markerSize) {
                potentialMarker.remove(0);
            }
        }
        return null;
    }

    @Override
    public Integer solvePartOne(String filePath) {
        return getMarker(getFileContent(filePath).get(0), 4);
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return getMarker(getFileContent(filePath).get(0), 14);
    }
}
