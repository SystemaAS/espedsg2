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
//import no.systema.z.main.maintenance.service.tds.MaintMainSvxstdfvService;
import no.systema.z.main.maintenance.service.MaintMainEdiiService;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSvxstdContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSvxstdRecord;
//import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSvxstdfvContainer;
//import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSvxstdfvRecord;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.util.manager.CodeDropDownMgrSkat;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;
//import no.systema.skat.z.maintenance.main.service.MaintDktvkService;
//import no.systema.skat.z.maintenance.skatncts.service.MaintDkxkodfService;


/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Jun 13, 2017
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdTdsExportSvxstdController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdTdsExportSvxstdController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgrSkat codeDropDownMgr = new CodeDropDownMgrSkat();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
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
	/*
	private JsonMaintMainDkxstdfvRecord fetchChildRecordSikkerhed(String applicationUser, String avd){
		JsonMaintMainDkxstdfvRecord record = new JsonMaintMainDkxstdfvRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_DKX003fvR_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&thavd=" + avd;
		
		//logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	//this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainDkxstdfvRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainDkxstdfvContainer container = this.maintMainDkxstdfvService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainDkxstdfvRecord tmp : list){
	        		record = tmp;

	        	}
	        }
    	}
    	return record;
    	
	}
	*/
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
	/*
	private int updateChildRecord(String applicationUser, JsonMaintMainDkxstdfvRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_DKX003fvR_DML_UPDATE_URL;
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
    		JsonMaintMainDkxstdfvContainer container = this.maintMainDkxstdfvService.doUpdate(jsonPayload);
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
	*/
	/**
	 * 
	 * @param recordToValidate
	 * @param sikkerhedChildRecord
	 */
	private void adjustSomeRecordValues(JsonMaintMainSvxstdRecord recordToValidate){
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
		/*this.codeDropDownMgr.populateCurrencyCodesHtmlDropDownsSkat(this.urlCgiProxyService, this.maintDktvkService, model, applicationUser);
		this.codeDropDownMgr.populateAvdListHtmlDropDownsSkat(this.urlCgiProxyService, this.maintMainKodtaService, model, applicationUser, "snealist");
		//Code lists in NCTS domain
		this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsNcts(this.urlCgiProxyService, this.maintDkxkodfService, model, applicationUser, MainMaintenanceConstants.CODE_NCTS_SIKKERHET_096_SPES_OMSTAND);
		this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsNcts(this.urlCgiProxyService, this.maintDkxkodfService, model, applicationUser, MainMaintenanceConstants.CODE_NCTS_SIKKERHET_116_TRANSP_KOST_BETAL_MATE);
		this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsNcts(this.urlCgiProxyService, this.maintDkxkodfService, model, applicationUser, MainMaintenanceConstants.CODE_SKAT_NCTS_EXPORT_108_TRANSPORTMATE);
		this.codeDropDownMgr.populateGeneralCodesHtmlDropDownsNcts(this.urlCgiProxyService, this.maintDkxkodfService, model, applicationUser, MainMaintenanceConstants.CODE_NCTS_DEKLARASJONS_TYPE);
		*/
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	/*
	private JsonMaintMainDkxstdfvRecord bindChildSikkerhed (HttpServletRequest request){
		JsonMaintMainDkxstdfvRecord record = new JsonMaintMainDkxstdfvRecord();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(record);
		binder.bind(request);
		return record;
	}*/
	
	
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
	
	/*
	@Qualifier ("maintMainDkxstdfvService")
	private MaintMainDkxstdfvService maintMainDkxstdfvService;
	@Autowired
	@Required
	public void setMaintMainDkxstdfvService (MaintMainDkxstdfvService value){ this.maintMainDkxstdfvService = value; }
	public MaintMainDkxstdfvService getMaintMainDkxstdfvService(){ return this.maintMainDkxstdfvService; }

	

	@Qualifier ("maintDktvkService")
	private MaintDktvkService maintDktvkService;
	@Autowired
	@Required
	public void setMaintDktvkService (MaintDktvkService value){ this.maintDktvkService = value; }
	public MaintDktvkService getMaintDktvkService(){ return this.maintDktvkService; }
	*/
	
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
	
	/*
	@Qualifier ("maintDkxkodfService")
	private MaintDkxkodfService maintDkxkodfService;
	@Autowired
	@Required
	public void setMaintDkxkodfService (MaintDkxkodfService value){ this.maintDkxkodfService = value; }
	public MaintDkxkodfService getMaintDkxkodfService(){ return this.maintDkxkodfService; }
	*/

	
}

