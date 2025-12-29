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

                // Do we have a diagonal?
                var line = Line2D.newBuilder().from(p1).to(p2).build();
                if(line.isDiagonal()) {
                    var width = line.width() + 1;
                    var height = line.height() + 1;
                    var area = width * height;
                    maxArea = Math.max(maxArea, area);
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

                // Do we have a diagonal?
                var line = Line2D.newBuilder().from(p1).to(p2).build();
                if(line.isDiagonal()) {
                    var rectangle = new Rectangle(p1, p2);

                    if(polygonWithColoredTiles.overlaps(rectangle.toPolygon())) {
                        // Include the "fence posts" by adding 1 to width and height.
                        var width = rectangle.width() + 1;
                        var height = rectangle.height() + 1;
                        var area = width * height;
                        maxArea = Math.max(maxArea, area);
                    }
                }
            }
        }

        return (long) maxArea;
    }
}
