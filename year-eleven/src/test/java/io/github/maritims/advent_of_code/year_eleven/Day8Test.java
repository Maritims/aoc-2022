package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day8Test {
    @Test
    void solveFirstPart_withSampleInput() {
        var sut = spy(new Day8(10));
        doReturn(Arrays.stream("""
                162,817,812
                57,618,57
                906,360,560
                592,479,940
                352,342,300
                466,668,158
                542,29,236
                431,825,988
                739,650,466
                52,470,668
                216,146,977
                819,987,18
                117,168,530
                805,96,715
                346,949,466
                970,615,88
                941,993,340
                862,61,35
                984,92,344
                425,690,689
                """.split("\n")).collect(Collectors.toCollection(ArrayList::new))).when(sut).loadInput();
        assertEquals(40, sut.solveFirstPart());
    }

    @Test
    public void solveFirstPart() {
        var sut = new Day8(1000);
        assertEquals(1024, sut.solveFirstPart());
    }

    @Test
    void solveSecondPart() {
    }
}