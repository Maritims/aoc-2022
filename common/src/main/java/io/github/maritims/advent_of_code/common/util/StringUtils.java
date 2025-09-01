package io.github.maritims.advent_of_code.common.util;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern REPEATING_PAIR_WITH_ANYTHING_IN_BETWEEN_PATTERN = Pattern.compile("(\\w{2}).*(\\1)");
    private static final Pattern REPEATING_LETTER_WITH_SINGLE_DELIMITER_PATTERN  = Pattern.compile("(\\w).(\\1)");

    public static int numberOfVowels(String str) {
        return (int) str.chars().filter(c -> "aeiou".contains("" + (char) c)).count();
    }

    public static boolean hasLettersFollowingEachOther(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasNeitherOfThesePatterns(String str, String... patterns) {
        return Arrays.stream(patterns).noneMatch(str::contains);
    }

    public static boolean hasRepeatingLetterWithSingleDelimiter(String str) {
        return REPEATING_LETTER_WITH_SINGLE_DELIMITER_PATTERN.matcher(str).find();
    }

    public static boolean hasRepeatedPairWithAnythingInBetween(String str) {
        return REPEATING_PAIR_WITH_ANYTHING_IN_BETWEEN_PATTERN.matcher(str).find();
    }
}
