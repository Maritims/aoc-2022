package io.github.maritims.aoc2022;

import java.util.LinkedList;
import java.util.List;

public class SixthPuzzle extends Puzzle<Integer> {
    @Override
    public Integer solvePartOne(String filePath) {
        String dataStreamBuffer = getFileContent(filePath).get(0);
        List<Character> lastFour = new LinkedList<>();
        for(int i = 0; i < dataStreamBuffer.length(); i++) {
            lastFour.add(dataStreamBuffer.charAt(i));
            if(lastFour.stream().distinct().count() == 4) {
                return i + 1;
            }
            if(lastFour.size() == 4) {
                lastFour.remove(0);
            }
        }
        return null;
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}
