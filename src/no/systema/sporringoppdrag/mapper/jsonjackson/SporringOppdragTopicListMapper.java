/**
 * 
 */
package no.systema.sporringoppdrag.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragTopicListContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragTopicListRecord;

/**
 * 
 * @author oscardelatorre
 * @date Feb 4, 2015
 * 
 * 
 */
public class SporringOppdragTopicListMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(SporringOppdragTopicListMapper.class.getName());
	
	public JsonSporringOppdragTopicListContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragTopicListContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSporringOppdragTopicListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		for (JsonSporringOppdragTopicListRecord record : topicListContainer.getQryoppdrag()){
			//DEBUG
		}
		return topicListContainer;
	}
}
