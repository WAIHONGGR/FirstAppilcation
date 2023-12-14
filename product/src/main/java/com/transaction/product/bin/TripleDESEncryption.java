    package com.transaction.product.bin;

    import javax.crypto.Cipher;
    import javax.crypto.KeyGenerator;
    import javax.crypto.SecretKey;
    import javax.crypto.spec.IvParameterSpec;
    import javax.crypto.spec.SecretKeySpec;
    import java.security.NoSuchAlgorithmException;

    public class TripleDESEncryption {

        public static void main() throws Exception {
            String text = "hello";

            IvParameterSpec iv = new IvParameterSpec(new byte[8]); // Initialization Vector

            SecretKey key = generateKeys();

            byte[] codedtext = encrypt(text, key, iv);
            System.out.println("Encrypted text: " + new String(codedtext));

            String decodedtext = decrypt(codedtext, key, iv);
            System.out.println("Decrypted text: " + decodedtext);
        }

            public static SecretKey generateKeys() throws NoSuchAlgorithmException {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("TripleDES");
                keyGenerator.init(168); // 168-bit key for Triple DES
                return keyGenerator.generateKey();
            }

        public static byte[] encrypt(String message, SecretKey key, IvParameterSpec iv)
                throws Exception {
            Cipher cipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] intermediate = cipher.doFinal(message.getBytes("UTF-8"));
            return intermediate;
        }

        public static String decrypt(byte[] message, SecretKey key, IvParameterSpec iv)
                throws Exception {
            Cipher cipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] intermediate = cipher.doFinal(message);

            return new String(intermediate, "UTF-8");
        }

    }
