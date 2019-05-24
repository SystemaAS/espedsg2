/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadFellesKodtsiContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 29, 2018
 * 
 *
 */
public interface MaintSadFellesKodtsiService {
	public JsonMaintSadFellesKodtsiContainer getList(String utfPayload);
	public JsonMaintSadFellesKodtsiContainer doUpdate(String utfPayload);
	
}
