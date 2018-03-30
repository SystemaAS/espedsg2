/**
 * 
 */
package no.systema.external.tds.z.maintenance.mapper;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.external.tds.z.maintenance.model.JsonMaintSvthaContainer;

/**
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 */
public class MaintSvthaMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintSvthaMapper.class.getName());
	
	public JsonMaintSvthaContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonMaintSvthaContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSvthaContainer.class); 
		
		return container;
	}
}
