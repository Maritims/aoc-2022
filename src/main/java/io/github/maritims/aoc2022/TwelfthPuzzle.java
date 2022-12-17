package io.github.maritims.aoc2022;

import java.util.*;

public class TwelfthPuzzle extends Puzzle<Integer, Integer> {
    static class HeightMap {
        private final char[][] grid;
        private final Point startingPoint;
        private final Point stoppingPoint;
        private final Point[] potentialStartingPoints;

        private HeightMap(char[][] grid, Point startingPoint, Point stoppingPoint, Point[] potentialStartingPoints) {
            this.grid = grid;
            this.startingPoint = startingPoint;
            this.stoppingPoint = stoppingPoint;
            this.potentialStartingPoints = potentialStartingPoints;
        }

        public int getWidth() {
            return grid[0].length;
        }

        public int getHeight() {
            return grid.length;
        }

        public Point getStartingPoint() {
            return startingPoint;
        }

        public Point getStoppingPoint() {
            return stoppingPoint;
        }

        public char getNodeAt(Point p) {
            return grid[p.y][p.x];
        }

        public static Optional<HeightMap> build(List<String> lines) {
            char[][] grid = new char[lines.size()][lines.get(0).length()];
            Point startingPoint = null;
            Point stoppingPoint = null;
            List<Point> possibleStartingPoints = new ArrayList<>();

            for(int row = 0; row < lines.size(); row++) {
                String line = lines.get(row);
                for(int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    if(c == 'S') {
                        c = 'a';
                        startingPoint = new Point(col, row);
                    } else if(c == 'E') {
                        c = 'z';
                        stoppingPoint = new Point(col, row);
                    }
                    if(c == 'a') {
                        possibleStartingPoints.add(new Point(col, row));
                    }
                    grid[row][col] = c;
                }
            }

            return Optional.of(new HeightMap(grid, startingPoint, stoppingPoint, possibleStartingPoints.toArray(new Point[0])));
        }

        public Point[] getPotentialStartingPoints() {
            return potentialStartingPoints;
        }
    }

    static class Point {
        private final int x;
        private final int y;
        private final int distance;

        Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        Point(int x, int y) {
            this(x, y, 0);
        }

        public int getDistance() {
            return distance;
        }

        public boolean isSameLocationAs(Point that) {
            return this.x == that.x && this.y == that.y;
        }

        public Point toTheLeft(int steps) {
            return new Point(x - steps, y);
        }

        public Point toTheRight(int steps) {
            return new Point(x + steps, y);
        }

        public Point above(int steps) {
            return new Point(x, y - steps);
        }

        public Point below(int steps) {
            return new Point(x, y + steps);
        }

        public Point withDistance(int distance) {
            return new Point(x, y, distance);
        }
    }

    static class BreadthFirstSearch {
        private static OptionalInt getShortestPath(HeightMap heightMap, Point startingPoint) {
            boolean[][] visited = new boolean[heightMap.getHeight()][heightMap.getWidth()];

            LinkedList<Point> queue = new LinkedList<>();
            queue.add(startingPoint);

            while (!queue.isEmpty()) {
                Point point = queue.poll();

                // Have we been here already?
                if(visited[point.y][point.x]) {
                    continue;
                }

                // Are we done?
                if(point.isSameLocationAs(heightMap.getStoppingPoint())) {
                    return OptionalInt.of(point.getDistance());
                }

                // We've been here now.
                visited[point.y][point.x] = true;

                // Can we go left?
                if(point.x > 0 && heightMap.getNodeAt(point.toTheLeft(1)) <= heightMap.getNodeAt(point) + 1) {
                    queue.add(point.toTheLeft(1).withDistance(point.getDistance() + 1));
                }

                // Can we go right?
                if(point.x < heightMap.getWidth() - 1 && heightMap.getNodeAt(point.toTheRight(1)) <= heightMap.getNodeAt(point) + 1) {
                    queue.add(point.toTheRight(1).withDistance(point.getDistance() + 1));
                }

                // Can we go up?
                if(point.y > 0 && heightMap.getNodeAt(point.above(1)) <= heightMap.getNodeAt(point) + 1) {
                    queue.add(point.above(1).withDistance(point.getDistance() + 1));
                }

                // Can we go down?
                if(point.y < heightMap.getHeight() - 1 && heightMap.getNodeAt(point.below(1)) <= heightMap.getNodeAt(point) + 1) {
                    queue.add(point.below(1).withDistance(point.getDistance() + 1));
                }
            }

            return OptionalInt.empty();
        }

        public static OptionalInt getShortestPath(HeightMap heightMap, boolean useBestPotentialStartingPoint) {
            return useBestPotentialStartingPoint ? Arrays.stream(heightMap.getPotentialStartingPoints())
                    .map(startingPoint -> getShortestPath(heightMap, startingPoint))
                    .filter(OptionalInt::isPresent)
                    .mapToInt(OptionalInt::getAsInt)
                    .min() : getShortestPath(heightMap, heightMap.getStartingPoint());
        }
    }

    @Override
    public Integer solvePartOne(String filePath) {
        return HeightMap.build(getFileContent(filePath))
                .map(heightMap -> BreadthFirstSearch.getShortestPath(heightMap, false))
                .filter(OptionalInt::isPresent)
                .map(OptionalInt::getAsInt)
                .orElse(null);
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return HeightMap.build(getFileContent(filePath))
                .map(heightMap -> BreadthFirstSearch.getShortestPath(heightMap, true))
                .filter(OptionalInt::isPresent)
                .map(OptionalInt::getAsInt)
                .orElse(null);
    }
}