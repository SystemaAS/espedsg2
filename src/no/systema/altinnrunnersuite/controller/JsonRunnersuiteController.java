package no.systema.altinnrunnersuite.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;
import no.systema.espedsgadmin.model.CustomerApplicationObject;
import no.systema.espedsgadmin.service.FileDatabaseService;


/**
 * eSpedsg altinn runner suite list Controller 
 * 
 * @author oscardelatorre
 * @date Aug 2019
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")

public class JsonRunnersuiteController {
	private static Logger logger = LoggerFactory.getLogger(JsonRunnersuiteController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	
	//
	private final String RUNNER_LIST = "list";
	private final String RUNNER_LIST_SIZE = "listSize";
	private StringManager strMgr = new StringManager();

	@PostConstruct
	public void initIt() throws Exception {
		
	}
	
		
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="altinnrunnersuite.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doList( HttpSession session, HttpServletRequest request, HttpServletResponse response){
		List<CustomerApplicationObject> list = new ArrayList<CustomerApplicationObject>();
		
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("altinnrunnersuite");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("appUser:" + appUser.getMenuList());
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_ALTINN_RUNNER_SUITES);
			//list = this.initTesterSuiteSpecificationStrict(appUser);
			List<CustomerApplicationObject> dbObjectList = this.fileDatabaseService.getCustomerApplicationList();
			for (CustomerApplicationObject record : dbObjectList){
				logger.info(record.getName());
				inner: for(String module : record.getApplicationList() ){
					//logger.info("app:" + module);
					if(module.contains("ALTINN-PROXY")){
						String tmp = record.getUrlHttps();
						if(StringUtils.isEmpty(tmp)){
							tmp = record.getUrl();
						}
						int i = tmp.indexOf("espedsg");
						if(i > -1){
							tmp = tmp.substring(0,i);
							record.setUrl(tmp);
							list.add(record);
							break inner;
						}else{
							//Saas:ar special https
							i = tmp.indexOf("systema.no");
							if(i > -1){
								String httpTmp = record.getUrl();
								int j = httpTmp.indexOf("espedsg");
								httpTmp = httpTmp.substring(0,j);
								record.setUrl(httpTmp);
								list.add(record);
								break inner;
							}
						}
					}
				}
			}
			model.put(RUNNER_LIST,list);
			model.put(RUNNER_LIST_SIZE, list.size());
			
			model.put("yesterday",this.getYesterdayDate());
			
			//--------------------------------------
    		//Final successView with domain objects
    		//--------------------------------------
			successView.addObject("model", model);
    		return successView;
		    
		}
	}
	/**
	 * 
	 * @return
	 */
	private String getYesterdayDate(){
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime yesterday = now.minusDays(1);
		
		return dtf.format(yesterday);
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	
	//SERVICES
	@Qualifier ("fileDatabaseService")
	private FileDatabaseService fileDatabaseService;
	@Autowired
	@Required
	public void setFileDatabaseService (FileDatabaseService value){ this.fileDatabaseService = value; }
	public FileDatabaseService getFileDatabaseService(){ return this.fileDatabaseService; }
	
	
}

