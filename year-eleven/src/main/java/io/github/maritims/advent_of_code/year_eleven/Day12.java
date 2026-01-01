package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.geometry.Point2D;
import io.github.maritims.advent_of_code.common.geometry.Polygon;
import io.github.maritims.advent_of_code.common.geometry.Rectangle;
import io.github.maritims.advent_of_code.common.geometry.visualization.PolygonPacker;

import java.util.*;
import java.util.regex.Pattern;

public class Day12 extends PuzzleSolver<Long, Long> {
    private static final Pattern PRESENT_ID_PATTERN    = Pattern.compile("^(\\d+):");
    private static final Pattern PRESENT_PATTERN       = Pattern.compile("^([#.]++)$");
    private static final Pattern CONFIGURATION_PATTERN = Pattern.compile("^(\\d+)x(\\d+): ((\\d+\\s*)+)$");

    record Configuration(int cols, int rows, int[] quantityPerIndex) {
    }

    @Override
    public Long solveFirstPart() {
        var          lines               = loadInput();
        Integer      currentPresentId    = null;
        List<String> currentPresentInput = null;
        var          presents            = new HashMap<Integer, Polygon>();
        var          configs             = new ArrayList<Configuration>();

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

            var configurationMatcher = CONFIGURATION_PATTERN.matcher(line);
            if (configurationMatcher.matches()) {
                var cols = Integer.parseInt(configurationMatcher.group(1));
                var rows = Integer.parseInt(configurationMatcher.group(2));
                var quantityPerIndex = Arrays.stream(configurationMatcher.group(3).split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                var config = new Configuration(cols, rows, quantityPerIndex);
                configs.add(config);
            }
        }

        var result = 0L;

        for (var config : configs) {
            var grid   = new Rectangle(new Point2D(0, 0), new Point2D(config.cols, config.rows));
            var pieces = new HashMap<Integer, List<Polygon>>();

            for (var id = 0; id < config.quantityPerIndex.length; id++) {
                var quantity = config.quantityPerIndex[id];
                if (quantity > 0) {
                    var piece = presents.get(id);
                    for (var j = 0; j < quantity; j++) {
                        pieces.computeIfAbsent(id, k -> new ArrayList<>()).add(piece);
                    }
                }
            }

            var packer = new PolygonPacker(grid, pieces.values().stream().flatMap(Collection::stream).toList());
            if (packer.pack() != null) {
                result++;
            }
        }

        return result;
    }

    @Override
    public Long solveSecondPart() {
        return 0L;
    }
}
