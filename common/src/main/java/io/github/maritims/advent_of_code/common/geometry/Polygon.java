package io.github.maritims.advent_of_code.common.geometry;

import java.util.ArrayList;
import java.util.List;

public record Polygon(List<Point2D> vertices) {
    public boolean overlaps(Polygon other) {
        var axes = new ArrayList<Vector2D>();
        axes.addAll(getAxes());
        axes.addAll(other.getAxes());

        for(var axis : axes) {
            var p1 = project(axis);
            var p2 = other.project(axis);
            if(!p1.overlaps(p2)) {
                // We found a gap.
                return false;
            }
        }

        return true;
    }

    public boolean contains(Point2D point) {
        var inside = false;
        for (var i = 0; i < vertices.size(); i++) {
            var p1 = vertices.get(i);
            var p2 = vertices.get((i + 1) % vertices.size());

            if ((p1.row() > point.row()) != (p2.row() > point.row()) &&
                    point.col() < (p2.col() - p1.col()) * (point.row() - p1.row()) / (p2.row() - p1.row()) + p1.col()) {
                inside = !inside;
            }
        }
        return inside;
    }

    public boolean contains(Rectangle rectangle) {
        return rectangle.vertices().stream().allMatch(this::contains);
    }

    public List<Vector2D> getAxes() {
        var axes = new ArrayList<Vector2D>();
        for(var i = 0; i < vertices.size(); i++) {
            var p1 = vertices.get(i);
            var p2 = vertices.get((i + 1) % vertices.size());
            axes.add(new Vector2D(p1, p2).perpendicular().normalize());
        }
        return axes;
    }

    public Projection project(Vector2D axis) {
        var min = Double.POSITIVE_INFINITY;
        var max = Double.NEGATIVE_INFINITY;
        for(var p : vertices) {
            var dot = (p.col() * axis.x()) + (p.row() * axis.y());
            min = Math.min(min, dot);
            max = Math.max(max, dot);
        }
        return new Projection(min, max);
    }
}
