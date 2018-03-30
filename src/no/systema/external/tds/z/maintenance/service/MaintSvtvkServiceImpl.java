/**
 * 
 */
package no.systema.external.tds.z.maintenance.service;

import no.systema.external.tds.z.maintenance.mapper.MaintSvtvkMapper;
import no.systema.external.tds.z.maintenance.model.JsonMaintSvtvkContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 * 
 */
public class MaintSvtvkServiceImpl implements MaintSvtvkService {
	/**
	 * 
	 */
	public JsonMaintSvtvkContainer getList(String utfPayload) {
		JsonMaintSvtvkContainer container = null;
		try{
			MaintSvtvkMapper mapper = new MaintSvtvkMapper();
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
	public JsonMaintSvtvkContainer doUpdate(String utfPayload) {
		JsonMaintSvtvkContainer container = null;
		try{
			MaintSvtvkMapper mapper = new MaintSvtvkMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
