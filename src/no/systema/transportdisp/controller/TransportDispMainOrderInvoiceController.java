package no.systema.transportdisp.controller;

import java.lang.reflect.Field;
import java.util.*;
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

import no.systema.main.model.SystemaWebUser;
//TRANSPDISP
import no.systema.transportdisp.util.RpgReturnResponseHandler;
import no.systema.transportdisp.util.TransportDispDateTimeFormatter;
import no.systema.transportdisp.util.TransportDispPercentageFormatter;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.util.manager.CodeDropDownMgr;
import no.systema.transportdisp.service.TransportDispChildWindowService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificOrderService;
import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;
import no.systema.transportdisp.mapper.url.request.UrlRequestParameterMapper;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeRecord;
import no.systema.transportdisp.validator.TransportDispWorkflowSpecificOrderInvoiceValidator;

/**
 * TransportDisp Main Order Controller 
 * 
 * @author oscardelatorre
 * @date Aug 13, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TransportDispMainOrderInvoiceController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private static Logger logger = Logger.getLogger(TransportDispMainOrderInvoiceController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	private TransportDispDateTimeFormatter dateTimeFormatter = new TransportDispDateTimeFormatter();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private MessageNoteManager messageNoteMgr = new MessageNoteManager();
	private TransportDispPercentageFormatter percentageFormatter = new TransportDispPercentageFormatter();
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	private String MESSAGE_NOTE_CONSIGNEE = "R";
	private String MESSAGE_NOTE_CARRIER = "G";
	private String MESSAGE_NOTE_INTERNAL = "b";
	//
	private String INVOICE_ITEM_LINE_GROUP_TOTAL_RECORD_LEGEND = "TOTAL";
	private String INVOICE_MINUS_CHARACTER = "-";
	
	
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
	@RequestMapping(value="transportdisp_mainorder_invoice.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doGetInvoice(@ModelAttribute ("record") JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("transportdisp_mainorder_invoice");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		String parentTrip = recordToValidate.getHepro();
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			if(recordToValidate.getHeavd()!=null && !"".equals(recordToValidate.getHeavd()) && 
				recordToValidate.getHeopd()!=null && !"".equals(recordToValidate.getHeopd())){
		    	final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_INVOICE_URL;
		    	//http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
		    	
	    		//add URL-parameters
	    		StringBuffer urlRequestParams = new StringBuffer();
	    		urlRequestParams.append("user=" + appUser.getUser());
	    		urlRequestParams.append("&avd=" + recordToValidate.getHeavd());
	    		urlRequestParams.append("&opd=" + recordToValidate.getHeopd());
	    		urlRequestParams.append("&lin="); //always blank=all lines
	    		//I=Vis kun Inntektslinjer=kun linjer for utgående faktura vises (ikke kostnader  / ikke slettemerkedede)
	    		//O = Open = Vis kun ÅPNE inntekstlinjer = Linjer som ennå ikke er fakturert / kan endres.. 
	    		//A=Vis ALLE linjer (vis inntektslinjer uavhengig av status OG også kostnadslinjer...) 
	    		urlRequestParams.append("&type=" + recordToValidate.getItemsType()); 
	    		
	    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
			    String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
			    //Debug --> 
			  	//logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
			  	logger.debug(this.jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    		if(jsonPayload!=null){
	    			JsonTransportDispWorkflowSpecificOrderInvoiceContainer container = this.transportDispWorkflowSpecificOrderService.getOrderInvoiceContainer(jsonPayload);
	    			if(container!=null){
	    				//ready mark status
	    				JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer readyMarkContainer = this.getReadyMark(appUser, recordToValidate.getHeavd(), recordToValidate.getHeopd());
	    				container.setReadyMarkStatus(readyMarkContainer.getStatus());
	    				//set domain object
	    				this.setDomainObjectsInView(model, container, session);
	    			}
	    		}
	    		
	    		
			}
			
			//populate drop downs
			this.setCodeDropDownMgr(appUser, model);
			this.setDropDownsFromFiles(model);
			
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
	 * Create new, update or delete invoice item line
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_mainorder_invoice_edit.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditInvoice(@ModelAttribute ("record") JsonTransportDispWorkflowSpecificOrderInvoiceRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		String action = request.getParameter("action");
		String heavd = request.getParameter("heavd");
		String heopd = request.getParameter("heopd");
		String parentTrip = request.getParameter("hepro");
		logger.info("ACTION: " + action);
		
		//ModelAndView successView = new ModelAndView("transportdisp_mainorder_invoice");
		ModelAndView successView = new ModelAndView("redirect:transportdisp_mainorder_invoice.do?action=doFind&heavd=" + heavd + "&heopd=" + heopd + "&itemsType=O");
		ModelAndView errorView = new ModelAndView("transportdisp_mainorder_invoice");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
					
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			String lineId = recordToValidate.getFali();
			
			if(TransportDispConstants.ACTION_UPDATE.equals(action)){
				logger.info("[INFO] doUpdate action ...");
				TransportDispWorkflowSpecificOrderInvoiceValidator validator = new TransportDispWorkflowSpecificOrderInvoiceValidator();
				//Fetch some validation conditions
				this.setGebyrMomsCode(appUser, recordToValidate);
				//Validate
				validator.validate(recordToValidate, bindingResult);
				//check for ERRORS
				if(bindingResult.hasErrors()){
					
					logger.info("[ERROR Validation] Record does not validate)");
			    	logger.info("[INFO lineId] " + lineId);
			    	//bindingErrorsExist = true;
			    	//populate drop downs
					this.setCodeDropDownMgr(appUser, model);
					this.setDropDownsFromFiles(model);
			    	//return objects on validation errors
			    	model.put(TransportDispConstants.DOMAIN_CONTAINER, session.getAttribute(session.getId() + TransportDispConstants.DOMAIN_CONTAINER));
			    	model.put(TransportDispConstants.DOMAIN_LIST, session.getAttribute(session.getId() + TransportDispConstants.DOMAIN_LIST));
			    	model.put("record", recordToValidate);
			    	model.put("parentTrip", parentTrip);
			    	//return model
			    	errorView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
			    	return errorView;
			    	
				}else{
					String MODE = "U";
					if(recordToValidate.getFali()!=null && !"".equals(recordToValidate.getFali())){
						//UPDATE
						logger.info("[INFO] UPDATE line nr: " + lineId + " start process... ");
					}else{
						//CREATE NEW = ADD
						MODE = "A";
						logger.info("[INFO] CREATE NEW line nr: " + lineId + " start process... ");
					}
					//-------------------------------
					//Execute back-end Update/Create
					//-------------------------------
					JsonTransportDispWorkflowSpecificOrderInvoiceContainer container = this.executeUpdateLine(appUser, recordToValidate, MODE);
					if(container!=null){
	    				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	    					this.populateAspectsOnBackendError(appUser, container, recordToValidate, model, parentTrip, session);
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
				JsonTransportDispWorkflowSpecificOrderInvoiceContainer container = this.executeUpdateLine(appUser, recordToValidate, DELETE_MODE);
				if(container!=null){
    				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
    					this.populateAspectsOnBackendError(appUser, container, recordToValidate, model, parentTrip, session);
    			    	errorView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
    					return errorView;
    				}else{
    					//Delete succefully done!
    		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
    				}
    			}
			}
		}
		
		return successView;
	}
	/**
	 * Execute update
	 * @param appUser
	 * @param recordToValidate
	 * @return
	 */
	private JsonTransportDispWorkflowSpecificOrderInvoiceContainer executeUpdateLine(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderInvoiceRecord recordToValidate, String mode ){
		JsonTransportDispWorkflowSpecificOrderInvoiceContainer retval = null;
		
		logger.info("[INFO] EXECUTE Update(D/A/U) line nr:" + recordToValidate.getFali() + " start process... ");
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_INVOICE_URL;
    	//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser()); 
		urlRequestParams.append("&avd=" + recordToValidate.getHeavd());
		urlRequestParams.append("&opd=" + recordToValidate.getHeopd());
		urlRequestParams.append("&lin=");
		if(recordToValidate.getFali()!=null && !"".equals(recordToValidate.getFali())){ urlRequestParams.append(recordToValidate.getFali()); }
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
			JsonTransportDispWorkflowSpecificOrderInvoiceContainer container = this.transportDispWorkflowSpecificOrderService.getOrderInvoiceContainer(jsonPayload);
			retval = container;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 * @return
	 */
	private JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer getReadyMark(SystemaWebUser appUser, String avd, String opd ){
		JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer retval = null;
		
		logger.info(" Ready mark start process... ");
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_STATUS_READYMARK_MAIN_ORDER_INVOICE_URL;
    	//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser()); 
		urlRequestParams.append("&avd=" + avd);
		urlRequestParams.append("&opd=" + opd);
		urlRequestParams.append("&mode=G");
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	
		if(jsonPayload!=null){
			JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer container = this.transportDispWorkflowSpecificOrderService.getOrderInvoiceReadyMarkContainer(jsonPayload);
			retval = container;
		}
		return retval;
	}
	/**
	 * 
	 * @param appUser
	 * @param container
	 * @param recordToValidate
	 * @param model
	 * @param parentTrip
	 * @param session
	 */
	private void populateAspectsOnBackendError(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderInvoiceContainer container, JsonTransportDispWorkflowSpecificOrderInvoiceRecord recordToValidate, Map model, String parentTrip, HttpSession session ){
		model.put(TransportDispConstants.ASPECT_ERROR_MESSAGE, "Linenr:[" + container.getLin() + "] " +  container.getErrMsg());
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
	private void setGebyrMomsCode(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderInvoiceRecord recordToValidate){
		//prepare the access CGI with RPG back-end
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_GEBYR_CODES_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&kode=" + recordToValidate.getFavk() + "&getval=J";
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
	 * @param model
	 * @param container
	 * @param session
	 */
	private void setDomainObjectsInView(Map model, JsonTransportDispWorkflowSpecificOrderInvoiceContainer container, HttpSession session){
		List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> list = new ArrayList<JsonTransportDispWorkflowSpecificOrderInvoiceRecord>();
		//could be two options
		if(container.getInvoiceLineUpdate()!=null){
			for (JsonTransportDispWorkflowSpecificOrderInvoiceRecord record: container.getInvoiceLineUpdate()){
				//DEBUG logger.info("A RECORD:" + record.getFask());
				list.add(record);
			}
		}else if(container.getInvoiceLines()!=null){
			for (JsonTransportDispWorkflowSpecificOrderInvoiceRecord record: container.getInvoiceLines()){
				//DEBUG logger.info("A RECORD:" + record.getFask());
				list.add(record);
			}
		}
		//[1] Sort the list after: fask(code part (S,K,X,etc) and fafakt(inv.nr)
		Collections.sort(list, new JsonTransportDispWorkflowSpecificOrderInvoiceRecord.OrderByCodeAndInvoiceNr());
		if(list!=null){
			logger.info("TripleAAA:" + list.size());
		}
		//[1.1] Group the list in sub-lists of related records
		Map listGroupsMap = this.setListGroups(list);
		//[2] Set totals in each group
		list = this.getListWithTotals(listGroupsMap);
		
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
	 * @param listGroupsMap
	 * @return
	 */
	private List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> getListWithTotals(Map<Integer, List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord>> listGroupsMap){
		List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> listWithTotals = new ArrayList<JsonTransportDispWorkflowSpecificOrderInvoiceRecord>();
		int TWO_DECIMALS = 2;
		boolean DECIMAL_THOUSAND_FORMAT = false;
		String LOCALE_NORWAY = "no";
		boolean isCostRecord = false;
		
		//Iterate through the Map of lists
		Iterator it = listGroupsMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        //logger.info(pair.getKey() + " = " + pair.getValue());
	        List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> group = (List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord>)pair.getValue();
	        Double fablnTotal = 0.00D;
	        for (JsonTransportDispWorkflowSpecificOrderInvoiceRecord groupRecord : group){
	        	if(groupRecord.getFabeln()!=null && !"".equals(groupRecord.getFabeln())){
	        		if(groupRecord.getFabeln().contains("-")){
	        			//negative sign on back-end (credit note)
	        			groupRecord.getFabeln().replace("-", "");
	        			//make it negative and update the total sum negatively
	        			fablnTotal += -1 * this.getDoubleField(groupRecord.getFabeln());
	        		}else{
	        			fablnTotal += this.getDoubleField(groupRecord.getFabeln());
	        		}
	        		if("K".equals(groupRecord.getFakda())){
	        			isCostRecord = true;
	        		}
	        	}else{
	        		if(this.INVOICE_ITEM_LINE_GROUP_TOTAL_RECORD_LEGEND.equals(groupRecord.getFaVT())){
	        			//add the total to this group
	        			groupRecord.setFabeln(this.numberFormatter.getString(fablnTotal, TWO_DECIMALS, DECIMAL_THOUSAND_FORMAT, LOCALE_NORWAY));
	        			//add the "-" character at the end of the string to comply to the cost-field formatting
	        			if(isCostRecord){ 
	        				groupRecord.setFabeln(groupRecord.getFabeln() + this.INVOICE_MINUS_CHARACTER);
	        				isCostRecord = false;
        				}
	        			fablnTotal = 0.00D;
	        		}else{
	        			fablnTotal += 0;
	        		}
	        	
	        	}
	        	listWithTotals.add(groupRecord);
	        }
	    }
		return listWithTotals; 
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	private Map<Integer,List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord>> setListGroups(List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> list){
		Map<Integer,List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord>> map = new HashMap<Integer, List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord>>();
		List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> newList = new ArrayList<JsonTransportDispWorkflowSpecificOrderInvoiceRecord>();
		String previousCode = "";
		int index = 0;
		Integer mapIndex = 0;
		for (JsonTransportDispWorkflowSpecificOrderInvoiceRecord record: list){
			//logger.info("Inside:" + record.getFabeln());
			if (index==0){
				previousCode = record.getFask() + record.getFafakt();
				newList.add(record);
				map.put(mapIndex, newList);
			}else{
				if (previousCode.equals(record.getFask() + record.getFafakt())){
					newList.add(record);
					//add the total record for the last record
					if(index + 1==list.size()){
						//add the total record
						newList.add(this.addTotalRecord());
						map.put(mapIndex, newList);
					}
				}else{
					//add the total record
					newList.add(this.addTotalRecord());
					//logger.info("populating map with list:" + newList.size());
					map.put(mapIndex, newList);
					//init new list with this very current record
					mapIndex++;
					newList = new ArrayList<JsonTransportDispWorkflowSpecificOrderInvoiceRecord>();
					newList.add(record);
					map.put(mapIndex, newList);
				}
				previousCode = record.getFask() + record.getFafakt();
			}
			index++;
		}
		return map; 
	}
	/**
	 * 
	 * @return
	 */
	private JsonTransportDispWorkflowSpecificOrderInvoiceRecord addTotalRecord(){
		JsonTransportDispWorkflowSpecificOrderInvoiceRecord record = new JsonTransportDispWorkflowSpecificOrderInvoiceRecord();
		record.setFaVT(this.INVOICE_ITEM_LINE_GROUP_TOTAL_RECORD_LEGEND);
		return record;
	}
	
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private Double getDoubleField(String value){
		Double retval = 0.00D;
		retval = this.numberFormatter.getDouble(this.getAbsoluteValue(value));
		return retval;
	}
	/**
	 * Wash some invalid signs
	 * @param value
	 * @return
	 */
	private String getAbsoluteValue(String value){
		String retval = value;
		int minus = value.indexOf(this.INVOICE_MINUS_CHARACTER);
		if(minus > -1){
			retval = value.replace(this.INVOICE_MINUS_CHARACTER, "");
			
		}
		
		return retval;
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
	
	
}

