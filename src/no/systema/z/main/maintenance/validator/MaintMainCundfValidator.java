package no.systema.z.main.maintenance.validator;

import org.apache.logging.log4j.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;

/**
 * 
 * @author Fredrik Möller
 * @date Okt 28, 2016
 * 
 *
 */
public class MaintMainCundfValidator implements Validator {
	private static final Logger logger = LogManager.getLogger(MaintMainCundfValidator.class.getName());

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return JsonMaintMainCundfRecord.class.isAssignableFrom(clazz); 
	}
	
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainCundfRecord record = (JsonMaintMainCundfRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adr3", "systema.maint.kunderegister.error.adr3");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "knavn", "systema.maint.kunderegister.error.knavn");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kundnr", "systema.maint.kunderegister.error.kundnr");
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
					//TODO: maybe
				}
		}
		
	}
	
	public void validateDelete(Object obj, Errors errors) { 
		JsonMaintMainCundfRecord record = (JsonMaintMainCundfRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firma", "systema.maint.kunderegister.error.firma"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kundnr", "systema.maint.kunderegister.error.kundnr");
		
		
	}
	
}
