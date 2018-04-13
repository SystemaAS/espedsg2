package no.systema.sporringoppdrag.controller;

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
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;


//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

//SPORRING
import no.systema.sporringoppdrag.util.SporringOppdragDateFormatter;
import no.systema.sporringoppdrag.url.store.SporringOppdragUrlDataStore;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragTopicListContainer;
import no.systema.sporringoppdrag.service.SporringOppdragTopicListService;
import no.systema.sporringoppdrag.util.SporringOppdragConstants;
import no.systema.sporringoppdrag.filter.SearchFilterSporringOppdragTopicList;
import no.systema.sporringoppdrag.validator.SporringOppdragMainListValidator;


/**
 * Sporring Oppdrag Controller 
 * 
 * @author oscardelatorre
 * @date Jan 29, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SporringOppdragMainListController {
	
	private static final Logger logger = Logger.getLogger(SporringOppdragMainListController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1000);
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private SporringOppdragDateFormatter dateFormatter = new SporringOppdragDateFormatter();
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="sporringoppdrag_mainlist", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterSporringOppdragTopicList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFind");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("sporringoppdrag_mainlist");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//SESSION FILTER (when applicable)
		//In some http calls we are required to use the session searchFilter if we have used the main list filter
		SearchFilterSporringOppdragTopicList sessionSearchFilter = null;
		String fs = request.getParameter("fs");
		if("1".equals(fs)){
			sessionSearchFilter = (SearchFilterSporringOppdragTopicList)session.getAttribute(SporringOppdragConstants.SESSION_SEARCH_FILTER);
		}
	
		//Start the controller's main flow
		if(appUser==null){
			return loginView;
			
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SPORRING_OPPDRAG);
			//TODO session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE);
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//-----------
			//Validation
			//-----------
			SporringOppdragMainListValidator validator = new SporringOppdragMainListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//drop downs
	    		this.setCodeDropDownMgr(appUser, model);
				successView.addObject(SporringOppdragConstants.DOMAIN_MODEL, model);
		    	
				successView.addObject(SporringOppdragConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
		    		
				//----------------------------------------------
				//get Search Filter and populate (bind) it here
				//----------------------------------------------
		    		SearchFilterSporringOppdragTopicList searchFilter = new SearchFilterSporringOppdragTopicList();
		    		if(sessionSearchFilter!=null){
		    			logger.info("session filter in use...");
		    			searchFilter = sessionSearchFilter;
		    		}else{
		    			logger.info("request filter in use...");
						ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            
			            
			            if(searchFilter.getWsdtf()==null || "".equals(searchFilter.getWsdtf())){
			            	//this (asChildwindowInit) will be active only in the first POST from a parent application. It will be remove in this same function below
			            	if("J".equals(appUser.getIntern())){ 
			            		searchFilter.setWsdtf(this.dateTimeMgr.getCurrentDate_ISO());			            		

			            	}else{
			            		int DEFAULT_NUMBER_OF_MONTHS_BACK = -1;
			            		searchFilter.setWsdtf(this.dateTimeMgr.getSpecificMonthFrom_CurrentDate_ISO(DEFAULT_NUMBER_OF_MONTHS_BACK));			            		
			            	}
			            }
			            //put the search filter in session in case we are wondering around in other tabs. This should always ensure
			            //the use of a the already populated filter, until the user makes his changes in the GUI.
			            session.setAttribute(SporringOppdragConstants.SESSION_SEARCH_FILTER, searchFilter);
		    		}
		    		
		    		//get BASE URL
		    		final String BASE_URL = SporringOppdragUrlDataStore.SPORRING_OPPDRAG_BASE_TOPICLIST_URL;
		    		//add URL-parameters
		    		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
		    		session.setAttribute(SporringOppdragConstants.ACTIVE_URL_RPG_SPORRING_OPPDRAG, BASE_URL + "==>params: " + urlRequestParams.toString()); 
			    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			    	logger.info("URL: " + BASE_URL);
			    	logger.info("URL PARAMS: " + urlRequestParams);
			    	
			    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			    	//Debug -->
			    	jsonDebugger.debugJsonPayload(jsonPayload);
			    	if(jsonPayload!=null && jsonPayload.length()>500){ logger.info(jsonPayload.substring(0,500));}
			    	else{logger.info(jsonPayload);}
			    	
			    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    	if(jsonPayload!=null){
			    		
			    		JsonSporringOppdragTopicListContainer jsonSporringOppdragTopicListContainer = this.sporringOppdragTopicListService.getSporringOppdragTopicListContainer(jsonPayload);
			    		
			    		//-----------------------------------------------------------
						//now filter the topic list with the search filter (if applicable)
						//-----------------------------------------------------------
			    		
			    		if(jsonSporringOppdragTopicListContainer!=null ){
			    			//default search (when applicable)
	    					outputList = jsonSporringOppdragTopicListContainer.getQryoppdrag();	
	    					this.setDomainObjectsInView(session, model, jsonSporringOppdragTopicListContainer);
			    		
			    		}	
						//--------------------------------------
						//Final successView with domain objects
						//--------------------------------------
			    		//drop downs
						this.setCodeDropDownMgr(appUser, model);
						
						successView.addObject(SporringOppdragConstants.DOMAIN_MODEL , model);
			    		//domain and search filter
						successView.addObject(SporringOppdragConstants.DOMAIN_LIST,outputList);
						successView.addObject("searchFilter", searchFilter);
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
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SearchFilterSporringOppdragTopicList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getWsavd()!=null && !"".equals(searchFilter.getWsavd())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsavd=" + searchFilter.getWsavd());
		}
		if(searchFilter.getWsopd()!=null && !"".equals(searchFilter.getWsopd())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsopd=" + searchFilter.getWsopd());
		}
		if(searchFilter.getWsdtf()!=null && !"".equals(searchFilter.getWsdtf())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsdtf=" + searchFilter.getWsdtf());
		}
		if(searchFilter.getWsdtt()!=null && !"".equals(searchFilter.getWsdtt())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsdtt=" + searchFilter.getWsdtt());
		}
		if(searchFilter.getWsrfa()!=null && !"".equals(searchFilter.getWsrfa())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsrfa=" + searchFilter.getWsrfa());
		}
		if(searchFilter.getWsrfk()!=null && !"".equals(searchFilter.getWsrfk())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsrfk=" + searchFilter.getWsrfk());
		}
		if(searchFilter.getWsfn()!=null && !"".equals(searchFilter.getWsfn())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsfn=" + searchFilter.getWsfn());
		}
		if(searchFilter.getWsgn()!=null && !"".equals(searchFilter.getWsgn())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsgn=" + searchFilter.getWsgn());
		}
		if(searchFilter.getWsawbn()!=null && !"".equals(searchFilter.getWsawbn())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsawbn=" + searchFilter.getWsawbn());
		}
		if(searchFilter.getWshawb()!=null && !"".equals(searchFilter.getWshawb())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wshawb=" + searchFilter.getWshawb());
		}
		if(searchFilter.getWsdthawb()!=null && !"".equals(searchFilter.getWsdthawb())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsdthawb=" + searchFilter.getWsdthawb());
		}
		if(searchFilter.getWsot()!=null && !"".equals(searchFilter.getWsot())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsot=" + searchFilter.getWsot());
		}
		if(searchFilter.getWsdtot()!=null && !"".equals(searchFilter.getWsdtot())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsdtot=" + searchFilter.getWsdtot());
		}
		if(searchFilter.getWsmrk1()!=null && !"".equals(searchFilter.getWsmrk1())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsmrk1=" + searchFilter.getWsmrk1());
		}
		if(searchFilter.getFscd()!=null && !"".equals(searchFilter.getFscd())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fscd=" + searchFilter.getFscd());
		}
		if(searchFilter.getWsfri2()!=null && !"".equals(searchFilter.getWsfri2())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsfri2=" + searchFilter.getWsfri2());
		}
		//Special treatment for Fraktbrevsnr (earlier handled with fscd and wsfri2 above). We have put this separated to make it more specific to the end-user
		if(searchFilter.getOwnFraktbrevsNr()!=null && !"".equals(searchFilter.getOwnFraktbrevsNr())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fscd=IFB" + searchFilter.getFscd());
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsfri2=" + searchFilter.getOwnFraktbrevsNr());
		}
		
		if(searchFilter.getWsdtfs()!=null && !"".equals(searchFilter.getWsdtfs())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsdtfs=" + searchFilter.getWsdtfs());
		}
		if(searchFilter.getWsblnr()!=null && !"".equals(searchFilter.getWsblnr())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsblnr=" + searchFilter.getWsblnr());
		}
		if(searchFilter.getWsblcn()!=null && !"".equals(searchFilter.getWsblcn())){
			urlRequestParamsKeys.append(SporringOppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsblcn=" + searchFilter.getWsblcn());
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
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSporringOppdragTopicListContainer container){
		//SET HEADER RECORDS  (from RPG)
		/*
		for (JsonSporringOppdragTopicListRecord record : container.getQryoppdrag()){
			model.put(SporringOppdragConstants.DOMAIN_RECORD, record);
		}*/
		
		model.put(SporringOppdragConstants.DOMAIN_CONTAINER, container);
		//Put list for upcomming view (PDF, Excel, etc)
		if(container.getQryoppdrag()!=null){
			session.setAttribute(session.getId() + SporringOppdragConstants.SESSION_LIST, container.getQryoppdrag());
		}
	}

	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("sporringOppdragTopicListService")
	private SporringOppdragTopicListService sporringOppdragTopicListService;
	@Autowired
	@Required
	public void setSporringOppdragTopicListService (SporringOppdragTopicListService value){ this.sporringOppdragTopicListService = value; }
	public SporringOppdragTopicListService getSporringOppdragTopicListService(){ return this.sporringOppdragTopicListService; }
	
	
	/*
	@Qualifier ("tvinnSadDropDownListPopulationService")
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	*/
	
}

