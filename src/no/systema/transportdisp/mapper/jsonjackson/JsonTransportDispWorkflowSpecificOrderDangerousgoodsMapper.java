/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.order.dangerousgoods.JsonTransportDispWorkflowSpecificOrderDangerousgoodsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.dangerousgoods.JsonTransportDispWorkflowSpecificOrderDangerousgoodsRecord;

/**
 * @author oscardelatorre
 * @date Mars 2019
 * 
 */
public class JsonTransportDispWorkflowSpecificOrderDangerousgoodsMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(JsonTransportDispWorkflowSpecificOrderDangerousgoodsMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowSpecificOrderDangerousgoodsContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowSpecificOrderDangerousgoodsContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTransportDispWorkflowSpecificOrderDangerousgoodsContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		if(container!=null && container.getAwblinelist()!=null){
			for (JsonTransportDispWorkflowSpecificOrderDangerousgoodsRecord record : container.getAwblinelist()){
				//DEBUG
			}
		}
		return container;
	}

	
}
