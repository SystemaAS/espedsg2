/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Apr 30, 2015
 */
public class JsonTransportDispCustomerContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	//this is an internal variable in order to know what type of partner we are searching for (shipper, consignee or Agent)
	private String ctype = null;
	public void setCtype(String value){ this.ctype = value;}
	public String getCtype(){ return this.ctype; }
	
	private String soknvn = null;
	public void setSoknvn(String value){ this.soknvn = value;}
	public String getSoknvn(){ return this.soknvn; }
	
	private String sokknr = null;
	public void setSokknr(String value){ this.sokknr = value;}
	public String getSokknr(){ return this.sokknr; }
	
	private String kunpnsted = null;
	public void setKunpnsted(String value){ this.kunpnsted = value;}
	public String getKunpnsted(){ return this.kunpnsted; }
	
	private String wsvarnv = null;
	public void setWsvarnv(String value){ this.wsvarnv = value;}
	public String getWsvarnv(){ return this.wsvarnv; }
	
	private String maxv = null;
	public void setMaxv(String value){ this.maxv = value;}
	public String getMaxv(){ return this.maxv; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTransportDispCustomerRecord> inqcustomer = null;
	public void setInqcustomer(Collection<JsonTransportDispCustomerRecord> value){ this.inqcustomer = value;}
	public Collection<JsonTransportDispCustomerRecord> getInqcustomer(){ return this.inqcustomer; }
	
}
