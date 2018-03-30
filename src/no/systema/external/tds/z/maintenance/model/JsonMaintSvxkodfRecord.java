package no.systema.external.tds.z.maintenance.model;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.main.util.*;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 */
public class JsonMaintSvxkodfRecord extends JsonAbstractGrandFatherRecord {
	
	private String tkunik = null;                             
	public void setTkunik (String value){ this.tkunik = value;   }   
	public String getTkunik (){ return this.tkunik;   }  
	
	private String tkkode = null;                                
	public void setTkkode (String value){ this.tkkode = value;   }   
	public String getTkkode (){ return this.tkkode;   }  
	
	private String tktxtn = null;                                
	public void setTktxtn (String value){ this.tktxtn = value;   }   
	public String getTktxtn (){ return this.tktxtn;   }  
	
	private String tktxte = null;                                
	public void setTktxte (String value){ this.tktxte = value;   }   
	public String getTktxte (){ return this.tktxte;   }  
	
	private String tkavg = null;                                
	public void setTkavg (String value){ this.tkavg = value;   }   
	public String getTkavg (){ return this.tkavg;   }  
	
	
	private String tkank = null;                                
	public void setTkank (String value){ this.tkank = value;   }   
	public String getTkank (){ return this.tkank;   }  
	
	private String tktrs = null;                                
	public void setTktrs (String value){ this.tktrs = value;   }   
	public String getTktrs (){ return this.tktrs;   }  
	
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
