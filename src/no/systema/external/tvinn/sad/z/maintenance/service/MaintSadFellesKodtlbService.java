/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadFellesKodtlbContainer;

/**
 * 
 * @author oscardelatorre
 * @date Okt 21, 2016
 * 
 *
 */
public interface MaintSadFellesKodtlbService {
	public JsonMaintSadFellesKodtlbContainer getList(String utfPayload);
	public JsonMaintSadFellesKodtlbContainer doUpdate(String utfPayload);
	
}
