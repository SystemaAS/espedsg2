/**
 * 
 */
package no.systema.espedsgadmin.service;

import java.util.List;

import no.systema.espedsgadmin.db.FileDatabaseManager;
import no.systema.espedsgadmin.model.CustomerApplicationObject;
import no.systema.espedsgadmin.model.TomcatAspApplicationObject;


/**
 * @author oscardelatorre
 * @date Apr 1, 2014
 * 
 *
 */
public class FileDatabaseServiceImpl implements FileDatabaseService {
	
	/**
	 * @return
	 * 
	 */
	public List<CustomerApplicationObject> getCustomerApplicationList(){
		
		FileDatabaseManager dbMgr = new FileDatabaseManager();
		List<CustomerApplicationObject> list = dbMgr.getCustAppMap();

		return list;
	}
	
	/**
	 * @return
	 * 
	 */
	public List<TomcatAspApplicationObject> getTomcatAspPortList(){
		
		FileDatabaseManager dbMgr = new FileDatabaseManager();
		List<TomcatAspApplicationObject> list = dbMgr.getTomcatAspCustomersPortList();

		return list;
	}
}
