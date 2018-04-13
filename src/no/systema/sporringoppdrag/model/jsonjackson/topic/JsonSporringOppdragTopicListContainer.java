/**
 * 
 */
package no.systema.sporringoppdrag.model.jsonjackson.topic;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Feb 4, 2015
 *
 */
public class JsonSporringOppdragTopicListContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}
	
	private String knavn = null;
	public void setKnavn(String value) {  this.knavn = value; }
	public String getKnavn() { return this.knavn;}
	
	private String visFaktSum = null;
	public void setVisFaktSum(String value) {  this.visFaktSum = value; }
	public String getVisFaktSum() { return this.visFaktSum;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSporringOppdragTopicListRecord> qryoppdrag;
	public void setQryoppdrag(Collection<JsonSporringOppdragTopicListRecord> value){ this.qryoppdrag = value; }
	public Collection<JsonSporringOppdragTopicListRecord> getQryoppdrag(){ return qryoppdrag; }
	
}
