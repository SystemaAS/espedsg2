/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
import lombok.Data;
/**
 * @author oscardelatorre
 * @date May 09, 2019
 * 
 *
 */
@Data
public class JsonTransportDispTTraceCodesRecord extends JsonAbstractGrandFatherRecord{

	
	private String kfkod = null;
	private String kftxt = null;
	
	
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
