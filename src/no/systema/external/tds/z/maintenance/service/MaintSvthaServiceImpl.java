/**
 * 
 */
package no.systema.external.tds.z.maintenance.service;

import no.systema.external.tds.z.maintenance.mapper.MaintSvthaMapper;
import no.systema.external.tds.z.maintenance.model.JsonMaintSvthaContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 * 
 */
public class MaintSvthaServiceImpl implements MaintSvthaService {
	/**
	 * 
	 */
	public JsonMaintSvthaContainer getList(String utfPayload) {
		JsonMaintSvthaContainer container = null;
		try{
			MaintSvthaMapper mapper = new MaintSvthaMapper();
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
	public JsonMaintSvthaContainer doUpdate(String utfPayload) {
		JsonMaintSvthaContainer container = null;
		try{
			MaintSvthaMapper mapper = new MaintSvthaMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
