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
		try{
			String uom = "";
			String str = "PALLER FOR GODS";
			int index = str.indexOf(" ");
			if(index>=0){
				uom = str.substring(0,index);
			}
			System.out.println(uom);
			
			/*
			String str = "1";
			if(str.length()>=4){
				String ownHegn1 = str.substring(0, 4);
				System.out.println("A:"+ ownHegn1);
				if(str.length()>=9){
					String ownHegn2 = str.substring(4,9);
					System.out.println("B:"+ ownHegn2);
					if(str.length()>=10){
						if(str.length()>=15){
							String ownHegn3 = str.substring(9,15);
							System.out.println("C:"+ ownHegn3);
						}else{
							String ownHegn3 = str.substring(9);
							System.out.println("D:"+ ownHegn3);
						}
					}
				}else{
					String ownHegn2 = str.substring(4);
					System.out.println("Y:"+ ownHegn2);
				}
			}else{
				String ownHegn1 = str;
				System.out.println("Z:"+ ownHegn1);
			}
			*/
			
			/*StringManager mgr = new StringManager();
			String str = "888,89";
			String s = mgr.trailingStringWithNumericFiller(mgr.removeChar(str, ","), 7, "0");
			System.out.println(mgr.removeChar(s, ","));*/
			
			
			/*
			String tmp = "http://localhost:8080";
			int m = tmp.lastIndexOf("/");
			System.out.println(tmp.substring(m + 1));
			
			String x = tmp.replace("http://", "");
			int i = x.indexOf(":");
			x = x.substring(0, i);
			
			System.out.println(x);
			*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
