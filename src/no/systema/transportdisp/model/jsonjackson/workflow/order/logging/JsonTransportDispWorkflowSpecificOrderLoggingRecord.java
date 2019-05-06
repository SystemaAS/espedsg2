/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.logging;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.util.AppConstants;

/**
 * @author oscardelatorre
 * @date Sep 28, 2015
 *
 */
public class JsonTransportDispWorkflowSpecificOrderLoggingRecord {
	
	private String frBrev = null;
	public void setFrBrev(String value) {  this.frBrev = value; }
	public String getFrBrev() {return this.frBrev;}
	
	private String event = null;
	public void setEvent(String value) {  this.event = value; }
	public String getEvent() {return this.event;}
	
	private String date = null;
	public void setDate(String value) {  this.date = value; }
	public String getDate() {return this.date;}
	
	private String time = null;
	public void setTime(String value) {  this.time = value; }
	public String getTime() {return this.time;}
	
	private String textEng = null;
	public void setTextEng(String value) {  this.textEng = value; }
	public String getTextEng() {return this.textEng;}
	
	private String textLoc = null;
	public void setTextLoc(String value) {  this.textLoc = value; }
	public String getTextLoc() {return this.textLoc;}
	
	private String ediCode = null;
	public void setEdiCode(String value) {  this.ediCode = value; }
	public String getEdiCode() {return this.ediCode;}
	
	private String ediReason = null;
	public void setEdiReason(String value) {  this.ediReason = value; }
	public String getEdiReason() {return this.ediReason;}
	
	private String depot = null;
	public void setDepot(String value) {  this.depot = value; }
	public String getDepot() {return this.depot;}
	
	private String name = null;
	public void setName(String value) {  this.name = value; }
	public String getName() {return this.name;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() {return this.status;}
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() {return this.user;}
	
	private String createDate = null;
	public void setCreateDate(String value) {  this.createDate = value; }
	public String getCreateDate() {return this.createDate;}
	
	private String createTime = null;
	public void setCreateTime(String value) {  this.createTime = value; }
	public String getCreateTime() {return this.createTime;}
	
	private String tripNo = null;
	public void setTripNo(String value) {  this.tripNo = value; }
	public String getTripNo() {return this.tripNo;}
	
	private String locCode = null;
	public void setLocCode(String value) {  this.locCode = value; }
	public String getLocCode() {return this.locCode;}
	
	private String locText = null;
	public void setLocText(String value) {  this.locText = value; }
	public String getLocText() {return this.locText;}
	
	private String latitude = null;
	public void setLatitude(String value) {  this.latitude = value; }
	public String getLatitude() {return this.latitude;}
	
	private String longitude = null;
	public void setLongitude(String value) {  this.longitude = value; }
	public String getLongitude() {return this.longitude;}
	
	private String eventURL = null;
	public void setEventURL(String value) {  this.eventURL = value; }
	public String getEventURL() {return this.eventURL;}
	
	private String manualCode = null;
	public void setManualCode(String value) {  this.manualCode = value; }
	public String getManualCode() {return this.manualCode;}
	
	
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
