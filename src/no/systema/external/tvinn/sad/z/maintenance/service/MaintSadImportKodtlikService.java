/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodtlikContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 29, 2018
 * 
 *
 */
public interface MaintSadImportKodtlikService {
	public JsonMaintSadImportKodtlikContainer getList(String utfPayload);
	public JsonMaintSadImportKodtlikContainer doUpdate(String utfPayload);
	
}
