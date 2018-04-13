/**
 * 
 */
package no.systema.sporringoppdrag.model.jsonjackson.topic.colli;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Feb 25, 2015
 * 
 *
 */
public class JsonSporringOppdragSpecificTopicSingleChildColliRecord extends JsonAbstractGrandFatherRecord{
	
	
	private String koid = null;
	public void setKoid(String value) {  this.koid = value; }
	public String getKoid() {return this.koid;}
	
	private String inndt = null;
	public void setInndt(String value) {  this.inndt = value; }
	public String getInndt() {return this.inndt;}
	
	private String innti = null;
	public void setInnti(String value) {  this.innti = value; }
	public String getInnti() {return this.innti;}
	
	private String utdt = null;
	public void setUtdt(String value) {  this.utdt = value; }
	public String getUtdt() {return this.utdt;}
	
	private String utti = null;
	public void setUtti(String value) {  this.utti = value; }
	public String getUtti() {return this.utti;}
	
	private String komlen = null;
	public void setKomlen(String value) {  this.komlen = value; }
	public String getKomlen() {return this.komlen;}
	
	private String kombre = null;
	public void setKombre(String value) {  this.kombre = value; }
	public String getKombre() {return this.kombre;}
	
	private String komhoy = null;
	public void setKomhoy(String value) {  this.komhoy = value; }
	public String getKomhoy() {return this.komhoy;}
	
	private String komvkt = null;
	public void setKomvkt(String value) {  this.komvkt = value; }
	public String getKomvkt() {return this.komvkt;}
	
	private String komm3 = null;
	public void setKomm3(String value) {  this.komm3 = value; }
	public String getKomm3() {return this.komm3;}
	
	private String komlm = null;
	public void setKomlm(String value) {  this.komlm = value; }
	public String getKomlm() {return this.komlm;}
	
	private String fvekt = null;
	public void setFvekt(String value) {  this.fvekt = value; }
	public String getFvekt() {return this.fvekt;}
	
	private String bilde1u = null;
	public void setBilde1u(String value) {  this.bilde1u = value; }
	public String getBilde1u() {return this.bilde1u;}
	
	private String bilde2u = null;
	public void setBilde2u(String value) {  this.bilde2u = value; }
	public String getBilde2u() {return this.bilde2u;}
	
	private String sumvkt = null;
	public void setSumvkt(String value) {  this.sumvkt = value; }
	public String getSumvkt() {return this.sumvkt;}
	
	private String summ3 = null;
	public void setSumm3(String value) {  this.summ3 = value; }
	public String getSumm3() {return this.summ3;}
	
	private String sumlm = null;
	public void setSumlm(String value) {  this.sumlm = value; }
	public String getSumlm() {return this.sumlm;}
	
	private String sumfvekt = null;
	public void setSumfvekt(String value) {  this.sumfvekt = value; }
	public String getSumfvekt() {return this.sumfvekt;}
	
	
	
  
  	
	
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
