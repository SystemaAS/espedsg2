/**
 * 
 */
package no.systema.sporringoppdrag.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
import no.systema.main.util.AppConstants;

/**
 * @author oscardelatorre
 * @date Feb 13, 2015
 * 
 *
 */
public class JsonSporringOppdragSpecificTopicChildFriesokeVeierRecord extends JsonAbstractGrandFatherRecord{
	
	private String kfsotx = null;
	public void setKfsotx(String value) {  this.kfsotx = value; }
	public String getKfsotx() {return this.kfsotx;}
	
	private String wssok = null;
	public void setWssok(String value) {  this.wssok = value; }
	public String getWssok() {return this.wssok;}
	
	private String wssokurl = null;
	public void setWssokurl(String value) {  this.wssokurl = value; }
	public String getWssokurl() {return this.wssokurl;}
	
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
