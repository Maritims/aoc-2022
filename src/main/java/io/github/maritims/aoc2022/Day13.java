package io.github.maritims.aoc2022;

import java.util.LinkedList;
import java.util.List;

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

    public boolean isInRightOrder(List<Object> left, List<Object> right) {
        for(int l = 0; l < left.size(); l++) {
            Object oL = left.get(l);
            Object oR = right.get(l);
            if(oL instanceof Integer && oR instanceof Integer) {
                if((Integer) oL < (Integer) oR) {
                    System.out.println("Left side is smaller, so inputs are in the right order");
                    return true;
                }

                if((Integer) oL > (Integer) oR) {
                    System.out.println("Right side is smaller, so inputs are not in the right order");
                    return false;
                }
            }

            int result = 0;

            if(oL instanceof List && oR instanceof Integer) {

            } else if(oL instanceof Integer && oR instanceof List) {

            } else if(oL instanceof List && oR instanceof List) {

            } else {
                Integer iL = (Integer) oL;
                Integer iR = (Integer) oR;
                if(iL > iR) {
                    result = 1;
                } else if(iR > iL) {
                    result = -1;
                }
            }

            if(result != 0) {
                return true;
            }
        }

        return true;
    }

    @Override
    public Integer solvePartOne(String filePath) {
        List<List<String>> pairs = splitListToLists(getFileContent(filePath));
        int i = 1;
        int sum = 0;
        for(List<String> pair : pairs) {
            List<Object> left = parse(pair.get(0), 1).getItem1();
            List<Object> right = parse(pair.get(1), 1).getItem1();

            try {
                if (isInRightOrder(left, right)) {
                    sum += i;
                }
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Unable to compare lines: " + left + " vs. " + right);
            }
            i++;
        }
        return sum;
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}
