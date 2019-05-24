package no.systema.z.main.maintenance.controller.kund;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodtlikContainer;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadImportKodtlikRecord;
import no.systema.external.tvinn.sad.z.maintenance.service.MaintSadImportKodtlikService;
import no.systema.external.tvinn.sad.z.maintenance.url.store.TvinnSadMaintenanceUrlDataStore;
import no.systema.jservices.common.dao.FirmDao;
import no.systema.jservices.common.dao.KodtftDao;
import no.systema.jservices.common.dao.KodtlkDao;
import no.systema.jservices.common.dao.KodtotyDao;
import no.systema.jservices.common.dao.Kodts2Dao;
import no.systema.jservices.common.dao.Kodts5Dao;
import no.systema.jservices.common.dao.Kodts6Dao;
import no.systema.jservices.common.dao.Kodts7Dao;
import no.systema.jservices.common.dao.Kodts8Dao;
import no.systema.jservices.common.dao.KodtsaDao;
import no.systema.jservices.common.dao.KodtsbDao;
import no.systema.jservices.common.dao.KodtvalfDao;
import no.systema.jservices.common.dao.SvtproDao;
import no.systema.jservices.common.dao.Svtx03fDao;
import no.systema.jservices.common.dao.Svtx10fDao;
import no.systema.jservices.common.dao.TariDao;
import no.systema.jservices.common.dao.VadrDao;
import no.systema.jservices.common.dao.ValufDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.jservices.common.values.Svtx03fKodTyper;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.z.main.maintenance.controller.ChildWindowKode;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtlikContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtlikRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtotyContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtotyRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainValufContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainValufRecord;
import no.systema.z.main.maintenance.service.MaintMainCundfService;
import no.systema.z.main.maintenance.service.MaintMainKodtlikService;
import no.systema.z.main.maintenance.service.MaintMainKodtotyService;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.z.main.maintenance.service.MaintMainValufService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.util.MessageSourceHelper;
import no.systema.z.main.maintenance.util.manager.CodeDropDownMgr;
/**
 * Gateway for Kunderegister
 * 
 * Listing of Kunder
 * 
 * Edit and create new
 * 
 * 
 * Child windows for all tabs, incl. subtabs.
 * 
 * 
 * @author Fredrik Möller
 * @date Okt 26, 2016
 * 
 * 	
 */
@Controller
public class MainMaintenanceCundfVkundController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceCundfVkundController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private boolean KOFAST_NO_ID = true; 
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	
	@Autowired
	VkundControllerUtil vkundControllerUtil;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="mainmaintenancecundf_vkund.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_vkund(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_vkund");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String dbTable = request.getParameter("id");
		String kundnr = request.getParameter("searchKundnr");
		String knavn = request.getParameter("searchKnavn");
		String syrg = request.getParameter("searchSyrg");
		String syland = request.getParameter("searchSyland");
		String postnr = request.getParameter("searchPostnr");

		String firma = request.getParameter("firma");
	
		Map model = new HashMap();
		if (appUser == null) {
			return this.loginView;
		} else {

			Collection<JsonMaintMainCundfRecord> list = new ArrayList<JsonMaintMainCundfRecord>();

			list = fetchList(appUser.getUser(), kundnr, firma, knavn, syrg, syland, postnr);
			
			model.put("dbTable", dbTable);
	    	model.put("knavn", knavn);
	    	model.put("kundnr", kundnr);
	    	model.put("syrg", syrg);
	    	model.put("syland", syland);
	    	model.put("postnr", postnr);

	    	model.put("list", list);

			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);

			return successView;

		}
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="mainmaintenancecundf_vkund_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_vkund_edit(@ModelAttribute ("record") JsonMaintMainCundfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_kunde_edit"); //NOTE: not name correlated jsp, default to Kunde tab
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		String kundnr = recordToValidate.getKundnr();
		String knavn = recordToValidate.getKnavn();
		String firma = recordToValidate.getFirma();
		StringBuffer errMsg = new StringBuffer();
		int dmlRetval = 0;

		KundeSessionParams kundeSessionParams = new KundeSessionParams();

		if (appUser == null) {
			return this.loginView;
		} else {

			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)) { // Open for edit
				kundeSessionParams.setKundnr(kundnr);
				kundeSessionParams.setKnavn(knavn);
				kundeSessionParams.setFirma(firma);

				setInstalledModules(kundeSessionParams, appUser.getUser());
				
				JsonMaintMainCundfRecord record = fetchRecord(appUser.getUser(), kundnr, firma);
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
				
				successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(kundeSessionParams.getKnavn()));

				model.put("kundnr", kundnr);
				model.put("firma", firma);
				model.put("updateId", updateId);
				model.put("isAdressCustomer", vkundControllerUtil.isAdressCustomer(appUser, new Integer(kundnr)));
				model.put("orgNrMulti", vkundControllerUtil.orgNrMulti(record.getSyrg(), appUser));
				model.put("hasSypogeAndNO", vkundControllerUtil.hasSypogeAndNO(record.getSypoge(), record.getSyland() , appUser));
				model.put("hasVareAddresseNr1", vkundControllerUtil.hasVadrValues(record));
				
				
			} else if (MainMaintenanceConstants.ACTION_CREATE.equals(action)) { // Lage ny
				
				model.put("invoiceCustomerAllowed", vkundControllerUtil.getInvoiceCustomerAllowed(appUser));
				
			} else if (MainMaintenanceConstants.ACTION_DELETE.equals(action)) { // Delete from list
				dmlRetval = deleteRecord(appUser.getUser(), kundnr, firma, MainMaintenanceConstants.MODE_DELETE, errMsg);
				session.removeAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
				if (dmlRetval < 0) {
					logger.info("[ERROR DML] Record does not validate)");
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				} else {
					successView = new ModelAndView("redirect:mainmaintenancecundf_vkund.do");
				}
			}

			populateDropDowns(model, appUser.getUser());

			session.setAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS, kundeSessionParams);
			model.put("action", action);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);

			return successView;
		}

	}

	private void setInstalledModules(KundeSessionParams kundeSessionParams, String appUser) { //Used for views in Vareregister
		FirmDao firmDao = getFirmDao(appUser);
		if ("J".equals(firmDao.getFiurse())) {
			kundeSessionParams.setExportNo(true);
		}
		if ("J".equals(firmDao.getFiursi())) {
			kundeSessionParams.setImportNo(true);
		}

		//TODO when available in table FIRM
		kundeSessionParams.setImportSe(true);
		kundeSessionParams.setExportSe(true);
		kundeSessionParams.setFantomSpaceWidth(getFantomSpaceWidth(kundeSessionParams));
	
	}

	private FirmDao getFirmDao(String appUser) {
		JsonReader<JsonDtoContainer<FirmDao>> jsonReader = new JsonReader<JsonDtoContainer<FirmDao>>();
		jsonReader.set(new JsonDtoContainer<FirmDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFIRMONLY_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		FirmDao firmDao = null;
		if (jsonPayload != null) {
			JsonDtoContainer<FirmDao> container = (JsonDtoContainer<FirmDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					if (container.getDtoList().size() > 0) {
						firmDao = container.getDtoList().get(0);
					}
				}
		}
		return firmDao;
	}

	/*
	 * For dynamically adjust tabs in Vareregister
	 */
	private int getFantomSpaceWidth(KundeSessionParams kundeSessionParams) {
		int spaceTotal = 1090;
		if (kundeSessionParams.isExportNo()) {
			spaceTotal = spaceTotal - 100;
		}
		if (kundeSessionParams.isImportNo()) {
			spaceTotal = spaceTotal - 100;
		}
		if (kundeSessionParams.isExportDk()) {
			spaceTotal = spaceTotal - 100;
		}
		if (kundeSessionParams.isImportDk()) {
			spaceTotal = spaceTotal - 100;
		}
		if (kundeSessionParams.isExportSe()) {
			spaceTotal = spaceTotal - 100;
		}
		if (kundeSessionParams.isImportSe()) {
			spaceTotal = spaceTotal - 100;
		}

		return spaceTotal;
	}

	@SuppressWarnings("rawtypes")
	private void populateDropDowns(Map model, String user) {
		codeDropDownMgr.populateBetBetDropDown(this.urlCgiProxyService,  model, user);
	}
	

	/**
	 * This method serve as data populater for all child windows for Kunderegister - VKUND.
	 * 
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="mainmaintenance_vkund_edit_childwindow_codes.do",  method={RequestMethod.GET} )
	public ModelAndView getCodes(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenance_vkund_edit_childwindow_codes");
		ModelAndView successViewCustomer = new ModelAndView("mainmaintenance_childwindow_customer");
		ModelAndView successViewExportTulltaxa = new ModelAndView("tdsexport_edit_items_childwindow_tulltaxa");
		ModelAndView successViewImportTulltaxa = new ModelAndView("tdsimport_edit_items_childwindow_tulltaxa");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String caller = request.getParameter("caller");  //Field in jsp
		
		if (appUser == null) {
			return this.loginView;
		} else {

			List list = getCodeList(appUser, caller);
			model.put("codeList", list);
			model.put("caller", caller);

			setLabels(appUser, model, caller);
			
			if ("fmot".equals(caller)) { // Reuse of mainmaintenance_childwindow_customer.jsp
				model.put("ctype", caller);
				successViewCustomer.addObject(AppConstants.DOMAIN_MODEL, model);
				return successViewCustomer;
			} else if ("svew_vata".equals(caller)) { // Reuse of tdsexport_edit_items_childwindow_tulltaxa.do
				return successViewExportTulltaxa;
			} else if ("sviw_vata".equals(caller)) { // Reuse of tdsimport_edit_items_childwindow_tulltaxa.do
				return successViewImportTulltaxa;
			} else {
				model.put("caller", caller);
				successView.addObject(AppConstants.DOMAIN_MODEL, model);
				return successView;
			}
		}
	}
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setLabels(SystemaWebUser appUser, Map model, String caller) {
		MessageSourceHelper messageSourceHelper = new MessageSourceHelper();
		Locale locale  = VkundControllerUtil.getLocale(appUser.getUsrLang(), caller);
		
		String labelSearch = messageSourceHelper.getMessage("systema.main.maintenance.search", null, locale);
		String labelCode = messageSourceHelper.getMessage("systema.main.maintenance.main.gate.code", null, locale);
		String labelDesc = messageSourceHelper.getMessage("systema.main.maintenance.main.gate.description", null, locale);

		model.put("lang", locale.getLanguage());
		
		model.put("labelSearch", labelSearch);
		model.put("labelCode", labelCode);
		model.put("labelDesc", labelDesc);
		
	}


	private List<ChildWindowKode> getCodeList(SystemaWebUser appUser, String caller) {
		List<ChildWindowKode> list = null;

		if ("ctype".equals(caller)) { // Funksjon
			list = getFunksjonKoder(appUser, KOFAST_NO_ID);
		} else if (isAvkved(caller)) {  //Faste koder
			list = getFunksjonKoder(appUser, !KOFAST_NO_ID);
		} else if ("valkod".equals(caller)) { //Valutakod
			list = getValkoder(appUser);
		} else if ("syland".equals(caller) || "valand".equals(caller) ) { //Landkode
			list = getLandkoder(appUser);
		} else if ("syopdt".equals(caller)) { //Oppdragstype
			list = getOppdragsTyper(appUser);
		} else if ("sylikv".equals(caller)) { //Likviditetskode
			list = getLikviditetskoder(appUser);
		} else if ("sypaid".equals(caller)) { //Param id
			list = getParamKoder(appUser,  !KOFAST_NO_ID);
		}  else if ("w2vf".equals(caller)) { //Verdi fast
			list = getVerdiFastKoder(appUser);
		}  else if ("w2lk".equals(caller)) { //Land (import)
			list = getLandImportKoder(appUser);
		} else if ("w2vnti".equals(caller)) { //Tariffnr (aka varenr)
			list = getTariffKoder(appUser);
		} else if ("w2tn".equals(caller)) { //Tollned
			list = getTollnedKoder(appUser);
		} else if ("w2pre".equals(caller)) { //Preferense
			list = getPreferenseKoder(appUser);
		} else if ("enhet".equals(caller)) { //Enhet
			list = getEnhetKoder(appUser);
		} else if ("avgkode".equals(caller)) { //Avg.kode
			list = getAvgkodeKoder(appUser);
		} else if ("kommref".equals(caller)) { //Komm.ref
			list = getKommrefKoder(appUser);
		} else if ("w2val".equals(caller)) { //Valutakod
			list = getValutaKoder(appUser);
		} else if ("fmot".equals(caller)) { //Fakturamottager
			list = getKunder(appUser);
		} else if ("syfr03".equals(caller)) { //Fritekstkode
			list = getFritekstKoder(appUser);
		} else if ("svew_ulkd".equals(caller) || "sviw_ulkd".equals(caller)) { //Ursprungsland
			list = getLandKoderSvKoderFromSvtx03f(appUser);
		} else if ("svew_vata".equals(caller)) { //Varukod
			logger.info("Calling child_window for svew_vata, re-use of tdsexport_edit_items_childwindow_tulltaxa.");
		} else if ("sviw_vata".equals(caller)) { //Varukod
			logger.info("Calling child_window for sviw_vata, re-use of tdsimport_edit_items_childwindow_tulltaxa.");
		} else if ("svew_eup1".equals(caller) || "sviw_eup1".equals(caller)) { //Förfarande 37:1
			list = getEup1KoderFromSvtpro(appUser);
		} else if ("svew_eup2".equals(caller) || "sviw_eup2".equals(caller)) { //Förfarande 37:2
			list = getEup2KoderFromSvtx03f(appUser);
		} else if ("svew_kosl".equals(caller)  || "sviw_kosl".equals(caller)) { //Kollislag
			list = getKollislagKoderFromSvtx03f(appUser);
		} else if ("svew_kos2".equals(caller) || "sviw_kos2".equals(caller)) { //Kollislag
			list = getKollislagKoderFromSvtx03f(appUser);
		} else if ("svew_kos3".equals(caller) || "sviw_kos3".equals(caller)) { //Kollislag
			list = getKollislagKoderFromSvtx03f(appUser);
		} else if ("svew_kos4".equals(caller) || "sviw_kos4".equals(caller)) { //Kollislag
			list = getKollislagKoderFromSvtx03f(appUser);
		} else if ("svew_kos5".equals(caller) || "sviw_kos5".equals(caller)) { //Kollislag
			list = getKollislagKoderFromSvtx03f(appUser);
		} else if ("svew_bit1".equals(caller) || "sviw_bit1".equals(caller)) { //Bilagda handlingar
			list = getBilagdaHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_bit2".equals(caller) || "sviw_bit2".equals(caller)) { //Bilagda handlingar
			list = getBilagdaHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_bit3".equals(caller) || "sviw_bit3".equals(caller)) { //Bilagda handlingar
			list = getBilagdaHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_bit4".equals(caller) || "sviw_bit4".equals(caller)) { //Bilagda handlingar
			list = getBilagdaHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_bit5".equals(caller) || "sviw_bit5".equals(caller)) { //Bilagda handlingar
			list = getBilagdaHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_bit6".equals(caller) || "sviw_bit6".equals(caller)) { //Bilagda handlingar
			list = getBilagdaHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_bit7".equals(caller) || "sviw_bit7".equals(caller)) { //Bilagda handlingar
			list = getBilagdaHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_bit8".equals(caller) || "sviw_bit8".equals(caller)) { //Bilagda handlingar
			list = getBilagdaHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_bit9".equals(caller) || "sviw_bit9".equals(caller)) { //Bilagda handlingar
			list = getBilagdaHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_tit1".equals(caller) || "sviw_tit1".equals(caller)) { //Tidigare handlingar
			list = getTidigareHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_tit2".equals(caller) || "sviw_tit2".equals(caller)) { //Tidigare handlingar
			list = getTidigareHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_tit3".equals(caller) || "sviw_tit3".equals(caller)) { //Tidigare handlingar
			list = getTidigareHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_tit4".equals(caller) || "sviw_tit4".equals(caller)) { //Tidigare handlingar
			list = getTidigareHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_tit5".equals(caller) || "sviw_tit5".equals(caller)) { //Tidigare handlingar
			list = getTidigareHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_tit6".equals(caller) || "sviw_tit6".equals(caller)) { //Tidigare handlingar
			list = getTidigareHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_tit7".equals(caller) || "sviw_tit7".equals(caller)) { //Tidigare handlingar
			list = getTidigareHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_tit8".equals(caller) || "sviw_tit8".equals(caller)) { //Tidigare handlingar
			list = getTidigareHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_tit9".equals(caller) || "sviw_tit9".equals(caller)) { //Tidigare handlingar
			list = getTidigareHandlingarKoderFromSvtx03f(appUser);
		} else if ("svew_lagl".equals(caller) || "sviw_lagl".equals(caller)) { //Land
			list = getLandKoderSvKoderFromSvtx03f(appUser);
		} else if ("sviw_fokd".equals(caller) ) { //Förmånskoder
			list = getFormansKoderFromSvtx03f(appUser);
		}
		
		else {
			throw new IllegalArgumentException(caller + " is not supported.");
		}

		return list;
	}
	
	private boolean isAvkved(String caller) {
		return caller.startsWith("avkved");
	}

	private List<ChildWindowKode> getFormansKoderFromSvtx03f(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<Svtx03fDao>> jsonReader = new JsonReader<JsonDtoContainer<Svtx03fDao>>();
		jsonReader.set(new JsonDtoContainer<Svtx03fDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVTX03F_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&02=" + Svtx03fKodTyper.FOR.toString());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<Svtx03fDao> container = (JsonDtoContainer<Svtx03fDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Svtx03fDao kodtftDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtftDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;

	}	
		
	private List<ChildWindowKode> getTidigareHandlingarKoderFromSvtx03f(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<Svtx03fDao>> jsonReader = new JsonReader<JsonDtoContainer<Svtx03fDao>>();
		jsonReader.set(new JsonDtoContainer<Svtx03fDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVTX03F_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&02=" + Svtx03fKodTyper.THO.toString());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<Svtx03fDao> container = (JsonDtoContainer<Svtx03fDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Svtx03fDao kodtftDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtftDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;

	}	
	
	
	private List<ChildWindowKode> getBilagdaHandlingarKoderFromSvtx03f(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<Svtx03fDao>> jsonReader = new JsonReader<JsonDtoContainer<Svtx03fDao>>();
		jsonReader.set(new JsonDtoContainer<Svtx03fDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVTX03F_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&02=" + Svtx03fKodTyper.MCF.toString());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<Svtx03fDao> container = (JsonDtoContainer<Svtx03fDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Svtx03fDao kodtftDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtftDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;

	}	

	
	
	
	private List<ChildWindowKode> getKollislagKoderFromSvtx03f(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<Svtx03fDao>> jsonReader = new JsonReader<JsonDtoContainer<Svtx03fDao>>();
		jsonReader.set(new JsonDtoContainer<Svtx03fDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVTX03F_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&02=" + Svtx03fKodTyper.KLI.toString());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<Svtx03fDao> container = (JsonDtoContainer<Svtx03fDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Svtx03fDao kodtftDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtftDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;

	}	
	
	private List<ChildWindowKode> getEup1KoderFromSvtpro(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<SvtproDao>> jsonReader = new JsonReader<JsonDtoContainer<SvtproDao>>();
		jsonReader.set(new JsonDtoContainer<SvtproDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVTPRO_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<SvtproDao> container = (JsonDtoContainer<SvtproDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (SvtproDao kodtftDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtftDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;

	}	
	
	private ChildWindowKode getChildWindowKode(SvtproDao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(dao.getSvpr_pr());
		kode.setDescription(dao.getSvpr_tx1());
		return kode;
	}
	
	private List<ChildWindowKode> getEup2KoderFromSvtx03f(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<Svtx03fDao>> jsonReader = new JsonReader<JsonDtoContainer<Svtx03fDao>>();
		jsonReader.set(new JsonDtoContainer<Svtx03fDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVTX03F_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&02=" + Svtx03fKodTyper.FFK.toString());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<Svtx03fDao> container = (JsonDtoContainer<Svtx03fDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Svtx03fDao kodtftDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtftDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;

	}

	/*Draft impl, for lists to Varukod, export/import*/
	private List<ChildWindowKode> getTaricnrKoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<Svtx10fDao>> jsonReader = new JsonReader<JsonDtoContainer<Svtx10fDao>>();
		jsonReader.set(new JsonDtoContainer<Svtx10fDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVTX10F_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.debug("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<Svtx10fDao> container = (JsonDtoContainer<Svtx10fDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Svtx10fDao kodtftDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtftDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;
	}

	private ChildWindowKode getChildWindowKode(Svtx10fDao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(dao.getSvtx10_01());
		kode.setDescription(dao.getSvtx10_03());
		return kode;
	}		
	

	private List<ChildWindowKode> getLandKoderSvKoderFromSvtx03f(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<Svtx03fDao>> jsonReader = new JsonReader<JsonDtoContainer<Svtx03fDao>>();
		jsonReader.set(new JsonDtoContainer<Svtx03fDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SVTX03F_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&02=" + Svtx03fKodTyper.GCY.toString());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.debug("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<Svtx03fDao> container = (JsonDtoContainer<Svtx03fDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Svtx03fDao kodtftDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtftDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;

	}

	private ChildWindowKode getChildWindowKode(Svtx03fDao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(dao.getSvtx03_03());
		kode.setDescription(dao.getSvtx03_04());
		return kode;
	}	
	
	private List<ChildWindowKode>  getFritekstKoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<KodtftDao>> jsonReader = new JsonReader<JsonDtoContainer<KodtftDao>>();
		jsonReader.set(new JsonDtoContainer<KodtftDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTFT_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<KodtftDao> container = (JsonDtoContainer<KodtftDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (KodtftDao kodtftDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtftDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;
	}	
	
	private ChildWindowKode getChildWindowKode(KodtftDao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(dao.getKfttyp());
		kode.setDescription(dao.getKftftx());
		return kode;
	}		
	
	private List<ChildWindowKode> getKunder(SystemaWebUser appUser) {
		Collection<JsonMaintMainCundfRecord> list = fetchList(appUser.getUser(), null, null);
		List<ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		for (JsonMaintMainCundfRecord record : list) {
			kode = getChildWindowKode(record);
			kodeList.add(kode);
		}

		return kodeList;
	}
	
	private ChildWindowKode getChildWindowKode(JsonMaintMainCundfRecord record) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(record.getKundnr());
		kode.setDescription(record.getKnavn());
		return kode;
	}	
	
	
	private List<ChildWindowKode>  getValutaKoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<KodtvalfDao>> jsonReader = new JsonReader<JsonDtoContainer<KodtvalfDao>>();
		jsonReader.set(new JsonDtoContainer<KodtvalfDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTVALF_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<KodtvalfDao> container = (JsonDtoContainer<KodtvalfDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (KodtvalfDao kodtvalDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtvalDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;
	}	
	
	private ChildWindowKode getChildWindowKode(KodtvalfDao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(dao.getKvakod());
		kode.setDescription(dao.getKvaxxx());
		return kode;
	}		
	
	private List<ChildWindowKode>  getKommrefKoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<KodtsbDao>> jsonReader = new JsonReader<JsonDtoContainer<KodtsbDao>>();
		jsonReader.set(new JsonDtoContainer<KodtsbDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTSB_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<KodtsbDao> container = (JsonDtoContainer<KodtsbDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (KodtsbDao kodtsaDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtsaDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;
	}	
	
	private ChildWindowKode getChildWindowKode(KodtsbDao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(dao.getKsbkd());
		kode.setDescription(dao.getKsbft());

		return kode;
	}	
	
	private List<ChildWindowKode>  getAvgkodeKoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<Kodts8Dao>> jsonReader = new JsonReader<JsonDtoContainer<Kodts8Dao>>();
		jsonReader.set(new JsonDtoContainer<Kodts8Dao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTS8_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<Kodts8Dao> container = (JsonDtoContainer<Kodts8Dao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Kodts8Dao kodtsaDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtsaDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;
	}
	
	private ChildWindowKode getChildWindowKode(Kodts8Dao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(dao.getKs8avg() +"-"+dao.getKs8skv());
		kode.setDescription(dao.getKs8ftx());

		return kode;
	}		
	
	private List<ChildWindowKode>  getEnhetKoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<KodtsaDao>> jsonReader = new JsonReader<JsonDtoContainer<KodtsaDao>>();
		jsonReader.set(new JsonDtoContainer<KodtsaDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTSA_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<KodtsaDao> container = (JsonDtoContainer<KodtsaDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (KodtsaDao kodtsaDao :  container.getDtoList()) {
						kode = getChildWindowKode(kodtsaDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;
	}			
	
	private ChildWindowKode getChildWindowKode(KodtsaDao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(dao.getKsakd());
		kode.setDescription(dao.getKsaft());

		return kode;
	}	
	
	private List<ChildWindowKode>  getPreferenseKoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<Kodts6Dao>> jsonReader = new JsonReader<JsonDtoContainer<Kodts6Dao>>();
		jsonReader.set(new JsonDtoContainer<Kodts6Dao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTS6_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<Kodts6Dao> container = (JsonDtoContainer<Kodts6Dao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Kodts6Dao kodts6Dao :  container.getDtoList()) {
						kode = getChildWindowKode(kodts6Dao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;
	}		
	
	private ChildWindowKode getChildWindowKode(Kodts6Dao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(dao.getKs6pre());
		kode.setDescription(dao.getKs6ftx());

		return kode;
	}		
	
	private List<ChildWindowKode>  getTollnedKoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<Kodts5Dao>> jsonReader = new JsonReader<JsonDtoContainer<Kodts5Dao>>();
		jsonReader.set(new JsonDtoContainer<Kodts5Dao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTS5_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<Kodts5Dao> container = (JsonDtoContainer<Kodts5Dao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Kodts5Dao kodts5Dao :  container.getDtoList()) {
						kode = getChildWindowKode(kodts5Dao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;
	}		
	
	private ChildWindowKode getChildWindowKode(Kodts5Dao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(dao.getKs5tln());
		kode.setDescription(dao.getKs5ftx());

		return kode;
	}	
		
	
	private List<ChildWindowKode>  getTariffKoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<TariDao>> jsonReader = new JsonReader<JsonDtoContainer<TariDao>>();
		jsonReader.set(new JsonDtoContainer<TariDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_TARI_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<TariDao> container = (JsonDtoContainer<TariDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (TariDao tariDao :  container.getDtoList()) {
						kode = getChildWindowKode(tariDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;
	}	
	
	private ChildWindowKode getChildWindowKode(TariDao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(String.valueOf(dao.getTatanr()));
		kode.setDescription(dao.getTatxt());

		return kode;
	}	
	
	
	private List<ChildWindowKode>  getLandImportKoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<Kodts2Dao>> jsonReader = new JsonReader<JsonDtoContainer<Kodts2Dao>>();
		jsonReader.set(new JsonDtoContainer<Kodts2Dao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTS2_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<Kodts2Dao> container = (JsonDtoContainer<Kodts2Dao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Kodts2Dao kodts2Dao :  container.getDtoList()) {
						kode = getChildWindowKode(kodts2Dao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;
	}		
	
	private ChildWindowKode getChildWindowKode(Kodts2Dao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(dao.getKs2lk());
		kode.setDescription(dao.getKs2ftx());

		return kode;
	}		
	
	
	
	private List<ChildWindowKode>  getVerdiFastKoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<Kodts7Dao>> jsonReader = new JsonReader<JsonDtoContainer<Kodts7Dao>>();
		jsonReader.set(new JsonDtoContainer<Kodts7Dao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTS7_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<Kodts7Dao> container = (JsonDtoContainer<Kodts7Dao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Kodts7Dao kodts7Dao :  container.getDtoList()) {
						kode = getChildWindowKode(kodts7Dao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;
	}		
	
	private ChildWindowKode getChildWindowKode(Kodts7Dao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(String.valueOf(dao.getKs7vf()));
		kode.setDescription(dao.getKs7ftx());

		return kode;
	}		
	
	private List<ChildWindowKode> getParamKoder(SystemaWebUser appUser, boolean noId) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KOFAST_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&kftyp=" + FasteKoder.SYPAR.toString());
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonMaintMainChildWindowKofastContainer container = null;
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		try {
			if (jsonPayload != null) {
				container = maintMainKofastService.getContainer(jsonPayload);
				if (container != null) {
					for (JsonMaintMainChildWindowKofastRecord record : container.getDtoList()) {
						kode = getChildWindowKode(record, noId);
						kodeList.add(kode);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kodeList;
	}
	
	private List<ChildWindowKode> getFunksjonKoder(SystemaWebUser appUser, boolean noId) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KOFAST_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&kftyp=" + FasteKoder.FUNKSJON.toString());
		logger.info(BASE_URL);
		logger.info(urlRequestParams);

		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonMaintMainChildWindowKofastContainer container = null;
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		try {
			if (jsonPayload != null) {
				container = maintMainKofastService.getContainer(jsonPayload);
				if (container != null) {
					for (JsonMaintMainChildWindowKofastRecord record : container.getDtoList()) {
						kode = getChildWindowKode(record, noId);
						kodeList.add(kode);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kodeList;
	}

	
	private List<ChildWindowKode> getLandkoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<KodtlkDao>> jsonReader = new JsonReader<JsonDtoContainer<KodtlkDao>>();
		jsonReader.set(new JsonDtoContainer<KodtlkDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTLK_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		JsonMaintMainKodtlikContainer container = maintMainKodtlikService.getContainer(jsonPayload);
		if (container != null) {
			for (JsonMaintMainKodtlikRecord record : container.getDtoList()) {
				kode = getChildWindowKode(record);
				kodeList.add(kode);
			}
		}		
		
		return kodeList;
	}	

	private ChildWindowKode getChildWindowKode(JsonMaintMainKodtlikRecord record) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(record.getKlklk());
		kode.setDescription(record.getKlknvn());

		return kode;
	}	
		
	private List<ChildWindowKode> getOppdragsTyper(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<KodtotyDao>> jsonReader = new JsonReader<JsonDtoContainer<KodtotyDao>>();
		jsonReader.set(new JsonDtoContainer<KodtotyDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTOTY_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		JsonMaintMainKodtotyContainer container = maintMainKodtotyService.getContainer(jsonPayload);
		if (container != null) {
			for (JsonMaintMainKodtotyRecord record : container.getDtoList()) {
				kode = getChildWindowKode(record);
				kodeList.add(kode);
			}
		}
		
		return kodeList;
	}	

	private ChildWindowKode getChildWindowKode(JsonMaintMainKodtotyRecord record) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(record.getKo1kod());
		kode.setDescription(record.getKo1ntx());

		return kode;
	}	
	
	private List<ChildWindowKode> getLikviditetskoder(SystemaWebUser appUser){
		String BASE_URL = TvinnSadMaintenanceUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT19R_GET_LIST_URL;
		String urlRequestParams = "user=" + appUser.getUser();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		List<ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonMaintSadImportKodtlikContainer container = this.maintSadImportKodtlikService.getList(jsonPayload);
			if (container != null) {
				for (JsonMaintSadImportKodtlikRecord record : container.getList()) {
					kode = getChildWindowKode(record);
					kodeList.add(kode);
				}
			}
		}
		return kodeList;

	}	

	private ChildWindowKode getChildWindowKode(JsonMaintSadImportKodtlikRecord record) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(record.getKlikod());
		kode.setDescription(record.getKlinav());

		return kode;
	}		
	
	
	private List<ChildWindowKode> getValkoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<ValufDao>> jsonReader = new JsonReader<JsonDtoContainer<ValufDao>>();
		jsonReader.set(new JsonDtoContainer<ValufDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_VALUF_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		JsonMaintMainValufContainer container = maintMainValufService.getContainer(jsonPayload);
		if (container != null) {
			for (JsonMaintMainValufRecord record : container.getDtoList()) {
				kode = getChildWindowKode(record);
				kodeList.add(kode);
			}
		}		
		
		return kodeList;
	}	

	private ChildWindowKode getChildWindowKode(JsonMaintMainValufRecord record) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(record.getValkod());
		kode.setDescription(record.getValtek());

		return kode;
	}	
	
	
	private ChildWindowKode getChildWindowKode(JsonMaintMainChildWindowKofastRecord record, boolean noId) {
		ChildWindowKode kode = new ChildWindowKode();
		if (noId) {
			kode.setCode("*" + record.getKftxt());
		} else {
			kode.setCode(record.getKfkod());
		}
		kode.setDescription(record.getKftxt());

		return kode;
	}

	private JsonMaintMainCundfRecord fetchRecord(String applicationUser, String kundnr, String firma) {
		logger.info("::fetchRecord::kundnr="+kundnr);
//		VkundControllerUtil util = new VkundControllerUtil(urlCgiProxyService);
		JsonMaintMainCundfRecord record = new JsonMaintMainCundfRecord(), fmotRecord = new JsonMaintMainCundfRecord();
		Collection<JsonMaintMainCundfRecord> recordList = fetchList(applicationUser, kundnr, firma);
		if (recordList.size() > 1) {
			// abort
			logger.error("Incorrect data when searching for specific CUNDF object, on params kundnr=" + kundnr
					+ ", firma=" + firma);
			return null;
		}
		for (Iterator<JsonMaintMainCundfRecord> iterator = recordList.iterator(); iterator.hasNext();) {
			record = (JsonMaintMainCundfRecord) iterator.next();
			if (no.systema.jservices.common.util.StringUtils.hasValueIgnoreZero(record.getFmot()) && !kundnr.equals(record.getFmot())) { // to avoid circular-ref
				fmotRecord= fetchRecord(applicationUser,record.getFmot(),firma);
				record.setFmotname(fmotRecord.getKnavn());
			}
			record.setElma(vkundControllerUtil.existInElma(record.getSyrg()));	
			JsonMaintMainCundcRecord cundc = vkundControllerUtil.getInvoiceEmailRecord(applicationUser,firma, kundnr );
			if (cundc != null) {
				record.setEpost("J");
				if (!no.systema.jservices.common.util.StringUtils.hasValue(cundc.getCemail())) {
					logger.error("Invalid setup of SINGELFAKTURA and SAMLEFAKTURA! email is empty!");
					record.setEpostmott("varning: epost saknes på SINGELFAKTURA/SAMLEFAKTURA.");
				} else {
					record.setEpostmott(cundc.getCemail());
				}
			} else {
				//not set
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

		return record;

	}

	private Collection<JsonMaintMainCundfRecord> fetchList(String applicationUser, String kundnr, String firma) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		if (kundnr != null && firma != null) {
			urlRequestParams.append("&kundnr=" + kundnr);
			urlRequestParams.append("&firma=" + firma);
		}

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		Collection<JsonMaintMainCundfRecord> list = new ArrayList<JsonMaintMainCundfRecord>();
		if (jsonPayload != null) {
			jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
			JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
			if (container != null) {
		        for(JsonMaintMainCundfRecord record : container.getList()){
					if (no.systema.jservices.common.util.StringUtils.hasValueIgnoreZero(record.getPostnr())) {
			        	String leftPaddedPostnr = StringUtils.leftPad(record.getPostnr(), 4, '0');
						record.setPostnr(leftPaddedPostnr);
					} else if (no.systema.jservices.common.util.StringUtils.hasValueIgnoreZero(record.getSypoge())) {
						record.setPostnr(record.getSypoge());
					}
					list.add(record);
		        }	
			}
		}

		return list;
	}

	private Collection<JsonMaintMainCundfRecord> fetchList(String applicationUser, String kundnr, String firma, String knavn, String syrg, String syland, String postnr) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		if (kundnr != null) {
			urlRequestParams.append("&kundnr=" + kundnr);
		}
		if (firma != null) {
			urlRequestParams.append("&firma=" + firma);
		}
		if (knavn != null) {
			urlRequestParams.append("&knavn=" + knavn);
		}		
		if (syrg != null) {
			urlRequestParams.append("&syrg=" + syrg);
		}
		if (syland != null) {
			urlRequestParams.append("&syland=" + syland);
		}
		if (postnr != null) {
			urlRequestParams.append("&postnr=" + postnr);
		}
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		Collection<JsonMaintMainCundfRecord> list = new ArrayList<JsonMaintMainCundfRecord>();
		if (jsonPayload != null) {
			jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
			JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
			if (container != null) {
		        for(JsonMaintMainCundfRecord record : container.getList()){
					if (no.systema.jservices.common.util.StringUtils.hasValueIgnoreZero(record.getPostnr())) {
			        	String leftPaddedPostnr = StringUtils.leftPad(record.getPostnr(), 4, '0');
						record.setPostnr(leftPaddedPostnr);
					} else if (no.systema.jservices.common.util.StringUtils.hasValueIgnoreZero(record.getSypoge())) {
						record.setPostnr(record.getSypoge());
					}

					list.add(record);
		        }	
			}
		}

		return list;
	}

	private int deleteRecord(String applicationUser, String kundnr, String firma, String mode, StringBuffer errMsg) {
		int retval = 0;
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_DML_UPDATE_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&mode=" + mode);
		urlRequestParams.append("&kundnr=" + kundnr);
		urlRequestParams.append("&firma=" + firma);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		if (jsonPayload != null) {
			JsonMaintMainCundfContainer container = this.maintMainCundfService.doUpdate(jsonPayload);
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
	
	@Qualifier ("maintMainCundfService")
	private MaintMainCundfService maintMainCundfService;
	@Autowired
	@Required
	public void setMaintMainCundfService (MaintMainCundfService value){ this.maintMainCundfService = value; }
	public MaintMainCundfService getMaintMainCundfService(){ return this.maintMainCundfService; }
	
	@Qualifier ("maintMainKofastService")
	private MaintMainKofastService maintMainKofastService;
	@Autowired
	@Required
	public void setMaintMainKofastService (MaintMainKofastService value){ this.maintMainKofastService = value; }
	public MaintMainKofastService getMaintMainKofastService(){ return this.maintMainKofastService; }
	

	@Qualifier ("maintSadImportKodtlikService")
	private MaintSadImportKodtlikService maintSadImportKodtlikService;
	@Autowired
	@Required
	public void setMaintSadImportKodtlikService (MaintSadImportKodtlikService value){ this.maintSadImportKodtlikService = value; }
	public MaintSadImportKodtlikService getMaintSadImportKodtlikService(){ return this.maintSadImportKodtlikService; }	
	
	@Qualifier ("maintMainKodtlikService")
	private MaintMainKodtlikService maintMainKodtlikService;
	@Autowired
	@Required
	public void setMaintMainKodtlikService (MaintMainKodtlikService value){ this.maintMainKodtlikService = value; }
	public MaintMainKodtlikService getMaintMainKodtlikServicee(){ return this.maintMainKodtlikService; }
	
	@Qualifier ("maintMainKodtotyService")
	private MaintMainKodtotyService maintMainKodtotyService;
	@Autowired
	@Required
	public void setMaintMainKodtotyService (MaintMainKodtotyService value){ this.maintMainKodtotyService = value; }
	public MaintMainKodtotyService getMaintMainKodtotyService(){ return this.maintMainKodtotyService; }		
		
	@Qualifier ("maintMainValufService")
	private MaintMainValufService maintMainValufService;
	@Autowired
	@Required
	public void setMaintMainValufService (MaintMainValufService value){ this.maintMainValufService = value; }
	public MaintMainValufService getMaintMainValufService(){ return this.maintMainValufService; }		
		
	
}

