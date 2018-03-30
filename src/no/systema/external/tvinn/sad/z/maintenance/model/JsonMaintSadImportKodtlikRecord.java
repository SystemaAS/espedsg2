/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.model;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.main.util.MainDateFormatter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mar 29, 2018
 *
 */
public class JsonMaintSadImportKodtlikRecord extends JsonAbstractGrandFatherRecord {
	private MainDateFormatter dateFormatter = new MainDateFormatter();
	
	private String klista = null;
	public void setKlista(String value) {  this.klista = value; }
	public String getKlista() { return this.klista;}
	
	private String kliuni = null;
	public void setKliuni(String value) {  this.kliuni = value; }
	public String getKliuni() { return this.kliuni;}
	
	private String klikod = null;
	public void setKlikod(String value) {  this.klikod = value; }
	public String getKlikod() { return this.klikod;}
	
	private String klinav = null;
	public void setKlinav(String value) {  this.klinav = value; }
	public String getKlinav() { return this.klinav;}

	private String klisto = null;
	public void setKlisto(String value) {  this.klisto = value; }
	public String getKlisto() { return this.klisto;}
	
	private String klixxx = null;
	public void setKlixxx(String value) {  this.klixxx = value; }
	public String getKlixxx() { return this.klixxx;}
	
	/**
	 * 
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
