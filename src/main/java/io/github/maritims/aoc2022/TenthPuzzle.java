package io.github.maritims.aoc2022;

import java.util.stream.IntStream;

public class TenthPuzzle extends Puzzle<Integer> {
    static class CPU {
        private int cycles = 0;
        private int X = 1;
        private int nextInterestingCycle = 20;
        private int totalSignalStrength = 0;

        public int getTotalSignalStrength() {
            return totalSignalStrength;
        }

        private void cycle() {
            cycles++;
            if (cycles == nextInterestingCycle) {
                totalSignalStrength += cycles * X;
                nextInterestingCycle += 40;
            }
        }

        public void run(String instruction) {
            if(instruction.startsWith("noop")) {
                cycle();
            } else if(instruction.startsWith("addx")) {
                IntStream.of(0, 2).forEach(i -> cycle());
                X += Integer.parseInt(instruction.split(" ")[1]);
            }
        }
    }

    @Override
    public Integer solvePartOne(String filePath) {
        CPU cpu = new CPU();
        getFileContent(filePath).forEach(cpu::run);
        return cpu.getTotalSignalStrength();
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}
