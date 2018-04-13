/**
 * 
 */
package no.systema.transportdisp.service;

import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispWorkflowBudgetMapper;
import no.systema.transportdisp.model.jsonjackson.workflow.budget.JsonTransportDispWorkflowSpecificBudgetContainer;

/**
 * 
 * @author oscardelatorre
 * @date Oct 12, 2015
 * 
 * 
 */
public class TransportDispWorkflowBudgetServiceImpl implements TransportDispWorkflowBudgetService {

	public JsonTransportDispWorkflowSpecificBudgetContainer getContainer(String utfPayload) {
		JsonTransportDispWorkflowSpecificBudgetContainer container = null;
		try{
			JsonTransportDispWorkflowBudgetMapper mapper = new JsonTransportDispWorkflowBudgetMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
