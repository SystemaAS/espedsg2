/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Maj 5, 2015
 * 
 *
 */
public class JsonTransportDispWorkflowSpecificTripMessageNoteRecord extends JsonAbstractGrandFatherRecord{
	
	private String frtdt = null;
	public void setFrtdt(String value) {  this.frtdt = value; }
	public String getFrtdt() {return this.frtdt;}
	
	private String frtli = null;
	public void setFrtli(String value) {  this.frtli = value; }
	public String getFrtli() {return this.frtli;}
	
	private String frttxt = null;
	public void setFrttxt(String value) {  this.frttxt = value; }
	public String getFrttxt() {return this.frttxt;}
	
	private String frtkod = null;
	public void setFrtkod(String value) {  this.frtkod = value; }
	public String getFrtkod() {return this.frtkod;}
	
	private String frtles = null;
	public void setFrtles(String value) {  this.frtles = value; }
	public String getFrtles() {return this.frtles;}
	
	
	
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
