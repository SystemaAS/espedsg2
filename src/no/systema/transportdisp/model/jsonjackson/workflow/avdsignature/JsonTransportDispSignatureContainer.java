/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.avdsignature;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Jun 10, 2015
 */
public class JsonTransportDispSignatureContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTransportDispSignatureRecord> signaturer = null;
	public void setSignaturer(Collection<JsonTransportDispSignatureRecord> value){ this.signaturer = value;}
	public Collection<JsonTransportDispSignatureRecord> getSignaturer(){ return this.signaturer; }
}
