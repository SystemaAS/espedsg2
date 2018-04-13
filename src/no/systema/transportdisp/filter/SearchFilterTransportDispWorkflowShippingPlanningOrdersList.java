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
 * @date   Apr 11, 2015
 * 
 */
public class SearchFilterTransportDispWorkflowShippingPlanningOrdersList {
	private static final Logger logger = Logger.getLogger(SearchFilterTransportDispWorkflowShippingPlanningOrdersList.class.getName());
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}
	
	
	private String tur = null;
	public void setTur(String value) {  this.tur = value; }
	public String getTur() { return this.tur;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String opdType = null;
	public void setOpdType(String value) {  this.opdType = value; }
	public String getOpdType() { return this.opdType;}
	
	private String mode = null;
	public void setMode(String value) {  this.mode = value; }
	public String getMode() { return this.mode;}
	
	private String from = null;
	public void setFrom(String value) {  this.from = value; }
	public String getFrom() { return this.from;}
	
	private String fromDateF = null;
	public void setFromDateF(String value) {  this.fromDateF = value; }
	public String getFromDateF() { return this.fromDateF;}
	
	private String fromDateT = null;
	public void setFromDateT(String value) {  this.fromDateT = value; }
	public String getFromDateT() { return this.fromDateT;}
	
	
	private String to = null;
	public void setTo(String value) {  this.to = value; }
	public String getTo() { return this.to;}
	
	private String toDateF = null;
	public void setToDateF(String value) {  this.toDateF = value; }
	public String getToDateF() { return this.toDateF;}
	
	private String toDateT = null;
	public void setToDateT(String value) {  this.toDateT = value; }
	public String getToDateT() { return this.toDateT;}
	
	private String wsprebook = null;
	public void setWsprebook(String value) {  this.wsprebook = value; }
	public String getWsprebook() { return this.wsprebook;}
	
	
	
	
	/**
	 * Gets the populated values by reflection
	 * @param searchFilter
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
