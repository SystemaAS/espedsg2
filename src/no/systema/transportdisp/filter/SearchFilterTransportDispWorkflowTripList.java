/**
 * 
 */
package no.systema.transportdisp.filter;

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
 * @date   Apr 1, 2015
 * 
 */
public class SearchFilterTransportDispWorkflowTripList {
	private static final Logger logger = Logger.getLogger(SearchFilterTransportDispWorkflowTripList.class.getName());
	
	private String wssavd = null;
	public void setWssavd(String value) {  this.wssavd = value; }
	public String getWssavd() { return this.wssavd;}
	
	private String wsstur = null;
	public void setWsstur(String value) {  this.wsstur = value; }
	public String getWsstur() { return this.wsstur;}
	
	private String wssst = null;
	public void setWssst(String value) {  this.wssst = value; }
	public String getWssst() { return this.wssst;}
	
	private String wtubiln = null;
	public void setWtubiln(String value) {  this.wtubiln = value; }
	public String getWtubiln() { return this.wtubiln;}
	
	private String wtustef = null;
	public void setWtustef(String value) {  this.wtustef = value; }
	public String getWtustef() { return this.wtustef;}
	
	private String wtustet = null;
	public void setWtustet(String value) {  this.wtustet = value; }
	public String getWtustet() { return this.wtustet;}
	
	private String wtudt = null;
	public void setWtudt(String value) {  this.wtudt = value; }
	public String getWtudt() { return this.wtudt;}
	
	private String wtudt2 = null;
	public void setWtudt2(String value) {  this.wtudt2 = value; }
	public String getWtudt2() { return this.wtudt2;}
	
	private String wtudtt = null;
	public void setWtudtt(String value) {  this.wtudtt = value; }
	public String getWtudtt() { return this.wtudtt;}
	
	private String wtudtt2 = null;
	public void setWtudtt2(String value) {  this.wtudtt2 = value; }
	public String getWtudtt2() { return this.wtudtt2;}
	
	private String wtusg = null;
	public void setWtusg(String value) {  this.wtusg = value; }
	public String getWtusg() { return this.wtusg;}
	
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
