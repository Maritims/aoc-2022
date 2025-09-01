package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.HexUtil;
import io.github.maritims.advent_of_code.common.PuzzleSolver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 extends PuzzleSolver<Integer, Integer> {
    static class AdventCoinMiner {
        private final String secretKey;
        private final String leadingZeros;

        AdventCoinMiner(String secretKey, String leadingZeros) {
            this.secretKey    = secretKey;
            this.leadingZeros = leadingZeros;
        }

        /**
         * An Advent Coin is represented by the number of steps it takes to generate an MD5 hash which has a given number of leading zeros.
         */
        public int mineAdventCoin() throws NoSuchAlgorithmException {
            String blockAddressWithAdventCoin = null;
            var    attempts                   = 0;
            var    md5                        = MessageDigest.getInstance("MD5");

            while (blockAddressWithAdventCoin == null) {
                var paddedSecretKey = secretKey + attempts;
                md5.update(paddedSecretKey.getBytes());

                var digest                    = md5.digest();
                var hexadecimalRepresentation = HexUtil.toHexString(digest);

                if (hexadecimalRepresentation.startsWith(leadingZeros)) {
                    blockAddressWithAdventCoin = paddedSecretKey;
                } else {
                    attempts++;
                }
            }

            return attempts;
        }
    }

    public Integer solveFirstPart() {
        try {
            return new AdventCoinMiner("yzbqklnj", "00000").mineAdventCoin();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer solveSecondPart() {
        try {
            return new AdventCoinMiner("yzbqklnj", "000000").mineAdventCoin();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
