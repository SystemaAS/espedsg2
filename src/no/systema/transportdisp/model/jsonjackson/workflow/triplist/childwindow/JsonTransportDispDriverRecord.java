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
public class JsonTransportDispDriverRecord extends JsonAbstractGrandFatherRecord{
	
	private String dransa = null;
	public void setDransa(String value) {  this.dransa = value; }
	public String getDransa() {return this.dransa;}
	
	private String drnavn = null;
	public void setDrnavn(String value) {  this.drnavn = value; }
	public String getDrnavn() {return this.drnavn;}
	
	
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
