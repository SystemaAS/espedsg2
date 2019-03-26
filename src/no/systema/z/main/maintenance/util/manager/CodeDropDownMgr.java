package no.systema.z.main.maintenance.util.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintKodtvaContainer;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintKodtvaRecord;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadExportKodts9Container;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadExportKodts9Record;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadFellesKodtlbContainer;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadFellesKodtlbRecord;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodts1Container;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodts1Record;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodts4Container;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodts4Record;
import no.systema.external.tvinn.sad.z.maintenance.service.MaintKodtvaService;
import no.systema.external.tvinn.sad.z.maintenance.service.MaintSadExportKodts9Service;
import no.systema.external.tvinn.sad.z.maintenance.service.MaintSadFellesKodtlbService;
import no.systema.external.tvinn.sad.z.maintenance.service.MaintSadImportKodts1Service;
import no.systema.external.tvinn.sad.z.maintenance.service.MaintSadImportKodts4Service;
import no.systema.external.tvinn.sad.z.maintenance.url.store.TvinnSadMaintenanceUrlDataStore;
import no.systema.jservices.common.dao.FirmDao;
import no.systema.jservices.common.dao.KodafDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtot2Container;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtot2Record;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrkodfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrkodfRecord;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;
import no.systema.z.main.maintenance.service.MaintMainKodtot2Service;
import no.systema.z.main.maintenance.service.sad.MaintMainTrkodfService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;

/**
 * 
 * @author oscardelatorre
 * @date Sep 7, 2016
 * 
 */
public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger();

	/**
	 * 
	 * @param urlCgiProxyService
	 * @param maintKodtvaService
	 * @param model
	 * @param applicationUser
	 */
	public void populateCurrencyCodesHtmlDropDownsSad(UrlCgiProxyService urlCgiProxyService,
			MaintKodtvaService maintKodtvaService, Map model, String applicationUser) {

		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_DROPDOWN_SYFT02R_GET_CURRENCY_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&distinct=1");

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// extract
		List<JsonMaintKodtvaRecord> list = new ArrayList();
		if (jsonPayload != null) {
			// lists
			JsonMaintKodtvaContainer container = maintKodtvaService.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
			}
		}
		model.put(MainMaintenanceConstants.CODE_MGR_CURRENCY_LIST, list);
	}

	/**
	 * 
	 * @param urlCgiProxyService
	 * @param maintMainKodtaService
	 * @param model
	 * @param applicationUser
	 * @param sadType
	 *            (SAD Import or Export parameter: sialist/sealist; NCTS
	 *            Import/Eksport: nialist/nealist
	 */
	public void populateAvdListHtmlDropDownsSad(UrlCgiProxyService urlCgiProxyService,
			MaintMainKodtaService maintMainKodtaService, Map model, String applicationUser, String sadType) {

		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&" + sadType + "=1"); // sialist
																					// or
																					// sealist/nialist
																					// or
																					// nealist
																					// in
																					// order
																					// to
																					// return
																					// not-yet-used
																					// avd
																					// from
																					// general
																					// avdelningar

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// extract
		List<JsonMaintMainKodtaRecord> list = new ArrayList();
		if (jsonPayload != null) {
			// lists
			JsonMaintMainKodtaContainer container = maintMainKodtaService.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
			}
		}
		model.put(MainMaintenanceConstants.CODE_MGR_AVD_GENERAL_LIST, list);
	}

	/**
	 * 
	 * @param urlCgiProxyService
	 * @param maintMainKodtot2Service
	 * @param model
	 * @param applicationUser
	 */
	public void populateOppdragsTypeHtmlDropDowns(UrlCgiProxyService urlCgiProxyService,
		MaintMainKodtot2Service maintMainKodtot2Service, Map model, String applicationUser) {

		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_DROPDOWN_SYFA61R_GET_OPPTYPE_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// extract
		List<JsonMaintMainKodtot2Record> list = new ArrayList();
		if (jsonPayload != null) {
			// lists
			JsonMaintMainKodtot2Container container = maintMainKodtot2Service.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
			}
		}
		model.put(MainMaintenanceConstants.CODE_MGR_OPP_TYPE_LIST, list);
	}

	/**
	 * 
	 * @param urlCgiProxyService
	 * @param maintMainTrkodfService
	 * @param model
	 * @param applicationUser
	 * @param code
	 */
	public void populateGeneralCodesHtmlDropDownsNcts(UrlCgiProxyService urlCgiProxyService,
			MaintMainTrkodfService maintMainTrkodfService, Map model, String applicationUser, String code) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_DROPDOWN_GET_CODES_SAD_LIST_URL;

		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&tkunik=" + code);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL + " PARAMS:" + urlRequestParams));
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// extract
		List<JsonMaintMainTrkodfRecord> list = new ArrayList();
		if (jsonPayload != null) {
			// lists
			JsonMaintMainTrkodfContainer container = maintMainTrkodfService.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
			}
		}
		if (code.equals(MainMaintenanceConstants.CODE_NCTS_SIKKERHET_096_SPES_OMSTAND)) {
			model.put(MainMaintenanceConstants.CODE_MGR_CODE_NCTS_096_LIST, list);
		} else if (code.equals(MainMaintenanceConstants.CODE_NCTS_SIKKERHET_116_TRANSP_KOST_BETAL_MATE)) {
			model.put(MainMaintenanceConstants.CODE_MGR_CODE_NCTS_116_LIST, list);
		}

	}

	/**
	 * Other domain tables OUTSIDE bcore structure BUT within TVINN
	 * 
	 * @param urlCgiProxyService
	 * @param maintSadImportKodts4Service
	 * @param model
	 * @param applicationUser
	 * @param code
	 */
	public void populateGeneralCodesHtmlDropDownsSadKodts4(UrlCgiProxyService urlCgiProxyService,
			MaintSadImportKodts4Service maintSadImportKodts4Service, Map model, String applicationUser, String code) {
		// Default
		String BASE_URL = TvinnSadMaintenanceUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS4R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&tkunik=" + code);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL + " PARAMS:" + urlRequestParams));
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// extract
		List<JsonMaintSadImportKodts4Record> list = new ArrayList();
		if (jsonPayload != null) {
			// lists
			JsonMaintSadImportKodts4Container container = maintSadImportKodts4Service.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
			}
		}
		model.put(MainMaintenanceConstants.CODE_MGR_CODE_SAD_4_TRANSPORTMATER_LIST, list);
	}

	/**
	 * 
	 * @param urlCgiProxyService
	 * @param maintSadImportKodts4Service
	 * @param model
	 * @param applicationUser
	 * @param code
	 */
	public void populateGeneralCodesHtmlDropDownsSad012Incoterms(UrlCgiProxyService urlCgiProxyService,
			MaintSadFellesKodtlbService maintSadImportKodtlbService, Map model, String applicationUser) {
		String BASE_URL = TvinnSadMaintenanceUrlDataStore.TVINN_SAD_MAINTENANCE_FELLES_BASE_SAD012R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// extract
		List<JsonMaintSadFellesKodtlbRecord> list = new ArrayList();
		if (jsonPayload != null) {
			// lists
			JsonMaintSadFellesKodtlbContainer container = maintSadImportKodtlbService.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
			}
		}
		model.put(MainMaintenanceConstants.CODE_MGR_CODE_SAD_INCOTERMS_LIST, list);

	}

	/**
	 * 
	 * @param urlCgiProxyService
	 * @param maintSadImportKodts1Service
	 * @param model
	 * @param applicationUser
	 */
	public void populateGeneralCodesHtmlDropDownsSad002EkspedtyperImport(UrlCgiProxyService urlCgiProxyService,
			MaintSadImportKodts1Service maintSadImportKodts1Service, Map model, String applicationUser) {

		String BASE_URL = TvinnSadMaintenanceUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS1R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// extract
		List<JsonMaintSadImportKodts1Record> list = new ArrayList();
		if (jsonPayload != null) {
			// lists
			JsonMaintSadImportKodts1Container container = maintSadImportKodts1Service.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
			}
		}
		model.put(MainMaintenanceConstants.CODE_MGR_CODE_SAD_IMPORT_EKSPEDTYPER_LIST, list);
	}

	/**
	 * 
	 * @param urlCgiProxyService
	 * @param maintSadImportKodts9Service
	 * @param model
	 * @param applicationUser
	 */
	public void populateGeneralCodesHtmlDropDownsSad002EkspedtyperExport(UrlCgiProxyService urlCgiProxyService,
			MaintSadExportKodts9Service maintSadImportKodts9Service, Map model, String applicationUser) {

		String BASE_URL = TvinnSadMaintenanceUrlDataStore.TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTS9R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// extract
		List<JsonMaintSadExportKodts9Record> list = new ArrayList();
		if (jsonPayload != null) {
			// lists
			JsonMaintSadExportKodts9Container container = maintSadImportKodts9Service.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
			}
		}
		model.put(MainMaintenanceConstants.CODE_MGR_CODE_SAD_EXPORT_EKSPEDTYPER_LIST, list);
	}

	
	/**
	 * Populate deklarasjontyper for dropdown, primary used in TR001, Avd. NCTS - Export and Forhandsvarsling. 
	 * 
	 * model attribute; deklarasjonsTypeCodeList
	 * 
	 * @param urlCgiProxyService
	 * @param maintMainTrkodfService
	 * @param model
	 * @param applicationUser
	 */
	public void populateDeklarationsTyperHtmlDropDown(UrlCgiProxyService urlCgiProxyService, MaintMainTrkodfService maintMainTrkodfService, Map model, String applicationUser) {
		String BASE_URL = TvinnSadMaintenanceUrlDataStore.TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR001R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&tkunik=" + MainMaintenanceConstants.CODE_NCTS_DEKLARASJONS_TYPE);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// extract
		List<JsonMaintMainTrkodfRecord> list = new ArrayList();
		if (jsonPayload != null) {
			// lists
			JsonMaintMainTrkodfContainer container = maintMainTrkodfService.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
			}
		}

		model.put(MainMaintenanceConstants.CODE_MGR_CODE_NCTS_EXPORT_DEKLARASJONS_TYPE_LIST, list);

	}	
	
	/**
	 * Get Betalingsbetingelse from KODAF
	 * 
	 * @param urlCgiProxyService
	 * @param model, put "betbetList"
	 * @param applicationUser
	 */
	public void populateBetBetDropDown(UrlCgiProxyService urlCgiProxyService,  Map model, String applicationUser) {
		JsonReader<JsonDtoContainer<KodafDao>> jsonReader = new JsonReader<JsonDtoContainer<KodafDao>>();
		jsonReader.set(new JsonDtoContainer<KodafDao>());

		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_DROPDOWN_GET_KODAF_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
//		logger.info("jsonPayload="+jsonPayload);
		List<KodafDao> list = new ArrayList();
		if (jsonPayload != null) {
			JsonDtoContainer<KodafDao> container = (JsonDtoContainer<KodafDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					if (container.getDtoList().size() > 0) {
						list = container.getDtoList();
					} else {
						logger.error("BetBet list is empty");
					}
				} else {
					logger.info("container is null");
				}
				
		}
		
		model.put("betbetList", list);

	}		

}
