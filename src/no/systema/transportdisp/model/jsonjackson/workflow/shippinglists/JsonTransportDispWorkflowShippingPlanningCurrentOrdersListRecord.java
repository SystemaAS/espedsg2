/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.shippinglists;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
import lombok.Data;
/**
 * @author oscardelatorre
 * @date Apr 11, 2015
 * 
 *
 */
@Data
public class JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord extends JsonAbstractGrandFatherRecord{
	
	private String heavd = null;
	private String heopd = null;
	private String herfa = null;
	private String henas = null;
	private String henak = null;
	private String hevs1 = null;
	private String wspos = null;
	private String wsblorr = null;
	private String hest = null;
	private String hent = null;
	private String hevkt = null;
	private String hem3 = null;
	private String helm = null;
	private String hepoen = null;
	private String interninfo = null;
	private String dftoll = null;
	private String heklmo = null;
	private String hedtmo = null;
	private String hesgm = null;
	private String ttstat = null;
	private String trsdfd = null;
	private String trsdfk = null;
	private String trsdtd = null;
	private String trsdtk = null;
	
	
	
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
