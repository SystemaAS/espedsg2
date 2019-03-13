package no.systema.z.main.maintenance.controller.kund;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import no.systema.jservices.common.elma.entities.Entry;
import no.systema.jservices.common.elma.proxy.EntryRequest;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
import no.systema.z.main.maintenance.service.MaintMainCundfService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.util.manager.CodeDropDownMgr;
import no.systema.z.main.maintenance.validator.MaintMainCundfValidator;


/**
 * Gateway for Kunde detaljer
 * 
 * 
 * @author Fredrik Möller
 * @date Okt 26, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceCundfKundeController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceCundfKundeController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	
	@Autowired
	VkundControllerUtil vkundControllerUtil;	

	@RequestMapping(value="mainmaintenancecundf_kunde_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_vkund_edit(@ModelAttribute ("record") JsonMaintMainCundfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_kunde_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String action = request.getParameter("action");
		StringBuffer errMsg = new StringBuffer();
		JsonMaintMainCundfRecord savedRecord = null;
		JsonMaintMainCundfRecord record = null;
		
		logger.info("recordToValidate="+ReflectionToStringBuilder.toString(recordToValidate));


		try {
			
	
		if (appUser == null) {
			return this.loginView;
		} else {
			KundeSessionParams kundeSessionParams = null;
			kundeSessionParams = (KundeSessionParams)session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			
			if (MainMaintenanceConstants.ACTION_CREATE.equals(action)) {  //New
				// Validate
				MaintMainCundfValidator validator = new MaintMainCundfValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					
					action = MainMaintenanceConstants.ACTION_CREATE;
					 
				} else {
					savedRecord = this.updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
					if (savedRecord == null) {
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
						
						action = MainMaintenanceConstants.ACTION_CREATE;
						
					} else {
						kundeSessionParams.setKundnr(savedRecord.getKundnr());
						kundeSessionParams.setFirma(savedRecord.getFirma());
						kundeSessionParams.setSonavn(savedRecord.getSonavn());
						kundeSessionParams.setKnavn(savedRecord.getKnavn());

						record = this.fetchRecord(appUser.getUser(), kundeSessionParams.getKundnr(), kundeSessionParams.getFirma());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
						
						action = MainMaintenanceConstants.ACTION_UPDATE;
						
					}
				}

			} else if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)) { //Update
				adjustRecordToValidate(recordToValidate, kundeSessionParams);
				MaintMainCundfValidator validator = new MaintMainCundfValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.error("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				} else {
					savedRecord = this.updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
					if (savedRecord == null) {            
						logger.error("[ERROR Update] Record could not be updated, errMsg="+errMsg.toString());
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					} else {
						record = this.fetchRecord(appUser.getUser(), kundeSessionParams.getKundnr(), kundeSessionParams.getFirma());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					}
				}
			} else { // Fetch
				record = fetchRecord(appUser.getUser(), kundeSessionParams.getKundnr(), kundeSessionParams.getFirma());
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
			}

			populateDropDowns(model, appUser.getUser());
			
			model.put("action", action);
			model.put("kundnr", kundeSessionParams.getKundnr());
			model.put("firma", kundeSessionParams.getFirma());
			model.put("invoiceCustomerAllowed", vkundControllerUtil.getInvoiceCustomerAllowed(appUser));
			model.put("isAdressCustomer", vkundControllerUtil.isAdressCustomer(appUser, new Integer(kundeSessionParams.getKundnr())));
			model.put("orgNrMulti", vkundControllerUtil.orgNrMulti(recordToValidate.getSyrg(), appUser));
			model.put("hasSypogeAndNO", vkundControllerUtil.hasSypogeAndNO(recordToValidate.getSypoge(), recordToValidate.getSyland() , appUser));

			
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(kundeSessionParams.getKnavn()));
			
			return successView;		

		}
		} catch (Exception e) {
			logger.error("ERROR:", e);
			String errorMessage = "Teknisk feil. Kontakt helpdesk. Error:"+e;
			model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
			model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errorMessage);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(recordToValidate.getKnavn()));
			
			return successView;		

		}

	}
	
	/**
	 * Check orgnr in ELMA. 
	 * 
	 * @param applicationUser
	 * @param syrg
	 * @return J if exist, else N.
	 */
	@RequestMapping(value = "existInElma.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String existInElma(@RequestParam String applicationUser, @RequestParam String syrg) {
		
		return existInElma(syrg); 

	}	

	private JsonMaintMainCundfRecord fetchRecord(String applicationUser, String kundnr, String firma) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&kundnr=" + kundnr);
		urlRequestParams.append("&firma=" + firma);

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(jsonPayload);

		JsonMaintMainCundfRecord record = new JsonMaintMainCundfRecord(), fmotRecord = new JsonMaintMainCundfRecord();
		if (jsonPayload != null) {
			jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist"); //??
			JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
			if (container != null) {
				for (Iterator<JsonMaintMainCundfRecord> iterator = container.getList().iterator(); iterator.hasNext();) {
					record = (JsonMaintMainCundfRecord) iterator.next();
					if (!kundnr.equals(record.getFmot())) { // to avoid circular-ref
						fmotRecord= fetchRecord(applicationUser,record.getFmot(),firma);
						record.setFmotname(fmotRecord.getKnavn());
					}
					String leftPaddedPostnr = StringUtils.leftPad(record.getPostnr(), 4, '0');
					record.setPostnr(leftPaddedPostnr);
					record.setElma(existInElma(record.getSyrg()));
				}
			}
		}

		return record;
	}
	

	private JsonMaintMainCundfRecord updateRecord(SystemaWebUser appUser, JsonMaintMainCundfRecord record, String mode, StringBuffer errMsg) {
		logger.info("::updateRecord::");
		JsonMaintMainCundfRecord savedRecord = null;
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mode=" + mode + "&lang=" +appUser.getUsrLang();
		String urlRequestParams = urlRequestParameterMapper.getUrlParameterValidString((record));
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);

		List<JsonMaintMainCundfRecord> list = new ArrayList();
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		logger.info("jsonPayload=" + jsonPayload);
		if (jsonPayload != null) {
			JsonMaintMainCundfContainer container = this.maintMainCundfService.doUpdate(jsonPayload);
			if (container != null) {
				if (container.getErrMsg() != null && !"".equals(container.getErrMsg())) {
					errMsg.append(container.getErrMsg());
					return null;
				}
				list = (List) container.getList();
				for (JsonMaintMainCundfRecord cundfEntity : list) {
					savedRecord = cundfEntity;
				}
			}
		}

		return savedRecord;
	}
	
	private void adjustRecordToValidate(JsonMaintMainCundfRecord recordToValidate, KundeSessionParams kundeSessionParams) {
		recordToValidate.setFirma(kundeSessionParams.getFirma());
		recordToValidate.setKundnr(kundeSessionParams.getKundnr());
	}
	

	private String existInElma(String orgnr) {
		Entry entry = entryRequest.getElmaEntry(orgnr);
		if (entry != null) {
			return "J";
		} else {
			return "N";
		}
	}	

	private void populateDropDowns(Map model, String user) {
		codeDropDownMgr.populateBetBetDropDown(this.urlCgiProxyService,  model, user);
	}	
	
	
	@Autowired
	EntryRequest entryRequest;	
	
	@Autowired
	RestTemplate restTemplate;
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("maintMainCundfService")
	private MaintMainCundfService maintMainCundfService;
	@Autowired
	@Required
	public void setMaintMainCundfService (MaintMainCundfService value){ this.maintMainCundfService = value; }
	public MaintMainCundfService getMaintMainCundfService(){ return this.maintMainCundfService; }

}

