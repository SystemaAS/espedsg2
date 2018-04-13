/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Apr 1, 2015
 * 
 *
 */
public class JsonTransportDispAvdRecord extends JsonAbstractGrandFatherRecord{
	
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() {return this.avd;}
	
	private String namn = null;
	public void setNamn(String value) {  this.namn = value; }
	public String getNamn() {return this.namn;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() {return this.status;}
	
	private String koaIE = null;
	public void setKoaIE(String value) {  this.koaIE = value; }
	public String getKoaIE() {return this.koaIE;}
	
	private String koaLK = null;
	public void setKoaLK(String value) {  this.koaLK = value; }
	public String getKoaLK() {return this.koaLK;}
	
	
	
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
