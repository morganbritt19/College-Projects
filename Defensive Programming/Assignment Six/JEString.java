/* This assignment requires us to develop a Java class called JEString that can store strings in encrypted form.
* We were instructed to use some form of encryption (AES or simple XoR) to encrypt string data when stored and decrypt the data when accessed.
*/

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class JEString {
    private String encryptedString;
    private String keyFilePath;

    public JEString(String initialString, String keyFilePath) throws Exception {
        this.keyFilePath = keyFilePath;
        generateKeyIfNotExists();
        setAndEncryptString(initialString);
    }

    public void setAndEncryptString(String value) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(keyFilePath));
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(value.getBytes());
        encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decryptString() throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(keyFilePath));
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
        return new String(decryptedBytes);
    }

    private void generateKeyIfNotExists() throws Exception {
        File keyFile = new File(keyFilePath);
        if (!keyFile.exists()) {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = new SecureRandom();
            keyGen.init(256, secureRandom); // Generate a 256-bit key
            SecretKey secretKey = keyGen.generateKey();

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(keyFile))) {
                outputStream.writeObject(secretKey.getEncoded());
            }
        }
    }


    public static void main(String[] args) {
        try {
            // Test the JEString class
            String keyFilePath = "keyfile.key";
            JEString jeString = new JEString("Hello, World!", keyFilePath);
            System.out.println("Encrypted String: " + jeString.encryptedString);
            System.out.println("Decrypted String: " + jeString.decryptString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String getEncryptedString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
