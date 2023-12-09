package io.github.maritims.aoc2022;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day5 extends Puzzle<String, String> {

    public static final Pattern CRATE_NUMBER_PATTERN = Pattern.compile("\\s+(\\d+)\\s");

    public interface CrateMover {
        int[] getInstructions(String instructions);
        void moveCrates(List<Character> sourceStack, List<Character> destinationStack, int quantity);
        String inspectTop(Map<Integer, List<Character>> stacks);
    }

    static class CrateMover9000 implements CrateMover {
        @Override
        public int[] getInstructions(String instructions) {
            String[] parts = instructions.split(" ");
            return new int[] { Integer.parseInt(parts[1]), Integer.parseInt(parts[3]) - 1, Integer.parseInt(parts[5]) - 1 };
        }

        @Override
        public void moveCrates(List<Character> sourceStack, List<Character> destinationStack, int quantity) {
            Iterator<Character> sourceIterator = sourceStack.iterator();
            for(int i = 0; i < quantity; i++) {
                Character crate = sourceIterator.next();
                sourceIterator.remove();
                destinationStack.add(0, crate);
            }
        }

        @Override
        public String inspectTop(Map<Integer, List<Character>> stacks) {
            return stacks.values()
                    .stream()
                    .map(stack -> stack.isEmpty() ? "" : stack.get(0))
                    .map(Object::toString)
                    .collect(Collectors.joining(""));
        }
    }

    static class CrateMover9001 extends CrateMover9000 implements CrateMover {
        @Override
        public void moveCrates(List<Character> sourceStack, List<Character> destinationStack, int quantity) {
            List<Character> crates = sourceStack.subList(0, quantity);
            destinationStack.addAll(0, crates);
            sourceStack.subList(0, quantity).clear();
        }
    }

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

    public String moveCrates(String filePath, CrateMover crane) {
        Iterator<String> iterator = getFileContent(filePath).iterator();
        Map<Integer, List<Character>> stacks = getCrateStacks(iterator);
        List<String> remainingLines = new ArrayList<>();
        iterator.forEachRemaining(remainingLines::add);

        remainingLines.stream()
                .map(crane::getInstructions)
                .forEach(instructions -> crane.moveCrates(stacks.get(instructions[1]), stacks.get(instructions[2]), instructions[0]));
        return crane.inspectTop(stacks);
    }

    @Override
    public String solvePartOne(String filePath) {
        return moveCrates(filePath, new CrateMover9000());
    }

    @Override
    public String solvePartTwo(String filePath) {
        return moveCrates(filePath, new CrateMover9001());
    }
}
