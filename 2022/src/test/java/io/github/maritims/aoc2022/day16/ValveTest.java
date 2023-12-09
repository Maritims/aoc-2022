package io.github.maritims.aoc2022.day16;

import org.junit.jupiter.api.Test;

import java.util.List;

class ValveTest {
    @Test
    public void getFromFile() {
        List<Valve> valves = Valve.getFromFile("16/example.txt");
        valves.forEach(System.out::println);
    }
}