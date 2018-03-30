/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.service;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadExportKodts6Container;

/**
 * 
 * @author Fredrik Möller /Oscar ported
 * @date Sept 7, 2016; Mar 29, 2018
 * 
 *
 */
public interface MaintSadExportKodts6Service {
	public JsonMaintSadExportKodts6Container getList(String utfPayload);
	public JsonMaintSadExportKodts6Container doUpdate(String utfPayload);
}
