/**
 * 
 */
package no.systema.transportdisp.service;

import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispWorkflowListMapper;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListGodsAndLastListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 31, 2015
 * 
 * 
 */
public class TransportDispWorkflowListServiceImpl implements TransportDispWorkflowListService {
	/**
	 * 
	 */
	public JsonTransportDispWorkflowListContainer getWorkflowListContainer(String utfPayload) {
		JsonTransportDispWorkflowListContainer container = null;
		try{
			JsonTransportDispWorkflowListMapper mapper = new JsonTransportDispWorkflowListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * 
	 */
	public JsonTransportDispWorkflowListGodsAndLastListContainer getWorkflowGodsAndListContainer(String utfPayload){
		JsonTransportDispWorkflowListGodsAndLastListContainer container = null;
		try{
			JsonTransportDispWorkflowListMapper mapper = new JsonTransportDispWorkflowListMapper();
			container = mapper.getGodsAndLastListContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}

}
