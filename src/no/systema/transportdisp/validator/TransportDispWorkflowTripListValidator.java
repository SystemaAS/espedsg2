package no.systema.transportdisp.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.validator.DateValidator;
import no.systema.transportdisp.filter.SearchFilterTransportDispWorkflowTripList;

/**
 * 
 * @author oscardelatorre
 * @date Apr 8, 2015
 * 
 *
 */
public class TransportDispWorkflowTripListValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = Logger.getLogger(TransportDispWorkflowSpecificOrderValidator.class.getName());
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return SearchFilterTransportDispWorkflowTripList.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		SearchFilterTransportDispWorkflowTripList record = (SearchFilterTransportDispWorkflowTripList)obj;
		
		//Check for Mandatory fields first
		if(record!=null){
			if(record.getWssst()!=null && "Z".equals(record.getWssst())){
				if( (record.getWtudt()!=null && !"".equals(record.getWtudt())) ){
					//validation passed
				}else{
					errors.rejectValue("wtudt", "systema.transportdisp.workflow.trip.list.search.null.fromDate");
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
