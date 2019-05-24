package no.systema.z.main.maintenance.validator.sad;

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
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdRecord;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * 
 * @author oscardelatorre
 * @date Sep 30, 2016
 * 
 *
 */
public class MaintMainTrustdValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintMainTrustdValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainTrustdRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainTrustdRecord record = (JsonMaintMainTrustdRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thavd", "", "Avd er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thnttd", "", "Antall eksemplar følgeseddel er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thntll", "", "Antall eksemplar lasteliste er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thfmll", "", "Trykk lastelisteformular er obligatorisk."); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thenkl", "", "Prosedyre er obligatorisk."); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtdn", "", "Nr.teller intern ref. er obligatorisk."); 
		//
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
		JsonMaintMainTrustdRecord record = (JsonMaintMainTrustdRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thavd", "", "Avd er obligatorisk");
	}
	
	
}
