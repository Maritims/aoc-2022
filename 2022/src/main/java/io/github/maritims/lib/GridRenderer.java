package io.github.maritims.lib;

import java.io.IOException;
import java.io.Writer;
import java.util.function.BiConsumer;

public class GridRenderer<T> {
    public void render(T[][] grid, Writer writer, BiConsumer<T, Writer> writeAction) throws IOException {
        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[row].length; col++) {
                T element = grid[row][col];
                writeAction.accept(element, writer);
            }
            if(row < grid.length - 1) {
                writer.write("\n");
            }
        }
    }
}
