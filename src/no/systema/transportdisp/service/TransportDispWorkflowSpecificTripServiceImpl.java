/**
 * 
 */
package no.systema.transportdisp.service;

import org.apache.log4j.Logger;

import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispWorkflowSpecificTripMapper;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripArchivedDocsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripShipContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripMessageNoteContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 1, 2015
 * 
 * 
 */
public class TransportDispWorkflowSpecificTripServiceImpl implements TransportDispWorkflowSpecificTripService {
	private static final Logger logger = Logger.getLogger(TransportDispWorkflowSpecificTripServiceImpl.class.getName());
	/**
	 * 
	 */
	public JsonTransportDispWorkflowSpecificTripContainer getContainer(String utfPayload) {
		JsonTransportDispWorkflowSpecificTripContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificTripMapper mapper = new JsonTransportDispWorkflowSpecificTripMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * 
	 */
	public JsonTransportDispWorkflowSpecificTripShipContainer getContainerShip(String utfPayload) {
		JsonTransportDispWorkflowSpecificTripShipContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificTripMapper mapper = new JsonTransportDispWorkflowSpecificTripMapper();
			container = mapper.getContainerShip(utfPayload);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	public JsonTransportDispWorkflowSpecificTripShipContainer getContainerShipDepartures(String utfPayload) {
		JsonTransportDispWorkflowSpecificTripShipContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificTripMapper mapper = new JsonTransportDispWorkflowSpecificTripMapper();
			container = mapper.getContainerShipDepartures(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * Update record (after update)
	 */
	public JsonTransportDispWorkflowSpecificTripContainer getUpdateContainer(String utfPayload) {
		JsonTransportDispWorkflowSpecificTripContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificTripMapper mapper = new JsonTransportDispWorkflowSpecificTripMapper();
			container = mapper.getUpdateContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonTransportDispWorkflowSpecificTripArchivedDocsContainer getArchivedDocsContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificTripArchivedDocsContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificTripMapper mapper = new JsonTransportDispWorkflowSpecificTripMapper();
			container = mapper.getArchivedDocsContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * 
	 */
	public JsonTransportDispWorkflowSpecificTripMessageNoteContainer getMessageNoteContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificTripMessageNoteContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificTripMapper mapper = new JsonTransportDispWorkflowSpecificTripMapper();
			container = mapper.getMessageNoteContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	
}
