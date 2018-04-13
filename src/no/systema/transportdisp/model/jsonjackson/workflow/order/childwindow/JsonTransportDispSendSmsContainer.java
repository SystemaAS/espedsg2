/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * @author oscardelatorre
 * @date Dec 07, 2017
 *
 */
public class JsonTransportDispSendSmsContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String heavd = null;
	public void setHeavd(String value) {  this.heavd = value; }
	public String getHeavd() { return this.heavd;}
	
	private String heopd = null;
	public void setHeopd(String value) {  this.heopd = value; }
	public String getHeopd() { return this.heopd;}
	
	private String turnr = null;
	public void setTurnr(String value) {  this.turnr = value; }
	public String getTurnr() { return this.turnr;}
	
	private String sprak = null;
	public void setSprak(String value) {  this.sprak = value; }
	public String getSprak() { return this.sprak;}
	
	private String smsnr = null;
	public void setSmsnr(String value) {  this.smsnr = value; }
	public String getSmsnr() { return this.smsnr;}
	
	private String type = null;
	public void setType(String value) {  this.type = value; }
	public String getType() { return this.type;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	/**
	 * User for java reflection in other classes
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
