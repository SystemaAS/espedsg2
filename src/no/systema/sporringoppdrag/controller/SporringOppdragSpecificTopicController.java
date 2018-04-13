package no.systema.sporringoppdrag.controller;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;


//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.main.model.SystemaWebUser;
//
import no.systema.sporringoppdrag.util.SporringOppdragConstants;
import no.systema.sporringoppdrag.filter.SearchFilterSporringOppdragSpecificTopic;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicRecord;
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


import no.systema.sporringoppdrag.service.SporringOppdragSpecificTopicService;
import no.systema.sporringoppdrag.url.store.SporringOppdragUrlDataStore;

/**
 * Sporring Oppdrag - show specific oppdrag Controller 
 * 
 * @author oscardelatorre
 * @date Jan 30, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SporringOppdragSpecificTopicController {
	
	private static final Logger logger = Logger.getLogger(SporringOppdragSpecificTopicController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="sporringoppdrag_show", params="action=doShow",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doShow(@ModelAttribute ("record") SearchFilterSporringOppdragSpecificTopic recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("sporringoppdrag_show");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		String knavn = request.getParameter("knavn");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SPORRING_OPPDRAG);
			//TODO session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE);
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//-----------
			//Validation
			//-----------
			/* TODO
			SadImportListValidator validator = new SadImportListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] search-filter does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//drop downs
		    		this.setCodeDropDownMgr(appUser, model);
				successView.addObject(SporringOppdragConstants.DOMAIN_MODEL, model);
		    		//successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
				//----------------------------------------------
				//get Search Filter and populate (bind) it here
				//----------------------------------------------
		    		SearchFilterSporringOppdragSpecificTopic searchFilter = new SearchFilterSporringOppdragSpecificTopic();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
	            //binder.registerCustomEditor(...); // if needed
	            binder.bind(request);
	            
	            //get BASE URL
		    		final String BASE_URL = SporringOppdragUrlDataStore.SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_URL;
		    		//add URL-parameters
		    		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
		    		session.setAttribute(SporringOppdragConstants.ACTIVE_URL_RPG_SPORRING_OPPDRAG, BASE_URL + "==>params: " + urlRequestParams.toString()); 
		    		
			    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			    	logger.info("URL: " + BASE_URL);
			    	logger.info("URL PARAMS: " + urlRequestParams);
			    	
			    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				//Debug --> 
			    	this.jsonDebugger.debugJsonPayload(jsonPayload);
			    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");

			    	if(jsonPayload!=null){
			    		JsonSporringOppdragSpecificTopicContainer jsonSporringOppdragSpecificTopicContainer = this.sporringOppdragSpecificTopicService.getSporringOppdragSpecificTopicContainer(jsonPayload);
			    		jsonSporringOppdragSpecificTopicContainer.setKnavn(knavn);
			    		//------------------------
					//populate gui child list
					//------------------------
			    		this.populateChildDocumentsFromJsonString(model, appUser, jsonSporringOppdragSpecificTopicContainer);
					this.populateChildFreetextFromJsonString(model, appUser, jsonSporringOppdragSpecificTopicContainer);
					this.populateChildInvoiceFromJsonString(model, appUser, jsonSporringOppdragSpecificTopicContainer);
					this.populateChildFriesokeVeierFromJsonString(model, appUser, jsonSporringOppdragSpecificTopicContainer);
					this.populateChildHendelseslogFromJsonString(model, appUser, jsonSporringOppdragSpecificTopicContainer);
					//set domain objects now
			    		this.setDomainObjectsInView(session,model,jsonSporringOppdragSpecificTopicContainer);	
			    		
					//--------------------------------------
					//Final successView with domain objects
					//--------------------------------------
					//drop downs
					//this.setCodeDropDownMgr(appUser, model);
					
					successView.addObject(SporringOppdragConstants.DOMAIN_MODEL , model);
			    		//domain and search filter
					//successView.addObject("searchFilter", searchFilter);
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
			    	
					return successView;
					
			    	}else{
					logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}
		    }
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="sporringoppdrag_renderArchive.do", method={ RequestMethod.GET })
	public ModelAndView doSporringOppdragRenderArchive(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside sporringoppdrag_renderArchive...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			//session.setAttribute(SporringOppdragConstants.ACTIVE_URL_RPG, SporringOppdragConstants.ACTIVE_URL_RPG_INITVALUE); 
			String filePath = request.getParameter("fp");
			if(filePath!=null && !"".equals(filePath)){
				String absoluteFilePath = filePath;
				if(absoluteFilePath!=null && absoluteFilePath.contains("http")){
					//nothing
				}else{
					absoluteFilePath = AppConstants.HTTP_ROOT_CGI + absoluteFilePath;
				}
                
                //must know the file type in order to put the correct content type on the Servlet response.
                String fileType = this.payloadContentFlusher.getFileType(filePath);
                if(AppConstants.DOCUMENTTYPE_PDF.equals(fileType)){
                		response.setContentType(AppConstants.HTML_CONTENTTYPE_PDF);
                }else if(AppConstants.DOCUMENTTYPE_TIFF.equals(fileType) || AppConstants.DOCUMENTTYPE_TIF.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TIFF);
                }else if(AppConstants.DOCUMENTTYPE_JPEG.equals(fileType) || AppConstants.DOCUMENTTYPE_JPG.equals(fileType)){
                		response.setContentType(AppConstants.HTML_CONTENTTYPE_JPEG);
                }else if(AppConstants.DOCUMENTTYPE_TXT.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }else if(AppConstants.DOCUMENTTYPE_DOC.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_WORD);
                }else if(AppConstants.DOCUMENTTYPE_XLS.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_EXCEL);
                }
                //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
                response.setHeader ("Content-disposition", "filename=\"archiveDocument." + fileType + "\"");
                
                logger.info("Start flushing file payload...");
                //send the file output to the ServletOutputStream
                try{
                		this.payloadContentFlusher.flushServletOutput(response, absoluteFilePath);
                		//payloadContentFlusher.flushServletOutput(response, "plain text test...".getBytes());
                	
                }catch (Exception e){
                		e.printStackTrace();
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}	

	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param container
	 */
	private void populateChildDocumentsFromJsonString(Map model, SystemaWebUser appUser, JsonSporringOppdragSpecificTopicContainer container){
		//fill in html lists here
		try{
			String BASE_URL = SporringOppdragUrlDataStore.SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_ARCHIVED_DOCUMENTS_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + container.getWsavd());
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + container.getWsopd());
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonSporringOppdragSpecificTopicChildDocumentContainer docContainer = this.sporringOppdragSpecificTopicService.getSporringOppdragSpecificTopicChildDocumentContainer(url);
			List<JsonSporringOppdragSpecificTopicChildDocumentRecord> list = new ArrayList();
			for(JsonSporringOppdragSpecificTopicChildDocumentRecord record: docContainer.getGetdoc()){
				list.add(record);
			}
			model.put(SporringOppdragConstants.RESOURCE_MODEL_KEY_CHILD_DOCUMENT_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param container
	 */
	private void populateChildFreetextFromJsonString(Map model, SystemaWebUser appUser, JsonSporringOppdragSpecificTopicContainer container){
		//fill in html lists here
		try{
			String BASE_URL = SporringOppdragUrlDataStore.SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_FREETEXT_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + container.getWsavd());
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + container.getWsopd());
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonSporringOppdragSpecificTopicChildFreetextContainer freeTextContainer = this.sporringOppdragSpecificTopicService.getSporringOppdragSpecificTopicChildFreetextContainer(url);
			List<JsonSporringOppdragSpecificTopicChildFreetextRecord> list = new ArrayList();
			for(JsonSporringOppdragSpecificTopicChildFreetextRecord record: freeTextContainer.getFreetextlistA()){
				list.add(record);
			}
			model.put(SporringOppdragConstants.RESOURCE_MODEL_KEY_CHILD_FREETEXT_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param container
	 */
	private void populateChildInvoiceFromJsonString(Map model, SystemaWebUser appUser, JsonSporringOppdragSpecificTopicContainer container){
		//fill in html lists here
		try{
			String BASE_URL = SporringOppdragUrlDataStore.SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_INVOICES_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + container.getWsavd());
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + container.getWsopd());
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			this.jsonDebugger.debugJsonPayload(url,50);
			JsonSporringOppdragSpecificTopicChildInvoiceContainer invoiceContainer = this.sporringOppdragSpecificTopicService.getSporringOppdragSpecificTopicChildInvoiceContainer(url);
			List<JsonSporringOppdragSpecificTopicChildInvoiceRecord> list = new ArrayList();
			for(JsonSporringOppdragSpecificTopicChildInvoiceRecord record: invoiceContainer.getGetfak()){
				list.add(record);
			}
			model.put(SporringOppdragConstants.RESOURCE_MODEL_KEY_CHILD_INVOICE_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param container
	 */
	private void populateChildFriesokeVeierFromJsonString(Map model, SystemaWebUser appUser, JsonSporringOppdragSpecificTopicContainer container){
		//fill in html lists here
		try{
			String BASE_URL = SporringOppdragUrlDataStore.SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_FRIE_SOKE_VEIER_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + container.getWsavd());
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + container.getWsopd());
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			this.jsonDebugger.debugJsonPayload(url);
			JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer frisokeVeierContainer = this.sporringOppdragSpecificTopicService.getSporringOppdragSpecificTopicChildFriesokeVeierContainer(url);
			List<JsonSporringOppdragSpecificTopicChildFriesokeVeierRecord> list = new ArrayList();
			for(JsonSporringOppdragSpecificTopicChildFriesokeVeierRecord record: frisokeVeierContainer.getGetfriesokeveier()){
				list.add(record);
			}
			model.put(SporringOppdragConstants.RESOURCE_MODEL_KEY_CHILD_FRIE_SOKE_VEIER_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param container
	 */
	private void populateChildHendelseslogFromJsonString(Map model, SystemaWebUser appUser, JsonSporringOppdragSpecificTopicContainer container){
		//fill in html lists here
		try{
			String BASE_URL = SporringOppdragUrlDataStore.SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_HENDELSESLOG_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + container.getWsavd());
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + container.getWsopd());
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			this.jsonDebugger.debugJsonPayload(url);
			JsonSporringOppdragSpecificTopicChildHendelseslogContainer hendelsesContainer = this.sporringOppdragSpecificTopicService.getSporringOppdragSpecificTopicChildHendelseslogContainer(url);
			List<JsonSporringOppdragSpecificTopicChildHendelseslogRecord> list = new ArrayList();
			for(JsonSporringOppdragSpecificTopicChildHendelseslogRecord record: hendelsesContainer.getGettrackandtrace()){
				list.add(record);
			}
			model.put(SporringOppdragConstants.RESOURCE_MODEL_KEY_CHILD_HENDELSESLOG_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SearchFilterSporringOppdragSpecificTopic searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		
		if(searchFilter.getHeavd()!=null && !"".equals(searchFilter.getHeavd())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "heavd=" + searchFilter.getHeavd());
		}
		if(searchFilter.getHeopd()!=null && !"".equals(searchFilter.getHeopd())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "heopd=" + searchFilter.getHeopd());
		}
		/*
		if(searchFilter.getSg()!=null && !"".equals(searchFilter.getSg())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sg=" + searchFilter.getSg());
		}
		if(searchFilter.getSitll()!=null && !"".equals(searchFilter.getSitll())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lopenr=" + searchFilter.getSitll());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + this.dateFormatter.convertToDate_ISO(searchFilter.getDatum()));
		}
		if(searchFilter.getStatus()!=null && !"".equals(searchFilter.getStatus())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "status=" + searchFilter.getStatus());
		}
		if(searchFilter.getAvsNavn()!=null && !"".equals(searchFilter.getAvsNavn())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avsNavn=" + searchFilter.getAvsNavn());
		}
		if(searchFilter.getMotNavn()!=null && !"".equals(searchFilter.getMotNavn())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "motNavn=" + searchFilter.getMotNavn());
		}
		if(searchFilter.getGodsnr()!=null && !"".equals(searchFilter.getGodsnr())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "godsnr=" + searchFilter.getGodsnr());
		}
		if(searchFilter.getInnstikk()!=null && !"".equals(searchFilter.getInnstikk())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "innstikk=" + searchFilter.getInnstikk());
		}
		*/
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		/* TODO COVI Status
		 * 
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		*/
	}
	/**
	 * 
	 * @param session
	 * @param model
	 * @param container
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSporringOppdragSpecificTopicContainer container){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSporringOppdragSpecificTopicRecord record : container.getDspoppdrag()){
			model.put(SporringOppdragConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(SporringOppdragConstants.DOMAIN_RECORD_TOPIC_SPORRING_OPPDRAG, record);
		}
		model.put(SporringOppdragConstants.DOMAIN_CONTAINER, container);
		
	}

	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("sporringOppdragSpecificTopicService")
	private SporringOppdragSpecificTopicService sporringOppdragSpecificTopicService;
	@Autowired
	@Required
	public void setSporringOppdragSpecificTopicService (SporringOppdragSpecificTopicService value){ this.sporringOppdragSpecificTopicService = value; }
	public SporringOppdragSpecificTopicService getSporringOppdragSpecificTopicService(){ return this.sporringOppdragSpecificTopicService; }
	
	
	
	
}

