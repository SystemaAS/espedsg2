/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.avdsignature;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Sep, 2018
 */
public class JsonTransportDispAvdGroupsContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTransportDispAvdGroupsRecord> inqAvdGrupp = null;
	public void setInqAvdGrupp(Collection<JsonTransportDispAvdGroupsRecord> value){ this.inqAvdGrupp = value;}
	public Collection<JsonTransportDispAvdGroupsRecord> getInqAvdGrupp(){ return this.inqAvdGrupp; }
}
