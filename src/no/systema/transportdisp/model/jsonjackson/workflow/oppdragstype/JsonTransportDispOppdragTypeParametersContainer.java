/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Aug 12, 2015
 */
public class JsonTransportDispOppdragTypeParametersContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String avd = null;
	public void setAvd(String value){ this.avd = value;}
	public String getAvd(){ return this.avd; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTransportDispOppdragTypeParametersRecord> stdParametere = null;
	public void setStdParametere(Collection<JsonTransportDispOppdragTypeParametersRecord> value){ this.stdParametere = value;}
	public Collection<JsonTransportDispOppdragTypeParametersRecord> getStdParametere(){ return this.stdParametere; }
	
}
