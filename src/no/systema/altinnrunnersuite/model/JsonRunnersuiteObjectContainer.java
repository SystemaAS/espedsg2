/**
 * 
 */
package no.systema.altinnrunnersuite.model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * @author oscardelatorre
 * @date Apr 09, 2018
 * 
 */
public class JsonRunnersuiteObjectContainer {
	
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	
	private Collection<JsonRunnersuiteObjectRecord>list;
	public void setList(Collection<JsonRunnersuiteObjectRecord> value){ this.list = value; }
	public Collection<JsonRunnersuiteObjectRecord> getList(){ return list; }
	
	
	
	
}
