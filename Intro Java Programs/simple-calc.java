import java.util.Scanner;
/*
 * @author morganBritt
 */
public class morganBrittLab5 {
    /* @param args the command line arguments */
public static void main (String[] args) {
    int menuInput; 
    Scanner keyboard = new Scanner(System.in);
    
    do { //execute this at least once 
        System.out.println("Choose one of the following options: "); 
        System.out.println("1. Perform an arithmetic operation");
        System.out.println("2. Apply a function");
        System.out.println("3. Calculate a factorial");
        System.out.println("4. Exit the program");
        menuInput = keyboard.nextInt();
        System.out.println();
        while (menuInput != 4); // while this is true 
        
    switch (menuInput)// depending on this value do something 
    {
        case 1:
            System.out.println("Enter an expression of the form NUM OP NUM: "); // user input 
            int firstNum =  keyboard.nextInt();  // read int 	
        	keyboard.nextLine(); // to skip a line 
            String operator =  keyboard.next(); // read string 
            int secondNum = keyboard.nextInt();     // read int 
            char op=operator.charAt(0);				
            switch(op){
                case '+':
                    System.out.println(firstNum+secondNum);
                break;
                 
                case '-':
                    System.out.println(firstNum - secondNum);
                  
                break;
                case '*':
                    System.out.println(firstNum * secondNum);
                break;
                case '/':
                    System.out.println(firstNum / secondNum);
                break;
                case '%':
                    System.out.println(firstNum % secondNum);
                break;
                 
            default:
                System.out.println("error unknown operator");
             } 
         								                                              
        break; 
        
        case 2:
            System.out.println("Enter an expression of the form FUNC ARG: ");
            String func = keyboard.next();
            double arg = keyboard.nextDouble();
            keyboard.nextLine(); // to skip a line
            switch(func){
                case "log":
                    System.out.println(Math.log10(arg));
                break;
                
                case "ln":
                    System.out.println(Math.log(arg));
                break;
                
                case "sqrt":
                    System.out.println(Math.sqrt(arg));
                
                break;
                default:
                    System.out.println("Error unknown function");
               
        break;
        
        case 3:
            int result = 1;
            System.out.println("Enter a number: ");
            int factorialInput = keyboard.nextInt();
            keyboard.nextLine(); // to skip a line
            while (factorialInput >= 1) {
                result *= factorialInput;
                // reduce the input by 1 each time we go through the loop
                factorialInput--;
        System.out.println("Result: " + result);
            }
        break; 
        
        case 4:
            System.out.println("Thank you for using Morgan Britt's Calcualtor");
            
        break;
        
    default: // if none are valid do this 
        System.out.println("Invalid input");
    }
}
}
}
    }

