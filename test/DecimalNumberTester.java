import java.text.DecimalFormat;
import no.systema.main.util.NumberFormatterLocaleAware;
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * @author oscardelatorre
 *
 */
public class DecimalNumberTester {
	
	public static void main(String[] args) {
		try{
			
			//Double price = 0.3888;
		    DecimalFormat dFormatter = new DecimalFormat("#.00");
		    //Double price2 = Double.parseDouble(decim.format(price));
		    //System.out.println(price2);
			
		    NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
		    //System.out.println(numberFormatter.getDouble("5,668", 3));
		    Double x = 1234567901234.6236787687;
		    Double tmp = numberFormatter.getDouble(x, 3);
		    //String tmpStr = numberFormatter.getDoubleToPlainString(tmp);
		    String tmpStr = numberFormatter.getDoubleToPlainString(tmp, 3);
		    System.out.println("tmp:" + tmpStr);
		    
		    
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
