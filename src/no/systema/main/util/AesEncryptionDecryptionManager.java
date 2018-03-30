/**
 * 
 */
package no.systema.main.util;


import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import no.systema.main.controller.DashboardController;

import java.util.Base64;


/**
 * Utility class to manage encryption with SHA-algorithm
 * 
 * @author oscardelatorre
 * @date Mar 27, 2018
 * 
 */
public class AesEncryptionDecryptionManager {
	private static final Logger logger = Logger.getLogger(AesEncryptionDecryptionManager.class.getName());
	private static final String ALGO = "AES";
	private static final byte[] keyValue =
	            //new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
			new byte[]{'B', 'a', 'c', 'h', 'I', 's', 'T', 'h', 'e', 'K', 'e', 'y', 'D', 'u', 'd', 'e' };

    /**
     * Encrypt a string with AES algorithm.
     *
     * @param data is a string
     * @return the encrypted string
     */
    public String encrypt(String data) {
    	byte[] encVal = null;
    	try{
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance(ALGO);
	        c.init(Cipher.ENCRYPT_MODE, key);
	        encVal = c.doFinal(data.getBytes());
    	}catch(Exception e){
    		logger.info("ERROR encryption" + e.toString());
    	}
        return Base64.getEncoder().encodeToString(encVal);
    }

    /**
     * Decrypt a string with AES algorithm.
     *
     * @param encryptedData is a string
     * @return the decrypted string
     */
    public String decrypt(String encryptedData) {
    	byte[] decValue = null;
    	try{
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance(ALGO);
	        c.init(Cipher.DECRYPT_MODE, key);
	        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
	        decValue = c.doFinal(decodedValue);
    	}catch(Exception e){
    		logger.info("ERROR decryption" + e.toString());
    	}
        return new String(decValue);
    }

    /**
     * Generate a new encryption key.
     */
    private Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGO);
    }
}
