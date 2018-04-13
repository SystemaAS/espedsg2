/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Maj 12, 2015
 */
public class JsonTransportDispLoadUnloadPlacesContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	
	private String soknvn = null;
	public void setSoknvn(String value){ this.soknvn = value;}
	public String getSoknvn(){ return this.soknvn; }
	
	private String sokkod = null;
	public void setSokkod(String value){ this.sokkod = value;}
	public String getSokkod(){ return this.sokkod; }
	
	private String caller = null;
	public void setCaller(String value){ this.caller = value;}
	public String getCaller(){ return this.caller; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTransportDispLoadUnloadPlacesRecord> inqlosslass = null;
	public void setInqlosslass(Collection<JsonTransportDispLoadUnloadPlacesRecord> value){ this.inqlosslass = value;}
	public Collection<JsonTransportDispLoadUnloadPlacesRecord> getInqlosslass(){ return this.inqlosslass; }
	
}
