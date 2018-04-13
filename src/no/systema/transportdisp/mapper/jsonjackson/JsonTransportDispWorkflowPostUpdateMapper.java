/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowPostUpdateContainer;



/**
 * @author oscardelatorre
 * @date Sep 15, 2015
 * 
 */
public class JsonTransportDispWorkflowPostUpdateMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(JsonTransportDispWorkflowPostUpdateMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowPostUpdateContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowPostUpdateContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTransportDispWorkflowPostUpdateContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		/*for (JsonTransportDispWorkflowSpecificOrderRecord record : container.getDspoppdrag()){
			//DEBUG
		}*/
		return container;
	}
	
}
