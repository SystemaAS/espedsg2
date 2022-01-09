package no.systema.z.main.maintenance.controller.kund;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;

/**
 * Gateway for Vare register in Kunderegister
 * 
 * 
 * @author Fredrik Möller
 * @date Mar 14, 2017
 * 
 * 
 */

@Controller
public class MainMaintenanceCundfVareRegisterController {
	private static final Logger logger = LoggerFactory.getLogger(MainMaintenanceCundfVareRegisterController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");

	@RequestMapping(value = "mainmaintenancecundf_vareregister.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doVareRegister(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = null;
		SystemaWebUser appUser = (SystemaWebUser) session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if (appUser == null) {
			return this.loginView;
		} else { 
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			
			if ("NO".equals(appUser.getFiland())) {
				successView = new ModelAndView("redirect:mainmaintenancecundf_vareexp_no.do");
			} else if ("SE".equals(appUser.getFiland())) {
				successView = new ModelAndView("redirect:mainmaintenancecundf_vareexp_se.do");
			} else { // default Export(no)
				successView = new ModelAndView("redirect:mainmaintenancecundf_vareexp_no.do");
			}
		}

		return successView;
	}


}
