/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import java.util.Collection;


/**
 * @author oscardelatorre
 * @date Jun 09, 2017
 *
 */
public class JsonTransportDispFrisokveiCodesContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispFrisokveiCodesRecord> awblinelist;
	public void setAwblinelist(Collection<JsonTransportDispFrisokveiCodesRecord> value){ this.awblinelist = value; }
	public Collection<JsonTransportDispFrisokveiCodesRecord> getAwblinelist(){ return awblinelist; }
	
	
	
}
