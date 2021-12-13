package no.systema.z.main.maintenance.controller.kund;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import no.systema.jservices.common.dao.FirmDao;
import no.systema.jservices.common.dao.SviwDao;
import no.systema.jservices.common.dto.SviwDto;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.jservices.common.util.StringUtils;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.validator.MaintMainSviwValidator;

/**
 * Controller for Import(se) for Vareregister in Kunderegister
 * 
 * 
 * @author Fredrik Möller
 * @date May 22, 2017
 * 
 * 
 */

@Controller
public class MainMaintenanceCundfVareImportSeController {
	private static final Logger logger = LogManager.getLogger(MainMaintenanceCundfVareImportSeController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();

	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "mainmaintenancecundf_vareimp_se.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doVareImportSe(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_vareimp_se_edit");
		SystemaWebUser appUser = (SystemaWebUser) session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();

		if (appUser == null) {
			return this.loginView;
		} else  {
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);

			List<SviwDto> list = new ArrayList<SviwDto>();
			list = fetchList(appUser.getUser(), kundeSessionParams.getKundnr());

			model.put("kundnr", kundeSessionParams.getKundnr());
			model.put("firma", kundeSessionParams.getFirma());
			model.put(MainMaintenanceConstants.DOMAIN_LIST, list);

			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(kundeSessionParams.getKnavn()));			
			
		}

		return successView;
	}

	@RequestMapping(value="mainmaintenancecundf_vareimp_se_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_vareimp_se_edit(@ModelAttribute ("record") SviwDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_vareimp_se_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		if (appUser == null) {
			return this.loginView;
		} else {
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			adjustRecordToValidate(recordToValidate, kundeSessionParams);

			MaintMainSviwValidator validator = new MaintMainSviwValidator();
			if (MainMaintenanceConstants.ACTION_DELETE.equals(action)) {
				validator.validateDelete(recordToValidate, bindingResult);
			} else {
				validator.validate(recordToValidate, bindingResult);
			}
			if (bindingResult.hasErrors()) {
				logger.info("[ERROR Validation] Record does not validate)");
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
					dmlRetval = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_DELETE, errMsg);
				}
				// check for Update errors
				if (dmlRetval < 0) {
					logger.info("[ERROR DML] Record does not validate)");
					logger.info(" errMsg.toString()="+ errMsg.toString());
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					logger.info("recordToValidate, err="+ReflectionToStringBuilder.toString(recordToValidate));
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate); 
					
					if (updateId != null && !"".equals(updateId)) {
						// meaning bounced in an Update and not a Create new
						model.put("updateId", updateId);
					}
				}

			}

			List<SviwDto> list = new ArrayList<SviwDto>();
			list = fetchList(appUser.getUser(),  kundeSessionParams.getKundnr());

			model.put("kundnr", kundeSessionParams.getKundnr());
			model.put("firma", kundeSessionParams.getFirma());
			model.put(MainMaintenanceConstants.DOMAIN_LIST, list);

			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(kundeSessionParams.getKnavn()));			
			
			return successView;

		}

	}
	
	private int updateRecord(SystemaWebUser appUser, SviwDao record, String mode, StringBuffer errMsg) {
		Locale locale = VkundControllerUtil.getLocale(appUser.getUsrLang(), "svew");
		int retval = 0;
		JsonReader<JsonDtoContainer<SviwDao>> jsonReader = new JsonReader<JsonDtoContainer<SviwDao>>();
		jsonReader.set(new JsonDtoContainer<SviwDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVIW_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mode=" + mode + "&lang=" +locale.getCountry();
		String urlRequestParams = urlRequestParameterMapper.getUrlParameterValidString(record);
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		if (jsonPayload != null) {
			JsonDtoContainer<SviwDao> container = (JsonDtoContainer<SviwDao>) jsonReader.get(jsonPayload);
			if (container != null) {
				if (container.getErrMsg() != null && !"".equals(container.getErrMsg())) {
					errMsg.append(container.getErrMsg());
					retval = MainMaintenanceConstants.ERROR_CODE;
				}
			}			
		}

		return retval;
	}	
	
	
	private void adjustRecordToValidate(SviwDao recordToValidate, KundeSessionParams kundeSessionParams) {
		recordToValidate.setSviw_knnr(Integer.parseInt(kundeSessionParams.getKundnr()));
	}	
	
	
	private List<SviwDto> fetchList(String applicationUser, String kundnr) {
		JsonReader<JsonDtoContainer<SviwDao>> jsonReader = new JsonReader<JsonDtoContainer<SviwDao>>();
		jsonReader.set(new JsonDtoContainer<SviwDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVIW_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&sviw_knnr=" + kundnr);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload="+jsonPayload);
		List<SviwDto> adjustedlist = new ArrayList<SviwDto>();
		if (jsonPayload != null) {
			JsonDtoContainer<SviwDao> container = (JsonDtoContainer<SviwDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					SviwDto uiDto = null;
					if (container.getDtoList() != null) {
						for (SviwDao sviwDao : container.getDtoList()) {
							uiDto = new SviwDto();
							uiDto.setSviw_knnr(kundnr);
							uiDto.setSviw_brut(StringUtils.convertToSystemaUIFormat(sviwDao.getSviw_brut()));
							uiDto.setSviw_knso(sviwDao.getSviw_knso());
							uiDto.setSviw_neto(StringUtils.convertToSystemaUIFormat(sviwDao.getSviw_neto()));
							uiDto.setSviw_ulkd(sviwDao.getSviw_ulkd());
							uiDto.setSviw_vasl(sviwDao.getSviw_vasl());
							uiDto.setSviw_vata(sviwDao.getSviw_vata());
							adjustedlist.add(uiDto);
						}
					}
				}
		}
		return adjustedlist;
	}	
	

	// Wired - SERVICES
	@Qualifier("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService(UrlCgiProxyService value) {
		this.urlCgiProxyService = value;
	}
	public UrlCgiProxyService getUrlCgiProxyService() {
		return this.urlCgiProxyService;
	}


}
