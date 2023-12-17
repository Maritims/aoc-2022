package io.github.maritims.aoc2023.day7;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Hand implements Comparable<Hand> {
    public static final LinkedList<Character> CARD_VALUES_WITH_JOKER    = new LinkedList<>(List.of('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A', 'J'));
    public static final LinkedList<Character> CARD_VALUES_WITHOUT_JOKER = new LinkedList<>(List.of('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'));

    private final String                cards;
    private final LinkedList<Character> cardValues;
    private final boolean               isJokerEnabled;
    private       HandType              type;

    public Hand(String cards, boolean isJokerEnabled) {
        this.cards          = cards;
        this.cardValues     = isJokerEnabled ? CARD_VALUES_WITH_JOKER : CARD_VALUES_WITHOUT_JOKER;
        this.isJokerEnabled = isJokerEnabled;
    }

    public String getCards() {
        return cards;
    }

    public LinkedList<Character> getCardValues() {
        return cardValues;
    }

    public HandType getType() {
        if (type == null) {
            // {A=5}, {A=4, B=1}, {A=3, B=1, C=1} etc.
            var cardOccurrences = getCards().chars()
                .mapToObj(i -> (char) i)
                .collect(groupingBy(identity(), counting()));

            // {5=1}, {4=1, 1=1}, {3=1, 1=2} etc.
            var groupFrequency = cardOccurrences.values()
                .stream()
                .collect(groupingBy(identity(), counting()));

            var jokers   = isJokerEnabled ? cardOccurrences.getOrDefault('J', 0L) : 0L;
            var singles  = groupFrequency.getOrDefault(1L, -1L);
            var pairs    = groupFrequency.getOrDefault(2L, -1L);
            var trios    = groupFrequency.getOrDefault(3L, -1L);
            var quartets = groupFrequency.getOrDefault(4L, -1L);

            // All cards are singles, but a J among the singles would make for a pair. E.g. ABCDJ == ABCDA.
            if (singles == getCards().length()) {
                type = jokers > 0 ? HandType.OnePair : HandType.HighCard;
            }

            // There's one pair, but a J among the remaining singles would make for three of a kind. E.g. AABCJ == AABCA.
            else if (pairs == 1 && singles == 3) {
                type = jokers > 0 ? HandType.ThreeOfAKind : HandType.OnePair;
            }

            // There's two pairs...
            // ...but if the single is a J it's a full house. E.g. AAABJ == AAABB.
            // ...and if one of the pairs is of J it's a quartet. E.g. AAJJC == AAAAC.
            else if (pairs == 2 && singles == 1) {
                if (jokers == 0) {
                    type = HandType.TwoPair;
                } else if (jokers == 1) {
                    type = HandType.FullHouse;
                } else if (jokers == 2) {
                    type = HandType.FourOfAKind;
                }
            }

            // There's three of a kind...
            // ...but a J among the singles would make for four of a kind. E.g. AAABJ == AAABA.
            // ...and a trio of J would also make for four of a kind. E.g. JJJAB == AAAAB.
            else if (trios == 1 && singles == 2) {
                type = jokers == 1 || jokers == 3 ? HandType.FourOfAKind : HandType.ThreeOfAKind;
            }

            // There's a full house...
            // ...but a pair of J would make for five of a kind. E.g. AAAJJ == AAAAA.
            // ...and a trio of J would also make for five of a kind. E.g. AAJJJ == AAAAA.
            else if (trios == 1 && pairs == 1) {
                type = jokers == 2 || jokers == 3 ? HandType.FiveOfAKind : HandType.FullHouse;
            }

            // There's four of a kind...
            // ...but if the single is a joker it's five of a kind. E.g. AAAAJ == AAAAA.
            // ...and a quartet of J would also make for five of a kidn. E.g. AJJJJ == AAAAA.
            else if (quartets == 1) {
                type = jokers == 1 || jokers == 4 ? HandType.FiveOfAKind : HandType.FourOfAKind;
            }

            // No other condition was met. It has to be five of a kind.
            else {
                type = HandType.FiveOfAKind;
            }
        }
        return type;
    }

    /**
     * A is the strongest card but has the lowest ASCII code of the letter based cards. Set the letter based card strengths to values which are just above the digit based card values.
     * <table>
     *     <tr>
     *         <th>Card</th>
     *         <th>Strength</th>
     *     </tr>
     *     <tr>
     *         <td>A</td>
     *         <td>62</td>
     *     </tr>
     *     <tr>
     *         <td>B</td>
     *         <td>61</td>
     *     </tr>
     *     <tr>
     *         <td>Q</td>
     *         <td>60</td>
     *     </tr>
     *     <tr>
     *         <td>J</td>
     *         <td>59, or '1' if the joker card is enabled which would be converted to 49 by the char value being cast to an int and therefore converted to its ASCII code.</td>
     *     </tr>
     *     <tr>
     *         <td>T</td>
     *         <td>58</td>
     *     </tr>
     *     <tr>
     *         <td>9-0</td>
     *         <td>57-48 as these char values are just cast to an int and therefore automatically converted to their ASCII codes.</td>
     *     </tr>
     * </table>
     * @param c The card value.
     * @return The card strength.
     */
    protected int getCardStrength(char c) {
        return switch (c) {
            case 'A' -> 62;
            case 'K' -> 61;
            case 'Q' -> 60;
            case 'J' -> isJokerEnabled ? '1' : 59;
            case 'T' -> 58;
            default -> c;
        };
    }

    @Override
    public String toString() {
        return getCards();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        if (!Objects.equals(getCards(), ((Hand) o).getCards())) return false;
        return getType() == ((Hand) o).getType();
    }

    @Override
    public int hashCode() {
        int result = getCards() != null ? getCards().hashCode() : 0;
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }

    /**
     * Hand strength is determined by {@link Hand#getType()}.
     * If both hands are of the same type, compare the hands card by card to see if any card in a position is of greater value than the card in the corresponding position in the other hand.
     * @param o the object to be compared.
     * @return An int value indicating whether this hand is stronger than the other hand.
     */
    @Override
    public int compareTo(Hand o) {
        if(getType().ordinal() > o.getType().ordinal()) return 1;
        if(getType().ordinal() < o.getType().ordinal()) return -1;

        for (var i = 0; i < getCards().length(); i++) {
            var s1 = getCardStrength(getCards().charAt(i));
            var s2 = o.getCardStrength(o.getCards().charAt(i));

            if (s1 > s2) return 1;
            if (s1 < s2) return -1;
        }

        return 0;
    }
}