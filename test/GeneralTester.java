import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;

/**
 * @author oscardelatorre
 *
 */
public class GeneralTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeneralTester tester = new GeneralTester();
		String str = "FW_ NC-327966-2019? Freight Documents  - Transocean Arctic_No Well_ Sampleboxer til cmt-lab.msg";
		String cleanStr = tester.replaceInvalidChars(str);
		System.out.println(cleanStr);
		
		/*
		if(tester.isValidStringForFilename(str)){
			System.out.println("A huevo cabrón ...");
		}else{
			System.out.println("Ni madres puto ...");
		}*/
	}
	
	public  boolean isValidStringForFilename(String str){ 
	    if (str == null || str.equals("")) { 
	        return false; 
	    } 
	    for (int i = 0; i < str.length(); i++) { 
	        char ch = str.charAt(i); 
	        int ascii = (int) ch;
	        if(ascii>=1 && ascii<=31){
	        	return false;
	        }else if(ch=='*' || ch==':' || ch=='<' || ch=='>' || ch=='?' || ch=='\\' || ch=='/' || ch=='|'){
	        	return false;
	        	
	        }
	        
	    } 
	    return true; 
	} 
	
	public String replaceInvalidChars(String str){
		String retval = str;
		
		String tmp =str;
		
		for (int i = 0; i < tmp.length(); i++) { 
			char ch = tmp.charAt(i); 
	        int ascii = (int) ch;
	        if(ascii>=1 && ascii<=31){
	        	System.out.println("(a) Invalid char:" + (char)ascii);
	        	tmp = str.replace(String.valueOf(ch), "");
	        }/*else if(ch=='*' || ch==':' || ch=='<' || ch=='>' || ch=='?' || ch=='\\' || ch=='/' || ch=='|'){
	        	System.out.println("(b) Invalid char:" + ch);
	        	tmp = tmp.replace(String.valueOf(ch), "");
	        	
	        }*/
		}
		//add as needed
		tmp = tmp.replaceAll("[^\\p{ASCII}]", "");
		tmp = tmp.replaceAll(" ","_");
		tmp = tmp.replaceAll("\\*","");
		tmp = tmp.replaceAll(":","");
		tmp = tmp.replaceAll("<","");
		tmp = tmp.replaceAll(">","");
		tmp = tmp.replaceAll("\\?","");
		tmp = tmp.replaceAll("\\\\","");
		tmp = tmp.replaceAll("/","");
		tmp = tmp.replaceAll("|","");
		
		
		retval = tmp;
		
		return retval;
	}
	
}
