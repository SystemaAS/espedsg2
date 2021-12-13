/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.mapper;

//jackson library
import org.apache.logging.log4j.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadExportKodts9Container;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadExportKodts9Record;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mars 29, 2018
 * 
 */
public class MaintSadExportKodts9Mapper {
	private static final Logger logger = LogManager.getLogger(MaintSadExportKodts9Mapper.class.getName());
	
	public JsonMaintSadExportKodts9Container getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadExportKodts9Container container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadExportKodts9Container.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadExportKodts9Record> list = container.getList();
		for(JsonMaintSadExportKodts9Record record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
