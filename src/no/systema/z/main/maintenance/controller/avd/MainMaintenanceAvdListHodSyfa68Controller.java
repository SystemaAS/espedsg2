package no.systema.z.main.maintenance.controller.avd;

import java.util.*;

import org.apache.logging.log4j.*;
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


//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.service.UrlCgiProxyService;
//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.service.MaintMainKodtaKodthService;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaKodthContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaKodthRecord;

import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.validator.MaintMainKodtaKodthValidator;

/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Aug 19, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdListHodSyfa68Controller {
	private static final Logger logger = LogManager.getLogger(MainMaintenanceAvdListHodSyfa68Controller.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private StringManager strManager = new StringManager();
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="mainmaintenanceavd_syfa68r.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_syfa63r (@ModelAttribute ("record") JsonMaintMainKodtaKodthRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavd_syfa68r_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String avd = request.getParameter("avd");
		String avdNavn = request.getParameter("avdnavn");
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//-------------
			//Fetch record 
			//-------------
			List<JsonMaintMainKodtaKodthRecord> list = this.fetchList(appUser.getUser(), avd);
			if(list!=null && list.size()>0){
				for(JsonMaintMainKodtaKodthRecord record : list){
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					model.put("updateId", avd);
				}
			}
			model.put("avd", avd);
			model.put("avdnavn", avdNavn);
			
			model.put(MainMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			return successView;
		}
	
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="mainmaintenanceavd_syfa68r_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_syfa63r_edit(@ModelAttribute ("record") JsonMaintMainKodtaKodthRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavd_syfa68r_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String avd = recordToValidate.getKohavd();
		String avdNavn = request.getParameter("avdnavn");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//---------
			//Validate
			//---------
			MaintMainKodtaKodthValidator validator = new MaintMainKodtaKodthValidator();
			if(MainMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				validator.validate(recordToValidate, bindingResult);
			}
			if(bindingResult.hasErrors()){
				//ERRORS
				logger.info("[ERROR Validation] Record does not validate)");
				if(updateId!=null && !"".equals(updateId)){
					//meaning bounced in an Update and not a Create new
					model.put("updateId", updateId);
				}
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
			}else{
				//------------
				//UPDATE table
				//------------
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(MainMaintenanceConstants.ACTION_UPDATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
						
					//CREATE	
					}else{
						//create new
						logger.info(MainMaintenanceConstants.ACTION_CREATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
					}
					
				}else if (MainMaintenanceConstants.ACTION_DELETE.equals(action)){
					logger.info(MainMaintenanceConstants.ACTION_DELETE);
					dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_DELETE, errMsg);
					
				}
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}else{
					//post successful update operations
					//updateId = recordToValidate.getKoaavd();
				}
			}
			
			//-------------
			//Fetch record 
			//-------------
			List<JsonMaintMainKodtaKodthRecord> list = this.fetchList(appUser.getUser(), avd);
			if(list!=null && list.size()>0){
				for(JsonMaintMainKodtaKodthRecord record : list){
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					model.put("updateId", avd);
				}
			}
			model.put(MainMaintenanceConstants.DOMAIN_LIST, list);
			model.put("action", action);
			model.put("avd", avd);
			model.put("avdnavn", avdNavn);
			
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			return successView;
			
		}
	}

	/**
	 * 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	private List<JsonMaintMainKodtaKodthRecord> fetchList(String applicationUser, String avd){
		List <JsonMaintMainKodtaKodthRecord> list = new ArrayList<JsonMaintMainKodtaKodthRecord>();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA68R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&kohavd=" + avd;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtaKodthContainer container = this.maintMainKodtaKodthService.getList(jsonPayload);
	        if(container!=null){
	        	for (JsonMaintMainKodtaKodthRecord rec : container.getList()){
	        		//DEBUG
	        	}
	        	list = (List)container.getList();
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	public int updateRecord(String applicationUser, JsonMaintMainKodtaKodthRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA68R_DML_UPDATE_URL;
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
    		JsonMaintMainKodtaKodthContainer container = this.maintMainKodtaKodthService.doUpdate(jsonPayload);
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
	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintMainKodtaKodthService")
	private MaintMainKodtaKodthService maintMainKodtaKodthService;
	@Autowired
	@Required
	public void setMaintMainKodtaKodthService (MaintMainKodtaKodthService value){ this.maintMainKodtaKodthService = value; }
	public MaintMainKodtaKodthService getMaintMainKodtaKodthService(){ return this.maintMainKodtaKodthService; }
	
		
}

