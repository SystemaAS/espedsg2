/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.mapper.MaintKodtvaMapper;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintKodtvaContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 7, 2016
 * 
 * 
 */
public class MaintKodtvaServiceImpl implements MaintKodtvaService {
	/**
	 * 
	 */
	public JsonMaintKodtvaContainer getList(String utfPayload) {
		JsonMaintKodtvaContainer container = null;
		try{
			MaintKodtvaMapper mapper = new MaintKodtvaMapper();
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
	public JsonMaintKodtvaContainer doUpdate(String utfPayload) {
		JsonMaintKodtvaContainer container = null;
		try{
			MaintKodtvaMapper mapper = new MaintKodtvaMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
