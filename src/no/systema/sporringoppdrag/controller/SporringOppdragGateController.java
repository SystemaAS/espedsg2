package no.systema.sporringoppdrag.controller;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
//models
import no.systema.main.util.DateTimeManager;
import no.systema.sporringoppdrag.util.SporringOppdragConstants;


/**
 * Gateway to the SporringOppdrag Application
 * 
 * 
 * @author oscardelatorre
 * @date Jan 29, 2015
 * 
 * 	
 */

@Controller
public class SporringOppdragGateController {
	private static final Logger logger = Logger.getLogger(SporringOppdragGateController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="sporringoppdraggate.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView sporringoppdraggate (HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("redirect:sporringoppdrag_mainlist.do?action=doFind");
		String childWindow = request.getParameter("cw");
		if(childWindow!=null && !"".equals(childWindow)){
			//This is the ONLY place where this session variable is created.
			//It is destroyed ONLY in the: no.systema.transportdisp.controller.LogoutTransportDispConstants
			session.setAttribute(SporringOppdragConstants.SESSION_CHILDWINDOW_FLAG, childWindow);
			
		}
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//appUser.setActiveMenu("INIT");
			logger.info("Inside method: sporringoppdraggate");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
		}
	    	logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
	    session.setAttribute(AppConstants.ACTIVE_URL_RPG, AppConstants.ACTIVE_URL_RPG_INITVALUE);
		return successView;
		
	}
	
}

