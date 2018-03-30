/**
 * 
 */
package no.systema.external.skat.z.maintenance.service;

import no.systema.external.skat.z.maintenance.model.JsonMaintDkxkodfContainer;;

/**
 * 
 * @author oscardelatorre
 * @date Apr 10, 2016
 * 
 *
 */
public interface MaintDkxkodfService {
	public JsonMaintDkxkodfContainer getList(String utfPayload);
	public JsonMaintDkxkodfContainer doUpdate(String utfPayload);
	
}
