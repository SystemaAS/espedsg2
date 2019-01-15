/**
 * 
 */
package no.systema.transportdisp.service;

import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowFellesutskriftContainer;
import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispWorkflowFellesutskriftMapper;
import no.systema.transportdisp.service.TransportDispWorkflowFellesutskriftService;

/**
 * 
 * @author oscardelatorre
 * @date Jan 2019
 * 
 * 
 */
public class TransportDispWorkflowFellesutskriftServiceImpl implements TransportDispWorkflowFellesutskriftService {

	
	/**
	 * 
	 */
	public JsonTransportDispWorkflowFellesutskriftContainer getFellesutskriftContainer(String utfPayload){
		JsonTransportDispWorkflowFellesutskriftContainer container = null;
		try{
			JsonTransportDispWorkflowFellesutskriftMapper mapper = new JsonTransportDispWorkflowFellesutskriftMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
}
