/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintKodtvaContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 29, 2018
 * 
 *
 */
public interface MaintKodtvaService {
	public JsonMaintKodtvaContainer getList(String utfPayload);
	public JsonMaintKodtvaContainer doUpdate(String utfPayload);
	
}
