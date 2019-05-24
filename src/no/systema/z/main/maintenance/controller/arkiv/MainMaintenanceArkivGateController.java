package no.systema.z.main.maintenance.controller.arkiv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
//models
import no.systema.z.main.maintenance.model.MainMaintenanceMainListObject;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.util.MessageSourceHelper;


/**
 * Gateway to the Main Maintenance Application, Arkiv
 * 
 * 
 * @author Fredrik Möller
 * @date Mar 6, 2017
 * 
 * 	
 */
@Controller
public class MainMaintenanceArkivGateController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceArkivGateController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private MessageSourceHelper messageSourceHelper = null;
	
	@RequestMapping(value="mainmaintenancearkivgate.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdgate(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancearkivgate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		messageSourceHelper = new MessageSourceHelper(request);
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenancearkivgate");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_MAIN_MAINTENANCE);
			session.setAttribute(MainMaintenanceConstants.ACTIVE_URL_RPG_MAIN_MAINTENANCE, MainMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			List list = this.populateMaintenanceMainList();
			model.put("list", list);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			return successView;
			
		}
	}

	private List<MainMaintenanceMainListObject> populateMaintenanceMainList(){
		List<MainMaintenanceMainListObject> listObject = new ArrayList<MainMaintenanceMainListObject>();
		MainMaintenanceMainListObject object = new  MainMaintenanceMainListObject();

		
		object.setId("1");
		object.setSubject(messageSourceHelper.getMessage("systema.main.maintenance.arkiv.documents", null));
		object.setCode("mainmaintenancearkiv");
		object.setText("ARC007 / ARKTXT");
		object.setDbTable("ARKTXT");
		object.setStatus("G");
		object.setPgm("arc007");
		listObject.add(object);
		//
		
		return listObject;
	}
	
}

