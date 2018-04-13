/**
 * 
 */
package no.systema.sporringoppdrag.model.jsonjackson.topic;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Feb 11, 2015
 *
 */
public class JsonSporringOppdragSpecificTopicContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String knavn = null;
	public void setKnavn(String value) {  this.knavn = value; }
	public String getKnavn() { return this.knavn;}
	
	private String wsavd = null;
	public void setWsavd(String value) {  this.wsavd = value; }
	public String getWsavd() { return this.wsavd;}
	
	private String wsopd = null;
	public void setWsopd(String value) {  this.wsopd = value; }
	public String getWsopd() { return this.wsopd;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSporringOppdragSpecificTopicRecord> dspoppdrag;
	public void setDspoppdrag(Collection<JsonSporringOppdragSpecificTopicRecord> value){ this.dspoppdrag = value; }
	public Collection<JsonSporringOppdragSpecificTopicRecord> getDspoppdrag(){ return dspoppdrag; }
	
	
}
