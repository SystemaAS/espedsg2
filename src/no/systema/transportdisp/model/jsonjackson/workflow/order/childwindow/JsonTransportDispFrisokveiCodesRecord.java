/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Jun 09, 2017
 * 
 *
 */
public class JsonTransportDispFrisokveiCodesRecord extends JsonAbstractGrandFatherRecord{

	
	private String kfsoko = null;
	public void setKfsoko(String value) {  this.kfsoko = value; }
	public String getKfsoko() {return this.kfsoko;}
	
	private String kfsotx = null;
	public void setKfsotx(String value) {  this.kfsotx = value; }
	public String getKfsotx() {return this.kfsotx;}
	
	private String kfsodk = null;
	public void setKfsodk(String value) {  this.kfsodk = value; }
	public String getKfsodk() {return this.kfsodk;}
	
	private String kfsttu = null;
	public void setKfsttu(String value) {  this.kfsttu = value; }
	public String getKfsttu() {return this.kfsttu;}
	
	
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
