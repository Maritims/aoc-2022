package io.github.maritims.advent_of_code.common.geometry;

import java.util.Objects;

public final class Line2D {
    private final Point2D start;
    private final Point2D end;

    // region Internal variables for lazy loading.
    private Double   width;
    private Double   height;
    private Boolean  isDiagonal;
    private Vector2D vector;
    // endregion

    public Line2D(Point2D start, Point2D end) {
        this.start = start;
        this.end   = end;
    }

    public static Line2D of(Point2D from, Point2D to) {
        return new Line2D(from, to);
    }

    public static Line2D of(double x1, double y1, double x2, double y2) {
        return new Line2D(new Point2D(x1, y1), new Point2D(x2, y2));
    }

    public Point2D getStart() {
        return start;
    }

    public Point2D getEnd() {
        return end;
    }

    public double getWidth() {
        if (width == null) {
            width = Math.abs(start.col() - end.col());
        }
        return width;
    }

    public double getHeight() {
        if (height == null) {
            height = Math.abs(start.row() - end.row());
        }
        return height;
    }

    public boolean isDiagonal() {
        if (isDiagonal == null) {
            isDiagonal = start.col() != end.col() && start.row() != end.row();
        }
        return isDiagonal;
    }

    public boolean isPointCollinear(Point2D point) {
        var lineVector   = toVector();
        var pointVector  = new Vector2D(start, point);
        var crossProduct = lineVector.cross(pointVector);

        return Math.abs(crossProduct) < 1e-9;
    }

    public boolean isPointWithinBounds(Point2D point) {
        return point.col() <= Math.max(start.col(), end.col()) &&
                point.col() >= Math.min(start.col(), end.col()) &&
                point.row() <= Math.max(start.row(), end.row()) &&
                point.row() >= Math.min(start.row(), end.row());
    }

    public boolean containsPoint(Point2D point) {
        return isPointCollinear(point) && isPointWithinBounds(point);
    }

    /**
     * Determines if this line segment and another line segment intersect "properly".
     * <p>
     * A proper intersection occurs when the two segments cross each other at a point
     * that is interior to both segments (forming an 'X' shape).
     * </p>
     * <p>
     * This method returns {@code false} if:
     * <ul>
     * <li>The segments are parallel.</li>
     * <li>The segments are collinear (even if they overlap).</li>
     * <li>The segments only touch at an endpoint (forming a 'T' or 'L' junction).</li>
     * </ul>
     * This is particularly useful for containment checks where a rectangle is allowed
     * to share an edge or a corner with a polygon boundary without being considered
     * "crossing" it.
     * </p>
     *
     * @param other The other line segment to check against.
     * @return {@code true} if the segments cross at an interior point; {@code false} otherwise.
     */
    public boolean intersectsProperly(Line2D other) {
        var v1 = toVector();
        var v2 = other.toVector();

        var thisStartToOtherStart = new Vector2D(start, other.start);
        var thisStartToOtherEnd   = new Vector2D(start, other.end);

        var otherStartToThisStart = new Vector2D(other.start, start);
        var otherStartToThisEnd   = new Vector2D(other.start, end);

        var d1 = v1.cross(thisStartToOtherStart);
        var d2 = v1.cross(thisStartToOtherEnd);
        var d3 = v2.cross(otherStartToThisStart);
        var d4 = v2.cross(otherStartToThisEnd);

        // A proper intersection ONLY happens if the endpoints are strictly
        // on opposite sides (one positive, one negative).
        return ((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) &&
                ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0));
    }

    Vector2D toVector() {
        if (vector == null) {
            vector = new Vector2D(start, end);
        }
        return vector;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Line2D line2D = (Line2D) o;
        return Objects.equals(start, line2D.start) && Objects.equals(end, line2D.end) && Objects.equals(width, line2D.width) && Objects.equals(height, line2D.height) && Objects.equals(isDiagonal, line2D.isDiagonal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, width, height, isDiagonal);
    }

    @Override
    public String toString() {
        return String.format("(%.0f,%.0f), (%.0f,%.0f)", start.col(), start.row(), end.col(), end.row());
    }
}
