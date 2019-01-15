/**
 * 
 */
package no.systema.transportdisp.service;

import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowFellesutskriftContainer;


/**
 * 
 * @author oscardelatorre
 * @date Mar 21, 2018
 * 
 *
 */
public interface TransportDispWorkflowFellesutskriftService {
	public JsonTransportDispWorkflowFellesutskriftContainer getFellesutskriftContainer(String utfPayload);
	

}
