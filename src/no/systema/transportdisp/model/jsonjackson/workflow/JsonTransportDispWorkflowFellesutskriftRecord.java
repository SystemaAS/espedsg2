/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import java.lang.reflect.Field;
import java.util.*;
import lombok.Data;

import no.systema.main.util.AppConstants;

/**
 * @author oscardelatorre
 * @date Jan 2019
 *
 */
@Data
public class JsonTransportDispWorkflowFellesutskriftRecord {
	
	
	private String jbk;
	private String of;
	private String vf;
	private String tpfb;
	private String iffb;
	private String loss;
	private String ffak;
	private String kowfet;
	private String cm;
	private String sakode;
	private String satype;
	private String wsavd;
	private String wssg;
	private String wspro;
	private String wsopd;
	private String wsgn;
	private String wsdt1;
	private String wsdt2;
	private String wssum;
	private String wsot1;
	private String wsot2;
	private String wsot3;
	private String wsot4;
	private String wsot5;
	private String wsms1;
	private String wsms2;
	private String wsms3;
	private String wsms4;
	private String wsms5;
	private String wsms6;
	private String wsprt;
	private String wsprt2;
	//
	private String aord;
	private String goli;
	private String lali;
	
	
	


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
