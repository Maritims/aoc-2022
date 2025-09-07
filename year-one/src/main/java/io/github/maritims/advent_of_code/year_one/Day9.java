package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.graph.*;
import io.github.maritims.advent_of_code.common.tuples.Tuple3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static io.github.maritims.advent_of_code.common.graph.TravellingSalesman.Strategy.HighestCost;
import static io.github.maritims.advent_of_code.common.graph.TravellingSalesman.Strategy.LowestCost;

public class Day9 extends PuzzleSolver<Integer, Integer> {

    static class InstructionResolver implements Graph.Resolver<String> {
        private static final Pattern      INSTRUCTION_PATTERN = Pattern.compile("^([A-Za-z]+) to ([A-Za-z]+) = (\\d+)$");
        private final        List<String> cities              = new ArrayList<>();

        private int getCityId(String city) {
            var cityId = cities.indexOf(city);
            if (cityId == -1) {
                cities.add(city);
                cityId = cities.size() - 1;
            }
            return cityId;
        }

        @Override
        public Tuple3<Vertex, Vertex, Integer> resolve(String input) {
            var matcher = INSTRUCTION_PATTERN.matcher(input);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid input: " + input);
            }

            var from     = matcher.group(1);
            var to       = matcher.group(2);
            var distance = Integer.parseInt(matcher.group(3));
            var fromId   = getCityId(from);
            var toId     = getCityId(to);

            return new Tuple3<>(new Vertex(fromId), new Vertex(toId), distance);
        }
    }

    @Override
    public Integer solveFirstPart() {
        var distanceMatrix = Graph.<String>newBuilder()
                .resolver(new InstructionResolver())
                .addVertices(loadInput())
                .build()
                .getDistanceMatrix();
        return new TravellingSalesman(distanceMatrix, LowestCost).compute();
    }

    @Override
    public Integer solveSecondPart() {
        var distanceMatrix = Graph.<String>newBuilder()
                .resolver(new InstructionResolver())
                .addVertices(loadInput())
                .build()
                .getDistanceMatrix();
        return new TravellingSalesman(distanceMatrix, HighestCost).compute();
    }
}
