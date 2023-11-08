import java.util.Scanner;
/*
* @author morgan
*/
public class Calculator {
    public static void main(String[] args) {
        int menuInput;
        Scanner keyboard = new Scanner(System.in);

        do {
            System.out.println("Choose one of the following options:");
            System.out.println("1. Perform an arithmetic operation");
            System.out.println("2. Apply a function");
            System.out.println("3. Calculate a factorial");
            System.out.println("4. Exit the program");

            menuInput = keyboard.nextInt();
            System.out.println();

            switch (menuInput) {
                case 1:
                    performArithmeticOperation(keyboard);
                    break;
                case 2:
                    applyMathFunction(keyboard);
                    break;
                case 3:
                    calculateFactorial(keyboard);
                    break;
                case 4:
                    System.out.println("Thank you for using the calculator.");
                    break;
                default:
                    System.out.println("Invalid input. Please choose a valid option.");
            }
        } while (menuInput != 4);
    }

    // Function to perform an arithmetic operation
    private static void performArithmeticOperation(Scanner keyboard) {
        System.out.println("Enter an expression of the form NUM OP NUM:");
        int firstNum = keyboard.nextInt();
        String operator = keyboard.next();
        int secondNum = keyboard.nextInt();

        switch (operator) {
            case "+":
                System.out.println(firstNum + secondNum);
                break;
            case "-":
                System.out.println(firstNum - secondNum);
                break;
            case "*":
                System.out.println(firstNum * secondNum);
                break;
            case "/":
                if (secondNum != 0) {
                    System.out.println((double) firstNum / secondNum);
                } else {
                    System.out.println("Error: Division by zero.");
                }
                break;
            case "%":
                if (secondNum != 0) {
                    System.out.println(firstNum % secondNum);
                } else {
                    System.out.println("Error: Division by zero.");
                }
                break;
            default:
                System.out.println("Error: Unknown operator");
        }
    }

    // Function to apply a mathematical function
    private static void applyMathFunction(Scanner keyboard) {
        System.out.println("Enter an expression of the form FUNC ARG:");
        String func = keyboard.next();
        double arg = keyboard.nextDouble();

        switch (func) {
            case "log":
                if (arg > 0) {
                    System.out.println(Math.log10(arg));
                } else {
                    System.out.println("Error: Invalid argument for log function.");
                }
                break;
            case "ln":
                if (arg > 0) {
                    System.out.println(Math.log(arg));
                } else {
                    System.out.println("Error: Invalid argument for ln function.");
                }
                break;
            case "sqrt":
                if (arg >= 0) {
                    System.out.println(Math.sqrt(arg));
                } else {
                    System.out.println("Error: Invalid argument for sqrt function.");
                }
                break;
            default:
                System.out.println("Error: Unknown function");
        }
    }

    // Function to calculate a factorial
    private static void calculateFactorial(Scanner keyboard) {
        System.out.println("Enter a number:");
        int factorialInput = keyboard.nextInt();
        int result = 1;

        if (factorialInput < 0) {
            System.out.println("Error: Factorial is defined only for non-negative integers.");
        } else {
            for (int i = 1; i <= factorialInput; i++) {
                result *= i;
            }
            System.out.println("Result: " + result);
        }
    }
}
