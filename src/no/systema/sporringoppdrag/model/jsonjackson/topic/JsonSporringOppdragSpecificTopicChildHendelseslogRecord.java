/**
 * 
 */
package no.systema.sporringoppdrag.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
import no.systema.main.util.AppConstants;

/**
 * @author oscardelatorre
 * @date Feb 14, 2015
 * 
 *
 */
public class JsonSporringOppdragSpecificTopicChildHendelseslogRecord extends JsonAbstractGrandFatherRecord{
	
	private String wttd = null;
	public void setWttd(String value) {  this.wttd = value; }
	public String getWttd() {
		if(this.wttd!=null){
			//Invalid date
			if(this.wttd.startsWith("00") && this.wttd.endsWith("00")){
				this.wttd = null;
			}
		}
		return this.wttd;
	}
	
	private String wttt = null;
	public void setWttt(String value) {  this.wttt = value; }
	public String getWttt() {
		if(this.wttt!=null){
			//Invalid time
			if(this.wttt.startsWith("00") && this.wttt.endsWith("00")){
				this.wttt = null;
			}
		}
		return this.wttt;
	}
	
	private String wttx = null;
	public void setWttx(String value) {  this.wttx = value; }
	public String getWttx() {return this.wttx;}
	
	//used for signature gif (scanned signature)
	private String wttg = null;
	public void setWttg(String value) {  this.wttg = value; }
	public String getWttg() {
		if(this.wttg!=null && !"".equals(this.wttg)){
			if(this.wttg.contains("http")||this.wttg.contains("HTTP")){ 
				//nothing 
			}else{
				//the img file is available withing the servers domain path
				//Original--> but Toten has to have cust.toten so we changed it to test (08.Nov.2017)
				//-->this.wttg = AppConstants.HTTP_ROOT_CGI + this.wttg;
				this.wttg = AppConstants.HTTP_ROOT_JQUERY_DOCS_ROOT + this.wttg;
				
			}
		}
		return this.wttg;
	}
	//used for other images
	private String wttg2 = null;
	public void setWttg2(String value) {  this.wttg2 = value; }
	public String getWttg2() {
		if(this.wttg2!=null && !"".equals(this.wttg2)){
			if(this.wttg2.contains("http")||this.wttg2.contains("HTTP")){ 
				//nothing 
			}else{
				//the img file is available withing the servers domain path
				this.wttg2 = AppConstants.HTTP_ROOT_CGI + this.wttg2;
			}
			
		}
		return this.wttg2;
		
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
