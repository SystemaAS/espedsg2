
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import no.systema.main.validator.DateValidator;

public class RegExTester {

	public static void main(String[] args) {
		/*RegExTester tester = new RegExTester();
		//String expression = "A470303DK";
        //String result = tester.doIt(expression);
		DateValidator dateValidator = new DateValidator();
		System.out.println(dateValidator.validateDateIso203_YYYYMMDDhhmm("201408221200"));
		*/
		String text = "   J         ";
		String newT = trimEnd(text);
		System.out.println(newT);
    }
	
	private static String trimEnd(String s){
	      int i = s.length()-1;
	      while(s.charAt(i)==' '){
	         i--;
	      }
	      return s.substring(0, i+1);
	   }
	/*
	private boolean checkDate(String value){
		boolean retval = true;
		String nameRegex = "^\\d{4}\\d{2}\\d{2}\\d{2}\\d{2}\\d{2}$";
		Pattern namePattern = Pattern.compile(nameRegex);
        Matcher nameMatcher = namePattern.matcher(value);
        boolean matchFound = nameMatcher.find();
        if (matchFound) {
            //nothing
        } else {
            retval = false;
        }
		return retval;
		
	}
	
	
	private String doIt(String value) {

        String retVal = "";
        try {
            if (value != null) {
                //String nameRegex = "^(\\d{4})$";
                //String nameRegex = "^\\w{2}\\d{9}\\w{3}$";
            	    String nameRegex = "[a-zA-Z]{1}[0-9]{6}DK";
                Pattern namePattern = Pattern.compile(nameRegex);
                Matcher nameMatcher = namePattern.matcher(value);
                boolean matchFound = nameMatcher.find();
                if (matchFound) {
                    retVal = "MATCH!";
                } else {
                    retVal = "NO MATCH";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return retVal;
    }
	*/
}
