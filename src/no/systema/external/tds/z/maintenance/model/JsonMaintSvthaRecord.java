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
public class JsonMaintSvthaRecord extends JsonAbstractGrandFatherRecord  {
	
	private boolean validSignature = false;
	public void setValidSignature (boolean value){ this.validSignature = value;   }   
	public boolean isValidSignature (){ return this.validSignature;   }  
	
	private boolean duplicateSignature = false;
	public void setDuplicateSignature (boolean value){ this.duplicateSignature = value;   }   
	public boolean getDuplicateSignature (){ return this.duplicateSignature;   }  
	
	private String svth_sysg = null;                                
	public void setSvth_sysg (String value){ this.svth_sysg = value;   }   
	public String getSvth_sysg (){ return this.svth_sysg;   }  
	
	private String svth_namn = null;                                
	public void setSvth_namn (String value){ this.svth_namn = value;   }   
	public String getSvth_namn (){ return this.svth_namn;   }  
	
	private String svth_usid = null;                                
	public void setSvth_usid (String value){ this.svth_usid = value;   }   
	public String getSvth_usid (){ return this.svth_usid;   }  
	
	private String svth_ann = null;                                
	public void setSvth_ann (String value){ this.svth_ann = value;   }   
	public String getSvth_ann (){ return this.svth_ann;   }  
	
	private String svth_annn = null;                                
	public void setSvth_annn (String value){ this.svth_annn = value;   }   
	public String getSvth_annn (){ return this.svth_annn;   }  
	
	private String svth_sms = null;                                
	public void setSvth_sms (String value){ this.svth_sms = value;   }   
	public String getSvth_sms (){ return this.svth_sms;   }  
	
	private String svth_pwd = null;                                
	public void setSvth_pwd (String value){ this.svth_pwd = value;   }   
	public String getSvth_pwd (){ return this.svth_pwd;   }  
	
	private String svth_pwdd = null;                                
	public void setSvth_pwdd (String value){ this.svth_pwdd = value;   }   
	public String getSvth_pwdd (){ return this.svth_pwdd;   }  
	
	private String svth_lock = null;                                
	public void setSvth_lock (String value){ this.svth_lock = value;   }   
	public String getSvth_lock (){ return this.svth_lock;   }  
	
	private String svth_tlf = null;                                
	public void setSvth_tlf (String value){ this.svth_tlf = value;   }   
	public String getSvth_tlf (){ return this.svth_tlf;   }  
	
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
