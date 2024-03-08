/*
* This is to be used in tandem with the JEString.java file in the Assignment Six folder.
* Its purpose is to generate a key file to be used for the encryption/decryption of the string "Hello World!" in the JEString.java file.
*/

import java.io.*;
import java.security.*;
import javax.crypto.*;

public class GenerateKeyFile {
    public static void main(String[] args) {
        try {
            // Generate AES encryption key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256); // 256-bit key
            SecretKey secretKey = keyGen.generateKey();

            // Write the raw key bytes to the key file
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("keyfile.key"))) {
                outputStream.writeObject(secretKey.getEncoded());
            }

            System.out.println("Key file generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
