/**
 * 
 */
package no.systema.external.tds.z.maintenance.service;

import no.systema.external.tds.z.maintenance.model.JsonMaintSvxkodfContainer;;

/**
 * 
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 *
 */
public interface MaintSvxkodfService {
	public JsonMaintSvxkodfContainer getList(String utfPayload);
	public JsonMaintSvxkodfContainer doUpdate(String utfPayload);
	
}
