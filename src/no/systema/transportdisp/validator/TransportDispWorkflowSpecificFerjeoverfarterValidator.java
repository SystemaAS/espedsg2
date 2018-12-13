package no.systema.transportdisp.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripShipRecord;
import no.systema.main.validator.DateValidator;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;

/**
 * 
 * @author oscardelatorre
 * @date Dec 2018
 * 
 *
 */
public class TransportDispWorkflowSpecificFerjeoverfarterValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = Logger.getLogger(TransportDispWorkflowSpecificFerjeoverfarterValidator.class.getName());
	private NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	private StringManager strMgr = new StringManager();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTransportDispWorkflowSpecificTripShipRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTransportDispWorkflowSpecificTripShipRecord record = (JsonTransportDispWorkflowSpecificTripShipRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fedat2", "systema.transportdisp.ferjeoverfarter.form.error.null.fedat2");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "wsfajn", "systema.transportdisp.ferjeoverfarter.form.error.null.wsfajn"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fefrom", "systema.transportdisp.ferjeoverfarter.form.error.null.fefrom"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "feto", "systema.transportdisp.ferjeoverfarter.form.error.null.feto"); 
		
		//Rule errors
		if(record!=null){
			if(strMgr.isNotNull(record.getFedat2())){
				if(!dateValidator.validateDate(record.getFedat2(), DateValidator.DATE_MASK_ISO)){
					errors.rejectValue("fedat2", "systema.transportdisp.ferjeoverfarter.form.error.rule.date.fedat2.invalid"); 
				}
			}
			 
		}	
	}
}
