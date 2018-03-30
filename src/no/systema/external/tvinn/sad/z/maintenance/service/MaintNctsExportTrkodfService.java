/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintNctsTransitKodeTypeContainer;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintNctsTrkodfContainer;

/**
 * 
 * @author Fredrik Möller / Oscar ported
 * @date Okt 17, 2016; Mar 29, 2018
 * 
 *
 */
public interface MaintNctsExportTrkodfService {
	public JsonMaintNctsTrkodfContainer getList(String utfPayload);
	public JsonMaintNctsTrkodfContainer doUpdate(String utfPayload);
	
	/**
	 * Retrieving TransitKoder for dropdown
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonMaintNctsTransitKodeTypeContainer getTransitKoderList(String utfPayload);

}
