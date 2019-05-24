package no.systema.z.main.maintenance.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.jservices.common.dto.ArktxtDto;

/**
 * 
 * @author Fredrik Möller
 * @date Apr 11, 2017
 * 
 *
 */
public class MaintMainArktxtValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintMainArktxtValidator.class.getName());

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return ArktxtDto.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		// ArktxtDto record = (ArktxtDto)obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "artype", "systema.maint.arktxt.error.artype");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "artxt", "systema.maint.arktxt.error.artxt");

	}

	public void validateDelete(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "artype", "systema.maint.arktxt.error.artype");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "artxt", "systema.maint.arktxt.error.artxt");
	}

}
