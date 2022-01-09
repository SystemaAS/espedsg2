package no.systema.z.main.maintenance.validator;

import org.slf4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaHodeRecord;

/**
 * 
 * @author oscardelatorre
 * @date Aug 18, 2016
 * 
 *
 */
public class MaintMainKodtaHodeValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(MaintMainKodtaHodeValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainKodtaHodeRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainKodtaHodeRecord record = (JsonMaintMainKodtaHodeRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "koaavd", "", "Avd er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "honet", "", "Språk er obligatorisk"); 
		
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
		JsonMaintMainKodtaHodeRecord record = (JsonMaintMainKodtaHodeRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "koaavd", "", "Avd er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "honet", "", "Språk er obligatorisk"); 
	}
	
}
