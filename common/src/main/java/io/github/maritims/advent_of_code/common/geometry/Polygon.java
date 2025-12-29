package io.github.maritims.advent_of_code.common.geometry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public final class Polygon {
    private final List<Point2D> vertices;

    // region Internal variables for lazy loading.
    private Rectangle    boundingBox;
    private Boolean      isConvex;
    private List<Line2D> edges;
    // endregion

    public Polygon(List<Point2D> vertices) {
        this.vertices = vertices;
    }

    public List<Point2D> getVertices() {
        return vertices;
    }

    public Rectangle getBoundingBox() {
        if (boundingBox == null) {
            var minCol = vertices.stream().mapToDouble(Point2D::col).min().orElseThrow();
            var maxCol = vertices.stream().mapToDouble(Point2D::col).max().orElseThrow();
            var minRow = vertices.stream().mapToDouble(Point2D::row).min().orElseThrow();
            var maxRow = vertices.stream().mapToDouble(Point2D::row).max().orElseThrow();

            boundingBox = new Rectangle(new Point2D(minCol, minRow), new Point2D(maxCol, maxRow));
        }
        return boundingBox;
    }

    public boolean isInBoundingBox(Rectangle rectangle) {
        var boundingBox = getBoundingBox();
        return rectangle.getTopLeft().col() >= boundingBox.getTopLeft().col() &&
                rectangle.getTopRight().col() <= boundingBox.getTopRight().col() &&
                rectangle.getTopLeft().row() >= boundingBox.getTopLeft().row() &&
                rectangle.getBottomLeft().row() <= boundingBox.getBottomLeft().row();
    }

    public boolean isConvex() {
        if (isConvex == null) {
            isConvex = computeConvexity();
        }
        return isConvex;
    }

    public boolean isConcave() {
        return !isConvex();
    }

    public boolean computeConvexity() {
        if (vertices.size() < 3) {
            return true;
        }

        Boolean isPositive = null;
        var     n          = vertices.size();

        for (var i = 0; i < n; i++) {
            var p1 = vertices.get(i);
            var p2 = vertices.get((i + 1) % n);
            var p3 = vertices.get((i + 2) % n);

            var v1           = p1.vectorTo(p2);
            var v2           = p2.vectorTo(p3);
            var crossProduct = v1.cross(v2);

            if (crossProduct != 0) {
                var currentIsPositive = crossProduct > 0;
                if (isPositive == null) {
                    isPositive = currentIsPositive;
                } else if (isPositive != currentIsPositive) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean containsPoint(Point2D point) {
        var crossings = 0L;
        var n         = vertices.size();

        for (var i = 0; i < n; i++) {
            var v1 = vertices.get(i);
            var v2 = vertices.get((i + 1) % n);

            // Check if the point is on this edge.
            var crossProduct = (point.row() - v1.row()) * (v2.col() - v1.col()) -
                    (point.col() - v1.col()) * (v2.row() - v1.row());

            if (crossProduct == 0) {
                if (Math.min(v1.col(), v2.col()) <= point.col() &&
                        point.col() <= Math.max(v1.col(), v2.col()) &&
                        Math.min(v1.row(), v2.row()) <= point.row() &&
                        point.row() <= Math.max(v1.row(), v2.row())) {
                    return true;
                }
            }

            // Ray casting check.
            if (((v1.row() > point.row()) != (v2.row() > point.row())) &&
                    (point.col() < (v2.col() - v1.col()) * (point.row() - v1.row()) /
                            (v2.row() - v1.row()) + v1.col())) {
                crossings++;
            }
        }

        return (crossings % 2) == 1;
    }

    public List<Line2D> getEdges() {
        if (edges == null) {
            edges = new ArrayList<>();
            var n = vertices.size();
            for (var i = 0; i < n; i++) {
                var edge = new Line2D(vertices.get(i), vertices.get((i + 1) % n));
                edges.add(edge);
            }
        }
        return edges;
    }

    public boolean containsRectangle(Rectangle rectangle) {
        if (!isInBoundingBox(rectangle)) {
            return false;
        }

        if (!rectangle.getVertices().stream().allMatch(this::containsPoint)) {
            return false;
        }

        if (isConcave()) {
            var polygonEdges   = getEdges();
            var rectangleEdges = rectangle.getEdges();

            for (var polygonEdge : polygonEdges) {
                for (var rectangleEdge : rectangleEdges) {
                    if (polygonEdge.intersectsProperly(rectangleEdge)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
