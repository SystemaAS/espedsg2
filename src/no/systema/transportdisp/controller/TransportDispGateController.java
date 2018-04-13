package no.systema.transportdisp.controller;

import java.net.InetAddress;
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

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
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
public class TransportDispGateController {
	private static final Logger logger = Logger.getLogger(TransportDispGateController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="transportdispgate.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView transportdispgate (HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("transportdispgate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
		}else{
			//appUser.setActiveMenu("INIT");
			logger.info("Inside method: transportdispgate");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
							
		}

	    	logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
	    session.setAttribute(AppConstants.ACTIVE_URL_RPG, AppConstants.ACTIVE_URL_RPG_INITVALUE);
		    
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

