package io.github.maritims.aoc2022;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FifthPuzzle extends Puzzle<String> {

    public static final Pattern CRATE_NUMBER_PATTERN = Pattern.compile("\\s+(\\d+)\\s");

    public Map<Integer, List<Character>> getCrateStacks(Iterator<String> iterator) {
        Map<Integer, List<Character>> crateStacks = new HashMap<>();

        while(iterator.hasNext()) {
            String line = iterator.next();

            Matcher matcher = CRATE_NUMBER_PATTERN.matcher(line);
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

        return crateStacks;
    }

    @Override
    public String solvePartOne(String filePath) {
        Iterator<String> iterator = getFileContent(filePath).iterator();
        Map<Integer, List<Character>> crateStacks = getCrateStacks(iterator);

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
        Iterator<String> iterator = getFileContent(filePath).iterator();
        Map<Integer, List<Character>> crateStacks = getCrateStacks(iterator);

        while(iterator.hasNext()) {
            String line = iterator.next();
            String[] instructions = line.split(" ");
            int quantity = Integer.parseInt(instructions[1]);
            int source = Integer.parseInt(instructions[3]) - 1;
            int destination = Integer.parseInt(instructions[5]) - 1;

            List<Character> crates = crateStacks.get(source).subList(0, quantity);
            crateStacks.get(destination).addAll(0, crates);
            crateStacks.get(source).subList(0, quantity).clear();

            iterator.remove();
        }
        return crateStacks.values()
                .stream()
                .map(stack -> stack.size() == 0 ? "" : stack.get(0))
                .map(Object::toString)
                .collect(Collectors.joining(""));
    }
}
