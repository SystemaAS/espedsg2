package no.systema.z.main.maintenance.validator.skat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.skat.JsonMaintMainDkiaRecord;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * 
 * @author oscardelatorre
 * @date Apr 26, 2017
 * 
 *
 */
public class MaintMainDkiaValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintMainDkiaValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainDkiaRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainDkiaRecord record = (JsonMaintMainDkiaRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkia_syav", "", "Afd er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkia_syop", "", "Angiv.nr - nr.teller er obligatorisk"); 
		
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
		JsonMaintMainDkiaRecord record = (JsonMaintMainDkiaRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkia_syav", "", "Afd. er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkia_syop", "", "Angiv.nr - nr.teller er obligatorisk"); 
		
	}
	
	
}
