package io.github.maritims.aoc2022;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

public class Day13 extends Puzzle<Integer, Integer> {
    static class Tuple2<T1, T2> {
        private final T1 item1;
        private final T2 item2;

        Tuple2(T1 item1, T2 item2) {
            this.item1 = item1;
            this.item2 = item2;
        }

        public T1 getItem1() {
            return item1;
        }

        public T2 getItem2() {
            return item2;
        }
    }

    public Tuple2<List<Object>, Integer> parse(String line, int startIndex) {
        List<Object> node = new LinkedList<>();
        StringBuilder current = new StringBuilder();

        while(startIndex < line.length() - 1) {
            char c = line.charAt(startIndex);
            if(c == '[') {
                // Encountered node opening bracket.
                // Use tuple as we need to update the startIndex with the resulting startIndex after the recursive call to parse(String, int);
                Tuple2<List<Object>, Integer> tuple = parse(line, startIndex + 1);
                startIndex = tuple.getItem2();
                node.add(tuple.getItem1());
                continue;
            }

            if(c == ']') {
                // Encountered node closing bracket.
                // Add current value to node if not empty. Forget resetting it since we're returning from this method call right after this check.
                if(current.length() > 0) {
                    node.add(Integer.parseInt(current.toString()));
                }
                // Return tuple as we need to return the resulting startIndex + 1, otherwise we'll just end up encountering this very same closing bracket again and end the parsing prematurely.
                return new Tuple2<>(node, startIndex + 1);
            }

            if(c == ',') {
                // Encountered node value separator.
                // add current value to node if not empty, then reset the current value.
                if(current.length() > 0) {
                    node.add(Integer.parseInt(current.toString()));
                    current.setLength(0);
                }
                startIndex++;
                continue;
            }

            current.append(c);
            startIndex++;
        }

        // Add whatever is in the current value to the node. Forget about resetting it since we're returning from this method call right after this check.
        if(current.length() > 0) {
            node.add(Integer.parseInt(current.toString()));
        }

        return new Tuple2<>(node, startIndex);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    public Optional<Integer> isInRightOrder(List<Object> leftList, List<Object> rightList) {
        int sum = 0;
        System.out.println(" - Compare " + leftList + " vs " + rightList);

        for(int i = 0; i < leftList.size(); i++) {
            if(sum != 0) {
                return Optional.of(sum);
            }

            if(i >= rightList.size()) {
                System.out.println("     - Right side ran out of items, so inputs are not in the right order");
                sum += 1;
                break;
            }

            Object leftObject = leftList.get(i);
            Object rightObject = rightList.get(i);

            if(leftObject instanceof Integer && rightObject instanceof List) {
                System.out.println("   - Mixed types; convert left and retry comparison");
                sum += isInRightOrder(singletonList(leftObject), (List<Object>) rightObject).orElse(0);
            } else if(leftObject instanceof List && rightObject instanceof Integer) {
                System.out.println("   - Mixed types; convert right and retry comparison");
                sum += isInRightOrder((List<Object>) leftObject, singletonList(rightObject)).orElse(0);
            } else if(leftObject instanceof List && rightObject instanceof List) {
                sum += isInRightOrder((List<Object>) leftObject, (List<Object>) rightObject).orElse(0);
            } else {
                Integer leftNumber = (Integer) leftObject;
                Integer rightNumber = (Integer) rightObject;
                System.out.println("   - Compare " + leftNumber + " vs " + rightNumber);

                int comparison = leftNumber.compareTo(rightNumber);
                sum += comparison;

                if(comparison < 0) {
                    System.out.println("     - Left side is smaller, so inputs are in the right order");
                    break;
                } else if(comparison > 0) {
                    System.out.println("     - Right side is smaller, so inputs are not in the right order");
                    break;
                }
            }
        }

        if(sum == 0 && leftList.size() < rightList.size()) {
            System.out.println("     - Left side ran out of items, so inputs are in the right order");
            sum += -1;
        }

        return Optional.of(sum);
    }

    @Override
    public Integer solvePartOne(String filePath) {
        List<List<String>> pairs = splitListToLists(getFileContent(filePath));
        int i = 0;
        int sum = 0;
        for(List<String> pair : pairs) {
            i++;
            List<Object> left = parse(pair.get(0), 1).getItem1();
            List<Object> right = parse(pair.get(1), 1).getItem1();
            sum += isInRightOrder(left, right).orElse(0) == -1 ? i : 0;
        }
        return sum;
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}
