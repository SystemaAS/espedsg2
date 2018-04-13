/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.frankatur;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Aug 07, 2015
 */
public class JsonTransportDispFrankaturContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String franka = null;
	public void setFranka(String value){ this.franka = value;}
	public String getFranka(){ return this.franka; }
	
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
	
	private Collection<JsonTransportDispFrankaturRecord> frankaturer = null;
	public void setFrankaturer(Collection<JsonTransportDispFrankaturRecord> value){ this.frankaturer = value;}
	public Collection<JsonTransportDispFrankaturRecord> getFrankaturer(){ return this.frankaturer; }
}
