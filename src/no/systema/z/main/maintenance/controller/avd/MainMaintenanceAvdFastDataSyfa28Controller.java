package no.systema.z.main.maintenance.controller.avd;

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


//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.service.UrlCgiProxyService;
//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.util.manager.CodeDropDownMgr;
import no.systema.z.main.maintenance.service.MaintMainKodtvKodtwService;
import no.systema.z.main.maintenance.service.MaintMainKodtpUtskrsService;
import no.systema.z.main.maintenance.service.MaintMainKodtot2Service;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtpUtskrsContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtpUtskrsRecord;

import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.validator.MaintMainKodtvKodtwValidator;

/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Aug 05, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdFastDataSyfa28Controller {
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdFastDataSyfa28Controller.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private StringManager strManager = new StringManager();
	
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="mainmaintenanceavd_syfa28r_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_syfa14r_edit(@ModelAttribute ("record") JsonMaintMainKodtvKodtwRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavd_syfa28r_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String avd = request.getParameter("avd");
		String avdNavn = request.getParameter("avdnavn");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavd_syfa28r_edit");
			logger.info("avd" + avd);
			logger.info("avdnavn" + avdNavn);
			logger.info("action" + action);
		
			//--------------
			//UPDATE record
			//--------------
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				
				//---------
				//Validate
				//---------
				MaintMainKodtvKodtwValidator validator = new MaintMainKodtvKodtwValidator();
				boolean createNew = false;
				//this step is required to pass validation in a CREATE NEW use case. It will be blanked after passed validation
				if(recordToValidate.getKovavd()==null || "".equals(recordToValidate.getKovavd())){
					createNew = true;
					recordToValidate.setKovavd(avd);
					validator.validate(recordToValidate, bindingResult);
				}else{
					avd = recordToValidate.getKovavd();
					validator.validate(recordToValidate, bindingResult);
				}
				
				if(bindingResult.hasErrors()){
					//ERRORS
					logger.info("[ERROR Validation] Record does not validate)");
					//model.put("dbTable", dbTable);
					if(createNew){
						recordToValidate.setKovavd(null);
					}
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					
				}else{
					//Update table
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					//adjust complex fields (kovxxx/kowxxx) before updates
					this.populateKovxxxField(recordToValidate);
					this.populateKowxxxField(recordToValidate);
					
					//logger.info(recordToValidate.getKovxxx());
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(MainMaintenanceConstants.MODE_UPDATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
					}else{
						
						//create new
						logger.info(MainMaintenanceConstants.MODE_ADD);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
						if( dmlRetval>0){
								
						}
					}
					
					//check for Update errors
					if( dmlRetval < 0){
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					}else{
						//post successful update operations
						updateId = recordToValidate.getKovavd();
					}
				}
				recordToValidate.setChildList(this.fetchChildList(appUser.getUser(), avd));
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				
				
			//DELETE	
			}else if(MainMaintenanceConstants.ACTION_DELETE.equals(action)){
				//N/A

			}else{
				//---------------------------
				//Fetch record and child list
				//---------------------------
				JsonMaintMainKodtvKodtwRecord record = this.maintMainKodtvKodtwService.fetchRecord(appUser.getUser(), avd);
				record.setChildList(this.fetchChildList(appUser.getUser(), avd));
				//DEBUG
				for (JsonMaintMainKodtpUtskrsRecord cRecord : record.getChildList()){
					//logger.info(cRecord.getKoplnr());
				}
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
			}
			
			//Drop downs
			this.populateDropDowns(model, appUser.getUser());
			//populate model
			if(action==null || "".equals(action)){
				action = "doUpdate";
			}
			model.put("action", action);
			model.put("avd", avd);
			model.put("avdnavn", avdNavn);
			
			model.put("updateId", updateId);
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
	@RequestMapping(value="mainmaintenanceavd_syfa28DuplicatePrintr_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_syfa14DuplicatePrintr_edit(HttpSession session, HttpServletRequest request){
		ModelAndView successView = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String modelAndViewStr = null;
		StringBuffer errMsg = new StringBuffer();
		boolean isError = false;
	
		String originalAvd = null;
		String originalLnr = null;
		String fromAvd = null;
		String toAvd = null;
		String avdNavn = null;

		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			if (paramName.contains("originalAvd")){
				originalAvd = request.getParameter(paramName);
			}else if (paramName.contains("originalLnr")){
				originalLnr = request.getParameter(paramName);
			}else if (paramName.contains("fromAvd")){
				fromAvd = request.getParameter(paramName);
			}else if (paramName.contains("toAvd")){
				toAvd = request.getParameter(paramName);
			}else if (paramName.contains("oAvdNavn")){
				avdNavn = request.getParameter(paramName);
			}
		}
		//DEBUG -->logger.info(originalAvd); logger.info(originalLnr);logger.info(fromAvd); logger.info(toAvd);logger.info(avdNavn);

		if(appUser==null){
			return this.loginView;
		}else{

			String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA28DPTAvdR_DML_UPDATE_CHILD_URL;
			StringBuffer urlRequestParams = new StringBuffer();
			urlRequestParams.append("user=" + appUser.getUser() + "&mode=U");
			urlRequestParams.append("&originalAvd=" + originalAvd);
			urlRequestParams.append("&originalLnr=" + originalLnr);
			urlRequestParams.append("&fromAvd=" + fromAvd);
			urlRequestParams.append("&toAvd=" + toAvd);
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
	    	//DEBUG
	    	jsonDebugger.debugJsonPayload(jsonPayload, 500);
	    	
	    	//extract
	    	if(jsonPayload!=null){
				//lists
	    		JsonMaintMainKodtvKodtwContainer container = this.maintMainKodtvKodtwService.doUpdate(jsonPayload);
		        if(container!=null){
		        	if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
		        		if(container.getErrMsg().toUpperCase().startsWith("ERROR")){
		        			errMsg.append(container.getErrMsg());
		        			isError = true;
		        		}
		        	}
		        }
	    	} 
	    	
	    	//check for Update errors
			if( isError ){
				logger.info("[ERROR ] Transaction UPDATE not valid ...)");
				model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
				//View
				modelAndViewStr = "mainmaintenanceavd_syfa28r_edit";
				//---------------------------
				//Fetch record and child list
				//---------------------------
				JsonMaintMainKodtvKodtwRecord record = this.maintMainKodtvKodtwService.fetchRecord(appUser.getUser(), originalAvd);
				record.setChildList(this.fetchChildList(appUser.getUser(), originalAvd));
				//DEBUG
				for (JsonMaintMainKodtpUtskrsRecord cRecord : record.getChildList()){
					//logger.info(cRecord.getKoplnr());
				}
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
				//Drop downs
				this.populateDropDowns(model, appUser.getUser());
				model.put("action", "doUpdate");
				model.put("avd", originalAvd);
				model.put("avdnavn", avdNavn);
				model.put("updateId", originalAvd);
				
			}else{
				//OK then reload through redirection
				modelAndViewStr = "redirect:mainmaintenanceavd_syfa28r_edit.do?avd=" + originalAvd + "&updateId=" + originalAvd + "&avdnavn=" + avdNavn;
				
			}
			
			successView = new ModelAndView(modelAndViewStr);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			    
			return successView;
			
		}
		
	}
	

	/**
	 * We must populate the kovxxx field from all html fields since this field saves values in different positions
	 * 
	 * @param recordToValidate
	 */
	public void populateKovxxxField(JsonMaintMainKodtvKodtwRecord recordToValidate){
		String SPACE = " ";
		//(0, 1-char)
		if(recordToValidate.getKovxxx0()!=null && !"".equals(recordToValidate.getKovxxx0())){
			String tmp = recordToValidate.getKovxxx0();
			recordToValidate.setKovxxx(tmp);
		}else{
			recordToValidate.setKovxxx(SPACE);
		}
		//(1, 2-chars)
		if(recordToValidate.getKovxxx1()!=null && !"".equals(recordToValidate.getKovxxx1())){
			String tmp = recordToValidate.getKovxxx() + recordToValidate.getKovxxx1();
			recordToValidate.setKovxxx(strManager.paddingString(tmp, 3));
		}else{
			recordToValidate.setKovxxx(recordToValidate.getKovxxx() + SPACE + SPACE);
		}
		//(3, 1-char)
		if(recordToValidate.getKovxxx3()!=null && !"".equals(recordToValidate.getKovxxx3())){
			String tmp = recordToValidate.getKovxxx() + recordToValidate.getKovxxx3();
			recordToValidate.setKovxxx(strManager.paddingString(tmp, 4));
		}else{
			recordToValidate.setKovxxx(recordToValidate.getKovxxx() + SPACE);
		}
		//(4, 1-char)
		if(recordToValidate.getKovxxx4()!=null && !"".equals(recordToValidate.getKovxxx4())){
			String tmp = recordToValidate.getKovxxx() + recordToValidate.getKovxxx4();
			recordToValidate.setKovxxx(strManager.paddingString(tmp, 5));
		}else{
			recordToValidate.setKovxxx(recordToValidate.getKovxxx() + SPACE);
		}
		//(5, 2-char)
		if(recordToValidate.getKovxxx5()!=null && !"".equals(recordToValidate.getKovxxx5())){
			String tmp = recordToValidate.getKovxxx() + recordToValidate.getKovxxx5();
			recordToValidate.setKovxxx(strManager.paddingString(tmp, 7));
		}else{
			recordToValidate.setKovxxx(recordToValidate.getKovxxx() + SPACE + SPACE);
		}
	}
	/**
	 * We must populate the kovxxx field from all html fields since this field saves values in different positions
	 * 
	 * @param recordToValidate
	 */
	public void populateKowxxxField(JsonMaintMainKodtvKodtwRecord recordToValidate){
		String SPACE = " ";
		//(0, 1-char)
		if(recordToValidate.getKowxxx0()!=null && !"".equals(recordToValidate.getKowxxx0())){
			String tmp = recordToValidate.getKowxxx0();
			recordToValidate.setKowxxx(tmp);
		}else{
			recordToValidate.setKowxxx(SPACE);
		}
		//(1, 1-char)
		if(recordToValidate.getKowxxx1()!=null && !"".equals(recordToValidate.getKowxxx1())){
			String tmp = recordToValidate.getKowxxx() + recordToValidate.getKowxxx1();
			recordToValidate.setKowxxx(strManager.paddingString(tmp, 2));
		}else{
			recordToValidate.setKowxxx(recordToValidate.getKowxxx() + SPACE);
		}
		//(2, 2-char)
		if(recordToValidate.getKowxxx2()!=null && !"".equals(recordToValidate.getKowxxx2())){
			String tmp = recordToValidate.getKowxxx() + recordToValidate.getKowxxx2();
			recordToValidate.setKowxxx(strManager.paddingString(tmp, 4));
		}else{
			recordToValidate.setKowxxx(recordToValidate.getKowxxx() + SPACE + SPACE);
		}
	}
	
	/**
	 * Gets the children list of the child section (FASTE DATA Del-2)
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private List<JsonMaintMainKodtpUtskrsRecord> fetchChildList(String applicationUser, String id){
		List <JsonMaintMainKodtpUtskrsRecord> list = new ArrayList<JsonMaintMainKodtpUtskrsRecord>();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA28R_GET_CHILDREN_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&kopavd=" + id;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtpUtskrsContainer container = this.maintMainKodtpUtskrsService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for (JsonMaintMainKodtpUtskrsRecord rec : list){
	        		//logger.info(rec.getUtpty());
	        	}
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
	private int updateRecord(String applicationUser, JsonMaintMainKodtvKodtwRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA28R_DML_UPDATE_URL;
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
    		JsonMaintMainKodtvKodtwContainer container = this.maintMainKodtvKodtwService.doUpdate(jsonPayload);
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
	 * @param model
	 * @param applicationUser
	 */
	private void populateDropDowns(Map model, String applicationUser){
		this.codeDropDownMgr.populateOppdragsTypeHtmlDropDowns(this.urlCgiProxyService, this.maintMainKodtot2Service, model, applicationUser);
	}
	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintMainKodtvKodtwService")
	private MaintMainKodtvKodtwService maintMainKodtvKodtwService;
	@Autowired
	@Required
	public void setMaintMainKodtvKodtwService (MaintMainKodtvKodtwService value){ this.maintMainKodtvKodtwService = value; }
	public MaintMainKodtvKodtwService getMaintMainKodtvKodtwService(){ return this.maintMainKodtvKodtwService; }
	
	
	@Qualifier ("maintMainKodtpUtskrsService")
	private MaintMainKodtpUtskrsService maintMainKodtpUtskrsService;
	@Autowired
	@Required
	public void setMaintMainKodtpUtskrsService (MaintMainKodtpUtskrsService value){ this.maintMainKodtpUtskrsService = value; }
	public MaintMainKodtpUtskrsService getMaintMainKodtpUtskrsService(){ return this.maintMainKodtpUtskrsService; }
	
	@Qualifier ("maintMainKodtot2Service")
	private MaintMainKodtot2Service maintMainKodtot2Service;
	@Autowired
	@Required
	public void setMaintMainKodtot2Service (MaintMainKodtot2Service value){ this.maintMainKodtot2Service = value; }
	public MaintMainKodtot2Service getMaintMainKodtot2Service(){ return this.maintMainKodtot2Service; }
	
	

		
}

