/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * @author oscardelatorre
 * @date Apr 30, 2015
 * 
 */
public class JsonTransportDispCustomerRecord extends JsonAbstractGrandFatherRecord  {
	
	private String aktkod = null;
	public void setAktkod(String value){ this.aktkod = value;}
	public String getAktkod(){ return this.aktkod; }
	
	
	private String kundnr = null;
	public void setKundnr(String value){ this.kundnr = value;}
	public String getKundnr(){ return this.kundnr; }
	
	private String navn = null;
	public void setNavn(String value){ this.navn = value;}
	public String getNavn(){ return this.navn; }
	
	private String adr1 = null;
	public void setAdr1(String value){ this.adr1 = value;}
	public String getAdr1(){ return this.adr1; }
	
	private String adr2 = null;
	public void setAdr2(String value){ this.adr2 = value;}
	public String getAdr2(){ return this.adr2; }
	
	private String adresse = null;
	public void setAdresse(String value){ this.adresse = value;}
	public String getAdresse(){ return this.adresse; }
	
	private String land = null;
	public void setLand(String value){ this.land = value;}
	public String getLand(){ return this.land; }
	
	private String fakknr = null;
	public void setFakknr(String value){ this.fakknr = value;}
	public String getFakknr(){ return this.fakknr; }
	
	
	//Delivery Address fields
	private String auxnavn = "";
	public void setAuxnavn(String value){ this.auxnavn = value;}
	public String getAuxnavn(){ return this.auxnavn; }
	
	private String auxtlf = "";
	public void setAuxtlf(String value){ this.auxtlf = value;}
	public String getAuxtlf(){ return this.auxtlf; }
	
	private String auxfax = "";
	public void setAuxfax(String value){ this.auxfax = value;}
	public String getAuxfax(){ return this.auxfax; }
	
	private String auxmail = "";
	public void setAuxmail(String value){ this.auxmail = value;}
	public String getAuxmail(){ return this.auxmail; }
	
	
	//vareadresser
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
