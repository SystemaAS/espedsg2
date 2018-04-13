/**
 * 
 */
package no.systema.sporringoppdrag.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

//application library
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicRecord;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildDocumentContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildDocumentRecord;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildFreetextContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildFreetextRecord;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildInvoiceContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildInvoiceRecord;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildFriesokeVeierRecord;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildHendelseslogContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildHendelseslogRecord;




/**
 * 
 * @author oscardelatorre
 * @date Feb 11, 2015
 * 
 * 
 */
public class SporringOppdragSpecificTopicMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(SporringOppdragSpecificTopicMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicRecord record : container.getDspoppdrag()){
			//DEBUG
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicChildDocumentContainer getChildDocumentContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicChildDocumentContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicChildDocumentContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicChildDocumentRecord record : container.getGetdoc()){
			//DEBUG
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicChildFreetextContainer getChildFreetextContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicChildFreetextContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicChildFreetextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicChildFreetextRecord record : container.getFreetextlistA()){
			//DEBUG
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicChildInvoiceContainer getChildInvoiceContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicChildInvoiceContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicChildInvoiceContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicChildInvoiceRecord record : container.getGetfak()){
			//DEBUG
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer getChildFriesokeVeierContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicChildFriesokeVeierRecord record : container.getGetfriesokeveier()){
			//DEBUG
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicChildHendelseslogContainer getChildHendelseslogContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicChildHendelseslogContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicChildHendelseslogContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicChildHendelseslogRecord record : container.getGettrackandtrace()){
			//DEBUG
		}
		return container;
	}
}
