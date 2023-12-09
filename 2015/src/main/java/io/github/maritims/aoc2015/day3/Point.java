package io.github.maritims.aoc2015.day3;

import java.util.function.Consumer;

public class Point implements Cloneable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void north() {
        y++;
    }

    public void south() {
        y--;
    }

    public void west() {
        x--;
    }

    public void east() {
        x++;
    }

    protected void move(char instruction) {
        switch (instruction) {
            case '^' -> north();
            case 'v' -> south();
            case '<' -> west();
            case '>' -> east();
            default -> throw new IllegalStateException("Unexpected value: " + instruction);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }

    @Override
    public Point clone() {
        try {
            return (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
