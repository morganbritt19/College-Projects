package com.mycompany.parallelbruteforce;

/**
 * This is a parallel implememtation of the SerialBruteForce.java project
 * It demonstrates a password cracking function using multithreading
 * In tandem with the other application, it can actively demonstrate the efficiency of multithreading
 * Built in Apache NetBeans
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelBruteForce {
    // Password to be cracked
    private static final String TARGET_PASSWORD = "Cx628";
    // Characters used for testing
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    // Length of the password
    private static final int PASSWORD_LENGTH = 5;
    private static final int NUM_THREADS = 4; // Adjust as needed

    // Tracks how long it takes to successfuly crack the password
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Create thread pool
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // Perform brute force attack
        bruteForce(executor);

        // Shutdown the executor
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Total time taken
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Parallel execution time: " + totalTime + " milliseconds");
    }

    // Brute force with multithreading
    private static void bruteForce(ExecutorService executor) {
        // Calculate size of search space for each thread
        int partitionSize = ALPHABET.length() / NUM_THREADS;
        // Iterate over number of threads and assign a portion of task to each
        for (int i = 0; i < NUM_THREADS; i++) {
            int startIndex = i * partitionSize;
            int endIndex = (i + 1) * partitionSize;
            executor.execute(new BruteForceWorker(startIndex, endIndex));
        }
    }

    // Worker class to perform brute force attack on a portion of the search space
    private static class BruteForceWorker implements Runnable {
        private final int startIndex;
        private final int endIndex;

        // Constructor to initialize start and end index of search space portion
        public BruteForceWorker(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        // Method to actually do the brute forcing
        @Override
        public void run() {
            // Initialize password character array
            char[] password = new char[PASSWORD_LENGTH];
            // Start brute force attack for assigned portion of search space
            bruteForceHelper(password, 0, startIndex, endIndex);
        }

        // Recursive method to generate and test all possible combinations within assigned search space portion
        private void bruteForceHelper(char[] password, int position, int startIndex, int endIndex) {
            System.out.println("Thread [" + Thread.currentThread().getId() + "] trying password: " + new String(password));

            if (position == PASSWORD_LENGTH) {
                String candidate = new String(password);
                if (candidate.equals(TARGET_PASSWORD)) {
                    System.out.println("Password found by Thread [" + Thread.currentThread().getId() + "]: " + candidate);
                    // Halt the program when password is found
                    System.exit(0);
                }
                return;
            }

            // Recursively generate and test all possible characters for each position in the password
            for (int i = startIndex; i < endIndex; i++) {
                password[position] = ALPHABET.charAt(i);
                bruteForceHelper(password, position + 1, startIndex, endIndex);
            }
        }
    }
}
