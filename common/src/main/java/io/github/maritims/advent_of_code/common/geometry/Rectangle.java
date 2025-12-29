package io.github.maritims.advent_of_code.common.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Rectangle {
    private final Point2D topLeft;
    private final Point2D bottomRight;

    // region Internal variables for lazy loading.
    private Point2D       topRight;
    private Point2D       bottomLeft;
    private List<Point2D> vertices;
    private Double        width;
    private Double        height;
    private Double        area;
    private Double        gridArea;
    private Point2D       center;
    private List<Line2D>  edges;
    // endregion

    public Rectangle(Point2D p1, Point2D p2) {
        Objects.requireNonNull(p1, "p1 must not be null");
        Objects.requireNonNull(p2, "p2 must not be null");

        var x1 = p1.col();
        var y1 = p1.row();
        var x2 = p2.col();
        var y2 = p2.row();

        this.topLeft     = new Point2D(Math.min(x1, x2), Math.min(y1, y2));
        this.bottomRight = new Point2D(Math.max(x1, x2), Math.max(y1, y2));
    }

    public Point2D getTopLeft() {
        return topLeft;
    }

    public Point2D getTopRight() {
        if (topRight == null) {
            topRight = new Point2D(getBottomRight().col(), getTopLeft().row());
        }
        return topRight;
    }

    public Point2D getBottomLeft() {
        if (bottomLeft == null) {
            bottomLeft = new Point2D(getTopLeft().col(), getBottomRight().row());
        }
        return bottomLeft;
    }

    public Point2D getBottomRight() {
        return bottomRight;
    }

    public Point2D getCenter() {
        if (center == null) {
            var xc = (getTopLeft().col() + getBottomRight().col()) / 2;
            var yc = (getTopLeft().row() + getBottomRight().row()) / 2;
            center = new Point2D(xc, yc);
        }
        return center;
    }

    public List<Line2D> getEdges() {
        if (edges == null) {
            edges = new ArrayList<>(List.of(
                    Line2D.of(getTopLeft(), getTopRight()),
                    Line2D.of(getTopRight(), getBottomRight()),
                    Line2D.of(getBottomRight(), getBottomLeft()),
                    Line2D.of(getBottomLeft(), getTopLeft())
            ));
        }
        return edges;
    }

    public List<Point2D> getVertices() {
        if (vertices == null) {
            vertices = List.of(getTopLeft(), getTopRight(), getBottomRight(), getBottomLeft());
        }
        return vertices;
    }

    public double getWidth() {
        if (width == null) {
            width = getBottomRight().col() - getTopLeft().col();
        }
        return width;
    }

    public double getHeight() {
        if (height == null) {
            height = getBottomRight().row() - getTopLeft().row();
        }
        return height;
    }

    public double getArea() {
        if (area == null) {
            area = getWidth() * getHeight();
        }
        return area;
    }

    public double getGridArea() {
        if (gridArea == null) {
            gridArea = (getWidth() + 1) * (getHeight() + 1);
        }
        return gridArea;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(topLeft, rectangle.topLeft) && Objects.equals(bottomRight, rectangle.bottomRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, bottomRight);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "topLeft=" + topLeft +
                ", bottomRight=" + bottomRight +
                ", topRight=" + topRight +
                ", bottomLeft=" + bottomLeft +
                ", vertices=" + vertices +
                ", width=" + width +
                ", height=" + height +
                ", area=" + area +
                ", gridArea=" + gridArea +
                ", center=" + center +
                ", edges=" + edges +
                '}';
    }
}
