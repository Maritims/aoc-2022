package io.github.maritims.advent_of_code.common.geometry;

public record Line2D(Point2D from, Point2D to) {
    public static class Builder {
        private Point2D from;
        private Point2D to;

        private Builder() {}

        public Builder from(Point2D from) {
            this.from = from;
            return this;
        }

        public Builder from(int col, int row) {
            return from(new Point2D(col, row));
        }

        public Builder to(Point2D to) {
            this.to = to;
            return this;
        }

        public Builder to(int x, int y) {
            return to(new Point2D(x, y));
        }

        public Line2D build() {
            return new Line2D(from, to);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public double width() {
        return Math.abs(from.col() - to.col());
    }

    public double height() {
        return Math.abs(from.row() - to.row());
    }

    public boolean isDiagonal() {
        return from.col() != to.col() && from.row() != to.row();
    }
}
