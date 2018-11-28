package no.systema.transportdisp.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.StringManager;

//models


/**
 * Gateway to the TranspDisp Application
 * 
 * 
 * @author oscardelatorre
 * @date Jan 13, 2015
 * 
 * 	
 */

@Controller
public class TransportDispPrintController {
	private static final Logger logger = Logger.getLogger(TransportDispPrintController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private StringManager strMgr = new StringManager();
	
	/**
	 * General AS400 direct print out
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transpdisp_mainorder_printout.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrintOut(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//fallback (last resort);
		ModelAndView successView = new ModelAndView("redirect:transportdisp_mainorderlist.do?action=doFind");
				
		String method = "doPrintOut";
		logger.info("Method: " + method);
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String tur = request.getParameter("tur");
		//URL call params
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		//check the parent caller for this print (ORDER or TRIP)
		if(strMgr.isNotNull(opd) && strMgr.isNotNull(avd) ){
			 //fill other params
			 urlRequestParamsKeys.append("&avd=" + avd);
			 urlRequestParamsKeys.append("&opd=" + opd);
			 urlRequestParamsKeys.append("&tur=");
			 
			 //print from Order GUI
			 successView = new ModelAndView("redirect:transportdisp_mainorder.do?user=" + appUser.getUser() + "&hepro=&heavd=" + avd + "&heopd=" + opd);
			 	
		}else{
			//fill other params
			urlRequestParamsKeys.append("&avd=&opd=&tur=" + tur);
			
			//print from Trip GUI
			if(strMgr.isNotNull(tur)){
				successView = new ModelAndView("redirect:transportdisp_workflow_getTrip.do?user=" + appUser.getUser() + "&tuavd=&tupro=");
			}
			
		}
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//-------------------------------------
			//get BASE URL = RPG-PROGRAM for PRINT
            //-------------------------------------
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_PRINT_OUT_FRAKTBREV;
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the Print (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			//Debug --> 
	    	logger.info(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	//END of PRINT here and now
	    	logger.info("Method PRINT END - " + method);	
		    	
		}
		
		return successView;
	}

	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	/*
	@Qualifier ("tvinnSadAuthorizationService")
	private TvinnSadAuthorizationService tvinnSadAuthorizationService;
	@Autowired
	@Required
	public void setTvinnSadAuthorizationService (TvinnSadAuthorizationService value) { this.tvinnSadAuthorizationService = value; }
	public TvinnSadAuthorizationService getTvinnSadAuthorizationService(){ return this.tvinnSadAuthorizationService; }
	*/	
}

