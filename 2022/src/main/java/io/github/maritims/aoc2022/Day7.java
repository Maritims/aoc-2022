package io.github.maritims.aoc2022;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day7 extends Puzzle<Integer, Integer> {
    static class FileStructure {
        private final String name;
        private final Integer size;
        private final String type;
        private FileStructure parent;
        private final LinkedHashSet<FileStructure> children = new LinkedHashSet<>();

        private FileStructure(String name, Integer size, String type) {
            this.name = name;
            this.size = size;
            this.type = type;
        }

        public Integer getSize() {
            int size = 0;
            size += Objects.requireNonNullElseGet(this.size, () -> children.stream().mapToInt(FileStructure::getSize).sum());
            return size;
        }

        public FileStructure addDirectory(String name) {
            if(this.children.stream().anyMatch(child -> name.equalsIgnoreCase(child.name))) {
                throw new RuntimeException("Directory already exists");
            }

            FileStructure directory = new FileStructure(name, null, "directory");
            directory.parent = this;
            children.add(directory);
            return directory;
        }

        public void addFile(String name, int size) {
            if(this.children.stream().anyMatch(child -> name.equalsIgnoreCase(child.name))) {
                throw new RuntimeException("File already exists");
            }

            FileStructure file = new FileStructure(name, size, "file");
            file.parent = this;
            children.add(file);
        }

        @Override
        public String toString() {
            return name;
        }

        public static FileStructure cd(FileStructure fileStructure, String name) {
            return "..".equalsIgnoreCase(name) ? fileStructure.parent : fileStructure.children.stream()
                    .filter(child -> name.equalsIgnoreCase(child.name))
                    .findFirst()
                    .orElseGet(() -> fileStructure.addDirectory(name));

        }

        public static Stream<FileStructure> getDirsThroughoutStructure(LinkedHashSet<FileStructure> fileStructures) {
            return fileStructures.stream()
                    .filter(fileStructure -> "directory".equalsIgnoreCase(fileStructure.type))
                    .flatMap(fileStructure -> "/".equalsIgnoreCase(fileStructure.name) ? getDirsThroughoutStructure(fileStructure.children) : Stream.concat(Stream.of(fileStructure), getDirsThroughoutStructure(fileStructure.children)));
        }

        @SuppressWarnings("StatementWithEmptyBody")
        public static FileStructure build(List<String> lines) {
            FileStructure root = new FileStructure("/", null, "directory");
            FileStructure cwd = null;

            for(String line : lines) {
                String[] parts = line.split(" ");
                if("$".equalsIgnoreCase(parts[0])) {
                    if("cd".equalsIgnoreCase(parts[1])) {
                        String arg = parts[2];
                        cwd = FileStructure.cd(cwd == null ? root : cwd, arg);
                    }
                } else if("dir".equalsIgnoreCase(parts[0])) {
                    // no-op
                } else {
                    // file entry
                    int fileSize = Integer.parseInt(parts[0]);
                    String fileName = parts[1];
                    cwd.addFile(fileName, fileSize);
                }
            }

            return root;
        }
    }

    @Override
    public Integer solvePartOne(String filePath) {
        FileStructure root = FileStructure.build(getFileContent(filePath));
        LinkedHashSet<FileStructure> hashSet = new LinkedHashSet<>();
        hashSet.add(root);
        return FileStructure.getDirsThroughoutStructure(hashSet)
                .mapToInt(FileStructure::getSize)
                .filter(size -> size > 0 && size <= 100000)
                .sum();
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        FileStructure root = FileStructure.build(getFileContent(filePath));
        LinkedHashSet<FileStructure> hashSet = new LinkedHashSet<>();
        hashSet.add(root);
        LinkedHashSet<FileStructure> dirs = FileStructure.getDirsThroughoutStructure(hashSet).collect(Collectors.toCollection(LinkedHashSet::new));
        int unusedSpace = 70000000 - root.getSize();
        int requiredSpace = 30000000;
        int smallestSizeToDelete = 0;
        for(FileStructure dir : dirs) {
            int size = dir.getSize();
            if(unusedSpace + size >= requiredSpace && (smallestSizeToDelete == 0 || smallestSizeToDelete > size)) {
                smallestSizeToDelete = size;
            }
        }
        return smallestSizeToDelete;
    }
}
