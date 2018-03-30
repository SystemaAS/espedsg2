/**
 * 
 */
package no.systema.external.tds.z.maintenance.service;

import no.systema.external.tds.z.maintenance.mapper.MaintSvxkodfMapper;
import no.systema.external.tds.z.maintenance.model.JsonMaintSvxkodfContainer;


/**
 * 
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 * 
 */
public class MaintSvxkodfServiceImpl implements MaintSvxkodfService {
	/**
	 * 
	 */
	public JsonMaintSvxkodfContainer getList(String utfPayload) {
		JsonMaintSvxkodfContainer container = null;
		try{
			MaintSvxkodfMapper mapper = new MaintSvxkodfMapper();
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
	public JsonMaintSvxkodfContainer doUpdate(String utfPayload) {
		JsonMaintSvxkodfContainer container = null;
		try{
			MaintSvxkodfMapper mapper = new MaintSvxkodfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
