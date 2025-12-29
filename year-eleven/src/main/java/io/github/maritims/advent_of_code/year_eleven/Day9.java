package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.geometry.Line2D;
import io.github.maritims.advent_of_code.common.geometry.Point2D;
import io.github.maritims.advent_of_code.common.geometry.Polygon;
import io.github.maritims.advent_of_code.common.geometry.Rectangle;

public class Day9 extends PuzzleSolver<Long, Long> {
    @Override
    public Long solveFirstPart() {
        var points = loadInput()
                .stream()
                .map(Point2D::fromString)
                .toList();
        var maxArea = 0.0;

        for(var i = 0; i < points.size(); i++) {
            for(var j = i + 1; j < points.size(); j++) {
                var p1 = points.get(i);
                var p2 = points.get(j);
                var line = Line2D.of(p1, p2);

                // Do we have a diagonal?
                if(line.isDiagonal()) {
                    var rectangle = new Rectangle(p1, p2);
                    maxArea = Math.max(maxArea, rectangle.getGridArea());
                }
            }
        }

        return (long) maxArea;
    }

    @Override
    public Long solveSecondPart() {
        var points = Point2D.fromStrings(loadInput());
        var polygonWithColoredTiles = new Polygon(points);
        var maxArea = 0.0;

        for(var i = 0; i < points.size(); i++) {
            for(var j = i + 1; j < points.size(); j++) {
                var p1 = points.get(i);
                var p2 = points.get(j);
                var line = p1.lineTo(p2);

                // Do we have a diagonal?
                if(line.isDiagonal()) {
                    var rectangle = new Rectangle(p1, p2);

                    // Don't bother with math if the area is smaller anyway.
                    if (maxArea > rectangle.getGridArea()) {
                        continue;
                    }

                    if(polygonWithColoredTiles.containsRectangle(rectangle)) {
                        maxArea = rectangle.getGridArea();
                    }
                }
            }
        }

        return (long) maxArea;
    }
}
