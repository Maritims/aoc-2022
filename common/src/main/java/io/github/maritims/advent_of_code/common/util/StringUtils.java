package io.github.maritims.advent_of_code.common.util;

import java.util.Arrays;
import java.util.regex.Pattern;

import static io.github.maritims.advent_of_code.common.util.ArgumentExceptionUtil.throwIfNull;
import static io.github.maritims.advent_of_code.common.util.ArgumentExceptionUtil.throwIfNullOrEmpty;

public class StringUtils {
    private static final Pattern REPEATING_PAIR_WITH_ANYTHING_IN_BETWEEN_PATTERN = Pattern.compile("(\\w{2}).*(\\1)");
    private static final Pattern REPEATING_LETTER_WITH_SINGLE_DELIMITER_PATTERN  = Pattern.compile("(\\w).(\\1)");
    private static final Pattern GREEDY_REPEATING_DIGITS_PATTERN                 = Pattern.compile("(\\d+)(\\1)");
    private static final Pattern NON_GREEDY_REPEATING_DIGITS_PATTERN             = Pattern.compile("(\\d+?)\\1+");

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

    public static boolean hasNonOverlappingPairs(String str) {
        for (var i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                for (var j = i + 2; j < str.length() - 1; j++) {
                    if (str.charAt(i) != str.charAt(j) && str.charAt(j) == str.charAt(j + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int convertHexToDecimal(char mostSignificantCharacter, char leastSignificantCharacter) {
        var mostSignificantDigit  = Character.digit(mostSignificantCharacter, 16);
        var leastSignificantDigit = Character.digit(leastSignificantCharacter, 16);

        return (mostSignificantDigit << 4) | leastSignificantDigit;
    }

    public static char convertHexToChar(char mostSignificantCharacter, char leastSignificantCharacter) {
        return (char) convertHexToDecimal(mostSignificantCharacter, leastSignificantCharacter);
    }

    public static String unescapeString(String escapedString) {
        throwIfNull(escapedString, "escapedString");
        if (escapedString.isBlank()) {
            return "";
        }

        var sb    = new StringBuilder();
        var start = escapedString.charAt(0) == '"' ? 1 : 0;
        var end   = escapedString.charAt(escapedString.length() - 1) == '"' ? escapedString.length() - 1 : escapedString.length();

        for (var i = start; i < end; i++) {
            if (escapedString.charAt(i) == '\\') {
                i++;

                switch (escapedString.charAt(i)) {
                    case '\\':
                        sb.append('\\');
                        break;
                    case '"':
                        sb.append('"');
                        break;
                    case 'x':
                        var hex = convertHexToChar(escapedString.charAt(i + 1), escapedString.charAt(i + 2));
                        sb.append(hex);
                        i += 2;
                        break;
                }
            } else {
                sb.append(escapedString.charAt(i));
            }
        }

        return sb.toString();
    }

    public static String escapeString(String str) {
        throwIfNull(str, "str");
        if (str.isBlank()) {
            return "\"\"";
        }

        var sb = new StringBuilder();
        sb.append('"');
        for (var c : str.toCharArray()) {
            if (c == '\\' || c == '"') {
                sb.append('\\');
            }
            sb.append(c);
        }

        sb.append('"');
        return sb.toString();
    }

    public static String lookAndSay(String input) {
        var sb = new StringBuilder();

        for (var i = 0; i < input.length(); i++) {
            var occurrences = 1;

            // Count while we're looking at occurrences of the same character.
            while (i < input.length() - 1 && input.charAt(i) == input.charAt(i + 1)) {
                i++;
                occurrences++;
            }

            sb.append(occurrences).append(input.charAt(i));
        }

        return sb.toString();
    }

    public static boolean hasIncreasingStraightSequence(String str) {
        for (int i = 0; i < Math.max(2, str.length() - 3); i++) {
            if (str.charAt(i) + 1 == str.charAt(i + 1) && str.charAt(i + 1) + 1 == str.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNoneOfTheseLetters(String str, char... letters) {
        for (var letter : letters) {
            if (str.contains("" + letter)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Increments the last character of the given string. If the last character is 'z', it wraps around
     * to 'a' and continues incrementing the preceding portion of the string recursively.
     *
     * @param str the input string to increment; must not be null or empty
     *
     * @return the incremented string with the character sequence updated
     *
     * @throws IllegalArgumentException if the input string is null or empty
     */
    public static String incrementString(String str) {
        throwIfNullOrEmpty(str, "str");

        var c = str.charAt(str.length() - 1);
        if (c == 'z') {
            return incrementString(str.substring(0, str.length() - 1)) + 'a';
        } else {
            return str.substring(0, str.length() - 1) + (char) (c + 1);
        }
    }

    public static boolean hasRepeatingSequenceOfDigits(String str, boolean greedy) {
        throwIfNullOrEmpty(str, "str");
        return greedy ? GREEDY_REPEATING_DIGITS_PATTERN.matcher(str).matches() : NON_GREEDY_REPEATING_DIGITS_PATTERN.matcher(str).matches();
    }
}
