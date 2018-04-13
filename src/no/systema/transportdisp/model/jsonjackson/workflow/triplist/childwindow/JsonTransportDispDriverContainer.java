/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 6, 2015
 *
 */
public class JsonTransportDispDriverContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String soksjn = null;
	public void setSoksjn(String value) {  this.soksjn = value; }
	public String getSoksjn() { return this.soksjn;}
	
	private String soksja = null;
	public void setSoksja(String value) {  this.soksja = value; }
	public String getSoksja() { return this.soksja;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private String error = null;
	public void setError(String value) {  this.error = value; }
	public String getError() { return this.error;}
	
	private Collection<JsonTransportDispDriverRecord> sjoflist;
	public void setSjoflist(Collection<JsonTransportDispDriverRecord> value){ this.sjoflist = value; }
	public Collection<JsonTransportDispDriverRecord> getSjoflist(){ return sjoflist; }
	
	
}
