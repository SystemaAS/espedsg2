/**
 * 
 */
package no.systema.transportdisp.service;

import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispWorkflowPostUpdateMapper;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowPostUpdateContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 1, 2015
 * 
 * 
 */
public class TransportDispPostUpdateServiceImpl implements TransportDispPostUpdateService {

	public JsonTransportDispWorkflowPostUpdateContainer getContainer(String utfPayload) {
		JsonTransportDispWorkflowPostUpdateContainer container = null;
		try{
			JsonTransportDispWorkflowPostUpdateMapper mapper = new JsonTransportDispWorkflowPostUpdateMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
