package io.github.maritims.advent_of_code.common.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    @Test
    void getEuclideanDistance() {
        var p1 = new Point3D(162, 817, 812);
        var p2 = new Point3D(425, 690, 689);
        assertEquals(316.90219311326956, p1.getEuclideanDistance(p2));
    }
}