package io.github.maritims.aoc2015.day6;

public class LightGrid {
    private final int[][] grid;
    private final boolean isDimmable;

    public LightGrid(int columns, int rows, boolean isDimmable) {
        grid = new int[columns][rows];
        this.isDimmable = isDimmable;
    }

    public void executeInstruction(LightInstruction lightInstruction) {
        for(var column = lightInstruction.getStartColumn(); column <= lightInstruction.getEndColumn(); column++) {
            for(var row = lightInstruction.getStartRow(); row <= lightInstruction.getEndRow(); row++) {
                if(lightInstruction.getCommand() == LightInstructionCommand.TurnOn) {
                    grid[column][row] = isDimmable ? grid[column][row] + 1 : 1;
                } else if(lightInstruction.getCommand() == LightInstructionCommand.TurnOff) {
                    grid[column][row] = isDimmable ? Math.max(0, grid[column][row] - 1) : 0;
                } else if(lightInstruction.getCommand() == LightInstructionCommand.Toggle) {
                    grid[column][row] = isDimmable ? grid[column][row] + 2 : (grid[column][row] == 0 ? 1 : 0);
                }
            }
        }
    }

    public int getPoweredLights() {
        var poweredLights = 0;

        for(var column = 0; column < grid.length; column++) {
            for(var row = 0; row < grid[column].length; row++) {
                poweredLights += grid[column][row] > 0 ? 1 : 0;
            }
        }

        return poweredLights;
    }

    public int getBrightness() {
        var brightness = 0;

        for(var column = 0; column < grid.length; column++) {
            for(var row = 0; row < grid[column].length; row++) {
                brightness += isDimmable ? grid[column][row] : (grid[column][row] > 0 ? 1 : 0);
            }
        }

        return brightness;
    }
}
