package no.systema.main.controller;

import org.springframework.web.servlet.ModelAndView;

import no.systema.main.util.AppConstants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.springframework.context.annotation.Scope;
import org.apache.log4j.Logger;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;



@Controller
/*@SessionAttributes(Constants.APP_USER_KEY)
@Scope("session")*/
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class.getName());
	
	private ModelAndView loginView = new ModelAndView("login");

	//The [*.do] suffix is just an arbitrary suffix that could be something else. 
	//If you change it here then it MUST be the same that is used
	//in the JSP or other view (href or other redirect) that is calling this Controller
	@RequestMapping("login.do")
	public ModelAndView login(Model model){
		logger.info("Before login controller execution");
		
		String message = "Welcome till Systema eSped";
		model.addAttribute("messageTag", message);
		//This SystemaWebUser instance is just to comply to the dynamic css property that MUST be in place in the JSP-Login window BEFORE the login
		//NOTE: The real SystemaWebUser is set in the Dashboard controller after the approval of the login
		SystemaWebUser userForCssPurposes = new SystemaWebUser();
		
		//Override default
		userForCssPurposes.setCssEspedsg(AppConstants.CSS_ESPEDSG);
		if(userForCssPurposes.getCssEspedsg().toLowerCase().contains("toten")){
			//Override default
			userForCssPurposes.setEspedsgLoginTitle("Toten Transport AS – EspedSG");
		}
		
		model.addAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, userForCssPurposes);
		loginView.addObject("model",model);
		//
		logger.info("After login controller execution");
		
		return this.loginView;
	}
    
}

