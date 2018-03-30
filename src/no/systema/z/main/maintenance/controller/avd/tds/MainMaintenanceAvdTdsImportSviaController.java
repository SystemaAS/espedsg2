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
import no.systema.z.main.maintenance.service.tds.MaintMainSviaService;
import no.systema.z.main.maintenance.service.MaintMainEdiiService;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSviaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSviaRecord;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.validator.tds.MaintMainSviaValidator;
import no.systema.z.main.maintenance.util.manager.CodeDropDownMgrSkat;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;


/**
 * Gateway to the Main Maintenance Application
 * 
 * @author oscardelatorre
 * @date Jun 13, 2017
 * 
 */

@Controller
public class MainMaintenanceAvdTdsImportSviaController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdTdsImportSviaController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgrSkat codeDropDownMgr = new CodeDropDownMgrSkat();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="mainmaintenanceavdtdsimport_svi051r.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdskatexport_svi051r (HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdtdsimport_svi051r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String id = request.getParameter("id");  //SVIA
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdtdsimport_svi051r");
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
	@RequestMapping(value="mainmaintenanceavdtdsimport_svi051r_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdskatexport_svi051_edit(@ModelAttribute ("record") JsonMaintMainSviaRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdtdsimport_svi051r_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String avd = request.getParameter("avd");
		String avdnavn = request.getParameter("avdnavn");
		String opd = request.getParameter("opd");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		String id = request.getParameter("id");
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdtdsimport_svi051r_edit");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			boolean isValidOnUpdate = true;
			//--------------
			//UPDATE record
			//--------------
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				
				avd = recordToValidate.getSvia_syav();
				//Adjust
				this.adjustSomeRecordValues(recordToValidate);
				//Validate
				MaintMainSviaValidator validator = new MaintMainSviaValidator();
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
							//TODO more ?
						}
					}else{
						//create new
						logger.info(MainMaintenanceConstants.MODE_ADD);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
						if(dmlRetval >= 0){
							//TODO more ?
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
						updateId = recordToValidate.getSvia_syav();
						avd = recordToValidate.getSvia_syav();
						opd = recordToValidate.getSvia_syop();
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
					successView = new ModelAndView("redirect:mainmaintenanceavdtdsimport_svi051r.do?id=SVIA");
				}
			}
			//-------------
			//Fetch record
			//-------------
			if(isValidOnUpdate && (avd!=null && !"".equals(avd)) ){
				JsonMaintMainSviaRecord record = this.fetchRecord(appUser.getUser(), avd, opd);
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
			model.put("opd", opd);
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
	private List<JsonMaintMainSviaRecord> fetchList(String applicationUser, String id){
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVI051R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&id=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainSviaRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainSviaContainer container = this.maintMainSviaService.getList(jsonPayload);
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
	 * @param opd
	 * @return
	 */
	private JsonMaintMainSviaRecord fetchRecord(String applicationUser, String avd, String opd){
		JsonMaintMainSviaRecord record = new JsonMaintMainSviaRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVI051R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&svia_syav=" + avd + "&svia_syop=" + opd;
		
		//logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	//this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainSviaRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainSviaContainer container = this.maintMainSviaService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainSviaRecord tmp : list){
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
	private int updateRecord(String applicationUser, JsonMaintMainSviaRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVI051R_DML_UPDATE_URL;
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
    		JsonMaintMainSviaContainer container = this.maintMainSviaService.doUpdate(jsonPayload);
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
	private void adjustSomeRecordValues(JsonMaintMainSviaRecord recordToValidate){
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
		this.codeDropDownMgr.populateAvdListHtmlDropDownsSkat(this.urlCgiProxyService, this.maintMainKodtaService, model, applicationUser, "ssealist");
	}
	
	
	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintMainSviaService")
	private MaintMainSviaService maintMainSviaService;
	@Autowired
	@Required
	public void setMaintMainSviaService (MaintMainSviaService value){ this.maintMainSviaService = value; }
	public MaintMainSviaService getMaintMainSviaService(){ return this.maintMainSviaService; }
	
	
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

