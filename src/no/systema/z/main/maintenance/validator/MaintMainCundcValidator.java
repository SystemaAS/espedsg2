package no.systema.z.main.maintenance.validator;

import org.slf4j.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcRecord;

/**
 * 
 * @author Fredrik Möller
 * @date Okt 28, 2016
 * 
 *
 */
public class MaintMainCundcValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(MaintMainCundcValidator.class.getName());

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return JsonMaintMainCundcRecord.class.isAssignableFrom(clazz); 
	}
	
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainCundcRecord record = (JsonMaintMainCundcRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cfirma", "systema.maint.kunderegister.error.firma"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ccompn", "systema.maint.kunderegister.error.kundnr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cconta", "systema.maint.kunderegister.error.cconta");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sonavn", "systema.maint.kunderegister.error.sonavn");

		//Logical (RULES) controls if we passed the NOT NULL errors
		if (!errors.hasFieldErrors()) {
			if (record != null) {
				checkIfValidToEnterFasteKoder(errors, record);
			}
		}
		
	}
	
	private void checkIfValidToEnterFasteKoder(Errors errors, JsonMaintMainCundcRecord record) {
		if (record.getAvkved1() != null && !(record.getAvkved1().length() == 0 || record.getAvkved1().contains(" "))) {
			if (!record.getCtype().startsWith("*")) {
				errors.rejectValue("avkved1", "", "Faste koder kan ikke registreras hvis *-Funksjon ikke finnes.");
			}
		}
	}

	public void validateDelete(Object obj, Errors errors) { 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cfirma", "systema.maint.kunderegister.error.firma"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ccompn", "systema.maint.kunderegister.error.kundnr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cconta", "systema.maint.kunderegister.error.cconta");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sonavn", "systema.maint.kunderegister.error.sonavn");
	}

	
	
	
	
	
}
