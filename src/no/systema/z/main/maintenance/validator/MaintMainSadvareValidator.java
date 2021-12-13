package no.systema.z.main.maintenance.validator;

import org.apache.logging.log4j.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.jservices.common.dao.SadvareDao;

/**
 * 
 * @author Fredrik Möller
 * @date Mar 28, 2017
 * 
 *
 */
public class MaintMainSadvareValidator implements Validator {
	private static final Logger logger = LogManager.getLogger(MaintMainSadvareValidator.class.getName());

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return SadvareDao.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		SadvareDao record = (SadvareDao)obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "levenr", "systema.maint.params.error.levenr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "varenr", "systema.maint.params.error.varenr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "varebe", "systema.maint.params.error.varebe");
		
		// Logical (RULES) controls if we passed the NOT NULL errors
		if (!errors.hasFieldErrors()) {
			if (record != null) {
				//Avgkode /sekvens 1..8
				if ((record.getW2asv1() != null && !"".equals(record.getW2asv1()))
						&& (record.getW2akd1() == null || "".equals(record.getW2akd1()))) {
					errors.rejectValue("w2akd1", "", "Hvis sekvensnummer fylles i må kode fylles i");
				}
				if ((record.getW2akd1() != null && !"".equals(record.getW2akd1()))
						&& (record.getW2asv1() == null || "".equals(record.getW2asv1()))) {
					errors.rejectValue("w2asv1", "", "Hvis kode fylles i må sekvensnummer fylles i");
				}				
				
				if ((record.getW2asv2() != null && !"".equals(record.getW2asv2()))
						&& (record.getW2akd2() == null || "".equals(record.getW2akd2()))) {
					errors.rejectValue("w2akd2", "", "Hvis sekvensnummer fylles i må kode fylles i");
				}
				if ((record.getW2akd2() != null && !"".equals(record.getW2akd2()))
						&& (record.getW2asv2() == null || "".equals(record.getW2asv2()))) {
					errors.rejectValue("w2asv2", "", "Hvis kode fylles i må sekvensnummer fylles i");
				}					
				if ((record.getW2asv3() != null && !"".equals(record.getW2asv3()))
						&& (record.getW2akd3() == null || "".equals(record.getW2akd3()))) {
					errors.rejectValue("w2akd3", "", "Hvis sekvensnummer fylles i må kode fylles i");
				}
				if ((record.getW2akd3() != null && !"".equals(record.getW2akd3()))
						&& (record.getW2asv3() == null || "".equals(record.getW2asv3()))) {
					errors.rejectValue("w2asv3", "", "Hvis kode fylles i må sekvensnummer fylles i");
				}				
				if ((record.getW2asv4() != null && !"".equals(record.getW2asv4()))
						&& (record.getW2akd4() == null || "".equals(record.getW2akd4()))) {
					errors.rejectValue("w2akd4", "", "Hvis sekvensnummer fylles i må kode fylles i");
				}
				if ((record.getW2akd4() != null && !"".equals(record.getW2akd4()))
						&& (record.getW2asv4() == null || "".equals(record.getW2asv4()))) {
					errors.rejectValue("w2asv4", "", "Hvis kode fylles i må sekvensnummer fylles i");
				}				
				if ((record.getW2asv5() != null && !"".equals(record.getW2asv5()))
						&& (record.getW2akd5() == null || "".equals(record.getW2akd5()))) {
					errors.rejectValue("w2akd5", "", "Hvis sekvensnummer fylles i må kode fylles i");
				}
				if ((record.getW2akd5() != null && !"".equals(record.getW2akd5()))
						&& (record.getW2asv5() == null || "".equals(record.getW2asv5()))) {
					errors.rejectValue("w2asv5", "", "Hvis kode fylles i må sekvensnummer fylles i");
				}					
				if ((record.getW2asv6() != null && !"".equals(record.getW2asv6()))
						&& (record.getW2akd6() == null || "".equals(record.getW2akd6()))) {
					errors.rejectValue("w2akd6", "", "Hvis sekvensnummer fylles i må kode fylles i");
				}	
				if ((record.getW2akd6() != null && !"".equals(record.getW2akd6()))
						&& (record.getW2asv6() == null || "".equals(record.getW2asv6()))) {
					errors.rejectValue("w2asv6", "", "Hvis kode fylles i må sekvensnummer fylles i");
				}					
				if ((record.getW2asv7() != null && !"".equals(record.getW2asv7()))
						&& (record.getW2akd7() == null || "".equals(record.getW2akd7()))) {
					errors.rejectValue("w2akd7", "", "Hvis sekvensnummer fylles i må kode fylles i");
				}
				if ((record.getW2akd7() != null && !"".equals(record.getW2akd7()))
						&& (record.getW2asv7() == null || "".equals(record.getW2asv7()))) {
					errors.rejectValue("w2asv7", "", "Hvis kode fylles i må sekvensnummer fylles i");
				}	
				if ((record.getW2asv8() != null && !"".equals(record.getW2asv8()))
						&& (record.getW2akd8() == null || "".equals(record.getW2akd8()))) {
					errors.rejectValue("w2akd8", "", "Hvis sekvensnummer fylles i må kode fylles i");
				}
				if ((record.getW2akd8() != null && !"".equals(record.getW2akd8()))
						&& (record.getW2asv8() == null || "".equals(record.getW2asv8()))) {
					errors.rejectValue("w2asv8", "", "Hvis kode fylles i må sekvensnummer fylles i");
				}	
			}
		}
		
	}

	public void validateDelete(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "levenr", "systema.maint.params.error.levenr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "varenr", "systema.maint.params.error.varenr");
	}

}
