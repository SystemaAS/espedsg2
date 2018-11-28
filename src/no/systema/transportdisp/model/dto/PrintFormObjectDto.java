package no.systema.transportdisp.model.dto;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import lombok.Data;


@Data
public class PrintFormObjectDto   {
	private String applicationUser;
	private String opd;
	private String avd;
	private String tur;
	
	private String fbType;
	private String cmrType;
	private String ffType;
	
	
}
