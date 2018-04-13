package no.systema.transportdisp.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import no.systema.transportdisp.model.jsonjackson.workflow.order.frisokvei.JsonTransportDispWorkflowSpecificOrderFrisokveiRecord;
import no.systema.main.validator.DateValidator;
import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * 
 * @author oscardelatorre
 * @date Jun 09, 2017
 * 
 *
 */
public class TransportDispWorkflowSpecificFrisokveiValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = Logger.getLogger(TransportDispWorkflowSpecificFrisokveiValidator.class.getName());
	private NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTransportDispWorkflowSpecificOrderFrisokveiRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTransportDispWorkflowSpecificOrderFrisokveiRecord record = (JsonTransportDispWorkflowSpecificOrderFrisokveiRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fskode", "systema.transportdisp.frisokvei.form.error.null.fskode");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fssok", "systema.transportdisp.frisokvei.form.error.null.fssok"); 
		
		//Rule errors
		if(record!=null){
			/*
			if(!record.TODO().equals(record.TODO())){
			    	//TODO errors.rejectValue("fskode", "systema.transportdisp.frisokvei.form.error.null.fskode");
			}*/
			 
		}	
	}
}
