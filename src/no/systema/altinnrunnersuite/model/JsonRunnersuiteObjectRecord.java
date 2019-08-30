/**
 * 
 */
package no.systema.altinnrunnersuite.model;
import java.lang.reflect.Field;
import java.util.*;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import lombok.Data;

/**
 * @author oscardelatorre
 * @date Aug 2019
 * 
 */
@Data
public class JsonRunnersuiteObjectRecord {
	
	private String id = null; 
	private String companyName = null; 
	private String ipaddress = null; 
	private String text = null; 
	private String status = null; 
	private String description = null; 
	//
	private String serviceName = null; 
	public void setServiceName(String value) {  this.serviceName = value; }
	public String getServiceName() { 
		if(serviceUrl!=null){
			String tmp = this.serviceUrl;
			int index = tmp.lastIndexOf("/");
			this.serviceName = tmp.substring(index + 1);
		}
		return this.serviceName;
	}
	
	private String serviceUrl = null; 
	
	public String errMsg = null;
	
		
}
