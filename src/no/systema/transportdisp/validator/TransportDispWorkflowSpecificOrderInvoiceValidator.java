package no.systema.transportdisp.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceRecord;
import no.systema.main.validator.DateValidator;
import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * 
 * @author oscardelatorre
 * @date Oct 07, 2015
 * 
 *
 */
public class TransportDispWorkflowSpecificOrderInvoiceValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = Logger.getLogger(TransportDispWorkflowSpecificOrderInvoiceValidator.class.getName());
	private NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTransportDispWorkflowSpecificOrderInvoiceRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTransportDispWorkflowSpecificOrderInvoiceRecord record = (JsonTransportDispWorkflowSpecificOrderInvoiceRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fask", "systema.transportdisp.orders.invoice.form.error.null.sk.fask");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "favk", "systema.transportdisp.orders.invoice.form.error.null.gebyr.favk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fabelv", "systema.transportdisp.orders.invoice.form.error.null.belop.fabelv"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "faval", "systema.transportdisp.orders.invoice.form.error.null.valuta.faval"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fakdm", "systema.transportdisp.orders.invoice.form.error.null.mva.fakdm"); 
		logger.info(record.getIntMVAx() + "XXX" + record.getFakdm());
		//References
		if(record!=null){
			if( record.getIntMVAx()!=null && !"".equals(record.getIntMVAx()) ){ 
			    if(!record.getIntMVAx().equals(record.getFakdm())){
			    	errors.rejectValue("favk", "systema.transportdisp.orders.invoice.form.error.rule.gebyrMoms.invalid");
				}
			}
		}	
	}
}
