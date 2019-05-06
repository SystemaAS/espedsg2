package no.systema.z.main.maintenance.controller.ajax;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import no.systema.jservices.common.brreg.proxy.entities.Enhet;
import no.systema.jservices.common.brreg.proxy.entities.IEnhet;
import no.systema.jservices.common.brreg.proxy.entities.UnderEnhet;
import no.systema.jservices.common.dao.SadvareDao;
import no.systema.jservices.common.dao.SvewDao;
import no.systema.jservices.common.dao.SviwDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.z.main.maintenance.controller.kund.VkundControllerUtil;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainSyparfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainSyparfRecord;
import no.systema.z.main.maintenance.service.MaintMainCundcService;
import no.systema.z.main.maintenance.service.MaintMainSadvareService;
import no.systema.z.main.maintenance.service.MaintMainSyparfService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * Vedlikehold Firmanivå Kunderegister AJAX Controller
 * 
 * @author Fredrik Möller
 * @date Nov 7, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintMaintenanceVkundAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintMaintenanceVkundAjaxHandlerController.class.getName());

	@Autowired
	VkundControllerUtil vkundControllerUtil;
	
	@RequestMapping(value = "getSpecificRecord_sviw.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Collection<SviwDao> getRecordSviw(@RequestParam String applicationUser, @RequestParam String sviw_knnr, String sviw_knso) {
		final String METHOD = "[DEBUG] getSpecificRecord_sviw ";
		logger.info(METHOD + " applicationUser=" + applicationUser + ", sviw_knnr=" + sviw_knnr + ", sviw_knso=" + sviw_knso);

		return fetchSpecificSviw(applicationUser, sviw_knnr, sviw_knso);
	}		
	
	@RequestMapping(value = "getSpecificRecord_svew.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Collection<SvewDao> getRecordSvew(@RequestParam String applicationUser, @RequestParam String svew_knnr, String svew_knso) {
		final String METHOD = "[DEBUG] getSpecificRecord_svew ";
		logger.info(METHOD + " applicationUser=" + applicationUser + ", svew_knnr=" + svew_knnr + ", svew_knso=" + svew_knso);

		return fetchSpecificSvew(applicationUser, svew_knnr, svew_knso);
	}		
	
	@RequestMapping(value = "getSpecificRecord_sadvare.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Collection<SadvareDao> getRecordSadvare(@RequestParam String applicationUser, @RequestParam String levenr, String varenr) {
		final String METHOD = "[DEBUG] getSpecificRecord_sadvare ";
		logger.info(METHOD + " applicationUser=" + applicationUser + ", levenr=" + levenr + ", varenr=" + varenr);

		return fetchSpecificSadvare(applicationUser, levenr, varenr);
	}	
	
	@RequestMapping(value = "getSpecificRecord_syparf.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<JsonMaintMainSyparfRecord> getRecordSyparf(@RequestParam String applicationUser, @RequestParam String sykunr, String syrecn) {
		final String METHOD = "[DEBUG] getSpecificRecord_syparf ";
		logger.info(METHOD + " applicationUser=" + applicationUser + ", sykunr=" + sykunr + ", syrecn=" + syrecn);

		return (List<JsonMaintMainSyparfRecord>) fetchSpecificSyparf(applicationUser, sykunr, syrecn);
	}
	
	@RequestMapping(value = "getSpecificRecord_syparf2.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<JsonMaintMainSyparfRecord> getRecordSyparf2(@RequestParam String applicationUser, @RequestParam String syuser, String syrecn) {
		final String METHOD = "[DEBUG] getSpecificRecord_syparf2 ";
		logger.info(METHOD + " applicationUser=" + applicationUser + ", syuser=" + syuser + ", syrecn=" + syrecn);
		return (List<JsonMaintMainSyparfRecord>) fetchSpecificSyparf2(applicationUser, syuser, syrecn);
	}
	
	
	@RequestMapping(value = "getSpecificRecord_cundc.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<JsonMaintMainCundcRecord> getRecordCundc(@RequestParam String applicationUser, @RequestParam String cfirma, String ccompn, String cconta, String ctype) {
		final String METHOD = "[DEBUG] getSpecificRecord_cundc ";
		logger.info(METHOD + " applicationUser=" + applicationUser + ", cfirma=" + cfirma + ", ccompn=" + ccompn+ ", cconta="+cconta+", ctype="+ctype);

		return (List<JsonMaintMainCundcRecord>) fetchSpecificCundc(applicationUser, cfirma, ccompn, cconta, ctype);
	}

	
	@RequestMapping(value = "getSpecificRecord_enhet_brreg.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody IEnhet getRecordHovedEnhetBrreg(@RequestParam String applicationUser, @RequestParam String orgnr) {
		final String METHOD = "[DEBUG] getSpecificRecord_enhet_brreg ";
		logger.info(METHOD + " applicationUser=" + applicationUser + ", orgnr=" + orgnr );

		List<IEnhet> list =  vkundControllerUtil.fetchSpecificEnhet(applicationUser, orgnr);
		
		if (list != null && !list.isEmpty()) {
			IEnhet i_enhet =  list.get(0);
			if (i_enhet instanceof Enhet) {
				Enhet enhet = (Enhet ) i_enhet;
				enhet.setNavn(StringUtils.substring(enhet.getNavn(), 0, 30));

				return enhet;
				
			} else {
				UnderEnhet underEnhet = (UnderEnhet ) i_enhet;
				underEnhet.setNavn(StringUtils.substring(underEnhet.getNavn(), 0, 30));

				return underEnhet;
				
			}
			
			
		} else {
			return null;
		} 
		
	}

	@RequestMapping(value = "getDefaultEmmaXmlInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<JsonMaintMainCundcRecord> getDefaultEmmaXmlInfo(@RequestParam String applicationUser, @RequestParam String firma) {
		final String METHOD = "[DEBUG] getDefaultEmmaXmlInfo ";
		logger.debug(METHOD + " applicationUser=" + applicationUser + ", firma=" + firma );

		return (List<JsonMaintMainCundcRecord>) fetchDefaultEmmaXmlInfo(applicationUser, firma);
	}	
	
	private Collection<SviwDao> fetchSpecificSviw(String appUser, String sviw_knnr, String sviw_knso) {
		JsonReader<JsonDtoContainer<SviwDao>> jsonReader = new JsonReader<JsonDtoContainer<SviwDao>>();
		jsonReader.set(new JsonDtoContainer<SviwDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVIW_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser);
		urlRequestParams.append("&sviw_knnr=" + sviw_knnr);
		urlRequestParams.append("&sviw_knso=" + sviw_knso);

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);

		JsonDtoContainer<SviwDao> container = (JsonDtoContainer<SviwDao>) jsonReader.get(jsonPayload);
		if (container != null) {
			return container.getDtoList();
		} else {
			return null;
		}
	}		

	private Collection<SvewDao> fetchSpecificSvew(String appUser, String svew_knnr, String svew_knso) {
		JsonReader<JsonDtoContainer<SvewDao>> jsonReader = new JsonReader<JsonDtoContainer<SvewDao>>();
		jsonReader.set(new JsonDtoContainer<SvewDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVEW_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser);
		urlRequestParams.append("&svew_knnr=" + svew_knnr);
		urlRequestParams.append("&svew_knso=" + svew_knso);

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);

		JsonDtoContainer<SvewDao> container = (JsonDtoContainer<SvewDao>) jsonReader.get(jsonPayload);
		if (container != null) {
			return container.getDtoList();
		} else {
			return null;
		}
	}		

	
	
	private Collection<SadvareDao> fetchSpecificSadvare(String appUser, String levenr, String varenr) {
		JsonReader<JsonDtoContainer<SadvareDao>> jsonReader = new JsonReader<JsonDtoContainer<SadvareDao>>();
		jsonReader.set(new JsonDtoContainer<SadvareDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SADVARE_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser);
		urlRequestParams.append("&levenr=" + levenr);
		urlRequestParams.append("&varenr=" + varenr);

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);

		JsonDtoContainer<SadvareDao> container = (JsonDtoContainer<SadvareDao>) jsonReader.get(jsonPayload);
		if (container != null) {
			return container.getDtoList();
		} else {
			return null;
		}
	}		
	
	private List<JsonMaintMainSyparfRecord> fetchSpecificSyparf(String appUser, String sykunr, String syrecn) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYPARF_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser);
		urlRequestParams.append("&sykunr=" + sykunr);
		urlRequestParams.append("&syrecn=" + syrecn);
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		List<JsonMaintMainSyparfRecord> list = new ArrayList<JsonMaintMainSyparfRecord>();

 		JsonMaintMainSyparfContainer container = maintMainSyparfService.getContainer(jsonPayload);
		if (container != null) {
			for (JsonMaintMainSyparfRecord syparfDto : container.getDtoList()) {
				list.add(syparfDto);
			}
		}		
		
		return list;
	}
	
	private List<JsonMaintMainSyparfRecord> fetchSpecificSyparf2(String appUser, String sykunr, String syrecn) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYPARF2_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser);
		urlRequestParams.append("&syuser=" + sykunr);
		if(syrecn!=null){
			urlRequestParams.append("&syrecn=" + syrecn);
		}
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		List<JsonMaintMainSyparfRecord> list = new ArrayList<JsonMaintMainSyparfRecord>();

 		JsonMaintMainSyparfContainer container = maintMainSyparfService.getContainer(jsonPayload);
		if (container != null) {
			for (JsonMaintMainSyparfRecord syparfDto : container.getDtoList()) {
				list.add(syparfDto);
			}
		}		
		
		return list;
	}
	
	private Collection<JsonMaintMainCundcRecord> fetchSpecificCundc(String applicationUser, String cfirma, String ccompn, String cconta, String ctype) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_CUNDC_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&cfirma=" + cfirma);
		urlRequestParams.append("&ccompn=" + ccompn);
		urlRequestParams.append("&cconta=" + cconta);
		urlRequestParams.append("&ctype=" + ctype);
		

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// debugger
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() + " CGI-end timestamp");
		List<JsonMaintMainCundcRecord> list = new ArrayList();
		if (jsonPayload != null) {
			JsonMaintMainCundcContainer container = maintMainCundcService.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
/*				for (JsonMaintMainCundcRecord record : list) {
					logger.info("record=" + record);
				}
*/			}
		}

		return list;
	}
	
	private Collection<JsonMaintMainCundcRecord> fetchDefaultEmmaXmlInfo(String applicationUser, String cfirma) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_CUNDC_GET_LATEST_EMMA_XML_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&cfirma=" + cfirma);

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// debugger
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() + " CGI-end timestamp");
		List<JsonMaintMainCundcRecord> list = new ArrayList();
		if (jsonPayload != null) {
			JsonMaintMainCundcContainer container = maintMainCundcService.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
//				for (JsonMaintMainCundcRecord record : list) {
//					logger.info("record=" + record);
//				}
			}
		}

		return list;
	}
	

	// SERVICES
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

	@Qualifier("maintMainCundcService")
	private MaintMainCundcService maintMainCundcService;

	@Autowired
	@Required
	public void setMaintMainCundcService(MaintMainCundcService value) {
		this.maintMainCundcService = value;
	}
	
	public MaintMainCundcService getMaintMainCundcService() {
		return this.maintMainCundcService;
	}
	
	@Qualifier ("maintMainSyparfService")
	private MaintMainSyparfService maintMainSyparfService;
	@Autowired
	@Required
	public void setMaintMainSyparfService (MaintMainSyparfService value){ this.maintMainSyparfService = value; }
	public MaintMainSyparfService getMaintMainSyparfService(){ return this.maintMainSyparfService; }		
	
	@Qualifier("maintMainSadvareService")
	private MaintMainSadvareService maintMainSadvareService;
	@Autowired
	@Required
	public void setMaintMainSadvareService(MaintMainSadvareService value) {
		this.maintMainSadvareService = value;
	}
	public MaintMainSadvareService getMaintMainSadvareService() {
		return this.maintMainSadvareService;
	}	
	

}
