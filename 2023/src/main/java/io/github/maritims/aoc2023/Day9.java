package io.github.maritims.aoc2023;

import io.github.maritims.toolbox.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day9 extends Day {
    protected Day9(boolean useSampleData) {
        super(9, useSampleData);
    }

    protected ArrayList<List<Integer>> getSequencedStories(ArrayList<List<Integer>> sequences) {
        var iterator    = sequences.get(sequences.size() - 1).iterator();
        var current     = iterator.next();
        var newSequence = new ArrayList<Integer>();
        var isAllZero   = true;

        while (iterator.hasNext()) {
            var next       = iterator.next();
            var difference = next - current;
            newSequence.add(difference);
            current = next;

            if (difference != 0) isAllZero = false;
        }

        sequences.add(newSequence);
        return isAllZero ? sequences : getSequencedStories(sequences);
    }

    protected int getSum(List<String> lines, boolean extrapolateForward) {
        var sum = 0;
        for (var line : lines) {
            var stories          = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
            var sequencedStories = new ArrayList<List<Integer>>();
            sequencedStories.add(new ArrayList<>(stories));
            sequencedStories = getSequencedStories(sequencedStories);

            for (var i = sequencedStories.size(); i > 0; i--) {
                // Take care of the all-zero row first.
                if (i == sequencedStories.size()) {
                    sequencedStories.get(i - 1).add(0);
                    continue;
                }

                var sequenceBelowCurrent = sequencedStories.get(i);
                var increment            = sequenceBelowCurrent.get(extrapolateForward ? sequenceBelowCurrent.size() - 1 : 0);
                var currentSequence      = sequencedStories.get(i - 1);
                var sequencingValue      = currentSequence.get(extrapolateForward ? currentSequence.size() - 1 : 0);
                var extrapolation        = extrapolateForward ? sequencingValue + increment : sequencingValue - increment;

                if (extrapolateForward) {
                    currentSequence.add(extrapolation);
                } else {
                    currentSequence.add(0, extrapolation);
                }
            }

            var sequence      = sequencedStories.get(0);
            var extrapolation = sequence.get(extrapolateForward ? sequence.size() - 1 : 0);
            sum += extrapolation;
        }
        return sum;
    }

    @Override
    public long solvePartOne() {
        var lines = readAllLines();
        return getSum(lines, true);
    }

    @Override
    public long solvePartTwo() {
        var lines = readAllLines();
        return getSum(lines, false);
    }
}