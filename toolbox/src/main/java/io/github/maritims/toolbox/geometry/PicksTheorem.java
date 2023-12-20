package io.github.maritims.toolbox.geometry;

/**
 * <h1>Pick's Theorem</h1>
 * <p>Pick's Theorem states that the area of a polygon can be calculated by considering the number of points interior to the polygon and the number of points on its boundary.</p>
 * <p>The area (A) can be calculated by using the following formula: A = i + (b * 0.5) - 1</p>
 */
public class PicksTheorem {
    /**
     * Calculate the area (A) of the polygon: A = i + (b * 0.5) - 1
     * @param i The number of points interior to the polygon.
     * @param b The number of points on along the boundary of the polygon.
     */
    public static double getArea(int i, int b) {
        return i + (b * 0.5) - 1;
    }

    /**
     * Calculate the number of points interior to the polygon: i = A - (b * 0.5) + 1
     */
    public static int getInteriorPoints(double A, int b) {
        return (int) (A - (b * 0.5) + 1);
    }
}
