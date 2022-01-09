/**
 * 
 */
package no.systema.aespedsgtestersuite.service;

import org.slf4j.*;

import no.systema.aespedsgtestersuite.mapper.JsonEspedsgTestersuiteMapper;
import no.systema.aespedsgtestersuite.model.JsonTestersuiteObjectContainer;

/**
 * 
 * @author oscardelatorre
 * Apr 09, 2018
 * 
 */
public class TestersuiteServiceImpl implements TestersuiteService{
	private static final Logger logger = LoggerFactory.getLogger(TestersuiteServiceImpl.class.getName());
	
	public JsonTestersuiteObjectContainer getContainer(String utfPayload) {
		JsonTestersuiteObjectContainer container = null;
		try{
			JsonEspedsgTestersuiteMapper mapper = new JsonEspedsgTestersuiteMapper();
			container = mapper.getContainer(utfPayload);
			//logger.info("CONTAINER:" + container.getErrMsg());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
}
