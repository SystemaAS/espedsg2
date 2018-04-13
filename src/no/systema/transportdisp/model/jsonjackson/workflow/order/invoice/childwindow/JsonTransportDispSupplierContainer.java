/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Oct 08, 2015
 */
public class JsonTransportDispSupplierContainer {
	
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String kode = null;
	public void setKode(String value) {  this.kode = value; }
	public String getKode() { return this.kode;}
	
	private String tekst = null;
	public void setTekst(String value) {  this.tekst = value; }
	public String getTekst() { return this.tekst;}
	
	private String getval = null;
	public void setGetval(String value) {  this.getval = value; }
	public String getGetval() { return this.getval;}
	
	private String fullinfo = null;
	public void setFullinfo(String value) {  this.fullinfo = value; }
	public String getFullinfo() { return this.fullinfo;}
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTransportDispSupplierRecord> leverandorer = null;
	public void setLeverandorer(Collection<JsonTransportDispSupplierRecord> value){ this.leverandorer = value;}
	public Collection<JsonTransportDispSupplierRecord> getLeverandorer(){ return this.leverandorer; }
	
}
