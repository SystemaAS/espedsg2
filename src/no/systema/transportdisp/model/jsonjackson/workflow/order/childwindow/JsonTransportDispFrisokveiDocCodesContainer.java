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
public class JsonTransportDispFrisokveiDocCodesContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispFrisokveiDocCodesRecord> awblinelist;
	public void setAwblinelist(Collection<JsonTransportDispFrisokveiDocCodesRecord> value){ this.awblinelist = value; }
	public Collection<JsonTransportDispFrisokveiDocCodesRecord> getAwblinelist(){ return awblinelist; }
	
	
	
}
