package no.systema.z.main.maintenance.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.jservices.common.dao.SvewDao;

/**
 * 
 * @author Fredrik Möller
 * @date Jun 2, 2017
 * 
 *
 */
public class MaintMainSviwValidator implements Validator {

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return SvewDao.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		//SvewDao record = (SvewDao)obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviw_knnr", "systema.maint.params.error.levenr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviw_knso", "systema.maint.params.error.varenr");
		
	}

	public void validateDelete(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviw_knnr", "systema.maint.params.error.levenr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviw_knso", "systema.maint.params.error.varenr");
	}

}
