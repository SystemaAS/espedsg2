package no.systema.z.main.maintenance.controller.kund;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import no.systema.jservices.common.dao.FratxtDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.MessageNoteManager;
import no.systema.external.tvinn.sad.z.maintenance.util.TvinnCodeDropDownMgr;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainFratxtContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainFratxtRecord;
import no.systema.z.main.maintenance.service.MaintMainCundcService;
import no.systema.z.main.maintenance.service.MaintMainFratxtService;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;


/**
 * Fritekst in Kunde
 * 
 * 
 * @author Fredrik Möller
 * @date Feb 20, 2017
 * 
 * 	
 */

@Controller
public class MainMaintenanceCundfFritekstController {
	private static final Logger logger = LogManager.getLogger(MainMaintenanceCundfFritekstController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private TvinnCodeDropDownMgr codeDropDownMgr = new TvinnCodeDropDownMgr();
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();

	@RequestMapping(value = "mainmaintenancecundf_fritekst_edit.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doFritekst(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_fritekst_edit");
		SystemaWebUser appUser = (SystemaWebUser) session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		List<ChangelogDTO> changelogList = null;
		Collection<JsonMaintMainFratxtRecord> originalList = null;
		String action = request.getParameter("action");
		String searchKfkod = request.getParameter("searchKfkod");
		String fxtxt = request.getParameter("fxtxt");
		
		if (appUser == null) {
			return this.loginView;
		} else {
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			String firma = kundeSessionParams.getFirma();
			String kundnr = kundeSessionParams.getKundnr();

			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)) { // update/create
				StringBuilder errMsg = new StringBuilder();
				int dmlRetval = 0;
				originalList = getFratxt(appUser, kundnr, searchKfkod);
				List<FratxtDao> insertList = getInsertList(kundnr, searchKfkod, appUser.getUser(), fxtxt);
			    List<FratxtDao> deleteList = getDeleteList(insertList, originalList);
				for (FratxtDao fratxtDao : deleteList) {
					dmlRetval = updateRecord(appUser, fratxtDao, MainMaintenanceConstants.MODE_DELETE, errMsg); 
					if (dmlRetval < 0) {
						logger.info("[ERROR updating/deleting]  record:"+ReflectionToStringBuilder.toString(fratxtDao)+", Error:"+errMsg.toString());
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString()); 
					}
				}		    
				for (FratxtDao fratxtDao : insertList) {
					dmlRetval = updateRecord(appUser, fratxtDao, MainMaintenanceConstants.MODE_ADD, errMsg); 
					if (dmlRetval < 0) {
						logger.info("[ERROR updating/inserting]  record:"+ReflectionToStringBuilder.toString(fratxtDao)+", Error:"+errMsg.toString());
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString()); 
					}
				}

				originalList = getFratxt(appUser, kundnr, searchKfkod);
				fxtxt = fetchTekst(originalList);
				changelogList = fetchChangelog(originalList);

			} else { // Fetch
				if (searchKfkod == null) {
					searchKfkod = "";  // represents alle
				}
				originalList = getFratxt(appUser, kundnr, searchKfkod);
				fxtxt = fetchTekst(originalList);
				changelogList = fetchChangelog(originalList);
			}

			model.put("kundnr", kundnr);
			model.put("firma", firma);
			model.put("searchKfkod", searchKfkod);
			
			populateDropDowns(model,appUser.getUser());
			
			model.put("fxtxt", fxtxt);
			model.put("changelogList", changelogList);
			
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(kundeSessionParams.getKnavn()));
			
			return successView;
		}
	}
	
	private List<FratxtDao> getInsertList(String kundnr, String kfkod, String appUser, String fxtxt) {
		List<FratxtDao> daoList = new ArrayList<FratxtDao>();
		MessageNoteManager messageNoteMgr = new MessageNoteManager();
		DateTimeManager dateTimeManager = new DateTimeManager();
		String[] text = messageNoteMgr.getChunksOfMessageNote(fxtxt);
		FratxtDao dao = null;
		int lnr = 1;
		for (int i = 0; i < text.length; i++) {
			dao = new FratxtDao();
			dao.setFxknr(kundnr);
			dao.setFxlnr(String.valueOf(lnr++));
			dao.setDelsys(kfkod);
			dao.setFxtxt(text[i].toUpperCase().trim());
			dao.setFxusr(appUser);
			dao.setFxdt(dateTimeManager.getCurrentDate_ISO());

			if (text[i] != null && text[i].length() > 1) {
				daoList.add(dao);
			}
		}

		return daoList;
	}
	
	
	private List<FratxtDao> getDeleteList(List<FratxtDao> insertList, Collection<JsonMaintMainFratxtRecord> originalList) {
		List<FratxtDao> deleteList = new ArrayList<FratxtDao>();
		String orgLnr = null;
		FratxtDao dao = null;
		search: for (JsonMaintMainFratxtRecord orgRecord : originalList) {
			orgLnr = orgRecord.getFxlnr();
			for (FratxtDao insertDao : insertList) {
				if (insertDao.getFxlnr().equals(orgLnr)) {
					continue search;
				}
			}
			dao = new FratxtDao();
			dao.setFxknr(orgRecord.getFxknr());
			dao.setFxlnr(orgRecord.getFxlnr());
			dao.setDelsys(orgRecord.getDelsys());
		
			deleteList.add(dao);
		}

		return deleteList;
	}
	
	
	private int updateRecord(SystemaWebUser appUser, FratxtDao record, String mode, StringBuilder errMsg) {
		int retval = 0;
		JsonReader<JsonDtoContainer<FratxtDao>> jsonReader = new JsonReader<JsonDtoContainer<FratxtDao>>();
		jsonReader.set(new JsonDtoContainer<FratxtDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_FRATXT_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mode=" + mode + "&lang=" +appUser.getUsrLang();
		String urlRequestParams = urlRequestParameterMapper.getUrlParameterValidString((record));
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		logger.info("jsonPayload=" + jsonPayload);
		if (jsonPayload != null) {
			JsonDtoContainer<FratxtDao> container = (JsonDtoContainer<FratxtDao>) jsonReader.get(jsonPayload);
			if (container != null) {
				if (container.getErrMsg() != null && !"".equals(container.getErrMsg())) {
					errMsg.append(container.getErrMsg());
					retval = MainMaintenanceConstants.ERROR_CODE;
				}
			}			
		}

		return retval;
	}
	
	
	private String fetchTekst(Collection<JsonMaintMainFratxtRecord> orgFratxtDaoList) {
		StringBuilder concatText = new StringBuilder();
		Collection<JsonMaintMainFratxtRecord> fratxtList = orgFratxtDaoList;
		int newLines = 0;
		int lnr = 0;
		int prevLnr = 0;
		for (JsonMaintMainFratxtRecord record : fratxtList) {
			prevLnr = lnr;
			lnr = Integer.parseInt(record.getFxlnr());
			newLines = lnr - prevLnr;
			concatText.append(newLines(newLines)).append(record.getFxtxt());
		}
		return concatText.toString();
	}
	
	
	private String newLines(int newLines) {
		StringBuilder lines = new StringBuilder();
		for (int i = 0; i < newLines; i++) {
			lines.append('\n');
		}
		return lines.toString();
	}
	
	private List<ChangelogDTO> fetchChangelog(Collection<JsonMaintMainFratxtRecord> orgFratxtDaoList) {
		Collection<JsonMaintMainFratxtRecord> fratxtList = orgFratxtDaoList;
		List<ChangelogDTO> changeLogList = new ArrayList<ChangelogDTO>();
		ChangelogDTO changeLog = null;
		for (JsonMaintMainFratxtRecord record : fratxtList) {
			changeLog = new ChangelogDTO();
			if (record.getFxusr() != null && !"".equals(record.getFxusr().trim())) {
				changeLog.setFxusr(record.getFxusr());
				changeLog.setFxdt(record.getFxdt());
				changeLogList.add(changeLog);
				break; //TODO, room for improvement
			}
		}
		return changeLogList;
	}	
	
	private Collection<JsonMaintMainFratxtRecord> getFratxt(SystemaWebUser appUser, String kundnr, String kfkod) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_FRATXT_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&fxknr=" + kundnr);
		urlRequestParams.append("&delsys=" + kfkod);
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		Collection<JsonMaintMainFratxtRecord> list = new ArrayList<JsonMaintMainFratxtRecord>();
  		JsonMaintMainFratxtContainer container = maintMainFratxtService.getContainer(jsonPayload);
		if (container != null) {
			for (JsonMaintMainFratxtRecord syparfDto : container.getDtoList()) {
				list.add(syparfDto);
			}
		}
		return list;
	}		
	

	private void populateDropDowns(Map model, String applicationUser){
		codeDropDownMgr.popluateDelsystem(urlCgiProxyService, maintMainKofastService, model, applicationUser);
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

	@Qualifier ("maintMainKofastService")
	private MaintMainKofastService maintMainKofastService;
	@Autowired
	@Required
	public void setMaintMainKofastService (MaintMainKofastService value){ this.maintMainKofastService = value; }
	public MaintMainKofastService getMaintMainKofastService(){ return this.maintMainKofastService; }	
	
	@Qualifier ("maintMainFratxtService")
	private MaintMainFratxtService maintMainFratxtService;
	@Autowired
	@Required
	public void setMaintMainFratxtService (MaintMainFratxtService value){ this.maintMainFratxtService = value; }
	public MaintMainFratxtService getMaintMainFratxtService(){ return this.maintMainFratxtService; }		
	
		
}

