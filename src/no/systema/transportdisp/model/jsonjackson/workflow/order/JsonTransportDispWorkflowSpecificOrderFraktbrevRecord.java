/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Maj 17, 2015
 * 
 *
 */
public class JsonTransportDispWorkflowSpecificOrderFraktbrevRecord extends JsonAbstractGrandFatherRecord{
	
	//FRAKTBREV fields
	private String fvst = null;
	public void setFvst(String value) {  this.fvst = value; }
	public String getFvst() {return this.fvst;}
	
	private String fvri = null;
	public void setFvri(String value) {  this.fvri = value; }
	public String getFvri() {return this.fvri;}
	
	private String fvavd = null;
	public void setFvavd(String value) {  this.fvavd = value; }
	public String getFvavd() {return this.fvavd;}
	
	private String fvopd = null;
	public void setFvopd(String value) {  this.fvopd = value; }
	public String getFvopd() {return this.fvopd;}
	
	private String fvfbnr = null;
	public void setFvfbnr(String value) {  this.fvfbnr = value; }
	public String getFvfbnr() {return this.fvfbnr;}
	
	private String fvlinr = null;
	public void setFvlinr(String value) {  this.fvlinr = value; }
	public String getFvlinr() {return this.fvlinr;}
	
	private String fvant = null;
	public void setFvant(String value) {  this.fvant = value; }
	public String getFvant() {return this.fvant;}
	
	private String fvpakn = null;
	public void setFvpakn(String value) {  this.fvpakn = value; }
	public String getFvpakn() {return this.fvpakn;}
	
	private String fvvt = null;
	public void setFvvt(String value) {  this.fvvt = value; }
	public String getFvvt() {return this.fvvt;}
	
	private String fvvkt = null;
	public void setFvvkt(String value) {  this.fvvkt = value; }
	public String getFvvkt() {return this.fvvkt;}
	
	private String fvvol = null;
	public void setFvvol(String value) {  this.fvvol = value; }
	public String getFvvol() {return this.fvvol;}
	
	private String fvlm = null;
	public void setFvlm(String value) {  this.fvlm = value; }
	public String getFvlm() {return this.fvlm;}
	
	private String fvlm2 = null;
	public void setFvlm2(String value) {  this.fvlm2 = value; }
	public String getFvlm2() {return this.fvlm2;}
	
	private String fvlen = null;
	public void setFvlen(String value) {  this.fvlen = value; }
	public String getFvlen() {return this.fvlen;}
	
	private String fvbrd = null;
	public void setFvbrd(String value) {  this.fvbrd = value; }
	public String getFvbrd() {return this.fvbrd;}
	
	private String fvhoy = null;
	public void setFvhoy(String value) {  this.fvhoy = value; }
	public String getFvhoy() {return this.fvhoy;}
	
	private String fmmrk1 = null;
	public void setFmmrk1(String value) {  this.fmmrk1 = value; }
	public String getFmmrk1() {return this.fmmrk1;}
	
	//FARLIGGODS
	private String ffunnr = null;
	public void setFfunnr(String value) {  this.ffunnr = value; }
	public String getFfunnr() {return this.ffunnr;}
	
	private String ffindx = null;
	public void setFfindx(String value) {  this.ffindx = value; }
	public String getFfindx() {return this.ffindx;}
	
	private String ffembg = null;
	public void setFfembg(String value) {  this.ffembg = value; }
	public String getFfembg() {return this.ffembg;}
	
	private String ffklas = null;
	public void setFfklas(String value) {  this.ffklas = value; }
	public String getFfklas() {return this.ffklas;}
	
	private String ffsedd = null;
	public void setFfsedd(String value) {  this.ffsedd = value; }
	public String getFfsedd() {return this.ffsedd;}
	
	private String fftres = null;
	public void setFftres(String value) {  this.fftres = value; }
	public String getFftres() {return this.fftres;}

	private String fffakt = null;
	public void setFffakt(String value) {  this.fffakt = value; }
	public String getFffakt() {return this.fffakt;}

	private String ffantk = null;
	public void setFfantk(String value) {  this.ffantk = value; }
	public String getFfantk() {return this.ffantk;}

	private String ffante = null;
	public void setFfante(String value) {  this.ffante = value; }
	public String getFfante() {return this.ffante;}

	private String ffenh = null;
	public void setFfenh(String value) {  this.ffenh = value; }
	public String getFfenh() {return this.ffenh;}

	private String ffpoen = null;
	public void setFfpoen(String value) {  this.ffpoen = value; }
	public String getFfpoen() {return this.ffpoen;}

	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
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
