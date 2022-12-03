package io.github.maritims.aoc2022;

import java.util.ArrayList;
import java.util.List;

public class ThirdPuzzle extends Puzzle {
    public ThirdPuzzle(String fileName) {
        super(fileName);
    }

    @Override
    public Integer solvePartOne() {
        int sum = 0;
        for (String rucksack : getFileContent()) {
            String[] compartments = new String[]{rucksack.substring(0, rucksack.length() / 2), rucksack.substring(rucksack.length() / 2)};
            List<Character> sharedItems = new ArrayList<>();
            for(char item1 : compartments[0].toCharArray()) {
                if(sharedItems.contains(item1)) {
                    continue;
                }
                for(char item2 : compartments[1].toCharArray()) {
                    if(item1 == item2) {
                        sharedItems.add(item1);
                        break;
                    }
                }
            }
            sum += sharedItems.stream().mapToInt(c -> c - (Character.isUpperCase(c) ? 'A' - 27 : 'a' - 1)).sum();
        }
        return sum;
    }

    @Override
    public Integer solvePartTwo() {
        return null;
    }
}