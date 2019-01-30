package no.systema.transportdisp.model.dto;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import lombok.Data;


@Data
public class PrintFormObjectDto   {
	private String applicationUser;
	private String sign;
	private String opd;
	private String avd;
	private String tur;
	
	private String fbType;
	private String cmrType;
	private String ffType;
	private String aordType;
	private String aordDocumentType; //(S=Single/I=Intern/E=Ekstern)
	
	private String godslistType;
	private String lastlistType;
	//
	private String fbTypeOnList;
	private String cmrTypeOnList;
	private String ffTypeOnList;
	private String godslistTypeOnList;
	private String lastlistTypeOnList;
	
	
}
