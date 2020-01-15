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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import no.systema.jservices.common.util.AesEncryptionDecryptionManager;
import no.systema.main.cookie.SessionCookieManager;
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
	private AesEncryptionDecryptionManager aesManager = new AesEncryptionDecryptionManager();
	
	//The [*.do] suffix is just an arbitrary suffix that could be something else. 
	//If you change it here then it MUST be the same that is used
	//in the JSP or other view (href or other redirect) that is calling this Controller
	@RequestMapping("login.do")
	public ModelAndView login(Model model, HttpServletRequest request, HttpServletResponse response ){
		logger.info("Before login controller execution");
		
		//if there was an error when changing the password...
		String errorChgPwd= request.getParameter("epw");
		
		String message = "Welcome till Systema eSped";
		model.addAttribute("messageTag", message);
		//This SystemaWebUser instance is just to comply to the dynamic css, reCaptcha - property that MUST be in place in the JSP-Login window BEFORE the login
		//NOTE: The real SystemaWebUser is set in the Dashboard controller after the approval of the login
		SystemaWebUser appUserPreLogin = new SystemaWebUser();
		
		//Override default
		appUserPreLogin.setCssEspedsg(AppConstants.CSS_ESPEDSG);
		if(appUserPreLogin.getCssEspedsg().toLowerCase().contains("toten")){
			//Override default
			appUserPreLogin.setEspedsgLoginTitle("Toten Transport AS – EspedSG");
		}else if(appUserPreLogin.getCssEspedsg().toLowerCase().contains("nortrail")){
			//Override default
			appUserPreLogin.setEspedsgLoginTitle("Nortrail AS – EspedSG");
		}
		//reCaptcha
		appUserPreLogin.setRecaptchaSiteKey(AppConstants.LOGIN_RECAPTCHA_SITE_KEY);
		appUserPreLogin.setRecaptchaSecretKey(AppConstants.LOGIN_RECAPTCHA_SECRET_KEY);
		
		
		model.addAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUserPreLogin);
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
	 */
	@RequestMapping(value="doChgPwd.do", method= { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView doChgPwd(HttpSession session, HttpServletRequest request){
		SessionCookieManager cookieMgr = new SessionCookieManager(request);
		
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		logger.info("Before login controller execution");
		ModelAndView successView = null;
		ModelAndView errorView = new ModelAndView("redirect:login.do?epw=1");
		ModelAndView localLoginView = new ModelAndView("redirect:login.do");
		
		
		String user = this.aesManager.decrypt(request.getParameter("validUser"));
		String pwd = request.getParameter("passwordNew");
		
		
		if(appUser==null){
			return this.loginView;
			
		}else if(!cookieMgr.isAuthorized(user, request))	{	
			return this.loginView;
			
		}else{
					
			String BASE_URL = MainUrlDataStore.SYSTEMA_WEB_LOGIN_CHANGE_PWD_URL;
			String urlRequestParamsKeys = "user=" + user.toUpperCase() + "&dp=" + pwd.toUpperCase() + "&mode=U";
			
			logger.debug("URL: " + BASE_URL);
	    	logger.debug("URL PARAMS: " + urlRequestParamsKeys);
	    	
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

