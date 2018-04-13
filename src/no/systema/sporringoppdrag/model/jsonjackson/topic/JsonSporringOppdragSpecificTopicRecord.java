/**
 * 
 */
package no.systema.sporringoppdrag.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Feb 11, 2015
 * 
 *
 */
public class JsonSporringOppdragSpecificTopicRecord extends JsonAbstractGrandFatherRecord{
	
	private String heur = null;
	public void setHeur(String value) {  this.heur = value; }
	public String getHeur() {return this.heur;}
	
	private String fitdvi = null;
	public void setFitdvi(String value) {  this.fitdvi = value; }
	public String getFitdvi() {return this.fitdvi;}
	
	private String heavd = null;
	public void setHeavd(String value) {  this.heavd = value; }
	public String getHeavd() {return this.heavd;}
	
	private String heopd = null;
	public void setHeopd(String value) {  this.heopd = value; }
	public String getHeopd() {return this.heopd;}
	
	private String hesg = null;
	public void setHesg(String value) {  this.hesg = value; }
	public String getHesg() {return this.hesg;}
	
	private String hedtop = null;
	public void setHedtop(String value) {  this.hedtop = value; }
	public String getHedtop() {return this.hedtop;}

	private String hepro = null;
	public void setHepro(String value) {  this.hepro = value; }
	public String getHepro() {return this.hepro;}
	
	private String wsetd = null;
	public void setWsetd(String value) {  this.wsetd = value; }
	public String getWsetd() {return this.wsetd;}
	
	private String wseta = null;
	public void setWseta(String value) {  this.wseta = value; }
	public String getWseta() {return this.wseta;}
	
	private String wsata = null;
	public void setWsata(String value) {  this.wsata = value; }
	public String getWsata() {return this.wsata;}
	
	private String tracking = null;
	public void setTracking(String value) {  this.tracking = value; }
	public String getTracking() {return this.tracking;}
	
	private String tracking2 = null;
	public void setTracking2(String value) {  this.tracking2 = value; }
	public String getTracking2() {return this.tracking2;}
	
	private String hegn = null;
	public void setHegn(String value) {  this.hegn = value; }
	public String getHegn() {return this.hegn;}
	
	private Integer hegnInt = 0;
	public Integer getHegnInt() {
		if(this.hegn!=null && this.hegn.startsWith("20")){
			if(this.hegn.length()>=4){
				try{
					String prefix = this.hegn.substring(0,4);
					this.hegnInt = Integer.parseInt(prefix);
				}catch(Exception e){
					//nothing
				}
			}
		}
		return this.hegnInt;
	}
	
	private String herfa = null;
	public void setHerfa(String value) {  this.herfa = value; }
	public String getHerfa() {return this.herfa;}
	
	private String hehawb = null;
	public void setHehawb(String value) {  this.hehawb = value; }
	public String getHehawb() {return this.hehawb;}
	
	private String hetrcn = null;
	public void setHetrcn(String value) {  this.hetrcn = value; }
	public String getHetrcn() {return this.hetrcn;}
	
	private String henas = null;
	public void setHenas(String value) {  this.henas = value; }
	public String getHenas() {return this.henas;}
	
	private String heads1 = null;
	public void setHeads1(String value) {  this.heads1 = value; }
	public String getHeads1() {return this.heads1;}
	
	private String heads2 = null;
	public void setHeads2(String value) {  this.heads2 = value; }
	public String getHeads2() {return this.heads2;}
	
	private String heads3 = null;
	public void setHeads3(String value) {  this.heads3 = value; }
	public String getHeads3() {return this.heads3;}
	
	
	private String henak = null;
	public void setHenak(String value) {  this.henak = value; }
	public String getHenak() {return this.henak;}
	
	
	private String headk1 = null;
	public void setHeadk1(String value) {  this.headk1 = value; }
	public String getHeadk1() {return this.headk1;}
	
	private String headk2 = null;
	public void setHeadk2(String value) {  this.headk2 = value; }
	public String getHeadk2() {return this.headk2;}
	
	private String headk3 = null;
	public void setHeadk3(String value) {  this.headk3 = value; }
	public String getHeadk3() {return this.headk3;}

	private String hesdff = null;
	public void setHesdff(String value) {  this.hesdff = value; }
	public String getHesdff() {return this.hesdff;}

	private String hesdf = null;
	public void setHesdf(String value) {  this.hesdf = value; }
	public String getHesdf() {return this.hesdf;}

	private String hesdt = null;
	public void setHesdt(String value) {  this.hesdt = value; }
	public String getHesdt() {return this.hesdt;}
	
	private String hesdvt = null;
	public void setHesdvt(String value) {  this.hesdvt = value; }
	public String getHesdvt() {return this.hesdvt;}

	private String hent = null;
	public void setHent(String value) {  this.hent = value; }
	public String getHent() {return this.hent;}
	
	private String clis = null;
	public void setClis(String value) {  this.clis = value; }
	public String getClis() {
		String CHAR_START = ">";
		String CHAR_END = "<";
		//Just extract whatever is inside the html-tag
		if(this.clis!=null){
			if(this.clis.contains(CHAR_START) && this.clis.contains(CHAR_END)){
				int start = this.clis.indexOf(CHAR_START);
				int end = this.clis.indexOf(CHAR_END);
				String subStr = this.clis.substring(start+1, end);
				this.clis = subStr;
			}
			
		}
		return this.clis;
	}

	private String hedtmo = null;
	public void setHedtmo(String value) {  this.hedtmo = value; }
	public String getHedtmo() {return this.hedtmo;}
	
	private String hevkt = null;
	public void setHevkt(String value) {  this.hevkt = value; }
	public String getHevkt() {return this.hevkt;}
	
	private String hefbv = null;
	public void setHefbv(String value) {  this.hefbv = value; }
	public String getHefbv() {return this.hefbv;}
	
	private String hem3 = null;
	public void setHem3(String value) {  this.hem3 = value; }
	public String getHem3() {return this.hem3;}
	
	private String helm = null;
	public void setHelm(String value) {  this.helm = value; }
	public String getHelm() {return this.helm;}
	
	private String hevs1 = null;
	public void setHevs1(String value) {  this.hevs1 = value; }
	public String getHevs1() {return this.hevs1;}
	
	private String hevs2 = null;
	public void setHevs2(String value) {  this.hevs2 = value; }
	public String getHevs2() {return this.hevs2;}
	
	private String hegm1 = null;
	public void setHegm1(String value) {  this.hegm1 = value; }
	public String getHegm1() {return this.hegm1;}
	
	private String hegm2 = null;
	public void setHegm2(String value) {  this.hegm2 = value; }
	public String getHegm2() {return this.hegm2;}
	
	private String hesgm = null;
	public void setHesgm(String value) {  this.hesgm = value; }
	public String getHesgm() {return this.hesgm;}
	
	
	
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
