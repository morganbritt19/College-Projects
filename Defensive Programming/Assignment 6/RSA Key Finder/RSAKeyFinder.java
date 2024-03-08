/*
* The idea of this program is to find things like RSA keys (or other strings with high entropy) inside text files.
* This was tested with a Shakespearean text provided in this project folder.
* The was developed in Apache NetBeans IDE 20. 
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RSAKeyFinder {

    public static void main(String[] args) {
        String fileName = "playwithkey.txt"; // Name of the text file
        int windowSize = 64; // Size of the sliding window
        double thresholdEntropy = 5; // Threshold for considering a window as having high entropy

        try {
            String content = readFile(fileName);
            findRSAKey(content, windowSize, thresholdEntropy);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    // Read the content of the text file
    private static String readFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    // Find potential RSA key locations in the content
    private static void findRSAKey(String content, int windowSize, double thresholdEntropy) {
        List<Integer> potentialKeyLocations = new ArrayList<>();
        double maxEntropy = 0;
        int maxEntropyLocation = 0;
        int totalWindows = content.length() - windowSize + 1;
        double totalEntropy = 0;

        for (int i = 0; i < totalWindows; i++) {
            String window = content.substring(i, i + windowSize);
            double entropy = calculateEntropy(window);
            totalEntropy += entropy;

            if (entropy >= thresholdEntropy) {
                potentialKeyLocations.add(i);
            }

            if (entropy > maxEntropy) {
                maxEntropy = entropy;
                maxEntropyLocation = i;
            }
        }

        double meanEntropy = (totalEntropy / totalWindows) * 100;
        System.out.println("CSCI 4628-5628 Entropy Lab6 Morgan Britt");
        System.out.println("Mean Entropy: " + meanEntropy);
        System.out.println("Approximate Key Location: " + maxEntropyLocation);
        System.out.println("Potential RSA key locations found at:");
            for (int location : potentialKeyLocations) {
                System.out.println("Index: " + location);
            }
            
        // Extract the RSA key from the content using the approximate key location
        int keyLength = 100; // Assuming the key length is 100 characters
        int keyStartIndex = Math.max(0, maxEntropyLocation - (keyLength / 2));
        int keyEndIndex = Math.min(content.length(), keyStartIndex + keyLength);
        String rsaKey = content.substring(keyStartIndex, keyEndIndex);
        System.out.println("Key Data: " + rsaKey);
        }

    // Calculate the entropy of a window
    private static double calculateEntropy(String window) {
        int[] frequency = new int[10]; // Assuming numeric characters only
        for (char c : window.toCharArray()) {
            if (Character.isDigit(c)) {
                frequency[c - '0']++; // Convert char to numeric value and increment frequency
            }
        }
        double entropy = 0;
        int totalCharacters = window.length();
        for (int f : frequency) {
            if (f > 0) {
                double probability = (double) f / totalCharacters;
                entropy -= probability * (Math.log(probability) / Math.log(2));
            }
        }
    return entropy;
    }
}
