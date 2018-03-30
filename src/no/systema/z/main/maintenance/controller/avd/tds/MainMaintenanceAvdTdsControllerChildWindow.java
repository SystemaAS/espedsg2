package no.systema.z.main.maintenance.controller.avd.tds;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.external.tds.z.maintenance.service.MaintSvxkodfService;
import no.systema.external.tds.z.maintenance.model.JsonMaintSvxkodfContainer;
import no.systema.external.tds.z.maintenance.model.JsonMaintSvxkodfRecord;
import no.systema.external.tds.z.maintenance.url.store.MaintenanceUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;


/**
 * MAIN Maintenance  Controller - Child Window popup
 * 
 * @author oscardelatorre
 * @date Jun 26, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MainMaintenanceAvdTdsControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdTdsControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	//customer
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	
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
		@RequestMapping(value="mainmaintenanceavdtdsncts_childwindow_generalcodes.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
		public ModelAndView searchCodes(HttpSession session, HttpServletRequest request){
			logger.info("Inside searchCodes");
			
			ModelAndView successView = new ModelAndView("mainmaintenanceavdtdsncts_childwindow_generalcodes");
			Map model = new HashMap();
			String callerType = request.getParameter("ctype");
			String tkunik = request.getParameter("tkunik");
			String tkkode = request.getParameter("tkkode");
			String tktxtn = request.getParameter("tktxtn");
			logger.info(callerType);
			logger.info("tkunik:" + tkunik);
			
			SystemaWebUser appUser = this.loginValidator.getValidUser(session);
			
			if(appUser==null){
				return this.loginView;
					
			}else{
				Collection<JsonMaintSvxkodfRecord> list = new ArrayList<JsonMaintSvxkodfRecord>();
				//prepare the access CGI with RPG back-end
				
				String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVX001R_GET_LIST_URL;
				String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCodes(appUser.getUser(), tkunik, tkkode);
				logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParamsKeys);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//debugger
				logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonMaintSvxkodfContainer container = this.maintSvxkodfService.getList(jsonPayload);
		    		if(container!=null){
		    			list = container.getList();
		    			for(JsonMaintSvxkodfRecord  record : list){
		    				
		    			}
		    		}
		    	}
				model.put("list", list);
				model.put("tkunik", tkunik);
				model.put("id", tkkode);
				model.put("text", tktxtn);
				
				model.put("ctype", callerType);
				
				successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
				
		    	return successView;	
			  	
			}
			
		}	
		
	/**
	 * 
	 * @param applicationUser
	 * @param tkunik
	 * @param tkkode
	 * @return
	 */
	private String getRequestUrlKeyParametersForSearchCodes(String applicationUser, String tkunik, String tkkode){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  sb.append("&tkunik=" + tkunik);
		  if(tkkode!=null && !"".equals(tkkode)){
			  sb.append("&tkkode=" + tkkode);
		  }
		  return sb.toString();
	  }
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintSvxkodfService")
	private MaintSvxkodfService maintSvxkodfService;
	@Autowired
	@Required	
	public void setMaintSvxkodfService(MaintSvxkodfService value){this.maintSvxkodfService = value;}
	public MaintSvxkodfService getMaintSvxkodfService(){ return this.maintSvxkodfService; }
	
}

