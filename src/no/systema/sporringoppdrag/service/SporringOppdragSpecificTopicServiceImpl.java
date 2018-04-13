/**
 * 
 */
package no.systema.sporringoppdrag.service;

import no.systema.sporringoppdrag.mapper.jsonjackson.SporringOppdragSpecificTopicMapper;
import no.systema.sporringoppdrag.mapper.jsonjackson.SporringOppdragSpecificTopicSingleChildMapper;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildDocumentContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildFreetextContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildHendelseslogContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildInvoiceContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.colli.JsonSporringOppdragSpecificTopicSingleChildColliContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.invoice.JsonSporringOppdragSpecificTopicSingleChildInvoiceContainer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 12, 2015
 * 
 * 
 */
public class SporringOppdragSpecificTopicServiceImpl implements SporringOppdragSpecificTopicService {
	/**
	 * 
	 */
	public JsonSporringOppdragSpecificTopicContainer getSporringOppdragSpecificTopicContainer(String utfPayload) {
		JsonSporringOppdragSpecificTopicContainer container = null;
		try{
			SporringOppdragSpecificTopicMapper mapper = new SporringOppdragSpecificTopicMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * 
	 */
	public JsonSporringOppdragSpecificTopicChildDocumentContainer getSporringOppdragSpecificTopicChildDocumentContainer(String utfPayload){
		JsonSporringOppdragSpecificTopicChildDocumentContainer container = null;
		try{
			SporringOppdragSpecificTopicMapper mapper = new SporringOppdragSpecificTopicMapper();
			container = mapper.getChildDocumentContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * 
	 */
	public JsonSporringOppdragSpecificTopicChildFreetextContainer getSporringOppdragSpecificTopicChildFreetextContainer(String utfPayload){
		JsonSporringOppdragSpecificTopicChildFreetextContainer container = null;
		try{
			SporringOppdragSpecificTopicMapper mapper = new SporringOppdragSpecificTopicMapper();
			container = mapper.getChildFreetextContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * 
	 */
	public JsonSporringOppdragSpecificTopicChildInvoiceContainer getSporringOppdragSpecificTopicChildInvoiceContainer(String utfPayload){
		JsonSporringOppdragSpecificTopicChildInvoiceContainer container = null;
		try{
			SporringOppdragSpecificTopicMapper mapper = new SporringOppdragSpecificTopicMapper();
			container = mapper.getChildInvoiceContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * 
	 */
	public JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer getSporringOppdragSpecificTopicChildFriesokeVeierContainer(String utfPayload){
		JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer container = null;
		try{
			SporringOppdragSpecificTopicMapper mapper = new SporringOppdragSpecificTopicMapper();
			container = mapper.getChildFriesokeVeierContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	/**
	 * Hendelseslog
	 */
	public JsonSporringOppdragSpecificTopicChildHendelseslogContainer getSporringOppdragSpecificTopicChildHendelseslogContainer(String utfPayload){
		JsonSporringOppdragSpecificTopicChildHendelseslogContainer container = null;
		try{
			SporringOppdragSpecificTopicMapper mapper = new SporringOppdragSpecificTopicMapper();
			container = mapper.getChildHendelseslogContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
		
	}
	
	/**
	 * Single Child - INVOICE
	 * 
	 */
	public JsonSporringOppdragSpecificTopicSingleChildInvoiceContainer getSporringOppdragSpecificTopicSingleChildInvoiceContainer(String utfPayload){
		JsonSporringOppdragSpecificTopicSingleChildInvoiceContainer container = null;
		try{
			SporringOppdragSpecificTopicSingleChildMapper mapper = new SporringOppdragSpecificTopicSingleChildMapper();
			container = mapper.getInvoiceContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * Singe Child - COLLI
	 * 
	 */
	public JsonSporringOppdragSpecificTopicSingleChildColliContainer getSporringOppdragSpecificTopicSingleChildColliContainer(String utfPayload){
		JsonSporringOppdragSpecificTopicSingleChildColliContainer container = null;
		try{
			SporringOppdragSpecificTopicSingleChildMapper mapper = new SporringOppdragSpecificTopicSingleChildMapper();
			container = mapper.getColliContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	

}
