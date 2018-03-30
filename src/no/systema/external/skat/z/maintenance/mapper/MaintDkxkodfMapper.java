/**
 * 
 */
package no.systema.external.skat.z.maintenance.mapper;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.external.skat.z.maintenance.model.JsonMaintDkxkodfContainer;
import no.systema.external.skat.z.maintenance.model.JsonMaintDkxkodfRecord;

/**
 * @author oscardelatorre
 * @date Mar 29, 2018
 * 
 */
public class MaintDkxkodfMapper {
	private static final Logger logger = Logger.getLogger(MaintDkxkodfMapper.class.getName());
	
	public JsonMaintDkxkodfContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintDkxkodfContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintDkxkodfContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintDkxkodfRecord> list = container.getList();
		for(JsonMaintDkxkodfRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
