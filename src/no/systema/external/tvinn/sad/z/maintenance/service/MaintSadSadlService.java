
/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadSadlContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 29, 2018
 * 
 *
 */
public interface MaintSadSadlService {
	public JsonMaintSadSadlContainer getList(String utfPayload);
	public JsonMaintSadSadlContainer doUpdate(String utfPayload);
	
}
