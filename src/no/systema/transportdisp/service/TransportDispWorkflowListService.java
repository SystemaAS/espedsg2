/**
 * 
 */
package no.systema.transportdisp.service;

import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListGodsAndLastListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 31, 2015
 * 
 *
 */
public interface TransportDispWorkflowListService {
	public JsonTransportDispWorkflowListContainer getWorkflowListContainer(String utfPayload);
	public JsonTransportDispWorkflowListGodsAndLastListContainer getWorkflowGodsAndListContainer(String utfPayload);
	
}
