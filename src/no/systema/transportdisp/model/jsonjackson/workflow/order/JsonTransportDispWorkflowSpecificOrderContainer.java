/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Maj 8, 2015
 *
 */
public class JsonTransportDispWorkflowSpecificOrderContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String lng = null;
	public void setLng(String value) {  this.lng = value; }
	public String getLng() { return this.lng;}
	
	private String wsna = null;
	public void setWsna(String value) {  this.wsna = value; }
	public String getWsna() { return this.wsna;}
	
	private String kunde = null;
	public void setKunde(String value) {  this.kunde = value; }
	public String getKunde() { return this.kunde;}
	
	private String sdato = null;
	public void setSdato(String value) {  this.sdato = value; }
	public String getSdato() { return this.sdato;}

	private String stid = null;
	public void setStid(String value) {  this.stid = value; }
	public String getStid() { return this.stid;}

	private String wsavd = null;
	public void setWsavd(String value) {  this.wsavd = value; }
	public String getWsavd() { return this.wsavd;}

	private String wsopd = null;
	public void setWsopd(String value) {  this.wsopd = value; }
	public String getWsopd() { return this.wsopd;}

	private String dir = null;
	public void setDir(String value) {  this.dir = value; }
	public String getDir() { return this.dir;}

	private String extref = null;
	public void setExtref(String value) {  this.extref = value; }
	public String getExtref() { return this.extref;}

	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispWorkflowSpecificOrderRecord> dspoppdrag;
	public void setDspoppdrag(Collection<JsonTransportDispWorkflowSpecificOrderRecord> value){ this.dspoppdrag = value; }
	public Collection<JsonTransportDispWorkflowSpecificOrderRecord> getDspoppdrag(){ return dspoppdrag; }
	
	
}
