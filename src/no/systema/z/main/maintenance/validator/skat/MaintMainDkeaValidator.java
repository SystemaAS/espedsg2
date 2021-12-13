package no.systema.z.main.maintenance.validator.skat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.apache.logging.log4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.skat.JsonMaintMainDkeaRecord;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * 
 * @author oscardelatorre
 * @date Apr 26, 2017
 * 
 *
 */
public class MaintMainDkeaValidator implements Validator {
	private static final Logger logger = LogManager.getLogger(MaintMainDkeaValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainDkeaRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainDkeaRecord record = (JsonMaintMainDkeaRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkea_syav", "", "Afd er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkea_syop", "", "Angiv.nr - nr.teller er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkea_ftip", "", "FTP-adresse er obligatorisk"); 
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//TODO (expand if applicable)
			}
		}
		
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	public void validateDelete(Object obj, Errors errors) { 
		JsonMaintMainDkeaRecord record = (JsonMaintMainDkeaRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkea_syav", "", "Afd. er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkea_syop", "", "Angiv.nr - nr.teller er obligatorisk"); 
		
	}
	
	
}
