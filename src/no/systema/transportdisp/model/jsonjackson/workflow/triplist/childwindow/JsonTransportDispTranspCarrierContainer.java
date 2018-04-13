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
public class JsonTransportDispTranspCarrierContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String soktnr = null;
	public void setSoktnr(String value) {  this.soktnr = value; }
	public String getSoktnr() { return this.soktnr;}
	
	private String soknvn = null;
	public void setSoknvn(String value) {  this.soknvn = value; }
	public String getSoknvn() { return this.soknvn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private String error = null;
	public void setError(String value) {  this.error = value; }
	public String getError() { return this.error;}
	
	private Collection<JsonTransportDispTranspCarrierRecord> translist;
	public void setTranslist(Collection<JsonTransportDispTranspCarrierRecord> value){ this.translist = value; }
	public Collection<JsonTransportDispTranspCarrierRecord> getTranslist(){ return translist; }
	
	
	 
	
}
