package io.github.maritims.aoc2022;

import java.util.*;
import java.util.stream.Collectors;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day3 extends Puzzle<Integer, Integer> {
    protected List<String> getCompartments(String rucksack) {
        return Arrays.asList(rucksack.substring(0, rucksack.length() / 2), rucksack.substring(rucksack.length() / 2));
    }

    protected int getPriority(Character item) {
        return item - (Character.isUpperCase(item) ? 'A' - 27 : 'a' - 1);
    }

    protected HashSet<Integer> getPriorities(String compartment) {
        return compartment
                .chars()
                .mapToObj(c -> (char) c)
                .map(this::getPriority)
                .collect(Collectors.toCollection(HashSet::new));
    }

    protected Integer getPriority(List<String> compartments) {
        HashSet<Integer> priorities = getPriorities(compartments.get(0));
        priorities.retainAll(getPriorities(compartments.get(1)));
        return priorities.stream().mapToInt(i -> i).sum();
    }

    @Override
    public Integer solvePartOne(String filePath) {
        return getFileContent(filePath).stream()
                .map(this::getCompartments)
                .mapToInt(this::getPriority)
                .sum();
    }

    protected HashSet<Character> getItemsInRucksack(String rucksack) {
        return rucksack.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toCollection(HashSet::new));
    }

    protected List<HashSet<Character>> getItemsInGroup(List<String> group) {
        return group.stream()
                .map(this::getItemsInRucksack)
                .collect(Collectors.toList());
    }

    protected Character getCommonItemInGroup(List<HashSet<Character>> itemsInGroup) {
        Iterator<HashSet<Character>> iterator = itemsInGroup.iterator();
        HashSet<Character> commons = iterator.next();
        while (iterator.hasNext()) {
            commons.retainAll(iterator.next());
        }
        return commons.iterator().next();
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        List<String> fileContent = getFileContent(filePath);
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