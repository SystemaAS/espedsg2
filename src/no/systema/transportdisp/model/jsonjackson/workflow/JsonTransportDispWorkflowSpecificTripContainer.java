/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 1, 2015
 *
 */
public class JsonTransportDispWorkflowSpecificTripContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	                  
	private String tuavd = null;
	public void setTuavd(String value) {  this.tuavd = value; }
	public String getTuavd() { return this.tuavd;}
	
	private String tupro = null;
	public void setTupro(String value) {  this.tupro = value; }
	public String getTupro() { return this.tupro;}

	private String mode = null;
	public void setMode(String value) {  this.mode = value; }
	public String getMode() { return this.mode;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispWorkflowSpecificTripRecord> getonetrip;
	public void setGetonetrip(Collection<JsonTransportDispWorkflowSpecificTripRecord> value){ this.getonetrip = value; }
	public Collection<JsonTransportDispWorkflowSpecificTripRecord> getGetonetrip(){ return getonetrip; }
	
	private Collection<JsonTransportDispWorkflowSpecificTripRecord> updatetrip;
	public void setUpdatetrip(Collection<JsonTransportDispWorkflowSpecificTripRecord> value){ this.updatetrip = value; }
	public Collection<JsonTransportDispWorkflowSpecificTripRecord> getUpdatetrip(){ return updatetrip; }
	
}
