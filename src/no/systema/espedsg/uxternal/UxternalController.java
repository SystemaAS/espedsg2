package no.systema.espedsg.uxternal;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
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
import no.systema.main.service.login.SystemaWebLoginService;
import no.systema.main.url.store.MainUrlDataStore;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.JsonFirmLoginContainer;
import no.systema.main.model.jsonjackson.JsonFirmLoginRecord;
import no.systema.main.model.jsonjackson.JsonSystemaUserContainer;
import no.systema.espedsgadmin.service.FileDatabaseService;


/**
 * eSpedsg External Controller
 * This controller is used from external systems. Usually from RPG-GUIs.
 * The main difference within all other modules or submodules is that no login will be required as mandatory. 
 * This modules could be used without any login use-case what so ever. E.g. loading a file model dialog ... etc
 * 
 * @author oscardelatorre
 * @date Oct 2020
 * 
 */

@Controller
public class UxternalController {
	
	//SERVICES
	@Autowired 
	private UrlCgiProxyService urlCgiProxyService;
		
	@Autowired 
	private SystemaWebLoginService systemaWebLoginService;
	
	
	private static final Logger logger = Logger.getLogger(UxternalController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private String ERROR_STD_TEXT = "Invalid server request";
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="uxternal_uploadfile.do", params="action=doInit", method=RequestMethod.GET)
	public ModelAndView doUploadFile(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("espedsg_uxternal_uploadfile");
		
		String user = request.getParameter("user");
		String avd = request.getParameter("avd");
		String opd = request.getParameter("opd");
		
		if(this.isValidRequest(user, avd, opd)){
			model.put("user", user);	
			model.put("wsavd", avd);
			model.put("wsopd", opd);
			
		}else{
			model.put("errorMessage", this.ERROR_STD_TEXT);
			
		}	
		successView.addObject("model" , model);
    	return successView;
	}
	
	/**
	 * validation of entry point
	 * @param user
	 * @param avd
	 * @param opd
	 * @return
	 */
	private boolean isValidRequest(String user, String avd, String opd){
		boolean retval = false;
		if(StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(avd) && StringUtils.isNotEmpty(opd)){
			if(checkUser(user)){
				retval = true;
			}
		}
		
		return retval;
	}
	
	/**
	 * Checks user validity in BRIDF
	 * @param applicationUser
	 * @return
	 */
	private boolean checkUser(String applicationUser){
		boolean retval = false;
		//http://localhost:8080/syjservicesbcore/syjsBRIDFDPR.do?user=OSCAR
		String URL = MainUrlDataStore.SYSTEMA_GET_USER_FROM_BRIDF_URL;
		String urlRequestParams = "user="+ applicationUser;
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(URL, urlRequestParams);
    	logger.warn(URL);
    	logger.warn(urlRequestParams);
    	logger.warn(jsonPayload);
    	JsonSystemaUserContainer container = this.systemaWebLoginService.getSystemaUserContainer(jsonPayload);
    	if(container!=null && container.getUser().equals(applicationUser)){
    		retval = true;
    	}
	
    	return retval;	
	}
	
	
}
	
	
	
	

