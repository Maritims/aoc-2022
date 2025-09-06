package io.github.maritims.advent_of_code.common.util;

import java.util.Arrays;
import java.util.regex.Pattern;

import static io.github.maritims.advent_of_code.common.util.ArgumentExceptionUtil.throwIfNull;

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
}
