/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodts1Container;

/**
 * 
 * @author oscardelatorre
 * @date Mars 29, 2018
 * 
 *
 */
public interface MaintSadImportKodts1Service {
	public JsonMaintSadImportKodts1Container getList(String utfPayload);
	public JsonMaintSadImportKodts1Container doUpdate(String utfPayload);
	
}
