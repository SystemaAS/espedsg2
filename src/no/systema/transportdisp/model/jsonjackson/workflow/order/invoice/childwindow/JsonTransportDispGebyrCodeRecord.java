/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Oct 08, 2015
 * 
 *
 */
public class JsonTransportDispGebyrCodeRecord extends JsonAbstractGrandFatherRecord{
	
	private String kgekod = null;
	public void setKgekod(String value) {  this.kgekod = value; }
	public String getKgekod() {return this.kgekod;}
	
	private String kgenot = null;
	public void setKgenot(String value) {  this.kgenot = value; }
	public String getKgenot() {return this.kgenot;}
	
	private String kgenhN = null;
	public void setKgenhN(String value) {  this.kgenhN = value; }
	public String getKgenhN() {return this.kgenhN;}
	
	
	private String intMVAx = null;
	public void setIntMVAx(String value) {  this.intMVAx = value; }
	public String getIntMVAx() {return this.intMVAx;}
	
	private String kosMVAx = null;
	public void setKosMVAx(String value) {  this.kosMVAx = value; }
	public String getKosMVAx() {return this.kosMVAx;}
	
	private String kg2tyt = null;
	public void setKg2tyt(String value) {  this.kg2tyt = value; }
	public String getKg2tyt() {return this.kg2tyt;}
	
	private String kgenhE = null;
	public void setKgenhE(String value) {  this.kgenhE = value; }
	public String getKgenhE() {return this.kgenhE;}
	
	private String kgenhT = null;
	public void setKgenhT(String value) {  this.kgenhT = value; }
	public String getKgenhT() {return this.kgenhT;}
	
	private String kgeavr = null;
	public void setKgeavr(String value) {  this.kgeavr = value; }
	public String getKgeavr() {return this.kgeavr;}
	
	private String kg2srt = null;
	public void setKg2srt(String value) {  this.kg2srt = value; }
	public String getKg2srt() {return this.kg2srt;}
	
	private String kgeutl = null;
	public void setKgeutl(String value) {  this.kgeutl = value; }
	public String getKgeutl() {return this.kgeutl;}
	
	private String kg2lev = null;
	public void setKg2lev(String value) {  this.kg2lev = value; }
	public String getKg2lev() {return this.kg2lev;}
	
	private String kg2krt = null;
	public void setKg2krt(String value) {  this.kg2krt = value; }
	public String getKg2krt() {return this.kg2krt;}
	
	
	
	
	
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
