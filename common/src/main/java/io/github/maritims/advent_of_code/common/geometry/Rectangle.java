package io.github.maritims.advent_of_code.common.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Rectangle {
    private final Point2D topLeft;
    private final Point2D bottomRight;

    public Rectangle(Point2D p1, Point2D p2) {
        Objects.requireNonNull(p1, "p1 must not be null");
        Objects.requireNonNull(p2, "p2 must not be null");

        var x1 = p1.col();
        var y1 = p1.row();
        var x2 = p2.col();
        var y2 = p2.row();

        this.topLeft = new Point2D(Math.min(x1, x2), Math.min(y1, y2));
        this.bottomRight = new Point2D(Math.max(x1, x2), Math.max(y1, y2));
    }

    public Point2D topLeft() {
        return topLeft;
    }

    public Point2D topRight() {
        return new Point2D(bottomRight.col(), topLeft.row());
    }

    public Point2D bottomLeft() {
        return new Point2D(topLeft.col(), bottomRight.row());
    }

    public Point2D bottomRight() {
        return bottomRight;
    }

    public List<Point2D> vertices() {
        return List.of(topLeft(), topRight(), bottomRight(), bottomLeft());
    }

    public double width() {
        return topLeft.col() - bottomRight.col();
    }

    public double height() {
        return topLeft.row() - bottomRight.row();
    }

    public double area() {
        return width() * height();
    }

    public Polygon toPolygon() {
        return new Polygon(new ArrayList<>(List.of(topLeft(), topRight(), bottomRight(), bottomLeft())));
    }
}
