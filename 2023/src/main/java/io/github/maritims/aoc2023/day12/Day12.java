package io.github.maritims.aoc2023.day12;

import io.github.maritims.toolbox.Day;

import java.util.List;
import java.util.function.Supplier;

public class Day12 extends Day {
    protected Day12(boolean useSampleData) {
        super(12, useSampleData);
    }

    int get(String line, List<Integer> groups) {
        // We're done if we're rid of the entire line and all groups.
        if(line.isEmpty() && groups.isEmpty()) {
            return 1;
        }

        // We're done if we're rid of all the groups and the line doesn't contain any more pound signs.
        if(groups.isEmpty() && !line.contains("#")) {
            return 1;
        }

        var c = line.substring(0, 1);

        if(c.equals(".")) {
            // We can't do anything about dots, so we'll just ignore them.
            return get(line.substring(1), groups);
        }

        if(c.equals("#")) {
            // We have to be in a group at this point.
            var group = groups.get(0);

            // Is the line long enough to match the group?
            // Does the line contain only pounds or question marks?
            // Are we at the end of the record or is the next character not a pound sign?

            Supplier<Boolean> isLongEnoughToMatch = () -> line.length() >= group;
            Supplier<Boolean> isLineCutOffByGroupValid = () -> !line.substring(0, group).contains(".");
            Supplier<Boolean> isEndOfLine = () -> line.length() == group;
            Supplier<Boolean> isNextCharNotPound = () -> !line.substring(group, 1).equals("#");

            if (
                isLongEnoughToMatch.get()
                    && isLineCutOffByGroupValid.get()
                    && (isEndOfLine.get() || isNextCharNotPound.get())
            ) {
                groups.remove(0);
                return get(line.substring(group), groups);
            }
        }

        if(c.equals("?")) {
            // Branch out from every question mark by assuming there's one branch where it's a pound sign and one branch where it's a dot.
            return get("#" + line.substring(1), groups) + get("." + line.substring(1), groups);
        }

        return 0;
    }

    @Override
    public long solvePartOne() {
        // Establish base cases.

        // # = Broken
        // . = Working
        // ? = Unknown

        // Find the number of possible arrangements.
        // How do we know if an arrangement is valid?

        return 0;
    }

    @Override
    public long solvePartTwo() {
        return 0;
    }
}
