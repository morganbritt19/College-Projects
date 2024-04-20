package com.mycompany.serialbruteforce;

/**
 * The basic premise of this application is to guess the set password by iterating through each 5 letter combination of the character list provided.
 * This application is designed to be single threaded
 * The sister application to this is demonstrating multithreading
 * There is a function included that will track the time it takes until the correct password is found
 * In tandem, these applications will demonstrate the efficiency of multithreading
 * Built with Apache NetBeans
 */

public class SerialBruteForce {
    // Password we want to crack
    private static final String TARGET_PASSWORD = "Cx628";
    // Character list
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    // Length of the password
    private static final int PASSWORD_LENGTH = 5;

    // Keeps track of how long the program runs
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Perform brute force attack
        bruteForce();
        
        // Will display the amount of time taken to crack the password
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Serial execution time: " + totalTime + " milliseconds");
    }

    // Method for brute forcing
    private static void bruteForce() {
        // Initialize a password character array set to the password length
        char[] password = new char[PASSWORD_LENGTH];
        // Begin recursive brute forcing process
        bruteForceHelper(password, 0);
    }
    
    // Generates and tests all possible combination of 5 characters
    private static void bruteForceHelper(char[] password, int position) {
        // Print out each attempt as it happens
        System.out.println("Trying password: " + new String(password));

        
        if (position == PASSWORD_LENGTH) {
            // If password is complete, check if it matches the expected password
            String candidate = new String(password);
            if (candidate.equals(TARGET_PASSWORD)) {
                // Print out password when found
                System.out.println("Password found: " + candidate);
                // Halt the program when password is found
                System.exit(0);
            }
            return;
        }

        // Generate/test al possible characters for each position in password
        for (int i = 0; i < ALPHABET.length(); i++) {
            password[position] = ALPHABET.charAt(i);
            bruteForceHelper(password, position + 1);
        }
    }
}

