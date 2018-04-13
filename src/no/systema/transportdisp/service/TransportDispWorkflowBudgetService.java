/**
 * 
 */
package no.systema.transportdisp.service;
import no.systema.transportdisp.model.jsonjackson.workflow.budget.JsonTransportDispWorkflowSpecificBudgetContainer;
 

/**
 * 
 * @author oscardelatorre
 * @date Oct 12, 2015
 * 
 *
 */
public interface TransportDispWorkflowBudgetService {
	public JsonTransportDispWorkflowSpecificBudgetContainer getContainer(String utfPayload);
	
}
