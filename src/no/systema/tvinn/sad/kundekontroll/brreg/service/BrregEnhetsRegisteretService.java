/**
 * 
 */
package no.systema.tvinn.sad.kundekontroll.brreg.service;

import no.systema.tvinn.sad.kundekontroll.brreg.jsonjackson.JsonEnhetsRegisteretDataCheckContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Sept 26, 2016 / copied Apr 10, 2018 Oscar
 * 
 *
 */
public interface BrregEnhetsRegisteretService {
	public JsonEnhetsRegisteretDataCheckContainer getList(String utfPayload);
}
