/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.invoice;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Oct 16, 2015
 *
 */
public class JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer {
	
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}
	
	private String mode = null;
	public void setMode(String value) {  this.mode = value; }
	public String getMode() { return this.mode;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	//{"user" : "JOVO", "avd" : "75", "opd" : "19", "mode" : "G", "status" : "Ferdigmedlt Singlefaktura/Samlefaktura", "errMsg" : "", "invoiceReadyMark" : [ ] }
	//
		
	
}
