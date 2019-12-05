package no.systema.aespedsgtpmmonitor.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.annotation.PostConstruct;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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
import no.systema.main.model.jsonjackson.JsonSystemaUserRecord;
import no.systema.aespedsgtpmmonitor.model.JsonTpmmonitorObjectRecord;
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

public class JsonTpmmonitorController {
	private static Logger logger = Logger.getLogger(JsonTpmmonitorController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	
	//
	private final String RUNNER_LIST = "list";
	private final String RUNNER_LIST_SIZE = "listSize";
	private StringManager strMgr = new StringManager();

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
	@RequestMapping(value="aespedsgtpmmonitor.do_OBSOLETE", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doList( HttpSession session, HttpServletRequest request, HttpServletResponse response){
		List<CustomerApplicationObject> list = new ArrayList<CustomerApplicationObject>();
		
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("aespedsgtpmmonitor");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		List<JsonSystemaUserRecord> listOfDashboardModules = (List)session.getAttribute(AppConstants.DOMAIN_LIST);
					
		//logger.info("appUser:" + appUser.getMenuList());
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TPM_MONITOR);
			List<JsonTpmmonitorObjectRecord> outputList = new ArrayList<JsonTpmmonitorObjectRecord>();
			
			//init with espedsg2 - dashboard
			JsonTpmmonitorObjectRecord tpmmonitorRec = new JsonTpmmonitorObjectRecord();
			tpmmonitorRec.setDesc("Dashboard");
			tpmmonitorRec.setModule("espedsg2");
			tpmmonitorRec.setUrl("/espedsg2/monitoring");
			outputList.add(tpmmonitorRec);
			
			//now with all modules reg. in: go esped (AS400)
			for (JsonSystemaUserRecord record : listOfDashboardModules){
				if(strMgr.isNotNull(record.getProg()) && record.getProg().startsWith("TOMCAT")){
					logger.info(record.getProg() + " " + record.getPrTxt());
					if(record.getProg().toUpperCase().contains("TDS")){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/espedsgtds/monitoring");
						outputList.add(tpmmonitorRec);
					}else if(record.getProg().toUpperCase().contains("SKAT")){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/espedsgskat/monitoring");
						outputList.add(tpmmonitorRec);
					}else if(record.getProg().toUpperCase().contains("TVINN")){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/espedsgtvinnsad/monitoring");
						outputList.add(tpmmonitorRec);
					}else if(record.getProg().toUpperCase().contains("EBOOKING")){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/espedsgebook/monitoring");
						outputList.add(tpmmonitorRec);
					}else if(record.getProg().toUpperCase().contains("TROR")){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/espedsgtror/monitoring");
						outputList.add(tpmmonitorRec);
					}else if(record.getProg().toUpperCase().contains("UFORTOPPD") || record.getProg().toUpperCase().contains("KVALITET") ){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/espedsgoverview/monitoring");
						outputList.add(tpmmonitorRec);
					}else if(record.getProg().toUpperCase().contains("PRISKALK")){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/espedsgpkalk/monitoring");
						outputList.add(tpmmonitorRec);
					}else if(record.getProg().toUpperCase().contains("SPORROPP") || record.getProg().toUpperCase().contains("WRKTRIPS") ){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/espedsgtranspdisp/monitoring");
						outputList.add(tpmmonitorRec);
					}else if(record.getProg().toUpperCase().contains("EFAKTURA")){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/espedsgefaktura/monitoring");
						outputList.add(tpmmonitorRec);
					}else if(record.getProg().toUpperCase().contains("RAPPORTER")){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/espedsgstats/monitoring");
						outputList.add(tpmmonitorRec);
					}else if(record.getProg().toUpperCase().contains("GODSREGNO")){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/espedsggodsno/monitoring");
						outputList.add(tpmmonitorRec);
					}else if(record.getProg().toUpperCase().contains("VISMA")){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/visma-net-proxy/monitoring");
						outputList.add(tpmmonitorRec);
					}else if(record.getProg().toUpperCase().contains("TAVGG")){
						tpmmonitorRec = new JsonTpmmonitorObjectRecord();
						tpmmonitorRec.setDesc(record.getPrTxt());
						tpmmonitorRec.setModule(record.getProg());
						tpmmonitorRec.setUrl("/espedsgtvinnavgg/monitoring");
						outputList.add(tpmmonitorRec);
					}
					
					
				}
				
			}
			
			model.put("moduleList",outputList);
			model.put("moduleListSize", outputList.size());
			
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

