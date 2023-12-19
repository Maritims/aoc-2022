package io.github.maritims.toolbox;

import java.util.Arrays;

public class MathUtil {
    /**
     * Find the greatest common divisor by using Euclid's algorithm.
     */
    public static long gcd(long a, long b) {
        // We've found the gcd once the remainder is 0.
        if(b == 0) {
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
}
