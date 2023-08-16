package com.example.envyplan.config;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {

    public static String generateSecretKey() {
        byte[] secretKeyBytes = generateRandomBytes(32);
        return Base64.getEncoder().encodeToString(secretKeyBytes);
    }

    private static byte[] generateRandomBytes(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[length];
        secureRandom.nextBytes(randomBytes);
        return randomBytes;
    }
}
