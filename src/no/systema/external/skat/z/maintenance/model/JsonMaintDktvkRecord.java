package no.systema.external.skat.z.maintenance.model;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 */
public class JsonMaintDktvkRecord extends JsonAbstractGrandFatherRecord {

	private String dkvk_kd = null;                                
	public void setDkvk_kd (String value){ this.dkvk_kd = value;   }   
	public String getDkvk_kd (){ return this.dkvk_kd;   }  
	
	private String dkvk_dts = null; 
	public void setDkvk_dts (String value){ this.dkvk_dts = value;   }   
	public String getDkvk_dts (){ return this.dkvk_dts;   }              

	private String dkvk_dte = null;
	public void setDkvk_dte (String value){ this.dkvk_dte = value;   }   
	public String getDkvk_dte (){ return this.dkvk_dte;   }              

	private String dkvk_omr = null;
	public void setDkvk_omr (String value){ this.dkvk_omr = value;   }   
	public String getDkvk_omr (){ return this.dkvk_omr;   }              

	private String dkvk_krs = null; 
	public void setDkvk_krs (String value){ this.dkvk_krs = value;   }   
	public String getDkvk_krs (){ return this.dkvk_krs;   }              

	
	/**
	 * 
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
