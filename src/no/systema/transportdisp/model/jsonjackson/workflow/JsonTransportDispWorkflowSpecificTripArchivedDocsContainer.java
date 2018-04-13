/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Maj 5, 2015
 *
 */
public class JsonTransportDispWorkflowSpecificTripArchivedDocsContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	                  
	private String wstur = null;
	public void setWstur(String value) {  this.wstur = value; }
	public String getWstur() { return this.wstur;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord> getdoctrip;
	public void setGetdoctrip(Collection<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord> value){ this.getdoctrip = value; }
	public Collection<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord> getGetdoctrip(){ return getdoctrip; }
	
	
}
