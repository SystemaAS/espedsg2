/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodts4Container;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 *
 */
public interface MaintSadImportKodts4Service {
	public JsonMaintSadImportKodts4Container getList(String utfPayload);
	public JsonMaintSadImportKodts4Container doUpdate(String utfPayload);
	
}
