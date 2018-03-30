/**
 * 
 */
package no.systema.external.skat.z.maintenance.service;

import no.systema.external.skat.z.maintenance.mapper.MaintDkxkodfMapper;
import no.systema.external.skat.z.maintenance.model.JsonMaintDkxkodfContainer;


/**
 * 
 * @author oscardelatorre
 * @date Apr 10, 2017
 * 
 * 
 */
public class MaintDkxkodfServiceImpl implements MaintDkxkodfService {
	/**
	 * 
	 */
	public JsonMaintDkxkodfContainer getList(String utfPayload) {
		JsonMaintDkxkodfContainer container = null;
		try{
			MaintDkxkodfMapper mapper = new MaintDkxkodfMapper();
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
	public JsonMaintDkxkodfContainer doUpdate(String utfPayload) {
		JsonMaintDkxkodfContainer container = null;
		try{
			MaintDkxkodfMapper mapper = new MaintDkxkodfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
