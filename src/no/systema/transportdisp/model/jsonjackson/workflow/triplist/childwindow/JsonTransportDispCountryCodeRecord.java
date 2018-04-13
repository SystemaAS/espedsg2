/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Mar 22, 2016
 * 
 *
 */
public class JsonTransportDispCountryCodeRecord extends JsonAbstractGrandFatherRecord{
	
	private String zkod = null;
	public void setZkod(String value) {  this.zkod = value; }
	public String getZkod() {return this.zkod;}
	
	private String zskv = null;
	public void setZskv(String value) {  this.zskv = value; }
	public String getZskv() {return this.zskv;}
	
	private String ztxt = null;
	public void setZtxt(String value) {  this.ztxt = value; }
	public String getZtxt() {return this.ztxt;}
	
	
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
