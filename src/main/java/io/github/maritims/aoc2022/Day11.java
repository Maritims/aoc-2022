package io.github.maritims.aoc2022;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day11 extends Puzzle<Integer, Long> {
    static class Monkey {
        private final LinkedList<Long> items;
        private final Function<Long, Long> operation;
        private final Integer divisor;
        private final Integer targetMonkeyIfTestPasses;
        private final Integer targetMonkeyIfTestFails;
        private Long inspected = 0L;

        public Monkey(LinkedList<Long> items, Function<Long, Long> operation, Integer divisor, Integer targetMonkeyIfTestPasses, Integer targetMonkeyIfTestFails) {
            this.items = items;
            this.operation = operation;
            this.divisor = divisor;
            this.targetMonkeyIfTestPasses = targetMonkeyIfTestPasses;
            this.targetMonkeyIfTestFails = targetMonkeyIfTestFails;
        }

        public LinkedList<Long> getItems() {
            return items;
        }

        public Integer getDivisor() {
            return divisor;
        }

        public Integer getTargetMonkeyIfTestPasses() {
            return targetMonkeyIfTestPasses;
        }

        public Integer getTargetMonkeyIfTestFails() {
            return targetMonkeyIfTestFails;
        }

        public Long getInspected() {
            return inspected;
        }

        public Long inspect(Long item) {
            inspected++;
            return operation.apply(item);
        }

        private static Function<Long, Long> buildOperationFromDefinition(String definition) {
            String[] parts = definition.substring(definition.indexOf('=') + 1).split(" ");
            char operator = parts[2].charAt(0);
            Optional<Long> value = "old".equalsIgnoreCase(parts[3]) ? Optional.empty() : Optional.of(Long.parseLong(parts[3]));
            return worryLevel -> {
                switch(operator) {
                    case '+':
                        return worryLevel + value.orElse(worryLevel);
                    case '-':
                        return worryLevel - value.orElse(worryLevel);
                    case '*':
                        return worryLevel * value.orElse(worryLevel);
                    case '/':
                        return worryLevel / value.orElse(worryLevel);
                }
                throw new UnsupportedOperationException();
            };
        }

        private static LinkedList<Long> buildItemsFromDefinition(String input) {
            String[] parts = input.substring(input.indexOf(':') + 1).split(",");
            return Arrays.stream(parts)
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toCollection(LinkedList::new));
        }

        public static Monkey buildFromDefinition(List<String> monkeyDefinition) {
            LinkedList<Long> items = Monkey.buildItemsFromDefinition(monkeyDefinition.get(1));
            Function<Long, Long> operation = Monkey.buildOperationFromDefinition(monkeyDefinition.get(2));
            Integer divisor = Integer.parseInt(monkeyDefinition.get(3).substring(monkeyDefinition.get(3).indexOf(':') + 1).split(" ")[3]);
            Integer targetMonkeyIfTestPasses = Integer.parseInt(monkeyDefinition.get(4).substring(monkeyDefinition.get(4).indexOf(':') + 1).split(" ")[4]);
            Integer targetMonkeyIfTestFails = Integer.parseInt(monkeyDefinition.get(5).substring(monkeyDefinition.get(4).indexOf(':') + 1).split(" ")[4]);
            return new Monkey(items, operation, divisor, targetMonkeyIfTestPasses, targetMonkeyIfTestFails);
        }
    }

    private static void playRound(LinkedList<Monkey> monkeys, Function<Long, Long> manageWorryLevel) {
        for(Monkey monkey : monkeys) {
            Iterator<Long> iterator = monkey.getItems().iterator();
            while(iterator.hasNext()) {
                Long item = iterator.next();

                // monkey inspects item
                item = monkey.inspect(item);

                // you're relieved the monkey didn't break it -> your worry level decreases
                item = manageWorryLevel.apply(item);

                // monkey tests your worry level
                int targetMonkey = (item % monkey.getDivisor()) == 0 ? monkey.getTargetMonkeyIfTestPasses() : monkey.getTargetMonkeyIfTestFails();

                // monkey throws item based on test result
                monkeys.get(targetMonkey)
                        .getItems()
                        .add(item);
                iterator.remove();
            }
        }
    }

    public Integer solvePartOne(String filePath) {
        LinkedList<Monkey> monkeys = splitListToLists(getFileContent(filePath)).stream()
                .map(Monkey::buildFromDefinition)
                .collect(Collectors.toCollection(LinkedList::new));
        IntStream.range(0, 20).forEach(round -> playRound(monkeys, item -> (long) Math.floor((double) item / 3)));
        return monkeys.stream()
                .map(Monkey::getInspected)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce((o1, o2) -> o1 * o2)
                .map(Long::intValue)
                .orElse(0);
    }

    @Override
    public Long solvePartTwo(String filePath) {
        LinkedList<Monkey> monkeys = splitListToLists(getFileContent(filePath)).stream()
                .map(Monkey::buildFromDefinition)
                .collect(Collectors.toCollection(LinkedList::new));
        int divisor = monkeys.stream().map(Monkey::getDivisor).reduce((d1, d2) -> d1 * d2).orElse(0);
        for (int round = 0; round < 10000; round++) {
            playRound(monkeys, (item) -> item % divisor);
        }
        return monkeys.stream()
                .map(Monkey::getInspected)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce((o1, o2) -> o1 * o2)
                .orElse(0L);
    }
}
