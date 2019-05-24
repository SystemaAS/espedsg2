package no.systema.z.main.maintenance.controller.kund;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
import org.springframework.web.servlet.ModelAndView;

import no.systema.jservices.common.dao.VadrDao;
import no.systema.jservices.common.util.StringUtils;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
import no.systema.z.main.maintenance.service.MaintMainCundcService;
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
		logger.info("action="+action);
		
		try {
			
	
		if (appUser == null) {
			return this.loginView;
		} else {
			KundeSessionParams kundeSessionParams = null;
			kundeSessionParams = (KundeSessionParams)session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			
			if (MainMaintenanceConstants.ACTION_CREATE.equals(action)) {  //New

				theBetBetFix(appUser, recordToValidate, action);
	
				specialRules(recordToValidate);
				
				
				// Validate
				MaintMainCundfValidator validator = new MaintMainCundfValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					
					action = MainMaintenanceConstants.ACTION_CREATE;
					 
				} else {
					savedRecord = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
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

						record = fetchRecord(appUser.getUser(), kundeSessionParams.getKundnr(), kundeSessionParams.getFirma());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
						
						action = MainMaintenanceConstants.ACTION_UPDATE;
						
						
					}
				}

			} else if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)) { //Update

				adjustRecordToValidate(recordToValidate, kundeSessionParams);
	
				theBetBetFix(appUser, recordToValidate, action);
	
				specialRules(recordToValidate);
				
				
				MaintMainCundfValidator validator = new MaintMainCundfValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.error("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				} else {
					savedRecord = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
					if (savedRecord == null) {            
						logger.error("[ERROR Update] Record could not be updated, errMsg="+errMsg.toString());
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					} else if (StringUtils.hasValue(errMsg.toString())){
						logger.error("[ERROR Update] Record could not be updated, errMsg="+errMsg.toString());
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						record = this.fetchRecord(appUser.getUser(), kundeSessionParams.getKundnr(), kundeSessionParams.getFirma());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					} else {
						record = this.fetchRecord(appUser.getUser(), kundeSessionParams.getKundnr(), kundeSessionParams.getFirma());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
						
					}
				}
			} else { // Fetch
				record = fetchRecord(appUser.getUser(), kundeSessionParams.getKundnr(), kundeSessionParams.getFirma());
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
				
				action = MainMaintenanceConstants.ACTION_UPDATE;

				
			}

			populateDropDowns(model, appUser.getUser());
			
			model.put("action", action);
			model.put("kundnr", kundeSessionParams.getKundnr());
			model.put("firma", kundeSessionParams.getFirma());
			model.put("invoiceCustomerAllowed", vkundControllerUtil.getInvoiceCustomerAllowed(appUser));
			if (kundeSessionParams.getKundnr() != null) {
				model.put("isAdressCustomer", vkundControllerUtil.isAdressCustomer(appUser, new Integer(kundeSessionParams.getKundnr())));
			}
			model.put("orgNrMulti", vkundControllerUtil.orgNrMulti(recordToValidate.getSyrg(), appUser));
			model.put("hasSypogeAndNO", vkundControllerUtil.hasSypogeAndNO(recordToValidate.getSypoge(), recordToValidate.getSyland() , appUser));
			if(record != null) {
				model.put("hasVareAddresseNr1", vkundControllerUtil.hasVadrValues(record));
			}

			
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
		return vkundControllerUtil.existInElma(syrg); 

	}	

	private JsonMaintMainCundfRecord fetchRecord(String applicationUser, String kundnr, String firma) {
		logger.info("::fetchRecord::");
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&kundnr=" + kundnr);
		urlRequestParams.append("&firma=" + firma);

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
//		logger.info(jsonPayload);

		JsonMaintMainCundfRecord record = new JsonMaintMainCundfRecord(), fmotRecord = new JsonMaintMainCundfRecord();
		if (jsonPayload != null) {
			jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist"); //??
			JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
			if (container != null) {
				for (Iterator<JsonMaintMainCundfRecord> iterator = container.getList().iterator(); iterator.hasNext();) {
					record = (JsonMaintMainCundfRecord) iterator.next();
					if (StringUtils.hasValueIgnoreZero(record.getFmot()) && !kundnr.equals(record.getFmot())) { // to avoid circular-ref
						fmotRecord= fetchRecord(applicationUser,record.getFmot(),firma);
						record.setFmotname(fmotRecord.getKnavn());
					}
					if (StringUtils.hasValueIgnoreZero(record.getPostnr())) {
						String leftPaddedPostnr = org.apache.commons.lang3.StringUtils.leftPad(record.getPostnr(), 4, '0');
						record.setPostnr(leftPaddedPostnr);
					}
					record.setElma(vkundControllerUtil.existInElma(record.getSyrg()));
					JsonMaintMainCundcRecord cundc = vkundControllerUtil.getInvoiceEmailRecord(applicationUser,firma, kundnr );
					if (cundc != null) {
						record.setEpost("J");
						if (!StringUtils.hasValue(cundc.getCemail())) {
							logger.error("Invalid setup of SINGELFAKTURA and SAMLEFAKTURA! email is empty!");
							record.setEpostmott("varning: epost saknes på SINGELFAKTURA/SAMLEFAKTURA");
						} else {
							record.setEpostmott(cundc.getCemail());
						}
					} else {
						logger.info("cundc is null");
					}
					VadrDao vadrDao = vkundControllerUtil.getVareAdressRecordNr1(applicationUser,firma, kundnr );
					if (vadrDao != null) {
						record.setVadrna(vadrDao.getVadrna());
						record.setVadrn1(vadrDao.getVadrn1());
						record.setVadrn2(vadrDao.getVadrn2());
						record.setVadrn3(vadrDao.getVadrn3());
						record.setValand(vadrDao.getValand());
					} else {
						logger.info("vadrDao is null");
					}
				
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

		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);

		List<JsonMaintMainCundfRecord> list = new ArrayList();
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	//	logger.info("jsonPayload=" + jsonPayload);
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

		logger.info("savedRecord="+ReflectionToStringBuilder.toString(savedRecord, ToStringStyle.MULTI_LINE_STYLE));
		if (savedRecord != null) {  

			manageInvoiceEmail(appUser, record, errMsg, savedRecord);

			manageVareAdresseNr1(appUser, record,  errMsg, savedRecord);
			
		}
		
		return savedRecord;

	}

	private void manageVareAdresseNr1(SystemaWebUser appUser, JsonMaintMainCundfRecord record,  StringBuffer errMsg, JsonMaintMainCundfRecord savedRecord) {
		logger.info("::manageVareAdresseNr1::");
		int retval;
		VadrDao existVadrDao = vkundControllerUtil.getVareAdressRecordNr1(appUser.getUser(),savedRecord.getFirma(), savedRecord.getKundnr() );

		VadrDao dao = new VadrDao();
		dao.setVadrnr(1);
		dao.setVadrna(record.getVadrna());
		dao.setVadrn1(record.getVadrn1());
		dao.setVadrn2(record.getVadrn2());
		dao.setVadrn3(record.getVadrn3());
		dao.setValand(record.getValand());
		dao.setFirma(savedRecord.getFirma());
		dao.setKundnr(Integer.parseInt(savedRecord.getKundnr()));
		
		if (existVadrDao == null) {
			if (isEmpty(dao)) {
				//do nothing
			} else {
				retval = vkundControllerUtil.saveVareAdressRecordNr1(appUser,dao, MainMaintenanceConstants.MODE_ADD, errMsg);
				if (retval == MainMaintenanceConstants.ERROR_CODE) {
					logger.error("Could not create VADR , error="+errMsg);
				}
				logger.info("ADDED, dao="+ReflectionToStringBuilder.toString(dao));
			}

		} else  {
			if (isEmpty(dao)) {
				retval = vkundControllerUtil.saveVareAdressRecordNr1(appUser,dao, MainMaintenanceConstants.MODE_DELETE, errMsg);
				if (retval == MainMaintenanceConstants.ERROR_CODE) {
					logger.error("Could not delete VADR for , error="+errMsg);
				}
				logger.info("DELETED, dao="+ReflectionToStringBuilder.toString(dao));
			} else {
				retval = vkundControllerUtil.saveVareAdressRecordNr1(appUser,dao, MainMaintenanceConstants.MODE_UPDATE, errMsg);
				if (retval == MainMaintenanceConstants.ERROR_CODE) {
					logger.error("Could not update VADR for , error="+errMsg);
				}
				logger.info("UPDATED, dao="+ReflectionToStringBuilder.toString(dao));
			}
		} 
	}

	private boolean isEmpty(VadrDao dao) {
		if (   StringUtils.hasValue(dao.getVadrna()) 
			|| StringUtils.hasValue(dao.getVadrn1()) 
			|| StringUtils.hasValue(dao.getVadrn2()) 
			|| StringUtils.hasValue(dao.getVadrn3()) 
			|| StringUtils.hasValue(dao.getValand())) {
			logger.info("::isEmpty, false::");
			return false;
		} else {
			logger.info("::isEmpty, true::");
			return true;
		}
	}

	private void manageInvoiceEmail(SystemaWebUser appUser, JsonMaintMainCundfRecord record, StringBuffer errMsg, JsonMaintMainCundfRecord savedRecord) {
		logger.info("::manageInvoiceEmail::");
		int retval;
		JsonMaintMainCundcRecord cundInvoiceRecord = vkundControllerUtil.getInvoiceEmailRecord(appUser.getUser(),savedRecord.getFirma(), savedRecord.getKundnr() );
		if (StringUtils.hasValue(record.getEpostmott()) && cundInvoiceRecord == null) {
			savedRecord.setEpostmott(record.getEpostmott());
			retval = createCundcInvoicesCtype(appUser, savedRecord, errMsg);
			if (retval == MainMaintenanceConstants.ERROR_CODE) {
				logger.error("Could not create invoice ctype for "+record.getEpostmott());
			}
		}
	}
	
	private int createCundcInvoicesCtype(SystemaWebUser appUser, JsonMaintMainCundfRecord cundf, StringBuffer errMsg) {
		logger.info("::createCundcInvoicesCtype::");
		int retval = 0;
		JsonMaintMainCundcRecord cundc = new JsonMaintMainCundcRecord();
		cundc.setCcompn(cundf.getKundnr());
		cundc.setCfirma(cundf.getFirma());
		cundc.setCemail(cundf.getEpostmott());

		cundc.setCtype("*SINGELFAKTURA");
		cundc.setCconta(cundf.getEpostmott());
		retval =  createCundc(appUser, cundc, errMsg);
		
		cundc.setCtype("*SAMLEFAKTURA");
		cundc.setCconta(cundf.getEpostmott());
		retval =  createCundc(appUser, cundc, errMsg);
		
		return retval;
	}
	
	private int createCundc(SystemaWebUser appUser, JsonMaintMainCundcRecord record, StringBuffer errMsg) {
		logger.info("::createCundc::");
		int retval = 0;
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_CUNDC_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mode=" + MainMaintenanceConstants.MODE_ADD + "&lang=" +appUser.getUsrLang();
		String urlRequestParams = urlRequestParameterMapper.getUrlParameterValidString((record));
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;

		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		if (jsonPayload != null) {
			JsonMaintMainCundcContainer container = maintMainCundcService.doUpdate(jsonPayload);
			if (container != null) {
				if (container.getErrMsg() != null && !"".equals(container.getErrMsg())) {
						errMsg.append(container.getErrMsg());
						retval = MainMaintenanceConstants.ERROR_CODE;
				}
			}
		}

		return retval;
		
	}
	
	/* betbet can has empty or something like it, this one is ugly */
	private void theBetBetFix(SystemaWebUser appUser, JsonMaintMainCundfRecord recordToValidate, String action) {
		if (MainMaintenanceConstants.ACTION_CREATE.equals(action)) {
			if ("F".equals(recordToValidate.getKundetype())) { // Fakturakunde
				// validation downstreams
			} else if ("A".equals(recordToValidate.getKundetype())) { // Adressekunde
				if ("NOT_SET".equals(recordToValidate.getBetbet())) {
					recordToValidate.setBetbet("");
				}
			}
		}

		if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)) {
			if ("N".equals(vkundControllerUtil.isAdressCustomer(appUser, new Integer(recordToValidate.getKundnr())))) { // Fakturakunde
				// validation downstreams
			} else { // Adressekunde
				if ("NOT_SET".equals(recordToValidate.getBetbet())) {
					recordToValidate.setBetbet("");
				}
			}
		}

	}
	
	
	private void adjustRecordToValidate(JsonMaintMainCundfRecord recordToValidate, KundeSessionParams kundeSessionParams) {
		recordToValidate.setFirma(kundeSessionParams.getFirma());
		recordToValidate.setKundnr(kundeSessionParams.getKundnr());
	}
	
	private void specialRules(JsonMaintMainCundfRecord recordToValidate) {
		logger.info("::specialRules::");
		if ("J".equals(recordToValidate.getSyfr06())) {
			logger.info("Removing sfakt value");
			recordToValidate.setSfakt("");
		}
		
	}

	private void populateDropDowns(Map model, String user) {
		codeDropDownMgr.populateBetBetDropDown(this.urlCgiProxyService,  model, user);
	}	
	
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

	
	@Qualifier ("maintMainCundcService")
	private MaintMainCundcService maintMainCundcService;
	@Autowired
	@Required
	public void setMaintMainCundcService (MaintMainCundcService value){ this.maintMainCundcService = value; }
	public MaintMainCundcService getMaintMainCundcService(){ return this.maintMainCundcService; }
	
	
	
	
}

