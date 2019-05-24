/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.mapper.MaintNctsExportTransitKodeTypeMapper;
import no.systema.external.tvinn.sad.z.maintenance.mapper.MaintNctsExportTrkodfMapper;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintNctsTransitKodeTypeContainer;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintNctsTrkodfContainer;

/**
 * 
 * @author Fredrik Möller / Oscar ported
 * @date Sep 19, 2016; Mar 29, 2018
 * 
 * 
 */
public class MaintNctsExportTrkodfServiceImpl implements MaintNctsExportTrkodfService {

	@Override
	public JsonMaintNctsTrkodfContainer getList(String utfPayload) {
		JsonMaintNctsTrkodfContainer container = null;
		try{
			MaintNctsExportTrkodfMapper mapper = new MaintNctsExportTrkodfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	@Override
	public JsonMaintNctsTrkodfContainer doUpdate(String utfPayload) {
		JsonMaintNctsTrkodfContainer container = null;
		try{
			MaintNctsExportTrkodfMapper mapper = new MaintNctsExportTrkodfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

	@Override
	public JsonMaintNctsTransitKodeTypeContainer getTransitKoderList(String utfPayload) {
		JsonMaintNctsTransitKodeTypeContainer container = null;
		try{
			MaintNctsExportTransitKodeTypeMapper mapper = new MaintNctsExportTransitKodeTypeMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}

}
