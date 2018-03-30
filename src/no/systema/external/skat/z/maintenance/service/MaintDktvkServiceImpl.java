/**
 * 
 */
package no.systema.external.skat.z.maintenance.service;

import no.systema.external.skat.z.maintenance.mapper.MaintDktvkMapper;
import no.systema.external.skat.z.maintenance.model.JsonMaintDktvkContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 29, 2018
 * 
 * 
 */
public class MaintDktvkServiceImpl implements MaintDktvkService {
	/**
	 * 
	 */
	public JsonMaintDktvkContainer getList(String utfPayload) {
		JsonMaintDktvkContainer container = null;
		try{
			MaintDktvkMapper mapper = new MaintDktvkMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonMaintDktvkContainer doUpdate(String utfPayload) {
		JsonMaintDktvkContainer container = null;
		try{
			MaintDktvkMapper mapper = new MaintDktvkMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
