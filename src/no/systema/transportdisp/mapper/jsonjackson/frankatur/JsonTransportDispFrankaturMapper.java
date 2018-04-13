/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson.frankatur;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.frankatur.JsonTransportDispFrankaturContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.frankatur.JsonTransportDispFrankaturRecord;

import java.util.*;

/**
 * @author oscardelatorre
 * @date Aug 07, 2015
 * 
 * 
 */
public class JsonTransportDispFrankaturMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(JsonTransportDispFrankaturMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispFrankaturContainer getContainer(String utfPayload) throws Exception{
		JsonTransportDispFrankaturContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTransportDispFrankaturContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTransportDispFrankaturRecord> fields = container.getFrankaturer();
			for(JsonTransportDispFrankaturRecord record : fields){
				
			}
		}
			
		return container;
	}
	
}
