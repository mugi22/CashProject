package com.id.kas.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
 
public class JCrypto {
    private Cipher ecipher;
    private Cipher dcipher;
    private String userKey; //harus 16 digit
    private SecretKey key;
    private String sEncripted;
    private String sDecript;
//    private String sString;
     
    
    public JCrypto(String mKey) {
		super();
		this.userKey = mKey;	
		getKey();
		JCrypto(key);
		
	}
    
//   public JCrypto(String mKey, String string) {
//		super();
//		this.userKey = mKey;
//		this.sString = string;
//	}
   
public SecretKey getKey() {
	   SecretKey key = new SecretKeySpec(userKey.getBytes(), "AES");
	   this.key = key;
       return key;
	}
	public void setKey(SecretKey key) {
		this.key = key;
	}
	

public void  JCrypto(SecretKey key) {
        try {
            ecipher = Cipher.getInstance("AES");
            dcipher = Cipher.getInstance("AES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            System.out.println("Failed in initialization");
            e.printStackTrace();
        }
    }
    
    
    
    
 
    public String encrypt(String sStringToEncript) {
        try {
            byte[] utf8 = sStringToEncript.getBytes("UTF-8");
            byte[] enc = ecipher.doFinal(utf8);             
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Failed in Encryption");
        }
        return null;
    }
 
    public String decrypt(String sStringToDecript) {//sStringToDecript
        try {
        	
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(sStringToDecript); 
            byte[] utf8 = dcipher.doFinal(dec); 
            return new String(utf8, "UTF-8");
        } catch (Exception e) {
            System.out.println("Failed in Decryption");
        }
        return null;
    }
    
//    public String doEncript(){
//    	String sEncript = null;
//    	try {
//    	JCrypto(getKey());
//    	sEncript= decrypt();
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }
//    	return sEncript;
//    }
//    
//    public String doDecript(){
//    	String sDecript = null;
//    	try {
//    	JCrypto(getKey());
//    	sDecript= decrypt();
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }
//    	return sDecript;
//    }
 
    public static void main(String[] args) {
//     try {
    	
//    	JCrypto crypto = new JCrypto("1234567891234567","Testing encryption");//encript
        JCrypto crypto = new JCrypto("funxsqgrdvhwqcoi");//,"5Mpp1Vwej3jfnyck99F0u0UaqxUkNH4ky3Z9BbxlvUA=");//decript
        
//        System.out.println(crypto.encrypt("DFx9oLD4ufzYCQNxtUovhac3qd6/S/1jOhAuLGJLkV7+Cle1aKO09X0IUr13sF32nAnIBUZMeSzx9fSzE1yKhQ==));
    	System.out.println(crypto.decrypt("lq4KootYjj7+u+nCJL1LUw=="));
    	System.out.println("lq4KootYjj7unCJL1LUw==");
    	System.out.println(crypto.encrypt("disable"));
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }
    }
}