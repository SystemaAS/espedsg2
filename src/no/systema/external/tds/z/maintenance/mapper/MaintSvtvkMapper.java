/**
 * 
 */
package no.systema.external.tds.z.maintenance.mapper;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.external.tds.z.maintenance.model.JsonMaintSvtvkContainer;

/**
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 */
public class MaintSvtvkMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintSvtvkMapper.class.getName());
	
	public JsonMaintSvtvkContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonMaintSvtvkContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSvtvkContainer.class); 
		
		return container;
	}
}
