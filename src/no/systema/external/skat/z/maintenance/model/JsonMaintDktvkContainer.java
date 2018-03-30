/**
 * 
 */
package no.systema.external.skat.z.maintenance.model;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Jun 13, 2016
 *
 */
public class JsonMaintDktvkContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintDktvkRecord> list;
	public void setList(Collection<JsonMaintDktvkRecord> value){ this.list = value; }
	public Collection<JsonMaintDktvkRecord> getList(){ return list; }
	
}
