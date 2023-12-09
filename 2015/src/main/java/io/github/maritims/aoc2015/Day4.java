package io.github.maritims.aoc2015;

import io.github.maritims.toolbox.HexUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {
    public int solve(String secretKey, String leadingZeros) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        int i = 0;
        while(true) {
            String input = secretKey + i;
            messageDigest.update(input.getBytes());
            byte[] digest = messageDigest.digest();
            String hexadecimalRepresentation = HexUtil.toHexString(digest);

            if(hexadecimalRepresentation.startsWith(leadingZeros)) {
                return i;
            }

            i++;
        }
    }
}
