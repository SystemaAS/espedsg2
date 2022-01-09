package no.systema.z.main.maintenance.validator;

import org.slf4j.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.jservices.common.dto.SyparfDto;

/**
 * 
 * @author oscardelatorre
 * @date May 09, 2017
 * 
 *
 */
public class MaintMainSyparf2Validator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(MaintMainSyparf2Validator.class.getName());

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return SyparfDto.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		// SyparfDto record = (SyparfDto)obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "syuser", "systema.maint.params.error.syuser");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sypaid", "systema.maint.params.error.sypaid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "syvrdn", "systema.maint.params.error.syvrdn");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "syvrda", "systema.maint.params.error.syvrda");

	}

	public void validateDelete(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "syuser", "systema.maint.params.error.syuser");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "syrecn", "systema.maint.params.error.syrecn");
	}

}
