package no.systema.transportdisp.controller;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Level;
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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;


//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.main.util.io.FileContentRenderer;

import no.systema.main.model.SystemaWebUser;
//TRANSPDISP
import no.systema.transportdisp.service.TransportDispWorkflowListService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListGodsAndLastListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripRecord;
import no.systema.transportdisp.filter.SearchFilterTransportDispWorkflowTripList;
import no.systema.transportdisp.validator.TransportDispWorkflowTripListValidator;

import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.RpgReturnResponseHandler;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.util.manager.CodeDropDownMgr;


/**
 * TransportDisp Controller 
 * 
 * @author oscardelatorre
 * @date Jan 13, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TransportDispWorkflowTripListController {
	private static Logger logger = Logger.getLogger(TransportDispWorkflowTripListController.class.getName());
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1000);
	private DateTimeManager dateTimeManager = new DateTimeManager();
	
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterTransportDispWorkflowTripList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Collection<JsonTransportDispWorkflowListRecord> outputList = new ArrayList<JsonTransportDispWorkflowListRecord>();
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//-----------
			//Validation
			//-----------
			TransportDispWorkflowTripListValidator validator = new TransportDispWorkflowTripListValidator();
			validator.validate(recordToValidate, bindingResult);
		
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//drop downs
	    		this.setDropDownsFromFiles(model);
	    		this.setCodeDropDownMgr(appUser, model);
	    		successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				successView.addObject(TransportDispConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
				
	            //get BASE URL
	    		final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_LIST_URL;
	    		//add URL-parameters
	    		String urlRequestParams = this.getRequestUrlKeyParameters(recordToValidate, appUser, request);
	    		
	    		session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
		    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

				//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonTransportDispWorkflowListContainer jsonTransportDispWorkflowListContainer = this.transportDispWorkflowListService.getWorkflowListContainer(jsonPayload);
		    		//logger.info(jsonTransportDispWorkflowListContainer.getErrMsg());
		    		//-----------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//-----------------------------------------------------------
					outputList = jsonTransportDispWorkflowListContainer.getWrktriplist();
					if(jsonTransportDispWorkflowListContainer.getErrMsg()!=null && !"".equals(jsonTransportDispWorkflowListContainer.getErrMsg())){
						model.put(TransportDispConstants.ASPECT_ERROR_MESSAGE, jsonTransportDispWorkflowListContainer.getErrMsg());
						logger.info("ERROR model:" + (String)model.get(TransportDispConstants.ASPECT_ERROR_MESSAGE));
					}//Put list for upcomming view (PDF, Excel, etc)
					session.setAttribute(session.getId() + TransportDispConstants.SESSION_LIST, outputList);
					
					//--------------------------------------
					//Final successView with domain objects
					//--------------------------------------
					//drop downs
					this.setDropDownsFromFiles(model);
					this.setCodeDropDownMgr(appUser, model);
					
					//Default values (always when doFind)
					JsonTransportDispWorkflowSpecificTripRecord record = new JsonTransportDispWorkflowSpecificTripRecord();
					record.setCenturyYearTurccTuraar(this.dateTimeManager.getCurrentYear());
					record.setTurmnd(this.dateTimeManager.getCurrentMonth());
					//default values
					record.setTudt(this.dateTimeManager.getCurrentDate_ISO());
					
			    	model.put(TransportDispConstants.DOMAIN_RECORD, record);
			    	model.put(TransportDispConstants.DOMAIN_CONTAINER_TRIP_LIST, jsonTransportDispWorkflowListContainer);
					
					successView.addObject("searchFilter", recordToValidate);
					successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
		    		//domain and search filter
					successView.addObject(TransportDispConstants.DOMAIN_LIST,outputList);
					//successView.addObject("searchFilter", searchFilter);
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
					//Put list for upcomming view (PDF, Excel, etc)
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
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_closeOpenTrip.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doCloseTrip(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		
		String avd = request.getParameter("tuavd");
		String tur = request.getParameter("tupro");
		String status = request.getParameter("tust");
		StringBuffer sb = new StringBuffer();
		ModelAndView successView = null;
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			
			if(avd!=null && !"".equals(avd)){ sb.append("&wssavd=" + avd); }
			successView = new ModelAndView("redirect:transportdisp_workflow.do?action=doFind&user=" + appUser.getUser() + sb.toString());
			
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TRANSPORT_DISP);
			//TODO session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
				
            //get BASE URL
	    		final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_ClOSE_SPECIFIC_TRIP_URL;
	    		//add URL-parameters
	    		String urlRequestParams = this.getRequestUrlKeyParameters(avd, tur, status, appUser, request);
	    		//session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
		    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
				 
		    	//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	if(rpgReturnPayload!=null){
		    		rpgReturnResponseHandler.evaluateRpgResponseOnTripUpdate(rpgReturnPayload);
		    		if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    			rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
		    			logger.info(rpgReturnResponseHandler.getErrorMessage());
		    		}	
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    		return successView;
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
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
	@RequestMapping(value="transportdisp_workflow_renderGodsOrLastlist.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doRenderGodsOrLastList(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		this.context = TdsAppContext.getApplicationContext();
		String tur = request.getParameter("tupro");
		String listType = request.getParameter("type");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//get BASE URL /default Godslista
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_PRINT_SPECIFIC_TRIP_GODSLISTA_URL;
    			if(listType!=null){
    				if("G".equalsIgnoreCase(listType)){
    					BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_PRINT_SPECIFIC_TRIP_GODSLISTA_URL;
    				}else if ("L".equalsIgnoreCase(listType)){
    					BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_PRINT_SPECIFIC_TRIP_LASTLISTA_URL;
    				}
    			}
	    		//add URL-parameters
	    		String urlRequestParams = this.getRequestUrlKeyParameters(tur, appUser);
	    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		    	//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	if(jsonPayload!=null){
		    		JsonTransportDispWorkflowListGodsAndLastListContainer jsonGodsAndLastListContainer = this.transportDispWorkflowListService.getWorkflowGodsAndListContainer(jsonPayload);
		    		if(jsonGodsAndLastListContainer!=null && jsonGodsAndLastListContainer.getIfsfil()!=null){
		    			String absoluteFilePath = jsonGodsAndLastListContainer.getIfsfil();
		    			try{
		    				new FileContentRenderer().renderContent(response, absoluteFilePath);
		    			}catch(Exception e){
		    				e.printStackTrace();
		    			}
		    		}
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
			}
		    	return(null);
		}
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_renderArchivedDocs.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doRenderArchivedDocs(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("method: doRenderArchivedDocs");
		this.context = TdsAppContext.getApplicationContext();
		String doclnk = request.getParameter("doclnk");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//get BASE URL /default Godslista
    			String absoluteFilePath = doclnk;
    			try{
    				new FileContentRenderer().renderContent(response, absoluteFilePath);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
		    	return(null);
		}
	}
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	
	private String getRequestUrlKeyParameters(SearchFilterTransportDispWorkflowTripList searchFilter, SystemaWebUser appUser, HttpServletRequest request){
		
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		if(!"".equals(searchFilter.getWssst()) && searchFilter.getWssst()!=null){urlRequestParams.append("&wssst=" + searchFilter.getWssst()); }
		if(!"".equals(searchFilter.getWssavd()) && searchFilter.getWssavd()!=null){urlRequestParams.append("&wssavd=" + searchFilter.getWssavd()); }
		if(!"".equals(searchFilter.getWsstur()) && searchFilter.getWsstur()!=null){urlRequestParams.append("&wsstur=" + searchFilter.getWsstur()); }
		
		if(!"".equals(searchFilter.getWtusg()) && searchFilter.getWtusg()!=null){urlRequestParams.append("&wtusg=" + searchFilter.getWtusg()); }
		if(!"".equals(searchFilter.getWtubiln()) && searchFilter.getWtubiln()!=null){urlRequestParams.append("&wtubiln=" + searchFilter.getWtubiln()); }
		
		
		if(!"".equals(searchFilter.getWtustef()) && searchFilter.getWtustef()!=null){
			urlRequestParams.append("&wtustef=" + searchFilter.getWtustef()); 
		}
		if(!"".equals(searchFilter.getWtustet()) && searchFilter.getWtustet()!=null){
			urlRequestParams.append("&wtustet=" + searchFilter.getWtustet()); 
		}
		if(!"".equals(searchFilter.getWtudt()) && searchFilter.getWtudt()!=null){
			String isoDate = searchFilter.getWtudt().replaceAll("\\.", "");
			urlRequestParams.append("&wtudt=" + isoDate); 
		}
		if(!"".equals(searchFilter.getWtudt2()) && searchFilter.getWtudt2()!=null){
			String isoDate = searchFilter.getWtudt2().replaceAll("\\.", "");
			urlRequestParams.append("&wtudt2=" + isoDate); 
		}
		if(!"".equals(searchFilter.getWtudtt()) && searchFilter.getWtudtt()!=null){
			String isoDate = searchFilter.getWtudtt().replaceAll("\\.", "");
			urlRequestParams.append("&wtudtt=" + isoDate); 
		}
		if(!"".equals(searchFilter.getWtudtt2()) && searchFilter.getWtudtt2()!=null){
			String isoDate = searchFilter.getWtudtt2().replaceAll("\\.", "");
			urlRequestParams.append("&wtudtt2=" + isoDate); 
		}
		
		return urlRequestParams.toString();
	}
	
	/**
	 * 
	 * @param avd
	 * @param tur
	 * @param appUser
	 * @param request
	 * @return
	 */
	private String getRequestUrlKeyParameters(String avd, String tur, String offset, SystemaWebUser appUser, HttpServletRequest request){
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		if(!"".equals(avd) && avd!=null){urlRequestParams.append("&tuavd=" + avd); }
		if(!"".equals(tur) && tur!=null){urlRequestParams.append("&tupro=" + tur); }
		if(offset!=null){
			urlRequestParams.append("&tust=" + offset);
		}
		return urlRequestParams.toString();
	}
	
	/**
	 * Render Gods- and Last lists
	 * @param tur
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(String tur, SystemaWebUser appUser){
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&wstur=" + tur);
		
		return urlRequestParams.toString();
	}
	
	/**
	 * Populates dropDowns from local file resources
	 * @param model
	 */
	private void setDropDownsFromFiles(Map<String, Object> model){
		model.put(TransportDispConstants.RESOURCE_MODEL_KEY_YEAR_LIST, this.transportDispDropDownListPopulationService.getYearList());
		model.put(TransportDispConstants.RESOURCE_MODEL_KEY_MONTH_LIST, this.transportDispDropDownListPopulationService.getMonthList());
		model.put(TransportDispConstants.RESOURCE_MODEL_KEY_CURRENCY_CODE_LIST, this.transportDispDropDownListPopulationService.getCurrencyList());
		
	}
	
	/**
	 * Population of codes (GUI drop-downs)
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.transportDispDropDownListPopulationService,
		model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		//oppdragtype
		this.codeDropDownMgr.populateHtmlDropDownsFromJsonStringOppdragsType(this.urlCgiProxyService, this.transportDispDropDownListPopulationService, model, appUser, null);
	}

	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("transportDispWorkflowListService")
	private TransportDispWorkflowListService transportDispWorkflowListService;
	@Autowired
	@Required
	public void setTransportDispWorkflowListService (TransportDispWorkflowListService value){ this.transportDispWorkflowListService = value; }
	public TransportDispWorkflowListService getTransportDispWorkflowListService(){ return this.transportDispWorkflowListService; }
	
	
	@Qualifier ("transportDispWorkflowSpecificTripService")
	private TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService;
	@Autowired
	public void setTransportDispWorkflowSpecificTripService (TransportDispWorkflowSpecificTripService value){ this.transportDispWorkflowSpecificTripService=value; }
	public TransportDispWorkflowSpecificTripService getTransportDispWorkflowSpecificTripService(){return this.transportDispWorkflowSpecificTripService;}
	
	
	@Qualifier ("transportDispDropDownListPopulationService")
	private TransportDispDropDownListPopulationService transportDispDropDownListPopulationService;
	@Autowired
	public void setTransportDispDropDownListPopulationService (TransportDispDropDownListPopulationService value){ this.transportDispDropDownListPopulationService=value; }
	public TransportDispDropDownListPopulationService getTransportDispDropDownListPopulationService(){return this.transportDispDropDownListPopulationService;}
	
	
}

