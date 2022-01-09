package no.systema.aespedsgtestersuite.controller;


import java.util.*;

import javax.annotation.PostConstruct;
import org.slf4j.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.JsonSystemaUserRecord;

import no.systema.aespedsgtestersuite.model.JsonTestersuiteObjectContainer;
import no.systema.aespedsgtestersuite.model.JsonTestersuiteObjectRecord;
import no.systema.aespedsgtestersuite.service.TestersuiteService;


/**
 * eSpedsg tester suite list Controller 
 * 
 * @author oscardelatorre
 * @date Feb 16, 2018
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")

public class JsonEspedsgTestersuiteController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(3000);
	private static Logger logger = LoggerFactory.getLogger(JsonEspedsgTestersuiteController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	
	private LoginValidator loginValidator = new LoginValidator();
	private final String GREEN_STATUS = "G";
	private final String CONTROLLER_TEST_MODULE_URL = "aespedsgtestersuite_detail";
	
	//test module-children
	private final String TEST_MODULE_OPPDREG = "oppdreg";
	private final String TEST_MODULE_TDS = "tds";
	private final String TEST_MODULE_TVINN = "tvinn";
	private final String TEST_MODULE_SKAT = "skat";
	private final String TEST_MODULE_AVG_GRUNNLAG = "avggrunn";
	private final String TEST_MODULE_EFAKTURA = "efaktura";
	private final String TEST_MODULE_EBOOKING = "ebooking";
	private final String TEST_MODULE_LASTETORG = "lastetorg";
	private final String TEST_MODULE_PRISKALK = "priskalk";
	private final String TEST_MODULE_UFORTOLL = "ufortoll";
	private final String TEST_MODULE_SPORROPPD = "sporroppd";
	private final String TEST_MODULE_ALTINN = "altinn";
	private final String TEST_MODULE_STATS = "stats";
	private final String TEST_MODULE_GODSREGNO = "godsno";
	
	//
	private final String TEST_LIST = "list";
	private final String TEST_LIST_SIZE = "listSize";
	private final String TEST_LIST_SERVICES = "listServices";
	private final String TEST_MODULECHILD = "moduleChild";
	//
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
	@RequestMapping(value="aespedsgtestersuite.do_OBSOLETE", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doList( HttpSession session, HttpServletRequest request, HttpServletResponse response){
		List list = new ArrayList();
		
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("aespedsgtestersuite");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//logger.info("appUser:" + appUser.getMenuList());
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TEST_SUITES);
			list = this.initTesterSuiteSpecificationStrict(appUser);
			model.put(TEST_LIST,list);
			model.put(TEST_LIST_SIZE, list.size());
			
    		//--------------------------------------
    		//Final successView with domain objects
    		//--------------------------------------
			successView.addObject("model", model);
    		return successView;
		    
		}
	}
	
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="aespedsgtestersuite_detail.do_OBSOLETE", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doTest( HttpSession session, HttpServletRequest request, HttpServletResponse response){
		Collection<JsonTestersuiteObjectRecord> list = new ArrayList<JsonTestersuiteObjectRecord>();
		
		Map<String, Object> model = new HashMap<String, Object>();
		//test module from JSP
		String testModule = request.getParameter("tm");
		
		ModelAndView successView = new ModelAndView("aespedsgtestersuite_detail");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			
			list = this.executeTesterSuiteServices(appUser, testModule, model);
			
			//back to caller VIEW
			model.put(this.TEST_MODULECHILD, testModule.toUpperCase());
			model.put(TEST_LIST_SERVICES,list);
			model.put(TEST_LIST_SIZE, list.size());
			
			//--------------------------------------
    		//Final successView with domain objects
    		//--------------------------------------
			successView.addObject("model", model);
    		return successView;
		    
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param testModule
	 * @param model
	 * @return
	 */
	private Collection<JsonTestersuiteObjectRecord> executeTesterSuiteServices(SystemaWebUser appUser, String testModule, Map<String, Object> model){
		logger.info("Inside setTesterSuiteServices...");
		Collection<JsonTestersuiteObjectRecord> outputList = new ArrayList<JsonTestersuiteObjectRecord>();
		
		try{
			String BASE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + this.getBaseUrl(testModule);
			
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp < " + BASE_URL);
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL,"user=" + appUser.getUser());
			//debug --> logger.info(jsonPayload);
			//get container
			JsonTestersuiteObjectContainer container = this.testersuiteService.getContainer(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			
			if(container!=null){
				outputList = container.getList();
			}else{
				//Debug
				logger.info("[ERROR] JSON-container = null... ? ");
			}
			
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		return outputList;
	}
	/**
	 * 
	 * @param testModule
	 * @return
	 */
	private String getBaseUrl (String testModule){
		String retval = "";
		if(this.TEST_MODULE_SKAT.equalsIgnoreCase(testModule)){
			retval = "/espedsgskat/sytsuite.do";
		
		}else if(this.TEST_MODULE_TDS.equalsIgnoreCase(testModule)){
			retval = "/espedsgtds/sytsuite.do";
		
		}else if(this.TEST_MODULE_TVINN.equalsIgnoreCase(testModule)){
			retval = "/espedsgtvinnsad/sytsuite.do";
		
		}else if(this.TEST_MODULE_AVG_GRUNNLAG.equalsIgnoreCase(testModule)){
			retval = "/espedsgtvinnavgg/sytsuite.do";
		
		}else if(this.TEST_MODULE_PRISKALK.equalsIgnoreCase(testModule)){
			retval = "/espedsgpkalk/sytsuite.do";
		
		}else if(this.TEST_MODULE_EFAKTURA.equalsIgnoreCase(testModule)){
			retval = "/espedsgefaktura/sytsuite.do";
		
		}else if(this.TEST_MODULE_UFORTOLL.equalsIgnoreCase(testModule)){
			retval = "/espedsgoverview/sytsuite.do";
		
		}else if(this.TEST_MODULE_EBOOKING.equalsIgnoreCase(testModule)){
			retval = "/espedsgebook/sytsuite.do";
			
		}else if(this.TEST_MODULE_LASTETORG.equalsIgnoreCase(testModule)){
			retval = "/espedsgtranspdisp/sytsuite_transpDisp.do";
			
		}else if(this.TEST_MODULE_SPORROPPD.equalsIgnoreCase(testModule)){
			retval = "/espedsgtranspdisp/sytsuite_sporroppd.do";
			
		}else if(this.TEST_MODULE_OPPDREG.equalsIgnoreCase(testModule)){
			retval = "/espedsgtror/sytsuite.do";
			
		}else if(this.TEST_MODULE_STATS.equalsIgnoreCase(testModule)){
			retval = "/espedsgstats/sytsuite.do";
			
		}else if(this.TEST_MODULE_GODSREGNO.equalsIgnoreCase(testModule)){
			retval = "/espedsggodsno/sytsuite.do";
		}
		
		
		
		return retval;
	}
	

	
	/**
	 * Since there is no data layer...
	 * @param appUser
	 * @return
	 */
	private List initTesterSuiteSpecification(SystemaWebUser appUser){
		List list = new ArrayList();
		JsonTestersuiteObjectRecord obj =null;
		//ALTINN
		obj = new JsonTestersuiteObjectRecord();
		obj.setId("s");obj.setModuleName("Altinn-proxy");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl("aespedsgtestersuite_altinnproxy");
		obj.setText(this.TEST_MODULE_ALTINN);
		//logger.info(obj.getServiceUrl());
		list.add(obj);
		//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("eBooking");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_EBOOKING);
			list.add(obj);
		//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("eFaktura Log - N");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_EFAKTURA);
			list.add(obj);
		//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Fortolling - Avgiftsgrunnlag NO");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_AVG_GRUNNLAG);
			list.add(obj);
		//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Fortolling - TVINN og Kundedatakontroll mot Brønnyøsund - NO");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_TVINN);
			list.add(obj);
		//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Fortolling - TDS");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_TDS);
			list.add(obj);
		//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Fortolling - SKAT");
			obj.setStatus(GREEN_STATUS);
			//obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_SKAT);
			list.add(obj);
		//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Godsregistrering");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_GODSREGNO);
			list.add(obj);

			//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Lastetorg");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(TEST_MODULE_LASTETORG);
			list.add(obj);
		//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Oppdragsregistrering");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_OPPDREG);
			list.add(obj);
		//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Priskalkulator");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_PRISKALK);
			list.add(obj);
		//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Spørring på Oppdrag");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_SPORROPPD);
			list.add(obj);
		//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Ufortollede oppdrag");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_UFORTOLL);
			list.add(obj);
		//
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Stats");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_STATS);
			list.add(obj);
			
			return list;
	}
	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private List initTesterSuiteSpecificationStrict(SystemaWebUser appUser){
		List list = new ArrayList();
		JsonTestersuiteObjectRecord obj =null;
		//ALTINN
		obj = new JsonTestersuiteObjectRecord();
		obj.setId("s");obj.setModuleName("Altinn-proxy");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl("aespedsgtestersuite_altinnproxy");
		obj.setText(this.TEST_MODULE_ALTINN);
		//logger.info(obj.getServiceUrl());
		list.add(obj);
		//
		if(this.moduleExists("EBOOKING", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("eBooking");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_EBOOKING);
			list.add(obj);
		}
		//
		if(this.moduleExists("EFAKTURA", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("eFaktura Log - N");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_EFAKTURA);
			list.add(obj);
		}
		//
		if(this.moduleExists("TAVGG", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Fortolling - Avgiftsgrunnlag NO");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_AVG_GRUNNLAG);
			list.add(obj);
		}
		//
		if(this.moduleExists("TVINN", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Fortolling - TVINN og Kundedatakontroll mot Brønnyøsund - NO");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_TVINN);
			list.add(obj);
		}
		//
		if(this.moduleExists("TDS", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Fortolling - TDS");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_TDS);
			list.add(obj);
		}
		//
		if(this.moduleExists("SKAT", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Fortolling - SKAT");
			obj.setStatus(GREEN_STATUS);
			//obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_SKAT);
			list.add(obj);
		}
		//
		if(this.moduleExists("GODSREGNO", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Godsregistrering NO");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_GODSREGNO);
			list.add(obj);
		}
		//
		if(this.moduleExists("WRKTRIPS", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Lastetorg");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(TEST_MODULE_LASTETORG);
			list.add(obj);
		}
		//
		if(this.moduleExists("TROR", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Oppdragsregistrering");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_OPPDREG);
			list.add(obj);
		}
		//
		if(this.moduleExists("PRISKALK", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Priskalkulator");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_PRISKALK);
			list.add(obj);
		}
		//
		if(this.moduleExists("SPORROPP", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Spørring på Oppdrag");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_SPORROPPD);
			list.add(obj);
		}
		//
		if(this.moduleExists("RAPPORTER", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Stats");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_STATS);
			list.add(obj);
		}
		//
		if(this.moduleExists("UFORTOPPD", appUser)){
			obj = new JsonTestersuiteObjectRecord();
			obj.setId("s");obj.setModuleName("Ufortollede oppdrag");
			obj.setStatus(GREEN_STATUS);
			obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
			obj.setText(this.TEST_MODULE_UFORTOLL);
			list.add(obj);
		}
		
		return list;
	}
	/**
	 * 
	 * @param moduleSignature
	 * @param appUser
	 * @return
	 */
	private boolean moduleExists(String moduleSignature, SystemaWebUser appUser){
		boolean retval = false;
		
		for(JsonSystemaUserRecord module: appUser.getMenuList()){
			if(strMgr.isNotNull(moduleSignature)){
				if(module.getProg().toUpperCase().contains(moduleSignature.toUpperCase())){
					retval = true;
				}
			}
		}
		
		return retval;
	}
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("testersuiteService")
	private TestersuiteService testersuiteService;
	@Autowired
	@Required
	public void setTestersuiteService (TestersuiteService value){ this.testersuiteService = value; }
	public TestersuiteService getTestersuiteService(){ return this.testersuiteService; }
	
	
}

