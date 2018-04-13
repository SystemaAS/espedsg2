/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * @author oscardelatorre
 * @date May 27, 2015
 * 
 */
public class JsonTransportDispCustomerDeliveryAddressRecord extends JsonAbstractGrandFatherRecord  {
	
	private String vadrnr = null;
	public void setVadrnr(String value){ this.vadrnr = value;}
	public String getVadrnr(){ return this.vadrnr; }
	
	private String vadrna = null;
	public void setVadrna(String value){ this.vadrna = value;}
	public String getVadrna(){ return this.vadrna; }
	
	private String vadrn1 = null;
	public void setVadrn1(String value){ this.vadrn1 = value;}
	public String getVadrn1(){ return this.vadrn1; }
		
	private String vadrn2 = null;
	public void setVadrn2(String value){ this.vadrn2 = value;}
	public String getVadrn2(){ return this.vadrn2; }
	
	private String vadrn3 = null;
	public void setVadrn3(String value){ this.vadrn3 = value;}
	public String getVadrn3(){ return this.vadrn3; }
	
	private String valand = null;
	public void setValand(String value){ this.valand = value;}
	public String getValand(){ return this.valand; }
	
	private String vakure = null;
	public void setVakure(String value){ this.vakure = value;}
	public String getVakure(){ return this.vakure; }
	
	private String vatlf = null;
	public void setVatlf(String value){ this.vatlf = value;}
	public String getVatlf(){ return this.vatlf; }
	
	private String vafax = null;
	public void setVafax(String value){ this.vafax = value;}
	public String getVafax(){ return this.vafax; }
	
	private String vamail = null;
	public void setVamail(String value){ this.vamail = value;}
	public String getVamail(){ return this.vamail; }
	
	
	/**
	 * User for java reflection in other classes
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
