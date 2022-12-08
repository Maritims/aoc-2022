package io.github.maritims.aoc2022;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EightPuzzle extends Puzzle<Integer> {
    public static <T> List<List<T>> getTransposed(List<List<T>> table) {
        List<List<T>> transposedTable = new LinkedList<>();
        int tableWidth = table.get(0).size();
        for(int i = 0; i < tableWidth; i++) {
            List<T> col = new LinkedList<>();
            for(List<T> row : table) {
                col.add(row.get(i));
            }
            transposedTable.add(col);
        }
        return transposedTable;
    }

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

    public boolean[] getVisible(boolean[] gridLine, String treeLine, boolean reverse) {
        int maxHeight = -1;
        for(
                int i = (reverse ? treeLine.length() - 1 : 0);
                reverse ? i > 0 : i < treeLine.length();
                i += (reverse ? -1 : 1)
        ) {
            int height = Character.getNumericValue(treeLine.charAt(i));
            if(height > maxHeight) {
                maxHeight = height;
                gridLine[i] = true;
            }
        }
        return gridLine;
    }

    public boolean[] getVisibleFromEitherSide(boolean[] gridLine, String treeLine) {
        gridLine = getVisible(gridLine, treeLine, false);
        gridLine = getVisible(gridLine, treeLine, true);
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

    public int[][] getGrid(List<String> lines) {
        int[][] grid = new int[lines.size()][lines.get(0).length()];
        for(int row = 0; row < lines.size(); row++) {
            for(int col = 0; col < lines.get(row).length(); col++) {
                grid[row][col] = Character.getNumericValue(lines.get(row).charAt(col));
            }
        }
        return grid;
    }

    public int getUpwardsFacingScenicScore(int[][] grid, int row, int col) {
        if(row == 0) {
            return 0;
        }

        int scenicScore = 0;
        for(int i = row - 1; i >= 0; i--) {
            scenicScore += 1;
            if(grid[i][col] >= grid[row][col]) {
                break;
            }
        }
        return scenicScore;
    }

    public int getDownwardsFacingScenicScore(int[][] grid, int row, int col) {
        if(row == grid.length - 1) {
            return 0;
        }

        int scenicScore = 0;
        for(int i = row + 1; i < grid.length; i++) {
            scenicScore += 1;
            if(grid[i][col] >= grid[row][col]) {
                break;
            }
        }
        return scenicScore;
    }

    public int getLeftFacingScenicScore(int[][] grid, int row, int col) {
        if(col == 0) {
            return 0;
        }

        int scenicScore = 0;
        for(int i = col - 1; i >= 0; i--) {
            scenicScore += 1;
            if(grid[row][i] >= grid[row][col]) {
                break;
            }
        }
        return scenicScore;
    }

    public int getRightFacingScenicScore(int[][] grid, int row, int col) {
        if(col == grid[0].length - 1) {
            return 0;
        }

        int scenicScore = 0;
        for(int i = col + 1; i < grid[0].length; i++) {
            scenicScore += 1;
            if(grid[row][i] >= grid[row][col]) {
                break;
            }
        }
        return scenicScore;
    }

    public int getScenicScore(int[][] grid, int row, int col) {
        int up = getUpwardsFacingScenicScore(grid, row, col);
        int down = getDownwardsFacingScenicScore(grid, row, col);
        int left = getLeftFacingScenicScore(grid, row, col);
        int right = getRightFacingScenicScore(grid, row, col);
        return up * down * left * right;
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        List<String> lines = getFileContent(filePath);
        int topScenicScore = 0;
        int[][] grid = getGrid(lines);
        for(int i = 0; i < lines.size(); i++) {
            for(int j = 0; j < lines.get(i).length(); j++) {
                int scenicScore = getScenicScore(grid, i, j);
                if(scenicScore > topScenicScore) {
                    topScenicScore = scenicScore;
                }
            }
        }
        return topScenicScore;
    }
}
