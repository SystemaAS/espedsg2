/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Mar 31, 2015
 *
 */
public class JsonTransportDispWorkflowListContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	                  
	private String wssst = null;
	public void setWssst(String value) {  this.wssst = value; }
	public String getWssst() { return this.wssst;}
	
	private String wssavd = null;
	public void setWssavd(String value) {  this.wssavd = value; }
	public String getWssavd() { return this.wssavd;}
	
	private String frames = null;
	public void setFrames(String value) {  this.frames = value; }
	public String getFrames() { return this.frames;}
	
	private String maxWarning = null;
	public void setMaxWarning(String value) {  this.maxWarning = value; }
	public String getMaxWarning() { return this.maxWarning;}

	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispWorkflowListRecord> wrktriplist;
	public void setWrktriplist(Collection<JsonTransportDispWorkflowListRecord> value){ this.wrktriplist = value; }
	public Collection<JsonTransportDispWorkflowListRecord> getWrktriplist(){ return wrktriplist; }
	
	/**
	 * Used for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
}
