package no.systema.z.main.maintenance.controller.kund;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import no.systema.jservices.common.brreg.proxy.entities.Enhet;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * 
 * Placeholder for util methods for Kunderegister.
 * 
 * @author Fredrik Möller
 * @date Dec 28, 2016
 *
 */
public class VkundControllerUtil {
	private static final Logger logger = Logger.getLogger(VkundControllerUtil.class.getName());
	private UrlCgiProxyService cgiProxyService = null;

	
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
		Enhet enhet = null;
		List<Enhet> enhetList = (List<Enhet>) fetchSpecificEnhet(user, orgnr);
		if (enhetList == null) {
			return null;
		}
		if (enhetList.size() == 1) {
			enhet = (Enhet) enhetList.get(0);
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
	public Collection<Enhet> fetchSpecificEnhet(String user, String orgnr ) {
		JsonReader<JsonDtoContainer<Enhet>> jsonReader = new JsonReader<JsonDtoContainer<Enhet>>();
		jsonReader.set(new JsonDtoContainer<Enhet>());
		String BASE_URL = MaintenanceMainUrlDataStore.BRREG_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + user);
		urlRequestParams.append("&orgnr=" + orgnr);

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = cgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload="+jsonPayload);

		JsonDtoContainer<Enhet> container =  (JsonDtoContainer<Enhet> )jsonReader.get(jsonPayload);
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

}
