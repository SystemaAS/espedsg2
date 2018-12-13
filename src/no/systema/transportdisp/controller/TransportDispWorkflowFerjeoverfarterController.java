package no.systema.transportdisp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;

//Trans.Disp
import no.systema.transportdisp.service.TransportDispChildWindowService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificOrderService;
import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;
import no.systema.transportdisp.mapper.url.request.UrlRequestParameterMapper;
import no.systema.transportdisp.util.manager.CodeDropDownMgr;
import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripShipContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripShipRecord;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.validator.TransportDispWorkflowSpecificFerjeoverfarterValidator;
/**
 * Transport disponering Controller - Ferjeoverfarter window
 * 
 * @author oscardelatorre
 * @date Dec 2018
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TransportDispWorkflowFerjeoverfarterController {
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private static final Logger logger = Logger.getLogger(TransportDispWorkflowFerjeoverfarterController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	private ControllerAjaxCommonFunctionsMgr controllerAjaxCommonFunctionsMgr;
	private StringManager strMgr = new StringManager();
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_ferjeoverfarter.do", method={RequestMethod.GET } )
	public ModelAndView doInit(@ModelAttribute ("record") JsonTransportDispWorkflowSpecificTripShipRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		
		logger.info("ACTION: " + action);
		//ModelAndView successView = new ModelAndView("transportdisp_mainorder_invoice");
		ModelAndView successView = new ModelAndView("transportdisp_workflow_ferjeoverfarter");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			this.controllerAjaxCommonFunctionsMgr = new ControllerAjaxCommonFunctionsMgr(this.urlCgiProxyService, this.transportDispWorkflowSpecificTripService);
			Collection<JsonTransportDispWorkflowSpecificTripShipRecord> shippingTripList = this.controllerAjaxCommonFunctionsMgr.fetchTripHeadingShippingTripList(appUser.getUser(), recordToValidate.getFeavd(), recordToValidate.getFetur());
			model.put("list", shippingTripList);
			
			//try to use the session list if the method is used from at redirection after UPDATE ...
			if(strMgr.isNotNull(request.getParameter("rd"))){
				Collection<JsonTransportDispWorkflowSpecificTripShipRecord> shippingDeparturesList = (Collection)session.getAttribute("listDeparturesSession");
				model.put("listDepartures", shippingDeparturesList);
			}else{
				Collection<JsonTransportDispWorkflowSpecificTripShipRecord> shippingDeparturesList = this.controllerAjaxCommonFunctionsMgr.fetchTripHeadingShippingTripListDepartures(appUser.getUser(), null);
				session.setAttribute("listDeparturesSession", shippingDeparturesList);
				model.put("listDepartures", shippingDeparturesList);
			}
			
	    	//
			model.put("avd", recordToValidate.getFeavd());
			model.put("tur", recordToValidate.getFetur());
			
			model.put("record", recordToValidate);
	    	successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    	logger.info("end of method");
			
	    	return successView;
		}
	}	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_ferjeoverfarter_edit.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditFrisokevei(@ModelAttribute ("record") JsonTransportDispWorkflowSpecificTripShipRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		//logger.info("isModeUpdate:");
		
		//Params
		StringBuffer params = new StringBuffer();
		
		logger.info("ACTION: " + action);
		ModelAndView successView = new ModelAndView("redirect:transportdisp_workflow_ferjeoverfarter.do?rd=1&feavd=" + recordToValidate.getFeavd() + "&fetur=" + recordToValidate.getFetur() );
		ModelAndView errorView = new ModelAndView("transportdisp_workflow_ferjeoverfarter");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			if(TransportDispConstants.ACTION_UPDATE.equals(action)){
				logger.info("[INFO] doUpdate action ...");
				TransportDispWorkflowSpecificFerjeoverfarterValidator validator = new TransportDispWorkflowSpecificFerjeoverfarterValidator();
				//Validate
				validator.validate(recordToValidate, bindingResult);
				//check for ERRORS
				if(bindingResult.hasErrors()){
					logger.info("[ERROR Validation] Record does not validate)");
					this.setDomainObjects(session, appUser.getUser(), recordToValidate, model);
					errorView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
					
			    	return errorView;
			    	
				}else{
					
					String MODE = "A";
					if(recordToValidate.getIsModeUpdate()!=null){
						//UPDATE
						logger.info("[INFO] UPDATE " + " start process... ");
					}
					//----------------------------------------------
					//Go on with the Update (if applicable)
					//----------------------------------------------
					JsonTransportDispWorkflowSpecificTripShipContainer container = this.executeUpdateLine(appUser, recordToValidate, MODE);
					if(container!=null){
	    				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	    					logger.info("[ERROR] Back-end Error: " + container.getErrMsg());
	    					model.put("errorMessage", container.getErrMsg());
	    					
	    					this.setDomainObjects(session, appUser.getUser(), recordToValidate, model);
	    					errorView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    					
	    					return errorView;
	    					
	    				}else{
	    					//succefully done!
	    		    		logger.info("[INFO] Valid Update -- Record successfully updated, OK ");
	    				}
	    			}
					logger.info("[INFO] UPDATE end process. ");

				}
				
			}else if(TransportDispConstants.ACTION_DELETE.equals(action)){
				String DELETE_MODE = "D";
				JsonTransportDispWorkflowSpecificTripShipContainer container = this.executeUpdateLine(appUser, recordToValidate, DELETE_MODE);
				if(container!=null){
    				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
    					this.setDomainObjects(session, appUser.getUser(), recordToValidate, model);
    					return errorView;
    				}else{
    					//Delete succefully done!
    		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
    		    		this.setDomainObjects(session, appUser.getUser(), recordToValidate, model);
    				}
    			}
				
			}
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param modelMap
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_ferjeoverfarter_departures_search.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doSearch(ModelMap modelMap, @ModelAttribute ("record") JsonTransportDispWorkflowSpecificTripShipRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		
		logger.info("ACTION: " + action);
		//ModelAndView successView = new ModelAndView("transportdisp_mainorder_invoice");
		ModelAndView successView = new ModelAndView("transportdisp_workflow_ferjeoverfarter");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			this.controllerAjaxCommonFunctionsMgr = new ControllerAjaxCommonFunctionsMgr(this.urlCgiProxyService, this.transportDispWorkflowSpecificTripService);
			Collection<JsonTransportDispWorkflowSpecificTripShipRecord> shippingTripList = this.controllerAjaxCommonFunctionsMgr.fetchTripHeadingShippingTripList(appUser.getUser(), recordToValidate.getFeavd(), recordToValidate.getFetur());
			model.put("list", shippingTripList);
			
			//try to use the session list if the method is used from at redirection after UPDATE ...
			Collection<JsonTransportDispWorkflowSpecificTripShipRecord> shippingDeparturesList = this.controllerAjaxCommonFunctionsMgr.fetchTripHeadingShippingTripListDepartures(appUser.getUser(), recordToValidate);
			session.setAttribute("listDeparturesSession", shippingDeparturesList);
			model.put("listDepartures", shippingDeparturesList);
			
	    	//
			model.put("avd", recordToValidate.getFeavd());
			model.put("tur", recordToValidate.getFetur());
			model.put("record", recordToValidate);
			//for the departures filter
			modelMap.addAttribute("recordSearchFefrom", recordToValidate.getFefrom());
			modelMap.addAttribute("recordSearchFeto", recordToValidate.getFeto());
			
	    	successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    	logger.info("end of method");
			
	    	return successView;
		}
	}	
	
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 * @param mode
	 * @return
	 */
	private JsonTransportDispWorkflowSpecificTripShipContainer executeUpdateLine(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificTripShipRecord recordToValidate, String mode){
		JsonTransportDispWorkflowSpecificTripShipContainer retval = null;
		//user=JOVO&feavd=1&fetur=501906&fefrom=TRELL&feto=SASSN&mode=A&wsfajn=J&fedat2=20181220&fetime=0830&feleng=12&fefirm=3150&fedate=19970101&fecurr=SEK&fepri1=1805&fepri2=2105
		logger.info("[INFO] EXECUTE Update(D/A/U) :" + recordToValidate.getFeavd() + recordToValidate.getFefirm() + " start process... ");
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_UPDATE_SPECIFIC_SHIPPING_TRIPS_URL;
    	//add URL-parameters
		
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&mode=" + mode);
		//depending on mode
		if("D".equals(mode)){
			urlRequestParams.append("&feavd=" + recordToValidate.getFeavd());
			urlRequestParams.append("&fetur=" + recordToValidate.getFetur());
			urlRequestParams.append("&fefrom=" + recordToValidate.getFefrom());
			urlRequestParams.append("&feto=" + recordToValidate.getFeto());
			
		}else{
			String urlRequestParamsBean = this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
			urlRequestParams.append(urlRequestParamsBean);
			
		}
			
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		
		if(jsonPayload!=null){
			JsonTransportDispWorkflowSpecificTripShipContainer container = this.transportDispWorkflowSpecificTripService.getContainerShip(jsonPayload);
			if(container!=null && container.getErrMsg()!=null){
				logger.info(container.getErrMsg());
			}
			
			retval = container;
			
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param session
	 * @param applicationUser
	 * @param recordToValidate
	 * @param model
	 */
	private void setDomainObjects(HttpSession session, String applicationUser, JsonTransportDispWorkflowSpecificTripShipRecord recordToValidate, Map model){
		//fetch lists>
		Collection<JsonTransportDispWorkflowSpecificTripShipRecord> shippingTripList = this.controllerAjaxCommonFunctionsMgr.fetchTripHeadingShippingTripList(applicationUser, recordToValidate.getFeavd(), recordToValidate.getFetur());
		model.put("list", shippingTripList);
		Collection<JsonTransportDispWorkflowSpecificTripShipRecord> shippingDeparturesList = (Collection)session.getAttribute("listDeparturesSession");
		model.put("listDepartures", shippingDeparturesList);
		
		//
		model.put("avd", recordToValidate.getFeavd());
		model.put("tur", recordToValidate.getFetur());
		
		model.put("record", recordToValidate);
		
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier 
	private TransportDispChildWindowService transportDispChildWindowService;
	@Autowired
	@Required	
	public void setTransportDispChildWindowService(TransportDispChildWindowService value){this.transportDispChildWindowService = value;}
	public TransportDispChildWindowService getTransportDispChildWindowService(){ return this.transportDispChildWindowService; }
	
	
	@Qualifier ("transportDispDropDownListPopulationService")
	private TransportDispDropDownListPopulationService transportDispDropDownListPopulationService;
	@Autowired
	public void setTransportDispDropDownListPopulationService (TransportDispDropDownListPopulationService value){ this.transportDispDropDownListPopulationService=value; }
	public TransportDispDropDownListPopulationService getTransportDispDropDownListPopulationService(){return this.transportDispDropDownListPopulationService;}
	
	@Qualifier ("transportDispWorkflowSpecificOrderService")
	private TransportDispWorkflowSpecificOrderService transportDispWorkflowSpecificOrderService;
	@Autowired
	public void setTransportDispWorkflowSpecificOrderService (TransportDispWorkflowSpecificOrderService value){ this.transportDispWorkflowSpecificOrderService=value; }
	public TransportDispWorkflowSpecificOrderService getTransportDispWorkflowSpecificOrderService(){return this.transportDispWorkflowSpecificOrderService;}
	
	@Qualifier 
	private TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService;
	@Autowired
	@Required	
	public void setTransportDispWorkflowSpecificTripService(TransportDispWorkflowSpecificTripService value){this.transportDispWorkflowSpecificTripService = value;}
	public TransportDispWorkflowSpecificTripService getTransportDispWorkflowSpecificTripService(){ return this.transportDispWorkflowSpecificTripService; }
	
	
}

