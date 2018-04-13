/**
 * 
 */
package no.systema.sporringoppdrag.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Feb 11, 2015
 *
 */
public class JsonSporringOppdragTopicListRecord extends JsonAbstractGrandFatherRecord {
	
	private String heavd = null;
	public void setHeavd(String value) {  this.heavd = value; }
	public String getHeavd() { return this.heavd;}
	
	private String heopd = null;
	public void setHeopd(String value) {  this.heopd = value; }
	public String getHeopd() { return this.heopd;}
	
	private String hedtop = null;
	public void setHedtop(String value) {  this.hedtop = value; }
	public String getHedtop() { return this.hedtop;}
	
	private String hegn = null;
	public void setHegn(String value) {  this.hegn = value; }
	public String getHegn() { return this.hegn;}

	private String henas = null;
	public void setHenas(String value) {  this.henas = value; }
	public String getHenas() { return this.henas;}
	
	private String henak = null;
	public void setHenak(String value) {  this.henak = value; }
	public String getHenak() { return this.henak;}

	private String hefr = null;
	public void setHefr(String value) {  this.hefr = value; }
	public String getHefr() { return this.hefr;}
	
	private String hent = null;
	public void setHent(String value) {  this.hent = value; }
	public String getHent() { return this.hent;}
	
	private String hevkt = null;
	public void setHevkt(String value) {  this.hevkt = value; }
	public String getHevkt() { return this.hevkt;}
	
	private String hem3 = null;
	public void setHem3(String value) {  this.hem3 = value; }
	public String getHem3() { return this.hem3;}
	
	private String helm = null;
	public void setHelm(String value) {  this.helm = value; }
	public String getHelm() { return this.helm;}
	
	private String hesdf = null;
	public void setHesdf(String value) {  this.hesdf = value; }
	public String getHesdf() { return this.hesdf;}
	
	private String hesdt = null;
	public void setHesdt(String value) {  this.hesdt = value; }
	public String getHesdt() { return this.hesdt;}

	private String hepro = null;
	public void setHepro(String value) {  this.hepro = value; }
	public String getHepro() { return this.hepro;}
	
	private String xwsref = null;
	public void setXwsref(String value) {  this.xwsref = value; }
	public String getXwsref() { return this.xwsref;}

	private String poddato = null;
	public void setPoddato(String value) {  this.poddato = value; }
	public String getPoddato() { return this.poddato;}
	
	private String hekdpl = null;
	public void setHekdpl(String value) {  this.hekdpl = value; }
	public String getHekdpl() { return this.hekdpl;}
	
	private String faktsum = null;
	public void setFaktsum(String value) {  this.faktsum = value; }
	public String getFaktsum() { return this.faktsum;}
	
	
	/**
	 * 
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
