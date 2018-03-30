/**
 * 
 */
package no.systema.external.skat.z.maintenance.model;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Mar 29, 2018
 *
 */
public class JsonMaintDkxkodfContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintDkxkodfRecord> list;
	public void setList(Collection<JsonMaintDkxkodfRecord> value){ this.list = value; }
	public Collection<JsonMaintDkxkodfRecord> getList(){ return list; }
	
}
