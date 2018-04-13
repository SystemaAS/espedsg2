/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Apr 6, 2015
 * 
 *
 */
public class JsonTransportDispBilCodeRecord extends JsonAbstractGrandFatherRecord{
	
	private String bkkode = null;
	public void setBkkode(String value) {  this.bkkode = value; }
	public String getBkkode() {return this.bkkode;}
	
	private String bktxt = null;
	public void setBktxt(String value) {  this.bktxt = value; }
	public String getBktxt() {return this.bktxt;}
	
	
	
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
