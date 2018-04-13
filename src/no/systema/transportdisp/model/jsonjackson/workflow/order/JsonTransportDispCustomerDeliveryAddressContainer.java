/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date May 27, 2015
 */
public class JsonTransportDispCustomerDeliveryAddressContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	//this is an internal variable in order to know what type of partner we are searching for (shipper, consignee or Agent)
	private String wkundnr = null;
	public void setWkundnr(String value){ this.wkundnr = value;}
	public String getWkundnr(){ return this.wkundnr; }
	
	private String wvadrnr = null;
	public void setWvadrnr(String value){ this.wvadrnr = value;}
	public String getWvadrnr(){ return this.wvadrnr; }
	
	private String wvadrna = null;
	public void setWvadrna(String value){ this.wvadrna = value;}
	public String getWvadrna(){ return this.wvadrna; }
	
	private String maxv = null;
	public void setMaxv(String value){ this.maxv = value;}
	public String getMaxv(){ return this.maxv; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTransportDispCustomerDeliveryAddressRecord> inqdeladdr = null;
	public void setInqdeladdr(Collection<JsonTransportDispCustomerDeliveryAddressRecord> value){ this.inqdeladdr = value;}
	public Collection<JsonTransportDispCustomerDeliveryAddressRecord> getInqdeladdr(){ return this.inqdeladdr; }
	
}
