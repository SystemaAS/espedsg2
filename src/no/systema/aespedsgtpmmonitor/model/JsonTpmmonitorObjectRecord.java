/**
 * 
 */
package no.systema.aespedsgtpmmonitor.model;
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
public class JsonTpmmonitorObjectRecord {
	
	private String module = null; 
	private String url = null; 
	private String desc = null;
	
	
		
}
