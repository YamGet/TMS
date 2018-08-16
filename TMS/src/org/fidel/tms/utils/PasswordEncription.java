package org.fidel.tms.utils;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.util.*;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

public class PasswordEncription {
	
//	public static void main(String[] args){
//
//		String password = "Summer2015";
//		
//		try{
//			String enc_password = encrypt(password);
//			System.out.println("Encrypted password: " + enc_password);
//			System.out.println("Decrypted password: " + decrypt(enc_password));
//			
//		} catch(Exception ex){
//			ex.printStackTrace();
//		}
//		
//	}
	
	private static final String ALGORITHM = "AES";
	private static final String KEY = "1Hbfh667adfDEJ78";
	
	public static String encrypt(String value) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(PasswordEncription.ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
		//String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
		
		Base64.Encoder base64Encoder = Base64.getEncoder().withoutPadding();
		String encryptedValue64 = base64Encoder.encodeToString(encryptedByteValue);
		
		return encryptedValue64;
	}
	
	public static String decrypt(String value) throws Exception{
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(PasswordEncription.ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
//		byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
//		byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
		
		byte[] decryptedByteValue = Base64.getDecoder().decode(value);
		
		String decryptedValue = new String(decryptedByteValue, "utf-8");
		return decryptedValue;				
	}
	
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(PasswordEncription.KEY.getBytes(), PasswordEncription.ALGORITHM);
		return key;
	}
}