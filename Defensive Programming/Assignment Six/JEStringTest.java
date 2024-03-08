// Here is a JEStringTest file that isn't strictly necessary, but providing it in tandem with the other code seems to be best practice.

public class JEStringTest {
    public static void main(String[] args) {
        try {
            // Test the JEString class
            String keyFilePath = "keyfile.key";
            JEString jeString = new JEString("Hello, World!", keyFilePath);

            // Print the encrypted string
            System.out.println("Encrypted String: " + jeString.getEncryptedString());

            // Decrypt and print the string
            String decryptedString = jeString.decryptString();
            System.out.println("Decrypted String: " + decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
