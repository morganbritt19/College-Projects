/* 
* This assignment requires us to develop a Java class called JEString that can store strings in encrypted form.
* We were instructed to use some form of encryption (AES or simple XoR) to encrypt string data when stored and decrypt the data when accessed.
* This was developed with the other classes in this assignment in Apache Netbeans IDE 20.
*/

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class JEString {
    private String encryptedString;
    private String keyFilePath;

    public JEString(String initialString, String keyFilePath) throws Exception {
        this.keyFilePath = keyFilePath;
        generateKeyIfNotExists();
        setAndEncryptString(initialString);
    }

    public void setAndEncryptString(String value) throws Exception {
    try (FileInputStream fis = new FileInputStream(keyFilePath)) {
        byte[] keyBytes = new byte[fis.available()];
        fis.read(keyBytes);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(value.getBytes());
        encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
    }
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
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();

        byte[] keyBytes = secretKey.getEncoded();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(keyFile))) {
            outputStream.writeObject(keyBytes);
        }

        // Print out the key bytes
        System.out.println("Generated key: " + Base64.getEncoder().encodeToString(keyBytes));
    }
}

    public String getEncryptedString() {
        return encryptedString;
    }

    public static void main(String[] args) {
        try {
            // Test the JEString class
            String keyFilePath = "keyfile.key";
            JEString jeString = new JEString("Hello, World!", keyFilePath);
            System.out.println("Encrypted String: " + jeString.getEncryptedString());
            System.out.println("Decrypted String: " + jeString.decryptString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
