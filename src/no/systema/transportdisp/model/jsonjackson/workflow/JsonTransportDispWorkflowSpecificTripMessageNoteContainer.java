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
public class JsonTransportDispWorkflowSpecificTripMessageNoteContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String wstur = null;
	public void setWstur(String value) {  this.wstur = value; }
	public String getWstur() { return this.wstur;}
	
	private String wsavd = null;
	public void setWsavd(String value) {  this.wsavd = value; }
	public String getWsavd() { return this.wsavd;}
	
	private String part = null;
	public void setPart(String value) {  this.part = value; }
	public String getPart() { return this.part;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispWorkflowSpecificTripMessageNoteRecord> freetextlist;
	public void setFreetextlist(Collection<JsonTransportDispWorkflowSpecificTripMessageNoteRecord> value){ this.freetextlist = value; }
	public Collection<JsonTransportDispWorkflowSpecificTripMessageNoteRecord> getFreetextlist(){ return freetextlist; }
	
	
}
