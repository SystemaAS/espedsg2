package no.systema.z.main.maintenance.validator.tds;

import org.slf4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.JsonDebugger;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSveaRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jun 08, 2017
 * 
 *
 */
public class MaintMainSveaValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(MaintMainSveaValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainSveaRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainSveaRecord record = (JsonMaintMainSveaRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svea_syav", "", "Avd är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svea_syop", "", "Ärendenr är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svea_omeo", "", "EORI är obligatorisk"); 
		
		
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
		JsonMaintMainSveaRecord record = (JsonMaintMainSveaRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svea_syav", "", "Avd är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svea_syop", "", "Ärendenr. är obligatorisk"); 
		
	}
	
	
}
