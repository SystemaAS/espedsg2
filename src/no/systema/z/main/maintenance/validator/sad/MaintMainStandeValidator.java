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
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainStandeRecord;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * 
 * @author oscardelatorre
 * @date Sep 19, 2016
 * 
 *
 */
public class MaintMainStandeValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintMainStandeValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainStandeRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainStandeRecord record = (JsonMaintMainStandeRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seavd", "", "Avd er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sedl", "", "Dataliste er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "setdn", "", "Nrt.teller intern ref. er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
				if(record.getSetolk()!=null && "J".equals(record.getSetolk())){
					if( (record.getS3039e()!=null && !"".equals(record.getS3039e())) && (record.getS0004()!=null && !"".equals(record.getS0004())) && 
						(record.getS0010()!=null && !"".equals(record.getS0010())) && (record.getSelv2()!=null && !"".equals(record.getSelv2()))
					){
						//OK
						
					}else{
						if( record.getS3039e()==null || "".equals(record.getS3039e()) ){
							errors.rejectValue("s3039e", "", "Ekspedisjonsenhet er obligatorisk.");
						}
						if( record.getS0004()==null || "".equals(record.getS0004()) ){
							errors.rejectValue("s0004", "", "UtvekslingsId Avdelning er obligatorisk.");
						}
						if( record.getS0010()==null || "".equals(record.getS0010()) ){
							errors.rejectValue("s0010", "", "UtvekslingsId Tollvesenet er obligatorisk.");
						}
						if( record.getSelv2()==null || "".equals(record.getSelv2()) ){
							errors.rejectValue("selv2", "", "Tollkvittering ved utleveringsattest er obligatorisk.");
						}
					}

				}
				
			}
		}
		
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		JsonMaintMainStandeRecord record = (JsonMaintMainStandeRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seavd", "", "Avd er obligatorisk");
	}
	
	
}
