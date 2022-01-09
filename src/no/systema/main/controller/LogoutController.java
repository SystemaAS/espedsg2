package no.systema.main.controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import no.systema.main.cookie.SessionCookieManager;
//application imports
import no.systema.main.util.AppConstants;
import no.systema.z.main.maintenance.util.manager.Log4jMgr;


@Controller
public class LogoutController {
	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class.getName());
	
	@RequestMapping("logout.do")
	public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request){
		
		if (session!=null){ 
			Log4jMgr log4jMgr = new Log4jMgr();
			log4jMgr.doLogoutLogger();
			//remove token cookie (init)
			SessionCookieManager cookieMgr = new SessionCookieManager(request);
			cookieMgr.removeGlobalCookie(response);
			
            session.removeAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
            session.invalidate();
            logger.info("Session invalidated..." + Calendar.getInstance().getTime());       
        }
		try{
			//issue a redirect for a fresh start
			response.sendRedirect("login.do");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	
    
}

