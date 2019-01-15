package no.systema.transportdisp.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.util.manager.CodeDropDownMgr;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowFellesutskriftContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowFellesutskriftRecord;
import no.systema.transportdisp.mapper.url.request.UrlRequestParameterMapper;
import no.systema.transportdisp.service.TransportDispChildWindowService;
import no.systema.transportdisp.service.TransportDispWorkflowFellesutskriftService;
import no.systema.transportdisp.service.TransportDispWorkflowListService;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;

//models


/**
 * Gateway to the TranspDisp Application
 * 
 * 
 * @author oscardelatorre
 * @date Jan 13, 2015
 * 
 * 	
 */

@Controller
public class TransportDispPrintController {
	private static final Logger logger = Logger.getLogger(TransportDispPrintController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private ModelAndView loginView = new ModelAndView("login");
	private StringManager strMgr = new StringManager();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	
	/**
	 * General AS400 direct print out
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transpdisp_mainorder_printout.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrintOut(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//fallback (last resort);
		ModelAndView successView = new ModelAndView("redirect:transportdisp_mainorderlist.do?action=doFind");
				
		String method = "doPrintOut";
		logger.info("Method: " + method);
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String tur = request.getParameter("tur");
		//URL call params
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		//check the parent caller for this print (ORDER or TRIP)
		if(strMgr.isNotNull(opd) && strMgr.isNotNull(avd) ){
			 //fill other params
			 urlRequestParamsKeys.append("&avd=" + avd);
			 urlRequestParamsKeys.append("&opd=" + opd);
			 urlRequestParamsKeys.append("&tur=");
			 
			 //print from Order GUI
			 successView = new ModelAndView("redirect:transportdisp_mainorder.do?user=" + appUser.getUser() + "&hepro=&heavd=" + avd + "&heopd=" + opd);
			 	
		}else{
			//fill other params
			urlRequestParamsKeys.append("&avd=&opd=&tur=" + tur);
			
			//print from Trip GUI
			if(strMgr.isNotNull(tur)){
				successView = new ModelAndView("redirect:transportdisp_workflow_getTrip.do?user=" + appUser.getUser() + "&tuavd=&tupro=");
			}
			
		}
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//-------------------------------------
			//get BASE URL = RPG-PROGRAM for PRINT
            //-------------------------------------
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_PRINT_OUT_FRAKTBREV;
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the Print (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			//Debug --> 
	    	logger.info(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	//END of PRINT here and now
	    	logger.info("Method PRINT END - " + method);	
		    	
		}
		
		return successView;
	}

	
	/**
	 * This method is used for init and for further execution (instead for CRUD)
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_mainorderlist_fellesutskrift.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doFellesutskrift(@ModelAttribute ("record") JsonTransportDispWorkflowFellesutskriftRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("transportdisp_mainorderlist_fellesutskrift");
		logger.info("Method: doFellesutskrift [RequestMapping-->transportdisp_mainorderlist_fellesutskrift.do]");
		
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String opd = request.getParameter("opd");
		String action = request.getParameter("action");
		model.put("avd", avd);
		model.put("sign", sign);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			
			if(TransportDispConstants.ACTION_EXECUTE.equals(action)){
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_EXECUTE_FELLESUTSKRIFT_URL;
				String urlRequestParamsKeys = "user=" + appUser.getUser();
				String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
				//put the final valid param. string
				urlRequestParams = urlRequestParamsKeys + urlRequestParams;
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		    	logger.info("URL PARAMS:" + urlRequestParams);
		    	
		    	
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		    	//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	
				if(jsonPayload!=null){
					JsonTransportDispWorkflowFellesutskriftContainer container = this.transportDispWorkflowFellesutskriftService.getFellesutskriftContainer(jsonPayload);
		    		//ok now with execution ...
					
		    	}else{
					logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}
			}else{
				recordToValidate.setWsavd(avd);
				recordToValidate.setWssg(sign);
				recordToValidate.setWsdt2(this.dateTimeMgr.getNewDateFromNow("ddMMyy", -7));
			}
			
			model.put(TransportDispConstants.DOMAIN_RECORD, recordToValidate);
			this.setCodeDropDownMgr(appUser, model);
			//here we prepare for the update
			model.put("action", TransportDispConstants.ACTION_EXECUTE);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
		}
		return successView;
	}
	
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		//Sign / AVD
		//this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonSignature(this.urlCgiProxyService, trorDropDownListPopulationService, model, appUser);
		this.codeDropDownMgr.populateHtmlDropDownsFromJsonAvd(this.urlCgiProxyService, transportDispChildWindowService, model, appUser);
		//this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(urlCgiProxyService, trorDropDownListPopulationService, model, appUser, this.codeDropDownMgr.CODE_TYPE_DELSYSTEM);
	}
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("transportDispChildWindowService")
	private TransportDispChildWindowService transportDispChildWindowService;
	@Autowired
	@Required
	public void setTransportDispChildWindowService (TransportDispChildWindowService value){ this.transportDispChildWindowService = value; }
	public TransportDispChildWindowService getTransportDispChildWindowService(){ return this.transportDispChildWindowService; }
	
	
	@Qualifier ("transportDispWorkflowFellesutskriftService")
	private TransportDispWorkflowFellesutskriftService transportDispWorkflowFellesutskriftService;
	@Autowired
	@Required
	public void setTransportDispWorkflowFellesutskriftService (TransportDispWorkflowFellesutskriftService value){ this.transportDispWorkflowFellesutskriftService = value; }
	public TransportDispWorkflowFellesutskriftService getTransportDispWorkflowFellesutskriftService(){ return this.transportDispWorkflowFellesutskriftService; }
	
	/*
	@Qualifier ("tvinnSadAuthorizationService")
	private TvinnSadAuthorizationService tvinnSadAuthorizationService;
	@Autowired
	@Required
	public void setTvinnSadAuthorizationService (TvinnSadAuthorizationService value) { this.tvinnSadAuthorizationService = value; }
	public TvinnSadAuthorizationService getTvinnSadAuthorizationService(){ return this.tvinnSadAuthorizationService; }
	*/	
	
}

