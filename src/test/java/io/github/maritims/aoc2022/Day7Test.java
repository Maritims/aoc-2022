package io.github.maritims.aoc2022;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.maritims.aoc2022.Day7.FileStructure;
import static io.github.maritims.lib.FileHelper.getFileContent;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Test extends PuzzleTest<Integer, Integer, Day7> {
    public Day7Test() {
        super(Day7.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("seventh/example.txt", 95437),
                Arguments.arguments("seventh/input.txt", 1428881)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return Stream.of(
                Arguments.arguments("seventh/example.txt", 24933642),
                Arguments.arguments("seventh/input.txt", 10475598)
        );
    }

    @Test
    public void getDirs() {
        List<String> lines = getFileContent("seventh/example.txt");
        FileStructure fileStructure = FileStructure.build(lines);
        LinkedHashSet<FileStructure> hashSet = new LinkedHashSet<>();
        hashSet.add(fileStructure);
        List<FileStructure> dirs = FileStructure.getDirsThroughoutStructure(hashSet).collect(Collectors.toList());
        assertEquals(3, dirs.size());
    }
}
