/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.model;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 9, 2016
 *
 */
public class JsonMaintSadFellesKodtsiContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadFellesKodtsiRecord> list;
	public void setList(Collection<JsonMaintSadFellesKodtsiRecord> value){ this.list = value; }
	public Collection<JsonMaintSadFellesKodtsiRecord> getList(){ return list; }
	
}
