/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.mapper.MaintSadExportKodts6Mapper;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadExportKodts6Container;

/**
 * 
 * @author Fredrik Möller
 * @date Sep 7, 2016
 * 
 * 
 */
public class MaintSadExportKodts6ServiceImpl implements MaintSadExportKodts6Service {
	/**
	 * 
	 */
	public JsonMaintSadExportKodts6Container getList(String utfPayload) {
		JsonMaintSadExportKodts6Container container = null;
		try{
			MaintSadExportKodts6Mapper mapper = new MaintSadExportKodts6Mapper();
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
	public JsonMaintSadExportKodts6Container doUpdate(String utfPayload) {
		JsonMaintSadExportKodts6Container container = null;
		try{
			MaintSadExportKodts6Mapper mapper = new MaintSadExportKodts6Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
