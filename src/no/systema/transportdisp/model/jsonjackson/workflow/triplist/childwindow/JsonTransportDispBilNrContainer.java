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
public class JsonTransportDispBilNrContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String ie = null;
	public void setIe(String value) {  this.ie = value; }
	public String getIe() { return this.ie;}
	
	private String sokbnr = null;
	public void setSokbnr(String value) {  this.sokbnr = value; }
	public String getSokbnr() { return this.sokbnr;}
	
	private String soktnr = null;
	public void setSoktnr(String value) {  this.soktnr = value; }
	public String getSoktnr() { return this.soktnr;}
	
	private String sokut = null;
	public void setSokut(String value) {  this.sokut = value; }
	public String getSokut() { return this.sokut;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private String error = null;
	public void setError(String value) {  this.error = value; }
	public String getError() { return this.error;}
	
	private Collection<JsonTransportDispBilNrRecord> bilnrlist;
	public void setBilnrlist(Collection<JsonTransportDispBilNrRecord> value){ this.bilnrlist = value; }
	public Collection<JsonTransportDispBilNrRecord> getBilnrlist(){ return bilnrlist; }
	
			
	
}
