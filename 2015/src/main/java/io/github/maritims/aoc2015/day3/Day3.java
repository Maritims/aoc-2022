package io.github.maritims.aoc2015.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3 {
    private final int santas;

    public Day3(int santas) {
        this.santas = santas;
    }

    protected void deliverPresent(Map<Point, Integer> deliveries, Point deliveryPoint) {
        deliveries.put(deliveryPoint.clone(), deliveries.containsKey(deliveryPoint) ? deliveries.get(deliveryPoint) + 1 : 1);
    }

    protected Map<Point, Integer> deliverPresents(String instructions) {
        Map<Point, Integer> deliveries = new HashMap<>();
        List<Point>         points     = IntStream.range(0, 2).mapToObj(i -> new Point(0, 0)).collect(Collectors.toList());

        for (int i = 0; i < instructions.length(); i++) {
            if (i == 0) {
                points.forEach(point -> deliverPresent(deliveries, point));
            }

            char instruction = instructions.charAt(i);
            Point pointToMove = santas > 1 && (i % 2) == 0 ? points.get(1) : points.get(0);
            pointToMove.move(instruction);

            deliveries.put(pointToMove.clone(), deliveries.containsKey(pointToMove) ? deliveries.get(pointToMove) + 1 : 1);
        }

        return deliveries;
    }

    public int solve(String fileName) throws IOException {
        String instructions = Files.readString(Paths.get("src", "main", "resources", fileName));
        return deliverPresents(instructions).size();
    }
}