/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadExportKodts9Container;

/**
 * 
 * @author oscardelatorre
 * @date Mars 29, 2018
 * 
 *
 */
public interface MaintSadExportKodts9Service {
	public JsonMaintSadExportKodts9Container getList(String utfPayload);
	public JsonMaintSadExportKodts9Container doUpdate(String utfPayload);
	
}
