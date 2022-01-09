package no.systema.z.main.maintenance.controller.avd.sad;

import java.util.*;

import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
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
import no.systema.z.main.maintenance.service.sad.MaintMainTrkodfService;
import no.systema.z.main.maintenance.service.sad.MaintMainTrustdService;
import no.systema.z.main.maintenance.service.sad.MaintMainTrustdfvService;
import no.systema.z.main.maintenance.service.MaintMainEdiiService;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaKodthRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdfvContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdfvRecord;

import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.validator.sad.MaintMainTrustdValidator;
import no.systema.z.main.maintenance.util.manager.CodeDropDownMgr;

import no.systema.main.util.MainDateFormatter;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;

/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Sep 30, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdSadNctsExportTrustdController {
	private static final Logger logger = LoggerFactory.getLogger(MainMaintenanceAvdSadNctsExportTrustdController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private MainDateFormatter dateFormatter = new MainDateFormatter();
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="mainmaintenanceavdsadnctsexport_tr003r.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdsadnctsimport_tr003r (HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdsadnctsexport_tr003r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String id = request.getParameter("id");  //TRUST or TRUST_FHV, for correct selection
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdsadnctsexport_tr003r");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			//Get list
	 		List list = this.fetchList(appUser.getUser(), id);
			model.put("list", list);
			model.put("id", id);
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
	
	@RequestMapping(value="mainmaintenanceavdsadnctsexport_tr003r_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdsadnctsimport_tr003_edit(@ModelAttribute ("record") JsonMaintMainTrustdRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdsadnctsexport_tr003r_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String avd = request.getParameter("avd");
		String avdnavn = request.getParameter("avdnavn");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		JsonMaintMainTrustdfvRecord dbChildRecord = null;
		String id = request.getParameter("id");
		
		//bind child record (only for validation purposes, even in back-end)
		JsonMaintMainTrustdfvRecord sikkerhedChildRecord = this.bindChildSikkerhed(request);
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdsadnctsexport_tr003_edit");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			boolean isValidOnUpdate = true;
			//--------------
			//UPDATE record
			//--------------
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				
				avd = recordToValidate.getThavd();
				//Adjust
				this.adjustSomeRecordValues(recordToValidate, sikkerhedChildRecord);
				recordToValidate.setSikkerhedChildRecord(sikkerhedChildRecord);
				//Validate
				MaintMainTrustdValidator validator = new MaintMainTrustdValidator();
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
						if(dmlRetval >= 0){
							//check if this child record exists
							dbChildRecord = this.fetchChildRecordSikkerhed(appUser.getUser(), avd);
							if(dbChildRecord!=null && (dbChildRecord.getThavd()!=null && !"".equals(dbChildRecord.getThavd())) ){
								dmlRetval = this.updateChildRecord(appUser.getUser(), recordToValidate.getSikkerhedChildRecord(), MainMaintenanceConstants.MODE_UPDATE, errMsg);
							}else{
								dmlRetval = this.updateChildRecord(appUser.getUser(), recordToValidate.getSikkerhedChildRecord(), MainMaintenanceConstants.MODE_ADD, errMsg);
							}
						}
					}else{
						//create new
						logger.info(MainMaintenanceConstants.MODE_ADD);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
						if(dmlRetval >= 0){
							//check if this child record exists (it should NOT)
							dbChildRecord = this.fetchChildRecordSikkerhed(appUser.getUser(), avd);
							if(dbChildRecord!=null && (dbChildRecord.getThavd()!=null && !"".equals(dbChildRecord.getThavd())) ){
								//something is wrong (corrupt record)
								//TODO ...
							}else{
								//should be the default behavior
								dmlRetval = this.updateChildRecord(appUser.getUser(), recordToValidate.getSikkerhedChildRecord(), MainMaintenanceConstants.MODE_ADD, errMsg);
							}
						}
							
					}
					
					//check for Update errors
					if( dmlRetval < 0){
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
						isValidOnUpdate = false;
					}else{
						//post successful update operations
						updateId = recordToValidate.getThavd();
						
					}
					
				}
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				
				
			//DELETE	
			}else if(MainMaintenanceConstants.ACTION_DELETE.equals(action)){
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				logger.info(MainMaintenanceConstants.MODE_DELETE);
				dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_DELETE, errMsg);
				//remove child record
				if(dmlRetval >= 0){
					dmlRetval = this.updateChildRecord(appUser.getUser(), sikkerhedChildRecord, MainMaintenanceConstants.MODE_DELETE, errMsg);
				}
				
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}else{
					//post successful update operations
					successView = new ModelAndView("redirect:mainmaintenanceavdsadnctsexport_tr003r.do");
					
				}
			}
			//-------------
			//Fetch record
			//-------------
			if(isValidOnUpdate && (avd!=null && !"".equals(avd)) ){
				JsonMaintMainTrustdRecord record = this.fetchRecord(appUser.getUser(), avd);
				JsonMaintMainTrustdfvRecord childRecord = this.fetchChildRecordSikkerhed(appUser.getUser(), avd);
				if(childRecord!=null){
					record.setSikkerhedChildRecord(childRecord);
				}else{
					record.setSikkerhedChildRecord(new JsonMaintMainTrustdfvRecord());
				}
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
			model.put("id", id);
			
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    
			return successView;
			
		}
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @return
	 */
	private List<JsonMaintMainTrustdRecord> fetchList(String applicationUser, String id){
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_TR003R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&id="+id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainTrustdRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainTrustdContainer container = this.maintMainTrustdService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        }
    	}
    	return list;
    	
	}
	
	private JsonMaintMainTrustdRecord fetchRecord(String applicationUser, String avd){
		JsonMaintMainTrustdRecord record = new JsonMaintMainTrustdRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_TR003R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&thavd=" + avd;
		
		//logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	//this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainTrustdRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainTrustdContainer container = this.maintMainTrustdService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainTrustdRecord tmp : list){
	        		record = tmp;

	        	}
	        }
    	}
    	return record;
    	
	}
	
	/**
	 * Sikkerhed child record 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	private JsonMaintMainTrustdfvRecord fetchChildRecordSikkerhed(String applicationUser, String avd){
		JsonMaintMainTrustdfvRecord record = new JsonMaintMainTrustdfvRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_TR003fvR_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&thavd=" + avd;
		
		//logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	//this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainTrustdfvRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainTrustdfvContainer container = this.maintMainTrustdfvService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainTrustdfvRecord tmp : list){
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
	private int updateRecord(String applicationUser, JsonMaintMainTrustdRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_TR003R_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		//logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainTrustdContainer container = this.maintMainTrustdService.doUpdate(jsonPayload);
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
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateChildRecord(String applicationUser, JsonMaintMainTrustdfvRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_TR003fvR_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		//logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainTrustdfvContainer container = this.maintMainTrustdfvService.doUpdate(jsonPayload);
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
	 * @param sikkerhedChildRecord
	 */
	private void adjustSomeRecordValues(JsonMaintMainTrustdRecord recordToValidate, JsonMaintMainTrustdfvRecord sikkerhedChildRecord){
		//----------
		//Dates
		//----------
		if(recordToValidate.getThddt()!=null && !"".equals(recordToValidate.getThddt()) ){
			if(recordToValidate.getThddt().length()== 6){
				recordToValidate.setThddt(dateFormatter.convertToDate_ISO(recordToValidate.getThddt()));
			}
		}
		
		if(sikkerhedChildRecord.getThdta()!=null && !"".equals(sikkerhedChildRecord.getThdta()) ){
			if(sikkerhedChildRecord.getThdta().length()== 6){
				sikkerhedChildRecord.setThdta(dateFormatter.convertToDate_ISO(sikkerhedChildRecord.getThdta()));
			}
		}
		
	}
	
	/**
	 * 
	 * @param model
	 * @param applicationUser
	 */
	private void populateDropDowns(Map model, String applicationUser){
		this.codeDropDownMgr.populateCurrencyCodesHtmlDropDownsSad(this.urlCgiProxyService, this.maintKodtvaService, model, applicationUser);
		this.codeDropDownMgr.populateAvdListHtmlDropDownsSad(this.urlCgiProxyService, this.maintMainKodtaService, model, applicationUser, "nealist");
		//Code lists in NCTS domain
		this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsNcts(this.urlCgiProxyService, this.maintMainTrkodfService, model, applicationUser, MainMaintenanceConstants.CODE_NCTS_SIKKERHET_096_SPES_OMSTAND);
		this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsNcts(this.urlCgiProxyService, this.maintMainTrkodfService, model, applicationUser, MainMaintenanceConstants.CODE_NCTS_SIKKERHET_116_TRANSP_KOST_BETAL_MATE);
		//Borrowed from TVINN domain
		this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsSadKodts4(this.urlCgiProxyService, this.maintSadImportKodts4Service, model, applicationUser, MainMaintenanceConstants.CODE_SAD_4_TRANSPORTMATE);
		this.codeDropDownMgr.populateDeklarationsTyperHtmlDropDown(this.urlCgiProxyService, this.maintMainTrkodfService, model, applicationUser);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	private JsonMaintMainTrustdfvRecord bindChildSikkerhed (HttpServletRequest request){
		JsonMaintMainTrustdfvRecord record = new JsonMaintMainTrustdfvRecord();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(record);
		binder.bind(request);
		return record;
	}
	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintMainTrustdService")
	private MaintMainTrustdService maintMainTrustdService;
	@Autowired
	@Required
	public void setMaintMainTrustdService (MaintMainTrustdService value){ this.maintMainTrustdService = value; }
	public MaintMainTrustdService getMaintMainTrustdService(){ return this.maintMainTrustdService; }
	
	@Qualifier ("maintMainTrustdfvService")
	private MaintMainTrustdfvService maintMainTrustdfvService;
	@Autowired
	@Required
	public void setMaintMainTrustdfvService (MaintMainTrustdfvService value){ this.maintMainTrustdfvService = value; }
	public MaintMainTrustdfvService getMaintMainTrustdfvService(){ return this.maintMainTrustdfvService; }
	
	
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
	
	
	@Qualifier ("maintMainEdiiService")
	private MaintMainEdiiService maintMainEdiiService;
	@Autowired
	@Required
	public void setMaintMainEdiiService (MaintMainEdiiService value){ this.maintMainEdiiService = value; }
	public MaintMainEdiiService getMaintMainEdiiService(){ return this.maintMainEdiiService; }
	
	
	@Qualifier ("maintMainTrkodfService")
	private MaintMainTrkodfService maintMainTrkodfService;
	@Autowired
	@Required
	public void setMaintMainTrkodfService (MaintMainTrkodfService value){ this.maintMainTrkodfService = value; }
	public MaintMainTrkodfService getMaintMainTrkodfService(){ return this.maintMainTrkodfService; }
	
	@Qualifier ("maintSadImportKodts4Service")
	private MaintSadImportKodts4Service maintSadImportKodts4Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts4Service (MaintSadImportKodts4Service value){ this.maintSadImportKodts4Service = value; }
	public MaintSadImportKodts4Service getMaintSadImportKodts4Service(){ return this.maintSadImportKodts4Service; }
	
	
	
}

