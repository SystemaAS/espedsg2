/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.invoice;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Aug 14, 2015
 *
 */
public class JsonTransportDispWorkflowSpecificOrderInvoiceContainer {
	
	private String totalNumberOfItemLines = null;
	public void setTotalNumberOfItemLines(String value) {  this.totalNumberOfItemLines = value; }
	public String getTotalNumberOfItemLines() { return this.totalNumberOfItemLines;}
	
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String lin = null;
	public void setLin(String value) {  this.lin = value; }
	public String getLin() { return this.lin;}
	
	private String type = null;
	public void setType(String value) {  this.type = value; }
	public String getType() { return this.type;}
	
	private String mode = null;
	public void setMode(String value) {  this.mode = value; }
	public String getMode() { return this.mode;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private String readyMarkStatus = null;
	public void setReadyMarkStatus(String value) {  this.readyMarkStatus = value; }
	public String getReadyMarkStatus() { return this.readyMarkStatus;}
	
	
	private Collection<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> invoiceLines;
	public void setInvoiceLines(Collection<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> value){ this.invoiceLines = value; }
	public Collection<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> getInvoiceLines(){ return invoiceLines; }
	
	private Collection<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> invoiceLineUpdate;
	public void setInvoiceLineUpdate(Collection<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> value){ this.invoiceLineUpdate = value; }
	public Collection<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> getInvoiceLineUpdate(){ return invoiceLineUpdate; }
	
	
}
