/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.mapper.MaintSadExportKodts9Mapper;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadExportKodts9Container;

/**
 * 
 * @author oscardelatorre
 * @date Mars 29, 2018
 * 
 * 
 */
public class MaintSadExportKodts9ServiceImpl implements MaintSadExportKodts9Service {
	/**
	 * 
	 */
	public JsonMaintSadExportKodts9Container getList(String utfPayload) {
		JsonMaintSadExportKodts9Container container = null;
		try{
			MaintSadExportKodts9Mapper mapper = new MaintSadExportKodts9Mapper();
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
	public JsonMaintSadExportKodts9Container doUpdate(String utfPayload) {
		JsonMaintSadExportKodts9Container container = null;
		try{
			MaintSadExportKodts9Mapper mapper = new MaintSadExportKodts9Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
