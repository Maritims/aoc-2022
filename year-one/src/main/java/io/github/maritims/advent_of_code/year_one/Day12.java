package io.github.maritims.advent_of_code.year_one;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.maritims.advent_of_code.common.PuzzleSolver;

import java.io.IOException;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

public class Day12 extends PuzzleSolver<Integer, Integer> {
    int traverse(JsonNode jsonNode, BiPredicate<JsonNode, String> predicate) {
        if (jsonNode == null) {
            return 0;
        }

        return switch (jsonNode.getNodeType()) {
            case ARRAY -> IntStream.range(0, jsonNode.size()).map(i -> traverse(jsonNode.get(i), predicate)).sum();
            case OBJECT -> {
                var fieldNames = jsonNode.fieldNames();
                var sum        = 0;
                while (fieldNames.hasNext()) {
                    var fieldName = fieldNames.next();
                    if (predicate != null && predicate.test(jsonNode, fieldName)) {
                        sum = 0;
                        break;
                    }
                    sum += traverse(jsonNode.get(fieldName), predicate);
                }
                yield sum;
            }
            case NUMBER -> jsonNode.asInt();
            default -> 0;
        };
    }

    @Override
    public Integer solveFirstPart() {
        var objectMapper = new ObjectMapper();
        try {
            var tree = objectMapper.readTree(loadInputStream());
            return traverse(tree, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer solveSecondPart() {
        var objectMapper = new ObjectMapper();
        try {
            var tree = objectMapper.readTree(loadInputStream());
            return traverse(tree, (jsonNode, fieldName) -> "red".equalsIgnoreCase(fieldName) || (jsonNode.get(fieldName).isTextual() && "red".equalsIgnoreCase(jsonNode.get(fieldName).asText())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
