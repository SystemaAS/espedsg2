package no.systema.z.main.maintenance.validator.tds;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.slf4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSvnstdRecord;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * 
 * @author oscardelatorre
 * @date Jun 26, 2017
 * 
 *
 */
public class MaintMainSvnstdValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(MaintMainSvnstdValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainSvnstdRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainSvnstdRecord record = (JsonMaintMainSvnstdRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tiavd", "", "Avd är obligatorisk"); 
		/*TODOValidationUtils.rejectIfEmptyOrWhitespace(errors, "thnttd", "", "Antall eksemplar følgeseddel er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thntll", "", "Antall eksemplar lasteliste er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thfmll", "", "Trykk lastelisteformular er obligatorisk."); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thenkl", "", "Prosedyre er obligatorisk."); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtdn", "", "Nr.teller intern ref. er obligatorisk."); 
		*/
		//
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "s0004", "", "UtväxlingsId Avdelning är obligatorisk."); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "s0010", "", "UtväxlingsId Tullverket är obligatorisk."); 
		
		
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
		JsonMaintMainSvnstdRecord record = (JsonMaintMainSvnstdRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tiavd", "", "Avd är obligatorisk");
	}
	
	
}
