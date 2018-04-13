/**
 * 
 */
package no.systema.transportdisp.service;

import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripArchivedDocsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripMessageNoteContainer;


/**
 * 
 * @author oscardelatorre
 * @date Mar 31, 2015
 * 
 *
 */
public interface TransportDispWorkflowSpecificTripService {
	public JsonTransportDispWorkflowSpecificTripContainer getContainer(String utfPayload);
	public JsonTransportDispWorkflowSpecificTripContainer getUpdateContainer(String utfPayload);
	public JsonTransportDispWorkflowSpecificTripArchivedDocsContainer getArchivedDocsContainer(String utfPayload);
	public JsonTransportDispWorkflowSpecificTripMessageNoteContainer getMessageNoteContainer(String utfPayload);
	
	
}
