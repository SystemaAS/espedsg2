/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Sep 29, 2015
 * 
 *
 */
public class JsonTransportDispTollstedCodesRecord extends JsonAbstractGrandFatherRecord{

	private String tollst = null;
	public void setTollst(String value) {  this.tollst = value; }
	public String getTollst() {return this.tollst;}
	
	private String navn = null;
	public void setNavn(String value) {  this.navn = value; }
	public String getNavn() {return this.navn;}
	
	
	
	
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
