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
 * @date Maj 12, 2015
 * 
 */
public class JsonTransportDispLoadUnloadPlacesRecord extends JsonAbstractGrandFatherRecord  {
	
	private String kotmko = null;
	public void setKotmko(String value){ this.kotmko = value;}
	public String getKotmko(){ return this.kotmko; }
	
	private String kotmnv = null;
	public void setKotmnv(String value){ this.kotmnv = value;}
	public String getKotmnv(){ return this.kotmnv; }
	
	
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
