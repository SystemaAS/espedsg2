/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.shippinglists;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Apr 11, 2015
 * 
 *
 */
public class JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord extends JsonAbstractGrandFatherRecord{
	
	private String heavd = null;
	public void setHeavd(String value) {  this.heavd = value; }
	public String getHeavd() {return this.heavd;}
	
	private String heopd = null;
	public void setHeopd(String value) {  this.heopd = value; }
	public String getHeopd() {return this.heopd;}
	
	
	
	private String herfa = null;
	public void setHerfa(String value) {  this.herfa = value; }
	public String getHerfa() {return this.herfa;}
	
	private String henas = null;
	public void setHenas(String value) {  this.henas = value; }
	public String getHenas() {return this.henas;}
	
	private String henak = null;
	public void setHenak(String value) {  this.henak = value; }
	public String getHenak() {return this.henak;}
	
	private String hevs1 = null;
	public void setHevs1(String value) {  this.hevs1 = value; }
	public String getHevs1() {return this.hevs1;}
	
	private String wspos = null;
	public void setWspos(String value) {  this.wspos = value; }
	public String getWspos() {return this.wspos;}

	private String wsblorr = null;
	public void setWsblorr(String value) {  this.wsblorr = value; }
	public String getWsblorr() {return this.wsblorr;}
	
	private String hest = null;
	public void setHest(String value) {  this.hest = value; }
	public String getHest() {return this.hest;}
	
	
	private String hent = null;
	public void setHent(String value) {  this.hent = value; }
	public String getHent() {return this.hent;}
	
	private String hevkt = null;
	public void setHevkt(String value) {  this.hevkt = value; }
	public String getHevkt() {return this.hevkt;}
	
	private String hem3 = null;
	public void setHem3(String value) {  this.hem3 = value; }
	public String getHem3() {return this.hem3;}
	
	private String helm = null;
	public void setHelm(String value) {  this.helm = value; }
	public String getHelm() {return this.helm;}
	
	private String hepoen = null;
	public void setHepoen(String value) {  this.hepoen = value; }
	public String getHepoen() {return this.hepoen;}
	
	private String interninfo = null;
	public void setInterninfo(String value) {  this.interninfo = value; }
	public String getInterninfo() {return this.interninfo;}
	
	private String dftoll = null;
	public void setDftoll(String value) {  this.dftoll = value; }
	public String getDftoll() {return this.dftoll;}
	
	private String heklmo = null;
	public void setHeklmo(String value) {  this.heklmo = value; }
	public String getHeklmo() {return this.heklmo;}
	
	private String hedtmo = null;
	public void setHedtmo(String value) {  this.hedtmo = value; }
	public String getHedtmo() {return this.hedtmo;}
	
	private String hesgm = null;
	public void setHesgm(String value) {  this.hesgm = value; }
	public String getHesgm() {return this.hesgm;}
	
	private String ttstat = null;
	public void setTtstat(String value) {  this.ttstat = value; }
	public String getTtstat() {return this.ttstat;}

	
	
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
