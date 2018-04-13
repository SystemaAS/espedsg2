package no.systema.transportdisp.controller;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.validator.LoginValidator;
import no.systema.main.model.SystemaWebUser;
//Transport Disp.
import no.systema.transportdisp.service.TransportDispWorkflowListService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;
import no.systema.transportdisp.filter.SearchFilterTransportDispWorkflowTripList;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripArchivedDocsRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripMessageNoteRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripContainer;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.mapper.url.request.UrlRequestParameterMapper;
import no.systema.transportdisp.util.RpgReturnResponseHandler;
import no.systema.transportdisp.validator.TransportDispWorkflowSpecificTripValidator;
import no.systema.transportdisp.util.TransportDispDateTimeFormatter;
import no.systema.transportdisp.util.manager.CodeDropDownMgr;
import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;

/**
 * 
 * TransportDisp Header Controller 
 * 
 * @author oscardelatorre
 * @date Jan 13, 2015
 * 
 */

@Controller
@Scope("session")
public class TransportDispWorkflowHeaderController {
	
	private static Logger logger = Logger.getLogger(TransportDispWorkflowHeaderController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	private TransportDispDateTimeFormatter dateTimeFormatter = new TransportDispDateTimeFormatter();
	private DateTimeManager dateTimeManager = new DateTimeManager();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private ControllerAjaxCommonFunctionsMgr controllerAjaxCommonFunctionsMgr;
	private LoginValidator loginValidator = new LoginValidator();
	private static final JsonDebugger jsonDebugger = new JsonDebugger(8000);
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	
	
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
	@RequestMapping(value = "transportdisp_workflow_getTrip.do", method = RequestMethod.GET)
	public ModelAndView doTranspDispGetTrip(HttpSession session, HttpServletRequest request){
		 logger.info("Inside: doTranspDispGetTrip");
		 ModelAndView successView = new ModelAndView("transportdisp_workflow");
		 SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		 SearchFilterTransportDispWorkflowTripList tripListSearchFilter = new SearchFilterTransportDispWorkflowTripList();
		 
		 Map model = new HashMap();
		 if(appUser==null){
				return loginView;
		 }else{
			 
			 String applicationUser = appUser.getUser();
			 String avdNr = request.getParameter("tuavd");
			 String tripNr = request.getParameter("tupro");
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
					    	this.setDomainObjectsInView(session, model, record );	
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
					    	this.setDomainObjectsInView(session, model, record );
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
	 * Creates or Updates a new Trip (Tur)
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_edit.do")
	public ModelAndView doTransportDispEdit(@ModelAttribute ("record") JsonTransportDispWorkflowSpecificTripRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("transportdisp_workflow");
		String method = "doTransportDispEdit";
		this.controllerAjaxCommonFunctionsMgr = new ControllerAjaxCommonFunctionsMgr(this.urlCgiProxyService, this.transportDispWorkflowSpecificTripService);

		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Method: " + method);
		//required params for a specific trip
		logger.info("Avd:" + recordToValidate.getTuavd());
		logger.info("Trip No.:" + recordToValidate.getTupro());
		String[] messageNote = this.getChunksOfMessageNote(recordToValidate);
		//---------------------------------
		//Crucial request parameters (Keys)
		//---------------------------------
		String action = request.getParameter("action");
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			if(action!=null){
				//Get Lorry-Capacity-Matrix params since there are hidden fields that must be fetched manually
				recordToValidate.setTukvkt(request.getParameter("own_tukvkt"));
				recordToValidate.setTutara(request.getParameter("own_tutara"));
				recordToValidate.setTukam3(request.getParameter("own_tukam3"));
				recordToValidate.setTukalM(request.getParameter("own_tukalM"));
				
				
				//----------------------------
				//CREATE and/or UPDATE RECORD
				//----------------------------	
				if(TransportDispConstants.ACTION_UPDATE.equals(action)){
					//-----------
					//Validation
					//-----------
					logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
					TransportDispWorkflowSpecificTripValidator validator = new TransportDispWorkflowSpecificTripValidator();
					//adjust some fields
					this.adjustFields(recordToValidate);
					//validate
					validator.validate(recordToValidate, bindingResult);
					logger.info("After validation:tuavd" + recordToValidate.getTuavd());
					//----------------------------
				    //check for validation ERRORS
					//----------------------------
					if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Record does not validate)");
					    //put domain objects and do go back to the original view...
				    	this.setDomainObjectsInView(session, model, recordToValidate );

					}else{
			    		String transactionMode = null;
						if(recordToValidate.getTupro()!=null && !"".equals(recordToValidate.getTupro())){
							logger.info("PURE UPDATE transaction...");
							transactionMode = TransportDispConstants.MODE_UPDATE;
						}else{
							logger.info("PURE CREATE NEW transaction...");
							transactionMode = TransportDispConstants.MODE_ADD;
							//Get new tupro and avd...by creating a new trip.
							//The new trip required only user and mode=A as parameters...nothing else
							Map<String,String> map = this.createNewTrip(action, recordToValidate, transactionMode, appUser);
							String tmpTupro = (String)map.get("tupro");
							String tmpTuavd  = (String)map.get("tuavd");
							recordToValidate.setTupro(tmpTupro);
							recordToValidate.setTuavd(tmpTuavd);
							
							//Change mode in order to update the newly created record. This in order to send all other parameters
							if(recordToValidate.getTupro()!=null && recordToValidate.getTuavd()!=null){ transactionMode = TransportDispConstants.MODE_UPDATE; }
						}
						//--------------------------------------------------
						//At this point we are ready to do an update
						//--------------------------------------------------
						if(transactionMode.equals(TransportDispConstants.MODE_UPDATE)){
				            //---------------------------
							//get BASE URL = RPG-PROGRAM
				            //---------------------------
							String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_UPDATE_SPECIFIC_TRIP_URL;
							//------------------
							//add URL-parameter
							//------------------
							String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, recordToValidate.getTuavd(), recordToValidate.getTupro(), transactionMode, appUser);
							//adjust some fields
							//this.adjustFields(recordToValidate);
							//Blank the message note since we do not want the content in the update. There is a special update
							//for this field a few steps ahead
							String messageNoteTmp = recordToValidate.getMessageNote();
							recordToValidate.setMessageNote(null);
							
							String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
							//reformat return object as to be presented in GUI
							//recordToValidate.setTutm(dateTimeFormatter.formatTimeHHmm(recordToValidate.getTutm()));
							//recordToValidate.setTutmt(dateTimeFormatter.formatTimeHHmm(recordToValidate.getTutmt()));
							recordToValidate.setMessageNote(messageNoteTmp);
							
							//put the final valid param. string
							String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopic;
							//for debug purposes in GUI
							session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL); 
					    	
							logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
					    	logger.info("URL: " + BASE_URL);
					    	logger.info("URL PARAMS: " + urlRequestParams);
					    	//----------------------------------------------------------------------------
					    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
					    	//----------------------------------------------------------------------------
						    String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
					    	//Debug --> 
					    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
					    	//we must evaluate a return RPG code in order to know if the Update was OK or not
					    	rpgReturnResponseHandler.evaluateRpgResponseOnTripUpdate(rpgReturnPayload);
					    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
					    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
					    		this.setFatalError(model, rpgReturnResponseHandler, recordToValidate);
					    		//isValidCreatedRecordTransactionOnRPG = false;
					    	}else{
					    		//Update successfully done!
					    		logger.info("[INFO] Record successfully updated, OK ");
					    		this.updateMessageNote(messageNote, recordToValidate.getTuavd(), recordToValidate.getTupro(), appUser);
					    		
					    		//set message note (after update aka refresh)
					    		Collection<JsonTransportDispWorkflowSpecificTripMessageNoteRecord> messageNoteAfterUpdate = null;
					    		messageNoteAfterUpdate = this.controllerAjaxCommonFunctionsMgr.fetchMessageNote(appUser.getUser(), recordToValidate.getTuavd(), recordToValidate.getTupro());
					    		StringBuffer br = new StringBuffer();
							for(JsonTransportDispWorkflowSpecificTripMessageNoteRecord record:messageNoteAfterUpdate ){
								br.append(record.getFrttxt() + "\n");
							}
							recordToValidate.setMessageNote(br.toString());
							//logger.info(recordToValidate.getMessageNote());
							
							//Now fetch the Archived Documents and fill the parent record with it
							Collection<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord> archiveDocsList = null;
							archiveDocsList = this.controllerAjaxCommonFunctionsMgr.fetchTripHeadingArchiveDocs(appUser.getUser(), recordToValidate.getTupro());
							recordToValidate.setGetdoctrip(archiveDocsList);
							
							//put domain objects
					    	this.setDomainObjectsInView(session, model, recordToValidate );
					    	}
						}
					}
				//------------------------
				//CREATE-ADD [PURE] RECORD
				//-------------------------
				}else if(TransportDispConstants.ACTION_CREATE.equals(action)){
					//OBSOLETE
					//This action is not used as an isolated create. It is realized in the UPDATE above in 2 transactions
					//Could be needed in the future.
				//------------------
				//REMOVE RECORD
				//------------------	
				}else if(TransportDispConstants.ACTION_DELETE.equals(action)){
					//Remove Topic
					//Could be delete OR set a remove status...(no physical delete)
					//TODO
				}
				
				//===========
				//FETCH LIST
				//===========
				//drop downs from files
				this.setDropDownsFromFiles(model);
				this.setCodeDropDownMgr(appUser, model);
				//Now get the parent list of workflow trips
				Collection<JsonTransportDispWorkflowListRecord> outputList = this.fetchWorkflowList(appUser.getUser(), recordToValidate.getTuavd(), session, model);
				successView.addObject(TransportDispConstants.DOMAIN_LIST,outputList);
				//put domain object in view
	    		successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
			}
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param avdNr
	 * @param session
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
	 * Create new trip
	 * @param recordToValidate
	 * @param action
	 * @param record
	 * @param transactionMode
	 * @param appUser
	 * @return
	 */
	private Map<String,String> createNewTrip(String action, JsonTransportDispWorkflowSpecificTripRecord record, String transactionMode, SystemaWebUser appUser){
		Map<String, String> map = new HashMap<String, String>();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_UPDATE_SPECIFIC_TRIP_URL;
		//------------------
		//add URL-parameter
		//------------------
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		if(record.getTuavd()!=null && !"".equals(record.getTuavd())){ urlRequestParams.append("&tuavd=" + record.getTuavd()); }
		if(record.getTusg()!=null && !"".equals(record.getTusg())){ urlRequestParams.append("&tusg=" + record.getTusg()); }
		urlRequestParams.append("&mode=" + transactionMode); 
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	//----------------------------------------------------------------------------
	    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
	    	//----------------------------------------------------------------------------
	    String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
	    	//Debug --> 
	    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnTripUpdate(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
	    		
	    	}else{
	    		//Update successfully done!
	    		logger.info("[INFO] Record successfully created, OK ");
	    		//put domain objects
	    		String tupro = rpgReturnResponseHandler.getTupro();
	    		String tuavd = rpgReturnResponseHandler.getTuavd();
	    		map.put("tupro", tupro);
	    		map.put("tuavd", tuavd);
	    	}
	    	return map;
	}
	
	/**
	 * 
	 * @param messageNote
	 * @param avd
	 * @param tur
	 * @param appUser
	 *
	 */
	private void updateMessageNote(String[] messageNote, String avd, String tur, SystemaWebUser appUser){
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_UPDATE_SPECIFIC_TRIP_MESSAGE_NOTE_URL;
		//------------------
		//add URL-parameter
		//------------------
		String urlRequestParams = this.getRequestUrlKeyParameters(messageNote, avd, tur, appUser);
		logger.info("Updating messageNote...");
    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	//----------------------------------------------------------------------------
	    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
	    	//----------------------------------------------------------------------------
	    String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	    	//Debug --> 
	    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnTripUpdate(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
	    	}else{
	    		//Update successfully done!
	    		logger.info("[INFO] Record successfully updated, OK ");
	    	}
	    	
	}

	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFields(JsonTransportDispWorkflowSpecificTripRecord recordToValidate){
		//century-year
		String centYear = recordToValidate.getCenturyYearTurccTuraar();
		if(centYear!=null && !"".equals(centYear)){
			if(centYear.length()==4){
				recordToValidate.setTuracc(centYear.substring(0,2));
				recordToValidate.setTuraar(centYear.substring(2,4));
				//clean
				recordToValidate.setCenturyYearTurccTuraar(null);
			}
		}
		
		//----------------------------------
		//Check date-shortcuts (user dates)
		//----------------------------------
		//(1) If the user wrote 2 chars: user day. Example: user writes 29 then it will be <currentYear><currentMonyh>29 (ISO)
		//(2) If the user wrote 4 chars: user day and user month. Example: user writes 2910 then it will be <currentYear>1029 (ISO)
		recordToValidate.setTudt(this.dateTimeManager.adjustUserDateToISODate(recordToValidate.getTudt()));
		recordToValidate.setTudtt(this.dateTimeManager.adjustUserDateToISODate(recordToValidate.getTudtt()));
		//----------------------------------
		//Check time-shortcuts (user times)
		//----------------------------------
		recordToValidate.setTutm(this.dateTimeManager.adjustUserTimeToHHmm(recordToValidate.getTutm()));
		recordToValidate.setTutmt(this.dateTimeManager.adjustUserTimeToHHmm(recordToValidate.getTutmt()));
		
	}
	
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsImportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonTransportDispWorkflowSpecificTripRecord record){
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
	 * @param action
	 * @param avd
	 * @param tripNr
	 * @param mode
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(String action, String tuavd, String tupro, String mode, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		if(TransportDispConstants.ACTION_UPDATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			if(tuavd!=null){
				urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tuavd=" + tuavd);
			}
			if(tupro!=null){
				urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tupro=" + tupro);
			}
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + mode);
			
		}else if(TransportDispConstants.ACTION_CREATE.equals(action)){
			//TODO
		}else if(TransportDispConstants.ACTION_DELETE.equals(action)){
			//TODO
		}
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * @param messageNote
	 * @param avd
	 * @param appUser
	 * @return
	 */
	public String getRequestUrlKeyParameters(String[] messageNote, String avd, String tur, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		StringBuffer message = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(avd!=null){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsavd=" + avd);
		}
		if(tur!=null){
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wstur=" + tur);
		}
		List<String> list = Arrays.asList(messageNote);
		int i = 0;
		for (String record: list){
			i++;
			//System.out.println("BB:" + record);
			String x = "&frttxt0" + i + "=" + record;
			message.append(x);
		}
		//System.out.println("MESSAGE:" + message.toString());
		return urlRequestParamsKeys.toString() + message.toString();
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
	 * 
	 * @param session
	 * @param model
	 * @param container
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonTransportDispWorkflowSpecificTripRecord record){
		model.put(TransportDispConstants.DOMAIN_RECORD, record);
		//put the header topic in session for the coming item lines
		//session.setAttribute(TransportDispConstants.DOMAIN_RECORD_TOPIC_TRANSPORT_DISP, record);
	
	}
	
	
	
	/**
	 * 
	 * 
	 * @param model
	 * @param jsonTdsImportSpecificTopicRecord
	 */
	private void setDomainObjectsInView(Map model, JsonTransportDispWorkflowSpecificTripRecord record){
		//SET HEADER RECORDS  (from RPG)
		model.put(TransportDispConstants.DOMAIN_RECORD, record);
	}
	
	
	
	/**
	 * for update reasons in order to manage the 70-characters chunks on MessageNote
	 * 
	 * @param request
	 * @param jsonSadImportSpecificTopicRecord
	 */
	public String[] getChunksOfMessageNote(JsonTransportDispWorkflowSpecificTripRecord record){
		String[] records = new String[30];
		if(record!=null){
			String messageRaw = record.getMessageNote();
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
	
	@Qualifier 
	private TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService;
	@Autowired
	@Required	
	public void setTransportDispWorkflowSpecificTripService(TransportDispWorkflowSpecificTripService value){this.transportDispWorkflowSpecificTripService = value;}
	public TransportDispWorkflowSpecificTripService getTransportDispWorkflowSpecificTripService(){ return this.transportDispWorkflowSpecificTripService; }
	  
	
	@Qualifier ("transportDispDropDownListPopulationService")
	private TransportDispDropDownListPopulationService transportDispDropDownListPopulationService;
	@Autowired
	public void setTransportDispDropDownListPopulationService (TransportDispDropDownListPopulationService value){ this.transportDispDropDownListPopulationService=value; }
	public TransportDispDropDownListPopulationService getTransportDispDropDownListPopulationService(){return this.transportDispDropDownListPopulationService;}
	
	
		
}

