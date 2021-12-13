/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.mapper;

//jackson library
import org.apache.logging.log4j.*;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodts1Container;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodts1Record;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mars 29, 2018
 * 
 */
public class MaintSadImportKodts1Mapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = LogManager.getLogger(MaintSadImportKodts1Mapper.class.getName());
	
	public JsonMaintSadImportKodts1Container getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodts1Container container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSadImportKodts1Container.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportKodts1Record> list = container.getList();
		for(JsonMaintSadImportKodts1Record record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
