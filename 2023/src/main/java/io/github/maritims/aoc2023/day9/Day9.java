package io.github.maritims.aoc2023.day9;

import io.github.maritims.toolbox.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day9 extends Day {
    protected Day9(boolean useSampleData) {
        super(9, useSampleData);
    }

    List<List<Integer>> getFullHistory(List<List<Integer>> numbers) {
        var iterator    = numbers.get(numbers.size() - 1).iterator();
        var current     = iterator.next();
        var differences = new ArrayList<Integer>();
        var isAllZero   = true;

        while (iterator.hasNext()) {
            var next       = iterator.next();
            var difference = next - current;
            differences.add(difference);
            current = next;

            if (difference != 0) isAllZero = false;
        }

        numbers.add(differences);
        return isAllZero ? numbers : getFullHistory(numbers);
    }

    @Override
    public long solvePartOne() {
        var lines   = readAllLines();
        var sum = 0;
        for (var line : lines) {
            var numbers = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
            var listOfNumbers = new ArrayList<List<Integer>>();
            listOfNumbers.add(new ArrayList<>(numbers));

            var fullHistory = getFullHistory(listOfNumbers);

            for (var i = fullHistory.size(); i > 0; i--) {
                // Take care of the all-zero row first.
                if (i == fullHistory.size()) {
                    fullHistory.get(i - 1).add(0);
                    continue;
                }

                var historyBelowCurrent = fullHistory.get(i);
                var increment           = historyBelowCurrent.get(historyBelowCurrent.size() - 1);

                var currentHistory = fullHistory.get(i - 1);
                var extrapolation  = currentHistory.get(currentHistory.size() - 1) + increment;
                currentHistory.add(extrapolation);
            }

            var originalHistory = fullHistory.get(0);
            var extrapolation = originalHistory.get(originalHistory.size() - 1);
            sum += extrapolation;
        }

        return sum;
    }

    @Override
    public long solvePartTwo() {
        return 0;
    }
}