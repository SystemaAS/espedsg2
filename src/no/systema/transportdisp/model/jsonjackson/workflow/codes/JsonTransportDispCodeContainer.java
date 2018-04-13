/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.codes;

import java.util.Collection;


/**
 * General Code Container for Tvinn general codes
 * 
 * 
 *
 * @author oscardelatorre
 * @date Jun 13, 2014
 *
 */
public class JsonTransportDispCodeContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ie = null;
	public void setIe(String value){ this.ie = value;}
	public String getIe(){ return this.ie; }
	
	private String typ = null;
	public void setTyp(String value){ this.typ = value;}
	public String getTyp(){ return this.typ; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTransportDispCodeRecord> kodlista = null;
	public void setKodlista(Collection<JsonTransportDispCodeRecord> value){ this.kodlista = value;}
	public Collection<JsonTransportDispCodeRecord> getKodlista(){ return this.kodlista; }
	
}
