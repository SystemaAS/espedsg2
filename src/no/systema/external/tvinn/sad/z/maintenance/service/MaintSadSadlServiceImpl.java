/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.mapper.MaintSadSadlMapper;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadSadlContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 * 
 */
public class MaintSadSadlServiceImpl implements MaintSadSadlService {
	/**
	 * 
	 */
	public JsonMaintSadSadlContainer getList(String utfPayload) {
		JsonMaintSadSadlContainer container = null;
		try{
			MaintSadSadlMapper mapper = new MaintSadSadlMapper();
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
	public JsonMaintSadSadlContainer doUpdate(String utfPayload) {
		JsonMaintSadSadlContainer container = null;
		try{
			MaintSadSadlMapper mapper = new MaintSadSadlMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
