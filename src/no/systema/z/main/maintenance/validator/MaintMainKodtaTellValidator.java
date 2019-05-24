package no.systema.z.main.maintenance.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaTellRecord;

/**
 * 
 * @author oscardelatorre
 * @date Aug 22, 2016
 * 
 *
 */
public class MaintMainKodtaTellValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintMainKodtaTellValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainKodtaTellRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainKodtaTellRecord record = (JsonMaintMainKodtaTellRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teavd", "", "Avd er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teopdn", "", "Oppd.nr er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teturn", "", "Turnr. er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				/*/N/A
				if( !this.validNumber(record.getAvutpr(), 99.99) ){
					errors.rejectValue("avutpr", "", "Utl pro. value invalid. The value can not be greater than 99.99");
				}
				*/
			}
		}
		
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		JsonMaintMainKodtaTellRecord record = (JsonMaintMainKodtaTellRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teavd", "", "Avd er obligatorisk");
	}
	
}
