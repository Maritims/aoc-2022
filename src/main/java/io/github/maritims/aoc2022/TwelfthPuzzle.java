package io.github.maritims.aoc2022;

import java.awt.*;
import java.util.LinkedList;

public class TwelfthPuzzle extends Puzzle<Integer, Integer> {
    static class BreadthFirstSearch {
        private static boolean isLegalCoordinates(int rows, int cols, Point p) {
            return p.x >= 0 && p.y >= 0 && cols > p.x && rows > p.y;
        }

        private static boolean isValidHeight(char source, char destination) {
            return destination <= source + 1;
        }

        public static int run(char[][] grid) {
            LinkedList<Point> queue = new LinkedList<>();
            int rows = grid.length;
            int cols = grid[0].length;
            boolean[][] visited = new boolean[rows][cols];
            queue.add(new Point(0, 0));
            grid[0][0] = 'a';

            while (!queue.isEmpty()) {
                Point source = queue.poll();
                if(!isLegalCoordinates(rows, cols, source) || visited[source.y][source.x]) {
                    continue;
                }
                visited[source.y][source.x] = true;
                System.out.print(grid[source.y][source.x] + " ");
                if(grid[source.y][source.x] == 'E') {
                    break;
                }
                queueNeighbourIfValid(queue, source, new Point(source.x - 1, source.y), rows, cols, grid); // left
                queueNeighbourIfValid(queue, source, new Point(source.x + 1, source.y), rows, cols, grid); // right
                queueNeighbourIfValid(queue, source, new Point(source.x, source.y - 1), rows, cols, grid); // up
                queueNeighbourIfValid(queue, source, new Point(source.x, source.y + 1), rows, cols, grid); // down
            }

            int steps = 0;
            for (boolean[] line : visited) {
                for (boolean square : line) {
                    if (square) {
                        steps++;
                    }
                }
            }
            return steps;
        }

        private static void queueNeighbourIfValid(LinkedList<Point> queue, Point source, Point destination, int rows, int cols, char[][] grid) {
            if(isLegalCoordinates(rows, cols, destination) && isValidHeight(grid[source.y][source.x], grid[destination.y][destination.x])) {
                queue.add(destination);
            }
        }
    }

    @Override
    public Integer solvePartOne(String filePath) {
        char[][] grid = getFileContent(filePath).stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
        return BreadthFirstSearch.run(grid);
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}