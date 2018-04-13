/**
 * 
 */
package no.systema.transportdisp.service;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowPostUpdateContainer;


/**
 * 
 * @author oscardelatorre
 * @date Sep 15, 2015
 * 
 *
 */
public interface TransportDispPostUpdateService {
	public JsonTransportDispWorkflowPostUpdateContainer getContainer(String utfPayload);
	
}
