package no.systema.z.main.maintenance.controller.avd;

import java.util.*;

import org.slf4j.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.main.service.UrlCgiProxyService;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
//models
import no.systema.z.main.maintenance.model.MainMaintenanceMainListObject;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;


/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Jun 30, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdGateController {
	private static final Logger logger = LoggerFactory.getLogger(MainMaintenanceAvdGateController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="mainmaintenanceavdgate.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdgate(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdgate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdgate");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_MAIN_MAINTENANCE);
			session.setAttribute(MainMaintenanceConstants.ACTIVE_URL_RPG_MAIN_MAINTENANCE, MainMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			List list = this.populateMaintenanceMainList();
			//this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
			//this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//this.setCodeDropDownMgr(appUser, model);
			//init filter with users signature (for starters)
			//searchFilter.setSg(appUser.getTvinnSadSign());
			//successView.addObject("searchFilter" , searchFilter);
			//init the rest
			model.put("list", list);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    
			return successView;
			
		}
	}

	/**
	 * 
	 * @return
	 */
	private List<MainMaintenanceMainListObject> populateMaintenanceMainList(){
		List<MainMaintenanceMainListObject> listObject = new ArrayList<MainMaintenanceMainListObject>();
		MainMaintenanceMainListObject object = new  MainMaintenanceMainListObject();
		        
		
		object.setId("1");
		object.setSubject("Generelle Avd");
		object.setCode("mainmaintenanceavd"); //JSP page prefix
		object.setText("SYFA14 / KODTA, NAVAVD, KODTSF, KODTD, KODTASID, FIRM, CUNDF");
		object.setDbTable("KODTA");
		object.setStatus("G");
		object.setPgm("syfa14r");
		listObject.add(object);
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("2");
		object.setSubject("TVINN - SAD Import");
		object.setCode("mainmaintenanceavdsadimport"); //JSP page prefix
		object.setText("SYFTAAA / STANDI");
		object.setDbTable("STANDI");
		object.setStatus("G");
		object.setPgm("syftaaar");
		listObject.add(object);
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("3");
		object.setSubject("TVINN - SAD Eksport");
		object.setCode("mainmaintenanceavdsadexport"); //JSP page prefix
		object.setText("SYFTAAA / STANDE");
		object.setDbTable("STANDE");
		object.setStatus("G");
		object.setPgm("syftaaar");
		listObject.add(object);
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("4");
		object.setSubject("TVINN - NCTS Import");
		object.setCode("mainmaintenanceavdsadnctsimport");
		object.setText("TR053R / TRISTD");
		object.setDbTable("TRISTD");
		object.setStatus("G");
		object.setPgm("tr053r");
		listObject.add(object);
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("5");
		object.setSubject("TVINN - NCTS Eksport");
		object.setCode("mainmaintenanceavdsadnctsexport");
		object.setText("TR003R / TRUSTD not (ENTRY, EXIT, SS)");
		object.setDbTable("TRUSTD");
		object.setStatus("G");
		object.setPgm("tr003r");
		listObject.add(object);
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("6");
		object.setSubject("TVINN - NCTS Forhåndsvarsling - Import");
		object.setCode("mainmaintenanceavdsadnctsexport");
		object.setText("TR003R / TRUSTD (ENTRY, EXIT, SS)");
		object.setDbTable("TRUSTD_FHV");
		object.setStatus("G");
		object.setPgm("tr003r");
		listObject.add(object);
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("7");
		object.setSubject("TDS - Import");
		object.setCode("mainmaintenanceavdtdsimport");
		object.setText("SVI051R / SVIA");
		object.setDbTable("SVIA");
		object.setStatus("G");
		object.setPgm("svi051r");
		listObject.add(object);
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("8");
		object.setSubject("TDS - Export");
		object.setCode("mainmaintenanceavdtdsexport");
		object.setText("SVE051R / SVEA");
		object.setDbTable("SVEA");
		object.setStatus("G");
		object.setPgm("sve051r");
		listObject.add(object);
		
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("9");
		object.setSubject("TDS - NCTS Import");
		object.setCode("mainmaintenanceavdtdsnctsimport");
		object.setText("SVX053R / SVNSTD");
		object.setDbTable("SVNSTD");
		object.setStatus("G");
		object.setPgm("svx053r");
		listObject.add(object);
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("10");
		object.setSubject("TDS - NCTS Export");
		object.setCode("mainmaintenanceavdtdsnctsexport");
		object.setText("SVX003R / SVXSTD not (ENTRY, EXIT, SS)");
		object.setDbTable("SVXSTD");
		object.setStatus("G");
		object.setPgm("svx003r");
		listObject.add(object);
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("11");
		object.setSubject("SKAT - Import");
		object.setCode("mainmaintenanceavdskatimport");
		object.setText("DKI051R / DKIA");
		object.setDbTable("DKIA");
		object.setStatus("G");
		object.setPgm("dki051r");
		listObject.add(object);
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("12");
		object.setSubject("SKAT - Eksport");
		object.setCode("mainmaintenanceavdskatexport");
		object.setText("DKE051R / DKEA");
		object.setDbTable("DKEA");
		object.setStatus("G");
		object.setPgm("dke051r");
		listObject.add(object);
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("13");
		object.setSubject("SKAT - NCTS Import");
		object.setCode("mainmaintenanceavdskatnctsimport");
		object.setText("DKX053R / DKNSTD");
		object.setDbTable("DKNSTD");
		object.setStatus("G");
		object.setPgm("dkx053r");
		listObject.add(object);
		//
		object = new  MainMaintenanceMainListObject();
		object.setId("14");
		object.setSubject("SKAT - NCTS Eksport");
		object.setCode("mainmaintenanceavdskatnctsexport");
		object.setText("DKX003R / DKXSTD not (ENTRY, EXIT, SS)");
		object.setDbTable("DKXSTD");
		object.setStatus("G");
		object.setPgm("dkx003r");
		listObject.add(object);
	
		return listObject;
	}
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
		
}

