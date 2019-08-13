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
		String str = "2019-08-13 15:00:56";
		try{
			String tmp = str.substring(11,13);
			System.out.println(tmp);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
