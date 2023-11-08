import java.util.Scanner;
/**
 *
 * @author morgan
 */
public class morganBrittProgrammingAssignment3 {
    public static void main (String[] args) {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Welcome to Morgan's calendar");
    System.out.println("Please enter the year");
    
    int year = keyboard.nextInt();
    if (year <= 1753 || year >= 9999){
        System.out.println("Error - Invalid year");
      	System.exit(-1);
    }
      boolean leapYear;
      if(year % 400 == 0){
     	leapYear = true;
      } else if(year % 100 == 0){
        leapYear = false;
      } else if(year % 4 == 0){
        leapYear = true;
      } else{
        leapYear = false;
      }
      
      boolean displayAnotherMonth= false;
	do{
      System.out.println("Pick a month");
      System.out.println("Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec");
      String month = keyboard.next();
        String monthCapitalized = month.toUpperCase();
        int numDayInMonth = 0;

        switch (monthCapitalized) {

          case "JAN":
            numDayInMonth = 31;
            break;
          case "FEB":
            if (leapYear == true)
              numDayInMonth = 29;
            else
              numDayInMonth = 28;
            break;
          case "MAR":
            numDayInMonth = 31;
            break;
          case "APR":
            numDayInMonth = 30;
            break;
          case "MAY":
            numDayInMonth = 31;
            break;
          case "JUN":
            numDayInMonth = 30;
            break;
          case "JUL":
            numDayInMonth = 31;
            break;
          case "AUG":
            numDayInMonth = 31;
            break;
          case "SEP":
            numDayInMonth = 30;
            break;
          case "OCT":
            numDayInMonth = 31;
            break;
          case "NOV":
            numDayInMonth = 30;
            break;
          case "DEC":
            numDayInMonth = 31;
            break;
          default:
            System.out.println("Error");
            System.exit(-1);
        }


      System.out.println("Enter a three-letter day abbreviation of the day the"
              + " month starts on");
      String dayOfWeek = keyboard.next();
      String dayOfWeekCapitalized = dayOfWeek.toUpperCase();

        int position = 0;

      switch (dayOfWeekCapitalized) {
              case "SUN":
                  position = 0;
                  break;
              case "MON":
                  position = 1;
                  break;
              case "TUE":
                  position = 2;
                  break;
              case "WED":    
                  position = 3;
                  break;
              case "THU":
                  position = 4;
                  break;
              case "FRI":
                  position = 5;
                  break;
              case "SAT":
                  position = 6;
                  break;
              default:
                  System.out.println("Error");
                  System.exit(-1);
      }
        System.out.println(month + " " + year);
          System.out.println(" Su Mo Tu We Th Fr Sa");
          int displayDate = 1-position;
        while(displayDate<=numDayInMonth){
          for(int i = 0; i < 7; i++){
            if(displayDate<1 || displayDate> numDayInMonth){
              System.out.print("   ");
            } else{
              System.out.printf("%3s","" + displayDate);
            }
            displayDate++;
          }
          System.out.println();
        }
        System.out.println("Would you like to display another calendar month? (Yes/No)");
        String response = keyboard.next();
        if(response.equalsIgnoreCase("yes")){
          displayAnotherMonth = true;
        }
      } while(displayAnotherMonth);
      System.out.println("Thank you for using the calendar program!");
    }
}
