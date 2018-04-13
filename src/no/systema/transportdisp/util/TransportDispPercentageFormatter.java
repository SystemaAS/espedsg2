/**
 * 
 */
package no.systema.transportdisp.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * @author oscardelatorre
 * @date Jun 5, 2015
 * 
 */
public class TransportDispPercentageFormatter {
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public String adjustPercentageNotationToFrontEndOnSpecificOrder(String value){
		NumberFormat formatter = DecimalFormat.getInstance();
		formatter.setMinimumFractionDigits(1);
        formatter.setMaximumFractionDigits(3);
        
		String retval = null;
		if (value!=null){
			Double a = this.numberFormatter.getDouble(value);
			Double percentage = a*100.00;
	        formatter.setRoundingMode(RoundingMode.FLOOR);
	        retval = formatter.format(percentage);
			retval = retval.replace(".", ",");
		}
		return retval;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public String adjustPercentageNotationToBackEndOnSpecificOrder(String value){
		NumberFormat formatter = DecimalFormat.getInstance();
		formatter.setMinimumFractionDigits(1);
        formatter.setMaximumFractionDigits(3);
        
		String retval = null;
		if (value!=null){
			Double a = this.numberFormatter.getDouble(value);
			Double percentage = a/100.00;
	        //formatter.setRoundingMode(RoundingMode.FLOOR);
	        retval = formatter.format(percentage);
			retval = retval.replace(".", ",");
		}
		return retval;
	}
	
	
}
