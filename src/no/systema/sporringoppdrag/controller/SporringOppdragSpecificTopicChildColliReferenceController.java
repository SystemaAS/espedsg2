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
import no.systema.sporringoppdrag.model.jsonjackson.topic.colli.JsonSporringOppdragSpecificTopicSingleChildColliContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.colli.JsonSporringOppdragSpecificTopicSingleChildColliRecord;

import no.systema.sporringoppdrag.service.SporringOppdragSpecificTopicService;
import no.systema.sporringoppdrag.url.store.SporringOppdragUrlDataStore;

/**
 * Sporring Oppdrag - show specific colli-ref Controller 
 * 
 * @author oscardelatorre
 * @date Feb 25, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SporringOppdragSpecificTopicChildColliReferenceController {
	
	private static final Logger logger = Logger.getLogger(SporringOppdragSpecificTopicChildColliReferenceController.class.getName());
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
	@RequestMapping(value="sporringoppdrag_show_collireference", params="action=doShow",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doShow(@ModelAttribute ("record") SearchFilterSporringOppdragSpecificTopic recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("sporringoppdrag_show_collireference");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//--------------
			//Search filter 
			//--------------
			SearchFilterSporringOppdragSpecificTopic searchFilter = new SearchFilterSporringOppdragSpecificTopic();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
            //binder.registerCustomEditor(...); // if needed
            binder.bind(request);
            
	          //get BASE URL
	    		final String BASE_URL = SporringOppdragUrlDataStore.SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_SINGLE_CHILD_COLLIID_URL;
	    		//add URL-parameters
	    		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
	    		session.setAttribute(SporringOppdragConstants.ACTIVE_URL_RPG_SPORRING_OPPDRAG, BASE_URL + "==>params: " + urlRequestParams.toString()); 
	    		
		    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		    	
	        this.jsonDebugger.debugJsonPayload(jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			
		    	if(jsonPayload!=null){
		    		JsonSporringOppdragSpecificTopicSingleChildColliContainer singleChildColliContainer = this.sporringOppdragSpecificTopicService.getSporringOppdragSpecificTopicSingleChildColliContainer(jsonPayload);
		    		//singleChildInvoiceContainer.setKnavn(searchFilter.getKnavn());
		    		//------------------------
				//populate gui child list
				//------------------------
		    		//set domain objects now
		    		this.setDomainObjectsInView(session,model,singleChildColliContainer);	
		    		
				//--------------------------------------
				//Final successView with domain objects
				//--------------------------------------
				//drop downs
				successView.addObject(SporringOppdragConstants.DOMAIN_MODEL , model);
		    		//domain and search filter
				successView.addObject("searchFilter", searchFilter);
				logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
		    	
				return successView;
				
		    	}else{
				logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}
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
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + searchFilter.getHeavd());
		}
		if(searchFilter.getHeopd()!=null && !"".equals(searchFilter.getHeopd())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + searchFilter.getHeopd());
		}
		
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
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSporringOppdragSpecificTopicSingleChildColliContainer container){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSporringOppdragSpecificTopicSingleChildColliRecord record : container.getDspcolli()){
			model.put(SporringOppdragConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			//session.setAttribute(SporringOppdragConstants.DOMAIN_RECORD_TOPIC_SPORRING_OPPDRAG, record);
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

