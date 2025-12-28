package io.github.maritims.advent_of_code.common.graph;

import io.github.maritims.advent_of_code.common.geometry.Point3D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class NearestNeighbour {
    public static <T, Q> List<T> sort(List<T> input, BiFunction<T, T, Q> quantifier, Comparator<Q> quantifiableComparator) {
        if(input == null) {
            throw new IllegalArgumentException("input cannot be null");
        }
        if (input.isEmpty()) {
            throw new IllegalArgumentException("input cannot be empty");
        }

        var remaining = new ArrayList<>(input); // Copy to ensure immutability.
        var result = new ArrayList<T>();
        var current = remaining.removeFirst();
        result.add(current);

        while (!remaining.isEmpty()) {
            var bestIndex = 0;
            var bestQuantifiable = quantifier.apply(current, remaining.getFirst());

            for(var i = 0; i < remaining.size(); i++) {
                var quantifiable = quantifier.apply(current, remaining.get(i));
                if(quantifiableComparator.compare(quantifiable, bestQuantifiable) < 0) {
                    bestQuantifiable = quantifiable;
                    bestIndex = i;
                }
            }

            // Rinse and repeat.
            current = remaining.remove(bestIndex);
            result.add(current);
        }

        return result;
    }
}
