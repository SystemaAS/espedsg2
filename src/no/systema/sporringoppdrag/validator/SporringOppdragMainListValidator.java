package no.systema.sporringoppdrag.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.sporringoppdrag.filter.SearchFilterSporringOppdragTopicList;

/**
 * 
 * @author oscardelatorre
 * @date Feb 4, 2015
 * 
 *
 */
public class SporringOppdragMainListValidator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return SearchFilterSporringOppdragTopicList.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		SearchFilterSporringOppdragTopicList record = (SearchFilterSporringOppdragTopicList)obj;
		
		//Check for Mandatory fields first
		if(record!=null){
			//AVD && OPP.
			if(!"".equals(record.getWsavd())){
				if("".equals(record.getWsopd())){
					errors.rejectValue("wsopd", "systema.sporringoppdrag.mainlist.form.search.error.rule.avd.invalidAlone");
				}
			}
			
			if(!"".equals(record.getWsopd())){
				if("".equals(record.getWsavd())){
					errors.rejectValue("wsavd", "systema.sporringoppdrag.mainlist.form.search.error.rule.opd.invalidAlone");
				}
			}
			//OPP.TYPE && OPP.DATO
			if(!"".equals(record.getWsot())){
				if("".equals(record.getWsdtot())){
					errors.rejectValue("wsdtot", "systema.sporringoppdrag.mainlist.form.search.error.rule.opdType.invalidAlone");
				}
			}
			if(!"".equals(record.getWsdtot())){
				if("".equals(record.getWsot())){
					errors.rejectValue("wsot", "systema.sporringoppdrag.mainlist.form.search.error.rule.opdDate.invalidAlone");
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
