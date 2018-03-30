/**
 * 
 */
package no.systema.external.tds.z.maintenance.model;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Mar 30, 2018
 *
 */
public class JsonMaintSvthaContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSvthaRecord> dtoList;
	public void setDtoList(Collection<JsonMaintSvthaRecord> value){ this.dtoList = value; }
	public Collection<JsonMaintSvthaRecord> getDtoList(){ return dtoList; }
	
}
