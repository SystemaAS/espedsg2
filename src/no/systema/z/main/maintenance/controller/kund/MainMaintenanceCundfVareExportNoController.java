package no.systema.z.main.maintenance.controller.kund;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import no.systema.main.service.UrlCgiProxyService;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadSadlRecord;
import no.systema.external.tvinn.sad.z.maintenance.service.MaintSadSadlService;
import no.systema.external.tvinn.sad.z.maintenance.controller.MaintSadExportSad004Controller;
import no.systema.external.tvinn.sad.z.maintenance.service.MaintSadExportKodts6Service;
import no.systema.z.main.maintenance.service.MaintMainSyparfService;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;

/**
 * Controller for Export(no) for Vareregister in Kunderegister
 * 
 * NOTE: This class has references to business logic hosted in package: no.systema.tvinn.sad.z.maintenance.sadexport
 * 
 * 
 * @author Fredrik Möller
 * @date Mar 15, 2017
 * 
 * 
 */

@Controller
public class MainMaintenanceCundfVareExportNoController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceCundfVareExportNoController.class.getName());

	@RequestMapping(value = "mainmaintenancecundf_vareexp_no.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doVareRegisterExpNo(HttpSession session, HttpServletRequest request) {
		KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
		String knavn = kundeSessionParams.getKnavn();

		MaintSadExportSad004Controller maintSadExportSad004Controller = new MaintSadExportSad004Controller();
		maintSadExportSad004Controller.setUrlCgiProxyService(urlCgiProxyService);
		maintSadExportSad004Controller.setMaintSadSadlService(maintSadSadlService);
		maintSadExportSad004Controller.setMaintSadExportKodts6Service(maintSadExportKodts6Service);

		ModelAndView successView  = maintSadExportSad004Controller.doSadMaintImportList(session, request,kundeSessionParams.getKundnr());
		successView.setViewName("mainmaintenancecundf_vareexp_no_edit");
		successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(knavn));
		
		return successView;
		
	}	
	
	@RequestMapping(value="mainmaintenancecundf_vareexp_no_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_vareexp_no_edit(@ModelAttribute ("record") JsonMaintSadSadlRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);	
		String knavn = kundeSessionParams.getKnavn();
		recordToValidate.setSlknr(kundeSessionParams.getKundnr());
		
		MaintSadExportSad004Controller maintSadExportSad004Controller = new MaintSadExportSad004Controller();
		maintSadExportSad004Controller.setUrlCgiProxyService(urlCgiProxyService);
		maintSadExportSad004Controller.setMaintSadSadlService(maintSadSadlService);
		maintSadExportSad004Controller.setMaintSadExportKodts6Service(maintSadExportKodts6Service);
		ModelAndView successView  = maintSadExportSad004Controller.doSadMaintImportEdit(recordToValidate, bindingResult, session, request);
		
		if (maintSadExportSad004Controller.hasError()) {
			successView.setViewName("mainmaintenancecundf_vareexp_no_edit");
			successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(knavn));
		} else {
			successView = new ModelAndView("redirect:mainmaintenancecundf_vareexp_no.do" );
		}
		
		return successView;
	
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

	@Qualifier("maintMainSyparfService")
	private MaintMainSyparfService maintMainSyparfService;

	@Autowired
	@Required
	public void setMaintMainSyparfService(MaintMainSyparfService value) {
		this.maintMainSyparfService = value;
	}

	public MaintMainSyparfService getMaintMainSyparfService() {
		return this.maintMainSyparfService;
	}

	@Qualifier("maintSadSadlService")
	private MaintSadSadlService maintSadSadlService;

	@Autowired
	@Required
	public void setMaintSadSadlService(MaintSadSadlService value) {
		this.maintSadSadlService = value;
	}

	public MaintSadSadlService getMaintSadSadlService() {
		return this.maintSadSadlService;
	}

	@Qualifier("maintSadExportKodts6Service")
	private MaintSadExportKodts6Service maintSadExportKodts6Service;

	@Autowired
	@Required
	public void setMaintSadExportKodts6Service(MaintSadExportKodts6Service value) {
		this.maintSadExportKodts6Service = value;
	}

	public MaintSadExportKodts6Service getMaintSadExportKodts6Service() {
		return this.maintSadExportKodts6Service;
	}
	
}
