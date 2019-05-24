/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintNctsTransitKodeTypeContainer;

/**
 * @author Fredrik Möller / Oscar ported
 * @date Okt 17, 2016; Mar 29, 2018
 * 
 */
public class MaintNctsExportTransitKodeTypeMapper {
	
	public JsonMaintNctsTransitKodeTypeContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintNctsTransitKodeTypeContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintNctsTransitKodeTypeContainer.class); 
		return container;
	}
}
