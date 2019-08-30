/**
 * 
 */
package no.systema.altinnrunnersuite.service;

import org.springframework.core.io.Resource;
import no.systema.aespedsgtestersuite.model.JsonTestersuiteObjectContainer;
/**
 * 
 * Request to the back-end usually returning a Payload String (JSON or other list structure)
 * 
 * @author oscardelatorre
 * Apr 09, 2018
 */
public interface RunnersuiteService {
	public JsonTestersuiteObjectContainer getContainer(String utfPayload);
	
}
