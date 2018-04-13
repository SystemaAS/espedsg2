/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 21, 2015
 *
 */
public class JsonTransportDispWorkflowListGodsAndLastListContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	                  
	private String wstur = null;
	public void setWstur(String value) {  this.wstur = value; }
	public String getWstur() { return this.wstur;}
	
	private String ifsfil = null;
	public void setIfsfil(String value) {  this.ifsfil = value; }
	public String getIfsfil() { return this.ifsfil;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	/* N/A at the moment
	private Collection<TODO> prtgodslist;
	public void setWrktriplist(Collection<JsonTransportDispWorkflowListRecord> value){ this.wrktriplist = value; }
	public Collection<JsonTransportDispWorkflowListRecord> getWrktriplist(){ return wrktriplist; }

	private Collection<TODO> prtlasteliste;
	public void setWrktriplist(Collection<JsonTransportDispWorkflowListRecord> value){ this.wrktriplist = value; }
	public Collection<JsonTransportDispWorkflowListRecord> getWrktriplist(){ return wrktriplist; }
	*/
	
	
	
}
