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
public class JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord extends JsonAbstractGrandFatherRecord{
	
	private String heavd = null;
	public void setHeavd(String value) {  this.heavd = value; }
	public String getHeavd() {return this.heavd;}
	
	private String heopd = null;
	public void setHeopd(String value) {  this.heopd = value; }
	public String getHeopd() {return this.heopd;}
	
	private String herfa = null;
	public void setHerfa(String value) {  this.herfa = value; }
	public String getHerfa() {return this.herfa;}
	
	private String hepro = null;
	public void setHepro(String value) {  this.hepro = value; }
	public String getHepro() {return this.hepro;}
	
	private String henas = null;
	public void setHenas(String value) {  this.henas = value; }
	public String getHenas() {return this.henas;}
	
	private String henak = null;
	public void setHenak(String value) {  this.henak = value; }
	public String getHenak() {return this.henak;}
	
	private String helka = null;
	public void setHelka(String value) {  this.helka = value; }
	public String getHelka() {return this.helka;}
	
	private String heads3 = null;
	public void setHeads3(String value) {  this.heads3 = value; }
	public String getHeads3() {return this.heads3;}
	
	private String hetri = null;
	public void setHetri(String value) {  this.hetri = value; }
	public String getHetri() {return this.hetri;}
	
	private String headk3 = null;
	public void setHeadk3(String value) {  this.headk3 = value; }
	public String getHeadk3() {return this.headk3;}
	
	
	private String hevs1 = null;
	public void setHevs1(String value) {  this.hevs1 = value; }
	public String getHevs1() {return this.hevs1;}
	
	private String hesdf = null;
	public void setHesdf(String value) {  this.hesdf = value; }
	public String getHesdf() {return this.hesdf;}

	private String hesdt = null;
	public void setHesdt(String value) {  this.hesdt = value; }
	public String getHesdt() {return this.hesdt;}
	
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
	
	private String trsdfd = null;
	public void setTrsdfd(String value) {  this.trsdfd = value; }
	public String getTrsdfd() {return this.trsdfd;}
	
	private String trsdfk = null;
	public void setTrsdfk(String value) {  this.trsdfk = value; }
	public String getTrsdfk() {return this.trsdfk;}
	
	private String trsdtd = null;
	public void setTrsdtd(String value) {  this.trsdtd = value; }
	public String getTrsdtd() {return this.trsdtd;}

	private String trsdtk = null;
	public void setTrsdtk(String value) {  this.trsdtk = value; }
	public String getTrsdtk() {return this.trsdtk;}

	private String heot = null;
	public void setHeot(String value) {  this.heot = value; }
	public String getHeot() {return this.heot;}

	private String hesg = null;
	public void setHesg(String value) {  this.hesg = value; }
	public String getHesg() {return this.hesg;}
	
	private String hepoen = null;
	public void setHepoen(String value) {  this.hepoen = value; }
	public String getHepoen() {return this.hepoen;}
	
	private String interninfo = null;
	public void setInterninfo(String value) {  this.interninfo = value; }
	public String getInterninfo() {return this.interninfo;}
	
	//Prebooking
	private String hestn7 = null;
	public void setHestn7(String value) {  this.hestn7 = value; }
	public String getHestn7() {return this.hestn7;}
	
	private String dftoll = null;
	public void setDftoll(String value) {  this.dftoll = value; }
	public String getDftoll() {return this.dftoll;}
	
	
	
	
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
