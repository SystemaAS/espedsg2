/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Jun 09, 2017
 * 
 *
 */
public class JsonTransportDispFrisokveiDocCodesRecord extends JsonAbstractGrandFatherRecord{

	
	private String kfkod = null;
	public void setKfkod(String value) {  this.kfkod = value; }
	public String getKfkod() {return this.kfkod;}
	
	private String kftxt = null;
	public void setKftxt(String value) {  this.kftxt = value; }
	public String getKftxt() {return this.kftxt;}
	
	
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
