package html.demo;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.util.Base64;

public class cryptMethods {
    public static String hashing(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(digest);
        } catch (Exception e) {
            errorMethods.defaultErrors(e);
            return null;
        }
    }
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static SecretKey key;
    private static Cipher encryptionCipher;

    public static void initKeys() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            key = keyGenerator.generateKey();
        } catch (Exception e) {
            errorMethods.defaultErrors(e);
        }
    }
    public static String encrypt(String plaintext) {
        try {
            byte[] byteText = plaintext.getBytes();
            encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
            encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = encryptionCipher.doFinal(byteText);
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            errorMethods.defaultErrors(e);
            return null;
        }
    }
    public static String decrypt(String encryptedString) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedString);
            Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, encryptionCipher.getIV());
            decryptionCipher.init(Cipher.DECRYPT_MODE, key, gcmParameterSpec);
            byte[] decryptedBytes = decryptionCipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            errorMethods.defaultErrors(e);
            return null;
        }
    }

    public static KeyStore keyStore;
    public static char[] passwordArray = "restaurantNEA".toCharArray();

    public static void initKeyStore() {
        try {
            KeyStore newKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            newKeyStore.load(null, passwordArray);
            try (FileOutputStream fileOutputStream = new FileOutputStream("restaurantNEA.jks")) {
                newKeyStore.store(fileOutputStream, passwordArray);
            }
            loadKeyStore();
        } catch (Exception e) {
            errorMethods.defaultErrors(e);
        }
    }
    public static void loadKeyStore() {
        try {
            keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new FileInputStream("restaurantNEA.jks"), passwordArray);
        } catch (Exception e) {
           if (e.toString().equals("java.io.FileNotFoundException: restaurantNEA.jks (The system cannot find the file specified)")) {
               initKeyStore();
           }
           else {
               errorMethods.defaultErrors(e);
           }
        }
    }
    public static void setKeyValInKeyStore() {

    }
}
