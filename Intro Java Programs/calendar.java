/**
*
* @author morgan
*/

import java.util.Scanner;

public class CalendarGenerator {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to the Calendar Generator");

        // Input: Get the year from the user
        int year = getYear(keyboard);

        // Determine if it's a leap year
        boolean leapYear = isLeapYear(year);

        boolean displayAnotherMonth = false;

        do {
            // Input: Get the month from the user
            String month = getMonth(keyboard);

            // Input: Get the day of the week the month starts on
            String dayOfWeek = getDayOfWeek(keyboard);

            // Display the calendar for the selected month
            displayCalendar(month, year, dayOfWeek, leapYear);

            // Ask the user if they want to display another calendar
            System.out.print("Do you want to display another calendar month? (Yes/No): ");
            String response = keyboard.next();
            displayAnotherMonth = response.equalsIgnoreCase("yes");
        } while (displayAnotherMonth);

        System.out.println("Thank you for using the Calendar Generator!");
        keyboard.close();
    }

    // Function to get a valid year from the user
    private static int getYear(Scanner keyboard) {
        int year;
        do {
            System.out.print("Please enter a year (1753-9999): ");
            year = keyboard.nextInt();
        } while (year <= 1753 || year >= 9999);
        return year;
    }

    // Function to check if a year is a leap year
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Function to get a valid month abbreviation from the user
    private static String getMonth(Scanner keyboard) {
        String month;
        do {
            System.out.print("Pick a month (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec): ");
            month = keyboard.next().toUpperCase();
        } while (!isValidMonth(month));
        return month;
    }

    // Function to check if a month abbreviation is valid
    private static boolean isValidMonth(String month) {
        String[] validMonths = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        for (String validMonth : validMonths) {
            if (validMonth.equals(month)) {
                return true;
            }
        }
        System.out.println("Error: Invalid month abbreviation");
        return false;
    }

    // Function to get a valid day abbreviation from the user
    private static String getDayOfWeek(Scanner keyboard) {
        String dayOfWeek;
        do {
            System.out.print("Enter a three-letter day abbreviation (Sun|Mon|Tue|Wed|Thu|Fri|Sat): ");
            dayOfWeek = keyboard.next().toUpperCase();
        } while (!isValidDayOfWeek(dayOfWeek));
        return dayOfWeek;
    }

    // Function to check if a day abbreviation is valid
    private static boolean isValidDayOfWeek(String dayOfWeek) {
        String[] validDays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        for (String validDay : validDays) {
            if (validDay.equals(dayOfWeek)) {
                return true;
            }
        }
        System.out.println("Error: Invalid day abbreviation");
        return false;
    }

    // Function to display the calendar for the selected month
    private static void displayCalendar(String month, int year, String dayOfWeek, boolean leapYear) {
        // Your calendar display code here
    }
}
