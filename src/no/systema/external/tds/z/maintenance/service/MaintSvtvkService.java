/**
 * 
 */
package no.systema.external.tds.z.maintenance.service;

import no.systema.external.tds.z.maintenance.model.JsonMaintSvtvkContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 *
 */
public interface MaintSvtvkService {
	public JsonMaintSvtvkContainer getList(String utfPayload);
	public JsonMaintSvtvkContainer doUpdate(String utfPayload);
	
}
