package io.github.maritims.aoc2022;

import java.util.*;
import java.util.stream.Collectors;

public class ThirdPuzzle extends Puzzle {
    public ThirdPuzzle(String fileName) {
        super(fileName);
    }

    protected int getPriority(Character c) {
        return c - (Character.isUpperCase(c) ? 'A' - 27 : 'a' - 1);
    }

    @Override
    public Integer solvePartOne() {
        int sum = 0;
        for (String rucksack : getFileContent()) {
            List<String> compartments = Arrays.asList(rucksack.substring(0, rucksack.length() / 2), rucksack.substring(rucksack.length() / 2));
            HashSet<Integer> priorities = compartments.get(0)
                    .chars()
                    .mapToObj(c -> (char) c)
                    .map(this::getPriority)
                    .collect(Collectors.toCollection(HashSet::new));
            priorities.retainAll(compartments.get(1)
                    .chars()
                    .mapToObj(c -> (char) c)
                    .map(this::getPriority)
                    .collect(Collectors.toCollection(HashSet::new)));
            sum += priorities.stream().mapToInt(i -> i).sum();
        }
        return sum;
    }

    protected List<Character> getItemsInRucksack(String rucksack) {
        return rucksack.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }

    protected List<List<Character>> getItemsInGroup(List<String> group) {
        return group.stream()
                .map(this::getItemsInRucksack)
                .collect(Collectors.toList());
    }

    protected Character getCommonItemInGroup(List<List<Character>> itemsInGroup) {
        Iterator<List<Character>> iterator = itemsInGroup.iterator();
        List<Character> commons = iterator.next();
        while (iterator.hasNext()) {
            commons.retainAll(iterator.next());
        }
        return commons.stream().distinct().collect(Collectors.toList()).get(0);
    }

    @Override
    public Integer solvePartTwo() {
        List<String> fileContent = getFileContent();
        List<List<String>> groups = new ArrayList<>();
        for (int i = 0; i < fileContent.size(); i += 3) {
            groups.add(fileContent.subList(i, i + 3));
        }

        return groups.stream()
                .map(this::getItemsInGroup)
                .map(this::getCommonItemInGroup)
                .mapToInt(this::getPriority)
                .sum();
    }
}