package no.systema.z.main.maintenance.validator;

import org.slf4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwRecord;

/**
 * 
 * @author oscardelatorre
 * @date Aug 05, 2016
 * 
 *
 */
public class MaintMainKodtvKodtwValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(MaintMainKodtvKodtwValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainKodtvKodtwRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainKodtvKodtwRecord record = (JsonMaintMainKodtvKodtwRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kovavd", "", "Avd er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
				if( !this.validNumber(record.getAvutpr(), 99.99) ){
					errors.rejectValue("avutpr", "", "Utl pro. value invalid. The value can not be greater than 99.99");
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
		
		JsonMaintMainKodtvKodtwRecord record = (JsonMaintMainKodtvKodtwRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kovavd", "", "Avd. er obligatorisk"); 
		
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean validNumber(String value, int limit){
		final Integer UPPER_LIMIT = limit;
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
	/**
	 * 
	 * @param value
	 * @param limit
	 * @return
	 */
	private boolean validNumber(String value, double limit){
		final Double UPPER_LIMIT = limit;
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
