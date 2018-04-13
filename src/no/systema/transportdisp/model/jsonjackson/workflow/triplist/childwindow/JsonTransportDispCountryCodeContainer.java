/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Mar 24, 2016
 *
 */
public class JsonTransportDispCountryCodeContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String typ = null;
	public void setTyp(String value) {  this.typ = value; }
	public String getTyp() { return this.typ;}
	
	private String kode = null;
	public void setKode(String value) {  this.kode = value; }
	public String getKode() { return this.kode;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonTransportDispCountryCodeRecord> kodlista;
	public void setKodlista(Collection<JsonTransportDispCountryCodeRecord> value){ this.kodlista = value; }
	public Collection<JsonTransportDispCountryCodeRecord> getKodlista(){ return kodlista; }
	
			
	
}
