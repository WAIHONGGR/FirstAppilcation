package com.transaction.product.bin;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DoubleDESEncryption {

    public static void main() throws Exception {

        String originalMessage = "This";

        // Generate a secret key for Triple DES
        final SecretKey key1 = generateDESedeKey();
        final SecretKey key2 = generateDESedeKey();

        // Encrypt the message
        byte[] encryptedMessage = encrypt(originalMessage, key1, key2);
        System.out.println("Encrypted message: " + Base64.getEncoder().encodeToString(encryptedMessage));

        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage, key1, key2);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    public static SecretKey generateDESedeKey() throws NoSuchAlgorithmException {
        KeyGenerator key = KeyGenerator.getInstance("DESede");
        key.init(168);
        return key.generateKey();
    }

    public static byte[] encrypt(String message, SecretKey key1, SecretKey key2) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE, key1);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());

        cipher.init(Cipher.ENCRYPT_MODE, key2);
        encryptedBytes = cipher.doFinal(encryptedBytes);

        return encryptedBytes;
    }

    public static String decrypt(byte[] encryptedMessage, SecretKey key1, SecretKey key2) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede");

        cipher.init(Cipher.DECRYPT_MODE, key2);
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);

        cipher.init(Cipher.DECRYPT_MODE, key1);
        decryptedBytes = cipher.doFinal(decryptedBytes);

        return new String(decryptedBytes);
    }
}
