/**
 * 
 */
package no.systema.sporringoppdrag.model.jsonjackson.topic.invoice;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Feb 25, 2015
 * 
 *
 */
public class JsonSporringOppdragSpecificTopicSingleChildInvoiceRecord extends JsonAbstractGrandFatherRecord{
	
	private String geb = null;
	public void setGeb(String value) {  this.geb = value; }
	public String getGeb() {return this.geb;}
	
	private String vtxt = null;
	public void setVtxt(String value) {  this.vtxt = value; }
	public String getVtxt() {return this.vtxt;}
	
	private String beln = null;
	public void setBeln(String value) {  this.beln = value; }
	public String getBeln() {return this.beln;}
	
	private String belm = null;
	public void setBelm(String value) {  this.belm = value; }
	public String getBelm() {return this.belm;}
	
	private String belb = null;
	public void setBelb(String value) {  this.belb = value; }
	public String getBelb() {return this.belb;}
	
	private String mkod = null;
	public void setMkod(String value) {  this.mkod = value; }
	public String getMkod() {return this.mkod;}
	
	private String totn = null;
	public void setTotn(String value) {  this.totn = value; }
	public String getTotn() {return this.totn;}
	
	private String totm = null;
	public void setTotm(String value) {  this.totm = value; }
	public String getTotm() {return this.totm;}
	
	private String totb = null;
	public void setTotb(String value) {  this.totb = value; }
	public String getTotb() {return this.totb;}
	
	private String totpli = null;
	public void setTotpli(String value) {  this.totpli = value; }
	public String getTotpli() {return this.totpli;}
	
	private String totfri = null;
	public void setTotfri(String value) {  this.totfri = value; }
	public String getTotfri() {return this.totfri;}
	
	
	
	
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
