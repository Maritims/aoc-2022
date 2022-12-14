package io.github.maritims.aoc2022;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EleventhPuzzle extends Puzzle<Integer, Integer> {
    @FunctionalInterface
    interface TestWorryLevel {
        boolean execute(Integer left, Integer right, char operator);
    }

    static class Monkey {
        private final LinkedList<Integer> items;
        private final Function<Integer, Integer> operation;
        private final Integer divisor;
        private final Integer targetMonkeyIfTestPasses;
        private final Integer targetMonkeyIfTestFails;
        private Integer inspected = 0;

        public Monkey(LinkedList<Integer> items, Function<Integer, Integer> operation, Integer divisor, Integer targetMonkeyIfTestPasses, Integer targetMonkeyIfTestFails) {
            this.items = items;
            this.operation = operation;
            this.divisor = divisor;
            this.targetMonkeyIfTestPasses = targetMonkeyIfTestPasses;
            this.targetMonkeyIfTestFails = targetMonkeyIfTestFails;
        }

        public LinkedList<Integer> getItems() {
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

        public Integer getInspected() {
            return inspected;
        }

        public Integer inspect(Integer item) {
            inspected++;
            return operation.apply(item);
        }

        private static Function<Integer, Integer> buildOperationFromDefinition(String definition) {
            String[] parts = definition.substring(definition.indexOf('=') + 1).split(" ");
            char operator = parts[2].charAt(0);
            Optional<Integer> value = "old".equalsIgnoreCase(parts[3]) ? Optional.empty() : Optional.of(Integer.parseInt(parts[3]));
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

        private static LinkedList<Integer> buildItemsFromDefinition(String input) {
            String[] parts = input.substring(input.indexOf(':') + 1).split(",");
            return Arrays.stream(parts)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(LinkedList::new));
        }

        public static Monkey buildFromDefinition(List<String> monkeyDefinition) {
            LinkedList<Integer> items = Monkey.buildItemsFromDefinition(monkeyDefinition.get(1));
            Function<Integer, Integer> operation = Monkey.buildOperationFromDefinition(monkeyDefinition.get(2));
            Integer divisor = Integer.parseInt(monkeyDefinition.get(3).substring(monkeyDefinition.get(3).indexOf(':') + 1).split(" ")[3]);
            Integer targetMonkeyIfTestPasses = Integer.parseInt(monkeyDefinition.get(4).substring(monkeyDefinition.get(4).indexOf(':') + 1).split(" ")[4]);
            Integer targetMonkeyIfTestFails = Integer.parseInt(monkeyDefinition.get(5).substring(monkeyDefinition.get(4).indexOf(':') + 1).split(" ")[4]);
            return new Monkey(items, operation, divisor, targetMonkeyIfTestPasses, targetMonkeyIfTestFails);
        }
    }

    private static void playRound(LinkedList<Monkey> monkeys) {
        for(Monkey monkey : monkeys) {
            Iterator<Integer> iterator = monkey.getItems().iterator();
            while(iterator.hasNext()) {
                Integer item = iterator.next();

                // monkey inspects item
                item = monkey.inspect(item);

                // you're relieved the monkey didn't break it -> your worry level decreases
                item = (int) Math.floor((double) item / 3);

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
        IntStream.range(0, 20)
                .mapToObj(round -> monkeys)
                .forEach(EleventhPuzzle::playRound);
        return monkeys.stream()
                .map(Monkey::getInspected)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce((o1, o2) -> o1 * o2)
                .orElse(0);
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}
