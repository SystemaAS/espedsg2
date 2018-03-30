package no.systema.aespedsgroadmap.controller;

import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

//SysJservices
import no.systema.aespedsgroadmap.model.RoadmapObject;


/**
 * eSpedsg roadmap main list Controller 
 * 
 * @author oscardelatorre
 * @date Dec 21, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")

public class JsonEspedsgRoadmapController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(3000);
	private static Logger logger = Logger.getLogger(JsonEspedsgRoadmapController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
		
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="aespedsg_roadmap.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind( HttpSession session, HttpServletRequest request, HttpServletResponse response){
		List list = new ArrayList();
		
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("aespedsgroadmap");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//appUser.setActiveMenu("JSERVICES");
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			list = this.setRoadmapSpecification();
			model.put("list",list);
			//model.addAttribute("model_list", list);
			
			/*
    		Map maxWarningMap = new HashMap<String,String>();
    		String BASE_URL = SysJservicesUrlDataStore.SYSJSERVICES_MAIN_CUNDF_LIST_URL;
    		String urlRequestParams = "user=" + appUser.getUser();
    		logger.info("URL: " + BASE_URL);
        	logger.info("URL PARAMS: " + urlRequestParams);
        	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
        	//Debug --> 
        	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
        	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
        	if(jsonPayload!=null){
        		JsonSysJservicesMainListContainer listContainer = this.sysJservicesListService.getMainListContainer(jsonPayload);
        		list = listContainer.getList();	
        		
        	}
        	*/
			
    		//--------------------------------------
    		//Final successView with domain objects
    		//--------------------------------------
			successView.addObject("model", model);
    		logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    		return successView;
		    
		}
	}
	
	/**
	 * Since there is no data layer...
	 * @return
	 */
	private List setRoadmapSpecification(){
		List list = new ArrayList();
		RoadmapObject obj = new RoadmapObject();
		obj.setId("1");obj.setSubject("Upgrade to Tomcat 8.0");
		obj.setText("RS/OT");
		obj.setStatus("G");
		list.add(obj);
		//
		obj = new RoadmapObject();
		obj.setId("2");obj.setSubject("Upgrade to Java 8");
		obj.setText("Java team");
		obj.setStatus("G");
		list.add(obj);
		//
		obj = new RoadmapObject();
		obj.setId("3");obj.setSubject("Upgrade to Spring 4");
		obj.setText("Java team");
		obj.setStatus("G");
		list.add(obj);
		//
		obj = new RoadmapObject();
		obj.setId("4");obj.setSubject("Infrastructure - Version controll");
		obj.setText("Git / Java team");
		obj.setStatus("G");
		list.add(obj);
		//
		obj = new RoadmapObject();
		obj.setId("5");obj.setSubject("Infrastructure - Code assambley");
		obj.setText("Jenkins / Java team");
		obj.setStatus("Y");
		list.add(obj);
		//
		obj = new RoadmapObject();
		obj.setId("6");obj.setSubject("AS400 migration to JavaServices");
		obj.setText("Java team on FreeForm");
		obj.setStatus("Y");
		list.add(obj);
		return list;
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	
}

