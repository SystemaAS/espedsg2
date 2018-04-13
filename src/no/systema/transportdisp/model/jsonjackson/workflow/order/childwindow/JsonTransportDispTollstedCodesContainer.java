/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import java.util.Collection;


/**
 * @author oscardelatorre
 * @date Sep 29, 2015
 *
 */
public class JsonTransportDispTollstedCodesContainer {
	
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String kode = null;
	public void setKode(String value) {  this.kode = value; }
	public String getKode() { return this.kode;}
	
	private String tekst = null;
	public void setTekst(String value) {  this.tekst = value; }
	public String getTekst() { return this.tekst;}
	
	private String fullInfo = null;
	public void setFullInfo(String value) {  this.fullInfo = value; }
	public String getFullInfo() { return this.fullInfo;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispTollstedCodesRecord> tollstedsKoder;
	public void setTollstedsKoder(Collection<JsonTransportDispTollstedCodesRecord> value){ this.tollstedsKoder = value; }
	public Collection<JsonTransportDispTollstedCodesRecord> getTollstedsKoder(){ return tollstedsKoder; }
	
	
	
}
