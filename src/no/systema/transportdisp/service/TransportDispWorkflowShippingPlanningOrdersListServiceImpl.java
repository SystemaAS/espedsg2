/**
 * 
 */
package no.systema.transportdisp.service;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispWorkflowShippingPlanningOrdersListMapper;
import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispWorkflowSpecificOrderMapper;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 11, 2015
 * 
 * 
 */
public class TransportDispWorkflowShippingPlanningOrdersListServiceImpl implements TransportDispWorkflowShippingPlanningOrdersListService {
	private static final Logger logger = Logger.getLogger(JsonTransportDispWorkflowSpecificOrderMapper.class.getName());
	
	/**
	 * 
	 */
	public JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer getCurrentOrdersListContainer(String utfPayload) {
		JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer container = null;
		try{
			JsonTransportDispWorkflowShippingPlanningOrdersListMapper mapper = new JsonTransportDispWorkflowShippingPlanningOrdersListMapper();
			container = mapper.getCurrentOrdersContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * 
	 */
	public JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer getOpenOrdersListContainer(String utfPayload) {
		JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer container = null;
		try{
			JsonTransportDispWorkflowShippingPlanningOrdersListMapper mapper = new JsonTransportDispWorkflowShippingPlanningOrdersListMapper();
			container = mapper.getOpenOrdersContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * renders fraktbrev PDF
	 */
	public JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer getOrderFraktbrevPdfContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderMapper mapper = new JsonTransportDispWorkflowSpecificOrderMapper();
			container = mapper.getFraktbrevPdfContainer(utfPayload);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
		return container;
		
	}
	

}
