/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.logging;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 28, 2015
 *
 */
public class JsonTransportDispWorkflowSpecificOrderLoggingContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avdelning = null;
	public void setAvdelning(String value) {  this.avdelning = value; }
	public String getAvdelning() { return this.avdelning;}
	
	private String oppdrag = null;
	public void setOppdrag(String value) {  this.oppdrag = value; }
	public String getOppdrag() { return this.oppdrag;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispWorkflowSpecificOrderLoggingRecord> trackTraceEvents;
	public void setTrackTraceEvents(Collection<JsonTransportDispWorkflowSpecificOrderLoggingRecord> value){ this.trackTraceEvents = value; }
	public Collection<JsonTransportDispWorkflowSpecificOrderLoggingRecord> getTrackTraceEvents(){ return trackTraceEvents; }
	
	
}
