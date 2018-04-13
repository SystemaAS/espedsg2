/**
 * 
 */
package no.systema.sporringoppdrag.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Feb 13, 2015
 * 
 *
 */
public class JsonSporringOppdragSpecificTopicChildInvoiceRecord extends JsonAbstractGrandFatherRecord{
	
	private String fafakt = null;
	public void setFafakt(String value) {  this.fafakt = value; }
	public String getFafakt() {return this.fafakt;}
	
	private String faklnk = null;
	public void setFaklnk(String value) {  this.faklnk = value; }
	public String getFaklnk() {
		if(this.faklnk!=null){
			if(this.faklnk.contains("http") || this.faklnk.contains("HTTP") ){
				//nothing
			}else{
				this.faklnk = "no http prefix? ... JsonSporringOppdragSpecificTopicChildInvoiceRecord(faklnk)";
			}
		}
		return this.faklnk;
	}
	
	
	
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
