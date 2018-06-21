import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;


import no.systema.main.util.DateTimeManager;
import no.systema.main.validator.DateValidator;

public class DateTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		DateTimeManager mgr = new DateTimeManager();
		String userDate = "160318";
		boolean isValid = mgr.isValidForwardDateIncludingToday(userDate, "ddMMyy");
		if(isValid){
			System.out.println("Valid date");
		}else{
			System.out.println("Invalid");
		}*/
		
		/*DateTimeManager mgr = new DateTimeManager();
		String tid = "1540";
		boolean isValid = mgr.isValidForwardTime(tid, "HHmm");
		if(isValid){
			System.out.println("Valid time");
		}else{
			System.out.println("Invalid");
		}*/
		
		//DateTimeManager mgr = new DateTimeManager();
		/*
		boolean isValid = mgr.validTodayBetweenLimits("20160512", "yyyyMMdd");
		if(isValid){
			System.out.println("Valid date");
		}else{
			System.out.println("Invalid");
		}*/
		
		Calendar calendar = Calendar.getInstance();
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);  
		System.out.println("Day of year:" + dayOfYear);
	}
	
	
}
