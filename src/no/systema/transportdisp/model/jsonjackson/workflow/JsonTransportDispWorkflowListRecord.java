/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

/**
 * @author oscardelatorre
 * @date Mar 31, 2015
 * 
 *
 */
public class JsonTransportDispWorkflowListRecord extends JsonAbstractGrandFatherRecord{
	
	
	private String xturtype = null;
	public void setXturtype(String value) {  this.xturtype = value; }
	public String getXturtype() {return this.xturtype;}
	
	private String tuavd = null;
	public void setTuavd(String value) {  this.tuavd = value; }
	public String getTuavd() {return this.tuavd;}
	
	private String tusg = null;
	public void setTusg(String value) {  this.tusg = value; }
	public String getTusg() { return this.tusg;}
	
	private String tupro = null;
	public void setTupro(String value) {  this.tupro = value; }
	public String getTupro() {return this.tupro;}
	
	private String tupro2 = null;
	public void setTupro2(String value) {  this.tupro2 = value; }
	public String getTupro2() {return this.tupro2;}
	
	private String tustef = null;
	public void setTustef(String value) {  this.tustef = value; }
	public String getTustef() {return this.tustef;}
	
	private String tustet = null;
	public void setTustet(String value) {  this.tustet = value; }
	public String getTustet() {return this.tustet;}
	
	private String tuetd = null;
	public void setTuetd(String value) {  this.tuetd = value; }
	public String getTuetd() {return this.tuetd;}

	private String tueta = null;
	public void setTueta(String value) {  this.tueta = value; }
	public String getTueta() {return this.tueta;}
	
	private String tuatd = null;
	public void setTuatd(String value) {  this.tuatd = value; }
	public String getTuatd() {return this.tuatd;}
	
	private String tucon1 = null;
	public void setTucon1(String value) {  this.tucon1 = value; }
	public String getTucon1() {return this.tucon1;}
	
	private String tubiln = null;
	public void setTubiln(String value) {  this.tubiln = value; }
	public String getTubiln() {return this.tubiln;}
	
	private String tuopdt = null;
	public void setTuopdt(String value) {  this.tuopdt = value; }
	public String getTuopdt() {return this.tuopdt;}
	
	
	private String tudt = null;
	public void setTudt(String value) {  this.tudt = value; }
	public String getTudt() { return this.tudt; }
	
	private String tudtt = null;
	public void setTudtt(String value) {  this.tudtt = value; }
	public String getTudtt() { return this.tudtt; }
	
	private String turclose = null;
	public void setTurclose(String value) {  this.turclose = value; }
	public String getTurclose() {return this.turclose;}
	
	private String turund = null;
	public void setTurund(String value) {  this.turund = value; }
	public String getTurund() { return this.turund; }
	
	private String tutvkt = null;
	public void setTutvkt(String value) {  this.tutvkt = value; }
	public String getTutvkt() { return this.tutvkt; }
	
	private String tutm3 = null;
	public void setTutm3(String value) {  this.tutm3 = value; }
	public String getTutm3() { return this.tutm3; }
	
	private String tutlm2 = null;
	public void setTutlm2(String value) {  this.tutlm2 = value; }
	public String getTutlm2() { return this.tutlm2; }
	
	private String tupoen = null;
	public void setTupoen(String value) {  this.tupoen = value; }
	public String getTupoen() { return this.tupoen; }
	
	private String tures = null;
	public void setTures(String value) {  this.tures = value; }
	public String getTures() { return this.tures; }
	
	private String tutm = null;
	public void setTutm(String value) {  this.tutm = value; }
	public String getTutm() { return this.tutm; }
	
	private String tutmt = null;
	public void setTutmt(String value) {  this.tutmt = value; }
	public String getTutmt() { return this.tutmt; }
	
	private String tutst1 = null;
	public void setTutst1(String value) {  this.tutst1 = value; }
	public String getTutst1() {return this.tutst1;}
	
	private String pdaStat = null;
	public void setPdaStat(String value) {  this.pdaStat = value; }
	public String getPdaStat() { return this.pdaStat; }
	
	private String pdaChgVkt = null;
	public void setPdaChgVkt(String value) {  this.pdaChgVkt = value; }
	public String getPdaChgVkt() { return this.pdaChgVkt; }
	
	private String pdaTurMsg = null;
	public void setPdaTurMsg(String value) {  this.pdaTurMsg = value; }
	public String getPdaTurMsg() { return this.pdaTurMsg; }
	
	private String pdaOpdMsg = null;
	public void setPdaOpdMsg(String value) {  this.pdaOpdMsg = value; }
	public String getPdaOpdMsg() { return this.pdaOpdMsg; }
	
	private String podTxt = null;
	public void setPodTxt(String value) {  this.podTxt = value; }
	public String getPodTxt() { return this.podTxt; }
	
	private String tuao = null;
	public void setTuao(String value) {  this.tuao = value; }
	public String getTuao() { return this.tuao; }
	
	
	
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
