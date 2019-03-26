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
public class JsonTransportDispTranspCarrierRecord extends JsonAbstractGrandFatherRecord{
	
	
	private String vmtran = null;
	public void setVmtran(String value) {  this.vmtran = value; }
	public String getVmtran() {return this.vmtran;}
	
	private String navn = null;
	public void setNavn(String value) {  this.navn = value; }
	public String getNavn() {return this.navn;}
	
	private String typKod = null;
	public void setTypKod(String value) {  this.typKod = value; }
	public String getTypKod() {return this.typKod;}
	
	private String typTxt = null;
	public void setTypTxt(String value) {  this.typTxt = value; }
	public String getTypTxt() {return this.typTxt;}

	
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
