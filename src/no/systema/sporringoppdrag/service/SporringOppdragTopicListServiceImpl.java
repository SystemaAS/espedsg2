/**
 * 
 */
package no.systema.sporringoppdrag.service;

import no.systema.sporringoppdrag.mapper.jsonjackson.SporringOppdragTopicListMapper;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 4, 2015
 * 
 * 
 */
public class SporringOppdragTopicListServiceImpl implements SporringOppdragTopicListService {

	public JsonSporringOppdragTopicListContainer getSporringOppdragTopicListContainer(String utfPayload) {
		JsonSporringOppdragTopicListContainer listContainer = null;
		try{
			SporringOppdragTopicListMapper mapper = new SporringOppdragTopicListMapper();
			listContainer = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listContainer;
		
	}

}
