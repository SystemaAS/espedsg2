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
import no.systema.main.model.SystemaWebUser;

//Trans.Disp
import no.systema.transportdisp.service.TransportDispChildWindowService;
import no.systema.transportdisp.service.TransportDispWorkflowBudgetService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;
import no.systema.transportdisp.mapper.url.request.UrlRequestParameterMapper;

import no.systema.transportdisp.util.manager.CodeDropDownMgr;
import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;
import no.systema.transportdisp.validator.TransportDispWorkflowSpecificBudgetValidator;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.budget.JsonTransportDispWorkflowSpecificBudgetContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.budget.JsonTransportDispWorkflowSpecificBudgetRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeRecord;

import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;

/**
 * Transport disponering Controller - Budget window
 * 
 * @author oscardelatorre
 * @date Oct 12, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TransportDispWorkflowBudgetController {
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private static final Logger logger = Logger.getLogger(TransportDispWorkflowBudgetController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	private ControllerAjaxCommonFunctionsMgr controllerAjaxCommonFunctionsMgr;
	
	
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
	@RequestMapping(value="transportdisp_workflow_budget.do", method={RequestMethod.GET} )
	public ModelAndView doInit(@ModelAttribute ("record") JsonTransportDispWorkflowSpecificBudgetRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		String parentTrip = request.getParameter("tur");
		String avd = request.getParameter("avd"); 
		String opd = request.getParameter("opd"); 
		
		
		logger.info("ACTION: " + action);
		logger.info("parentTrip:" + parentTrip);
		//ModelAndView successView = new ModelAndView("transportdisp_mainorder_invoice");
		ModelAndView successView = new ModelAndView("transportdisp_workflow_budget");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_BUDGET_URL;
			//add URL-parameters
    		StringBuffer urlRequestParams = new StringBuffer();
    		urlRequestParams.append("user=" + appUser.getUser());
    		urlRequestParams.append("&avd=" + avd); 
    		urlRequestParams.append("&opd=" + opd);
    		if(parentTrip!=null && !"".equals(parentTrip)){
    			urlRequestParams.append("&tur=" + parentTrip);
    		}
    		urlRequestParams.append("&type=A");
    		
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParams.toString());
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    		logger.debug(this.jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    		if(jsonPayload!=null){
    			JsonTransportDispWorkflowSpecificBudgetContainer container = this.transportDispWorkflowBudgetService.getContainer(jsonPayload);
    			if(container!=null){
    				this.setDomainObjectsInView(model, container, session);
    			}	
    		}
    		//get the trip header since we need some values in JSP
    		if(parentTrip!=null && !"".equals(parentTrip)){
				this.controllerAjaxCommonFunctionsMgr = new ControllerAjaxCommonFunctionsMgr(this.urlCgiProxyService, this.transportDispWorkflowSpecificTripService);
				JsonTransportDispWorkflowSpecificTripContainer specificTripContainer = this.controllerAjaxCommonFunctionsMgr.fetchTripHeading(appUser.getUser(), avd, parentTrip);
				if(specificTripContainer!=null && specificTripContainer.getGetonetrip()!=null){
					for(JsonTransportDispWorkflowSpecificTripRecord record: specificTripContainer.getGetonetrip()){
						//logger.info(record.getTunat());
						model.put("recordSpecificTrip", record);
					}
				}
			}
    		
			this.setGebyrMomsCode(appUser, recordToValidate);
			//populate drop downs
			this.setCodeDropDownMgr(appUser, model);
			this.setDropDownsFromFiles(model);
	    	model.put("record", recordToValidate);
	    	model.put("parentTrip", parentTrip);
	    	//
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
	@RequestMapping(value="transportdisp_workflow_budget_edit.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditBudget(@ModelAttribute ("record") JsonTransportDispWorkflowSpecificBudgetRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		String avd = request.getParameter("avd");
		String opd = request.getParameter("opd");
		String parentTrip = request.getParameter("hepro");
		String tur = request.getParameter("tur"); //for delete
		
		//this is the key for all update (U/D)
		String lineId = recordToValidate.getBubnr();
		logger.info("isModeUpdate:" + recordToValidate.getIsModeUpdate());
		
		//Params
		StringBuffer params = new StringBuffer();
		params.append("&bnr=" + lineId);
		if(avd!=null && !"".equals(avd)){ params.append("&avd=" + avd); }
		if(opd!=null && !"".equals(opd)){ params.append("&opd=" + opd); }
		if(parentTrip!=null && !"".equals(parentTrip)){ 	
			params.append("&tur=" + parentTrip); 
		}else if(tur!=null && !"".equals(tur)){ 
			params.append("&tur=" + tur); //delete on tur
		}
		
		logger.info("ACTION: " + action);
		ModelAndView successView = new ModelAndView("redirect:transportdisp_workflow_budget.do?action=doFind" + params.toString() );
		ModelAndView errorView = new ModelAndView("transportdisp_workflow_budget");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			if(TransportDispConstants.ACTION_UPDATE.equals(action)){
				logger.info("[INFO] doUpdate action ...");
				TransportDispWorkflowSpecificBudgetValidator validator = new TransportDispWorkflowSpecificBudgetValidator();
				//Fetch some validation conditions
				this.setGebyrMomsCode(appUser, recordToValidate);
				//Validate
				validator.validate(recordToValidate, bindingResult);
				//check for ERRORS
				if(bindingResult.hasErrors()){
					logger.info("[ERROR Validation] Record does not validate)");
			    	logger.info("[INFO Rekvnr] " + lineId);
			    	//this.populateAspectsOnBackendError(appUser, "ERROR-?", recordToValidate, model, parentTrip, session);
			    	this.setGebyrMomsCode(appUser, recordToValidate);
					
			    	//populate drop downs
					this.setCodeDropDownMgr(appUser, model);
					this.setDropDownsFromFiles(model);
					model.put(TransportDispConstants.DOMAIN_LIST, session.getAttribute(session.getId() + TransportDispConstants.DOMAIN_LIST));
					model.put(TransportDispConstants.DOMAIN_CONTAINER, session.getAttribute(session.getId() + TransportDispConstants.DOMAIN_CONTAINER));
					model.put("record", recordToValidate);
			    	model.put("parentTrip", parentTrip);
			    	
					errorView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
			    	
			    	return errorView;
			    	
				}else{
					//adjust fields
					recordToValidate.setBupCc(this.getCentury2digits(recordToValidate.getBupAr()));
					recordToValidate.setBupAr(this.getYear2digits(recordToValidate.getBupAr()));
					
					String MODE = "U";
					if(recordToValidate.getIsModeUpdate()!=null && "true".equalsIgnoreCase(recordToValidate.getIsModeUpdate())){
						//UPDATE
						logger.info("[INFO] UPDATE line nr: " + lineId + " start process... ");
					}else{
						//CREATE NEW
						MODE = "A";
						logger.info("[INFO] CREATE new line in process...");
					}
					
					//-------------------------------
					//Execute back-end Update/Create
					//-------------------------------
					JsonTransportDispWorkflowSpecificBudgetContainer container = this.executeUpdateLine(appUser, recordToValidate, MODE,avd,opd,parentTrip);
					if(container!=null){
	    				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	    					logger.info("[ERROR] Back-end Error: " + container.getErrMsg());
	    					this.populateAspectsOnBackendError(appUser, container.getErrMsg(), recordToValidate, model, parentTrip, session);
	    			    	errorView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    					return errorView;
	    				}else{
	    					//succefully done!
	    		    		logger.info("[INFO] Valid Update -- Record successfully updated, OK ");
	    				}
	    			}
					logger.info("[INFO] UPDATE line nr: " + lineId + " end process. ");
				}
				
			}else if(TransportDispConstants.ACTION_DELETE.equals(action)){
				String DELETE_MODE = "D";
				JsonTransportDispWorkflowSpecificBudgetContainer container = this.executeUpdateLine(appUser, recordToValidate, DELETE_MODE, avd, opd, parentTrip);
				if(container!=null){
    				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
    					this.populateAspectsOnBackendError(appUser, container.getErrMsg(), recordToValidate, model, tur, session);
    			    	errorView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
    					return errorView;
    				}else{
    					//Delete succefully done!
    		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
    				}
    			}
				
			}
			
	    	return successView;
		}
	}
	
	/**
	 * Update line ( (A)dd, (U)pdate, (D)elete )
	 * @param appUser
	 * @param recordToValidate
	 * @param mode
	 * @param avd
	 * @param opd
	 * @param parentTrip
	 * @return
	 */
	private JsonTransportDispWorkflowSpecificBudgetContainer executeUpdateLine(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificBudgetRecord recordToValidate, String mode,
																				String avd, String opd, String parentTrip){
		JsonTransportDispWorkflowSpecificBudgetContainer retval = null;
		
		logger.info("[INFO] EXECUTE Update(D/A/U) line nr:" + recordToValidate.getBubnr() + " start process... ");
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_BUDGET_URL;
    	//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser()); 
		if("U".equals(mode) || "D".equals(mode)){
			urlRequestParams.append("&bnr=" + recordToValidate.getBubnr());
		}else if("A".equals(mode)){
			if(parentTrip!=null && !"".equals(parentTrip)){
				urlRequestParams.append("&tur=" + parentTrip);
			}else{
				urlRequestParams.append("&avd=" + avd);
				urlRequestParams.append("&opd=" + opd);
			}
		}
		urlRequestParams.append("&mode=" + mode);
		//We need to fill out the record in case Update/Create
		if(!"D".equals(mode)){
			 String urlRequestParamsRecord = this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
			 urlRequestParams.append(urlRequestParamsRecord);
		}
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	
		if(jsonPayload!=null){
			JsonTransportDispWorkflowSpecificBudgetContainer container = this.transportDispWorkflowBudgetService.getContainer(jsonPayload);
			retval = container;
			if(container.getErrMsg()!=null){
				logger.info(container.getErrMsg());
			}
		}
		return retval;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param errorMessage
	 * @param recordToValidate
	 * @param model
	 * @param parentTrip
	 * @param session
	 */
	private void populateAspectsOnBackendError(SystemaWebUser appUser, String errorMessage, JsonTransportDispWorkflowSpecificBudgetRecord recordToValidate, Map model, String parentTrip, HttpSession session ){
		model.put(TransportDispConstants.ASPECT_ERROR_MESSAGE, "Linenr:[" + recordToValidate.getBubnr() + "] " +  errorMessage);
		//populate drop downs
		this.setCodeDropDownMgr(appUser, model);
		this.setDropDownsFromFiles(model);
    	//return objects on validation errors
    	model.put(TransportDispConstants.DOMAIN_CONTAINER, session.getAttribute(session.getId() + TransportDispConstants.DOMAIN_CONTAINER));
    	model.put(TransportDispConstants.DOMAIN_LIST, session.getAttribute(session.getId() + TransportDispConstants.DOMAIN_LIST));
    	model.put("record", recordToValidate);
    	model.put("parentTrip", parentTrip);
	}
	
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 */
	private void setGebyrMomsCode(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificBudgetRecord recordToValidate){
		//prepare the access CGI with RPG back-end
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_GEBYR_CODES_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&kode=" + recordToValidate.getBuvk() + "&getval=J";
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
				for(JsonTransportDispGebyrCodeRecord  record : container.getGebyrKoder()){
					recordToValidate.setIntMVAx(record.getIntMVAx());
					recordToValidate.setKosMVAx(record.getKosMVAx());
				}
			}
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.transportDispDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		this.codeDropDownMgr.populateHtmlDropDownsFromJsonStringGebyrCodes(this.urlCgiProxyService, this.transportDispDropDownListPopulationService, 
				model, appUser);
	}
	
	/**
	 * 
	 * @param model
	 */
	private void setDropDownsFromFiles(Map<String, Object> model){
		model.put(TransportDispConstants.RESOURCE_MODEL_KEY_CURRENCY_CODE_LIST, this.transportDispDropDownListPopulationService.getCurrencyList());
	}
	
	/**
	 * 
	 * @param model
	 * @param container
	 * @param session
	 */
	private void setDomainObjectsInView(Map model, JsonTransportDispWorkflowSpecificBudgetContainer container, HttpSession session){
		List<JsonTransportDispWorkflowSpecificBudgetRecord> list = new ArrayList<JsonTransportDispWorkflowSpecificBudgetRecord>();
		//could be two options
		if(container.getBudgetLines()!=null){
			for (JsonTransportDispWorkflowSpecificBudgetRecord record: container.getBudgetLines()){
				//DEBUG logger.info("A RECORD:" + record.getFask());
				list.add(record);
			}
		}else if (container.getBudgetLineUpdate()!=null){
			for (JsonTransportDispWorkflowSpecificBudgetRecord record: container.getBudgetLines()){
				//DEBUG logger.info("A RECORD:" + record.getFask());
				list.add(record);
			}
		}
		//[1] Sort the list after: fask(code part (S,K,X,etc) and fafakt(inv.nr)
		//Collections.sort(list, new JsonTransportDispWorkflowSpecificOrderInvoiceRecord.OrderByCodeAndInvoiceNr());
		//[1.1] Group the list in sub-lists of related records
		//Map listGroupsMap = this.setListGroups(list);
		//[2] Set totals in each group
		//list = this.getListWithTotals(listGroupsMap);
		
		//always keep track of the total nr of item lines
		String nrOfItems = String.valueOf(list.size());
		container.setTotalNumberOfItemLines(nrOfItems);
		
		logger.info("putting on model...");
		model.put(TransportDispConstants.DOMAIN_CONTAINER, container);
		model.put(TransportDispConstants.DOMAIN_LIST, list);
		//put the objects in session ONLY for the validation errors routine in an UPDATE. Otherwise we do have to retrive th
		session.setAttribute(session.getId() + TransportDispConstants.DOMAIN_CONTAINER, container);
		session.setAttribute(session.getId() + TransportDispConstants.DOMAIN_LIST, list);
		
	}
	
	
	/**
	 * 
	 * @param ccyy
	 * @return
	 */
	private String getCentury2digits(String ccyy){
		String retval = ccyy;
		if(ccyy!=null && !"".equals(ccyy)){
		  if(ccyy.length()==4){
			  retval = ccyy.substring(0,2);
		  }
		}
		return retval;
	}
	/**
	 * 
	 * @param ccyy
	 * @return
	 */
	private String getYear2digits(String ccyy){
		String retval = ccyy;
		if(ccyy!=null && !"".equals(ccyy)){
		  if(ccyy.length()==4){
			  retval = ccyy.substring(2);
		  }
		}
		return retval;
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
	
	@Qualifier ("transportDispWorkflowBudgetService")
	private TransportDispWorkflowBudgetService transportDispWorkflowBudgetService;
	@Autowired
	public void setTransportDispWorkflowBudgetService (TransportDispWorkflowBudgetService value){ this.transportDispWorkflowBudgetService=value; }
	public TransportDispWorkflowBudgetService getTransportDispWorkflowBudgetService(){return this.transportDispWorkflowBudgetService;}
	
	@Qualifier 
	private TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService;
	@Autowired
	@Required	
	public void setTransportDispWorkflowSpecificTripService(TransportDispWorkflowSpecificTripService value){this.transportDispWorkflowSpecificTripService = value;}
	public TransportDispWorkflowSpecificTripService getTransportDispWorkflowSpecificTripService(){ return this.transportDispWorkflowSpecificTripService; }
	
	
}

