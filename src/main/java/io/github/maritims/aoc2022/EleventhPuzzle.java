package io.github.maritims.aoc2022;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class EleventhPuzzle extends Puzzle<Integer, Integer> {
    @FunctionalInterface
    interface Inspection {
        Integer increaseWorryLevel(Integer old);
    }

    static class Monkey {
        private final LinkedList<Integer> items;
        private final Inspection inspection;
        private final Function<Integer, Boolean> test;
        private final Supplier<Integer> targetMonkeyWhenPassingTest;
        private final Supplier<Integer> targetMonkeyWhenFailingTest;
        private int inspected = 0;

        public Monkey(
                LinkedList<Integer> items,
                Inspection inspection,
                Function<Integer, Boolean> test,
                Supplier<Integer> targetMonkeySupplierForPassingTest,
                Supplier<Integer> targetMonkeySupplierForFailingTest
        ) {
            this.items = items;
            this.inspection = inspection;
            this.test = test;
            this.targetMonkeyWhenPassingTest = targetMonkeySupplierForPassingTest;
            this.targetMonkeyWhenFailingTest = targetMonkeySupplierForFailingTest;
        }

        public LinkedList<Integer> getItems() {
            return items;
        }

        public Monkey inspect(Integer item) {
            inspection.increaseWorryLevel(item);
            System.out.println("\t\tWorry level is multiplied to " + item);
            inspected++;
            return this;
        }

        public Monkey relieve(Integer item) {
            int worryLevel = (int) Math.floor((double) item / 3);
            System.out.println("\t\tMonkey gets bored with item. Worry level is divided by 3 to " + worryLevel);
            return this;
        }

        public Integer getTargetMonkey(Integer item) {
            int targetMonkey;
            if(test.apply(item)) {
                targetMonkey = targetMonkeyWhenPassingTest.get();
                System.out.println("\t\tTest passed.\n\t\tItem with worry level " + item + " is thrown to monkey " + targetMonkey);
            } else {
                targetMonkey = targetMonkeyWhenFailingTest.get();
                System.out.println("\t\tTest failed.\n\t\tItem with worry level " + item + " is thrown to monkey " + targetMonkey);
            }
            return targetMonkey;
        }

        public int getInspected() {
            return inspected;
        }
    }

    LinkedList<Integer> getItems(String input) {
        int start = input.indexOf(':') + 1;
        String[] parts = input.substring(start).split(",");
        return Arrays.stream(parts)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    Inspection getInspection(String input) {
        int start = input.indexOf('=') + 1;
        String[] parts = input.substring(start).split(" ");
        return old -> {
            char operator = parts[2].charAt(0);
            Integer left = "old".equalsIgnoreCase(parts[1]) ? old : Integer.parseInt(parts[1]);
            Integer right = "old".equalsIgnoreCase(parts[3]) ? old : Integer.parseInt(parts[3]);
            switch (operator) {
                case '+':
                    return left + right;
                case '-':
                    return left - right;
                case '/':
                    return left / right;
                case '*':
                    return left * right;
            }
            throw new UnsupportedOperationException();
        };
    }

    Function<Integer, Boolean> getTest(String input) {
        int start = input.indexOf(':') + 1;
        int divisor = Integer.parseInt(input.trim().substring(start).split(" ")[2]);
        return item -> item % divisor == 0;
    }

    Supplier<Integer> getTargetMonkey(String input) {
        return () -> Integer.parseInt(input.substring(input.length() - 1));
    }

    @Override
    public Integer solvePartOne(String filePath) {
        List<List<String>> monkeyDefinitions = splitListToLists(getFileContent(filePath));
        LinkedList<Monkey> monkeys = monkeyDefinitions.stream().map(list -> {
            LinkedList<Integer> items = getItems(list.get(1));
            Inspection inspection = getInspection(list.get(2));
            Function<Integer, Boolean> test = getTest(list.get(3));
            Supplier<Integer> targetMonkeyForPassingTest = getTargetMonkey(list.get(4));
            Supplier<Integer> targetMonkeyForFailingTest = getTargetMonkey(list.get(5));
            return new Monkey(items, inspection, test, targetMonkeyForPassingTest, targetMonkeyForFailingTest);
        }).collect(Collectors.toCollection(LinkedList::new));

        for(int round = 0; round < 20; round++) {
            for(int i = 0; i < monkeys.size(); i++) {
                System.out.println("Monkey " + i);
                Monkey monkey = monkeys.get(i);

                Iterator<Integer> itemIterator = monkey.getItems().iterator();
                while(itemIterator.hasNext()) {
                    Integer item = itemIterator.next();
                    System.out.println("\tMonkey inspects an item with a worry level of " + item);
                    int targetMonkey = monkey.inspect(item).relieve(item).getTargetMonkey(item);
                    monkeys.get(targetMonkey).getItems().add(item);
                    itemIterator.remove();
                }
            }
        }
        return monkeys.stream()
                .map(Monkey::getInspected)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce((i1, i2) -> i1 * i2)
                .orElse(0);
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}
