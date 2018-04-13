/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.frisokvei;

import java.util.*;
import java.lang.reflect.Field;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author oscardelatorre
 * @date Jun 09, 2017
 * 
 *
 */
public class JsonTransportDispWorkflowSpecificOrderFrisokveiRecord extends JsonAbstractGrandFatherRecord {
	
	//This is required in order to know if is an update or create new record
	//Since there is id, we must have a mechanism to know if the user is updating or creating a new record
	private String isModeUpdate = null;
	public void setIsModeUpdate(String value) {  this.isModeUpdate = value; }
	public String getIsModeUpdate() {return this.isModeUpdate;}
		
	
	private String fsavd = null;
	public void setFsavd(String value) {  this.fsavd = value; }
	public String getFsavd() {return this.fsavd;}
	
	private String fsopd = null;
	public void setFsopd(String value) {  this.fsopd = value; }
	public String getFsopd() {return this.fsopd;}
	
	
	private String fskode = null;
	public void setFskode(String value) {  this.fskode = value; }
	public String getFskode() {return this.fskode;}
	
	private String fskodeKey = null;
	public void setFskodeKey(String value) {  this.fskodeKey = value; }
	public String getFskodeKey() {return this.fskodeKey;}
	
	private String fssok = null;
	public void setFssok(String value) {  this.fssok = value; }
	public String getFssok() {return this.fssok;}
	
	private String fssokKey = null;
	public void setFssokKey(String value) {  this.fssokKey = value; }
	public String getFssokKey() {return this.fssokKey;}
	
	private String fsdokk = null;
	public void setFsdokk(String value) {  this.fsdokk = value; }
	public String getFsdokk() {return this.fsdokk;}
	
	private String fsdtop = null;
	public void setFsdtop(String value) {  this.fsdtop = value; }
	public String getFsdtop() {return this.fsdtop;}
	
	private String kfsttu = null;
	public void setKfsttu(String value) {  this.kfsttu = value; }
	public String getKfsttu() {return this.kfsttu;}
	
	private String kfsotx = null;
	public void setKfsotx(String value) {  this.kfsotx = value; }
	public String getKfsotx() {return this.kfsotx;}
	
	private String krav = null;
	public void setKrav(String value) {  this.krav = value; }
	public String getKrav() {return this.krav;}
	
	private String websok = null;
	public void setWebsok(String value) {  this.websok = value; }
	public String getWebsok() {return this.websok;}
	

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
