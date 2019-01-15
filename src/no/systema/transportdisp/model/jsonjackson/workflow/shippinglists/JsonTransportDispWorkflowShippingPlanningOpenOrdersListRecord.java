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
public class JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord extends JsonAbstractGrandFatherRecord{
	
	private String heavd = null;
	private String heopd = null;
	private String herfa = null;
	private String hepro = null;
	private String henas = null;
	private String henak = null;
	private String helka = null;
	private String heads3 = null;
	private String hetri = null;
	private String headk3 = null;
	private String hevs1 = null;
	private String hesdf = null;
	private String hesdt = null;
	private String hent = null;
	private String hevkt = null;
	private String hem3 = null;
	private String helm = null;
	private String trsdfd = null;
	private String trsdfk = null;
	private String trsdtd = null;
	private String trsdtk = null;
	private String heot = null;
	private String hesg = null;
	private String hepoen = null;
	private String interninfo = null;
	//Prebooking
	private String hestn7 = null;
	
	private String dftoll = null;
	private String dista1 = null;
	private String ttstat = null;
	private String hesgm = null;
	private String hedtmo = null;
	private String heklmo = null;
	private String hepk1 = null;
	private String hepk7 = null;
	
	
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
