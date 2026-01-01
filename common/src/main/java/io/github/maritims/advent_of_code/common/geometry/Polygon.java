package io.github.maritims.advent_of_code.common.geometry;

import java.util.*;

public final class Polygon {
    private final List<Point2D> vertices;

    // region Internal variables for lazy loading.
    private Rectangle    boundingBox;
    private Boolean      isConvex;
    private List<Line2D> edges;
    // endregion

    public Polygon(List<Point2D> vertices) {
        if (vertices == null || vertices.isEmpty()) {
            throw new IllegalArgumentException("vertices cannot be null or empty");
        }
        this.vertices = vertices;
    }

    public Polygon(Point2D... vertices) {
        if (vertices == null || vertices.length == 0) {
            throw new IllegalArgumentException("vertices cannot be null or empty");
        }
        this.vertices = Arrays.asList(vertices);
    }

    public static Polygon parsePolygon(List<String> lines) {
        var points = new ArrayList<Point2D>();

        for (var row = 0; row < lines.size(); row++) {
            for (var col = 0; col < lines.get(row).length(); col++) {
                var c = lines.get(row).charAt(col);
                if (c == '#') {
                    var point = new Point2D(col, row);
                    points.add(point);
                } else if (c != '.') {
                    throw new IllegalArgumentException("Unexpected character in input: " + c);
                }
            }
        }

        return new Polygon(points);
    }

    Rectangle getBoundingBox() {
        if (boundingBox == null) {
            var minCol = vertices.stream().mapToDouble(Point2D::col).min().orElseThrow();
            var maxCol = vertices.stream().mapToDouble(Point2D::col).max().orElseThrow();
            var minRow = vertices.stream().mapToDouble(Point2D::row).min().orElseThrow();
            var maxRow = vertices.stream().mapToDouble(Point2D::row).max().orElseThrow();

            boundingBox = new Rectangle(new Point2D(minCol, minRow), new Point2D(maxCol, maxRow));
        }
        return boundingBox;
    }

    List<Line2D> getEdges() {
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

    boolean computeConvexity() {
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

    boolean isInBoundingBox(Rectangle rectangle) {
        var boundingBox = getBoundingBox();
        return rectangle.getTopLeft().col() >= boundingBox.getTopLeft().col() &&
                rectangle.getTopRight().col() <= boundingBox.getTopRight().col() &&
                rectangle.getTopLeft().row() >= boundingBox.getTopLeft().row() &&
                rectangle.getBottomLeft().row() <= boundingBox.getBottomLeft().row();
    }

    /**
     * Use ray casting to determine whether the polygon contains the given point.
     *
     * @param point The point to check.
     * @return True if the point is contained within the polygon according to ray casting, otherwise false.
     */
    boolean isInPolygon(Point2D point) {
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

    boolean isConvex() {
        if (isConvex == null) {
            isConvex = computeConvexity();
        }
        return isConvex;
    }

    boolean isConcave() {
        return !isConvex();
    }

    public List<Point2D> getVertices() {
        return vertices;
    }

    public Set<Point2D> getDiscretePoints() {
        return new HashSet<>(vertices);
    }

    public List<Polygon> getUniqueOrientations() {
        var seen         = new HashSet<List<Point2D>>();
        var orientations = new ArrayList<Polygon>();
        var current      = this.normalize();

        for (var i = 0; i < 4; i++) {
            current = current.rotate90().normalize();
            // Vertices list is a good key for uniqueness
            if (seen.add(current.getVertices())) {
                orientations.add(current);
            }
        }
        return orientations;
    }

    public boolean containsRectangle(Rectangle rectangle) {
        if (!isInBoundingBox(rectangle)) {
            return false;
        }

        if (!rectangle.getVertices().stream().allMatch(this::isInPolygon)) {
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

    /**
     * Shift the polygon's position. Does not mutate the instance itself.
     *
     * @param dx The difference in position on the X axis (the columns).
     * @param dy The difference in position on the Y axis (the rows).
     * @return A new polygon with the new position.
     */
    public Polygon move(double dx, double dy) {
        var translatedVertices = vertices.stream()
                .map(point -> new Point2D(point.col() + dx, point.row() + dy))
                .toList();
        return new Polygon(translatedVertices);
    }

    /**
     * Rotate the polygon 90 degrees clockwise. Does not mutate the instance itself.
     * Transformation: (col, row) -> (-row, col)
     *
     * @return A new polygon with a new orientation.
     */
    public Polygon rotate90() {
        var rotatedVertices = vertices.stream()
                .map(point -> new Point2D(-point.row(), point.col()))
                .toList();
        return new Polygon(rotatedVertices);
    }

    public Polygon normalize() {
        var boundingBox = getBoundingBox();
        var minCol      = boundingBox.getTopLeft().col();
        var minRow      = boundingBox.getTopLeft().row();

        // Translate the polygon so its top-left corner is at (0,0)
        return this.move(-minCol, -minRow);
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "vertices=" + vertices +
                '}';
    }
}
