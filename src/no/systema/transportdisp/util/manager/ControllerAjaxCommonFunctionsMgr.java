	/**
 * 
 */
package no.systema.transportdisp.util.manager;

import java.util.*;

import org.apache.log4j.Logger;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesRecord;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;

//import no.systema.tvinn.sad.model.external.url.UrlTvinnSadTolltariffenObject;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripArchivedDocsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripArchivedDocsRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripMessageNoteContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripMessageNoteRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.codes.JsonTransportDispCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.codes.JsonTransportDispCodeRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsRecord;
import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.service.TransportDispChildWindowService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;


/**
 * Work with Trips - Transport Disponering
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a caller when needed.
 * 
 * It aims to provide a single-point-of-entry for common methods found from within an MCV-Controller or an Ajax-Controller
 *  
 * 
 * @author oscardelatorre
 * @date Maj 5, 2015
 * 
 * 
 */
public class ControllerAjaxCommonFunctionsMgr {
	private static final Logger logger = Logger.getLogger(ControllerAjaxCommonFunctionsMgr.class.getName());
	private UrlCgiProxyService urlCgiProxyService;
	private TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService;
	private TransportDispChildWindowService transportDispChildWindowService;
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	//required constructor (when applicable)
	public ControllerAjaxCommonFunctionsMgr(UrlCgiProxyService urlCgiProxyService, TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService){
		this.urlCgiProxyService = urlCgiProxyService;
		this.transportDispWorkflowSpecificTripService = transportDispWorkflowSpecificTripService;
	}
	//required constructor (when applicable)
	public ControllerAjaxCommonFunctionsMgr(UrlCgiProxyService urlCgiProxyService, TransportDispChildWindowService transportDispChildWindowService){
		this.urlCgiProxyService = urlCgiProxyService;
		this.transportDispChildWindowService = transportDispChildWindowService;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param avdNr
	 * @param tripNr
	 * @return
	 */
	public Collection<JsonTransportDispWorkflowSpecificTripMessageNoteRecord> fetchMessageNote(String applicationUser, String avdNr, String tripNr){
		Collection<JsonTransportDispWorkflowSpecificTripMessageNoteRecord> outputList = new ArrayList<JsonTransportDispWorkflowSpecificTripMessageNoteRecord>();
		//===========
		//FETCH LIST
		//===========
		//get BASE URL
		final String BASE_LIST_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_MESSAGE_NOTE_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);
		if(avdNr!=null && !"".equals(avdNr)){ urlRequestParams.append("&wsavd=" + avdNr); }
		if(tripNr!=null && !"".equals(tripNr)){ urlRequestParams.append("&wstur=" + tripNr); }
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_LIST_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_LIST_URL, urlRequestParams.toString());
		//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonTransportDispWorkflowSpecificTripMessageNoteContainer messageNoteContainer = this.transportDispWorkflowSpecificTripService.getMessageNoteContainer(jsonPayload);
    		outputList = messageNoteContainer.getFreetextlist();
    		for(JsonTransportDispWorkflowSpecificTripMessageNoteRecord note: outputList){
    			logger.info(note.getFrttxt());
    		}
    		logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    	}
    	return outputList;
		
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param tripNr
	 * @return
	 */
	public Collection<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord> fetchTripHeadingArchiveDocs(String applicationUser, String tripNr) {
		 Collection<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord> outputList = new ArrayList<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord>();
		 //===========
		 //FETCH LIST
		 //===========
		 logger.info("Inside: getTripHeadingArchiveDocs");
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_ARCHIVED_DOCS_URL;
		 String urlRequestParamsKeys = "user=" + applicationUser + "&wstur=" + tripNr;
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 if(jsonPayload!=null){
			 	try{
			 		JsonTransportDispWorkflowSpecificTripArchivedDocsContainer container = this.transportDispWorkflowSpecificTripService.getArchivedDocsContainer(jsonPayload);
					if(container!=null){
						outputList = container.getGetdoctrip();
						for(JsonTransportDispWorkflowSpecificTripArchivedDocsRecord record : outputList){
							//logger.info("####Link:" + record.getDoclnk());
						}
					}
			 	}catch(Exception e){
			 		e.printStackTrace();
			 	}
			 }
		 return outputList;
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @return
	 */
	public Collection<JsonPostalCodesRecord> fetchPostalCodes (String applicationUser,JsonPostalCodesRecord recordToValidate, boolean exactMatch){
		Collection<JsonPostalCodesRecord> outputList = new ArrayList<JsonPostalCodesRecord>();
		//prepare the access CGI with RPG back-end
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_POSTAL_CODES_URL;
		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchPostalCodes(applicationUser, recordToValidate, exactMatch);
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    
		if(jsonPayload!=null){
			JsonPostalCodesContainer container = this.transportDispChildWindowService.getPostalCodesContainer(jsonPayload);
    			if(container!=null){
    				outputList = container.getPostnrlist();
    				for(JsonPostalCodesRecord  record : outputList){
    					//DEBUG --> logger.info("XXXXXXXXXX:" + record.getViapnr());
    				}
    			}
		}	
		return outputList;
	}
	
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param searchFilterRecord
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchPostalCodes(String applicationUser, JsonPostalCodesRecord searchFilterRecord, boolean exactMatch){
		final String POSTALCODE_DIRECTION_FRA = "fra";
		final String POSTALCODE_DIRECTION_TIL = "til";
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + applicationUser);
		if(POSTALCODE_DIRECTION_FRA.equals(searchFilterRecord.getDirection())){
			urlRequestParamsKeys.append("&varlk=fralk");
			urlRequestParamsKeys.append("&varkod=fra");
		}else if(POSTALCODE_DIRECTION_TIL.equals(searchFilterRecord.getDirection())){
			urlRequestParamsKeys.append("&varlk=tillk");
			urlRequestParamsKeys.append("&varkod=til");
		}
		
		if(searchFilterRecord.getSt2lk()!=null && !"".equals(searchFilterRecord.getSt2lk())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soklk=" + searchFilterRecord.getSt2lk());
		}
		if(searchFilterRecord.getSt2nvn()!=null && !"".equals(searchFilterRecord.getSt2nvn())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soknvn=" + searchFilterRecord.getSt2nvn());
		}
		if(searchFilterRecord.getWskunpa()!=null && !"".equals(searchFilterRecord.getWskunpa())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wskunpa=" + searchFilterRecord.getWskunpa());
		}
		if(searchFilterRecord.getSt2kod()!=null && !"".equals(searchFilterRecord.getSt2kod())){
			//urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wst2kod=" + searchFilterRecord.getSt2kod());
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sokkod=" + searchFilterRecord.getSt2kod());
		}
		
		if(exactMatch){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "getval=J");
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "getavd=J");
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * Gets the trip header information
	 * @param appapplicationUser
	 * @param avdNr
	 * @param tripNr
	 * @return
	 */
	public JsonTransportDispWorkflowSpecificTripContainer fetchTripHeading(String applicationUser, String avdNr, String tripNr){
		JsonTransportDispWorkflowSpecificTripContainer container = null;
		//Now get the tur-header data for coming requirements, e.g. economy matrix.
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_URL;
	 	StringBuffer urlRequestParamsKeys = new StringBuffer();
	 	urlRequestParamsKeys.append("user=" + applicationUser);
	 	if(avdNr!=null){ urlRequestParamsKeys.append("&tuavd=" + avdNr); }
	 	if(tripNr!=null){ urlRequestParamsKeys.append("&tupro=" + tripNr); }
	 	
	 	logger.info("URL: " + BASE_URL);
	 	logger.info("PARAMS: " + urlRequestParamsKeys);
	 	logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	 	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
	 	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	 	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	 	if(jsonPayload!=null){
		 	try{
		 		container = this.transportDispWorkflowSpecificTripService.getContainer(jsonPayload);
		 	}catch(Exception e){
		 		
		 	}
	 	}
	 	return container;
	}
}
