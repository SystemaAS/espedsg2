/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 1, 2015
 *
 */
public class JsonTransportDispAvdContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String ie = null;
	public void setIe(String value) {  this.ie = value; }
	public String getIe() { return this.ie;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispAvdRecord> avdelningar;
	public void setAvdelningar(Collection<JsonTransportDispAvdRecord> value){ this.avdelningar = value; }
	public Collection<JsonTransportDispAvdRecord> getAvdelningar(){ return avdelningar; }
	
	
}
