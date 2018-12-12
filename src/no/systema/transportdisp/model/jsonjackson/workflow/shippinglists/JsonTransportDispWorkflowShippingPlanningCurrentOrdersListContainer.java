/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.shippinglists;

import java.util.Collection;

import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * @author oscardelatorre
 * @date Apr 11, 2015
 *
 */
public class JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	                  
	private String wstur = null;
	public void setWstur(String value) {  this.wstur = value; }
	public String getWstur() { return this.wstur;}
	
	private String maxWarning = null;
	public void setMaxWarning(String value) {  this.maxWarning = value; }
	public String getMaxWarning() { return this.maxWarning;}

	private String error = null;
	public void setError(String value) {  this.error = value; }
	public String getError() { return this.error;}
	
	private Double hentTotalAmount = 0.000D;
	public void setHentTotalAmount(Double value) {  this.hentTotalAmount = value; }
	public String getHentTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.hentTotalAmount, 0, false);
	}
	private Double hevktTotalAmount = 0.000D;
	public void setHevktTotalAmount(Double value) {  this.hevktTotalAmount = value; }
	public String getHevktTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.hevktTotalAmount, 0, false);
	}
	private Double hem3TotalAmount = 0.000D;
	public void setHem3TotalAmount(Double value) {  this.hem3TotalAmount = value; }
	public String getHem3TotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.hem3TotalAmount, 3, false);
	}
	private Double helmTotalAmount = 0.000D;
	public void setHelmTotalAmount(Double value) {  this.helmTotalAmount = value; }
	public String getHelmTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.helmTotalAmount, 3, false);
	}
	
	
	private Collection<JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord> orderlistlandtur;
	public void setOrderlistlandtur(Collection<JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord> value){ this.orderlistlandtur = value; }
	public Collection<JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord> getOrderlistlandtur(){ return orderlistlandtur; }
	
	
}
