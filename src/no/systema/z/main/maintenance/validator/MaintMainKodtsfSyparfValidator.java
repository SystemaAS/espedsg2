package no.systema.z.main.maintenance.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtsfSyparfRecord;

/**
 * 
 * @author oscardelatorre
 * @date Okt 17, 2016
 * 
 *
 */
public class MaintMainKodtsfSyparfValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintMainKodtsfSyparfValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainKodtsfSyparfRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainKodtsfSyparfRecord record = (JsonMaintMainKodtsfSyparfRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kosfsi", "systema.main.maintenance.error.mainmaintenancesyfa60.kosfsi.signature.null"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kosfnv", "systema.main.maintenance.error.mainmaintenancesyfa60.kosfnv.name.null"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//check for duplicate
				if (record.getDuplicateSignature()){
					errors.rejectValue("duplicateSignature", "systema.main.maintenance.error.mainmaintenancesyfa60.kosfsi.signature.duplicate");
				}
			}
		}
		
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintMainKodtsfSyparfRecord record = (JsonMaintMainKodtsfSyparfRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kosfsi", "systema.main.maintenance.error.mainmaintenancesyfa60.kosfsi.signature.null"); 
		
	}
	
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean validInteger(String value){
		final Integer LOWER_LIMIT = 1;
		boolean retval = true;
		if (value!=null && !"".equals(value)){
			try{
				Integer tmpInt = Integer.parseInt(value);
				if(tmpInt < LOWER_LIMIT){
					retval = false;
				}
			}catch(Exception e){
				retval = false;
			}
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean validNumber(String value){
		final Integer UPPER_LIMIT = 99;
		boolean retval = true;
		if (value!=null && !"".equals(value)){
			String tmp = value.replace(",", ".");
			try{
				Double tmpDbl = Double.parseDouble(tmp);
				if(tmpDbl>UPPER_LIMIT){
					retval = false;
				}
			}catch(Exception e){
				retval = false;
			}
		}
		
		return retval;
	}
}
