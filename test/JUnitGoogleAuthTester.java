

import static org.junit.Assert.*;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import no.systema.main.service.GoogleAuthenticatorService;


public class JUnitGoogleAuthTester {
	
	String secretKey = "F5J3VFM3OVQPA7DOPOVO2WWK5XISIA3T";
	
	@Test
	public void testGoogleBarCode(){
		String barCodeFilePath = "/Users/oscardelatorre/barcode.png";
		int barCodeHeight = 250;
		int barCodeWidth = 250;
		
		
		System.out.println(secretKey);
		//String secretKey = "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK";
		String email = "helpdesk@systema.no";
		String companyName = "SYSTEMA AS";
		String barCodeData = GoogleAuthenticatorService.getGoogleAuthenticatorBarCode(secretKey, email, companyName);
		System.out.println(barCodeData);
		GoogleAuthenticatorService.createQRCode(barCodeData, barCodeFilePath, barCodeHeight, barCodeWidth);
		
		
	}
	
	@Test
	public void testGoogleNumberFromAuthenticator(){
		//Scanner scanner = new Scanner(System.in);
		//String code = scanner.nextLine();
		String code = "441847";
		if (code.equals(GoogleAuthenticatorService.getTOTPCode(secretKey))) {
		    System.out.println("Logged in successfully");
		} else {
		    System.out.println("Invalid 2FA Code");
		}
	}
	
	@Test
	public void testGoogleGenerateSecretKey(){
		//F5J3VFM3OVQPA7DOPOVO2WWK5XISIA3T
		String secretKey = GoogleAuthenticatorService.generateSecretKey();
		System.out.println(secretKey);
		
	}
	

}
