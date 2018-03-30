/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.mapper;

//jackson library
import org.apache.log4j.Logger;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodts4Container;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodts4Record;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mars 29, 2018
 * 
 */
public class MaintSadImportKodts4Mapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodts4Mapper.class.getName());
	
	public JsonMaintSadImportKodts4Container getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodts4Container container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSadImportKodts4Container.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportKodts4Record> list = container.getList();
		for(JsonMaintSadImportKodts4Record record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
