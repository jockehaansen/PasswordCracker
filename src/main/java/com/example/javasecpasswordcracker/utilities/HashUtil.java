package com.example.javasecpasswordcracker.utilities;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    public static String md5(String input){
        return hash(input, "MD5");
    }

    public static String sha256(String input){
        return hash(input, "SHA256");
    }

    private static String hash(String input, String algorithm){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            byte[] hashBytes = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));

            BigInteger no = new BigInteger(1, hashBytes);
            String hashText = no.toString(16);

            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found: " + algorithm, e);
        }
    }
}
