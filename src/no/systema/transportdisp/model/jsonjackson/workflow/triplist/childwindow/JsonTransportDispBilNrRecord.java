/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Apr 6, 2015
 * 
 *
 */
public class JsonTransportDispBilNrRecord extends JsonAbstractGrandFatherRecord{
	
	private String unbiln = null;
	public void setUnbiln(String value) {  this.unbiln = value; }
	public String getUnbiln() {return this.unbiln;}
	
	private String untilh = null;
	public void setUntilh(String value) {  this.untilh = value; }
	public String getUntilh() {return this.untilh;}
	
	private String unland = null;
	public void setUnland(String value) {  this.unland = value; }
	public String getUnland() {return this.unland;}
	
	private String untrme = null;
	public void setUntrme(String value) {  this.untrme = value; }
	public String getUntrme() {return this.untrme;}
	
	private String untrma = null;
	public void setUntrma(String value) {  this.untrma = value; }
	public String getUntrma() {return this.untrma;}
	
	private String unvekt = null;
	public void setUnvekt(String value) {  this.unvekt = value; }
	public String getUnvekt() {return this.unvekt;}
	
	private String untara = null;
	public void setUntara(String value) {  this.untara = value; }
	public String getUntara() {return this.untara;}
	
	private String unm3 = null;
	public void setUnm3(String value) {  this.unm3 = value; }
	public String getUnm3() {return this.unm3;}
	
	private String unlm = null;
	public void setUnlm(String value) {  this.unlm = value; }
	public String getUnlm() {return this.unlm;}
	
	private String ununty = null;
	public void setUnunty(String value) {  this.ununty = value; }
	public String getUnunty() {return this.ununty;}
	
	private String untran = null;
	public void setUntran(String value) {  this.untran = value; }
	public String getUntran() {return this.untran;}
	
	private String unretu = null;
	public void setUnretu(String value) {  this.unretu = value; }
	public String getUnretu() {return this.unretu;}
	
	private String unretunavn = null;
	public void setUnretunavn(String value) {  this.unretunavn = value; }
	public String getUnretunavn() {return this.unretunavn;}
	
	private String vmnavn = null;
	public void setVmnavn(String value) {  this.vmnavn = value; }
	public String getVmnavn() {return this.vmnavn;}
	
	private String unval = null;
	public void setUnval(String value) {  this.unval = value; }
	public String getUnval() {return this.unval;}
	
	private String unkmp = null;
	public void setUnkmp(String value) {  this.unkmp = value; }
	public String getUnkmp() {return this.unkmp;}
	
	private String unvali = null;
	public void setUnvali(String value) {  this.unvali = value; }
	public String getUnvali() {return this.unvali;}
	
	private String unkmpi = null;
	public void setUnkmpi(String value) {  this.unkmpi = value; }
	public String getUnkmpi() {return this.unkmpi;}
	
	private String unvale = null;
	public void setUnvale(String value) {  this.unvale = value; }
	public String getUnvale() {return this.unvale;}
	
	private String unkmpe = null;
	public void setUnkmpe(String value) {  this.unkmpe = value; }
	public String getUnkmpe() {return this.unkmpe;}
	
	
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
