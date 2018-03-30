package no.systema.external.tds.z.maintenance.model;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 */
public class JsonMaintSvtvkRecord extends JsonAbstractGrandFatherRecord    {

	private String svvk_kd = null;                                
	public void setSvvk_kd (String value){ this.svvk_kd = value;   }   
	public String getSvvk_kd (){ return this.svvk_kd;   }  
	
	private String svvk_dts = null; 
	public void setSvvk_dts (String value){ this.svvk_dts = value;   }   
	public String getSvvk_dts (){ return this.svvk_dts;   }              

	private String svvk_dte = null;
	public void setSvvk_dte (String value){ this.svvk_dte = value;   }   
	public String getSvvk_dte (){ return this.svvk_dte;   }              

	private String svvk_omr = null;
	public void setSvvk_omr (String value){ this.svvk_omr = value;   }   
	public String getSvvk_omr (){ return this.svvk_omr;   }              

	private String svvk_krs = null; 
	public void setSvvk_krs (String value){ this.svvk_krs = value;   }   
	public String getSvvk_krs (){ return this.svvk_krs;   }              

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
