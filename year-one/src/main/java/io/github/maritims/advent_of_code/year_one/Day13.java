package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.graph.AStar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day13 extends PuzzleSolver<Integer, Integer> {
    static class SeatingState implements AStar.State {
        /**
         * People seated so far.
         */
        private final List<Integer> seating;
        /**
         * Which people are already seated.
         */
        private final boolean[]     used;
        /**
         * Happiness matrix.
         */
        private final int[][]       happiness;

        SeatingState(List<Integer> seating, boolean[] used, int[][] happiness) {
            this.seating   = seating;
            this.used      = used;
            this.happiness = happiness;
        }

        /**
         * Generate all possible next seatings by adding one remaining person.
         */
        @Override
        public List<AStar.State> getSuccessors() {
            var successors = new ArrayList<AStar.State>();
            var n          = happiness.length;

            for (var i = 0; i < n; i++) {
                // Ignore persons already seated.
                if (used[i]) {
                    continue;
                }

                var newSeating = new ArrayList<>(seating);
                newSeating.add(i); // Seat them next.
                var newUsed = Arrays.copyOf(used, n);
                newUsed[i] = true;
                successors.add(new SeatingState(newSeating, newUsed, happiness));
            }

            return successors;
        }

        /**
         * Cost so far.
         * @return Negative partial mood (we minimize cost9.
         */
        private int g() {
            var sum = 0;
            for (var i = 0; i < seating.size() - 1; i++) {
                var a = seating.get(i);
                var b = seating.get(i + 1);
                sum += happiness[a][b] + happiness[b][a];
            }
            return sum;
        }

        private int h() {
            var estimate = 0;
            var n = happiness.length;
            var remaining = IntStream.range(0, n).filter(i -> !used[i]).boxed().collect(Collectors.toCollection(ArrayList::new));

            // assume each remaining person contributes their best possible mood with another remaining person
            for (int person : remaining) {
                int maxMood = Integer.MIN_VALUE;
                for (int neighbor : remaining) {
                    if (neighbor != person) {
                        maxMood = Math.max(maxMood, happiness[person][neighbor] + happiness[neighbor][person]);
                    }
                }
                estimate -= maxMood; // negative because A* minimizes
            }

            return estimate;
        }

        private int f() {
            return g() + h();
        }

        @Override
        public int getCost() {
            return -f();
        }

        @Override
        public boolean isGoal() {
            return seating.size() == happiness.length;
        }

        // Compute total mood including circular wrap-around
        int totalMood() {
            int sum = 0;
            for (int i = 0; i < seating.size() - 1; i++) {
                int a = seating.get(i);
                int b = seating.get(i + 1);
                sum += happiness[a][b] + happiness[b][a];
            }
            if (seating.size() == happiness.length && seating.size() > 1) {
                int last = seating.get(seating.size() - 1);
                int first = seating.get(0);
                sum += happiness[last][first] + happiness[first][last];
            }
            return sum;
        }
    }

    static class SeatingHeuristic implements AStar.Heuristic {
        private final int[][] happiness;

        SeatingHeuristic(int[][] happiness) {
            this.happiness = happiness;
        }

        @Override
        public int estimate(AStar.State state) {
            var s        = (SeatingState) state;
            var estimate = 0;

            var remaining = IntStream.range(0, happiness.length)
                    .filter(i -> !s.used[i])
                    .boxed()
                    .collect(Collectors.toCollection(ArrayList::new));

            for (var person : remaining) {
                var maxNeighbour = Integer.MIN_VALUE;
                for (var neighbour : remaining) {
                    if (!Objects.equals(neighbour, person)) {
                        maxNeighbour = Math.max(maxNeighbour, happiness[person][neighbour] + happiness[neighbour][person]);
                    }
                }
                estimate -= maxNeighbour;
            }

            return estimate;
        }
    }

    int findChangeInHappiness(int[][] happiness) {
        var start     = new SeatingState(new ArrayList<>(), new boolean[happiness.length], happiness);
        var heuristic = new SeatingHeuristic(happiness);
        var optimal   = new AStar<>().search(start, heuristic, () -> 0);
        return optimal.getCost();
    }

    @Override
    public Integer solveFirstPart() {
        return 0;
    }

    @Override
    public Integer solveSecondPart() {
        return 0;
    }
}
