package no.systema.espedsgadmin.controller;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;

import no.systema.main.model.SystemaWebUser;
import no.systema.espedsgadmin.service.FileDatabaseService;


/**
 * eSpedsg Admin Controller 
 * 
 * @author oscardelatorre
 * @date Mar 31, 2014
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class CustomerApplicationController {
	
	private static final Logger logger = Logger.getLogger(CustomerApplicationController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="espedsgadmin.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("espedsgadmin");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			
			List dbObjectList = this.fileDatabaseService.getCustomerApplicationList();
			List dbTomcatPortsObjectList = this.fileDatabaseService.getTomcatAspPortList();
			//
			model.put("dbObjectList", dbObjectList);
			model.put("dbTomcatPortsObjectList", dbTomcatPortsObjectList);
			
			successView.addObject("model" , model);
			
	    	return successView;
		}
	}
	
	
	//SERVICES
	@Qualifier ("fileDatabaseService")
	private FileDatabaseService fileDatabaseService;
	@Autowired
	@Required
	public void setFileDatabaseService (FileDatabaseService value){ this.fileDatabaseService = value; }
	public FileDatabaseService getFileDatabaseService(){ return this.fileDatabaseService; }
	
	
}

