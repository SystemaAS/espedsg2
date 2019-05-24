package no.systema.z.main.maintenance.controller.avd;

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

//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.service.MaintMainKodtaKodthService;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;
import no.systema.z.main.maintenance.service.MaintMainFirmService;
import no.systema.z.main.maintenance.service.MaintMainKodtaTellService;
import no.systema.z.main.maintenance.service.MaintMainKodtvKodtwService;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaKodthContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaKodthRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainFirmContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainFirmRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaTellContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaTellRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwRecord;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.validator.MaintMainKodtaValidator;


/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Jul 01, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdGeneralSyfa14Controller {
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdGeneralSyfa14Controller.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="mainmaintenanceavd_syfa14r.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_syfa14r(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavd_syfa14r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavd_syfa14r");
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
	@RequestMapping(value="mainmaintenanceavd_syfa14r_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_syfa14r_edit(@ModelAttribute ("record") JsonMaintMainKodtaRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavd_syfa14r_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String avd = request.getParameter("avd");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu("INIT");
			logger.info("Inside method: mainmaintenanceavd_syfa14r_edit");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_MAIN_MAINTENANCE);
			session.setAttribute(MainMaintenanceConstants.ACTIVE_URL_RPG_MAIN_MAINTENANCE, MainMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
			//--------------
			//UPDATE record
			//--------------
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				avd = recordToValidate.getKoaavd();
				//bind child records
				JsonMaintMainKodtaKodthRecord listeHodeRecord = this.bindChildListeHode(request);
				JsonMaintMainKodtaTellRecord oppnrTurRecord = this.bindChildOppnrTur(request);
				
				
				//Validate
				MaintMainKodtaValidator validator = new MaintMainKodtaValidator();
				validator.validate(recordToValidate, bindingResult);
				if(bindingResult.hasErrors()){
					//ERRORS
					logger.info("[ERROR Validation] Record does not validate)");
					//reload children
					recordToValidate.setListeHodeRecord(listeHodeRecord);
					recordToValidate.setOppnrTurRecord(oppnrTurRecord);
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					
				}else{
					//Update table
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(MainMaintenanceConstants.MODE_UPDATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
						//------------------------
						//now update all children
						//------------------------
						//UPDATE children
						dmlRetval = this.updateChilden(appUser.getUser(), recordToValidate, listeHodeRecord, oppnrTurRecord, errMsg);
						
					}else{
						//create new
						logger.info(MainMaintenanceConstants.MODE_ADD);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
						//------------------------
						//now create all children
						//------------------------
						//Create Children
						dmlRetval = this.createChilden(appUser.getUser(), recordToValidate, listeHodeRecord, oppnrTurRecord, errMsg);
						
					}
					
					//check for Update errors
					if( dmlRetval < 0){
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					}else{
						//post successful update operations
						updateId = recordToValidate.getKoaavd();
						//refresh
						JsonMaintMainKodtaRecord record = this.fetchRecord(appUser.getUser(), recordToValidate.getKoaavd());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					}
				}
				
				
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
					successView = new ModelAndView("redirect:mainmaintenanceavd_syfa14r.do?id=KODTA");
					
				}
			}else{
				//-------------
				//Fetch record
				//-------------
				JsonMaintMainKodtaRecord record = new JsonMaintMainKodtaRecord();
				if(avd!=null && !"".equals(avd)){
					//get record including children records (listehode & oppdnrTur)
					record = this.fetchRecord(appUser.getUser(), avd);
					
					//check if fastedate exists
					JsonMaintMainKodtvKodtwRecord recordFasteData = this.maintMainKodtvKodtwService.fetchRecord(appUser.getUser(), avd);
					if(recordFasteData!=null){
						if(recordFasteData.getKovavd()!=null && !"".equals(recordFasteData.getKovavd())){
							//exists
						}else{
							record.setFasteDataExists(false);
						}
					}
				}
				this.populateDefaultFirmValues(appUser.getUser(), record, avd);
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
			}
			
			
			//populate model
			if(action==null || "".equals(action)){
				action = "doUpdate";
			}
			model.put("action", action);
			model.put("avd", avd);
			model.put("updateId", updateId);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    
			return successView;
			
		}
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	private JsonMaintMainKodtaKodthRecord bindChildListeHode (HttpServletRequest request){
		JsonMaintMainKodtaKodthRecord record = new JsonMaintMainKodtaKodthRecord();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(record);
		binder.bind(request);
		return record;
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	private JsonMaintMainKodtaTellRecord bindChildOppnrTur (HttpServletRequest request){
		JsonMaintMainKodtaTellRecord record = new JsonMaintMainKodtaTellRecord();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(record);
		binder.bind(request);
		return record;
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @return
	 */
	private List<JsonMaintMainKodtaRecord> fetchList(String applicationUser){
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainKodtaRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtaContainer container = this.maintMainKodtaService.getList(jsonPayload);
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
	private JsonMaintMainKodtaRecord fetchRecord(String applicationUser, String id){
		JsonMaintMainKodtaRecord record = new JsonMaintMainKodtaRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&koaavd=" + id;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainKodtaRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtaContainer container = this.maintMainKodtaService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainKodtaRecord tmp : list){
	        		record = tmp;
	        	}
	        }
    	}
    	//get children
    	record.setListeHodeRecord(this.fetchListeHodeRecord(applicationUser, id));
    	record.setOppnrTurRecord(this.fetchOppnrOgTur(applicationUser, id));
    	
    	return record;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	private JsonMaintMainKodtaKodthRecord fetchListeHodeRecord(String applicationUser, String avd){
		JsonMaintMainKodtaKodthRecord record = new JsonMaintMainKodtaKodthRecord();
    	
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
	        		record = rec;
	        	}
	        }
    	}
    	return record;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	private JsonMaintMainKodtaTellRecord fetchOppnrOgTur(String applicationUser, String avd){
		JsonMaintMainKodtaTellRecord record = new JsonMaintMainKodtaTellRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA26R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&teavd=" + avd;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtaTellContainer container = this.maintMainKodtaTellService.getList(jsonPayload);
	        if(container!=null){
	        	for (JsonMaintMainKodtaTellRecord rec : container.getList()){
	        		record = rec;
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
	private int updateRecord(String applicationUser, JsonMaintMainKodtaRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_DML_UPDATE_URL;
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
    		JsonMaintMainKodtaContainer container = this.maintMainKodtaService.doUpdate(jsonPayload);
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
	 * @param avd
	 */
	private void populateDefaultFirmValues (String applicationUser, JsonMaintMainKodtaRecord record, String avd ){
		if(avd!=null && !"".equals(avd)){
			//nothing
		}else{
			//get default
			String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFIRMR_GET_LIST_URL;
			String urlRequestParamsKeys = "user=" + applicationUser;
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
	    		JsonMaintMainFirmContainer container = this.maintMainFirmService.getList(jsonPayload);
		        if(container!=null){
		        	if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
		        		if(container.getErrMsg().toUpperCase().startsWith("ERROR")){
		        			//TODO something or let go ... 
		        			//errMsg.append(container.getErrMsg());
		        		}
		        	}else{
		        		for(JsonMaintMainFirmRecord firmRecord : container.getList()){
		        			record.setKoafir(firmRecord.getFifirm());
		        		}
		        	}
		        }
	    	}
	    	
		}
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	public int updateChildRecord(String applicationUser, JsonMaintMainKodtaKodthRecord record, String mode, StringBuffer errMsg){
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
	
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateChildRecord(String applicationUser, JsonMaintMainKodtaTellRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA26R_DML_UPDATE_URL;
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
    		JsonMaintMainKodtaTellContainer container = this.maintMainKodtaTellService.doUpdate(jsonPayload);
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
	 * @param recordToValidate
	 * @param listeHodeRecord
	 * @param oppnrTurRecord
	 * @param errMsg
	 * @return
	 */
	private int updateChilden(String applicationUser, JsonMaintMainKodtaRecord recordToValidate, JsonMaintMainKodtaKodthRecord listeHodeRecord, JsonMaintMainKodtaTellRecord oppnrTurRecord,  StringBuffer errMsg){
		int dmlRetval = 0;
		if(listeHodeRecord!=null && oppnrTurRecord!=null){
			//UPDATE ListeHode
			if(listeHodeRecord.getKohavd()!=null && !"".equals(listeHodeRecord.getKohavd())){
				//DEBUG -->logger.info("UPDATE child: listeHode...");
				dmlRetval = this.updateChildRecord(applicationUser, listeHodeRecord, MainMaintenanceConstants.MODE_UPDATE, errMsg);
			}else{
				listeHodeRecord.setKohavd(recordToValidate.getKoaavd());
				dmlRetval = this.updateChildRecord(applicationUser, listeHodeRecord, MainMaintenanceConstants.MODE_ADD, errMsg);
			}
			//UPDATE OppnrTur
			if(oppnrTurRecord.getTeavd()!=null && !"".equals(oppnrTurRecord.getTeavd())){
				//DEBUG -->logger.info("UPDATE child: listeHode...");
				dmlRetval = this.updateChildRecord(applicationUser, oppnrTurRecord, MainMaintenanceConstants.MODE_UPDATE, errMsg);
			}else{
				oppnrTurRecord.setTeavd(recordToValidate.getKoaavd());
				dmlRetval = this.updateChildRecord(applicationUser, oppnrTurRecord, MainMaintenanceConstants.MODE_ADD, errMsg);
			}
		}
		return dmlRetval;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param listeHodeRecord
	 * @param oppnrTurRecord
	 * @param errMsg
	 * @return
	 */
	private int createChilden(String applicationUser, JsonMaintMainKodtaRecord recordToValidate, JsonMaintMainKodtaKodthRecord listeHodeRecord, JsonMaintMainKodtaTellRecord oppnrTurRecord,  StringBuffer errMsg){
		int dmlRetval = 0;
		if(listeHodeRecord!=null && oppnrTurRecord!=null){
			//(1)
			listeHodeRecord.setKohavd(recordToValidate.getKoaavd());
			dmlRetval = this.updateChildRecord(applicationUser, listeHodeRecord, MainMaintenanceConstants.MODE_ADD, errMsg);
			//(2)
			oppnrTurRecord.setTeavd(recordToValidate.getKoaavd());
			dmlRetval = this.updateChildRecord(applicationUser, oppnrTurRecord, MainMaintenanceConstants.MODE_ADD, errMsg);
		}
		return dmlRetval;
	}	
	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintMainKodtaService")
	private MaintMainKodtaService maintMainKodtaService;
	@Autowired
	@Required
	public void setMaintMainKodtaService (MaintMainKodtaService value){ this.maintMainKodtaService = value; }
	public MaintMainKodtaService getMaintMainKodtaService(){ return this.maintMainKodtaService; }
	
	
	@Qualifier ("maintMainFirmService")
	private MaintMainFirmService maintMainFirmService;
	@Autowired
	@Required
	public void setMaintMainFirmService (MaintMainFirmService value){ this.maintMainFirmService = value; }
	public MaintMainFirmService getMaintMainFirmService(){ return this.maintMainFirmService; }
	
	
	@Qualifier ("maintMainKodtvKodtwService")
	private MaintMainKodtvKodtwService maintMainKodtvKodtwService;
	@Autowired
	@Required
	public void setMaintMainKodtvKodtwService (MaintMainKodtvKodtwService value){ this.maintMainKodtvKodtwService = value; }
	public MaintMainKodtvKodtwService getMaintMainKodtvKodtwService(){ return this.maintMainKodtvKodtwService; }
	
	//Child record
	@Qualifier ("maintMainKodtaKodthService")
	private MaintMainKodtaKodthService maintMainKodtaKodthService;
	@Autowired
	@Required
	public void setMaintMainKodtaKodthService (MaintMainKodtaKodthService value){ this.maintMainKodtaKodthService = value; }
	public MaintMainKodtaKodthService getMaintMainKodtaKodthService(){ return this.maintMainKodtaKodthService; }
	
	//Child record
	@Qualifier ("maintMainKodtaTellService")
	private MaintMainKodtaTellService maintMainKodtaTellService;
	@Autowired
	@Required
	public void setMaintMainKodtaTellService (MaintMainKodtaTellService value){ this.maintMainKodtaTellService = value; }
	public MaintMainKodtaTellService getMaintMainKodtaTellService(){ return this.maintMainKodtaTellService; }
	
		
}

