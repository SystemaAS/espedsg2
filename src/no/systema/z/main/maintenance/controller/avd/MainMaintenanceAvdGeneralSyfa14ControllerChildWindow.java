package no.systema.z.main.maintenance.controller.avd;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.validator.LoginValidator;
import no.systema.main.model.SystemaWebUser;

import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaRecord;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;

import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;




/**
 * SYFA14 Child windows popup
 * 
 * @author oscardelatorre
 * @date Sep 12, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MainMaintenanceAvdGeneralSyfa14ControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdGeneralSyfa14ControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(200);
	private ModelAndView loginView = new ModelAndView("login");
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private LoginValidator loginValidator = new LoginValidator();
	
	
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
	@RequestMapping(value="mainmaintenanceavd_childwindow_syfa14r.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView searchAvd(HttpSession session, HttpServletRequest request){
		logger.info("Inside searchEdi");
		
		ModelAndView successView = new ModelAndView("mainmaintenanceavd_childwindow_syfa14r");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		//TVINN
		String sadImportAvdList = request.getParameter("sialist"); //SadImportAvdlist
		String sadExportAvdList = request.getParameter("sealist"); //SadExportAvdlist
		String sadNctsImportAvdList = request.getParameter("nialist"); //SadNctsImportAvdlist
		String sadNctsExportAvdList = request.getParameter("nealist"); //SadNctsExportAvdlist
		//SKAT
		String skatImportAvdList = request.getParameter("ssialist"); //SkatImportAvdlist
		String skatExportAvdList = request.getParameter("ssealist"); //SkatExportAvdlist
		String skatNctsImportAvdList = request.getParameter("snialist"); //SkatNctsImportAvdlist
		String skatNctsExportAvdList = request.getParameter("snealist"); //SkatNctsExportAvdlist
		//TDS
		String tdsNctsImportAvdList = request.getParameter("tnialist"); //TdsNctsImportAvdlist
		String tdsNctsExportAvdList = request.getParameter("tnealist"); //TdsNctsExportAvdlist
		
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		if(appUser==null){
			return this.loginView;
				
		}else{
			Collection<JsonMaintMainKodtaRecord> list = new ArrayList<JsonMaintMainKodtaRecord>();
			//prepare the access CGI with RPG back-end
			
			String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL;
			String urlRequestParamsKeys = "user=" + appUser.getUser();
			//------
			//TVINN
			//------
			if(sadImportAvdList!=null && !"".equals(sadImportAvdList)){
				urlRequestParamsKeys = urlRequestParamsKeys + "&sialist=1"; //only the available avd. (sad import)
			}
			if(sadExportAvdList!=null && !"".equals(sadExportAvdList)){
				urlRequestParamsKeys = urlRequestParamsKeys + "&sealist=1"; //only the available avd. (sad export)
			}
			if(sadNctsImportAvdList!=null && !"".equals(sadNctsImportAvdList)){
				urlRequestParamsKeys = urlRequestParamsKeys + "&nialist=1"; //only the available avd. (sad ncts import)
			}
			if(sadNctsExportAvdList!=null && !"".equals(sadNctsExportAvdList)){
				urlRequestParamsKeys = urlRequestParamsKeys + "&nealist=1"; //only the available avd. (sad ncts export)
			}
			//------
			//SKAT
			//------
			if(skatImportAvdList!=null && !"".equals(skatImportAvdList)){
				urlRequestParamsKeys = urlRequestParamsKeys + "&ssialist=1"; //only the available avd. (skat import)
			}
			if(skatExportAvdList!=null && !"".equals(skatExportAvdList)){
				urlRequestParamsKeys = urlRequestParamsKeys + "&ssealist=1"; //only the available avd. (skat export)
			}
			if(skatNctsImportAvdList!=null && !"".equals(skatNctsImportAvdList)){
				urlRequestParamsKeys = urlRequestParamsKeys + "&snialist=1"; //only the available avd. (skat ncts import)
			}
			if(skatNctsExportAvdList!=null && !"".equals(skatNctsExportAvdList)){
				urlRequestParamsKeys = urlRequestParamsKeys + "&snealist=1"; //only the available avd. (skat ncts export)
			}
			//-----
			//TDS 
			//-----
			if(tdsNctsImportAvdList!=null && !"".equals(skatNctsImportAvdList)){
				urlRequestParamsKeys = urlRequestParamsKeys + "&tnialist=1"; //only the available avd. (tds ncts import)
			}
			if(tdsNctsExportAvdList!=null && !"".equals(skatNctsExportAvdList)){
				urlRequestParamsKeys = urlRequestParamsKeys + "&tnealist=1"; //only the available avd. (tds ncts export)
			}
			
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//debugger
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonMaintMainKodtaContainer container = this.maintMainKodtaService.getList(jsonPayload);
	    		if(container!=null){
	    			list = container.getList();
	    			for(JsonMaintMainKodtaRecord  record : list){
	    				
	    			}
	    		}
	    	}
			model.put("list", list);
			model.put("ctype", callerType);
			
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;	
		}
	}
	
	//SERVICES
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
	
}

