package io.github.maritims.aoc2022;

import java.util.stream.IntStream;

public class TenthPuzzle extends Puzzle<Integer> {
    static class CPU {
        private int X = 1;

        public int getX() {
            return X;
        }

        public void addX(int V) {
            X += V;
        }
    }

    static class CRT {
        private boolean[][] pixels = new boolean[6][40];

        public void draw(int x, int y, boolean isLit) {
            pixels[y][x] = isLit;
        }
    }

    static class ClockCircuit {
        private int cycles = 0;
        private int nextInterestingCycle = 20;
        private int totalSignalStrength = 0;
        private final CPU cpu;
        private final CRT crt;

        public ClockCircuit(CPU cpu, CRT crt) {
            this.cpu = cpu;
            this.crt = crt;
        }

        public int getTotalSignalStrength() {
            return totalSignalStrength;
        }

        private void cycle() {
            cycles++;
            if (cycles == nextInterestingCycle) {
                totalSignalStrength += cycles * cpu.getX();
                nextInterestingCycle += 40;
            }
        }

        public void run(String instruction) {
            if(instruction.startsWith("noop")) {
                cycle();
            } else if(instruction.startsWith("addx")) {
                IntStream.of(0, 2).forEach(i -> cycle());
                cpu.addX(Integer.parseInt(instruction.split(" ")[1]));
            }
        }
    }

    @Override
    public Integer solvePartOne(String filePath) {
        ClockCircuit clockCircuit = new ClockCircuit(new CPU(), new CRT());
        getFileContent(filePath).forEach(clockCircuit::run);
        return clockCircuit.getTotalSignalStrength();
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}
