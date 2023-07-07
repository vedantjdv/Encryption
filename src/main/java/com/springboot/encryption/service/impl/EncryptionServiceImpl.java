package com.springboot.encryption.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.springboot.encryption.service.EncryptionService;

@Service
public class EncryptionServiceImpl implements EncryptionService{


    public String encryptData(String data) throws Exception{

        String sensitiveData = data;
        
        String key = "1234"; 
        String encryptionKey = generateEncryptionKey(key);

	//	System.out.println(encryptionKey+"\n");
          
            String encryptedData = encrypt(sensitiveData, encryptionKey);
			 System.out.println("Encrypted data: " + encryptedData);
            String decryptedData = decrypt(encryptedData, encryptionKey);
            
       //  String abc= generateEncryptionKey(key);
 //        System.out.println("Ans  "+abc);
             System.out.println("Decrypted card number: " + decryptedData);
        return encryptedData;
    }

    private static String generateEncryptionKey(String expiryDate) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(expiryDate.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = new byte[16];
        System.arraycopy(encodedHash, 0, keyBytes, 0, 16);

        return Base64.getEncoder().encodeToString(keyBytes);
    }
    
    private static String encrypt(String data, String encryptionKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes()); 

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static String decrypt(String encryptedData, String encryptionKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        return new String(decryptedBytes);
    }

    
}
