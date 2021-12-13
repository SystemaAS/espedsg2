/**
 * 
 */
package no.systema.altinnrunnersuite.mapper;

//
import java.util.Collection;

//jackson library
import org.apache.logging.log4j.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.aespedsgtestersuite.model.JsonTestersuiteObjectContainer;
import no.systema.aespedsgtestersuite.model.JsonTestersuiteObjectRecord;

/**
 * @author oscardelatorre
 * @date Apr 09, 2018
 * 
 */
public class JsonRunnersuiteMapper {
	private static final Logger logger = LogManager.getLogger(JsonRunnersuiteMapper.class.getName());
	
	public JsonTestersuiteObjectContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTestersuiteObjectContainer container = mapper.readValue(utfPayload.getBytes(), JsonTestersuiteObjectContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTestersuiteObjectRecord> list = container.getList();
		for(JsonTestersuiteObjectRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
