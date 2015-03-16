package com.id.kas.util;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;
import java.io.BufferedReader;
import java.io.FileReader;


public class AEScrypto222 {
	private static String algorithm = "AES";
	private  byte[] keyValue;//=new byte[] {'0','2','3','4','5','6','7','8','9','1','2','3','4','5','6','7'};// your key

	
	public byte[] getKeyValue(String sKey) {
//		keyValue = new byte[] {'0','5','3','a','5','6','7','8','9','1','2','3','4','5','6','7'};// your key
//		sKey ="1234567812345678";
		byte[] c = sKey.getBytes(StandardCharsets.UTF_8);
		keyValue = c;
		return keyValue;
	}

	

		// Performs Encryption
	    public  String encrypt(String plainText) 
	    {
	            Key key;
				try {
					key = generateKey();
					Cipher chiper = Cipher.getInstance(algorithm);
		            chiper.init(Cipher.ENCRYPT_MODE, key);
		            byte[] encVal = chiper.doFinal(plainText.getBytes());
		            String encryptedValue = new BASE64Encoder().encode(encVal);
		            return encryptedValue;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
	          
	            
	    }

	    // Performs decryption
	    public String decrypt(String encryptedText) 
	    {
	            // generate key 
	            Key key;
				try {
					key = generateKey();
					 Cipher chiper = Cipher.getInstance(algorithm);
			            chiper.init(Cipher.DECRYPT_MODE, key);
			            byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedText);
			            byte[] decValue = chiper.doFinal(decordedValue);
			            String decryptedValue = new String(decValue);
			            return decryptedValue;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
	           
	    }

	//generateKey() is used to generate a secret key for AES algorithm
	    private  Key generateKey() throws Exception 
	    {
	    	System.out.println(getKeyValue("1234567812345678"));
	            Key key = new SecretKeySpec(keyValue, algorithm);
	            return key;
	    }

	    // performs encryption & decryption 
	    public static void main(String[] args) 
	    {
	    	AEScrypto222 aeScrypto = new AEScrypto222();
	    	aeScrypto.getKeyValue("sfivbqhfbczzfnko");
	            String plainText = "isAdd=enable&isEdit=enable&isDelete=enable&isView=enable";
	            String encryptedText = aeScrypto.encrypt(plainText);
	            String decryptedText = aeScrypto.decrypt(encryptedText);

	            System.out.println("Plain Text : " + plainText);
	            System.out.println("Encrypted Text : " + encryptedText);
	            System.out.println("Decrypted Text : " + "R7aW+7PMzWsytCBnRirQzEfEL+BnLYYrQ6bqK3j1BJ1q5rCqYZ+6dYaOZSYQMyU2gT+ProS50gMs tclFlkyTZg==");
	    }
}
