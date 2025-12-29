package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.geometry.Point2D;
import io.github.maritims.advent_of_code.common.geometry.Polygon;
import io.github.maritims.advent_of_code.common.geometry.Rectangle;
import io.github.maritims.advent_of_code.common.tuples.Tuple2;

public class Day9 extends PuzzleSolver<Long, Long> {
    private Tuple2<Long, Long> solution;


    private Tuple2<Long, Long> solveBothParts() {
        if (solution == null) {
            var points                  = Point2D.fromStrings(loadInput());
            var polygonWithColoredTiles = new Polygon(points);
            var maxAreaForFirstPart     = 0.0;
            var maxAreaForSecondPart    = 0.0;

            for (var i = 0; i < points.size(); i++) {
                for (var j = i + 1; j < points.size(); j++) {
                    var p1   = points.get(i);
                    var p2   = points.get(j);
                    var line = p1.lineTo(p2);

                    // Do we have a diagonal?
                    if (line.isDiagonal()) {
                        var rectangle = new Rectangle(p1, p2);

                        maxAreaForFirstPart = Math.max(maxAreaForFirstPart, rectangle.getGridArea());

                        // Don't bother with math if the area is smaller anyway.
                        if (rectangle.getGridArea() > maxAreaForSecondPart && polygonWithColoredTiles.containsRectangle(rectangle)) {
                            maxAreaForSecondPart = rectangle.getGridArea();
                        }
                    }
                }
            }

            solution = new Tuple2<>((long) maxAreaForFirstPart, (long) maxAreaForSecondPart);
        }
        return solution;
    }

    @Override
    public Long solveFirstPart() {
        return solveBothParts().item1();
    }

    @Override
    public Long solveSecondPart() {
        return solveBothParts().item2();
    }
}
