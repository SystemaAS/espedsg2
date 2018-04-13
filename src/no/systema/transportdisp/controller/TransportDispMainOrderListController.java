package no.systema.transportdisp.controller;

import java.lang.reflect.Field;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


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
import no.systema.main.util.io.FileContentRenderer;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;

//TRANSPDISP
import no.systema.transportdisp.util.RpgReturnResponseHandler;
import no.systema.transportdisp.service.TransportDispWorkflowListService;
import no.systema.transportdisp.service.TransportDispWorkflowShippingPlanningOrdersListService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificOrderService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord;
import no.systema.transportdisp.filter.SearchFilterTransportDispWorkflowShippingPlanningOrdersList;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;
import no.systema.transportdisp.util.manager.java.reflect.ReflectionUrlStoreMgr;

/**
 * TransportDisp Order List Controller 
 * 
 * @author oscardelatorre
 * @date Apr 11, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TransportDispMainOrderListController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	private static Logger logger = Logger.getLogger(TransportDispMainOrderListController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private ControllerAjaxCommonFunctionsMgr controllerAjaxCommonFunctionsMgr;
	private ReflectionUrlStoreMgr reflectionUrlStoreMgr = new ReflectionUrlStoreMgr();
	private StringManager strMgr = new StringManager();
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
	@RequestMapping(value="transportdisp_mainorderlist.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterTransportDispWorkflowShippingPlanningOrdersList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		this.controllerAjaxCommonFunctionsMgr = new ControllerAjaxCommonFunctionsMgr(this.urlCgiProxyService, this.transportDispWorkflowSpecificTripService);
		this.context = TdsAppContext.getApplicationContext();
		Collection<JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord> outputListCurrentOrders = new ArrayList<JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord>();
		Collection<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord> outputListOpenOrders = new ArrayList<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord>();
		String wstur = request.getParameter("wstur");
		String wssavd = request.getParameter("wssavd");
		if(wssavd!=null && !"".equals(wssavd)){ recordToValidate.setAvd(wssavd); }
		if(wstur!=null && !"".equals(wstur)){ recordToValidate.setTur(wstur); }
		
		
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("transportdisp_mainorderlist");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TRANSPORT_DISP);
			appUser.setUrlStoreProps(this.reflectionUrlStoreMgr.printProperties("no.systema.transportdisp.url.store.TransportDispUrlDataStore", "html")); //Debug info om UrlStore
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
				//this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				//this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
	    		successView.addObject(TransportDispConstants.DOMAIN_LIST_CURRENT_ORDERS, new ArrayList());
	    		successView.addObject(TransportDispConstants.DOMAIN_LIST_OPEN_ORDERS, new ArrayList());
	    		successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
				//STEP [1]
		    	//Get all lists
	    		outputListCurrentOrders = this.getListCurrentOrders(appUser, recordToValidate, model);
	    		//Put list for upcoming view (PDF, Excel, or ErrorHandling in add/remove orders (method below...)
				if(outputListCurrentOrders!=null){
					session.setAttribute(session.getId() + TransportDispConstants.SESSION_LIST_CURRENT_ORDERS_ON_TRIP, outputListCurrentOrders);
				}
	    		StringBuffer userAvd = new StringBuffer();
	    		outputListOpenOrders = this.getListOpenOrders(appUser, recordToValidate, model, userAvd);
	    		//Put list for upcoming view (PDF, Excel, or ErrorHandling in add/remove orders (method below...)
	    		if(outputListOpenOrders!=null){
					session.setAttribute(session.getId() + TransportDispConstants.SESSION_LIST_OPEN_ORDERS_ON_TRIP, outputListOpenOrders);
				}
	    		model.put("userAvd", userAvd.toString());
	    		
	    		//STEP [2]
	    		//Get the trip header and economic Matrix
	    		model.put(TransportDispConstants.DOMAIN_RECORD, new JsonTransportDispWorkflowSpecificTripRecord());
		 		JsonTransportDispWorkflowSpecificTripContainer container = this.controllerAjaxCommonFunctionsMgr.fetchTripHeading(appUser.getUser(), recordToValidate.getAvd(), recordToValidate.getTur());
		 		if(container!=null){
	   			 	for(JsonTransportDispWorkflowSpecificTripRecord  record : container.getGetonetrip()){
	   			 		model.put(TransportDispConstants.DOMAIN_RECORD, record);		   			 		
	   			 	}
   			 	}
		 	
	   		 	//--------------------------------------
				//Final successView with domain objects
				//--------------------------------------
		 		String errorFromRedirect = request.getParameter("err");
		 		if(strMgr.isNotNull(errorFromRedirect)){
		 			model.put(TransportDispConstants.ASPECT_ERROR_MESSAGE, errorFromRedirect);
		 		}
				//drop downs
				//this.setCodeDropDownMgr(appUser, model);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		//domain and search filter
				successView.addObject(TransportDispConstants.DOMAIN_LIST_CURRENT_ORDERS, outputListCurrentOrders);
				successView.addObject(TransportDispConstants.DOMAIN_LIST_OPEN_ORDERS,outputListOpenOrders);
				//Put list for upcoming view (PDF, Excel, etc)
				if(outputListOpenOrders!=null){
					session.setAttribute(session.getId() + TransportDispConstants.SESSION_LIST, outputListOpenOrders);
				}
				successView.addObject("searchFilter", recordToValidate);
				
				logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
				return successView;
			    	
		    }
		}
		
	}
	
	/**
	 * The method moves orders from/to current orders list/open orders list and vice-versa
	 * Note: The order is never deleted. The permanent deletion of an order is done somewhere else...
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="transportdisp_mainorderlist_add_remove_order.do",  method={RequestMethod.GET} )
	public ModelAndView doAddRemoveOrder(@ModelAttribute ("record") SearchFilterTransportDispWorkflowShippingPlanningOrdersList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		Map model = new HashMap();
		this.context = TdsAppContext.getApplicationContext();
		Collection<JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord> outputListCurrentOrders = new ArrayList<JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord>();
		Collection<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord> outputListOpenOrders = new ArrayList<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord>();
		String wstur = request.getParameter("wstur");
		String wsavd = request.getParameter("wsavd");
		String wsopd = request.getParameter("wsopd");
		String wmode = request.getParameter("wmode");

		if(wmode!=null && !"".equals(wmode)){recordToValidate.setMode(wmode);}
		if(wsavd!=null && !"".equals(wsavd))recordToValidate.setAvd(wsavd);
		if(wstur!=null && !"".equals(wstur))recordToValidate.setTur(wstur);
		if(wsopd!=null && !"".equals(wsopd))recordToValidate.setOpd(wsopd);
		logger.info("#OPD:" + recordToValidate.getOpd());
		ModelAndView errorView = new ModelAndView("transportdisp_mainorderlist");
		ModelAndView successView = new ModelAndView("redirect:transportdisp_mainorderlist.do?action=doFind&wssavd=" + wsavd + "&wstur=" + wstur);
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
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
				//this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				//this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
	    		successView.addObject(TransportDispConstants.DOMAIN_LIST_CURRENT_ORDERS, new ArrayList());
	    		successView.addObject(TransportDispConstants.DOMAIN_LIST_OPEN_ORDERS, new ArrayList());
	    		successView.addObject("searchFilter", recordToValidate);
				return errorView;
	    		
		    }else{
		    	final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_ADD_DELETE_ORDER_FROM_TRIP_URL;
	    		//add URL-parameters
	    		StringBuffer urlRequestParams = new StringBuffer();
	    		urlRequestParams.append("user=" + appUser.getUser());
	    		urlRequestParams.append("&wmode=" + wmode);urlRequestParams.append("&wstur=" + wstur);
	    		urlRequestParams.append("&wsavd=" + wsavd);urlRequestParams.append("&wsopd=" + wsopd);
	    		
	    		//session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
		    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		    	//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnAddRemoveOrder(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("AVD/OPD:" + wsavd +"/"+ wsopd + " - " + rpgReturnResponseHandler.getErrorMessage());
		    		this.setFatalErrorAddRemoveOrders(model, rpgReturnResponseHandler, recordToValidate);
		    		
		    		successView = errorView;
		    		successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
		    		//-------------------------------------------------------------------------------------------------------
		    		//Now re-populate all elements since we are not able to redirect (we must present the ERROR message ...)
		    		//-------------------------------------------------------------------------------------------------------
		    		//STEP [1] get from session
		    		List sessionCurrentOrdersList = (List)session.getAttribute(session.getId() + TransportDispConstants.SESSION_LIST_CURRENT_ORDERS_ON_TRIP);
		    		List sessionOpenOrdersList = (List)session.getAttribute(session.getId() + TransportDispConstants.SESSION_LIST_OPEN_ORDERS_ON_TRIP);
		    		successView.addObject(TransportDispConstants.DOMAIN_LIST_CURRENT_ORDERS, sessionCurrentOrdersList);
		    		successView.addObject(TransportDispConstants.DOMAIN_LIST_OPEN_ORDERS, sessionOpenOrdersList);
		    		
		    		//STEP [2] Get the trip header and economic Matrix
		    		model.put(TransportDispConstants.DOMAIN_RECORD, new JsonTransportDispWorkflowSpecificTripRecord());
			 		JsonTransportDispWorkflowSpecificTripContainer container = this.controllerAjaxCommonFunctionsMgr.fetchTripHeading(appUser.getUser(), recordToValidate.getAvd(), recordToValidate.getTur());
			 		if(container!=null){
		   			 	for(JsonTransportDispWorkflowSpecificTripRecord  record : container.getGetonetrip()){
		   			 		model.put(TransportDispConstants.DOMAIN_RECORD, record);		   			 		
		   			 	}
	   			 	}
		    		//put search filter
		    		successView.addObject("searchFilter", recordToValidate);
		    		//------------------
		    		//END re-population
		    		//------------------
		    		 
		    	}
		    	
		    	return successView;
		    }
		}
	}
	
	/**
	 * Permanent deletion of a specific order from the order list
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_mainorderlist_permanently_delete_order.do",  method={RequestMethod.GET} )
	public ModelAndView doPermanentlyDeleteOrder(@ModelAttribute ("record") SearchFilterTransportDispWorkflowShippingPlanningOrdersList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		Map model = new HashMap();

		logger.info("#AVD:" + recordToValidate.getAvd());
		logger.info("#OPD:" + recordToValidate.getOpd());
		ModelAndView errorView = new ModelAndView("transportdisp_mainorderlist");
		StringBuffer params = new StringBuffer();
		if(recordToValidate.getAvd()!=null && !"".equals(recordToValidate.getAvd())){
			params.append("&wssavd=" + recordToValidate.getAvd());
		}
		ModelAndView successView = new ModelAndView("redirect:transportdisp_mainorderlist.do?action=doFind" + params);
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//-----------
			//Validation
			//-----------
			/* TODO (further on...?)
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
				//this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				//this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
	    		successView.addObject(TransportDispConstants.DOMAIN_LIST_CURRENT_ORDERS, new ArrayList());
	    		successView.addObject(TransportDispConstants.DOMAIN_LIST_OPEN_ORDERS, new ArrayList());
	    		successView.addObject("searchFilter", recordToValidate);
				//return errorView;
	    		
		    }else{
		    	final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_PERMANENTLY_DELETE_MAIN_ORDER_URL;
	    		//add URL-parameters
	    		StringBuffer urlRequestParams = new StringBuffer();
	    		urlRequestParams.append("user=" + appUser.getUser());
	    		urlRequestParams.append("&avd=" + recordToValidate.getAvd());
	    		urlRequestParams.append("&opd=" + recordToValidate.getOpd());
	    		
	    		//session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
		    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		    	//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnPermanentDeleteOrder(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on DELETE: " + rpgReturnResponseHandler.getErrorMessage());
		    		this.setFatalErrorPermanentDeleteOrders(model, rpgReturnResponseHandler, recordToValidate);	
		    		errorView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
		    		errorView.addObject("searchFilter", recordToValidate);
		    		return errorView;
		    	}
		    }
			return successView;
		}
	}
	
	/**
	 * Copy order
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_mainorderlist_copy_order.do",  method={RequestMethod.POST} )
	public ModelAndView doCopyOrder(HttpSession session, HttpServletRequest request){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		Map model = new HashMap();
		String originalAvd = "";
		String originalOpd = ""; 
		String originalTur = "";
		String sign = "";
		String newAvd = "";
		//get the dynamic parameters
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);

	        if (element != null && value != null) {
        		//logger.info("####################################################");
    			//logger.info("param Name : " + element + " value: " + value);
    			if(element.startsWith("originalAvd")){
    				originalAvd = value;
    			}else if(element.startsWith("originalOpd")){
    				originalOpd = value;
    			}else if(element.startsWith("newAvd")){
    				newAvd = value;
    			}else if(element.startsWith("sign")){
    				sign = value;
    			}//Tur?
	        }
	    }
		
		logger.info("#orig. avd:" + originalAvd);
		logger.info("#orig. opd:" + originalOpd);
		logger.info("#new avd:" + newAvd);
		logger.info("#sign:" + sign);
		
		ModelAndView errorView = new ModelAndView("transportdisp_mainorderlist");
		ModelAndView successView = null;
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			
			//start process	
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_COPY_ORDER_URL;
    		//add URL-parameters
    		StringBuffer urlRequestParams = new StringBuffer();
    		urlRequestParams.append("user=" + appUser.getUser());
    		urlRequestParams.append("&avd=" + originalAvd);urlRequestParams.append("&opd=" + originalOpd);
    		urlRequestParams.append("&newavd=" + newAvd);
    		urlRequestParams.append("&newhesg=" + sign);
    		
    		
    		
    		//session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
	    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
	    	//Debug --> 
	    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnCopyOrder(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on COPY: " + rpgReturnResponseHandler.getErrorMessage());
	    		this.setFatalErrorCopyOrders(model, rpgReturnResponseHandler, originalAvd, originalOpd, newAvd);	
	    		errorView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
	    		return errorView;
	    	}
	    	//now build the success view to redirect to (with no errors)
	    	StringBuffer sbSuccessViewParams = new StringBuffer();
			sbSuccessViewParams.append ("user=" + appUser.getUser());
			sbSuccessViewParams.append ("&heavd=" + rpgReturnResponseHandler.getNewavd());
			sbSuccessViewParams.append ("&heopd="  + rpgReturnResponseHandler.getNewopd());
			successView = new ModelAndView("redirect:transportdisp_mainorder.do?" + sbSuccessViewParams.toString());

	    	return successView;
		}
	}
	
	/**
	 * Move order
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_mainorderlist_move_order.do",  method={RequestMethod.POST} )
	public ModelAndView doMoveOrder(HttpSession session, HttpServletRequest request){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		Map model = new HashMap();
		String originalAvd = "";
		String originalOpd = ""; 
		String originalTur = "";
		String newAvd = "";
		//get the dynamic parameters
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);

	        if (element != null && value != null) {
        		//logger.info("####################################################");
    			//logger.info("param Name : " + element + " value: " + value);
    			if(element.startsWith("originalAvd")){
    				originalAvd = value;
    			}else if(element.startsWith("originalOpd")){
    				originalOpd = value;
    			}else if(element.startsWith("newAvdMove")){
    				newAvd = value;
    			}//Tur?
	        }
	    }
		
		logger.info("#orig. AVD:" + originalAvd);
		logger.info("#orig. OPD:" + originalOpd);
		logger.info("#New AVD:" + newAvd);
		
		ModelAndView errorView = new ModelAndView("transportdisp_mainorderlist");
		ModelAndView successView = null;
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			
			//start process	
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_MOVE_ORDER_URL;
    		//add URL-parameters
    		StringBuffer urlRequestParams = new StringBuffer();
    		urlRequestParams.append("user=" + appUser.getUser());
    		urlRequestParams.append("&avd=" + originalAvd);urlRequestParams.append("&opd=" + originalOpd);
    		urlRequestParams.append("&newavd=" + newAvd);
    		
    		//session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
	    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
	    	//Debug --> 
	    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnMoveOrder(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on MOVE: " + rpgReturnResponseHandler.getErrorMessage());
	    		this.setFatalErrorCopyOrders(model, rpgReturnResponseHandler, originalAvd, originalOpd, newAvd);	
	    		errorView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
	    		return errorView;
	    	}
	    	//now build the success view to redirect to (with no errors)
	    	StringBuffer sbSuccessViewParams = new StringBuffer();
			sbSuccessViewParams.append ("user=" + appUser.getUser());
			sbSuccessViewParams.append ("&heavd=" + rpgReturnResponseHandler.getNewavd());
			sbSuccessViewParams.append ("&heopd="  + rpgReturnResponseHandler.getNewopd());
			successView = new ModelAndView("redirect:transportdisp_mainorder.do?" + sbSuccessViewParams.toString());
	    	return successView;
		}
	}
	
		
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="transportdisp_mainorderlist_renderFraktbrev.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doRenderFraktbrev(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		this.context = TdsAppContext.getApplicationContext();
		String avd = request.getParameter("wsavd");
		String opd = request.getParameter("wsopd");
		String wstoll = request.getParameter("wstoll");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//get BASE URL /default Godslista
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_PRINT_SPECIFIC_ORDER_FRAKTBREV_URL;
			//add URL-parameters
    		StringBuffer urlRequestParams = new StringBuffer();
    		urlRequestParams.append("user=" + appUser.getUser());
    		urlRequestParams.append("&wsavd=" + avd);
    		urlRequestParams.append("&wsopd=" + opd);
    		urlRequestParams.append("&wstoll=" + wstoll);
    		
    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
	    	//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	if(jsonPayload!=null){
	    		JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer fraktbrevPdfContainer = this.transportDispWorkflowShippingPlanningOrdersListService.getOrderFraktbrevPdfContainer(jsonPayload);
	    		if(fraktbrevPdfContainer!=null && fraktbrevPdfContainer.getIfsfil()!=null){
	    			String absoluteFilePath = fraktbrevPdfContainer.getIfsfil();
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
	 * @param appUser
	 * @param wstur
	 * @return
	 */
	private Collection<JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord> getListCurrentOrders(SystemaWebUser appUser, SearchFilterTransportDispWorkflowShippingPlanningOrdersList recordToValidate, Map model){
		Collection<JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord> outputListCurrentOrders = new ArrayList();
		//------------------------------------
        //[STEP 1] get Current Orders BASE URL
		//------------------------------------
		final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_LIST_ORDERS_ON_TRIP_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		if(!"".equals(recordToValidate.getTur())&& recordToValidate.getTur()!=null){ urlRequestParams.append("&wstur=" + recordToValidate.getTur()); }
		
		//session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayloadCurrentOrders = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadCurrentOrders));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayloadCurrentOrders!=null){
    		JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer jsonCurrentOrdersListContainer = this.transportDispWorkflowShippingPlanningOrdersListService.getCurrentOrdersListContainer(jsonPayloadCurrentOrders);
    		Double hentTotalAmount = 0.000D;
    		Double hem3TotalAmount = 0.000D;
    		Double helmTotalAmount = 0.000D;
    		Double hevktTotalAmount = 0.000D;
    		
    		for(JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord record : jsonCurrentOrdersListContainer.getOrderlistlandtur()){
    			if(record.getHent()!=null && !"".equals(record.getHent())){
    				String tmpHent = record.getHent().replace(",", ".");
    				hentTotalAmount += Double.parseDouble(tmpHent);
    			}
    			if(record.getHem3()!=null && !"".equals(record.getHem3())){
    				String tmpHem3 = record.getHem3().replace(",", ".");
    				hem3TotalAmount += Double.parseDouble(tmpHem3);
    			}
    			if(record.getHelm()!=null && !"".equals(record.getHelm())){
    				String tmpHelm = record.getHelm().replace(",", ".");
    				helmTotalAmount += Double.parseDouble(tmpHelm);
    			}
    			if(record.getHevkt()!=null && !"".equals(record.getHevkt())){
    				String tmpHevkt = record.getHevkt().replace(",", ".");
    				hevktTotalAmount += Double.parseDouble(tmpHevkt);
    			}
    			
    		}
    		//Totals
    		jsonCurrentOrdersListContainer.setHentTotalAmount(hentTotalAmount);
    		jsonCurrentOrdersListContainer.setHem3TotalAmount(hem3TotalAmount);
    		jsonCurrentOrdersListContainer.setHelmTotalAmount(helmTotalAmount);
    		jsonCurrentOrdersListContainer.setHevktTotalAmount(hevktTotalAmount);
    		
    		model.put(TransportDispConstants.DOMAIN_CONTAINER_CURRENT_ORDERS, jsonCurrentOrdersListContainer);
    		outputListCurrentOrders = jsonCurrentOrdersListContainer.getOrderlistlandtur();
    	}		
		return outputListCurrentOrders;
	    	
	}
	/**
	 * 
	 * @param appUser
	 * @param wssavd
	 * @return
	 */
	private Collection<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord> getListOpenOrders(SystemaWebUser appUser, SearchFilterTransportDispWorkflowShippingPlanningOrdersList recordToValidate, Map model, StringBuffer userAvd){
		Collection<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord> outputListOpenOrders = new ArrayList();
		//------------------------------------
        //[STEP 2] get Open Orders BASE URL
    		//------------------------------------
    		final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_LIST_ORDERS_NOT_ON_TRIP_URL;
    		//add URL-parameters
    		StringBuffer urlRequestParams = new StringBuffer();
    		urlRequestParams.append("user=" + appUser.getUser());
    		if(!"".equals(recordToValidate.getAvd())&& recordToValidate.getAvd()!=null ){ urlRequestParams.append("&wssavd=" + recordToValidate.getAvd()); }
    		if(!"".equals(recordToValidate.getOpd())&& recordToValidate.getOpd()!=null ){ urlRequestParams.append("&wssopd=" + recordToValidate.getOpd()); }
    		if(!"".equals(recordToValidate.getOpdType())&& recordToValidate.getOpdType()!=null ){ urlRequestParams.append("&wssot=" + recordToValidate.getOpdType()); }
    		if(!"".equals(recordToValidate.getSign())&& recordToValidate.getSign()!=null ){ urlRequestParams.append("&wsssg=" + recordToValidate.getSign()); }
    		//From and dates
    		if(!"".equals(recordToValidate.getFrom())&& recordToValidate.getFrom()!=null ){ urlRequestParams.append("&wssdf=" + recordToValidate.getFrom()); }
    		if(!"".equals(recordToValidate.getFromDateF())&& recordToValidate.getFromDateF()!=null ){ urlRequestParams.append("&wopdtf=" + recordToValidate.getFromDateF()); }
    		if(!"".equals(recordToValidate.getFromDateT())&& recordToValidate.getFromDateT()!=null ){ urlRequestParams.append("&wopdtf2=" + recordToValidate.getFromDateT()); }
    		//To and dates
    		if(!"".equals(recordToValidate.getTo())&& recordToValidate.getTo()!=null ){ urlRequestParams.append("&wssdt=" + recordToValidate.getTo()); }
    		if(!"".equals(recordToValidate.getToDateF())&& recordToValidate.getToDateF()!=null ){ urlRequestParams.append("&wopdtt=" + recordToValidate.getToDateF()); }
    		if(!"".equals(recordToValidate.getToDateT())&& recordToValidate.getToDateT()!=null ){ urlRequestParams.append("&wopdtt2=" + recordToValidate.getToDateT()); }
    		if(!"".equals(recordToValidate.getWsprebook())&& recordToValidate.getWsprebook()!=null ){ urlRequestParams.append("&wsprebook=" + recordToValidate.getWsprebook()); }
    		
    		
    		//session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
	    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
	    	//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer jsonOpenOrdersListContainer = this.transportDispWorkflowShippingPlanningOrdersListService.getOpenOrdersListContainer(jsonPayload);
	    		model.put(TransportDispConstants.DOMAIN_CONTAINER_OPEN_ORDERS, jsonOpenOrdersListContainer);
	    		if(jsonOpenOrdersListContainer!=null){
		    		outputListOpenOrders = jsonOpenOrdersListContainer.getOrderlistlandled();
		    		if(userAvd!=null){
			    		if(jsonOpenOrdersListContainer.getWssavd()!=null && !"".equals(jsonOpenOrdersListContainer.getWssavd())){
			    			userAvd.append(jsonOpenOrdersListContainer.getWssavd());
			    			
			    		}else if(jsonOpenOrdersListContainer.getStdavd()!=null && !"".equals(jsonOpenOrdersListContainer.getStdavd())){
			    			Integer stdAvd = 0;
			    			try{ stdAvd = Integer.valueOf(jsonOpenOrdersListContainer.getStdavd());}
			    			catch(Exception e){ logger.info("ERROR in this line!!!"); }
			    			userAvd.append(String.valueOf(stdAvd));
			    			
			    		}else{
			    			logger.debug("[ERROR] in this line... ?");
			    		}
		    		}
	    		}
	    	}		
	    
		return outputListOpenOrders;
	}
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalErrorAddRemoveOrders(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, SearchFilterTransportDispWorkflowShippingPlanningOrdersList record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInViewAddRemoveOrders(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 */
	private void setAspectsInViewAddRemoveOrders (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(TransportDispConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getWstur());
		model.put(TransportDispConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
	}
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalErrorPermanentDeleteOrders(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, SearchFilterTransportDispWorkflowShippingPlanningOrdersList record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		model.put(TransportDispConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getHeavd());
		errorMetaInformation.append(rpgReturnResponseHandler.getHeopd());
		model.put(TransportDispConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param avd
	 * @param opd
	 * @param newAvd
	 */
	private void setFatalErrorCopyOrders(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, String avd, String opd, String newAvd ){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		model.put(TransportDispConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(avd);
		errorMetaInformation.append(opd);
		model.put(TransportDispConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	
	/**
	 * 
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, SearchFilterTransportDispWorkflowShippingPlanningOrdersList record){
		//SET HEADER RECORDS  (from RPG)
		model.put(TransportDispConstants.DOMAIN_RECORD, record);
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
	
	
	@Qualifier ("transportDispWorkflowShippingPlanningOrdersListService")
	private TransportDispWorkflowShippingPlanningOrdersListService transportDispWorkflowShippingPlanningOrdersListService;
	@Autowired
	public void setTransportDispWorkflowShippingPlanningOrdersListService (TransportDispWorkflowShippingPlanningOrdersListService value){ this.transportDispWorkflowShippingPlanningOrdersListService=value; }
	public TransportDispWorkflowShippingPlanningOrdersListService getTransportDispWorkflowShippingPlanningOrdersListService(){return this.transportDispWorkflowShippingPlanningOrdersListService;}
	
	@Qualifier 
	private TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService;
	@Autowired
	@Required	
	public void setTransportDispWorkflowSpecificTripService(TransportDispWorkflowSpecificTripService value){this.transportDispWorkflowSpecificTripService = value;}
	public TransportDispWorkflowSpecificTripService getTransportDispWorkflowSpecificTripService(){ return this.transportDispWorkflowSpecificTripService; }
	
	
}

