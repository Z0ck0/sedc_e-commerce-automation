package com.magento.softwaretestingboard.utilities;

import java.util.Random;
import java.util.UUID;



public class CredentialsGenerator {

    // Declare a class-level variables to store the random email & password
    private static String randomEmail;
    private static String randomPassword;


    // Generate Random Email
    public static String getRandomEmail() {
        randomEmail = "user" + System.currentTimeMillis() + "@example.com";
        //randomEmail = "user" + System.nanoTime() + "@example.com";

        return randomEmail;
    }


    // Generate Random Password
    public static String getRandomPassword() {
        randomPassword = UUID.randomUUID().toString();
        return randomPassword;
    }


    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Generate Random First Name
    public static String generateRandomFirstName(int length) {
        Random random = new Random();
        StringBuilder firstName = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            firstName.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return firstName.toString();
    }


    // Generate Random Last Name
    public static String generateRandomLastName(int length) {
        Random random = new Random();
        StringBuilder lastName = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            lastName.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return lastName.toString();
    }
}