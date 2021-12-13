package no.systema.z.main.maintenance.validator.sad;

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
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTristdRecord;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * 
 * @author oscardelatorre
 * @date Sep 20, 2016
 * 
 *
 */
public class MaintMainTristdValidator implements Validator {
	private static final Logger logger = LogManager.getLogger(MaintMainTristdValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainTristdRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainTristdRecord record = (JsonMaintMainTristdRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tiavd", "", "Avd er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tienkl", "", "Prosedyre er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titdn", "", "Nrt.teller intern ref. er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "s0004", "", "UtvekslingsId Avdelning er obligatorisk."); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "s0010", "", "UtvekslingsId Tollvesenet er obligatorisk."); 
		
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
		JsonMaintMainTristdRecord record = (JsonMaintMainTristdRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tiavd", "", "Avd er obligatorisk");
	}
	
	
}
