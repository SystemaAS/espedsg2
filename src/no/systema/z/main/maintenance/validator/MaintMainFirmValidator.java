package no.systema.z.main.maintenance.validator;

import org.slf4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainFirmRecord;

/**
 * 
 * @author oscardelatorre
 * @date Nov 1, 2016
 * 
 *
 */
public class MaintMainFirmValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(MaintMainFirmValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainFirmRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainFirmRecord record = (JsonMaintMainFirmRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fifirm", "", "Firmakode er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fift", "", "Firmanavn er obligatorisk"); 
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
				if( !this.validNumber(record.getFitax()) ){
					errors.rejectValue("fitax", "", "Momssats er ugyldig. Max value: 99,99");
				}
				if( !this.validNumber(record.getFitax2()) ){
					errors.rejectValue("fitax2", "", "Tidligere momssats er ugyldig. Max value: 99,99");
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
		
		JsonMaintMainFirmRecord record = (JsonMaintMainFirmRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fifirm", "", "Firmakode er obligatorisk"); 
		
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
		final Integer UPPER_LIMIT = 100;
		boolean retval = true;
		if (value!=null && !"".equals(value)){
			String tmp = value.replace(",", ".");
			try{
				Double tmpDbl = Double.parseDouble(tmp);
				if(tmpDbl>=UPPER_LIMIT){
					retval = false;
				}
			}catch(Exception e){
				retval = false;
			}
		}
		
		return retval;
	}
}
