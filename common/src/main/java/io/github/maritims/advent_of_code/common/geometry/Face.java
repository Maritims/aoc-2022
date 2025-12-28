package io.github.maritims.advent_of_code.common.geometry;

public record Face(int width, int height) {
    public Face {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Face dimensions must be greater than 0");
        }

    }

    public Integer getArea() {
        return width * height;
    }

    /**
     * The squaredDistance around the face.
     */
    public int getPerimeter() {
        return 2 * (width + height);
    }
}
