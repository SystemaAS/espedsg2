/**
 * 
 */
package no.systema.sporringoppdrag.filter;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Logger;

/**
 * This search class is used at the GUI search behavior
 * It is MANDATORY to have the same attribute name convention as the JSON-object fetched from the JSON-payload at the back-end.
 * The reason for this is the java-reflection mechanism used when searching (since no SQL or other mechanism is used)
 * By using java reflection to match the object fields, these 2 (the JSON object and its SearchFilter object) must have the same attribute name 
 * 
 * @author oscardelatorre
 * @date   Feb 11, 2015
 * 
 */
public class SearchFilterSporringOppdragTopicList {
	private static final Logger logger = Logger.getLogger(SearchFilterSporringOppdragTopicList.class.getName());
	
	private String wsavd = null;
	public void setWsavd(String value) {  this.wsavd = value; }
	public String getWsavd() { return this.wsavd;}
	
	private String wsopd = null;
	public void setWsopd(String value) {  this.wsopd = value; }
	public String getWsopd() { return this.wsopd;}
	
	private String wsdtf = null;
	public void setWsdtf(String value) {  this.wsdtf = value; }
	public String getWsdtf() { return this.wsdtf;}
	
	private String wsdtt = null;
	public void setWsdtt(String value) {  this.wsdtt = value; }
	public String getWsdtt() { return this.wsdtt;}
	
	private String wsrfa = null;
	public void setWsrfa(String value) { this.wsrfa = value;}
	public String getWsrfa() { return this.wsrfa;}
	
	private String wsrfk = null;
	public void setWsrfk(String value) { this.wsrfk = value;}
	public String getWsrfk() { return this.wsrfk;}
	
	private String wsfn = null;
	public void setWsfn(String value) { this.wsfn = value;}
	public String getWsfn() { return this.wsfn;}
	
	private String wsgn = null;
	public void setWsgn(String value) {  this.wsgn = value; }
	public String getWsgn() { return this.wsgn;}
	
	private String wsawbn = null;
	public void setWsawbn(String value) {  this.wsawbn = value; }
	public String getWsawbn() { return this.wsawbn;}
	
	private String wshawb = null;
	public void setWshawb(String value) {  this.wshawb = value; }
	public String getWshawb() { return this.wshawb;}
	
	private String wsdthawb = null;
	public void setWsdthawb(String value) {  this.wsdthawb = value; }
	public String getWsdthawb() { return this.wsdthawb;}
	
	private String wsot = null;
	public void setWsot(String value) {  this.wsot = value; }
	public String getWsot() { return this.wsot;}
	
	private String wsdtot = null;
	public void setWsdtot(String value) {  this.wsdtot = value; }
	public String getWsdtot() { return this.wsdtot;}
	
	private String wsmrk1 = null;
	public void setWsmrk1(String value) {  this.wsmrk1 = value; }
	public String getWsmrk1() { return this.wsmrk1;}
	
	private String ownFraktbrevsNr = null;
	public void setOwnFraktbrevsNr(String value) {  this.ownFraktbrevsNr = value; }
	public String getOwnFraktbrevsNr() { return this.ownFraktbrevsNr;}
	
	
	private String fscd = null;
	public void setFscd(String value) {  this.fscd = value; }
	public String getFscd() { return this.fscd;}
	
	private String wsfri1 = null;
	public void setWsfri1(String value) {  this.wsfri1 = value; }
	public String getWsfri1() { return this.wsfri1;}

	private String wsfri2 = null;
	public void setWsfri2(String value) {  this.wsfri2 = value; }
	public String getWsfri2() { return this.wsfri2;}

	private String wsdtfs = null;
	public void setWsdtfs(String value) {  this.wsdtfs = value; }
	public String getWsdtfs() { return this.wsdtfs;}

	private String wsdtfst = null;
	public void setWsdtfst(String value) {  this.wsdtfst = value; }
	public String getWsdtfst() { return this.wsdtfst;}

	private String wsblnr = null;
	public void setWsblnr(String value) {  this.wsblnr = value; }
	public String getWsblnr() { return this.wsblnr;}

	private String wsblcn = null;
	public void setWsblcn(String value) {  this.wsblcn = value; }
	public String getWsblcn() { return this.wsblcn;}

	
	/**
	 * Gets the populated values by reflection
	 * @param searchFilte
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getPopulatedFields() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		for(Field field : list){
			field.setAccessible(true);
			logger.info("FIELD NAME: " + field.getName() + "VALUE:" + (String)field.get(this));
			String value = (String)field.get(this);
			if(value!=null && !"".equals(value)){
				logger.info(field.getName() + " Value:" + value);
				map.put(field.getName(), value);
			}
		}
		
		return map;
	}
}
