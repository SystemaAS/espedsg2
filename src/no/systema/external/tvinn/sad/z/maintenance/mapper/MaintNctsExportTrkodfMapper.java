/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintNctsTrkodfContainer;

/**
 * @author Fredrik Möller / Oscar ported
 * @date Okt 17, 2016; Mar 29, 2018
 * 
 */
public class MaintNctsExportTrkodfMapper {
	
	public JsonMaintNctsTrkodfContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintNctsTrkodfContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintNctsTrkodfContainer.class); 
		return container;
	}
}
