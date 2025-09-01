package io.github.maritims.advent_of_code.year_one.day6;

import java.util.regex.MatchResult;

public class LightInstruction {
    private final LightInstructionCommand command;
    private final int    startColumn;
    private final int    startRow;
    private final int    endColumn;
    private final int    endRow;

    public static LightInstruction fromMatchResult(MatchResult matchResult) {
        var command = matchResult.group(1);

        var start       = matchResult.group(3).split(",");
        var startColumn = Integer.parseInt(start[0]);
        var startRow    = Integer.parseInt(start[1]);

        var end       = matchResult.group(4).split(",");
        var endColumn = Integer.parseInt(end[0]);
        var endRow    = Integer.parseInt(end[1]);

        return new LightInstruction(command, startColumn, startRow, endColumn, endRow);
    }

    public LightInstruction(String command, int startColumn, int startRow, int endColumn, int endRow) {
        this.command     = LightInstructionCommand.fromString(command).orElseThrow();
        this.startColumn = startColumn;
        this.startRow    = startRow;
        this.endColumn   = endColumn;
        this.endRow      = endRow;
    }

    public LightInstructionCommand getCommand() {
        return command;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public int getEndRow() {
        return endRow;
    }
}