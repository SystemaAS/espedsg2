package no.systema.main.controller;

import org.springframework.web.servlet.ModelAndView;

import no.systema.main.util.AppConstants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.login.SystemaWebLoginService;
import no.systema.main.url.store.MainUrlDataStore;
import no.systema.main.util.AppConstants;
import no.systema.main.model.jsonjackson.JsonBridfChangePwdContainer;
import no.systema.main.model.jsonjackson.JsonBridfChangePwdRecord;
import no.systema.main.model.jsonjackson.JsonSystemaUserContainer;




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
	public ModelAndView login(Model model, HttpServletRequest request ){
		logger.info("Before login controller execution");
		//if there was an error when changing the password...
		String errorChgPwd= request.getParameter("epw");
		
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
		if("1".equalsIgnoreCase(errorChgPwd)){
			model.addAttribute(AppConstants.ASPECT_ERROR_MESSAGE, "There was an error when changing your password...");
		}
		loginView.addObject("model",model);
		//
		logger.info("After login controller execution");
		
		return this.loginView;
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="doChgPwd.do", method= { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView doChgPwd(HttpServletRequest request){
		
		Map model = new HashMap();
		
		logger.info("Before login controller execution");
		ModelAndView successView = null;
		ModelAndView errorView = new ModelAndView("redirect:login.do?epw=1");
		ModelAndView localLoginView = new ModelAndView("redirect:login.do");
		
		String user = request.getParameter("validUser");
		String pwd = request.getParameter("passwordNew");
						
		String BASE_URL = MainUrlDataStore.SYSTEMA_WEB_LOGIN_CHANGE_PWD_URL;
		String urlRequestParamsKeys = "user=" + user.toUpperCase() + "&dp=" + pwd.toUpperCase() + "&mode=U";
		
		logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	
    	//--------------------------------------
    	//EXECUTE the FETCH (RPG program) here
    	//--------------------------------------
    	try{
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    	//Debug --> 
	    	//System.out.println(jsonPayload);
	    	logger.info(jsonPayload);
	    	if(jsonPayload!=null){
	    		JsonSystemaUserContainer jsonSystemaUserContainer = this.systemaWebLoginService.getSystemaUserContainerForPassword(jsonPayload);
	    		logger.info("A");
	    		//check for errors
	    		if(jsonSystemaUserContainer!=null){
	    			logger.info("B");
	    			if(jsonSystemaUserContainer.getErrMsg()!=null && !"".equals(jsonSystemaUserContainer.getErrMsg())){
	    				successView = errorView;
	    			}else{
	    				logger.info("OK");
	    				successView = localLoginView;
	    			}
	    		}else{
	    			logger.info("C");
	    			successView = errorView;
	    		}
	    	}else{
	    		logger.info("D");
	    		successView = errorView;
	    	}
    	}catch(Exception e){
    		logger.info("F");
    		successView = errorView;
    	}
		
		return successView;
	}
	
	
	
		//SERVICES
		@Qualifier ("urlCgiProxyService")
		private UrlCgiProxyService urlCgiProxyService;
		@Autowired
		@Required
		public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
		public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
		
		@Qualifier ("systemaWebLoginService")
		private SystemaWebLoginService systemaWebLoginService;
		@Autowired
		@Required
		public void setSystemaWebLoginService (SystemaWebLoginService value){ this.systemaWebLoginService = value; }
		public SystemaWebLoginService getSystemaWebLoginService(){ return this.systemaWebLoginService; }
		
		
    
}

