package no.systema.z.main.maintenance.controller.avd.sad;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.external.tvinn.sad.z.maintenance.service.MaintKodtvaService;
import no.systema.external.tvinn.sad.z.maintenance.service.MaintSadExportKodts9Service;
import no.systema.external.tvinn.sad.z.maintenance.service.MaintSadFellesKodtlbService;
import no.systema.external.tvinn.sad.z.maintenance.service.MaintSadImportKodts4Service;
//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.DateTimeManager;

//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.service.sad.MaintMainStandeService;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainStandeContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainStandeRecord;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.validator.sad.MaintMainStandeValidator;
import no.systema.z.main.maintenance.util.manager.CodeDropDownMgr;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;

/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Sep 16, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdSadExportStandeController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdSadExportStandeController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="mainmaintenanceavdsadexport_syftaaar.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_syfa14r(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdsadexport_syftaaar");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdsadexport_syftaaar");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			//Get list
	 		List list = this.fetchList(appUser.getUser());
			model.put("list", list);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    
			return successView;
			
		}
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="mainmaintenanceavdsadexport_syftaaar_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdsadexport_syftaaar_edit(@ModelAttribute ("record") JsonMaintMainStandeRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdsadexport_syftaaar_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String avd = request.getParameter("avd");
		String avdnavn = request.getParameter("avdnavn");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdsadexport_syftaaar_edit");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			boolean isValidOnUpdate = true;
			//--------------
			//UPDATE record
			//--------------
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				avd = recordToValidate.getSeavd();
				//Validate
				this. adjustSomeRecordValues(recordToValidate);
				
				MaintMainStandeValidator validator = new MaintMainStandeValidator();
				validator.validate(recordToValidate, bindingResult);
				if(bindingResult.hasErrors()){
					//ERRORS
					logger.info("[ERROR Validation] Record does not validate)");
					//model.put("dbTable", dbTable);
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					isValidOnUpdate = false;
				}else{
					
					//Update table
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(MainMaintenanceConstants.MODE_UPDATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
					}else{
						//create new
						logger.info(MainMaintenanceConstants.MODE_ADD);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
						
					}
					
					//check for Update errors
					if( dmlRetval < 0){
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
						isValidOnUpdate = false;
					}else{
						//post successful update operations
						updateId = recordToValidate.getSeavd();
						
					}
					
				}
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				
				
			//DELETE	
			}else if(MainMaintenanceConstants.ACTION_DELETE.equals(action)){
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				
				logger.info(MainMaintenanceConstants.MODE_DELETE);
				dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_DELETE, errMsg);
				
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}else{
					//post successful update operations
					successView = new ModelAndView("redirect:mainmaintenanceavdsadexport_syftaaar.do");
					
				}
			}
			//-------------
			//Fetch record
			//-------------
			if(isValidOnUpdate && (avd!=null && !"".equals(avd)) ){
				JsonMaintMainStandeRecord record = this.fetchRecord(appUser.getUser(), avd);
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
			}
			
			//populate model
			if(action==null || "".equals(action)){
				action = "doUpdate";
			}
			this.populateDropDowns(model, appUser.getUser());
			model.put("action", action);
			model.put("avd", avd);
			model.put("avdnavn", avdnavn);
			model.put("updateId", updateId);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    
			return successView;
			
		}
	}
	
	/**
	 * 
	 * @param model
	 * @param applicationUser
	 */
	private void populateDropDowns(Map model, String applicationUser){
		this.codeDropDownMgr.populateCurrencyCodesHtmlDropDownsSad(this.urlCgiProxyService, this.maintKodtvaService, model, applicationUser);
		this.codeDropDownMgr.populateAvdListHtmlDropDownsSad(this.urlCgiProxyService, this.maintMainKodtaService, model, applicationUser, "sealist");
		//Borrowed from TVINN domain
		this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsSadKodts4(this.urlCgiProxyService, this.maintSadImportKodts4Service, model, applicationUser, MainMaintenanceConstants.CODE_SAD_4_TRANSPORTMATE);
		//Incoterms
		this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsSad012Incoterms (this.urlCgiProxyService, this.maintSadImportKodtlbService, model, applicationUser);
		//
		this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsSad002EkspedtyperExport(urlCgiProxyService, this.maintSadExportKodts9Service, model, applicationUser);
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @return
	 */
	private List<JsonMaintMainStandeRecord> fetchList(String applicationUser){
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFTAAAER_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainStandeRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainStandeContainer container = this.maintMainStandeService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private JsonMaintMainStandeRecord fetchRecord(String applicationUser, String id){
		JsonMaintMainStandeRecord record = new JsonMaintMainStandeRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFTAAAER_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&seavd=" + id;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainStandeRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainStandeContainer container = this.maintMainStandeService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainStandeRecord tmp : list){
	        		record = tmp;

	        	}
	        }
    	}
    	return record;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintMainStandeRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFTAAAER_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainStandeContainer container = this.maintMainStandeService.doUpdate(jsonPayload);
	        if(container!=null){
	        	if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	        		if(container.getErrMsg().toUpperCase().startsWith("ERROR")){
	        			errMsg.append(container.getErrMsg());
	        			retval = MainMaintenanceConstants.ERROR_CODE;
	        		}
	        	}
	        }
    	}    	
    	return retval;
	}
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustSomeRecordValues(JsonMaintMainStandeRecord recordToValidate){
		//----------
		//Dates
		//----------
		/*
		if(recordToValidate.getSidt()!=null && !"".equals(recordToValidate.getSidt()) ){
			if(recordToValidate.getSidt().length()< 6){
				recordToValidate.setSidt(this.dateTimeMgr.getCurrentDate_ISO());
			}
		}else{
			recordToValidate.setSidt(this.dateTimeMgr.getCurrentDate_ISO());
		}
		*/	
		
	}
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintMainStandeService")
	private MaintMainStandeService maintMainStandeService;
	@Autowired
	@Required
	public void setMaintMainStandeService (MaintMainStandeService value){ this.maintMainStandeService = value; }
	public MaintMainStandeService getMaintMainStandeService(){ return this.maintMainStandeService; }
	
	
	@Qualifier ("maintKodtvaService")
	private MaintKodtvaService maintKodtvaService;
	@Autowired
	@Required
	public void setMaintKodtvaService (MaintKodtvaService value){ this.maintKodtvaService = value; }
	public MaintKodtvaService getMaintKodtvaService(){ return this.maintKodtvaService; }
	
	
	@Qualifier ("maintMainKodtaService")
	private MaintMainKodtaService maintMainKodtaService;
	@Autowired
	@Required
	public void setMaintMainKodtaService (MaintMainKodtaService value){ this.maintMainKodtaService = value; }
	public MaintMainKodtaService getMaintMainKodtaService(){ return this.maintMainKodtaService; }
	
	@Qualifier ("maintSadImportKodts4Service")
	private MaintSadImportKodts4Service maintSadImportKodts4Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts4Service (MaintSadImportKodts4Service value){ this.maintSadImportKodts4Service = value; }
	public MaintSadImportKodts4Service getMaintSadImportKodts4Service(){ return this.maintSadImportKodts4Service; }
	
	@Qualifier ("maintSadImportKodtlbService")
	private MaintSadFellesKodtlbService maintSadImportKodtlbService;
	@Autowired
	@Required
	public void setMaintSadImportKodtsiService (MaintSadFellesKodtlbService value){ this.maintSadImportKodtlbService = value; }
	public MaintSadFellesKodtlbService getMaintSadImportKodtsiService(){ return this.maintSadImportKodtlbService; }
	
	@Qualifier ("maintSadExportKodts9Service")
	private MaintSadExportKodts9Service maintSadExportKodts9Service;
	@Autowired
	@Required
	public void setMaintSadExportKodts9Service (MaintSadExportKodts9Service value){ this.maintSadExportKodts9Service = value; }
	public MaintSadExportKodts9Service getMaintSadExportKodts9Service(){ return this.maintSadExportKodts9Service; }
	
	

}

