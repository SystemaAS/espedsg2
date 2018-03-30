/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.mapper;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadFellesKodtsiContainer;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadFellesKodtsiRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mar 29, 2018
 * 
 */
public class MaintSadFellesKodtsiMapper {
	private static final Logger logger = Logger.getLogger(MaintSadFellesKodtsiMapper.class.getName());
	
	public JsonMaintSadFellesKodtsiContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadFellesKodtsiContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadFellesKodtsiContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadFellesKodtsiRecord> list = container.getList();
		for(JsonMaintSadFellesKodtsiRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
