/**
 * 
 */
package no.systema.transportdisp.util;

import no.systema.main.util.DateTimeManager;
/**
 * 
 * @author oscardelatorre
 * @date Apr 1, 2015
 * 
 */
public class TransportDispDateTimeFormatter {
	final String HOUR_MINUTE_SEPARATOR = ":";
	/**
	 * Formats from HHmm to HH:mm
	 * @param rawValue
	 * @return
	 */
	public String formatTimeHHmm(String rawValue){
		String retval = rawValue;
		if(rawValue!=null && !"".equals(rawValue)){
			if(rawValue.contains(this.HOUR_MINUTE_SEPARATOR)){
				//nothing
			}else{
				if(rawValue.length()==4){
					String hh = rawValue.substring(0,2);
					String mm = rawValue.substring(2,4);
					retval = hh + this.HOUR_MINUTE_SEPARATOR + mm;
				}
			}
		}
		
		return retval;
	}
	
}
