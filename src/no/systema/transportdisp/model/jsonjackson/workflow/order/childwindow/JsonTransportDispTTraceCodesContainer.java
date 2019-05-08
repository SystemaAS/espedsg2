/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import java.util.Collection;


/**
 * @author oscardelatorre
 * @date May 09, 2019
 *
 */
public class JsonTransportDispTTraceCodesContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispTTraceCodesRecord> inqTTactList;
	public void setInqTTactList(Collection<JsonTransportDispTTraceCodesRecord> value){ this.inqTTactList = value; }
	public Collection<JsonTransportDispTTraceCodesRecord> getInqTTactList(){ return inqTTactList; }
	
	
	
}
