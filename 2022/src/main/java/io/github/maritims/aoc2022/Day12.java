package io.github.maritims.aoc2022;

import io.github.maritims.lib.Point;

import java.util.*;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day12 extends Puzzle<Integer, Integer> {
    static class HeightMap {
        private final char[][] grid;
        private final PointWithDistance startingPoint;
        private final PointWithDistance stoppingPoint;
        private final PointWithDistance[] potentialStartingPoints;

        private HeightMap(char[][] grid, PointWithDistance startingPoint, PointWithDistance stoppingPoint, PointWithDistance[] potentialStartingPoints) {
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

        public PointWithDistance getStartingPoint() {
            return startingPoint;
        }

        public PointWithDistance getStoppingPoint() {
            return stoppingPoint;
        }

        public char getNodeAt(PointWithDistance p) {
            return grid[p.getY()][p.getX()];
        }

        public static Optional<HeightMap> build(List<String> lines) {
            char[][] grid = new char[lines.size()][lines.get(0).length()];
            PointWithDistance startingPoint = null;
            PointWithDistance stoppingPoint = null;
            List<PointWithDistance> possibleStartingPoints = new ArrayList<>();

            for(int row = 0; row < lines.size(); row++) {
                String line = lines.get(row);
                for(int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    if(c == 'S') {
                        c = 'a';
                        startingPoint = new PointWithDistance(col, row);
                    } else if(c == 'E') {
                        c = 'z';
                        stoppingPoint = new PointWithDistance(col, row);
                    }
                    if(c == 'a') {
                        possibleStartingPoints.add(new PointWithDistance(col, row));
                    }
                    grid[row][col] = c;
                }
            }

            return Optional.of(new HeightMap(grid, startingPoint, stoppingPoint, possibleStartingPoints.toArray(new PointWithDistance[0])));
        }

        public PointWithDistance[] getPotentialStartingPoints() {
            return potentialStartingPoints;
        }
    }

    static class PointWithDistance extends Point {
        private final int distance;

        PointWithDistance(int x, int y, int distance) {
            super(x, y);
            this.distance = distance;
        }

        PointWithDistance(int x, int y) {
            this(x, y, 0);
        }

        public int getDistance() {
            return distance;
        }

        public boolean isSameLocationAs(PointWithDistance that) {
            return Objects.equals(getX(), that.getX()) && Objects.equals(getY(), that.getY());
        }

        public PointWithDistance toTheLeft(int steps) {
            return new PointWithDistance(getX() - steps, getY());
        }

        public PointWithDistance toTheRight(int steps) {
            return new PointWithDistance(getX() + steps, getY());
        }

        public PointWithDistance above(int steps) {
            return new PointWithDistance(getX(), getY() - steps);
        }

        public PointWithDistance below(int steps) {
            return new PointWithDistance(getX(), getY() + steps);
        }

        public PointWithDistance withDistance(int distance) {
            return new PointWithDistance(getX(), getY(), distance);
        }
    }

    static class BreadthFirstSearch {
        private static OptionalInt getShortestPath(HeightMap heightMap, PointWithDistance startingPoint) {
            boolean[][] visited = new boolean[heightMap.getHeight()][heightMap.getWidth()];

            LinkedList<PointWithDistance> queue = new LinkedList<>();
            queue.add(startingPoint);

            while (!queue.isEmpty()) {
                PointWithDistance point = queue.poll();

                // Have we been here already?
                if(visited[point.getY()][point.getX()]) {
                    continue;
                }

                // Are we done?
                if(point.isSameLocationAs(heightMap.getStoppingPoint())) {
                    return OptionalInt.of(point.getDistance());
                }

                // We've been here now.
                visited[point.getY()][point.getX()] = true;

                // Can we go left?
                if(point.getX() > 0 && heightMap.getNodeAt(point.toTheLeft(1)) <= heightMap.getNodeAt(point) + 1) {
                    queue.add(point.toTheLeft(1).withDistance(point.getDistance() + 1));
                }

                // Can we go right?
                if(point.getX() < heightMap.getWidth() - 1 && heightMap.getNodeAt(point.toTheRight(1)) <= heightMap.getNodeAt(point) + 1) {
                    queue.add(point.toTheRight(1).withDistance(point.getDistance() + 1));
                }

                // Can we go up?
                if(point.getY() > 0 && heightMap.getNodeAt(point.above(1)) <= heightMap.getNodeAt(point) + 1) {
                    queue.add(point.above(1).withDistance(point.getDistance() + 1));
                }

                // Can we go down?
                if(point.getY() < heightMap.getHeight() - 1 && heightMap.getNodeAt(point.below(1)) <= heightMap.getNodeAt(point) + 1) {
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