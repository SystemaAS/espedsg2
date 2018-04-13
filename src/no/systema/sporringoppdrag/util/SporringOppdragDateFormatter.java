/**
 * 
 */
package no.systema.sporringoppdrag.util;

import no.systema.main.util.DateTimeManager;
/**
 * 
 * @author oscardelatorre
 * @date Feb 4, 2015
 * 
 */
public class SporringOppdragDateFormatter {
	
	/**
	 * Converts Norwegian dates (ddMMyy) to ISO (yyyyMMdd)
	 * @param value
	 * @return
	 */
	public String convertToDate_ISO (String value){
		DateTimeManager dateMgr = new DateTimeManager();
		return dateMgr.getDateFormatted_ISO(value, DateTimeManager.NO_FORMAT);
	}
	
	/**
	 * Converts ISO date (yyyyMMdd) to Norwegian (ddMMyy)
	 * @param value
	 * @return
	 */
	public String convertToDate_NO (String value){
		DateTimeManager dateMgr = new DateTimeManager();
		String retval = value;
		//only when the source value is of mask-format-type= yyyyMMdd, otherwise ignore
		if(value!=null && value.length()==8){
			retval = dateMgr.getDateFormatted_NO(value, DateTimeManager.ISO_FORMAT);
		}
		return retval;
	}
}
