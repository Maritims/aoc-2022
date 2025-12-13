package io.github.maritims.advent_of_code.year_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {
    @Test
    void find() {
        // Alice: 0
        // Bob: 1
        // Carol: 2
        // David 3
        var happiness = new int[][]{
                new int[]{0, 54, -79, -2},   // Alice -> Alice (0), Bob (54), Carol (-79), David (-2)
                new int[]{83, 0, -7, -63},   // Bob -> Alice (83), Bob (0), Carol (-7), David (-63)
                new int[]{-62, 60, 0, 55},   // Carol -> Alice (-62), Bob (60), Carol (0), David (55)
                new int[]{46, -7, 41, 0}     // David -> Alice (46), Bob (-7), Carol (41), David (0)
        };
        var result = -new Day13().findChangeInHappiness(happiness);
        assertEquals(330, result);
    }
}