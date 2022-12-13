package io.github.maritims.aoc2022;

import java.util.stream.IntStream;

public class TenthPuzzle extends Puzzle<Integer, String> {
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
        private final boolean[][] pixels;

        public CRT(int width, int height) {
            pixels = new boolean[height][width];
        }

        public boolean[][] getPixels() {
            return pixels;
        }

        public void draw(int x, int y) {
            pixels[y][x] = true;
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
            if(crt != null) {
                int pixelX = cycles % 40;
                int pixelY = cycles / 40;
                if(pixelX == cpu.getX() || pixelX == cpu.getX() - 1 || pixelX == cpu.getX() + 1) {
                    crt.draw(pixelX, pixelY);
                }
            }

            cycles++;

            if (cycles == nextInterestingCycle) {
                totalSignalStrength += cycles * cpu.getX();
                nextInterestingCycle += 40;
            }
        }

        public void tick(String instruction) {
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
        ClockCircuit clockCircuit = new ClockCircuit(new CPU(), null);
        getFileContent(filePath).forEach(clockCircuit::tick);
        return clockCircuit.getTotalSignalStrength();
    }

    @Override
    public String solvePartTwo(String filePath) {
        CRT crt = new CRT(40, 6);
        ClockCircuit clockCircuit = new ClockCircuit(new CPU(), crt);
        getFileContent(filePath).forEach(clockCircuit::tick);

        StringBuilder sb = new StringBuilder();
        boolean[][] pixels = crt.getPixels();
        for(int i = 0; i < pixels.length; i++) {
            for(boolean pixel : pixels[i]) {
                sb.append(pixel ? "#" : ".");
            }
            if(i < pixels.length - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
        return sb.toString();
    }
}
