/*
- This is a basic age calculator designed to showcase input sanitization.
- This won't perform completely accurate calculations since it doesn't look at the day or month, but is specifically designed as an example for checking user input.
*/

import java.time.LocalDate;
import java.util.Scanner;

public class AgeCalculator {
    public static void main(String[] args) {
        // Define integer variables "birthYear" initialized to 0 and declare the "age" variable without intializing it
        int birthYear = 0, age;

        // Define a string variable "string" that will store user input
        String string;

        // Create the Scanner object that will intake user input
        Scanner scanner = new Scanner(System.in);

        // Calculate the current year using LocalDate
        int currentYear = LocalDate.now().getYear();

        // Prompt the user to enter their birth year
        // Initializes a boolean "validInput" to false to check for valid input after user enters a string
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter your birth year: ");
            // This will store the user input in the string variable
            string = scanner.nextLine();

            // Use the Scanner class to input the user’s birth year from the console as a string
            try {
                // Convert the string to an integer
                birthYear = Integer.parseInt(string);

                // Check if the birth year is within a reasonable range (not in the future)
                if (birthYear <= currentYear && birthYear > 0) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid birth year.");
                }
            // This catch block will return an error if the input is not a valid number
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Calculate the user’s age
        age = currentYear - birthYear;

        // Output the calculated
        System.out.println("Age: " + age);

        // Closing the scanner object
        scanner.close();
    }
}
