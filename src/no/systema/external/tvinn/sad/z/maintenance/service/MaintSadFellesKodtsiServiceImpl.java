/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.mapper.MaintSadFellesKodtsiMapper;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadFellesKodtsiContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 29, 2018
 * 
 * 
 */
public class MaintSadFellesKodtsiServiceImpl implements MaintSadFellesKodtsiService {
	/**
	 * 
	 */
	public JsonMaintSadFellesKodtsiContainer getList(String utfPayload) {
		JsonMaintSadFellesKodtsiContainer container = null;
		try{
			MaintSadFellesKodtsiMapper mapper = new MaintSadFellesKodtsiMapper();
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
	public JsonMaintSadFellesKodtsiContainer doUpdate(String utfPayload) {
		JsonMaintSadFellesKodtsiContainer container = null;
		try{
			MaintSadFellesKodtsiMapper mapper = new MaintSadFellesKodtsiMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
