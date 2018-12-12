/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.core.JsonParser;

//application library
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord;

/**
 * @author oscardelatorre
 * @date Apr 11, 2015
 * 
 */
public class JsonTransportDispWorkflowShippingPlanningOrdersListMapper {
	private static final Logger logger = Logger.getLogger(JsonTransportDispWorkflowShippingPlanningOrdersListMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer getCurrentOrdersContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
		
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord record : container.getOrderlistlandtur()){
			//DEBUG
		}
		return container;
	}

	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer getOpenOrdersContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
		
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord record : container.getOrderlistlandled()){
			//DEBUG
		}
		return container;
	}
	
}
