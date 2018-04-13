/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import java.util.*;
import java.lang.reflect.Field;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author oscardelatorre
 * @date Dec 11, 2017
 * 
 *
 */
public class JsonTransportDispFrisokveiGiltighetsListRecord extends JsonAbstractGrandFatherRecord {
	
	//This is required in order to know if is an update or create new record
	//Since there is id, we must have a mechanism to know if the user is updating or creating a new record
	private String isModeUpdate = null;
	public void setIsModeUpdate(String value) {  this.isModeUpdate = value; }
	public String getIsModeUpdate() {return this.isModeUpdate;}
		
	
	private String cokunr = null;
	public void setCokunr(String value) {  this.cokunr = value; }
	public String getCokunr() {return this.cokunr;}
	
	private String coavd = null;
	public void setCoavd(String value) {  this.coavd = value; }
	public String getCoavd() {return this.coavd;}
		
	
	private String cofri = null;
	public void setCofri(String value) {  this.cofri = value; }
	public String getCofri() {return this.cofri;}
	
	private String cotxt = null;
	public void setCotxt(String value) {  this.cotxt = value; }
	public String getCotxt() {return this.cotxt;}
	
	

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
