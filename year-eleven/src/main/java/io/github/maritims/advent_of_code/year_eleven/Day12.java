package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.geometry.Point2D;
import io.github.maritims.advent_of_code.common.geometry.Polygon;
import io.github.maritims.advent_of_code.common.geometry.Rectangle;
import io.github.maritims.advent_of_code.common.geometry.visualization.PolygonPacker;
import io.github.maritims.advent_of_code.common.tuples.Tuple2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.IntStream;

public class Day12 extends PuzzleSolver<Long, Long> {
    private static final Logger log = LoggerFactory.getLogger(Day12.class);

    record Grid(int width, int height, List<Integer> counts) {
    }

    @Override
    public Long solveFirstPart() {
        var lines    = loadInput();
        var grids    = new ArrayList<Tuple2<Rectangle, List<Integer>>>();
        var polygons = new ArrayList<Polygon>();
        var shape    = new ArrayList<String>();

        for (String line : lines) {
            if (line.endsWith(":")) {
                shape = new ArrayList<>();
            } else if (line.startsWith("#") || line.startsWith(".")) {
                shape.add(line);
            } else if (line.isBlank()) {
                polygons.add(Polygon.parsePolygon(shape));
                shape = null;
            } else {
                var parts      = line.replace(":", "").split(" ");
                var first      = parts[0];
                var counts     = Arrays.stream(parts).skip(1).map(Integer::parseInt).toList();
                var dimensions = first.split("x");
                var width      = Integer.parseInt(dimensions[0]);
                var height     = Integer.parseInt(dimensions[1]);
                var grid       = new Rectangle(new Point2D(0, 0), new Point2D(width, height));
                grids.add(new Tuple2<>(grid, counts));
            }
        }

        var impossibleGrids = 0;
        var successfulGrids = 0;
        var iterator        = grids.iterator();

        while (iterator.hasNext()) {
            var grid                = iterator.next();
            var pointsToFitOnGrid   = 0;
            var polygonsToFitOnGrid = new ArrayList<Polygon>();
            var spaceOnGrid         = grid.item1().getArea();

            for (var i = 0; i < grid.item2().size(); i++) {
                var count = grid.item2().get(i);
                if (count > 0) {
                    var polygon = polygons.get(i);
                    polygonsToFitOnGrid.add(polygon);

                    for (var j = 0; j < count; j++) {
                        polygonsToFitOnGrid.add(polygon);
                        pointsToFitOnGrid += polygon.getVertices().size();
                    }
                }
            }

            // Can the pieces be fit next to each other?
            var maxDiscreteWidth  = polygonsToFitOnGrid.stream().mapToDouble(Polygon::getDiscreteWidth).max().orElseThrow();
            var maxDiscreteHeight = polygonsToFitOnGrid.stream().mapToDouble(Polygon::getDiscreteHeight).max().orElseThrow();
            var slots             = (grid.item1().getWidth() / maxDiscreteWidth) * (grid.item1().getHeight() / maxDiscreteHeight);

            if (slots > polygonsToFitOnGrid.size()) {
                successfulGrids++;
                iterator.remove();
            }
            // Can the pieces fit in a perfect packing situation?
            else if (pointsToFitOnGrid > spaceOnGrid) {
                impossibleGrids++;
                iterator.remove();
            }
            // Flip, translate and rotate our way to success...?
            else {

            }
        }

        log.debug("possible grids: {}, impossible grids: {}, undetermined grids: {}", successfulGrids, impossibleGrids, grids.size());

        return 0L;
    }

    @Override
    public Long solveSecondPart() {
        return 0L;
    }
}
