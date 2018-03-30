package no.systema.main.util;

public class AesEncryptionDecryptionTester {

	public static void main(String[] args) {
		AesEncryptionDecryptionManager mgr = new AesEncryptionDecryptionManager();
		String encStr = null;
		String decStr = null;
		try{
			encStr = mgr.encrypt("OSCAR");
			System.out.println(encStr);
			decStr = mgr.decrypt(encStr);
			System.out.println(decStr);
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

}
