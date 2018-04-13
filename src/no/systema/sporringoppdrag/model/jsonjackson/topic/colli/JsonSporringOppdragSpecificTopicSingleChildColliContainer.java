/**
 * 
 */
package no.systema.sporringoppdrag.model.jsonjackson.topic.colli;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Feb 25, 2015
 *
 */
public class JsonSporringOppdragSpecificTopicSingleChildColliContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String sndn = null;
	public void setSndn(String value) {  this.sndn = value; }
	public String getSndn() { return this.sndn;}
	
	private String besk = null;
	public void setBesk(String value) {  this.besk = value; }
	public String getBesk() { return this.besk;}
	
	
	private Collection<JsonSporringOppdragSpecificTopicSingleChildColliRecord> dspcolli;
	public void setDspcolli(Collection<JsonSporringOppdragSpecificTopicSingleChildColliRecord> value){ this.dspcolli = value; }
	public Collection<JsonSporringOppdragSpecificTopicSingleChildColliRecord> getDspcolli(){ return dspcolli; }
	
	
}
