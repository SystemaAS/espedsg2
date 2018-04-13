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
public class JsonTransportDispPackingCodesRecord extends JsonAbstractGrandFatherRecord{

	private String enkode = null;
	public void setEnkode(String value) {  this.enkode = value; }
	public String getEnkode() {return this.enkode;}
	
	private String entext = null;
	public void setEntext(String value) {  this.entext = value; }
	public String getEntext() {return this.entext;}
	
	private String enlen = null;
	public void setEnlen(String value) {  this.enlen = value; }
	public String getEnlen() {return this.enlen;}
	
	private String enbrd = null;
	public void setEnbrd(String value) {  this.enbrd = value; }
	public String getEnbrd() {return this.enbrd;}
	
	private String enhoy = null;
	public void setEnhoy(String value) {  this.enhoy = value; }
	public String getEnhoy() {return this.enhoy;}
	
	private String enlm2 = null;
	public void setEnlm2(String value) {  this.enlm2 = value; }
	public String getEnlm2() {return this.enlm2;}
	
	private String enlm = null;
	public void setEnlm(String value) {  this.enlm = value; }
	public String getEnlm() {return this.enlm;}
	
	
	
	
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
