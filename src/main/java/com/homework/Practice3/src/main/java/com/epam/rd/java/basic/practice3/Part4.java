package com.epam.rd.java.basic.practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {

    public static void main(String[] args)  throws NoSuchAlgorithmException {
        //magic goes next
    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes());
        byte[] hash = digest.digest();
        StringBuilder result = new StringBuilder();
        for (byte aByte : hash) {
            result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }
}
