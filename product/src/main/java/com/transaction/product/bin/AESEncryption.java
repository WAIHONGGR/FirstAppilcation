package com.transaction.product.bin;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESEncryption {

    public static void main() throws Exception {

        String originalMessage = "This is the secret message to encrypt!";

        // Generate a secret key for AES
        SecretKey secretKey = generateAESKey();

        // Encrypt the message
        String encryptedMessage = encrypt(originalMessage, secretKey);

        System.out.println("Encrypted message: " + encryptedMessage);

        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage, secretKey);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator key = KeyGenerator.getInstance("AES");
        key.init(256); // You can use 128, 192, or 256 bits for AES
        return key.generateKey();
    }

    public static String encrypt(String message, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedMessage, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decodedEncryptedMessage = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(decodedEncryptedMessage);
        return new String(decryptedBytes);
    }
}
