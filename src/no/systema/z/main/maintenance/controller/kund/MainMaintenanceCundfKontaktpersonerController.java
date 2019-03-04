package no.systema.z.main.maintenance.controller.kund;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcRecord;
import no.systema.z.main.maintenance.service.MaintMainCundcService;
//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.validator.MaintMainCundcValidator;


/**
 * Kontaktpersoner in Kunde
 * 
 * 
 * @author Fredrik Möller
 * @date Nov 2, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceCundfKontaktpersonerController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceCundfKontaktpersonerController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	

	@RequestMapping(value = "mainmaintenancecundf_kontaktpersoner_list.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doKontaktPersonerList(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_kontaktpersoner_edit");
		SystemaWebUser appUser = (SystemaWebUser) session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();

		if (appUser == null) {
			return this.loginView;
		} else {
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			String firma = kundeSessionParams.getFirma();
			String kundnr = kundeSessionParams.getKundnr();

			List<JsonMaintMainCundcRecord> list = new ArrayList();
			list = this.fetchList(appUser.getUser(), firma, kundnr);

			model.put("kundnr", kundnr);
			model.put("firma", firma);
			model.put(MainMaintenanceConstants.DOMAIN_LIST, list);
			
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(kundeSessionParams.getKnavn()));

			return successView;
		}
	}
	
	
	@RequestMapping(value="mainmaintenancecundf_kontaktpersoner_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_kontaktpersoner_edit(@ModelAttribute ("record") JsonMaintMainCundcRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_kontaktpersoner_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		logger.info("recordToValidate="+ReflectionToStringBuilder.toString(recordToValidate));
		
		
		if (appUser == null) {
			return this.loginView;
		} else {
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			adjustRecordToValidate(recordToValidate, kundeSessionParams);

			MaintMainCundcValidator validator = new MaintMainCundcValidator();
			if (MainMaintenanceConstants.ACTION_DELETE.equals(action)) {
				validator.validateDelete(recordToValidate, bindingResult);
			} else {
				validator.validate(recordToValidate, bindingResult);
			}

			if (bindingResult.hasErrors()) {
				logger.error("[ERROR Validation] Record does not validate)");
//				bindingResult.getAllErrors().forEach(error -> {
//					logger.error("ERROR:"+error.getDefaultMessage());
//				});
				
				
				if (updateId != null && !"".equals(updateId)) {
					// meaning bounced in an Update and not a Create new
					model.put("updateId", updateId);
				}
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
			} else {
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)) {
					if (updateId != null && !"".equals(updateId)) {
						dmlRetval = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
					} else {
						dmlRetval = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
					}
				} else if (MainMaintenanceConstants.ACTION_DELETE.equals(action)) {
					logger.info("::delete::");
					dmlRetval = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_DELETE, errMsg);
					logger.info("dmlRetval="+dmlRetval);
				}
				// check for Update errors
				if (dmlRetval < 0) {
					logger.error("[ERROR DML] Record does not validate), errMsg="+errMsg);
					if (updateId != null && !"".equals(updateId)) {
						// meaning bounced in an Update and not a Create new
						model.put("updateId", updateId);
					}
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					//model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}

			}

			List<JsonMaintMainCundcRecord> list = new ArrayList();
			list = this.fetchList(appUser.getUser(), kundeSessionParams.getFirma(), kundeSessionParams.getKundnr());

			model.put("kundnr", kundeSessionParams.getKundnr());
			model.put("firma", kundeSessionParams.getFirma());
			model.put(MainMaintenanceConstants.DOMAIN_LIST, list);

			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(kundeSessionParams.getKnavn()));

			return successView;

		}

	}
	
	private void adjustRecordToValidate(JsonMaintMainCundcRecord recordToValidate, KundeSessionParams kundeSessionParams) {
		recordToValidate.setCfirma(kundeSessionParams.getFirma());
		recordToValidate.setCcompn(kundeSessionParams.getKundnr());
		recordToValidate.setSonavn(kundeSessionParams.getSonavn());
		String cavd = recordToValidate.getCavd1()+recordToValidate.getCavd2()+recordToValidate.getCavd3()+recordToValidate.getCavd4()+
				 			recordToValidate.getCavd5()+recordToValidate.getCavd6()+recordToValidate.getCavd7()+recordToValidate.getCavd8()+
				 			recordToValidate.getCavd9()+recordToValidate.getCavd10()+recordToValidate.getCavd11()+recordToValidate.getCavd12()+
				 			recordToValidate.getCavd13()+recordToValidate.getCavd14()+recordToValidate.getCavd15()+recordToValidate.getCavd16()+
				 			recordToValidate.getCavd17()+recordToValidate.getCavd18()+recordToValidate.getCavd19()+recordToValidate.getCavd20();
		recordToValidate.setCavd(cavd);
		String copd = recordToValidate.getCopd1()+recordToValidate.getCopd2()+recordToValidate.getCopd3()+recordToValidate.getCopd4()+recordToValidate.getCopd5();
		recordToValidate.setCopd(copd);
		
	}

	
	private List<JsonMaintMainCundcRecord> fetchList(String applicationUser, String cfirma, String ccompn){
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_CUNDC_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user="+ applicationUser);
		urlRequestParams.append("&cfirma="+cfirma);
		urlRequestParams.append("&ccompn="+ccompn);
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	logger.info("jsonPayload="+jsonPayload);
    	List<JsonMaintMainCundcRecord> list = new ArrayList();
    	if(jsonPayload!=null){
    		JsonMaintMainCundcContainer container = this.maintMainCundcService.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
/*				for (JsonMaintMainCundcRecord record : list) {
					logger.info("record:" + record.toString());
				}
*/			}
    	}
    	return list;
    	
	}
	
	private int updateRecord(SystemaWebUser appUser, JsonMaintMainCundcRecord record, String mode, StringBuffer errMsg) {
		int retval = 0;

		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_CUNDC_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mode=" + mode + "&lang=" +appUser.getUsrLang();
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		// extract
		if (jsonPayload != null) {
			// lists
			JsonMaintMainCundcContainer container = this.maintMainCundcService.doUpdate(jsonPayload);
			if (container != null) {
				if (container.getErrMsg() != null && !"".equals(container.getErrMsg())) {
						errMsg.append(container.getErrMsg());
						retval = MainMaintenanceConstants.ERROR_CODE;
				}
			}
		}

		return retval;
	}	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("maintMainCundcService")
	private MaintMainCundcService maintMainCundcService;
	@Autowired
	@Required
	public void setMaintMainCundcService (MaintMainCundcService value){ this.maintMainCundcService = value; }
	public MaintMainCundcService getMaintMainCundcService(){ return this.maintMainCundcService; }

		
}

