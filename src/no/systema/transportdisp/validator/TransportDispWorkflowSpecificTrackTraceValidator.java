package no.systema.transportdisp.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingRecordEdit;

import no.systema.main.validator.DateValidator;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;

/**
 * 
 * @author oscardelatorre
 * @date May 09 2019
 * 
 *
 */
public class TransportDispWorkflowSpecificTrackTraceValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = Logger.getLogger(TransportDispWorkflowSpecificTrackTraceValidator.class.getName());
	private NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	private StringManager strMgr = new StringManager();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTransportDispWorkflowSpecificOrderLoggingRecordEdit.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTransportDispWorkflowSpecificOrderLoggingRecordEdit record = (JsonTransportDispWorkflowSpecificOrderLoggingRecordEdit)obj;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "todo", "systema.transportdisp.ttrace.form.error.null.todo");
		
		//Rule errors
		if(record!=null){
			//------
			//dates 
			//------
			if(strMgr.isNotNull(record.getTtdate())){
				if(!dateValidator.validateDate(record.getTtdate(), DateValidator.DATE_MASK_ISO)){
					errors.rejectValue("ttdate", "systema.transportdisp.orders.ttrace.form.error.rule.ttdate.invalid");
				}
			}
			if(!"".equals(record.getTttime())){
				if(!this.dateValidator.validateTimeHHmm(record.getTttime())){
					errors.rejectValue("tttime", "systema.transportdisp.orders.ttrace.form.error.rule.tttime.invalid");
				}
			}
		}	
	}
}
