/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Jan 2019
 *
 */
public class JsonTransportDispWorkflowFellesutskriftContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispWorkflowFellesutskriftRecord> fellesutskrift;
	public void setFellesutskrift(Collection<JsonTransportDispWorkflowFellesutskriftRecord> value){ this.fellesutskrift = value; }
	public Collection<JsonTransportDispWorkflowFellesutskriftRecord> getFellesutskrift(){ return fellesutskrift; }
	
	
}
