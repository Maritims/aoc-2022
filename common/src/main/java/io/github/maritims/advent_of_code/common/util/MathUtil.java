package io.github.maritims.advent_of_code.common.util;

import io.github.maritims.advent_of_code.common.geometry.Point;
import io.github.maritims.advent_of_code.common.geometry.ShoelaceFormula;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class MathUtil {
    /**
     * Find the greatest common divisor by using Euclid's algorithm.
     */
    public static long gcd(long a, long b) {
        // We've found the gcd once the remainder is 0.
        if (b == 0) {
            return a;
        }

        var r = a % b;
        return gcd(b, r);
    }

    /**
     * Find the least common multiple of 2 numbers by using the greatest common divisor instead of using brute force or prime factorization.
     */
    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    /**
     * Find the least common multiple of 2 or more numbers using the greatest common divisor.
     */
    public static long lcm(Long... a) {
        return Arrays.stream(a).reduce(1L, MathUtil::lcm);
    }

    /**
     * Calculate the interior area of an irregular polygon by using the shoelace formula.
     */
    public static Double shoelace(List<Point> points) {
        return new ShoelaceFormula().calculate(points);
    }

    public static int getManhattanDistance(Point source, Point destination) {
        var n = Math.abs(destination.column() - source.column());
        var m = Math.abs(destination.row() - source.row());
        return n + m;
    }

    public static BiFunction<Number, Number, Number> resolveFunctionFromOperator(char operator) {
        return switch (operator) {
            case '+' -> (a, b) -> a.doubleValue() + b.doubleValue();
            case '-' -> (a, b) -> a.doubleValue() - b.doubleValue();
            case '*' -> (a, b) -> a.doubleValue() * b.doubleValue();
            case '/' -> (a, b) -> a.doubleValue() / b.doubleValue();
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }
}