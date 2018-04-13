/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Sep 15, 2015
 *
 */
public class JsonTransportDispWorkflowPostUpdateContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	                  
	private String heavd = null;
	public void setHeavd(String value) {  this.heavd = value; }
	public String getHeavd() { return this.heavd;}
	
	private String heopd = null;
	public void setHeopd(String value) {  this.heopd = value; }
	public String getHeopd() { return this.heopd;}
	
	private String infoMsg = null;
	public void setInfoMsg(String value) {  this.infoMsg = value; }
	public String getInfoMsg() { return this.infoMsg;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	/*
	private Collection<TODO> orderPostUpdate;
	public void setOrderPostUpdate(Collection<TODO> value){ this.orderPostUpdate = value; }
	public Collection<TODO> getOrderPostUpdate(){ return orderPostUpdate; }
	*/
	
}
