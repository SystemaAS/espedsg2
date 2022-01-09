package no.systema.z.main.maintenance.validator;

import org.slf4j.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.jservices.common.dto.SyparfDto;

/**
 * 
 * @author Fredrik Möller
 * @date Mar 6, 2017
 * 
 *
 */
public class MaintMainSyparfValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(MaintMainSyparfValidator.class.getName());

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return SyparfDto.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		// SyparfDto record = (SyparfDto)obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sykunr", "systema.maint.params.error.sykunr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sypaid", "systema.maint.params.error.sypaid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "syvrdn", "systema.maint.params.error.syvrdn");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "syvrda", "systema.maint.params.error.syvrda");

	}

	public void validateDelete(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sykunr", "systema.maint.params.error.sykunr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "syrecn", "systema.maint.params.error.syrecn");
	}

}
