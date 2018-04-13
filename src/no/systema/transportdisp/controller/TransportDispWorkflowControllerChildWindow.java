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
import no.systema.transportdisp.service.TransportDispWorkflowListService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;
import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesRecord;
import no.systema.transportdisp.filter.SearchFilterTransportDispWorkflowTripList;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.codes.JsonTransportDispCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispCustomerContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispCustomerRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispLoadUnloadPlacesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispLoadUnloadPlacesRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispPackingCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispPackingCodesRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispTollstedCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispTollstedCodesRecord;

import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispSupplierContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispSupplierRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeRecord;

import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiCodesRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiDocCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiDocCodesRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiGiltighetsListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiGiltighetsListRecord;




import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispAvdContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispAvdRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispBilCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispBilCodeRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispBilNrContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispBilNrRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispDriverContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispDriverRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispTranspCarrierContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispTranspCarrierRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.codes.JsonTransportDispCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.codes.JsonTransportDispCodeRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispFileUploadValidationContainer;


import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.validator.TransportDispWorkflowTripListValidator;




/**
 * Transport disponering Controller - child windows for search
 * 
 * @author oscardelatorre
 * @date Apr 1, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TransportDispWorkflowControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(TransportDispWorkflowControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private DateTimeManager dateTimeManager = new DateTimeManager();
	private StringManager strMgr = new StringManager();
	//customer
	private final String DATATABLE_AVD_LIST = "avdList";
	private final String DATATABLE_BILNR_LIST = "bilNrList";
	private final String DATATABLE_BILCODE_LIST = "bilCodeList";
	private final String DATATABLE_TRANSPCARRIER_LIST = "transpCarrierList";
	private final String DATATABLE_DRIVER_LIST = "driverList";
	private final String DATATABLE_CUSTOMER_LIST = "customerList";
	private final String DATATABLE_LOAD_UNLOAD_PLACES_LIST = "loadUnloadPlacesList";
	private final String DATATABLE_DANGEROUS_GOODS_LIST = "dangerousGoodsList";
	private final String DATATABLE_PACKING_CODES_LIST = "packingCodesList";
	private final String DATATABLE_TOLLSTED_CODES_LIST = "tollstedCodesList";
	private final String DATATABLE_SUPPLIER_LIST = "supplierList";
	private final String DATATABLE_GEBYRCODE_LIST = "gebyrCodeList";
	private final String DATATABLE_COUNTRYCODE_LIST = "countryCodeList";
	private final String DATATABLE_FRISOKVEI_CODES_LIST = "frisokveiCodesList";
	private final String DATATABLE_FRISOKVEI_DOCCODES_LIST = "frisokveiDocCodesList";
	private final String DATATABLE_FRISOKVEI_CODES_GILTIGHETS_LIST = "frisokveiCodesGiltihetsList";
	
	
	
	
	//Postal codes
	private final String DATATABLE_POSTALCODE_LIST = "postalCodeList";
	private final String POSTALCODE_DIRECTION = "direction";
	
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private ControllerAjaxCommonFunctionsMgr controllerAjaxCommonFunctionsMgr = null;
	
	
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
	@RequestMapping(value="transportdisp_workflow_childwindow_avd.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInit(@ModelAttribute ("record") JsonTransportDispAvdRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInit");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_avd");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="transportdisp_workflow_childwindow_avd.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") JsonTransportDispAvdRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFind");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_avd");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] search-filter does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//this.setCodeDropDownMgr(appUser, model);
		    		model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
		    		//prepare the access CGI with RPG back-end
		    		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_AVD_URL;
		    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    
		    		if(jsonPayload!=null){
			    		JsonTransportDispAvdContainer container = this.transportDispChildWindowService.getAvdContainer(jsonPayload);
			    		if(container!=null){
			    			List<JsonTransportDispAvdRecord> list = new ArrayList();
			    			for(JsonTransportDispAvdRecord  record : container.getAvdelningar()){
			    				//logger.info("CUSTOMER NO: " + record.getKundnr());
			    				//logger.info("NAME: " + record.getKnavn());
			    				record.setStatus(recordToValidate.getStatus());
			    				list.add(record);
			    			}
			    			model.put(this.DATATABLE_AVD_LIST, list);
			    			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			    		}
		    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_bilnr.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitBilNr(@ModelAttribute ("record") JsonTransportDispBilNrRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitBilNr");
		Map model = new HashMap();
		StringBuffer paramsRedirect = new StringBuffer();
		if(recordToValidate.getUnbiln()!=null && !"".equals(recordToValidate.getUnbiln())){
			paramsRedirect.append("&sokbnr=" + recordToValidate.getUnbiln());
		}
		ModelAndView successView = new ModelAndView("redirect:transportdisp_workflow_childwindow_bilnr.do?action=doFind" + paramsRedirect.toString());
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="transportdisp_workflow_childwindow_bilnr.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindBilNr(@ModelAttribute ("record") JsonTransportDispBilNrContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindBilNr");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_bilnr");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] search-filter does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//this.setCodeDropDownMgr(appUser, model);
		    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
		    		//prepare the access CGI with RPG back-end
		    		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_BILNR_URL;
		    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    
		    		if(jsonPayload!=null){
		    			JsonTransportDispBilNrContainer container = this.transportDispChildWindowService.getBilNrContainer(jsonPayload);
			    		if(container!=null){
			    			List<JsonTransportDispBilNrRecord> list = new ArrayList();
			    			for(JsonTransportDispBilNrRecord  record : container.getBilnrlist()){
			    				//logger.info("CUSTOMER NO: " + record.getKundnr());
			    				//logger.info("NAME: " + record.getKnavn());
			    				list.add(record);
			    			}
			    			model.put(this.DATATABLE_BILNR_LIST, list);
			    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
			    		}
		    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_bilcode.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitBilCode(@ModelAttribute ("record") JsonTransportDispBilCodeRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitBilCode");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_bilcode");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="transportdisp_workflow_childwindow_bilcode.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindBilCode(@ModelAttribute ("record") JsonTransportDispBilCodeContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindBilCode");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_bilcode");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] search-filter does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//this.setCodeDropDownMgr(appUser, model);
		    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
		    		//prepare the access CGI with RPG back-end
		    		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_BILCODE_URL;
		    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    
		    		if(jsonPayload!=null){
		    			JsonTransportDispBilCodeContainer container = this.transportDispChildWindowService.getBilCodeContainer(jsonPayload);
			    		if(container!=null){
			    			List<JsonTransportDispBilCodeRecord> list = new ArrayList<JsonTransportDispBilCodeRecord>();
			    			for(JsonTransportDispBilCodeRecord  record : container.getBilkodlist()){
			    				//logger.info("CUSTOMER NO: " + record.getKundnr());
			    				//logger.info("NAME: " + record.getKnavn());
			    				list.add(record);
			    			}
			    			
			    			model.put(this.DATATABLE_BILCODE_LIST, list);
			    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
			    		}
		    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_transpcarrier.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitTranspCarrier(@ModelAttribute ("record") JsonTransportDispTranspCarrierRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTranspCarrier");
		Map model = new HashMap();
		StringBuffer paramsRedirect = new StringBuffer();
		String soktnr = request.getParameter("soktnr");
		if(soktnr!=null && !"".equals(soktnr)){
			paramsRedirect.append("&soktnr=" + soktnr);
		}
		ModelAndView successView = new ModelAndView("redirect:transportdisp_workflow_childwindow_transpcarrier.do?action=doFind" + paramsRedirect.toString());

		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="transportdisp_workflow_childwindow_transpcarrier.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindTranspCarrier(@ModelAttribute ("record") JsonTransportDispTranspCarrierContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindTranspCarrier");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_transpcarrier");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_CARRIER_URL;
	    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTransportDispTranspCarrierContainer container = this.transportDispChildWindowService.getTranspCarrierContainer(jsonPayload);
		    		if(container!=null){
		    			List<JsonTransportDispTranspCarrierRecord> list = new ArrayList();
		    			for(JsonTransportDispTranspCarrierRecord  record : container.getTranslist()){
		    				list.add(record);
		    			}
		    			model.put(this.DATATABLE_TRANSPCARRIER_LIST, list);
		    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
		    		}
	    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_driver.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitDriver(@ModelAttribute ("record") JsonTransportDispDriverContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitDriver");
		Map model = new HashMap();
		String driverId = request.getParameter("dv");
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_driver");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			model.put("driverId", driverId);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	
	/**
	 * Driver
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_driver.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindDriver(@ModelAttribute ("record") JsonTransportDispDriverContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindDriver");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		String driverId = request.getParameter("dv");
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_driver");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] search-filter does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//this.setCodeDropDownMgr(appUser, model);
		    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
		    		model.put("driverId", driverId);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
		    		//prepare the access CGI with RPG back-end
		    		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_DRIVER_URL;
		    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    
		    		if(jsonPayload!=null){
		    			JsonTransportDispDriverContainer container = this.transportDispChildWindowService.getDriverContainer(jsonPayload);
			    		if(container!=null){
			    			List<JsonTransportDispDriverRecord> list = new ArrayList();
			    			for(JsonTransportDispDriverRecord  record : container.getSjoflist()){
			    				//logger.info("CUSTOMER NO: " + record.getKundnr());
			    				//logger.info("NAME: " + record.getKnavn());
			    				list.add(record);
			    			}
			    			model.put(this.DATATABLE_DRIVER_LIST, list);
			    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
			    			model.put("driverId", driverId);
			    		}
		    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	 * Postal Codes doInit
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_postalcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitPostalCodes(@ModelAttribute ("record") JsonPostalCodesRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitPostalCodes");
		Map model = new HashMap();
		StringBuffer paramsRedirect = new StringBuffer();
		paramsRedirect.append("&direction=" + recordToValidate.getDirection());
		if(recordToValidate.getSt2lk()!=null && !"".equals(recordToValidate.getSt2lk())){
			paramsRedirect.append("&st2lk=" + recordToValidate.getSt2lk());
		}
		if(recordToValidate.getSt2kod()!=null && !"".equals(recordToValidate.getSt2kod())){
			paramsRedirect.append("&st2kod=" + recordToValidate.getSt2kod());
		}
		if(recordToValidate.getCaller()!=null && !"".equals(recordToValidate.getCaller())){
			paramsRedirect.append("&caller=" + recordToValidate.getCaller());
		}
		
		ModelAndView successView = new ModelAndView("redirect:transportdisp_workflow_childwindow_postalcodes.do?action=doFind" + paramsRedirect.toString());
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(this.POSTALCODE_DIRECTION, recordToValidate.getDirection());
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	
	/**
	 * Postal Codes
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_postalcodes.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindPostalCodes(@ModelAttribute ("record") JsonPostalCodesRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindPostalCodes");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_postalcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*XXChildWindowSearchCustomerValidator validator = new XXChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				this.controllerAjaxCommonFunctionsMgr = new ControllerAjaxCommonFunctionsMgr(this.urlCgiProxyService, this.transportDispChildWindowService );
	    		boolean exactMatch = false;
				Collection<JsonPostalCodesRecord> list = this.controllerAjaxCommonFunctionsMgr.fetchPostalCodes(appUser.getUser(), recordToValidate, exactMatch); 
		    		
	    		model.put(this.DATATABLE_POSTALCODE_LIST, list);
	    		model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
    			model.put(this.POSTALCODE_DIRECTION, recordToValidate.getDirection());
	    			
    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
				logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
				return successView;
		    		
		    }
		}
	}
	
	
	/**
	 * Customer doInit
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_customer.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitCustomer(@ModelAttribute ("record") JsonTransportDispCustomerContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitCustomer");
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_customer");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	
	/**
	 * Customer
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_customer.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindCustomer(@ModelAttribute ("record") JsonTransportDispCustomerContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindCustomer");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_customer");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] search-filter does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//this.setCodeDropDownMgr(appUser, model);
		    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
		    		//prepare the access CGI with RPG back-end
		    		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_CUSTOMER_URL;
		    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    
		    		if(jsonPayload!=null){
		    			JsonTransportDispCustomerContainer container = this.transportDispChildWindowService.getCustomerContainer(jsonPayload);
			    		if(container!=null){
			    			List<JsonTransportDispCustomerRecord> list = new ArrayList<JsonTransportDispCustomerRecord>();
			    			for(JsonTransportDispCustomerRecord  record : container.getInqcustomer()){
			    				//logger.info("CUSTOMER NO: " + record.getKundnr());
			    				//logger.info("NAME: " + record.getNavn());
			    				list.add(record);
			    			}
			    			model.put(this.DATATABLE_CUSTOMER_LIST, list);
			    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
			    		}
		    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_uploadFile.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitUploadFile(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitUploadFile");
		Map model = new HashMap();
		String wstur = request.getParameter("wstur");
		String wsavd = request.getParameter("wsavd");
		String wsopd = request.getParameter("wsopd");
		
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_uploadfile");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put("wstur", wstur);
			model.put("wsavd", wsavd);
			model.put("wsopd", wsopd);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param name
	 * @param file
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_uploadFile.do", params="action=doSave", method = RequestMethod.POST)
    public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file, HttpSession session, HttpServletRequest request ) {
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return "Not logged in...?";
		}else{
			logger.info("User:" + appUser.getUser());
	        if (!file.isEmpty()) {
        		String fileName = file.getOriginalFilename();
        		logger.info("FILE NAME:" + fileName);
                //validate file
                JsonTransportDispFileUploadValidationContainer uploadValidationContainer = this.validateFileUpload(fileName, appUser);
                //if valid
                if(uploadValidationContainer!=null && "".equals(uploadValidationContainer.getErrMsg())){
	                // TEST String rootPath = System.getProperty("catalina.home");
                		String rootPath	= uploadValidationContainer.getTmpdir();
                	    File dir = new File(rootPath);
                	    
		        	    try {
			                byte[] bytes = file.getBytes();
			                // Create the file on server
			                File serverFile = new File(dir.getAbsolutePath() + File.separator +  fileName);
			                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			                stream.write(bytes);
			                stream.close();
			                logger.info("Server File Location=" + serverFile.getAbsolutePath());
			                //catch parameters
			                uploadValidationContainer.setWstur(request.getParameter("wstur"));
	        	    		uploadValidationContainer.setWsavd(request.getParameter("wsavd"));
	        	    		uploadValidationContainer.setWsopd(request.getParameter("wsopd"));
	        	    		uploadValidationContainer.setWstype(request.getParameter("wstype"));
	        	    		//this will check if either the wstur or wsavd/wsopd will save the upload
	        	    		uploadValidationContainer = this.saveFileUpload(uploadValidationContainer, fileName, appUser);
			                if(uploadValidationContainer!=null && uploadValidationContainer.getErrMsg()==""){
			                		String suffixMsg = "";
			                		if(uploadValidationContainer.getWstur()!=null && !"".equals(uploadValidationContainer.getWstur())){
			                			suffixMsg = "  -->Tur:" + "["+ uploadValidationContainer.getWstur() + "]";
			                		}else{
			                			suffixMsg = "  -->Avd/Opd:" + "["+ uploadValidationContainer.getWsavd() + "/" + uploadValidationContainer.getWsopd() + "]";
			                		}
			                		return "You successfully uploaded file:" + fileName +  suffixMsg;
			                }else{
			                		return "You failed to upload [on MOVE] =" + fileName;
			                }
		        	    } catch (Exception e) {
		            		//run time upload error
		            		String absoluteFileName = rootPath + File.separator + fileName;
		            		return "You failed to upload to:" + fileName + " runtime error:" + e.getMessage();
		            }

                }else{
		        		if(uploadValidationContainer!=null){
		        			//Back-end error message output upon validation
		        			return uploadValidationContainer.getErrMsg();
		        		}else{
		        			return "NULL on upload file validation Object??";
		        		}
		        	}
	        } else {
	            return "You failed to upload an empty file.";
	        }
		}
    }
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_loadunloadplaces.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitLoadUloadPlaces(@ModelAttribute ("record") JsonTransportDispLoadUnloadPlacesContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitLoadUloadPlaces");
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_loadunloadplaces");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	@RequestMapping(value="transportdisp_workflow_childwindow_loadunloadplaces.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindLoadUnloadPlaces(@ModelAttribute ("record") JsonTransportDispLoadUnloadPlacesContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindLoadUploadPlaces");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_loadunloadplaces");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] search-filter does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//this.setCodeDropDownMgr(appUser, model);
		    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
		    		//prepare the access CGI with RPG back-end
		    		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_LOAD_UNLOAD_PLACES_URL;
		    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    
		    		if(jsonPayload!=null){
		    			JsonTransportDispLoadUnloadPlacesContainer container = this.transportDispChildWindowService.getLoadUnloadPlacesContainer(jsonPayload);
			    		if(container!=null){
			    			List<JsonTransportDispLoadUnloadPlacesRecord> list = new ArrayList<JsonTransportDispLoadUnloadPlacesRecord>();
			    			for(JsonTransportDispLoadUnloadPlacesRecord  record : container.getInqlosslass()){
			    				//logger.info("Load PLACE: " + record.getKotmnv());
			    				list.add(record);
			    			}
			    			model.put(this.DATATABLE_LOAD_UNLOAD_PLACES_LIST, list);
			    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
			    		}
		    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_dangerousgoods.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitDangerousGoods(@ModelAttribute ("record") JsonTransportDispDangerousGoodsContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitDangerousGoods");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_dangerousgoods");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	@RequestMapping(value="transportdisp_workflow_childwindow_dangerousgoods.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindDangerousGoods(@ModelAttribute ("record") JsonTransportDispDangerousGoodsContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindDangerousGoods");
		Collection<JsonTransportDispDangerousGoodsRecord> outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_dangerousgoods");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_DANGEROUS_GOODS_URL;
		    	String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
				
		    	logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParamsKeys);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				
				if(jsonPayload!=null){
					JsonTransportDispDangerousGoodsContainer container = this.transportDispChildWindowService.getDangerousGoodsContainer(jsonPayload);
		    			if(container!=null){
		    				outputList = container.getUnNumbers();
		    			}
				}
		    	
    			model.put(this.DATATABLE_DANGEROUS_GOODS_LIST, outputList);
    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    			return successView;
				
		    }
			
		}
	}
	
	
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_packingcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitPackingCodes(@ModelAttribute ("record") JsonTransportDispPackingCodesContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitPackingCodes");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_packingcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	@RequestMapping(value="transportdisp_workflow_childwindow_packingcodes.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindPackingCodes(@ModelAttribute ("record") JsonTransportDispPackingCodesContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindPackingCodes");
		Collection<JsonTransportDispPackingCodesRecord> outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_packingcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_PACKING_CODES_URL;
		    	String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
				
		    	logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParamsKeys);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				
				if(jsonPayload!=null){
					JsonTransportDispPackingCodesContainer container = this.transportDispChildWindowService.getPackingCodesContainer(jsonPayload);
		    			if(container!=null){
		    				outputList = container.getForpaknKoder();
		    			}
				}
		    	
    			model.put(this.DATATABLE_PACKING_CODES_LIST, outputList);
    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    			return successView;
				
		    }
			
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_frisokveicodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitFrisokveiCodes(@ModelAttribute ("record") JsonTransportDispFrisokveiCodesContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitFrisokveiCodes");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_frisokveicodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	@RequestMapping(value="transportdisp_workflow_childwindow_frisokveicodes.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindFrisokveiCodes(@ModelAttribute ("record") JsonTransportDispFrisokveiCodesRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindFrisokveiCodes");
		Collection<JsonTransportDispFrisokveiCodesRecord> outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_frisokveicodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_FRISOKVEI_CODES_URL;
		    	String urlRequestParamsKeys = "user=" + appUser.getUser();
				
		    	logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParamsKeys);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				
				if(jsonPayload!=null){
					JsonTransportDispFrisokveiCodesContainer container = this.transportDispChildWindowService.getFrisokveiCodesContainer(jsonPayload);
		    			if(container!=null){
		    				outputList = container.getAwblinelist();
		    			}
				}
		    	
    			model.put(this.DATATABLE_FRISOKVEI_CODES_LIST, outputList);
    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    			return successView;
				
		    }
			
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
	@RequestMapping(value="transportdisp_workflow_childwindow_frisokveicodes_giltihetslist.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindFrisokveiCodesGiltighetsList(@ModelAttribute ("record") JsonTransportDispFrisokveiGiltighetsListRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindFrisokveiCodesGiltighetsList");
		Collection<JsonTransportDispFrisokveiGiltighetsListRecord> outputList = new ArrayList();
		Map model = new HashMap();
		String kode = request.getParameter("kode");
		String avd = request.getParameter("avd");
		String opd = request.getParameter("opd");
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_frisokveicodes_giltighetslist");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_FRISOKVEI_VALID_LIST_URL;
		    	StringBuffer urlRequestParamsKeys = new StringBuffer();
		    	urlRequestParamsKeys.append("user=" + appUser.getUser());
		    	if(strMgr.isNotNull(kode)){
		    		urlRequestParamsKeys.append("&avd=" + avd);
		    		urlRequestParamsKeys.append("&opd=" + opd);
		    		urlRequestParamsKeys.append("&kode=" + kode);
		    	}
				
		    	logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParamsKeys);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
				//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				
				if(jsonPayload!=null){
					JsonTransportDispFrisokveiGiltighetsListContainer container = this.transportDispChildWindowService.getOrderFrisokveiContainerGiltighetsLista(jsonPayload);
		    			if(container!=null){
		    				outputList = container.getGyldigliste();
		    			}
				}
		    	
    			model.put(this.DATATABLE_FRISOKVEI_CODES_GILTIGHETS_LIST, outputList);
    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    			return successView;
				
		    }
			
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_frisokveidoccodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitFrisokveiDocCodes(@ModelAttribute ("record") JsonTransportDispFrisokveiCodesContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitFrisokveiDocCodes");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_frisokveidoccodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	@RequestMapping(value="transportdisp_workflow_childwindow_frisokveidoccodes.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindFrisokveiDocCodes(@ModelAttribute ("record") JsonTransportDispFrisokveiDocCodesRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindFrisokveiDocCodes");
		Collection<JsonTransportDispFrisokveiDocCodesRecord> outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_frisokveidoccodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_FRISOKVEI_DOCCODES_URL;
		    	String urlRequestParamsKeys = "user=" + appUser.getUser() + "&kftyp=FSDOKK";
				
		    	logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParamsKeys);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				
				if(jsonPayload!=null){
					JsonTransportDispFrisokveiDocCodesContainer container = this.transportDispChildWindowService.getFrisokveiDocCodesContainer(jsonPayload);
		    			if(container!=null){
		    				outputList = container.getAwblinelist();
		    			}
				}
		    	
    			model.put(this.DATATABLE_FRISOKVEI_DOCCODES_LIST, outputList);
    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    			return successView;
				
		    }
			
		}
	}
	
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_tollstedcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitTollstedCodes(@ModelAttribute ("record") JsonTransportDispTollstedCodesContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTollstedCodes");
		Map model = new HashMap();
		StringBuffer paramsRedirect = new StringBuffer();
		if(recordToValidate.getKode()!=null && !"".equals(recordToValidate.getKode())){
			paramsRedirect.append("&kode=" + recordToValidate.getKode());
		}
		ModelAndView successView = new ModelAndView("redirect:transportdisp_workflow_childwindow_tollstedcodes.do?action=doFind" + paramsRedirect.toString());
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	@RequestMapping(value="transportdisp_workflow_childwindow_tollstedcodes.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindTollstedCodes(@ModelAttribute ("record") JsonTransportDispTollstedCodesContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindTollstedCodes");
		Collection<JsonTransportDispTollstedCodesRecord> outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_tollstedcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_TOLLSTED_CODES_URL;
		    	String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
				
		    	logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParamsKeys);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				
				if(jsonPayload!=null){
					JsonTransportDispTollstedCodesContainer container = this.transportDispChildWindowService.getTollstedCodesContainer(jsonPayload);
		    			if(container!=null){
		    				outputList = container.getTollstedsKoder();
		    			}
				}
		    	
    			model.put(this.DATATABLE_TOLLSTED_CODES_LIST, outputList);
    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    			return successView;
				
		    }
			
		}
	}
	
	/**
	 * Supplier doInit
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_supplier.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitSpplier(@ModelAttribute ("record") JsonTransportDispSupplierContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitSpplier");
		Map model = new HashMap();
		
		StringBuffer paramsRedirect = new StringBuffer();
		if(recordToValidate.getKode()!=null && !"".equals(recordToValidate.getKode())){
			paramsRedirect.append("&kode=" + recordToValidate.getKode());
		}
		ModelAndView successView = new ModelAndView("redirect:transportdisp_workflow_childwindow_supplier.do?action=doFind" + paramsRedirect.toString());
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	
	/**
	 * Supplier
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_supplier.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindSupplier(@ModelAttribute ("record") JsonTransportDispSupplierContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindSupplier");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_supplier");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_SUPPLIER_URL;
	    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTransportDispSupplierContainer container = this.transportDispChildWindowService.getSupplierContainer(jsonPayload);
		    		if(container!=null){
		    			List<JsonTransportDispSupplierRecord> list = new ArrayList<JsonTransportDispSupplierRecord>();
		    			for(JsonTransportDispSupplierRecord  record : container.getLeverandorer()){
		    				//logger.info("CUSTOMER NO: " + record.getKundnr());
		    				//logger.info("NAME: " + record.getNavn());
		    				list.add(record);
		    			}
		    			model.put(this.DATATABLE_SUPPLIER_LIST, list);
		    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
		    		}
	    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	 * Gebyrkoder
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_gebyrcode.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitGebyrCodes(@ModelAttribute ("record") JsonTransportDispGebyrCodeContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGebyrCodes");
		Map model = new HashMap();
		StringBuffer paramsRedirect = new StringBuffer();
		if(recordToValidate.getKode()!=null && !"".equals(recordToValidate.getKode())){
			paramsRedirect.append("&kode=" + recordToValidate.getKode());
		}
		ModelAndView successView = new ModelAndView("redirect:transportdisp_workflow_childwindow_gebyrcode.do?action=doFind" + paramsRedirect.toString());
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	
	/**
	 * Gebyr
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_gebyrcode.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindGebyrCodes(@ModelAttribute ("record") JsonTransportDispGebyrCodeContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindGebyrCodes");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_gebyrcode");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_GEBYR_CODES_URL;
	    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTransportDispGebyrCodeContainer container = this.transportDispChildWindowService.getGebyrCodeContainer(jsonPayload);
		    		if(container!=null){
		    			List<JsonTransportDispGebyrCodeRecord> list = new ArrayList<JsonTransportDispGebyrCodeRecord>();
		    			for(JsonTransportDispGebyrCodeRecord  record : container.getGebyrKoder()){
		    				list.add(record);
		    			}
		    			model.put(this.DATATABLE_GEBYRCODE_LIST, list);
		    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
		    		}
	    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow_country.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitCountry(@ModelAttribute ("record") JsonTransportDispCodeRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitBilNr");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		ModelAndView successView = new ModelAndView("redirect:transportdisp_workflow_childwindow_country.do?action=doFind" + "&ctype=" + callerType);
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	@RequestMapping(value="transportdisp_workflow_childwindow_country.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindCountry(@ModelAttribute ("record") JsonTransportDispCodeRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindBilNr");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		final String CODE_TYPE_COUNTRY = "2";
		
		String callerType = request.getParameter("ctype");
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow_country");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_CODES_URL;
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&typ=" + CODE_TYPE_COUNTRY;
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTransportDispCodeContainer container = null;
	    			try{ container = this.transportDispDropDownListPopulationService.getCodeContainer(jsonPayload); }
	    			catch(Exception e){ e.printStackTrace(); }
	    			
		    		if(container!=null){
		    			List<JsonTransportDispCodeRecord> list = new ArrayList();
		    			for(JsonTransportDispCodeRecord  record : container.getKodlista()){
		    				//logger.info("todo");
		    				//logger.info("todo");
		    				list.add(record);
		    			}
		    			model.put(this.DATATABLE_COUNTRYCODE_LIST, list);
		    			model.put(TransportDispConstants.DOMAIN_CONTAINER, recordToValidate);
		    			model.put("callerType", callerType);
		    		}
	    			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
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
	 * @return
	 */
	@RequestMapping(value = "transportdisp_workflow_getTrip_cw.do", method = RequestMethod.GET)
	public ModelAndView doTranspDispGetTripCw(HttpSession session, HttpServletRequest request){
		 logger.info("Inside: doTranspDispGetTripCw");
		 ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow");
		 SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		 SearchFilterTransportDispWorkflowTripList tripListSearchFilter = new SearchFilterTransportDispWorkflowTripList();
		 
		 Map model = new HashMap();
		 if(appUser==null){
				return loginView;
		 }else{
			 
			 String applicationUser = appUser.getUser();
			 String avdNr = request.getParameter("tuavd");
			 String tripNr = request.getParameter("tupro");
			 String opd	= request.getParameter("opd");
			 model.put("opd", opd);
			 
			 tripListSearchFilter.setWssavd(avdNr);
			 //prepare the access CGI with RPG back-end
			 String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_URL;
			 String urlRequestParamsKeys = "user=" + applicationUser + "&tuavd=" + avdNr + "&tupro=" + tripNr;
			 logger.info("URL: " + BASE_URL);
			 logger.info("PARAMS: " + urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			 logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
			 if(jsonPayload!=null){
			 	try{
			 		JsonTransportDispWorkflowSpecificTripContainer container = this.transportDispWorkflowSpecificTripService.getContainer(jsonPayload);
					if(container!=null){
						 boolean recordIsNull = true;
						 for(JsonTransportDispWorkflowSpecificTripRecord  record : container.getGetonetrip()){
							recordIsNull = false;
							//put domain object
							model.put(TransportDispConstants.DOMAIN_RECORD, record);	
						 }
						 //Defaults (if applicable)
						 if(recordIsNull){
							JsonTransportDispWorkflowSpecificTripRecord record = new JsonTransportDispWorkflowSpecificTripRecord();
							DateTimeManager dateMgr = new DateTimeManager();
							record.setCenturyYearTurccTuraar(dateMgr.getCurrentYear());
							record.setTurmnd(dateMgr.getCurrentMonth());
							//default values
							record.setTudt(this.dateTimeManager.getCurrentDate_ISO());
							//put domain object
							model.put(TransportDispConstants.DOMAIN_RECORD, record);
						 }
					}
			 	}catch(Exception e){
			 		e.printStackTrace();
			 	}
			 }
			 //drop downs from files
			 this.setDropDownsFromFiles(model);
			 this.setCodeDropDownMgr(appUser, model);
			 //Now get the parent list of workflow trips
			 Collection<JsonTransportDispWorkflowListRecord> outputList = this.fetchWorkflowList(applicationUser, avdNr, session, model);
			 successView.addObject(TransportDispConstants.DOMAIN_LIST,outputList);
			 successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);	
			 successView.addObject("searchFilter", tripListSearchFilter);
		} 
		return successView;
		 
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_childwindow.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterTransportDispWorkflowTripList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Collection<JsonTransportDispWorkflowListRecord> outputList = new ArrayList<JsonTransportDispWorkflowListRecord>();
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("transportdisp_workflow_childwindow");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String opd	= request.getParameter("opd");
		model.put("opd", opd);
		
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
					/*
					JsonTransportDispWorkflowSpecificTripRecord record = new JsonTransportDispWorkflowSpecificTripRecord();
					record.setCenturyYearTurccTuraar(this.dateTimeManager.getCurrentYear());
					record.setTurmnd(this.dateTimeManager.getCurrentMonth());
					//default values
					record.setTudt(this.dateTimeManager.getCurrentDate_ISO());
					model.put(TransportDispConstants.DOMAIN_RECORD, record);
			    	*/
					model.put(TransportDispConstants.DOMAIN_CONTAINER_TRIP_LIST, jsonTransportDispWorkflowListContainer);
					
					successView.addObject("searchFilter", recordToValidate);
					successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
		    		//domain and search filter
					successView.addObject(TransportDispConstants.DOMAIN_LIST,outputList);
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
	 * @param searchFilter
	 * @param appUser
	 * @param request
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
	 * @param appUser
	 * @param avdNr
	 * @param session
	 * @param model
	 * @return
	 */
	private Collection<JsonTransportDispWorkflowListRecord> fetchWorkflowList(String appUser, String avdNr, HttpSession session, Map model ){
		Collection<JsonTransportDispWorkflowListRecord> outputList = new ArrayList<JsonTransportDispWorkflowListRecord>();
		logger.info("Inside: fetchWorkflowList");
		//===========
		//FETCH LIST
		//===========
		//get BASE URL
		final String BASE_LIST_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_LIST_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser);
		if(avdNr!=null && !"".equals(avdNr)){ 
			urlRequestParams.append("&wssavd=" + avdNr); 
		}
		session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_LIST_URL + "==>params: " + urlRequestParams.toString()); 
    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_LIST_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_LIST_URL, urlRequestParams.toString());
		//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonTransportDispWorkflowListContainer jsonTransportDispWorkflowListContainer = this.transportDispWorkflowListService.getWorkflowListContainer(jsonPayload);
    		model.put(TransportDispConstants.DOMAIN_CONTAINER_TRIP_LIST, jsonTransportDispWorkflowListContainer);
			
    		//-----------------------------------------------------------
    		//now filter the topic list with the search filter (if applicable)
    		//-----------------------------------------------------------
    		outputList = jsonTransportDispWorkflowListContainer.getWrktriplist();
    		
    		//put list in view
    		if(outputList!=null){
				session.setAttribute(session.getId() + TransportDispConstants.SESSION_LIST, outputList);
			}
    		//successView.addObject(TransportDispConstants.DOMAIN_LIST,outputList);
    		//successView.addObject("searchFilter", searchFilter);
    		logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    	}
    	//put domain object in view
		//successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
    	return outputList;
		
	}
	/**
	 * 
	 * @param model
	 */
	private void setDropDownsFromFiles(Map<String, Object> model){
		model.put(TransportDispConstants.RESOURCE_MODEL_KEY_YEAR_LIST, this.transportDispDropDownListPopulationService.getYearList());
		model.put(TransportDispConstants.RESOURCE_MODEL_KEY_MONTH_LIST, this.transportDispDropDownListPopulationService.getMonthList());
		model.put(TransportDispConstants.RESOURCE_MODEL_KEY_CURRENCY_CODE_LIST, this.transportDispDropDownListPopulationService.getCurrencyList());
		
	}
	
	
	
	/**
	 * 
	 * @param fileName
	 * @param appUser
	 * @return
	 */
	private JsonTransportDispFileUploadValidationContainer validateFileUpload(String fileName, SystemaWebUser appUser){
		JsonTransportDispFileUploadValidationContainer uploadValidationContainer = null;
		//prepare the access CGI with RPG back-end
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_UPLOAD_FILE_VALIDATION_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&wsdokn=" + fileName;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			uploadValidationContainer = this.transportDispChildWindowService.getFileUploadValidationContainer(jsonPayload);
			logger.info(uploadValidationContainer.getErrMsg());
		}
		return uploadValidationContainer;
	}
	/**
	 * 
	 * @param uploadValidationContainer
	 * @param fileName
	 * @param appUser
	 * @return
	 */
	private JsonTransportDispFileUploadValidationContainer saveFileUpload(JsonTransportDispFileUploadValidationContainer uploadValidationContainer, String fileName, SystemaWebUser appUser){
		//prepare the access CGI with RPG back-end
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_UPLOAD_FILE_AFTER_VALIDATION_APPROVAL_URL;
		String absoluteFileName = uploadValidationContainer.getTmpdir() + fileName;
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		//Either TUR or AVD/OPD (order level)... Depending on the caller (Tur-level OR order-level)
		if(uploadValidationContainer.getWstur()!=null && !"".equals(uploadValidationContainer.getWstur())){
			urlRequestParamsKeys.append("&wstur=" + uploadValidationContainer.getWstur());
		}else{
			if(uploadValidationContainer.getWsavd()!=null && !"".equals(uploadValidationContainer.getWsavd())){
				urlRequestParamsKeys.append("&wsavd=" + uploadValidationContainer.getWsavd());
			}
			if(uploadValidationContainer.getWsopd()!=null && !"".equals(uploadValidationContainer.getWsopd())){
				urlRequestParamsKeys.append("&wsopd=" + uploadValidationContainer.getWsopd());
			}
		}
		urlRequestParamsKeys.append("&wstype=" + uploadValidationContainer.getWstype());
		urlRequestParamsKeys.append("&wsdokn=" + absoluteFileName);
		
		
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			uploadValidationContainer = this.transportDispChildWindowService.getFileUploadValidationContainer(jsonPayload);
			logger.info(uploadValidationContainer.getErrMsg());
		}
		return uploadValidationContainer;
	}
	
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTransportDispAvdRecord searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=A");
		
		if(searchFilter.getAvd()!=null && !"".equals(searchFilter.getAvd())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + searchFilter.getAvd());
		}
		if(searchFilter.getNamn()!=null && !"".equals(searchFilter.getNamn())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "namn=" + searchFilter.getNamn());
		}
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTransportDispBilNrContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getSokbnr()!=null && !"".equals(searchFilter.getSokbnr())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sokbnr=" + searchFilter.getSokbnr());
		}
		if(searchFilter.getSoktnr()!=null && !"".equals(searchFilter.getSoktnr())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soktnr=" + searchFilter.getSoktnr());
		}
		if(searchFilter.getSokut()!=null && !"".equals(searchFilter.getSokut())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sokut=" + searchFilter.getSokut());
		}
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTransportDispBilCodeContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getSoknvn()!=null && !"".equals(searchFilter.getSoknvn())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soknvn=" + searchFilter.getSoknvn());
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTransportDispTranspCarrierContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getSoktnr()!=null && !"".equals(searchFilter.getSoktnr())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soktnr=" + searchFilter.getSoktnr());
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "getval=J"); //to fore exact match
		}
		if(searchFilter.getSoknvn()!=null && !"".equals(searchFilter.getSoknvn())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soknvn=" + searchFilter.getSoknvn());
		}
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTransportDispDriverContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getSoksja()!=null && !"".equals(searchFilter.getSoksja())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soksja=" + searchFilter.getSoksja());
		}
		if(searchFilter.getSoksjn()!=null && !"".equals(searchFilter.getSoksjn())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soksjn=" + searchFilter.getSoksjn());
		}
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * Customer
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTransportDispCustomerContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getSokknr()!=null && !"".equals(searchFilter.getSokknr())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sokknr=" + searchFilter.getSokknr());
		}
		if(searchFilter.getSoknvn()!=null && !"".equals(searchFilter.getSoknvn())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soknvn=" + searchFilter.getSoknvn());
		}
		if(searchFilter.getKunpnsted()!=null && !"".equals(searchFilter.getKunpnsted())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kunpnsted=" + searchFilter.getKunpnsted());
		}
		if(searchFilter.getWsvarnv()!=null && !"".equals(searchFilter.getWsvarnv())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsvarnv=" + searchFilter.getWsvarnv());
		}
		if(searchFilter.getMaxv()!=null && !"".equals(searchFilter.getMaxv())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "maxv=" + searchFilter.getMaxv());
		}
		
		return urlRequestParamsKeys.toString();
	}

	/**
	 * Load/Unload places
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTransportDispLoadUnloadPlacesContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getSoknvn()!=null && !"".equals(searchFilter.getSoknvn())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soknvn=" + searchFilter.getSoknvn());
		}
		if(searchFilter.getSokkod()!=null && !"".equals(searchFilter.getSokkod())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sokkod=" + searchFilter.getSokkod());
		}
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * Dangerous goods
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTransportDispDangerousGoodsContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getUnnr()!=null && !"".equals(searchFilter.getUnnr())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "unnr=" + searchFilter.getUnnr());
		}
		//user=JOVO&unnr=1950=&embg=&indx=&getval=&fullinfo=J
		urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fullinfo=J"); //always the max. nr of columns (as default)
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTransportDispPackingCodesContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getKode()!=null && !"".equals(searchFilter.getKode())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode=" + searchFilter.getKode());
		}
		if(searchFilter.getTekst()!=null && !"".equals(searchFilter.getTekst())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tekst=" + searchFilter.getTekst());
		}
		//urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "getval=J");
		urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fullinfo=J"); //always the max. nr of columns (as default)		

		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTransportDispTollstedCodesContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getKode()!=null && !"".equals(searchFilter.getKode())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode=" + searchFilter.getKode());
		}
		if(searchFilter.getTekst()!=null && !"".equals(searchFilter.getTekst())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tekst=" + searchFilter.getTekst());
		}
		//urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "getval=J");
		urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fullinfo=J"); //always the max. nr of columns (as default)		

		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * Customer
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTransportDispSupplierContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getKode()!=null && !"".equals(searchFilter.getKode())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode=" + searchFilter.getKode());
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "getval=J"); //to fore exact match
		}
		if(searchFilter.getTekst()!=null && !"".equals(searchFilter.getTekst())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tekst=" + searchFilter.getTekst());
		}
		urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fullinfo=J");
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * Gebyr codes
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTransportDispGebyrCodeContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getKode()!=null && !"".equals(searchFilter.getKode())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode=" + searchFilter.getKode());
		}
		if(searchFilter.getTekst()!=null && !"".equals(searchFilter.getTekst())){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tekst=" + searchFilter.getTekst());
		}
		//urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "getval=N");
		urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fullinfo=J");
		
		
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
	
	
	
	//---------------
	//Trip Services
	//---------------
	@Qualifier 
	private TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService;
	@Autowired
	@Required	
	public void setTransportDispWorkflowSpecificTripService(TransportDispWorkflowSpecificTripService value){this.transportDispWorkflowSpecificTripService = value;}
	public TransportDispWorkflowSpecificTripService getTransportDispWorkflowSpecificTripService(){ return this.transportDispWorkflowSpecificTripService; }
	

	@Qualifier ("transportDispWorkflowListService")
	private TransportDispWorkflowListService transportDispWorkflowListService;
	@Autowired
	@Required
	public void setTransportDispWorkflowListService (TransportDispWorkflowListService value){ this.transportDispWorkflowListService = value; }
	public TransportDispWorkflowListService getTransportDispWorkflowListService(){ return this.transportDispWorkflowListService; }
	
	@Qualifier ("transportDispDropDownListPopulationService")
	private TransportDispDropDownListPopulationService transportDispDropDownListPopulationService;
	@Autowired
	public void setTransportDispDropDownListPopulationService (TransportDispDropDownListPopulationService value){ this.transportDispDropDownListPopulationService=value; }
	public TransportDispDropDownListPopulationService getTransportDispDropDownListPopulationService(){return this.transportDispDropDownListPopulationService;}
	
	
}

