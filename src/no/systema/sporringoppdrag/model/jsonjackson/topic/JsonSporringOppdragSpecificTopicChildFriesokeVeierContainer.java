/**
 * 
 */
package no.systema.sporringoppdrag.model.jsonjackson.topic;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Feb 13, 2015
 *
 */
public class JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String part = null;
	public void setPart(String value) {  this.part = value; }
	public String getPart() { return this.part;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSporringOppdragSpecificTopicChildFriesokeVeierRecord> getfriesokeveier;
	public void setGetfriesokeveier(Collection<JsonSporringOppdragSpecificTopicChildFriesokeVeierRecord> value){ this.getfriesokeveier = value; }
	public Collection<JsonSporringOppdragSpecificTopicChildFriesokeVeierRecord> getGetfriesokeveier(){ return getfriesokeveier; }
	
	
}
