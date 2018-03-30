/**
 * 
 */
package no.systema.external.tds.z.maintenance.service;

import no.systema.external.tds.z.maintenance.model.JsonMaintSvthaContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 *
 */
public interface MaintSvthaService {
	public JsonMaintSvthaContainer getList(String utfPayload);
	public JsonMaintSvthaContainer doUpdate(String utfPayload);
	
}
