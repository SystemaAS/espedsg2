	/**
 * 
 */
package no.systema.transportdisp.util.manager;

import java.util.*;

import org.apache.log4j.Logger;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;

//import no.systema.tvinn.sad.model.external.url.UrlTvinnSadTolltariffenObject;
import no.systema.transportdisp.model.jsonjackson.workflow.codes.JsonTransportDispCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.codes.JsonTransportDispCodeRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.frankatur.JsonTransportDispFrankaturContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.frankatur.JsonTransportDispFrankaturRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispAvdContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispAvdRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.avdsignature.JsonTransportDispAvdGroupsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.avdsignature.JsonTransportDispAvdGroupsRecord;


import no.systema.transportdisp.util.manager.CodeDropDownMgr;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;
import no.systema.transportdisp.service.TransportDispChildWindowService;


/**
 * The class handles general gui drop downs aspect population for Work with Trips - Transport Disponering
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a controller when needed.
 * 
 * 
 * 
 * @author oscardelatorre
 * @date Maj 1, 2015
 * 
 * 	2=Landkoder                     
 * 
 */

public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	//
	public static final String CODE_2_COUNTRY = "2";
	
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param skatDropDownListPopulationService
	 * @param model
	 * @param appUser
	 * @param paramTYP
	 * @param paramKODE2
	 * @param paramKODE3
	 */
	public void populateCodesHtmlDropDownsFromJsonString(UrlCgiProxyService urlCgiProxyService, TransportDispDropDownListPopulationService listPopulationService,
														Map model, SystemaWebUser appUser, String paramTYP, String paramKODE2, String paramKODE3){
		//fill in html lists here
		try{
			
			String CODES_URL = TransportDispUrlDataStore.TRANSPORT_DISP_CODES_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "typ=" + paramTYP);
			if(paramKODE2 !=null){
				urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode2=" + paramKODE2);
			}
			
			//Now build the payload and send to the back end via the drop down service
			//logger.info("CODES_URL:" + CODES_URL);
			//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			//debug
			//logger.info(utfPayload);
			JsonTransportDispCodeContainer codeContainer = listPopulationService.getCodeContainer(utfPayload);
			List<JsonTransportDispCodeRecord> list = new ArrayList();
			
			//Take some exception into consideration here or run the default to populate the final list
			for(JsonTransportDispCodeRecord codeRecord: codeContainer.getKodlista()){
				//default
				list.add(codeRecord);
				//logger.info("CODE_RECORD: " + codeRecord.getZtxt());
			}
			
			if(this.CODE_2_COUNTRY.equalsIgnoreCase(paramTYP)){
				model.put(TransportDispConstants.RESOURCE_MODEL_KEY_COUNTRY_CODE_LIST,list);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param listPopulationService
	 * @param model
	 * @param appUser
	 * @param paramsMap
	 */
	public void populateHtmlDropDownsFromJsonStringFrankatur(UrlCgiProxyService urlCgiProxyService, TransportDispDropDownListPopulationService listPopulationService,
		Map model, SystemaWebUser appUser, Map paramsMap){
		//fill in html lists here
		try{
			String URL = TransportDispUrlDataStore.TRANSPORT_DISP_GENERAL_FRANKATUR_INCOTERMS_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			//optional parameters in case to be used
			if(paramsMap!=null){
				String franka = (String)paramsMap.get("franka");
				String beskr = (String)paramsMap.get("beskr");
				String getval = (String)paramsMap.get("getval");
				String fullinfo = (String)paramsMap.get("fullinfo");
				if(franka!=null && !"".equals(franka)){ urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "franka=" + franka); }
				if(beskr!=null && !"".equals(beskr)){ urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "beskr=" + beskr); }
				if(getval!=null && !"".equals(getval)){ urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "getval=" + getval); }
				if(fullinfo!=null && !"".equals(fullinfo)){ urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fullinfo=" + fullinfo); }
			}
			//Now build the payload and send to the back end via the drop down service
			//logger.info("URL:" + URL);
			String utfPayload = urlCgiProxyService.getJsonContent(URL, urlRequestParamsKeys.toString());
			//logger.info(utfPayload);
			JsonTransportDispFrankaturContainer frankaturContainer = listPopulationService.getFrankaturContainer(utfPayload);
			
			//Take some exception into consideration here or run the default to populate the final list
			for(JsonTransportDispFrankaturRecord record: frankaturContainer.getFrankaturer()){
				//logger.info("FRANKATUR RECORD: " + record.getFranka());
			}
			model.put(TransportDispConstants.RESOURCE_MODEL_KEY_INCOTERMS_LIST,frankaturContainer.getFrankaturer());
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}
	
	public void populateHtmlDropDownsFromJsonStringAvdGroups(UrlCgiProxyService urlCgiProxyService, TransportDispDropDownListPopulationService listPopulationService,
			Map model, SystemaWebUser appUser){
			//fill in html lists here
			try{
				String URL = TransportDispUrlDataStore.TRANSPORT_DISP_GENERAL_AVD_GROUPS_URL;
				StringBuffer urlRequestParamsKeys = new StringBuffer();
				urlRequestParamsKeys.append("user=" + appUser.getUser());
				String utfPayload = urlCgiProxyService.getJsonContent(URL, urlRequestParamsKeys.toString());
				logger.info(URL);
				logger.info(urlRequestParamsKeys.toString());
				//logger.info(utfPayload);
				utfPayload = utfPayload.replaceAll("aGrKode", "agrKode");
				utfPayload = utfPayload.replaceAll("aGrNavn", "agrNavn");
				logger.info(utfPayload);
				JsonTransportDispAvdGroupsContainer container = listPopulationService.getAvdGroupsContainer(utfPayload);
				
				if(container!=null && container.getInqAvdGrupp()!=null){
					for(JsonTransportDispAvdGroupsRecord record: container.getInqAvdGrupp()){
						logger.info("AVD-Groups: " + record.getAgrKode() + " " + record.getAgrNavn());
					}
					model.put(TransportDispConstants.RESOURCE_MODEL_KEY_AVD_GROUPS_LIST, container.getInqAvdGrupp());
				}else{
					model.put(TransportDispConstants.RESOURCE_MODEL_KEY_AVD_GROUPS_LIST, new ArrayList());
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
				
		}
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param listPopulationService
	 * @param model
	 * @param appUser
	 * @param paramsMap
	 */
	public void populateHtmlDropDownsFromJsonStringOppdragsType(UrlCgiProxyService urlCgiProxyService, TransportDispDropDownListPopulationService listPopulationService,
			Map model, SystemaWebUser appUser, Map paramsMap){
			//fill in html lists here
			try{
				String URL = TransportDispUrlDataStore.TRANSPORT_DISP_GENERAL_OPPDRAGSTYPE_URL;
				StringBuffer urlRequestParamsKeys = new StringBuffer();
				urlRequestParamsKeys.append("user=" + appUser.getUser());
				//optional parameters in case to be used
				if(paramsMap!=null){
					String opdtyp = (String)paramsMap.get("opdtyp");
					String beskr = (String)paramsMap.get("beskr");
					String getval = (String)paramsMap.get("getval");
					String fullinfo = (String)paramsMap.get("fullinfo");
					if(opdtyp!=null && !"".equals(opdtyp)){ urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opdtyp=" + opdtyp); }
					if(beskr!=null && !"".equals(beskr)){ urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "beskr=" + beskr); }
					if(getval!=null && !"".equals(getval)){ urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "getval=" + getval); }
					if(fullinfo!=null && !"".equals(fullinfo)){ urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fullinfo=" + fullinfo); }
				}
				//Now build the payload and send to the back end via the drop down service
				//logger.info("URL:" + URL);
				String utfPayload = urlCgiProxyService.getJsonContent(URL, urlRequestParamsKeys.toString());
				//logger.info(utfPayload);
				JsonTransportDispOppdragTypeContainer container = listPopulationService.getOppdragTypeContainer(utfPayload);
				
				//Take some exception into consideration here or run the default to populate the final list
				for(JsonTransportDispOppdragTypeRecord record: container.getOppdragsTyper()){
					//logger.info("FRANKATUR RECORD: " + record.getFranka());
				}
				model.put(TransportDispConstants.RESOURCE_MODEL_KEY_OPPDRAGSTYPE_LIST, container.getOppdragsTyper());
				
			}catch(Exception e){
				e.printStackTrace();
			}
				
		}	
		/**
		 * 
		 * @param urlCgiProxyService
		 * @param listPopulationService
		 * @param model
		 * @param appUser
		 * @param paramsMap
		 */
		public void populateHtmlDropDownsFromJsonStringGebyrCodes(UrlCgiProxyService urlCgiProxyService, TransportDispDropDownListPopulationService listPopulationService,
			Map model, SystemaWebUser appUser){
			//fill in html lists here
			try{
				String URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_GEBYR_CODES_URL;
				StringBuffer urlRequestParamsKeys = new StringBuffer();
				urlRequestParamsKeys.append("user=" + appUser.getUser() + "&fullinfo=N");
				
				//Now build the payload and send to the back end via the drop down service
				logger.info("URL:" + URL);
				logger.info("PARAMS:" + urlRequestParamsKeys.toString());
				
				String utfPayload = urlCgiProxyService.getJsonContent(URL, urlRequestParamsKeys.toString());
				//logger.info(utfPayload);
				JsonTransportDispGebyrCodeContainer container = listPopulationService.getGebyrCodeContainer(utfPayload);
				
				//Take some exception into consideration here or run the default to populate the final list
				for(JsonTransportDispGebyrCodeRecord record: container.getGebyrKoder()){
					//logger.info("GEBYR RECORD: " + record.getKgekod());
				}
				model.put(TransportDispConstants.RESOURCE_MODEL_KEY_GEBYRCODES_LIST, container.getGebyrKoder());
				
			}catch(Exception e){
				e.printStackTrace();
			}
				
		}	
		/**
		 * 
		 * @param urlCgiProxyService
		 * @param listPopulationService
		 * @param model
		 * @param appUser
		 */
		public void populateHtmlDropDownsFromJsonAvd(UrlCgiProxyService urlCgiProxyService, TransportDispChildWindowService listPopulationService,
				Map model, SystemaWebUser appUser){
				//fill in html lists here
				String DATATABLE_AVD_LIST = "avdList";
				try{
					String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_AVD_URL;
					String urlRequestParamsKeys = "user=" + appUser.getUser();
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	//logger.debug(jsonPayload);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    
		    		if(jsonPayload!=null){
			    		JsonTransportDispAvdContainer container = listPopulationService.getAvdContainer(jsonPayload);
			    		if(container!=null){
			    			model.put(DATATABLE_AVD_LIST, container.getAvdelningar());
			    		}
		    		}
				}catch(Exception e){
						e.printStackTrace();
				}		
			}
	
}
