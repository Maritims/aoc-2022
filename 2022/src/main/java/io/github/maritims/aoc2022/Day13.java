package io.github.maritims.aoc2022;

import io.github.maritims.lib.Tuple2;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.maritims.lib.FileHelper.getFileContent;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class Day13 extends Puzzle<Integer, Integer> {
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

    private List<List<Object>> parse(List<String> pair) {
        return asList(
                parse(pair.get(0), 1).getItem1(),
                parse(pair.get(1), 1).getItem1()
        );
    }

    public final Comparator<List<Object>> COMPARATOR = new Comparator<>() {
        @Override
        public int compare(List<Object> leftList, List<Object> rightList) {
            int sum = 0;
            log(" - Compare " + leftList + " vs " + rightList);

            for (int i = 0; i < leftList.size(); i++) {
                if (sum != 0) {
                    break;
                }

                if (i >= rightList.size()) {
                    log("     - Right side ran out of items, so inputs are not in the right order");
                    sum += 1;
                    break;
                }

                Object leftObject  = leftList.get(i);
                Object rightObject = rightList.get(i);

                if (leftObject instanceof Integer && rightObject instanceof List) {
                    log("   - Mixed types; convert left and retry comparison");
                    sum += compare(singletonList(leftObject), (List<Object>) rightObject);
                } else if (leftObject instanceof List && rightObject instanceof Integer) {
                    log("   - Mixed types; convert right and retry comparison");
                    sum += compare((List<Object>) leftObject, singletonList(rightObject));
                } else if (leftObject instanceof List && rightObject instanceof List) {
                    sum += compare((List<Object>) leftObject, (List<Object>) rightObject);
                } else {
                    Integer leftNumber  = (Integer) leftObject;
                    Integer rightNumber = (Integer) rightObject;
                    log("   - Compare " + leftNumber + " vs " + rightNumber);

                    int comparison = leftNumber.compareTo(rightNumber);
                    sum += comparison;

                    if (comparison < 0) {
                        log("     - Left side is smaller, so inputs are in the right order");
                        break;
                    } else if (comparison > 0) {
                        log("     - Right side is smaller, so inputs are not in the right order");
                        break;
                    }
                }
            }

            if (sum == 0 && leftList.size() < rightList.size()) {
                log("     - Left side ran out of items, so inputs are in the right order");
                sum -= 1;
            }

            return sum;
        }
    };

    @Override
    public Integer solvePartOne(String filePath) {
        List<List<String>> pairs = splitListToLists(getFileContent(filePath));
        return IntStream.range(0, pairs.size())
                .mapToObj(i -> new Tuple2<>(parse(pairs.get(i)), i))
                .mapToInt(tuple -> COMPARATOR.compare(tuple.getItem1().get(0), tuple.getItem1().get(1)) >= 0 ? 0 : (tuple.getItem2() + 1))
                .sum();
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        List<List<String>> pairs = splitListToLists(getFileContent(filePath));
        List<List<Object>> packets = pairs.stream()
                .map(this::parse)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        packets.addAll(asList(singletonList(2), singletonList(6)));
        packets.sort(COMPARATOR);

        List<Integer> dividerPackets = new LinkedList<>();
        for(int i = 0; i < packets.size(); i++) {
            List<Object> packet = packets.get(i);
            if (packet.size() == 1 && packet.get(0) instanceof Integer && ((Integer) packet.get(0) == 2 || (Integer) packet.get(0) == 6)) {
                dividerPackets.add(i + 1);
            }
        }
        return dividerPackets.stream()
                .reduce((i1, i2) -> i1 * i2)
                .orElse(0);
    }
}
