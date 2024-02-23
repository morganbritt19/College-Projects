import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankingApp {
    private static final Logger logger = LogManager.getLogger(BankingApp.class);
    private static double balance = 1000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Banking App!");
        System.out.println("Your current balance is: $" + balance);

        try {
            System.out.print("Enter the amount to withdraw: $");
            double amount = scanner.nextDouble();

            withdraw(amount);

            System.out.println("Withdrawal successful. Your new balance is: $" + balance);
        } catch (Exception ex) {
            logger.error("An error occurred: " + ex.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount. Amount must be greater than zero.");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds. Your balance is: $" + balance);
        }

        balance -= amount;
    }
}
