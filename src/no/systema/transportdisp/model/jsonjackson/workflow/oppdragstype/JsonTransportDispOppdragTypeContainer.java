/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Aug 07, 2015
 */
public class JsonTransportDispOppdragTypeContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String opdTyp = null;
	public void setOpdTyp(String value){ this.opdTyp = value;}
	public String getOpdTyp(){ return this.opdTyp; }
	
	private String beskr = null;
	public void setBeskr(String value){ this.beskr = value;}
	public String getBeskr(){ return this.beskr; }
	
	private String getval = null;
	public void setGetval(String value){ this.getval = value;}
	public String getGetval(){ return this.getval; }
	
	private String fullInfo = null;
	public void setFullInfo(String value){ this.fullInfo = value;}
	public String getFullInfo(){ return this.fullInfo; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTransportDispOppdragTypeRecord> oppdragsTyper = null;
	public void setOppdragsTyper(Collection<JsonTransportDispOppdragTypeRecord> value){ this.oppdragsTyper = value;}
	public Collection<JsonTransportDispOppdragTypeRecord> getOppdragsTyper(){ return this.oppdragsTyper; }
}
