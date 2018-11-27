/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
import lombok.Data;
/**
 * @author oscardelatorre
 * @date Nov 2018
 * 
 *
 */
@Data
public class JsonTransportDispWorkflowSpecificTripShipRecord extends JsonAbstractGrandFatherRecord{
	
	private String fefrom = null;
	private String feto = null;
	private String feleng = null;
	private String fedate = null;
	private String fefirm = null;
	private String levNavn = null;
	private String fecurr = null;
	private String fepri1 = null;
	private String fepri2 = null;
	private String fepriN = null;
	
	private String fetref = null;
	private String ferund = null;
	private String fedat2 = null;
	private String fetime = null;
	private String fefakt = null;
	private String fejour = null;
	private String feopko = null;
	private String fecc = null;
	private String fear = null;
	private String femnd = null;
	
	private String fedato = null;
	private String fedaff = null;
	private String febiln = null;
	
	
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
