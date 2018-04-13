/**
 * 
 */
package no.systema.sporringoppdrag.service;

import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 4, 2015
 * 
 *
 */
public interface SporringOppdragTopicListService {
	public JsonSporringOppdragTopicListContainer getSporringOppdragTopicListContainer(String utfPayload);
}
