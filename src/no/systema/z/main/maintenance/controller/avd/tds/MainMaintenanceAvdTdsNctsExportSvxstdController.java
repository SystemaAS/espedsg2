package no.systema.z.main.maintenance.controller.avd.tds;

import java.util.*;

import org.apache.log4j.Logger;
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

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.DateTimeManager;

//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.service.tds.MaintMainSvxstdService;
import no.systema.z.main.maintenance.service.tds.MaintMainSvxstdfvService;
import no.systema.z.main.maintenance.service.MaintMainEdiiService;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSvxstdContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSvxstdRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSvxstdfvContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSvxstdfvRecord;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.validator.tds.MaintMainSvxstdValidator;
import no.systema.z.main.maintenance.util.manager.CodeDropDownMgrTds;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;

import no.systema.external.tds.z.maintenance.service.MaintSvtvkService;
import no.systema.external.tds.z.maintenance.service.MaintSvxkodfService;


/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Jun 21, 2017
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdTdsNctsExportSvxstdController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdTdsNctsExportSvxstdController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgrTds codeDropDownMgr = new CodeDropDownMgrTds();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="mainmaintenanceavdtdsnctsexport_svx003r.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdtdsnctsexport_svx003r (HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdtdsnctsexport_svx003r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String id = request.getParameter("id"); 
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdtdsnctsexport_svx003r");
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
	
	@RequestMapping(value="mainmaintenanceavdtdsnctsexport_svx003r_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdtdsnctsexport_svx003_edit(@ModelAttribute ("record") JsonMaintMainSvxstdRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdtdsnctsexport_svx003r_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String avd = request.getParameter("avd");
		String avdnavn = request.getParameter("avdnavn");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		JsonMaintMainSvxstdfvRecord dbChildRecord = null;
		String id = request.getParameter("id");
		
		//bind child record (only for validation purposes, even in back-end)
		JsonMaintMainSvxstdfvRecord sakerhetChildRecord = this.bindChildSakerhet(request);
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdtdsnctsexport_svx003r_edit");
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
				this.adjustSomeRecordValues(recordToValidate, sakerhetChildRecord);
				recordToValidate.setSakerhetChildRecord(sakerhetChildRecord);
				//Validate
				MaintMainSvxstdValidator validator = new MaintMainSvxstdValidator();
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
							dbChildRecord = this.fetchChildRecordSakerhet(appUser.getUser(), avd);
							if(dbChildRecord!=null && (dbChildRecord.getThavd()!=null && !"".equals(dbChildRecord.getThavd())) ){
								dmlRetval = this.updateChildRecord(appUser.getUser(), recordToValidate.getSakerhetChildRecord(), MainMaintenanceConstants.MODE_UPDATE, errMsg);
							}else{
								dmlRetval = this.updateChildRecord(appUser.getUser(), recordToValidate.getSakerhetChildRecord(), MainMaintenanceConstants.MODE_ADD, errMsg);
							}
						}
					}else{
						//create new
						logger.info(MainMaintenanceConstants.MODE_ADD);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
						if(dmlRetval >= 0){
							//check if this child record exists (it should NOT)
							dbChildRecord = this.fetchChildRecordSakerhet(appUser.getUser(), avd);
							if(dbChildRecord!=null && (dbChildRecord.getThavd()!=null && !"".equals(dbChildRecord.getThavd())) ){
								//something is wrong (corrupt record)
								//TODO ...
							}else{
								//should be the default behavior
								dmlRetval = this.updateChildRecord(appUser.getUser(), recordToValidate.getSakerhetChildRecord(), MainMaintenanceConstants.MODE_ADD, errMsg);
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
					dmlRetval = this.updateChildRecord(appUser.getUser(), sakerhetChildRecord, MainMaintenanceConstants.MODE_DELETE, errMsg);
				}
				
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}else{
					//post successful update operations
					successView = new ModelAndView("redirect:mainmaintenanceavdtdsnctsexport_svx003r.do?id=SVXSTD");
					
				}
			}
			//-------------
			//Fetch record
			//-------------
			if(isValidOnUpdate && (avd!=null && !"".equals(avd)) ){
				JsonMaintMainSvxstdRecord record = this.fetchRecord(appUser.getUser(), avd);
				JsonMaintMainSvxstdfvRecord childRecord = this.fetchChildRecordSakerhet(appUser.getUser(), avd);
				if(childRecord!=null){
					record.setSakerhetChildRecord(childRecord);
				}else{
					record.setSakerhetChildRecord(new JsonMaintMainSvxstdfvRecord());
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
	private List<JsonMaintMainSvxstdRecord> fetchList(String applicationUser, String id){
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVX003R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&id="+id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainSvxstdRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainSvxstdContainer container = this.maintMainSvxstdService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        }
    	}
    	return list;
    	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	private JsonMaintMainSvxstdRecord fetchRecord(String applicationUser, String avd){
		JsonMaintMainSvxstdRecord record = new JsonMaintMainSvxstdRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVX003R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&thavd=" + avd;
		
		//logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	//this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainSvxstdRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainSvxstdContainer container = this.maintMainSvxstdService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainSvxstdRecord tmp : list){
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
	private JsonMaintMainSvxstdfvRecord fetchChildRecordSakerhet(String applicationUser, String avd){
		JsonMaintMainSvxstdfvRecord record = new JsonMaintMainSvxstdfvRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVX003fvR_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&thavd=" + avd;
		
		//logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	//this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainSvxstdfvRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainSvxstdfvContainer container = this.maintMainSvxstdfvService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainSvxstdfvRecord tmp : list){
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
	private int updateRecord(String applicationUser, JsonMaintMainSvxstdRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVX003R_DML_UPDATE_URL;
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
    		JsonMaintMainSvxstdContainer container = this.maintMainSvxstdService.doUpdate(jsonPayload);
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
	private int updateChildRecord(String applicationUser, JsonMaintMainSvxstdfvRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVX003fvR_DML_UPDATE_URL;
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
    		JsonMaintMainSvxstdfvContainer container = this.maintMainSvxstdfvService.doUpdate(jsonPayload);
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
	private void adjustSomeRecordValues(JsonMaintMainSvxstdRecord recordToValidate, JsonMaintMainSvxstdfvRecord sikkerhedChildRecord){
		//----------
		//Dates
		//----------
		/*N/A for DK
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
		*/
	}
	
	/**
	 * 
	 * @param model
	 * @param applicationUser
	 */
	private void populateDropDowns(Map model, String applicationUser){
		this.codeDropDownMgr.populateAvdListHtmlDropDownsTds(this.urlCgiProxyService, this.maintMainKodtaService, model, applicationUser, "tnealist");
		
		//TODO !!!
		//this.codeDropDownMgr.populateCurrencyCodesHtmlDropDownsSkat(this.urlCgiProxyService, this.maintDktvkService, model, applicationUser);
		//Code lists in NCTS domain
		//this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsNcts(this.urlCgiProxyService, this.maintSvxkodfService, model, applicationUser, MainMaintenanceConstants.CODE_NCTS_SIKKERHET_096_SPES_OMSTAND);
		//this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsNcts(this.urlCgiProxyService, this.maintDkxkodfService, model, applicationUser, MainMaintenanceConstants.CODE_NCTS_SIKKERHET_116_TRANSP_KOST_BETAL_MATE);
		//this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsNcts(this.urlCgiProxyService, this.maintDkxkodfService, model, applicationUser, MainMaintenanceConstants.CODE_SKAT_NCTS_EXPORT_108_TRANSPORTMATE);
		//this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsNcts(this.urlCgiProxyService, this.maintDkxkodfService, model, applicationUser, MainMaintenanceConstants.CODE_NCTS_DEKLARASJONS_TYPE);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	private JsonMaintMainSvxstdfvRecord bindChildSakerhet (HttpServletRequest request){
		JsonMaintMainSvxstdfvRecord record = new JsonMaintMainSvxstdfvRecord();
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
	
	
	@Qualifier ("maintMainSvxstdService")
	private MaintMainSvxstdService maintMainSvxstdService;
	@Autowired
	@Required
	public void setMaintMainSvxstdService (MaintMainSvxstdService value){ this.maintMainSvxstdService = value; }
	public MaintMainSvxstdService getMaintMainSvxstdService(){ return this.maintMainSvxstdService; }
	
	@Qualifier ("maintMainSvxstdfvService")
	private MaintMainSvxstdfvService maintMainSvxstdfvService;
	@Autowired
	@Required
	public void setMaintMainSvxstdfvService (MaintMainSvxstdfvService value){ this.maintMainSvxstdfvService = value; }
	public MaintMainSvxstdfvService getMaintMainSvxstdfvService(){ return this.maintMainSvxstdfvService; }
	
	
	@Qualifier ("maintSvtvkService")
	private MaintSvtvkService maintSvtvkService;
	@Autowired
	@Required
	public void setMaintSvtvkService (MaintSvtvkService value){ this.maintSvtvkService = value; }
	public MaintSvtvkService getMaintSvtvkService(){ return this.maintSvtvkService; }
	
	
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
	

}

