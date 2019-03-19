package no.systema.z.main.maintenance.controller.kund;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.brreg.proxy.entities.Enhet;
import no.systema.jservices.common.brreg.proxy.entities.IEnhet;
import no.systema.jservices.common.dao.FirkuDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.jservices.common.util.StringUtils;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcRecord;
import no.systema.z.main.maintenance.service.MaintMainCundcService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * 
 * Placeholder for util methods for Kunderegister.
 * 
 * @author Fredrik Möller
 * @date Dec 28, 2016
 *
 */
@Service
public class VkundControllerUtil {
	private static final Logger logger = Logger.getLogger(VkundControllerUtil.class.getName());
	private UrlCgiProxyService cgiProxyService = null;

	@Bean 
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
	@Autowired
	MaintMainCundcService maintMainCundcService;
	
	
	/**
	 * Inject UrlCgiProxyService for http calls.
	 * 
	 * @param cgiProxyService
	 */
	public VkundControllerUtil(UrlCgiProxyService cgiProxyService) {
		this.cgiProxyService = cgiProxyService;
	}
	
	
	/**
	 * For UI. Trimming knavn to fit in tab
	 * 
	 * @param knavn
	 * @return a trimmed knavn if lenght > 10
	 */
	public static String getTrimmedKnav(String knavn) {
		StringBuilder knavn_display = new StringBuilder();
		int maxLenght = 10;
		if (knavn != null && knavn.length() > maxLenght) {
			knavn_display.append(knavn.substring(0, maxLenght));
			knavn_display.append("...");
			return knavn_display.toString();
		} else {
			return knavn;
		}
	}

	
	/**
	 * Fetch a {@link Enhet} as a Map with key/value, conversion done by Jackson
	 * 
	 * @param user
	 * @param orgnr
	 * @return a Map, incl. key/value from Enhet.
	 */
	public Map<String, Object> fetchEnhetMap(String user, String orgnr) {
		JsonReader<Map> jsonReader = new JsonReader<Map>();
		jsonReader.set(new HashMap<String, Object>());
		IEnhet enhet = null;
		List<IEnhet> enhetList = (List<IEnhet>) fetchSpecificEnhet(user, orgnr);
		if (enhetList == null) {
			return null;
		}
		if (enhetList.size() == 1) {
			enhet = enhetList.get(0);
			return jsonReader.convertValue(enhet, Map.class);
		} else {
			return null;
		}
	}
	
	/**
	 * Fetch a specific enhet wrapped in a Collection
	 * 
	 * @param user
	 * @param orgnr, aka syrg in CUNDF
	 * @return a Collection with one Enhet, if npt found return null
	 */
	public List<IEnhet> fetchSpecificEnhet(String user, String orgnr ) {
		JsonReader<JsonDtoContainer<IEnhet>> jsonReader = new JsonReader<JsonDtoContainer<IEnhet>>();
		jsonReader.set(new JsonDtoContainer<IEnhet>());
		String BASE_URL = MaintenanceMainUrlDataStore.BRREG_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + user);
		urlRequestParams.append("&orgnr=" + orgnr);

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = cgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
//		logger.info("jsonPayload="+jsonPayload);

		JsonDtoContainer<IEnhet> container =  (JsonDtoContainer<IEnhet> )jsonReader.get(jsonPayload);
		if (container != null) {
			return container.getDtoList();
		} else {
			return null;
		}
	}	
	
	/**
	 * Return a Locale based on the SystemaWebUser's defined usrlang
	 * 
	 * If caller is svew or sviw Locale is set to sv, SE.
	 * 
	 * @param usrLang
	 * @param caller typically naming from ui or dao
	 * @return a Locale
	 */
	public static Locale getLocale(String usrLang, String caller){
		Locale locale = null;

		if ("SE".equals(usrLang)) {
			locale = new Locale("sv", "SE");
		} else if ("NO".equals(usrLang)) {
			locale = new Locale("no", "NO");
		} else if ("DK".equals(usrLang)) {
			locale = new Locale("dk", "DK");
		} else {
			locale = Locale.getDefault();
		}		
		
		//Potential override of Locale
		if (isSweden(caller)) {
			locale = new Locale("sv", "SE");
		}		
		
		return locale;
	}
	
	/*
	 * caller starts with svew or sviw
	 * 
	 * @param caller
	 * @return true if caller starts with svew or sviw
	 */
	private static boolean isSweden(String caller) {
		return caller.startsWith("svew") || caller.startsWith("sviw");
	}	

	/**
	 * Get info if it is allowed to create invoice customer
	 * 
	 * @param appUser
	 * @return J if ok, N if not
	 */
	public String getInvoiceCustomerAllowed(SystemaWebUser appUser) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_INVOICE_VALID_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("?user=" + appUser.getUser());
		logger.info("Full url: " + BASE_URL +urlRequestParams.toString());

		ResponseEntity<String> response = restTemplate().exchange(BASE_URL + urlRequestParams.toString(),
				HttpMethod.GET, null, String.class);

		if (response != null) {
			return response.getBody();
		} else {
			return null;
		}
	
	}
	
	/**
	 * Get info if orgnr already is in use.
	 * 
	 * @param syrg orgnr
	 * @param appUser
	 * @return J if in use, N if not
	 */
	public String orgNrExist(String syrg, SystemaWebUser appUser) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_ORGNR_EXIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("?user=" + appUser.getUser());
		urlRequestParams.append("&syrg=" + syrg);
		logger.info("Full url: " + BASE_URL +urlRequestParams.toString());

		ResponseEntity<String> response = restTemplate().exchange(BASE_URL + urlRequestParams.toString(),
				HttpMethod.GET, null, String.class);

		if (response != null) {
			return response.getBody();
		} else {
			return null;
		}
	
	}

	/**
	 * Get info if orgnr is used multiple times
	 * 
	 * @param syrg orgnr
	 * @param appUser
	 * @return J if in use, N if not
	 */	
	public String orgNrMulti(String syrg, SystemaWebUser appUser) {
		if (!StringUtils.hasValue(syrg)) {
			return "N";
		}
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_ORGNR_MULTI_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("?user=" + appUser.getUser());
		urlRequestParams.append("&syrg=" + syrg);
		logger.info("Full url: " + BASE_URL +urlRequestParams.toString());

		ResponseEntity<String> response = restTemplate().exchange(BASE_URL + urlRequestParams.toString(),
				HttpMethod.GET, null, String.class);

		if (response != null) {
			return response.getBody();
		} else {
			logger.error("No response from "+BASE_URL +urlRequestParams.toString());
			return null;
		}
	}	
	
	
	
	/**
	 * Check if customer is address customer, if not meaning invoice customer.
	 * 
	 * @param appUser
	 * @param kundnr
	 * @return J if is address customer, N if not.
	 */
	public String isAdressCustomer(SystemaWebUser appUser, int kundnr) {
		FirkuDao firkuDao = getFirku(appUser);

		if(kundnr >= firkuDao.getFikufr() && kundnr <= firkuDao.getFikuti()) {
			return "J";
		} else {
			return "N";
		}

	}
	
	
	/**
	 * Check if postnr foreign and country=NO, a bit strange....
	 * 
	 * @param sypoge
	 * @param syland
	 * @param appUser
	 * @return J if strange combo, N if not.
	 */
	public String hasSypogeAndNO(String sypoge, String syland, SystemaWebUser appUser) {
		String has = "N";
		if (StringUtils.hasValue(sypoge) && StringUtils.hasValue(syland)) {
			if ("NO".equals(syland)) {
				has =  "J";
			} 
		}
		return has;
	}

	/**
	 * Check is customer has invoice email set.
	 * 
	 * @param kundnr
	 * @param appUser
	 * @return J if invoice email exist, N if not.
	 */
	public JsonMaintMainCundcRecord getInvoiceEmailRecord( String appUser, String firma, String kundnr ) {
		logger.info("::hasInvoiceEmail::");
		List<JsonMaintMainCundcRecord> list = getInvoiceEmailRows(appUser, firma, kundnr );
		
		Collection<JsonMaintMainCundcRecord> singelFaktura, samleFaktura;
		singelFaktura =
				list
		        .stream()
		        .filter(f -> f.getCtype().contains("*SINGELFAKTURA"))
		        .collect(Collectors.toSet());		
		samleFaktura =
				list
		        .stream()
		        .filter(f -> f.getCtype().contains("*SAMLEFAKTURA"))
		        .collect(Collectors.toSet());				
		
		if (!singelFaktura.isEmpty()) {
			return singelFaktura.iterator().next();
		} else if (!samleFaktura.isEmpty()) {
			return samleFaktura.iterator().next();
		} else {
			return null;
		}
		
		
	}	
	
	
	private FirkuDao getFirku(SystemaWebUser appUser) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_FIRKU_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("?user=" + appUser.getUser());
		logger.info("Full url: " + BASE_URL + urlRequestParams.toString());

		JsonReader<JsonDtoContainer<FirkuDao>> jsonReader = new JsonReader<JsonDtoContainer<FirkuDao>>();
		jsonReader.set(new JsonDtoContainer<FirkuDao>());

		ResponseEntity<String> jsonPayload = restTemplate().exchange(BASE_URL + urlRequestParams.toString(), HttpMethod.GET, null, String.class);
		logger.info("jsonPayload=" + jsonPayload.getBody());
		FirkuDao dao = null;

		if (jsonPayload != null) {
			JsonDtoContainer<FirkuDao> container = (JsonDtoContainer<FirkuDao>) jsonReader.get(jsonPayload.getBody());
			if (container != null) {
				for (FirkuDao firku : container.getDtoList()) {
					dao = firku;
				}
			}
		}

		return dao;

	}

	
	private List<JsonMaintMainCundcRecord> getInvoiceEmailRows(String appUser,  String firma, String kundnr) {
		logger.info("::getInvoiceEmailRows::");
		JsonReader<JsonDtoContainer<JsonMaintMainCundcRecord>> jsonReader = new JsonReader<JsonDtoContainer<JsonMaintMainCundcRecord>>();
		jsonReader.set(new JsonDtoContainer<JsonMaintMainCundcRecord>());

		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_CUNDC_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("?user=" + appUser);
		urlRequestParams.append("&cfirma=" + firma);
		urlRequestParams.append("&ccompn=" + kundnr);

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		ResponseEntity<String> jsonPayload = restTemplate().exchange(BASE_URL + urlRequestParams.toString(), HttpMethod.GET, null, String.class);
//		logger.info("jsonPayload=" + jsonPayload.getBody());
		
		List<JsonMaintMainCundcRecord> list = new ArrayList();
		
		if (jsonPayload != null) {
			JsonMaintMainCundcContainer container = maintMainCundcService.getList(jsonPayload.getBody());
			if (container != null) {
				list = (List) container.getList();
				// for (JsonMaintMainCundcRecord record : list) {
				// logger.info("RECORD=" + record);
				// }
			}
		}
		
		return list;

	}
	
	

}
