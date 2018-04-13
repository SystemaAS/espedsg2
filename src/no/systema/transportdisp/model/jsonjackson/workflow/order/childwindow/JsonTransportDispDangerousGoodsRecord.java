/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Aug 20, 2015
 * 
 *
 */
public class JsonTransportDispDangerousGoodsRecord extends JsonAbstractGrandFatherRecord{

	
	private String adunnr = null;
	public void setAdunnr(String value) {  this.adunnr = value; }
	public String getAdunnr() {return this.adunnr;}
	
	private String adembg = null;
	public void setAdembg(String value) {  this.adembg = value; }
	public String getAdembg() {return this.adembg;}
	
	private String adindx = null;
	public void setAdindx(String value) {  this.adindx = value; }
	public String getAdindx() {return this.adindx;}
	
	private String adtextK = null;
	public void setAdtextK(String value) {  this.adtextK = value; }
	public String getAdtextK() {return this.adtextK;}
	
	private String adklas = null;
	public void setAdklas(String value) {  this.adklas = value; }
	public String getAdklas() {return this.adklas;}
	
	private String adsedd = null;
	public void setAdsedd(String value) {  this.adsedd = value; }
	public String getAdsedd() {return this.adsedd;}
	
	private String adtres = null;
	public void setAdtres(String value) {  this.adtres = value; }
	public String getAdtres() {return this.adtres;}
	
	private String adfakt = null;
	public void setAdfakt(String value) {  this.adfakt = value; }
	public String getAdfakt() {return this.adfakt;}
	
	
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
