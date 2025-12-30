package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.geometry.Polygon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Day12 extends PuzzleSolver<Long, Long> {
    private static final Pattern PRESENT_ID_PATTERN = Pattern.compile("^(\\d+):");
    private static final Pattern PRESENT_PATTERN    = Pattern.compile("^([#.]++)$");

    @Override
    public Long solveFirstPart() {
        var          lines               = loadInput();
        Integer      currentPresentId    = null;
        List<String> currentPresentInput = null;
        var          presents            = new HashMap<Integer, Polygon>();

        for (var line : lines) {
            if (line.isBlank()) {
                if (currentPresentId != null && currentPresentInput != null) {
                    var present = Polygon.parsePolygon(currentPresentInput);
                    presents.put(currentPresentId, present);
                    currentPresentId    = null;
                    currentPresentInput = null;
                }
                continue;
            }

            var presentIdMatcher = PRESENT_ID_PATTERN.matcher(line);
            if (presentIdMatcher.matches()) {
                currentPresentId = Integer.parseInt(presentIdMatcher.group(1));
                continue;
            }

            var presentMatcher = PRESENT_PATTERN.matcher(line);
            if (presentMatcher.matches()) {
                if (currentPresentInput == null) {
                    currentPresentInput = new ArrayList<>();
                }
                currentPresentInput.add(presentMatcher.group(1));
            }
        }

        System.out.println(presents);

        return 0L;
    }

    @Override
    public Long solveSecondPart() {
        return 0L;
    }
}
