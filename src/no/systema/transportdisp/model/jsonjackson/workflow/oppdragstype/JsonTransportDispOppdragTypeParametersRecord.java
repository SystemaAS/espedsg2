/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * @author oscardelatorre
 * @date May 27, 2015
 * 
 */
public class JsonTransportDispOppdragTypeParametersRecord extends JsonAbstractGrandFatherRecord  {
	
	private String opdTyp = null;
	public void setOpdTyp(String value){ this.opdTyp = value;}
	public String getOpdTyp(){ return this.opdTyp; }
	
	private String klokkeJN = null;
	public void setKlokkeJN(String value){ this.klokkeJN = value;}
	public String getKlokkeJN(){ return this.klokkeJN; }
	
	private String test1 = null;
	public void setTest1(String value){ this.test1 = value;}
	public String getTest1(){ return this.test1; }
	
	private String test2 = null;
	public void setTest2(String value){ this.test2 = value;}
	public String getTest2(){ return this.test2; }
	
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
