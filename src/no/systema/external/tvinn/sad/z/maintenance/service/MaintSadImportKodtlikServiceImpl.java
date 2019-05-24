/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodtlikContainer;
import no.systema.external.tvinn.sad.z.maintenance.mapper.MaintSadImportKodtlikMapper;

/**
 * 
 * @author oscardelatorre
 * @date Mar 29, 2018
 * 
 * 
 */
public class MaintSadImportKodtlikServiceImpl implements MaintSadImportKodtlikService {
	/**
	 * 
	 */
	public JsonMaintSadImportKodtlikContainer getList(String utfPayload) {
		JsonMaintSadImportKodtlikContainer container = null;
		try{
			MaintSadImportKodtlikMapper mapper = new MaintSadImportKodtlikMapper();
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
	public JsonMaintSadImportKodtlikContainer doUpdate(String utfPayload) {
		JsonMaintSadImportKodtlikContainer container = null;
		try{
			MaintSadImportKodtlikMapper mapper = new MaintSadImportKodtlikMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
