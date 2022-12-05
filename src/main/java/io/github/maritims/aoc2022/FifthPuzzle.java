package io.github.maritims.aoc2022;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FifthPuzzle extends Puzzle<String> {
    @Override
    public String solvePartOne(String filePath) {
        List<String> lines = getFileContent(filePath);
        Map<Integer, List<Character>> crateStacks = new HashMap<>();
        Pattern pattern = Pattern.compile("\\s+(\\d+)\\s");

        Iterator<String> iterator = lines.iterator();
        while(iterator.hasNext()) {
            String line = iterator.next();

            Matcher matcher = pattern.matcher(line);
            if(matcher.find()) {
                iterator.remove();
                iterator.next();
                iterator.remove();
                break;
            }

            int stack = 0;
            for (int j = 1; j < line.length(); j += 4) {
                char crate = line.charAt(j);
                if(crate != 32) {
                    crateStacks.computeIfAbsent(stack, crates -> new ArrayList<>()).add(crate);
                }
                stack++;
            }
            iterator.remove();
        }

        while(iterator.hasNext()) {
            String line = iterator.next();
            String[] instructions = line.split(" ");
            int crates = Integer.parseInt(instructions[1]);
            int source = Integer.parseInt(instructions[3]) - 1;
            int destination = Integer.parseInt(instructions[5]) - 1;

            Iterator<Character> sourceIterator = crateStacks.get(source).iterator();
            int i = 0;
            Character crate;
            while(i < crates) {
                // Pick up the crate
                crate = sourceIterator.next();
                sourceIterator.remove();

                // Put the crate down
                crateStacks.get(destination).add(0, crate);

                // Go to the next crate
                i++;
            }
            iterator.remove();
        }
        return crateStacks.values()
                .stream()
                .map(stack -> stack.size() == 0 ? "" : stack.get(0))
                .map(Object::toString)
                .collect(Collectors.joining(""));
    }

    @Override
    public String solvePartTwo(String filePath) {
        return null;
    }
}
