/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.budget;

import java.util.*;
import java.lang.reflect.Field;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author oscardelatorre
 * @date Oct 12, 2015
 * 
 *
 */
public class JsonTransportDispWorkflowSpecificBudgetRecord extends JsonAbstractGrandFatherRecord {
	
	//hepro (in order to know if the budget Use Case belongs to a trip or to an order. 
	//If the record belongs to the Trip (Tur) Use Case the record will always have a parentTrip.
	private String hepro = null;
	public void setHepro(String value) {  this.hepro = value; }
	public String getHepro() {return this.hepro;}
	
	//This is required in order to know if is an update or create new record
	//Since there is no linenr and only rekvisitionsnr we must have a mechanism to know if the user is updating or creating a new record
	private String isModeUpdate = null;
	public void setIsModeUpdate(String value) {  this.isModeUpdate = value; }
	public String getIsModeUpdate() {return this.isModeUpdate;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() {return this.opd;}
	
	
	private String intMVAx = null;
	public void setIntMVAx(String value) {  this.intMVAx = value; }
	public String getIntMVAx() {return this.intMVAx;}
	
	private String kosMVAx = null;
	public void setKosMVAx(String value) {  this.kosMVAx = value; }
	public String getKosMVAx() {return this.kosMVAx;}
	
	private String traNavn = null;
	public void setTraNavn(String value) {  this.traNavn = value; }
	public String getTraNavn() {return this.traNavn;}
	
	private String levNavn = null;
	public void setLevNavn(String value) {  this.levNavn = value; }
	public String getLevNavn() {return this.levNavn;}
	
	
	private String bust = null;
	public void setBust(String value) {  this.bust = value; }
	public String getBust() {return this.bust;}
	
	private String bubnr = null;
	public void setBubnr(String value) {  this.bubnr = value; }
	public String getBubnr() {return this.bubnr;}
	
	private String bupCc = null;
	public void setBupCc(String value) {  this.bupCc = value; }
	public String getBupCc() {return this.bupCc;}
	
	private String bupAr = null;
	public void setBupAr(String value) {  this.bupAr = value; }
	public String getBupAr() {return this.bupAr;}
	
	private String bupMn = null;
	public void setBupMn(String value) {  this.bupMn = value; }
	public String getBupMn() {return this.bupMn;}
	
	private String butnr = null;
	public void setButnr(String value) {  this.butnr = value; }
	public String getButnr() {return this.butnr;}
	
	private String bulnr = null;
	public void setBulnr(String value) {  this.bulnr = value; }
	public String getBulnr() {return this.bulnr;}
	
	private String buval = null;
	public void setBuval(String value) {  this.buval = value; }
	public String getBuval() {return this.buval;}
	
	private String bubl = null;
	public void setBubl(String value) {  this.bubl = value; }
	public String getBubl() {return this.bubl;}
	
	private String buvk = null;
	public void setBuvk(String value) {  this.buvk = value; }
	public String getBuvk() {return this.buvk;}
	
	private String busg = null;
	public void setBusg(String value) {  this.busg = value; }
	public String getBusg() {return this.busg;}
	
	private String butavd = null;
	public void setButavd(String value) {  this.butavd = value; }
	public String getButavd() {return this.butavd;}
	
	private String butunr = null;
	public void setButunr(String value) {  this.butunr = value; }
	public String getButunr() {return this.butunr;}
	
	private String buoavd = null;
	public void setBuoavd(String value) {  this.buoavd = value; }
	public String getBuoavd() {return this.buoavd;}
	
	private String buopd = null;
	public void setBuopd(String value) {  this.buopd = value; }
	public String getBuopd() {return this.buopd;}
	
	private String bublst = null;
	public void setBublst(String value) {  this.bublst = value; }
	public String getBublst() {return this.bublst;}
	
	private String butype = null;
	public void setButype(String value) {  this.butype = value; }
	public String getButype() {return this.butype;}
	
	private String butxt = null;
	public void setButxt(String value) {  this.butxt = value; }
	public String getButxt() {return this.butxt;}
	
	private String bubilk = null;
	public void setBubilk(String value) {  this.bubilk = value; }
	public String getBubilk() {return this.bubilk;}
	
	private String bubiln = null;
	public void setBubiln(String value) {  this.bubiln = value; }
	public String getBubiln() {return this.bubiln;}
	
	private String bufedt = null;
	public void setBufedt(String value) {  this.bufedt = value; }
	public String getBufedt() {return this.bufedt;}
	
	private String bufer = null;
	public void setBufer(String value) {  this.bufer = value; }
	public String getBufer() {return this.bufer;}
	
	private String bubnr2 = null;
	public void setBubnr2(String value) {  this.bubnr2 = value; }
	public String getBubnr2() {return this.bubnr2;}
	
	private String bubnr3 = null;
	public void setBubnr3(String value) {  this.bubnr3 = value; }
	public String getBubnr3() {return this.bubnr3;}
	
	
	private String bulnr3 = null;
	public void setBulnr3(String value) {  this.bulnr3 = value; }
	public String getBulnr3() {return this.bulnr3;}
	
	
	private String bublfd = null;
	public void setBublfd(String value) {  this.bublfd = value; }
	public String getBublfd() {return this.bublfd;}
	
	
	private String bukdm = null;
	public void setBukdm(String value) {  this.bukdm = value; }
	public String getBukdm() {return this.bukdm;}
	
	
	
	
	public String toUpperCase(String value) {
		String retval = value;
		if(value!=null){
			retval = value.toUpperCase();
		}
		return retval;
	}
	
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
