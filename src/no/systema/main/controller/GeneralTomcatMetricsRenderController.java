package no.systema.main.controller;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.util.AppConstants;
import no.systema.main.util.AppResources;
import no.systema.main.util.ApplicationPropertiesUtil;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.main.util.StringManager;
import no.systema.main.context.TdsServletContext;
import no.systema.main.model.SystemaWebUser;


/**
 * 
 * Tomcat(file) Render Controller 
 * 
 * @author oscardelatorre
 * @date Aug 13, 2019
 * 
 * 
 */

@Controller
public class GeneralTomcatMetricsRenderController {
	//OBSOLETE:  static final ResourceBundle resources = AppResources.getBundle();
	
	private static final Logger logger = Logger.getLogger(GeneralTomcatMetricsRenderController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private StringManager strMgr = new StringManager();
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="renderTomcatMetrics.do", method={ RequestMethod.GET })
	public ModelAndView doRenderTomcatMetrics(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doRenderTomcatMetrics...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			//the method redirects you to the JSP and from there a java script is executed. Look then on the AJAX: TomcatAjaxMetricsController
			ModelAndView tomcatMetricsView = new ModelAndView("tomcatMetrics");
			return(tomcatMetricsView);
			
		}
			
	}	
	
	
}

