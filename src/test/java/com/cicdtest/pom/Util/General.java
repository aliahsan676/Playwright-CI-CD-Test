package com.cicdtest.pom.Util;

import java.util.Random;

public class General {
    public static void waitForDomStable() {
        try {
            Thread.sleep(25000); // Simple wait; Replace with proper Playwright waits if needed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitForDivStable() {
        try {
            Thread.sleep(10000); // Simple wait; Replace with proper Playwright waits if needed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static String getRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
