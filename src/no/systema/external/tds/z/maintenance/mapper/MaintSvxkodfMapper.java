/**
 * 
 */
package no.systema.external.tds.z.maintenance.mapper;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.external.tds.z.maintenance.model.JsonMaintSvxkodfContainer;
import no.systema.external.tds.z.maintenance.model.JsonMaintSvxkodfRecord;

/**
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 */
public class MaintSvxkodfMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintSvxkodfMapper.class.getName());
	
	public JsonMaintSvxkodfContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonMaintSvxkodfContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSvxkodfContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSvxkodfRecord> list = container.getList();
		for(JsonMaintSvxkodfRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
