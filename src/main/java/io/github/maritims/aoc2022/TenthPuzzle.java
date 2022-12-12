package io.github.maritims.aoc2022;

import java.util.stream.IntStream;

public class TenthPuzzle extends Puzzle<Integer> {
    static class CPU {
        private int cycles = 0;
        private int X = 1;
        private int nextInterestingCycle = 20;
        private int totalSignalStrength = 0;

        public int getX() {
            return X;
        }

        public void cycle() {
            cycles++;
            if (cycles == nextInterestingCycle) {
                totalSignalStrength += cycles * X;
                nextInterestingCycle += 40;
            }
        }

        public void addX(int V) {
            X += V;
        }

        public int getTotalSignalStrength() {
            return totalSignalStrength;
        }
    }

    @Override
    public Integer solvePartOne(String filePath) {
        CPU cpu = new CPU();
        for(String instruction : getFileContent(filePath)) {
            if(instruction.startsWith("noop")) {
                cpu.cycle();
            } else if(instruction.startsWith("addx")) {
                IntStream.of(0, 2).forEach(i -> cpu.cycle());
                cpu.addX(Integer.parseInt(instruction.split(" ")[1]));
            }
        }
        return cpu.getTotalSignalStrength();
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}
