/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.mapper.MaintSadFellesKodtlbMapper;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadFellesKodtlbContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 29, 2018
 * 
 * 
 */
public class MaintSadFellesKodtlbServiceImpl implements MaintSadFellesKodtlbService {
	/**
	 * 
	 */
	public JsonMaintSadFellesKodtlbContainer getList(String utfPayload) {
		JsonMaintSadFellesKodtlbContainer container = null;
		try{
			MaintSadFellesKodtlbMapper mapper = new MaintSadFellesKodtlbMapper();
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
	public JsonMaintSadFellesKodtlbContainer doUpdate(String utfPayload) {
		JsonMaintSadFellesKodtlbContainer container = null;
		try{
			MaintSadFellesKodtlbMapper mapper = new MaintSadFellesKodtlbMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
