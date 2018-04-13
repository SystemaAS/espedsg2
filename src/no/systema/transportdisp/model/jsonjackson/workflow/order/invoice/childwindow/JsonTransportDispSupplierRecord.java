/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * @author oscardelatorre
 * @date Oct 08, 2015
 * 
 */
public class JsonTransportDispSupplierRecord extends JsonAbstractGrandFatherRecord  {
	
	private String aktkod = null;
	public void setAktkod(String value){ this.aktkod = value;}
	public String getAktkod(){ return this.aktkod; }
	
	
	private String levnr = null;
	public void setLevnr(String value){ this.levnr = value;}
	public String getLevnr(){ return this.levnr; }
	
	private String lnavn = null;
	public void setLnavn(String value){ this.lnavn = value;}
	public String getLnavn(){ return this.lnavn; }
	
	private String adr1 = null;
	public void setAdr1(String value){ this.adr1 = value;}
	public String getAdr1(){ return this.adr1; }
	
	private String adr2 = null;
	public void setAdr2(String value){ this.adr2 = value;}
	public String getAdr2(){ return this.adr2; }
	
	private String postnr = null;
	public void setPostnr(String value){ this.postnr = value;}
	public String getPostnr(){ return this.postnr; }
	
	private String sted = null;
	public void setSted(String value){ this.sted = value;}
	public String getSted(){ return this.sted; }
	
	private String land = null;
	public void setLand(String value){ this.land = value;}
	public String getLand(){ return this.land; }
	
	private String kundnr = "";
	public void setKundnr(String value){ this.kundnr = value;}
	public String getKundnr(){ return this.kundnr; }
	
	
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
