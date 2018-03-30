/**
 * 
 */
package no.systema.external.skat.z.maintenance.service;

import no.systema.external.skat.z.maintenance.model.JsonMaintDktvkContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 29, 2018
 * 
 *
 */
public interface MaintDktvkService {
	public JsonMaintDktvkContainer getList(String utfPayload);
	public JsonMaintDktvkContainer doUpdate(String utfPayload);
	
}
