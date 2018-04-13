/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.validationbackend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
/**
 * @author oscardelatorre
 * @date Aug 21, 2015
 *
 */
public class JsonTransportDispWorkflowSpecificOrderValidationBackendContainer {
	
	private List<String> errMsgListFromValidationBackend = new ArrayList<String>();
	public void setErrMsgListFromValidationBackend(List<String> value) {  this.errMsgListFromValidationBackend = value; }
	public List<String> getErrMsgListFromValidationBackend() { return this.errMsgListFromValidationBackend;}
	
	private List<String> infoMsgListFromValidationBackend = new ArrayList<String>();
	public void setInfoMsgListFromValidationBackend(List<String> value) {  this.infoMsgListFromValidationBackend = value; }
	public List<String> getInfoMsgListFromValidationBackend() { return this.infoMsgListFromValidationBackend;}
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String heavd = null;
	public void setHeavd(String value) {  this.heavd = value; }
	public String getHeavd() { return this.heavd;}
	
	private String heopd = null;
	public void setHeopd(String value) {  this.heopd = value; }
	public String getHeopd() { return this.heopd;}
	
	private String mode = null;
	public void setMode(String value) {  this.mode = value; }
	public String getMode() { return this.mode;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private String infoMsg = null;
	public void setInfoMsg(String value) {  this.infoMsg = value; }
	public String getInfoMsg() { return this.infoMsg;}
	
	private Collection<JsonTransportDispWorkflowSpecificOrderRecord> ordervalidate;
	public void setOrdervalidate(Collection<JsonTransportDispWorkflowSpecificOrderRecord> value){ this.ordervalidate = value; }
	public Collection<JsonTransportDispWorkflowSpecificOrderRecord> getOrdervalidate(){ return ordervalidate; }
	
}
