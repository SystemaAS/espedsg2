package no.systema.z.main.maintenance.controller.ajax;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaHodeContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaHodeRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaKodthContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaKodthRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaTellContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaTellRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtsfSyparfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtsfSyparfRecord;


import no.systema.z.main.maintenance.service.MaintMainKodtaHodeService;
import no.systema.z.main.maintenance.service.MaintMainKodtaKodthService;
import no.systema.z.main.maintenance.service.MaintMainKodtaTellService;
import no.systema.z.main.maintenance.service.MaintMainKodtsfSyparfService;


import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 *  MAIN Maintenance AVd - Ajax Controller 
 * 
 * @author oscardelatorre
 * @date Aug 18, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MainMaintenanceAvdAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdAjaxHandlerController.class.getName());
	
	/**
	 * 
	 * @param applicationUser
	 * @param koaavd
	 * @param hoavd
	 * @param honet
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_syfa63r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintMainKodtaHodeRecord>getRecordSad002_kodts1
	  	(@RequestParam String applicationUser, @RequestParam String koaavd, @RequestParam String hoavd, @RequestParam String honet ) {
		final String METHOD = "[DEBUG] getSpecificRecord_syfa63r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintMainKodtaHodeRecord> result = new ArrayList();
	 	//get table
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA63R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&koaavd=" + koaavd + "&hoavd=" + hoavd + "&honet=" + honet ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	List<JsonMaintMainKodtaHodeRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtaHodeContainer container = this.maintMainKodtaHodeService.getList(jsonPayload);
    		if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainKodtaHodeRecord record: list){
	        		//logger.info(record.getHoavd());
	        	}
	        }
    	}
    	return list;
	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param kohavd
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_syfa68r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintMainKodtaKodthRecord>getRecordSyfa68 (@RequestParam String applicationUser, @RequestParam String kohavd ) {
		final String METHOD = "[DEBUG] getSpecificRecord_syfa68r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintMainKodtaHodeRecord> result = new ArrayList();
	 	//get table
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA68R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&kohavd=" + kohavd;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	List<JsonMaintMainKodtaKodthRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtaKodthContainer container = this.maintMainKodtaKodthService.getList(jsonPayload);
    		if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainKodtaKodthRecord record: list){
	        		//logger.info(record.getKohavd());
	        	}
	        }
    	}
    	return list;
	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param teavd
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_syfa26r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintMainKodtaTellRecord>getRecordSyfa26 (@RequestParam String applicationUser, @RequestParam String teavd ) {
		final String METHOD = "[DEBUG] getSpecificRecord_syfa26r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintMainKodtaHodeRecord> result = new ArrayList();
	 	//get table
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA26R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&teavd=" + teavd;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	List<JsonMaintMainKodtaTellRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtaTellContainer container = this.maintMainKodtaTellService.getList(jsonPayload);
    		if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainKodtaTellRecord record: list){
	        		//logger.info(record.getTeavd());
	        	}
	        }
    	}
    	return list;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param teavd
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_syfa60r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintMainKodtsfSyparfRecord>getRecordSyfa60 (@RequestParam String applicationUser, @RequestParam String kosfsi ) {
		final String METHOD = "[DEBUG] getSpecificRecord_syfa60r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintMainKodtsfSyparfRecord> result = new ArrayList();
	 	//get table
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA60R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&kosfsi=" + kosfsi;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	List<JsonMaintMainKodtsfSyparfRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtsfSyparfContainer container = this.maintMainKodtsfSyparfService.getList(jsonPayload);
    		if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainKodtsfSyparfRecord record: list){
	        		logger.info(record.getKosfsi());
	        	}
	        }
    	}
    	return list;
	
	}
	
	
	
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintMainKodtaHodeService")
	private MaintMainKodtaHodeService maintMainKodtaHodeService;
	@Autowired
	@Required
	public void setMaintMainKodtaHodeService (MaintMainKodtaHodeService value){ this.maintMainKodtaHodeService = value; }
	public MaintMainKodtaHodeService getMaintMainKodtaHodeService(){ return this.maintMainKodtaHodeService; }
	
	
	@Qualifier ("maintMainKodtaKodthService")
	private MaintMainKodtaKodthService maintMainKodtaKodthService;
	@Autowired
	@Required
	public void setMaintMainKodtaKodthService (MaintMainKodtaKodthService value){ this.maintMainKodtaKodthService = value; }
	public MaintMainKodtaKodthService getMaintMainKodtaKodthService(){ return this.maintMainKodtaKodthService; }
	
	
	@Qualifier ("maintMainKodtaTellService")
	private MaintMainKodtaTellService maintMainKodtaTellService;
	@Autowired
	@Required
	public void setMaintMainKodtaTellService (MaintMainKodtaTellService value){ this.maintMainKodtaTellService = value; }
	public MaintMainKodtaTellService getMaintMainKodtaTellService(){ return this.maintMainKodtaTellService; }
	
	
	@Qualifier ("maintMainKodtsfSyparfService")
	private MaintMainKodtsfSyparfService maintMainKodtsfSyparfService;
	@Autowired
	@Required
	public void setMaintMainKodtsfSyparfService (MaintMainKodtsfSyparfService value){ this.maintMainKodtsfSyparfService = value; }
	public MaintMainKodtsfSyparfService getMaintMainKodtsfSyparfService(){ return this.maintMainKodtsfSyparfService; }
	
	
	
	
}

