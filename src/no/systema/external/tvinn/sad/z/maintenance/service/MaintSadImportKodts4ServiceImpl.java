/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.mapper.MaintSadImportKodts4Mapper;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodts4Container;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 * 
 */
public class MaintSadImportKodts4ServiceImpl implements MaintSadImportKodts4Service {
	/**
	 * 
	 */
	public JsonMaintSadImportKodts4Container getList(String utfPayload) {
		JsonMaintSadImportKodts4Container container = null;
		try{
			MaintSadImportKodts4Mapper mapper = new MaintSadImportKodts4Mapper();
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
	public JsonMaintSadImportKodts4Container doUpdate(String utfPayload) {
		JsonMaintSadImportKodts4Container container = null;
		try{
			MaintSadImportKodts4Mapper mapper = new MaintSadImportKodts4Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
