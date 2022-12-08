package io.github.maritims.aoc2022;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EightPuzzle extends Puzzle<Integer> {
    public String getRotatedColumn(List<String> lines, int column) {
        return lines.stream()
                .map(line -> line.charAt(column))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public boolean[] getRotatedColumn(boolean[][] lines, int column) {
        List<Boolean> list = Arrays.stream(lines)
                .map(line -> line[column])
                .collect(Collectors.toCollection(LinkedList::new));
        boolean[] normalizedColumn = new boolean[list.size()];
        IntStream.range(0, list.size()).forEach(i -> normalizedColumn[i] = list.get(i));
        return normalizedColumn;
    }

    public boolean[] getVisibleFromTheStart(boolean[] gridLine, String treeLine) {
        int maxHeight = -1;
        for(int i = 0; i < treeLine.length(); i++) {
            int height = Character.getNumericValue(treeLine.charAt(i));
            if(height > maxHeight) {
                maxHeight = height;
                gridLine[i] = true;
            }
        }
        return gridLine;
    }

    public boolean[] getVisibleFromTheEnd(boolean[] gridLine, String treeLine) {
        int maxHeight = -1;
        for(int i = treeLine.length() - 1; i > 0; i--) {
            int height = Character.getNumericValue(treeLine.charAt(i));
            if(height > maxHeight) {
                maxHeight = height;
                gridLine[i] = true;
            }
        }
        return gridLine;
    }

    public boolean[] getVisibleFromEitherSide(boolean[] gridLine, String treeLine) {
        gridLine = getVisibleFromTheStart(gridLine, treeLine);
        gridLine = getVisibleFromTheEnd(gridLine, treeLine);
        return gridLine;
    }

    @Override
    public Integer solvePartOne(String filePath) {
        List<String> lines = getFileContent(filePath);
        int columns = lines.get(0).length();
        boolean[][] grid = new boolean[lines.size()][columns];

        // Check the sides.
        for(int row = 0; row < lines.size(); row++) {
            grid[row] = getVisibleFromEitherSide(new boolean[lines.get(row).length()], lines.get(row));
        }

        // Check above and below.
        for(int col = 0; col < columns; col++) {
            boolean[] rotatedColumn = getVisibleFromEitherSide(
                    getRotatedColumn(grid, col),
                    getRotatedColumn(lines, col)
            );
            for(int row = 0; row < grid.length; row++) {
                grid[row][col] = rotatedColumn[row];
            }
        }

        int visible = 0;
        for (boolean[] line : grid) {
            for (boolean tree : line) {
                visible += tree ? 1 : 0;
            }
        }
        return visible;
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}
