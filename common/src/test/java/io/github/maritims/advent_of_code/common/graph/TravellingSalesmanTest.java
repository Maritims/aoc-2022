package io.github.maritims.advent_of_code.common.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.maritims.advent_of_code.common.graph.TravellingSalesman.Strategy.HighestCost;
import static io.github.maritims.advent_of_code.common.graph.TravellingSalesman.Strategy.LowestCost;
import static org.junit.jupiter.api.Assertions.*;

class TravellingSalesmanTest {
    public static Stream<Arguments> compute() {
        return Stream.of(
                Arguments.of(LowestCost, 605),
                Arguments.of(HighestCost, 982)
        );
    }

    @ParameterizedTest
    @MethodSource
    void compute(TravellingSalesman.Strategy strategy, int expectedResult) {
        var graph = Graph.<String>newBuilder()
                .addVertices(new Vertex(0), new Vertex(1), 464)
                .addVertices(new Vertex(0), new Vertex(2), 518)
                .addVertices(new Vertex(1), new Vertex(2), 141)
                .build();
        var distanceMatrix = graph.getDistanceMatrix();
        var tsp            = new TravellingSalesman(distanceMatrix, strategy);
        var result         = tsp.compute();
        assertEquals(expectedResult, result);
    }
}