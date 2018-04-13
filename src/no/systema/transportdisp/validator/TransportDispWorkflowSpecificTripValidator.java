package no.systema.transportdisp.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.validator.DateValidator;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripRecord;

/**
 * 
 * @author oscardelatorre
 * @date Apr 8, 2015
 * 
 *
 */
public class TransportDispWorkflowSpecificTripValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = Logger.getLogger(TransportDispWorkflowSpecificOrderValidator.class.getName());
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTransportDispWorkflowSpecificTripRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTransportDispWorkflowSpecificTripRecord record = (JsonTransportDispWorkflowSpecificTripRecord)obj;
		
		//Check for Mandatory fields first
		if(record!=null){
			if( this.valueExists(record.getCenturyYearTurccTuraar()) && this.valueExists(record.getTurmnd()) &&
				this.valueExists(record.getTubiln()) && this.valueExists(record.getTulk()) && 
				this.valueExists(record.getTubilk()) &&  this.valueExists(record.getTuknt2()) &&
				this.valueExists(record.getTunat())){
				//do nothing. Validation test passed!
			}else{
				//at least avd or sign must exist IF everything else is empty... 
				errors.rejectValue("tupro", "systema.transportdisp.workflow.trip.error.null.leastNumberOfValues"); 
			}
			//independent validation
			if(record.getTutbel()!=null && !"".equals(record.getTutbel())){
				if( (record.getTutval()!=null && !"".equals(record.getTutval())) &&  (record.getTutako()!=null && !"".equals(record.getTutako())) ){
					//validation passed
				}else{
					errors.rejectValue("tutbel", "systema.transportdisp.workflow.trip.error.rule.invalidPrice");
				}
			}
			
			if(record.getTutm()!=null && !"".equals(record.getTutm())){
				if(!this.dateValidator.validateTimeHHmm(record.getTutm())){
					errors.rejectValue("tutm", "systema.transportdisp.workflow.trip.error.rule.time.tutm.invalid");
				}
			}
			if(record.getTutmt()!=null && !"".equals(record.getTutmt())){
				if(!this.dateValidator.validateTimeHHmm(record.getTutmt())){
					errors.rejectValue("tutmt", "systema.transportdisp.workflow.trip.error.rule.time.tutmt.invalid");
				}
			}
		}
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean valueExists(String value){
		boolean retval = false;
		if(value!=null){
			if(!"".equals(value)){
				retval = true;
			}
		}
		
		return retval;
	}
}
