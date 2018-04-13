package no.systema.transportdisp.controller;

import java.lang.reflect.Field;
import java.util.*;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.math.RoundingMode;
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
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;


//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.general.notisblock.NotisblockService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.url.store.MainUrlDataStore;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.MessageNoteManager;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockContainer;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesRecord;
import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;
//TRANSPDISP
import no.systema.transportdisp.util.RpgReturnResponseHandler;
import no.systema.transportdisp.util.TransportDispDateTimeFormatter;
import no.systema.transportdisp.util.TransportDispPercentageFormatter;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.util.manager.CodeDropDownMgr;
import no.systema.transportdisp.util.manager.OppdragTypeParametersMgr;
import no.systema.transportdisp.service.TransportDispChildWindowService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificOrderService;
import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;
import no.systema.transportdisp.service.TransportDispPostUpdateService;
import no.systema.transportdisp.mapper.url.request.UrlRequestParameterMapper;

import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispAvdContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispAvdRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.avdsignature.JsonTransportDispSignatureContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.avdsignature.JsonTransportDispSignatureRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowPostUpdateContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeParametersContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeParametersRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderMessageNoteContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderMessageNoteRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.validationbackend.JsonTransportDispWorkflowSpecificOrderValidationBackendContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderArchivedDocsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderArchivedDocsRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingRecord;



import no.systema.transportdisp.validator.TransportDispWorkflowSpecificOrderValidator;
import no.systema.transportdisp.validator.backend.SpecificOrderValidatorBackend;
import no.systema.transportdisp.util.manager.java.reflect.ReflectionSpecificOrderHeaderMgr;

/**
 * TransportDisp Main Order Controller 
 * 
 * @author oscardelatorre
 * @date Apr 24, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TransportDispMainOrderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1000);
	private static Logger logger = Logger.getLogger(TransportDispMainOrderController.class.getName());
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private SpecificOrderValidatorBackend specificOrderValidatorBackend = null;
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private MessageNoteManager messageNoteMgr = new MessageNoteManager();
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	private DateTimeManager dateTimeManager = new DateTimeManager();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private TransportDispPercentageFormatter percentageFormatter = new TransportDispPercentageFormatter();
	private ReflectionSpecificOrderHeaderMgr reflectionSpecificOrderHeaderMgr = new ReflectionSpecificOrderHeaderMgr();
	private StringManager strMgr = new StringManager();
	private String MESSAGE_NOTE_CONSIGNEE = "R";
	private String MESSAGE_NOTE_CARRIER = "G";
	private String MESSAGE_NOTE_INTERNAL = "b";
	
	
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
	@RequestMapping(value="transportdisp_mainorder.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doGetOrder(@ModelAttribute ("record") JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		this.context = TdsAppContext.getApplicationContext();
		logger.info("#AVD:" + recordToValidate.getHeavd());
		logger.info("#OPD:" + recordToValidate.getHeopd());
		logger.info("#TUR:" + recordToValidate.getHepro());
		String parentTrip = recordToValidate.getHepro();
		String orderLineTotalsString = request.getParameter("oltotals");
		logger.info("ORDER TOTALS STRING:" +  orderLineTotalsString);
		
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("transportdisp_mainorder");
		ModelAndView errorView = new ModelAndView("transportdisp_mainorderlist");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//Update the order with the new totals(if applicable). Usually when creating new line with AJAX)
			if(orderLineTotalsString!=null && !"".equals(orderLineTotalsString)){
				//[1] pre-Fetch the whole record first since we do not have all values in place (when calling from within jquery in a GET)
				JsonTransportDispWorkflowSpecificOrderRecord preRecord = null;
				final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_URL;
	    		//add URL-parameters
	    		StringBuffer urlRequestParams = new StringBuffer();
	    		urlRequestParams.append("user=" + appUser.getUser());urlRequestParams.append("&heavd=" + recordToValidate.getHeavd());urlRequestParams.append("&heopd=" + recordToValidate.getHeopd());
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
			    if(jsonPayload!=null){
	    			JsonTransportDispWorkflowSpecificOrderContainer container = this.transportDispWorkflowSpecificOrderService.getContainer(jsonPayload);
	    			for (JsonTransportDispWorkflowSpecificOrderRecord record: container.getDspoppdrag()){
	    				preRecord = record;
	    			}
	    		}
			    //[2] Edit the totals and execute the update
			    this.updateOrderLineTotalsPreRecord(preRecord, orderLineTotalsString);
			    this.updateOrderLineTotalsAfterLineUpdate(appUser, session, preRecord, model);
			}
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			if(recordToValidate.getHeavd()!=null && !"".equals(recordToValidate.getHeavd()) && 
				recordToValidate.getHeopd()!=null && !"".equals(recordToValidate.getHeopd())){
		    	final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_URL;
	    		//add URL-parameters
	    		StringBuffer urlRequestParams = new StringBuffer();
	    		urlRequestParams.append("user=" + appUser.getUser());
	    		urlRequestParams.append("&heavd=" + recordToValidate.getHeavd());
	    		urlRequestParams.append("&heopd=" + recordToValidate.getHeopd());
	    		
	    		session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
		    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
			    String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
			    //Debug --> 
			  	//logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
			  	logger.debug(jsonPayload);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    		if(jsonPayload!=null){
	    			JsonTransportDispWorkflowSpecificOrderContainer container = this.transportDispWorkflowSpecificOrderService.getContainer(jsonPayload);
    				for (JsonTransportDispWorkflowSpecificOrderRecord record: container.getDspoppdrag()){
	    				//adjust percentage
	    				//record.setHevalp(percentageFormatter.adjustPercentageNotationToFrontEndOnSpecificOrder(record.getHevalp()));
	    				//populate all message notes
	    				this.populateMessageNotes(appUser, record);
	    				//populate fraktbrev lines
	    				this.populateFraktbrev(appUser, record);
	    				//populate archive docs
	    				this.populateArchiveDocs(appUser, record);
	    				//populate track and trace
	    				this.populateTrackAndTrace(appUser, record);
	    				//set domain objects
	    				this.setDomainObjectsInView(model, record);
	    				//set in session (used in invoice child)
	    				session.setAttribute(TransportDispConstants.DOMAIN_RECORD_ORDER_TRANSPORT_DISP, record);
	    			}
	    		}
			}else{ //prepare for create new
				//Default values (always when doFind)
				logger.info("New Order record");
				DateTimeManager dateMgr = new DateTimeManager();
				recordToValidate.setWsetdd(dateMgr.getCurrentDate_ISO());
				recordToValidate.setHebodt(dateMgr.getCurrentDate_ISO());
				//set opprdag type parameters (when applicable)
				OppdragTypeParametersMgr oppdragTypeParametersMgr = new OppdragTypeParametersMgr(this.urlCgiProxyService, this.transportDispDropDownListPopulationService);
				oppdragTypeParametersMgr.fetchOppdragTypeParametersAttributes(appUser, recordToValidate);
				recordToValidate.setHeot(recordToValidate.getOppdragTypeParameters().getOpdTyp());
				//set avd default country codes
				this.setAvdDefaultCountryCodes(appUser, recordToValidate);
				//populate
				this.populateFraktbrev( appUser, recordToValidate);
			    this.setDomainObjectsInView(model, recordToValidate);
			}
			
			//populate drop downs
			this.setCodeDropDownMgr(appUser, model);
			this.setDropDownsFromFiles(model);
			this.setDropDownsFromJsonString(model, appUser);
    		//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
    		model.put("parentTrip", parentTrip);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
		    
			return successView;
		}
	}
	
	/**
	 * The method fills in the required domain objects in an error scenario (back to the JSP-view)
	 * @param appUser
	 * @param recordToValidate
	 * @param model
	 * @param parentTrip
	 */
	private void setDomainObjectsOnValidationErrors(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, Map model, String parentTrip ){
		this.setDomainObjectsInView(model, recordToValidate);
		this.setCodeDropDownMgr(appUser, model);
		this.setDropDownsFromFiles(model);
		this.setDropDownsFromJsonString(model, appUser);
		model.put("parentTrip", parentTrip);
		
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_mainorder_update.do",  method={RequestMethod.POST} )
	public ModelAndView doUpdateOrder(@ModelAttribute ("record") JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		this.specificOrderValidatorBackend = new SpecificOrderValidatorBackend(this.urlCgiProxyService, this.transportDispWorkflowSpecificOrderService);
		boolean isCreateNewTransaction = false;
		
		logger.info("#AVD:" + recordToValidate.getHeavd());
		logger.info("#OPD:" + recordToValidate.getHeopd());
		logger.info("#TUR:" + recordToValidate.getHepro());
		logger.info("#TotalNrOfLines:" + recordToValidate.getTotalNumberOfLines());
		
		String parentTrip = recordToValidate.getHepro();
		//fallback (usually when validating a create new order scenario
		if("".equals(parentTrip) || parentTrip == null){ 
			parentTrip = request.getParameter("parentTrip");
			logger.info("PARENT TRIP:" + parentTrip);
		}
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView returnView = new ModelAndView("transportdisp_mainorder");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//------------------------
			//Validation [1] Front-end
			//------------------------
			this.setPostalCodesValidationRequirements(appUser.getUser(), recordToValidate);
			OppdragTypeParametersMgr oppdragTypeParametersMgr = new OppdragTypeParametersMgr(this.urlCgiProxyService, this.transportDispDropDownListPopulationService);
			oppdragTypeParametersMgr.fetchOppdragTypeParametersAttributes(appUser, recordToValidate);
			
			TransportDispWorkflowSpecificOrderValidator validator = new TransportDispWorkflowSpecificOrderValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			//adjust some fields before big-mamma update
			this.adjustFields(recordToValidate);
			//adjust percentage
			recordToValidate.setHevalp(percentageFormatter.adjustPercentageNotationToBackEndOnSpecificOrder(recordToValidate.getHevalp()));
			//populate all order lines with end-user input in order to validate that at least one line exists.
			this.populateOrderLineRecordsWithUserInput(request, recordToValidate);
			//validate
			validator.validate(recordToValidate, bindingResult);
		    
			//check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] form filter does not validate)");
	    		//populate all message notes
				//this.populateMessageNotes(appUser, recordToValidate); (N/A)
	    		//restore percentage GUI-formatted
	    		//recordToValidate.setHevalp(percentageFormatter.adjustPercentageNotationToFrontEndOnSpecificOrder(recordToValidate.getHevalp()));
	    		logger.info("FFAVD:" + recordToValidate.getFfavd());
	    		//set domain objects
	    		this.setDomainObjectsOnValidationErrors(appUser, recordToValidate, model, parentTrip);
				returnView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
				return returnView;
	    			
		    }else{
		    	//-----------------------------------------------------
				//Validation [2] Back-end ORDER-HEADER
		    	//Note: only if it validates we proceed to the UPDATE
				//-----------------------------------------------------
		    	//[2.1] Execute back-end validation for order header
		    	this.specificOrderValidatorBackend.validateOrder(appUser, recordToValidate, session, request);
		    	//[2.2] This next call updates the recordToValidate values (either there is an error or not, depending on what the back-end sends in its JSON-return values)
		    	//Note:Sometimes the back-end could send updated values (without failing on validation) and then we must update them right here
		    	this.reflectionSpecificOrderHeaderMgr.updateOriginalAttributesOnTargetRecord(recordToValidate, this.specificOrderValidatorBackend.getValidationOutputRecord());
		    	//logger.info("INITTTTTT");
		    	//At this point we now have a recordToValidate updated with all fields (JSON) returned by the validator
		    	JsonTransportDispWorkflowSpecificOrderValidationBackendContainer validationOutputContainer = new JsonTransportDispWorkflowSpecificOrderValidationBackendContainer();
		    	//Check for error on back-end
		    	if(this.specificOrderValidatorBackend.getValidationOutputErrMsgList()!=null && this.specificOrderValidatorBackend.getValidationOutputErrMsgList().size()>0){
		    		validationOutputContainer = this.specificOrderValidatorBackend.getValidationOutputContainer();
		    		//ERRORs at the back-end. Abort everything and return to the end-user with the clear error messages
		    		logger.info("VALIDATION BACK-END ERROR (ORDER)");
		    		//logger.info(" size:" + validationOutputContainer.getErrMsgListFromValidationBackend().size());
		    		model.put(TransportDispConstants.DOMAIN_CONTAINER_VALIDATION_BACKEND, validationOutputContainer);
		    		//restore percentage GUI-formatted
		    		//recordToValidate.setHevalp(percentageFormatter.adjustPercentageNotationToFrontEndOnSpecificOrder(recordToValidate.getHevalp()));
				
		    		//set domain objects
		    		this.setDomainObjectsOnValidationErrors(appUser, recordToValidate, model, parentTrip);
					returnView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
					return returnView;
					
		    	}else{
		    		//-----------------------------------------------------
					//Validation [3] Back-end FRISOKVEI
			    	//Note: only if it validates we proceed to the UPDATE
					//-----------------------------------------------------
		    		this.specificOrderValidatorBackend.validateFriSokvei(appUser, recordToValidate.getHeavd(), recordToValidate.getHeopd());
		    		if (strMgr.isNotNull(this.specificOrderValidatorBackend.getValidationOutputContainer().getErrMsg()) ){
		    			//ERRORs at the frisokvei back-end. Abort everything and return to the end-user with the clear error messages
			    		logger.info("VALIDATION BACK-END ERROR (ORDER - FRISOKVEI)");
			    		//logger.info(" size:" + validationOutputContainer.getErrMsgListFromValidationBackend().size());
			    		model.put(TransportDispConstants.DOMAIN_CONTAINER_VALIDATION_BACKEND, this.specificOrderValidatorBackend.getValidationOutputContainer());
			    		//restore percentage GUI-formatted
			    		//recordToValidate.setHevalp(percentageFormatter.adjustPercentageNotationToFrontEndOnSpecificOrder(recordToValidate.getHevalp()));
					
			    		//set domain objects
			    		this.setDomainObjectsOnValidationErrors(appUser, recordToValidate, model, parentTrip);
						returnView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
						return returnView;
						
		    		}else{
		    			//At this point all validation (at an ORDER-HEADER level) has been successfully passed.
						//CREATE NEW (ADD)
				    	if(this.isNewRecord(recordToValidate)){
			    			logger.info("PURE CREATE NEW transaction...");
							//Create new order
							Map<String,String> map = this.createNewOrder(recordToValidate.getHeavd(), appUser);
							//Now we have got an OPD for further UPDATE
							String opd = (String)map.get("heopd");
							recordToValidate.setHeopd(opd);
							isCreateNewTransaction = true;
			    		}
			    		//UPDATE (this will be executed after an ADD (when applicable)
			    		if(this.isQualifiedForUpdate(recordToValidate)){
			    			logger.info("UPDATE transaction...");
			    			//--------------------
		    				//Start HEADER UPDATE
			    			//--------------------
			    			final String UPDATE_BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_URL;
			    			String urlRequestKeyParams = this.getRequestUrlKeyParameters(recordToValidate, appUser, TransportDispConstants.MODE_UPDATE );
			    			
			    			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			    			String urlRequestParamsOrder = this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
			    			String urlRequestParams = urlRequestKeyParams.toString() + urlRequestParamsOrder;
					    	logger.info("URL: " + UPDATE_BASE_URL);
					    	logger.info("URL PARAMS: " + urlRequestParams );
					    	//----------------------------------------------------------------------------
					    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
					    	//----------------------------------------------------------------------------
					    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(UPDATE_BASE_URL, urlRequestParams);
					    	//Debug --> 
					    	logger.info("Checking errMsg in rpgReturnPayload [UPDATE]:" + rpgReturnPayload);
					    	//we must evaluate a return RPG code in order to know if the Update was OK or not
					    	rpgReturnResponseHandler.evaluateRpgResponseOnEditSpecificOrder(rpgReturnPayload);
					    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
					    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
					    		this.setFatalError(model, rpgReturnResponseHandler, recordToValidate);
					    		//isValidCreatedRecordTransactionOnRPG = false;
					    	}else{
					    		//Update successfully done!
					    		logger.info("[INFO] Record successfully updated, OK ");
					    		//Update the message notes (2 steps: 1.Delete the original ones, 2.Create the new ones)
					    		this.processNewMessageNotes(recordToValidate, appUser);
					    		
					    		//-------------------------------------------------------
					    		//START ITEM LINES UPDATE
				    			//Validation [3] Back-end-Order lines
				    			//------------------------------------------------
				    			//Validate order lines
				    			//[3.1] Execute back end validation for order lines (now that the order has been created)
					    		this.specificOrderValidatorBackend.validateOrderLines(appUser, recordToValidate, request);
					    		//update with the return values of the back-end (if any). 
				    			this.reflectionSpecificOrderHeaderMgr.updateOriginalAttributesOnTargetFraktbrevLines(recordToValidate, this.specificOrderValidatorBackend.getValidationOutputOderLinesList());
				    			recordToValidate.setFraktbrevList(this.reflectionSpecificOrderHeaderMgr.getTargetFraktbrevListUpdated());
				    			if(this.specificOrderValidatorBackend.getValidationOutputErrMsgList()!=null && this.specificOrderValidatorBackend.getValidationOutputErrMsgList().size()>0){
						    		validationOutputContainer = this.specificOrderValidatorBackend.getValidationOutputContainer();
						    		//ERRORs at the back-end. Abort everything and return to the end-user with the clear error messages
						    		logger.info("VALIDATION BACK-END ERROR (ORDER LINES)");
						    		//logger.info(" size:" + validationOutputContainer.getErrMsgListFromValidationBackend().size());
						    		model.put(TransportDispConstants.DOMAIN_CONTAINER_VALIDATION_BACKEND, validationOutputContainer);
						    		//restore percentage GUI-formatted
						    		//recordToValidate.setHevalp(percentageFormatter.adjustPercentageNotationToFrontEndOnSpecificOrder(recordToValidate.getHevalp()));
						    		//set domain objects
						    		this.setDomainObjectsOnValidationErrors(appUser, recordToValidate, model, parentTrip);
									returnView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
									return returnView;
				    			}else{
				    				logger.info("[START]: processOrderLines...");
				    				//Update the order lines
					    			this.processOrderLines(recordToValidate, appUser);
					    			//postUpdate events on back-end
					    			this.processPostUpdateEvents(recordToValidate, appUser);
					    			logger.info("[END]: processOrderLines");
				    			}
				    		}	
			    		}
		    		}
		    	}
	    		//------------------------------------------
	    		//FETCH the updated or newly created record
	    		//------------------------------------------
		    	final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_URL;
	    		//add URL-parameters
	    		StringBuffer urlRequestParams = new StringBuffer();
	    		urlRequestParams.append("user=" + appUser.getUser());
	    		urlRequestParams.append("&heavd=" + recordToValidate.getHeavd());
	    		urlRequestParams.append("&heopd=" + recordToValidate.getHeopd());
	    		
	    		session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
		    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		    	//Debug --> 
	    		logger.debug(jsonPayload);
		  	
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    		if(jsonPayload!=null){
	    			JsonTransportDispWorkflowSpecificOrderContainer container = this.transportDispWorkflowSpecificOrderService.getContainer(jsonPayload);
	    			for (JsonTransportDispWorkflowSpecificOrderRecord record: container.getDspoppdrag()){
	    				//adjust percentage
	    				//record.setHevalp(percentageFormatter.adjustPercentageNotationToFrontEndOnSpecificOrder(record.getHevalp()));
	    				//populate all message notes
	    				this.populateMessageNotes(appUser, record);
	    				//populate fraktbrev lines
	    				this.populateFraktbrev( appUser, record);
	    				//populate archive docs
	    				this.populateArchiveDocs(appUser, record);
	    				//update the order in session since we might go to Invoice tab directly after this
	    				session.setAttribute(TransportDispConstants.DOMAIN_RECORD_ORDER_TRANSPORT_DISP, record);
	    				
	    				if(isCreateNewTransaction){
	    					this.reflectionSpecificOrderHeaderMgr.updateOriginalAttributesOnTargetFraktbrevLines(record, this.specificOrderValidatorBackend.getValidationOutputOderLinesList());
			    			recordToValidate.setFraktbrevList(this.reflectionSpecificOrderHeaderMgr.getTargetFraktbrevListUpdated());
			    		}
	    				//TODO (Run validation and reflexion and sum TOTALS)
	    				//ONLY when isCreateNewTransaction ?
	    				
	    				//set domain objects
	    				this.setDomainObjectsInView(model, record);
	    				this.setCodeDropDownMgr(appUser, model);
	    				this.setDropDownsFromFiles(model);
	    			}
	    		}
	    		//--------------------------------------
	    		//Final successView with domain objects
	    		//--------------------------------------
	    		model.put("parentTrip", parentTrip);
	    		returnView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
		    }
			return returnView;
		}
	}
	
	/**
	 * The method executes a doDelete on order line item
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_mainorder_delete_order_line.do",  method={RequestMethod.GET} )
	public ModelAndView doDeleteOrderLine(@ModelAttribute ("record") JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("#AVD:" + recordToValidate.getHeavd());
		logger.info("#OPD:" + recordToValidate.getHeopd());
		logger.info("#TUR:" + recordToValidate.getHepro());
		String parentTrip = recordToValidate.getHepro();
		//logger.info("#HESTL4:" + recordToValidate.getHestl4());
		//set the order line nr in a place-holder
		recordToValidate.setOrderLineToDelete(request.getParameter("lin"));
		
		logger.info("#LINENR:" + recordToValidate.getOrderLineToDelete());
		
		ModelAndView successView = new ModelAndView("transportdisp_mainorder");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		Map model = new HashMap();
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
    		//UPDATE (Delete)
			logger.info("UPDATE (DELETE) transaction...");
			
			final String UPDATE_BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL;
			String urlRequestKeyParams = this.getRequestUrlKeyParameters(recordToValidate, appUser, TransportDispConstants.MODE_DELETE );
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("URL: " + UPDATE_BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestKeyParams );
	    	//-----------------------------------------------
	    	//EXECUTE the UPDATE - DELETE (RPG program) here 
	    	//-----------------------------------------------
	    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(UPDATE_BASE_URL, urlRequestKeyParams);
			//Debug --> 
	    	logger.info("Checking errMsg in rpgReturnPayload [UPDATE - DELETE]:" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnEditSpecificOrder(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on DELETE: " + rpgReturnResponseHandler.getErrorMessage());
	    		this.setFatalError(model, rpgReturnResponseHandler, recordToValidate);
	    	}else{
	    		//Update successfully done!
	    		logger.info("[INFO] Record successfully updated, OK ");
    		}	
    		//------------------------------------------
			//FETCH the updated or newly created record
    		//------------------------------------------
	    	final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_URL;
    		//add URL-parameters
    		StringBuffer urlRequestParams = new StringBuffer();
    		urlRequestParams.append("user=" + appUser.getUser());
    		urlRequestParams.append("&heavd=" + recordToValidate.getHeavd());
    		urlRequestParams.append("&heopd=" + recordToValidate.getHeopd());
    		
    		session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
		    String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
	    	//Debug --> 
		  	//logger.debug(this.jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		  	
    		//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    		if(jsonPayload!=null){
    			JsonTransportDispWorkflowSpecificOrderContainer container = this.transportDispWorkflowSpecificOrderService.getContainer(jsonPayload);
    			for (JsonTransportDispWorkflowSpecificOrderRecord record: container.getDspoppdrag()){
    				//adjust percentage
    				//record.setHevalp(percentageFormatter.adjustPercentageNotationToFrontEndOnSpecificOrder(record.getHevalp()));
    				//populate all message notes
    				this.populateMessageNotes(appUser, record);
    				//populate fraktbrev lines
    				this.populateFraktbrev(appUser, record);
    				//populate archive docs
    				this.populateArchiveDocs(appUser, record);
    				//adjust record order lines totals
    				this.adjustOrderLineTotals(request, record);
    				//update order totals on back-end (required)
    				this.updateOrderLineTotalsAfterLineUpdate(appUser, session, record, model);
    				//set domain objects
    				this.setDomainObjectsInView(model, record);
    				this.setCodeDropDownMgr(appUser, model);
    				this.setDropDownsFromFiles(model);
    			}
    		}
    		//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
    		model.put("parentTrip", parentTrip);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
    		logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");

    		return successView;

		}
	}
	

	/**
	 * Processes (updates) the order lines
	 * @param request
	 * @param recordToValidate
	 * @param appUser
	 */
	private List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> processOrderLines(JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, SystemaWebUser appUser){
		logger.info("Inside:processOrderLines");
		List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> errorList = new ArrayList<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord>();
		//check the total number of lines in order to input a new linenr
		int i=1;
		for(JsonTransportDispWorkflowSpecificOrderFraktbrevRecord fraktbrevRecord : recordToValidate.getFraktbrevList()){
			String lineNr = fraktbrevRecord.getFvlinr();
			/* Debug
		 	logger.info("RETURN RECORD fvli:" + fraktbrevRecord.getFvlinr());
			logger.info("RETURN RECORD desc:" + fraktbrevRecord.getFvvt());
			logger.info("RETURN RECORD ant:" + fraktbrevRecord.getFvant());
			logger.info("RETURN RECORD brd:" + fraktbrevRecord.getFvbrd());
			logger.info("RETURN RECORD lm:" + fraktbrevRecord.getFvlm());
			*/
			String mode = TransportDispConstants.MODE_ADD;
			if(lineNr!=null && !"".equals(lineNr) ){ 
				mode = TransportDispConstants.MODE_UPDATE; }
			else{
				//this line is new!
				lineNr = String.valueOf(i);
			}
			if(this.validMandatoryFieldsFraktbrev(fraktbrevRecord)){
				//Start with the update (mode=(A)dd,(D)elete,(U)pdate)
				String BASE_URL_UPDATE = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL;
				//------------------
				//add URL-parameter
				//------------------
				StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
				urlRequestParamsKeysBuffer.append("user=" + appUser.getUser());
				urlRequestParamsKeysBuffer.append("&avd=" + recordToValidate.getHeavd());
				urlRequestParamsKeysBuffer.append("&opd=" + recordToValidate.getHeopd());
				urlRequestParamsKeysBuffer.append("&fbn=1");
				urlRequestParamsKeysBuffer.append("&lin=" + lineNr);
				urlRequestParamsKeysBuffer.append(this.getFvUrlRequestParamsForUpdate(fraktbrevRecord));
				urlRequestParamsKeysBuffer.append("&mode=" + mode);
				
				String urlRequestParams = urlRequestParamsKeysBuffer.toString();
				//logger.info("URL: " + BASE_URL_UPDATE);
				//logger.info("PARAMS: " + urlRequestParams);
				//logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
				//logger.info(jsonPayload);
				//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				if(jsonPayload!=null){ 
					JsonTransportDispWorkflowSpecificOrderFraktbrevContainer fraktbrevContainer = this.transportDispWorkflowSpecificOrderService.getFraktbrevContainer(jsonPayload);
					//logger.info("JsonNotisblockContainer:" + jsonNotisblockContainer);
					if(fraktbrevContainer!=null){
						//logger.info("A:" + jsonNotisblockContainer.getErrMsg());
						if( !"".equals(fraktbrevContainer.getErrMsg()) ){
							//Debug
							logger.info("[ERROR]:" + fraktbrevContainer.getErrMsg());
							fraktbrevRecord.setErrMsg(fraktbrevContainer.getErrMsg());
							errorList.add(fraktbrevRecord);
						}
					}
				}
			}
			//counter to keep track of lines with mode=A (new ones...)
			i++;
			
		}
		return errorList;
		
	}
	
	/* DEPRECATED ...
	private void processOrderLinesOrig(HttpServletRequest request, JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, SystemaWebUser appUser){
		logger.info("Inside:processOrderLines");
		int totalNumberOfLines = this.getTotalNumberOfLines(recordToValidate);
		
		JsonTransportDispWorkflowSpecificOrderFraktbrevRecord fraktbrevRecord = null; 
		for(int i=1;i<=totalNumberOfLines;i++){
			String lineNr = request.getParameter("fvlinr_" + i);
			fraktbrevRecord = new JsonTransportDispWorkflowSpecificOrderFraktbrevRecord();
			String mode = TransportDispConstants.MODE_ADD;
			if(lineNr!=null && !"".equals(lineNr) ){ 
				mode = TransportDispConstants.MODE_UPDATE; }
			else{
				lineNr = request.getParameter("lineNr_" + i);
			}
			//only valid fields (check requirements)
			if(this.validMandatoryFieldsFraktbrev(request, i)){
				//Start with the update (mode=(A)dd,(D)elete,(U)pdate)
				String BASE_URL_UPDATE = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL;
				//------------------
				//add URL-parameter
				//------------------
				StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
				urlRequestParamsKeysBuffer.append("user=" + appUser.getUser());
				urlRequestParamsKeysBuffer.append("&avd=" + recordToValidate.getHeavd());
				urlRequestParamsKeysBuffer.append("&opd=" + recordToValidate.getHeopd());
				urlRequestParamsKeysBuffer.append("&fbn=1");
				urlRequestParamsKeysBuffer.append("&lin=" + lineNr);
				urlRequestParamsKeysBuffer.append(this.getFvUrlRequestParamsForUpdate(request, i, fraktbrevRecord));
				urlRequestParamsKeysBuffer.append("&mode=" + mode);
				
				String urlRequestParams = urlRequestParamsKeysBuffer.toString();
				logger.info("URL: " + BASE_URL_UPDATE);
				logger.info("PARAMS: " + urlRequestParams);
				//logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
				logger.info(jsonPayload);
				//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				if(jsonPayload!=null){ 
					JsonTransportDispWorkflowSpecificOrderFraktbrevContainer fraktbrevContainer = this.transportDispWorkflowSpecificOrderService.getFraktbrevContainer(jsonPayload);
					//logger.info("JsonNotisblockContainer:" + jsonNotisblockContainer);
					if(fraktbrevContainer!=null){
						//logger.info("A:" + jsonNotisblockContainer.getErrMsg());
						if( !"".equals(fraktbrevContainer.getErrMsg()) ){
							//Debug
							logger.info("[ERROR]:" + fraktbrevContainer.getErrMsg());
						}
					}
				}
			}
		}
	}
	*/
	
	/**
	 * This method is used for new orders.
	 * It will create a first item line which is mandatory whenever there are TOTALS. TOTALS are always present in the item-line matrix for new orders. Hence the need of
	 * copying the total line into the first item line in order to comply to the math: ∑-lines=TOTALS
	 * 
	 * @param recordToValidate
	 * @param appUser
	 * @deprecated
	 */
	private void processOrderLineForNewOrder(JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, SystemaWebUser appUser){
		logger.info("Inside:processOrderLineForNewOrder");
		int i=1;
		for(JsonTransportDispWorkflowSpecificOrderFraktbrevRecord fraktbrevRecord : recordToValidate.getFraktbrevList()){
			if(fraktbrevRecord.getFvlinr()==null || "".equals(fraktbrevRecord.getFvlinr())){
				String counter = String.valueOf(i);  fraktbrevRecord.setFvlinr(counter);
			}
			logger.info("RETURN RECORD fvlinr:" + fraktbrevRecord.getFvlinr());
			logger.info("RETURN RECORD desc:" + fraktbrevRecord.getFvvt());
			logger.info("RETURN RECORD ant:" + fraktbrevRecord.getFvant());
			logger.info("RETURN RECORD brd:" + fraktbrevRecord.getFvbrd());
			logger.info("RETURN RECORD lm:" + fraktbrevRecord.getFvlm());
			logger.info("RETURN RECORD lm2:" + fraktbrevRecord.getFvlm2());
			
			String mode = TransportDispConstants.MODE_ADD;
			//Start with the update (mode=(A)dd
			String BASE_URL_UPDATE = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL;
			//------------------
			//add URL-parameter
			//------------------
			StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
			urlRequestParamsKeysBuffer.append("user=" + appUser.getUser());
			urlRequestParamsKeysBuffer.append("&avd=" + recordToValidate.getHeavd());
			urlRequestParamsKeysBuffer.append("&opd=" + recordToValidate.getHeopd());
			urlRequestParamsKeysBuffer.append("&fbn=1");
			urlRequestParamsKeysBuffer.append("&lin=1" );
			urlRequestParamsKeysBuffer.append("&fvlinr=" + fraktbrevRecord.getFvlinr());
			urlRequestParamsKeysBuffer.append("&fmmrk1=" + fraktbrevRecord.getFmmrk1());
			urlRequestParamsKeysBuffer.append("&fvant=" + fraktbrevRecord.getFvant());
			urlRequestParamsKeysBuffer.append("&fvvt=" + fraktbrevRecord.getFvvt());
			urlRequestParamsKeysBuffer.append("&fvvkt=" + fraktbrevRecord.getFvvkt());
			urlRequestParamsKeysBuffer.append("&fvvol=" + fraktbrevRecord.getFvvol());
			urlRequestParamsKeysBuffer.append("&fvlm=" + fraktbrevRecord.getFvlm());
			urlRequestParamsKeysBuffer.append("&fvlm2=" + fraktbrevRecord.getFvlm2());
					
			urlRequestParamsKeysBuffer.append("&mode=" + mode);
			
			String urlRequestParams = urlRequestParamsKeysBuffer.toString();
			logger.info("URL: " + BASE_URL_UPDATE);
			logger.info("PARAMS: " + urlRequestParams);
			//logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
			logger.info(jsonPayload);
			//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			if(jsonPayload!=null){ 
				JsonTransportDispWorkflowSpecificOrderFraktbrevContainer fraktbrevContainer = this.transportDispWorkflowSpecificOrderService.getFraktbrevContainer(jsonPayload);
				//logger.info("JsonNotisblockContainer:" + jsonNotisblockContainer);
				if(fraktbrevContainer!=null){
					//logger.info("A:" + jsonNotisblockContainer.getErrMsg());
					if( !"".equals(fraktbrevContainer.getErrMsg()) ){
						//Debug
						logger.info("[ERROR]:" + fraktbrevContainer.getErrMsg());
					}
				}
			}
			i++;
		}	

	}

	
	/**
	 * 
	 * @param request
	 * @param counter
	 * @return
	 */
	private boolean validMandatoryFieldsFraktbrev(JsonTransportDispWorkflowSpecificOrderFraktbrevRecord fraktbrevRecord){
		boolean retval = false;
		String ant = fraktbrevRecord.getFvant();
		String vkt = fraktbrevRecord.getFvvkt();
		String desc = fraktbrevRecord.getFvvt();
		
		if( (ant!=null && !"".equals(ant))  && 
			(vkt!=null && !"".equals(vkt))  && 
			(desc!=null && !"".equals(desc))){
			
			retval = true;
			
		}
		return retval;
	}
	/**
	 * 
	 * @param request
	 * @param counter
	 * @param fraktbrevRecord
	 * @return
	 */
	private String getFvUrlRequestParamsForUpdate(JsonTransportDispWorkflowSpecificOrderFraktbrevRecord fraktbrevRecord){
		StringBuffer urlRequestParams = new StringBuffer();
		//Build the param-string
		if(fraktbrevRecord.getFvlinr()!=null && !"".equals(fraktbrevRecord.getFvlinr())){
			urlRequestParams.append("&fvlinr=" + fraktbrevRecord.getFvlinr());
		}
		urlRequestParams.append("&fmmrk1=" + fraktbrevRecord.getFmmrk1());
		urlRequestParams.append("&fvant=" + fraktbrevRecord.getFvant());
		urlRequestParams.append("&fvpakn=" + fraktbrevRecord.getFvpakn());
		urlRequestParams.append("&fvvt=" + fraktbrevRecord.getFvvt());
		urlRequestParams.append("&fvvkt=" + fraktbrevRecord.getFvvkt());
		urlRequestParams.append("&fvvol=" + fraktbrevRecord.getFvvol());
		urlRequestParams.append("&fvlm=" + fraktbrevRecord.getFvlm());
		urlRequestParams.append("&fvlm2=" + fraktbrevRecord.getFvlm2());
		urlRequestParams.append("&fvlen=" + fraktbrevRecord.getFvlen());
		urlRequestParams.append("&fvbrd=" + fraktbrevRecord.getFvbrd());
		urlRequestParams.append("&fvhoy=" + fraktbrevRecord.getFvhoy());
		//farlig goods
		urlRequestParams.append("&ffunnr=" + fraktbrevRecord.getFfunnr());
		urlRequestParams.append("&ffembg=" + fraktbrevRecord.getFfembg());
		urlRequestParams.append("&ffindx=" + fraktbrevRecord.getFfindx());
		
		urlRequestParams.append("&ffantk=" + fraktbrevRecord.getFfantk());
		urlRequestParams.append("&ffante=" + fraktbrevRecord.getFfante());
		urlRequestParams.append("&ffenh=" + fraktbrevRecord.getFfenh());
		
		return urlRequestParams.toString();
	}
	/**
	 * Process the message notes in this record and update when applicable
	 * @param recordToValidate
	 * @param appUser
	 */
	private void processNewMessageNotes(JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, SystemaWebUser appUser){
		if(recordToValidate !=null){
			//CONSIGNEE (RECEIVER)
			if(recordToValidate.getMessageNoteConsignee()!=null && !"".equals(recordToValidate.getMessageNoteConsignee())){
				//Delete all values
				this.deleteOriginalMessageNote(this.MESSAGE_NOTE_CONSIGNEE, recordToValidate, appUser);
				//Add new values
				String [] messageNoteConsignee = this.messageNoteMgr.getChunksOfMessageNote(recordToValidate.getMessageNoteConsignee());
				this.updateMessageNote(messageNoteConsignee, this.MESSAGE_NOTE_CONSIGNEE, recordToValidate, appUser);
			}else{
				if(recordToValidate.getMessageNoteConsigneeOriginal()!=null && !"".equals(recordToValidate.getMessageNoteConsigneeOriginal())){
					//Delete all values
					this.deleteOriginalMessageNote(this.MESSAGE_NOTE_CONSIGNEE, recordToValidate, appUser);
				}
			}
			//CARRIER
			if(recordToValidate.getMessageNoteCarrier()!=null && !"".equals(recordToValidate.getMessageNoteCarrier())){
				//Delete all values
				this.deleteOriginalMessageNote(this.MESSAGE_NOTE_CARRIER, recordToValidate, appUser);
				//Add new values
				String [] messageNoteCarrier = this.messageNoteMgr.getChunksOfMessageNote(recordToValidate.getMessageNoteCarrier());
				this.updateMessageNote(messageNoteCarrier, this.MESSAGE_NOTE_CARRIER, recordToValidate, appUser);
			}else{
				//In case the user removes all lines without writing new ones
				if(recordToValidate.getMessageNoteCarrierOriginal()!=null && !"".equals(recordToValidate.getMessageNoteCarrierOriginal())){
					//Delete all values
					this.deleteOriginalMessageNote(this.MESSAGE_NOTE_CARRIER, recordToValidate, appUser);
				}
			}
			
			
			//INTERNAL
			if(recordToValidate.getMessageNoteInternal()!=null && !"".equals(recordToValidate.getMessageNoteInternal())){
				//Delete all values
				this.deleteOriginalMessageNote(this.MESSAGE_NOTE_INTERNAL, recordToValidate, appUser);
				//Add new values
				String [] messageNoteInternal = this.messageNoteMgr.getChunksOfMessageNote(recordToValidate.getMessageNoteInternal());
				this.updateMessageNote(messageNoteInternal, this.MESSAGE_NOTE_INTERNAL, recordToValidate, appUser);
			}else{
				if(recordToValidate.getMessageNoteInternalOriginal()!=null && !"".equals(recordToValidate.getMessageNoteInternalOriginal())){
					//Delete all values
					this.deleteOriginalMessageNote(this.MESSAGE_NOTE_INTERNAL, recordToValidate, appUser);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param messageNote
	 * @param messageParty (r=receiver,consignee  ; g=carrier  ; b=internal)
	 * @param record
	 * @param appUser
	 */
	private void updateMessageNote(String[] messageNote, String messageParty, JsonTransportDispWorkflowSpecificOrderRecord record, SystemaWebUser appUser){
		String CARRIAGE_RETURN = "[\n\r]";
		List<String> messageNotePayload = Arrays.asList(messageNote);
		int lineNr = 0;
		
		for(String linePayload: messageNotePayload){
			linePayload = linePayload.replaceAll(CARRIAGE_RETURN, "");
			linePayload = linePayload.trim();
			lineNr++;
			//---------------------------
			//get BASE URL = RPG-PROGRAM
	        //---------------------------
			if(linePayload!=null && !"".equals(linePayload)){
				String BASE_URL_UPDATE = MainUrlDataStore.SYSTEMA_NOTIS_BLOCK_UPDATE_ITEMLINE_URL;
				boolean doUpdate = false;
				//------------------
				//add URL-parameter
				//------------------
				StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
				urlRequestParamsKeysBuffer.append("user=" + appUser.getUser());
				urlRequestParamsKeysBuffer.append("&frtavd=" + record.getHeavd());
				urlRequestParamsKeysBuffer.append("&frtopd=" + record.getHeopd());
				if(this.MESSAGE_NOTE_CONSIGNEE.equals(messageParty)){
					if(lineNr==1){
						doUpdate = true;
						urlRequestParamsKeysBuffer.append("&frtli=" + lineNr);
					}else if (lineNr==2){
						doUpdate = true;
						urlRequestParamsKeysBuffer.append("&frtli=" + lineNr);
					}
				}else if(this.MESSAGE_NOTE_CARRIER.equals(messageParty)){
					if(lineNr==1){
						doUpdate = true;
						urlRequestParamsKeysBuffer.append("&frtli=3"); //always 1:st line for carrier
					}else if(lineNr==2){
						doUpdate = true;
						urlRequestParamsKeysBuffer.append("&frtli=4"); //always 2:nd line for carrier
					}
				}else if(this.MESSAGE_NOTE_INTERNAL.equals(messageParty)){
					if(lineNr==1){
						doUpdate = true;
						urlRequestParamsKeysBuffer.append("&frtli=5"); //always 1:st line for internal
					}else if(lineNr==2){
						doUpdate = true;
						urlRequestParamsKeysBuffer.append("&frtli=6"); //always 2:nd line for internal
					}
				}else{
					//nothing
				}
				
				urlRequestParamsKeysBuffer.append("&frttxt=" + linePayload);
				urlRequestParamsKeysBuffer.append("&frtkod=" + messageParty);		 
				urlRequestParamsKeysBuffer.append("&mode=A");
				if(doUpdate){
					String urlRequestParams = urlRequestParamsKeysBuffer.toString();
					logger.info("URL: " + BASE_URL_UPDATE);
					logger.info("PARAMS: " + urlRequestParams);
					//logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
					String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
					logger.info(jsonPayload);
					//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
					 
					JsonNotisblockContainer jsonNotisblockContainer = this.notisblockService.getNotisblockListContainer(jsonPayload);
					//logger.info("JsonNotisblockContainer:" + jsonNotisblockContainer);
					if(jsonNotisblockContainer!=null){
						//logger.info("A:" + jsonNotisblockContainer.getErrMsg());
						if( !"".equals(jsonNotisblockContainer.getErrMsg()) ){
							//Debug
							logger.info("[ERROR]:" + jsonNotisblockContainer.getErrMsg());
						}
					}
				}
			}
		}
	}
	/**
	 * The unique KEY is comprised of: avd, opd, linenr
	 * This affects the way we update. Each party has a slot of 2 lines: Consignee (lines:1-2), Carrier(lines:3-4), Internal(lines:5-6)
	 * @param messageParty (r=receiver,consignee  ; g=carrier  ; b=internal)
	 * @param record
	 * @param appUser
	 */
	private void deleteOriginalMessageNote( String messageParty, JsonTransportDispWorkflowSpecificOrderRecord record, SystemaWebUser appUser){
		//we do this in order to secure a total delete no matter how many lines, otherwise it gets complex in case validation errors arise
		int TOTAL_NUMBER_OF_LINES_CEILING_FOR_DELETE_LOOP = 10;
		for(int lineNr=1; lineNr<=TOTAL_NUMBER_OF_LINES_CEILING_FOR_DELETE_LOOP;lineNr++){
			boolean doUpdate = false;
			//---------------------------
			//get BASE URL = RPG-PROGRAM
	        //---------------------------
			String BASE_URL_UPDATE = MainUrlDataStore.SYSTEMA_NOTIS_BLOCK_UPDATE_ITEMLINE_URL;
			//------------------
			//add URL-parameter
			//------------------
			StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
			urlRequestParamsKeysBuffer.append("user=" + appUser.getUser());
			urlRequestParamsKeysBuffer.append("&frtavd=" + record.getHeavd());
			urlRequestParamsKeysBuffer.append("&frtopd=" + record.getHeopd());
			if(this.MESSAGE_NOTE_CONSIGNEE.equals(messageParty)){
				if(lineNr==1 || lineNr==2){
					doUpdate = true;
					urlRequestParamsKeysBuffer.append("&frtli=" + lineNr);
				}
			}else if(this.MESSAGE_NOTE_CARRIER.equals(messageParty)){
				if(lineNr==3 || lineNr==4){
					doUpdate = true;
					urlRequestParamsKeysBuffer.append("&frtli=" + lineNr);
				}
			}else if(this.MESSAGE_NOTE_INTERNAL.equals(messageParty)){
				if(lineNr==5 || lineNr==6){
					doUpdate = true;
					urlRequestParamsKeysBuffer.append("&frtli=" + lineNr);
				}
			}else{
				//nothing
			}
			//N/A -->urlRequestParamsKeysBuffer.append("&frtkod=" + messageParty);
			urlRequestParamsKeysBuffer.append("&mode=D");
			String urlRequestParams = urlRequestParamsKeysBuffer.toString();
			//DEBUG
			if(doUpdate){
				logger.info("URL: " + BASE_URL_UPDATE);
				logger.info("PARAMS: " + urlRequestParams);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
				//DEBUG only 5
				logger.info(jsonPayload);
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				JsonNotisblockContainer jsonNotisblockContainer = this.notisblockService.getNotisblockListContainer(jsonPayload);
				//logger.info("JsonNotisblockContainer:" + jsonNotisblockContainer);
				if(jsonNotisblockContainer!=null){
					//logger.info("A:" + jsonNotisblockContainer.getErrMsg());
					if( !"".equals(jsonNotisblockContainer.getErrMsg()) ){
						//Debug
						logger.info("[WARNING (delete lines)]:" + jsonNotisblockContainer.getErrMsg());
					}
				}
			}
		}
	}
	/**
	 * 
	 * @param action
	 * @param avd
	 * @param transactionMode
	 * @param appUser
	 * @return
	 */
	private Map<String,String> createNewOrder(String avd, SystemaWebUser appUser){
		Map<String, String> map = new HashMap<String, String>();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_URL;
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append("&avd=" + avd);urlRequestParamsKeys.append("&mode=" + TransportDispConstants.MODE_ADD);
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys.toString());
	    	//----------------------------------------------------------------------------
	    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
	    	//----------------------------------------------------------------------------
	    String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
	    	//Debug --> 
	    	logger.info("Checking errMsg in rpgReturnPayload [CREATE NEW]:" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnEditSpecificOrder(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
	    		
	    	}else{
	    		//Update successfully done!
	    		logger.info("[INFO] Record successfully created, OK ");
	    		//put domain objects
	    		String heopd = rpgReturnResponseHandler.getHeopd();
	    		map.put("heopd", heopd);
	    	}
	    	return map;
	}
	
	/**
	 * Adjusts some fields to comply with back-end requirements (ISO-dates, etc)
	 * @param recordToValidate
	 */
	private void adjustFields(JsonTransportDispWorkflowSpecificOrderRecord recordToValidate){
		String etd = recordToValidate.getWsetdd();
		String eta = recordToValidate.getWsetad();
		String atd = recordToValidate.getWsatdd();
		String ata = recordToValidate.getWsatad();
		
		
		if(etd!=null && !"".equals(etd)){
			recordToValidate.setWsetdd(etd.replaceAll("\\.", ""));
		}
		if(eta!=null && !"".equals(eta)){
			recordToValidate.setWsetad(eta.replaceAll("\\.", ""));
		}
		if(atd!=null && !"".equals(atd)){
			recordToValidate.setWsatdd(atd.replaceAll("\\.", ""));
		}
		if(ata!=null && !"".equals(ata)){
			recordToValidate.setWsatad(ata.replaceAll("\\.", ""));
		}
		//----------------------------------
		//Check date-shortcuts (user dates)
		//----------------------------------
		//(1) If the user wrote 2 chars: user day. Example: user writes 29 then it will be <currentYear><currentMonyh>29 (ISO)
		//(2) If the user wrote 4 chars: user day and user month. Example: user writes 2910 then it will be <currentYear>1029 (ISO)
		recordToValidate.setHebodt(this.dateTimeManager.adjustUserDateToISODate(recordToValidate.getHebodt()));
		recordToValidate.setWsetdd(this.dateTimeManager.adjustUserDateToISODate(recordToValidate.getWsetdd()));
		recordToValidate.setWsetad(this.dateTimeManager.adjustUserDateToISODate(recordToValidate.getWsetad()));
		//----------------------------------
		//Check time-shortcuts (user times)
		//----------------------------------
		recordToValidate.setWsbotm(this.dateTimeManager.adjustUserTimeToHHmm(recordToValidate.getWsbotm()));
		recordToValidate.setWsetdk(this.dateTimeManager.adjustUserTimeToHHmm(recordToValidate.getWsetdk()));
		recordToValidate.setWsetak(this.dateTimeManager.adjustUserTimeToHHmm(recordToValidate.getWsetak()));
		
	}
	/**
	 * 
	 * @param request
	 * @param recordToValidate
	 */
	private void adjustOrderLineTotals(HttpServletRequest request, JsonTransportDispWorkflowSpecificOrderRecord record){
		//set some header values
		String hent = request.getParameter("hent");
		String hevkt = request.getParameter("hevkt");
		String hem3 = request.getParameter("hem3");
		String helm = request.getParameter("helm");
		String helmla = request.getParameter("helmla");
		String hepoen = request.getParameter("hepoen");
		String hestl4 = request.getParameter("hestl4");
		
		//lend to
		record.setHent(hent);
		record.setHevkt(hevkt);
		record.setHem3(hem3);
		record.setHelm(helm);
		record.setHelmla(helmla);
		record.setHepoen(hepoen);
		record.setHestl4(hestl4);
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param appUser
	 * @param mode
	 * @return
	 */
	private String getRequestUrlKeyParameters(JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, SystemaWebUser appUser, String mode){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		if(TransportDispConstants.MODE_UPDATE.equalsIgnoreCase(mode)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append("&avd=" + recordToValidate.getHeavd());
			urlRequestParamsKeys.append("&opd=" + recordToValidate.getHeopd());
			urlRequestParamsKeys.append("&mode=" + TransportDispConstants.MODE_UPDATE);
			
			
		}else if(TransportDispConstants.MODE_ADD.equalsIgnoreCase(mode)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append("&avd=" + recordToValidate.getHeavd());
			urlRequestParamsKeys.append("&mode=" + TransportDispConstants.MODE_ADD);
			
			
		}else if(TransportDispConstants.MODE_DELETE.equalsIgnoreCase(mode)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append("&avd=" + recordToValidate.getHeavd());
			urlRequestParamsKeys.append("&opd=" + recordToValidate.getHeopd());
			urlRequestParamsKeys.append("&fbn=1");
			urlRequestParamsKeys.append("&lin=" + recordToValidate.getOrderLineToDelete());
			urlRequestParamsKeys.append("&mode=" + TransportDispConstants.MODE_DELETE);
			
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isNewRecord(JsonTransportDispWorkflowSpecificOrderRecord record){
		boolean retval = true;
		if(record!=null){
			if(record.getHeopd()!=null && !"".equals(record.getHeopd()) ){
				retval = false;
			}
		}
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isQualifiedForUpdate(JsonTransportDispWorkflowSpecificOrderRecord record){
		boolean retval = false;
		if(record!=null){
			if(record.getHeavd()!=null && !"".equals(record.getHeavd()) && 
			   record.getHeopd()!=null && !"".equals(record.getHeopd()) ){
				retval = true;
			}
		}
		return retval;
	}
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonTransportDispWorkflowSpecificOrderRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 */
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(TransportDispConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getTupro());
		model.put(TransportDispConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
	}
	
	/**
	 * 
	 * @param appUser
	 * @param orderRecord
	 */
	private void populateMessageNotes(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderRecord orderRecord){
		Collection<JsonTransportDispWorkflowSpecificOrderMessageNoteRecord> messageNoteConsignee = null;
		Collection<JsonTransportDispWorkflowSpecificOrderMessageNoteRecord> messageNoteCarrier = null;
		Collection<JsonTransportDispWorkflowSpecificOrderMessageNoteRecord> messageNoteInternal = null;
		
		messageNoteConsignee = this.fetchMessageNote(appUser.getUser(), orderRecord, this.MESSAGE_NOTE_CONSIGNEE);
		messageNoteCarrier = this.fetchMessageNote(appUser.getUser(), orderRecord, this.MESSAGE_NOTE_CARRIER);
		messageNoteInternal = this.fetchMessageNote(appUser.getUser(), orderRecord, this.MESSAGE_NOTE_INTERNAL);
		
		StringBuffer brConsignee = new StringBuffer();
		for(JsonTransportDispWorkflowSpecificOrderMessageNoteRecord record: messageNoteConsignee ){
			brConsignee.append(record.getFrttxt() + "\n");
		}
		StringBuffer brCarrier = new StringBuffer();
		for(JsonTransportDispWorkflowSpecificOrderMessageNoteRecord record: messageNoteCarrier ){
			brCarrier.append(record.getFrttxt() + "\n");
		}
		StringBuffer brInternal = new StringBuffer();
		for(JsonTransportDispWorkflowSpecificOrderMessageNoteRecord record: messageNoteInternal ){
			brInternal.append(record.getFrttxt() + "\n");
		}
		//populate final message notes now
		orderRecord.setMessageNoteConsignee(brConsignee.toString());
		orderRecord.setMessageNoteCarrier(brCarrier.toString());
		orderRecord.setMessageNoteInternal(brInternal.toString());
		
	}
	/**
	 * 
	 * @param request
	 * @param appUser
	 * @param record
	 */
	private void populateFraktbrev(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderRecord orderRecord){
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_LIST_MAIN_ORDER_FRAKTBREV_URL;
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append("&avd=" + orderRecord.getHeavd());
		urlRequestParamsKeys.append("&opd=" + orderRecord.getHeopd());
		urlRequestParamsKeys.append("&fbn=1");
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> fraktbrevList = new ArrayList<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord>();
	    
    	//Only with EXISTENT ORDER
    	if(orderRecord.getHeopd()!=null && !"".equals(orderRecord.getHeopd())){
			//----------------------------------------------------------------------------
	    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
	    	//----------------------------------------------------------------------------
    		logger.info("URL: " + BASE_URL);
        	logger.info("URL PARAMS: " + urlRequestParamsKeys.toString());
        	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
	    	//Debug -->
		    logger.info(jsonPayload);
		    if(jsonPayload!=null){
	    		JsonTransportDispWorkflowSpecificOrderFraktbrevContainer container = this.transportDispWorkflowSpecificOrderService.getFraktbrevContainer(jsonPayload);
				if(container!=null){
		    		for (JsonTransportDispWorkflowSpecificOrderFraktbrevRecord fraktbrevRecord: container.getAwblinelist()){
						fraktbrevList.add(fraktbrevRecord);
					}
					//Ensures that the array ALWAYS shows the required 4 lines (with value or empty)
		    		this.populateEmptyFraktbrevList(fraktbrevList);
				}
	    	}
    	}else{
    		this.populateEmptyFraktbrevList(fraktbrevList);
    	}
    	logger.info(Calendar.getInstance().getTime() + " CGI-stop timestamp");
    	
    	//populate the list on parent record
		orderRecord.setFraktbrevList(fraktbrevList);
	}
	/**
	 * 
	 * @param fraktbrevList
	 */
	private void populateEmptyFraktbrevList (List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> fraktbrevList){
		if(fraktbrevList==null || fraktbrevList.size()<TransportDispConstants.CONSTANT_TOTAL_NUMBER_OF_ORDER_LINES){
			int start = fraktbrevList.size();
			for(int i = ++start;i<=TransportDispConstants.CONSTANT_TOTAL_NUMBER_OF_ORDER_LINES;i++){
				//logger.info("#########################:" + i);
				fraktbrevList.add(new JsonTransportDispWorkflowSpecificOrderFraktbrevRecord());
			}
		}
	}
	/**
	 * 
	 * @param appUser
	 * @param orderRecord
	 */
	private void populateArchiveDocs(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderRecord orderRecord){
		//===========
		 //FETCH LIST
		 //===========
		 logger.info("Inside: populateArchiveDocs");
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_UPLOADED_DOCS_URL;
		 String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + orderRecord.getHeavd() + "&opd=" + orderRecord.getHeopd();
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 Collection<JsonTransportDispWorkflowSpecificOrderArchivedDocsRecord> archivedDocList = new ArrayList<JsonTransportDispWorkflowSpecificOrderArchivedDocsRecord>();
		    
		 if(jsonPayload!=null){
		 	try{
		 		JsonTransportDispWorkflowSpecificOrderArchivedDocsContainer container = this.transportDispWorkflowSpecificOrderService.getOrderArchivedDocsContainer(jsonPayload);
				if(container!=null){
					archivedDocList = container.getGetdoc();
					for(JsonTransportDispWorkflowSpecificOrderArchivedDocsRecord record : container.getGetdoc()){
						//DEBUG -->logger.info("####Link:" + record.getDoclnk());
					}
				}
				
		 	}catch(Exception e){
		 		e.printStackTrace();
		 	}
		 }
		//populate the list on parent record
		 orderRecord.setArchivedDocsRecord(archivedDocList);
		 
	}
	/**
	 * 
	 * @param appUser
	 * @param orderRecord
	 */
	private void populateTrackAndTrace(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderRecord orderRecord){
		//===========
		 //FETCH LIST
		 //===========
		 logger.info("Inside: populateTrackAndTrace");
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_GENERAL_TRACK_AND_TRACE_URL;
		 String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + orderRecord.getHeavd() + "&opd=" + orderRecord.getHeopd();
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		 
		 Collection<JsonTransportDispWorkflowSpecificOrderLoggingRecord> list = new ArrayList<JsonTransportDispWorkflowSpecificOrderLoggingRecord>();
		 if(jsonPayload!=null){
		 	try{
		 		JsonTransportDispWorkflowSpecificOrderLoggingContainer container = this.transportDispWorkflowSpecificOrderService.getOrderLoggingContainer(jsonPayload);
				if(container!=null){
					list = container.getTrackTraceEvents();
					for(JsonTransportDispWorkflowSpecificOrderLoggingRecord record : list){
						//DEBUG -->logger.info("####Link:" + record.getDoclnk());
					}
				}
				
		 	}catch(Exception e){
		 		e.printStackTrace();
		 	}
		 }
		//populate the list on parent record
		 orderRecord.setLoggingRecord(list);
		 
	}
	/**
	 * 
	 * @param appUser
	 * @param orderRecord
	 */
	private void processPostUpdateEvents(JsonTransportDispWorkflowSpecificOrderRecord orderRecord, SystemaWebUser appUser){
		//===========
		 //FETCH LIST
		 //===========
		 logger.info("Inside: processPostUpdateEvents");
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_POST_UPDATE_Z;
		 String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + orderRecord.getHeavd() + "&opd=" + orderRecord.getHeopd();
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		 
		 if(jsonPayload!=null){
		 	try{
		 		JsonTransportDispWorkflowPostUpdateContainer container = this.transportDispPostUpdateService.getContainer(jsonPayload);
				if(container!=null){
					//DO SOMETHING. To be determined since this call is merely to trigger a post-event on the back-end
				}
				
		 	}catch(Exception e){
		 		e.printStackTrace();
		 	}
		 }
		 
	}
	
	/**
	 * 
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonTransportDispWorkflowSpecificOrderRecord record){
		//SET HEADER RECORDS  (from RPG)
		model.put(TransportDispConstants.DOMAIN_RECORD, record);
		
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
		//frankatur
		this.codeDropDownMgr.populateHtmlDropDownsFromJsonStringFrankatur(this.urlCgiProxyService, this.transportDispDropDownListPopulationService, model, appUser, null);
		//oppdragtype
		this.codeDropDownMgr.populateHtmlDropDownsFromJsonStringOppdragsType(this.urlCgiProxyService, this.transportDispDropDownListPopulationService, model, appUser, null);
	}
	/**
	 * 
	 * @param model
	 */
	private void setDropDownsFromFiles(Map<String, Object> model){
		model.put(TransportDispConstants.RESOURCE_MODEL_KEY_CURRENCY_CODE_LIST, this.transportDispDropDownListPopulationService.getCurrencyList());
	}
	
	/**
	 * for update reasons in order to manage the 70-characters chunks on MessageNote
	 * 
	 * @param request
	 * @param jsonSadImportSpecificTopicRecord
	 */
	public String[] getChunksOfMessageNote(JsonTransportDispWorkflowSpecificOrderRecord record, String type){
		String messageRaw = "";
		String[] records = new String[30];
		if(record!=null){
			if(this.MESSAGE_NOTE_CONSIGNEE.equalsIgnoreCase(type)){
				messageRaw = record.getMessageNoteConsignee();
			}else if(this.MESSAGE_NOTE_CARRIER.equalsIgnoreCase(type)){
				messageRaw = record.getMessageNoteCarrier();
			}else if(this.MESSAGE_NOTE_INTERNAL.equalsIgnoreCase(type)){
				messageRaw = record.getMessageNoteInternal();
			}	
			if(messageRaw!=null){
				records = messageRaw.split("\\n");
				for (int i = 0; i < records.length; i++){
					records[i] = records[i].replace("\\n", "");
					logger.debug("##:" + records[i]);
				}
			}
		}
		return records;
	}
	
	/**
	 * Get message notes
	 * 
	 * @param applicationUser
	 * @param orderRecord
	 * @param type
	 * @return
	 */
	public Collection<JsonTransportDispWorkflowSpecificOrderMessageNoteRecord> fetchMessageNote(String applicationUser, JsonTransportDispWorkflowSpecificOrderRecord orderRecord, String type){
		Collection<JsonTransportDispWorkflowSpecificOrderMessageNoteRecord> outputList = new ArrayList<JsonTransportDispWorkflowSpecificOrderMessageNoteRecord>();
		//===========
		//FETCH LIST
		//===========
		//get BASE URL
    		final String BASE_LIST_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_MESSAGE_NOTE_URL;
    		//add URL-parameters
    		StringBuffer urlRequestParams = new StringBuffer();
    		urlRequestParams.append("user=" + applicationUser);
    		if(orderRecord.getHeavd()!=null && !"".equals(orderRecord.getHeavd())){ urlRequestParams.append("&avd=" + orderRecord.getHeavd()); }
    		if(orderRecord.getHeopd()!=null && !"".equals(orderRecord.getHeopd())){ urlRequestParams.append("&opd=" + orderRecord.getHeopd()); }
    		if(type!=null && !"".equals(type)){ urlRequestParams.append("&kod=" + type); }
    		
    		
    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    		logger.info("URL: " + BASE_LIST_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_LIST_URL, urlRequestParams.toString());
	    	//Debug --> 
	    	//logger.debug(jsonPayload);
	    	//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonTransportDispWorkflowSpecificOrderMessageNoteContainer messageNoteContainer = this.transportDispWorkflowSpecificOrderService.getMessageNoteContainer(jsonPayload);
	    		outputList = messageNoteContainer.getFreetextlistA();
	    		for(JsonTransportDispWorkflowSpecificOrderMessageNoteRecord note: outputList){
	    			//logger.info(note.getFrttxt());
	    		}
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
		}
	    
	    	return outputList;
		
	}
	/**
	 * Get signatures
	 * @param model
	 * @param appUser
	 */
	private void setDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		
		try{
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_GENERAL_SIGN_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:" + BASE_URL);
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTransportDispSignatureContainer container = this.transportDispDropDownListPopulationService.getSignContainer(url);
			List<JsonTransportDispSignatureRecord> list = new ArrayList();
			for(JsonTransportDispSignatureRecord record: container.getSignaturer()){
				list.add(record);
			}
			model.put(TransportDispConstants.RESOURCE_MODEL_KEY_SIGN_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 */
	private void setPostalCodesValidationRequirements(String applicationUser, JsonTransportDispWorkflowSpecificOrderRecord recordToValidate){
		boolean exactMatch = false;
		ControllerAjaxCommonFunctionsMgr controllerAjaxCommonFunctionsMgr = new ControllerAjaxCommonFunctionsMgr(this.urlCgiProxyService, this.transportDispChildWindowService);
		//Hesdf (From)
		if(recordToValidate.getHesdf()!=null && !"".equals(recordToValidate.getHesdf())){
			if(recordToValidate.getHelka()!=null && !"".equals(recordToValidate.getHelka())){
				JsonPostalCodesRecord postalCodesRecord = new JsonPostalCodesRecord();
				postalCodesRecord.setSt2kod(recordToValidate.getHesdf());
				postalCodesRecord.setSt2lk(recordToValidate.getHelka());
				Collection<JsonPostalCodesRecord> list = controllerAjaxCommonFunctionsMgr.fetchPostalCodes(applicationUser, postalCodesRecord, exactMatch);
				if(list!=null && !list.isEmpty()){
					recordToValidate.setIsValidPostalCodeHesdf(true);
				}
			}
		}
		//Hesdt (To)
		if(recordToValidate.getHesdt()!=null && !"".equals(recordToValidate.getHesdt())){
			if(recordToValidate.getHetri()!=null && !"".equals(recordToValidate.getHetri())){
				JsonPostalCodesRecord postalCodesRecord = new JsonPostalCodesRecord();
				postalCodesRecord.setSt2kod(recordToValidate.getHesdt());
				postalCodesRecord.setSt2lk(recordToValidate.getHetri());
				Collection<JsonPostalCodesRecord> list = controllerAjaxCommonFunctionsMgr.fetchPostalCodes(applicationUser, postalCodesRecord, exactMatch);
				if(list!=null && !list.isEmpty()){
					recordToValidate.setIsValidPostalCodeHesdt(true);
				}
			}
		}
		//Hesdff (From-Via)
		if(recordToValidate.getHesdff()!=null && !"".equals(recordToValidate.getHesdff())){
			if(recordToValidate.getHelks()!=null && !"".equals(recordToValidate.getHelks())){
				JsonPostalCodesRecord postalCodesRecord = new JsonPostalCodesRecord();
				postalCodesRecord.setSt2kod(recordToValidate.getHesdff());
				postalCodesRecord.setSt2lk(recordToValidate.getHelks());
				Collection<JsonPostalCodesRecord> list = controllerAjaxCommonFunctionsMgr.fetchPostalCodes(applicationUser, postalCodesRecord, exactMatch);
				if(list!=null && !list.isEmpty()){
					recordToValidate.setIsValidPostalCodeHesdff(true);
				}
			}
		}
		//Hesdt (To - Via)
		if(recordToValidate.getHesdvt()!=null && !"".equals(recordToValidate.getHesdvt())){
			if(recordToValidate.getHelkk()!=null && !"".equals(recordToValidate.getHelkk())){
				JsonPostalCodesRecord postalCodesRecord = new JsonPostalCodesRecord();
				postalCodesRecord.setSt2kod(recordToValidate.getHesdvt());
				postalCodesRecord.setSt2lk(recordToValidate.getHelkk());
				Collection<JsonPostalCodesRecord> list = controllerAjaxCommonFunctionsMgr.fetchPostalCodes(applicationUser, postalCodesRecord, exactMatch);
				if(list!=null && !list.isEmpty()){
					recordToValidate.setIsValidPostalCodeHesdvt(true);
				}
			}
		}
	}
	/**
	 * 
	 * @param recordToValidate
	 * @return
	 */
	private int getTotalNumberOfLines(JsonTransportDispWorkflowSpecificOrderRecord recordToValidate){
		//check the total number of lines
		int totalNumberOfLines = TransportDispConstants.CONSTANT_TOTAL_NUMBER_OF_ORDER_LINES; //Default
		if(!"".equals(recordToValidate.getTotalNumberOfLines()) && recordToValidate.getTotalNumberOfLines()!=null){
			try{
				int tmpLimit = Integer.parseInt(recordToValidate.getTotalNumberOfLines());
				if(tmpLimit>totalNumberOfLines){
					totalNumberOfLines = Integer.parseInt(recordToValidate.getTotalNumberOfLines());
				}
			}catch(Exception e){
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.info(errors);
			}
		}
		return totalNumberOfLines;
	}
	
	/**
	 * Used mainly for validate issues.
	 * When a user input does not validate, the system must be able to return all user-input in all order lines (record) matrix that have been input
	 * 
	 * @param request
	 * @param counter
	 * @param fraktbrevRecord
	 * @return
	 */
	private void populateOrderLineRecordsWithUserInput(HttpServletRequest request, JsonTransportDispWorkflowSpecificOrderRecord recordToValidate){
		int totalNumberOfLines = this.getTotalNumberOfLines(recordToValidate);
		List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> list = new ArrayList<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord>();
		JsonTransportDispWorkflowSpecificOrderFraktbrevRecord fraktbrevRecord = null;
		
		for(int counter=1;counter<=totalNumberOfLines;counter++){
			fraktbrevRecord = new JsonTransportDispWorkflowSpecificOrderFraktbrevRecord();
			String lineNr = request.getParameter("fvlinr_" + counter);
			if(lineNr!=null && !"".equals(lineNr)){
				fraktbrevRecord.setFvlinr(lineNr);
			}
			fraktbrevRecord.setFmmrk1(request.getParameter("fmmrk1_" + counter));
			fraktbrevRecord.setFvant(request.getParameter("fvant_" + counter));
			fraktbrevRecord.setFvpakn(request.getParameter("fvpakn_" + counter));
			fraktbrevRecord.setFvvt(request.getParameter("fvvt_" + counter));
			fraktbrevRecord.setFvvkt(request.getParameter("fvvkt_" + counter));
			fraktbrevRecord.setFvvol(request.getParameter("fvvol_" + counter));
			fraktbrevRecord.setFvlm(request.getParameter("fvlm_" + counter));
			fraktbrevRecord.setFvlm2(request.getParameter("fvlm2_" + counter));
			fraktbrevRecord.setFvlen(request.getParameter("fvlen_" + counter));
			fraktbrevRecord.setFvbrd(request.getParameter("fvbrd_" + counter));
			fraktbrevRecord.setFvhoy(request.getParameter("fvhoy_" + counter));
			//farlig goods
			fraktbrevRecord.setFfunnr(request.getParameter("ffunnr_" + counter));
			fraktbrevRecord.setFfembg(request.getParameter("ffembg_" + counter));
			fraktbrevRecord.setFfindx(request.getParameter("ffindx_" + counter));
			
			fraktbrevRecord.setFfantk(request.getParameter("ffantk_" + counter));
			fraktbrevRecord.setFfante(request.getParameter("ffante_" + counter));
			fraktbrevRecord.setFfenh(request.getParameter("ffenh_" + counter));
			list.add(fraktbrevRecord);
			
		}
		logger.info("********** order lines list, SIZE:" + list.size());
		recordToValidate.setFraktbrevList(list);
		
	}
	
	/**
	 * Deep refresh when updating order lines. We must capture the new totals.
	 * 
	 * @param model
	 * @param session
	 * @param order
	 */
	private void updateOrderLineTotalsAfterLineUpdate(SystemaWebUser appUser, HttpSession session, JsonTransportDispWorkflowSpecificOrderRecord record, Map model){
		if(this.isQualifiedForUpdate(record)){
			logger.info("UPDATE transaction...");
			//--------------------
			//Start HEADER UPDATE
			//--------------------
			final String UPDATE_BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_URL;
			String urlRequestKeyParams = this.getRequestUrlKeyParameters(record, appUser, TransportDispConstants.MODE_UPDATE );
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			String urlRequestParamsOrder = this.urlRequestParameterMapper.getUrlParameterValidString((record));
			String urlRequestParams = urlRequestKeyParams.toString() + urlRequestParamsOrder;
	    	logger.info("URL: " + UPDATE_BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams );
	    	//----------------------------------------------------------------------------
	    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
	    	//----------------------------------------------------------------------------
	    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(UPDATE_BASE_URL, urlRequestParams);
	    	//Debug --> 
	    	logger.info("Checking errMsg in rpgReturnPayload [UPDATE]:" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnEditSpecificOrder(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
	    		this.setFatalError(model, rpgReturnResponseHandler, record);
	    		//isValidCreatedRecordTransactionOnRPG = false;
	    	}else{
	    		//Update successfully done!
	    		logger.info("[INFO] Record successfully updated, OK ");
	    		//update order record in session)
	    		session.setAttribute(TransportDispConstants.DOMAIN_RECORD_ORDER_TRANSPORT_DISP, record);
	    	}
		}
	}
	
	/**
	 * 
	 * @param preRecord
	 * @param orderLineTotals
	 */
	private void updateOrderLineTotalsPreRecord(JsonTransportDispWorkflowSpecificOrderRecord preRecord, String orderLineTotals){
		if(orderLineTotals!=null){
			List<String> list = Arrays.asList(orderLineTotals.split("@"));
			for(String record : list){
				if(record.startsWith("hent_")) { preRecord.setHent(record.replace("hent_", "")); }
				if(record.startsWith("hevkt_")) { preRecord.setHevkt(record.replace("hevkt_", "")); }
				if(record.startsWith("hem3_")) { preRecord.setHem3(record.replace("hem3_", "")); }
				if(record.startsWith("helm_")) { preRecord.setHelm(record.replace("helm_", "")); }
				if(record.startsWith("helmla_")) { preRecord.setHelmla(record.replace("helmla_", "")); }
				if(record.startsWith("hepoen_")) { preRecord.setHepoen(record.replace("hepoen_", "")); }
				if(record.startsWith("hestl4_")) { preRecord.setHestl4(record.replace("hestl4_", "")); }
			}
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param record
	 */
	public void setAvdDefaultCountryCodes(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderRecord record){
		try{
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_AVD_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			//logger.info("heavd:" + record.getHeavd() + "XX");
			if(record.getHeavd()!=null && !"".equals(record.getHeavd())){
				urlRequestParamsKeys.append("&avd=" + record.getHeavd());
			}
			
			//Now build the URL and send to the back end via the drop down service
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:" + BASE_URL);
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTransportDispAvdContainer container = this.transportDispChildWindowService.getAvdContainer(jsonPayload);
			for(JsonTransportDispAvdRecord avdRecord: container.getAvdelningar()){
				/* NOTE! --> mail JOVO 17.sep.2015 
				koaLK =   Landkode  
				koaIE = 1, 2, 3, D = Innland      -     Både FRA OG TIL settes til   koaLK (=NO) 
				for future use (men implementer det gjerne nå): 
				koaIE = I = Import     - dersom landkode oppgitt så er det FRA-land (og Til-land er 'firmaets landkode' = NO ) 
				koaIE = E = Eksport     - dersom landkode oppgitt så er det TIL-land (og Fra-land er 'firmaets landkode' = NO ) 
				*/
				if("1".equals(avdRecord.getKoaIE()) || "2".equals(avdRecord.getKoaIE()) || "3".equals(avdRecord.getKoaIE()) || "D".equalsIgnoreCase(avdRecord.getKoaIE())){
					record.setHelka(avdRecord.getKoaLK());
					record.setHetri(avdRecord.getKoaLK());
				}
			}
			//logger.info("KLOKKE:" + record.getKlokkeJN());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	//SERVICES
	@Qualifier ("transportDispWorkflowSpecificOrderService")
	private TransportDispWorkflowSpecificOrderService transportDispWorkflowSpecificOrderService;
	@Autowired
	@Required
	public void setTransportDispWorkflowSpecificOrderService (TransportDispWorkflowSpecificOrderService value){ this.transportDispWorkflowSpecificOrderService = value; }
	public TransportDispWorkflowSpecificOrderService getTransportDispWorkflowSpecificOrderService(){ return this.transportDispWorkflowSpecificOrderService; }
	
	@Qualifier ("transportDispDropDownListPopulationService")
	private TransportDispDropDownListPopulationService transportDispDropDownListPopulationService;
	@Autowired
	public void setTransportDispDropDownListPopulationService (TransportDispDropDownListPopulationService value){ this.transportDispDropDownListPopulationService=value; }
	public TransportDispDropDownListPopulationService getTransportDispDropDownListPopulationService(){return this.transportDispDropDownListPopulationService;}
	
	@Qualifier ("transportDispChildWindowService")
	private TransportDispChildWindowService transportDispChildWindowService;
	@Autowired
	public void setTransportDispChildWindowService (TransportDispChildWindowService value){ this.transportDispChildWindowService=value; }
	public TransportDispChildWindowService getTransportDispChildWindowService(){return this.transportDispChildWindowService;}
	
	
	@Qualifier ("notisblockService")
	private NotisblockService notisblockService;
	@Autowired
	public void setNotisblockService (NotisblockService value){ this.notisblockService=value; }
	public NotisblockService getNotisblockService(){return this.notisblockService;}
	
	
	@Qualifier ("transportDispPostUpdateService")
	private TransportDispPostUpdateService transportDispPostUpdateService;
	@Autowired
	public void setTransportDispPostUpdateService (TransportDispPostUpdateService value){ this.transportDispPostUpdateService=value; }
	public TransportDispPostUpdateService getTransportDispPostUpdateService(){return this.transportDispPostUpdateService;}
	
	
	
}

