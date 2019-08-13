package no.systema.main.controller.ajax;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.util.ApplicationPropertiesUtil;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.main.util.StringManager;

import no.systema.main.model.TomcatMetrics;


/**
 * 
 * Tomcat(file) Ajax Controller 
 * 
 * @author oscardelatorre
 * @date Aug 13, 2019
 * 
 * 
 */

@RestController
public class TomcatAjaxMetricsController {
	//OBSOLETE:  static final ResourceBundle resources = AppResources.getBundle();
	
	private static final Logger logger = Logger.getLogger(TomcatAjaxMetricsController.class.getName());
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	
	private final String RELATIVE_LOGFILE_PATH = "logs/" + ApplicationPropertiesUtil.getProperty("log4j.logger.file");   //OBSOLETE: resources.getString("log4j.logger.file");
	private final String SERVLET_CONTEXT_WEBAPPS_ROOT = "webapps";
	//Special case Transport Module
	private final String RELATIVE_LOGFILE_PATH_TRANSPORT_MODULE = "logs/log4j_espedsg2_transportModule.log"; 
	
	private ModelAndView loginView = new ModelAndView("login");
	
	private ApplicationContext context;
	//
	private StringManager strMgr = new StringManager();
	
	
	/**
	 * 
	 * @param applicationUser
	 * @return
	 */
	@RequestMapping(value="getTomcatMetricsData.do", method={ RequestMethod.GET })
	public @ResponseBody List getTomcatMetricsData(@RequestParam String user){
		
		logger.info("Inside getTomcatMetricsData...");
		List<TomcatMetrics> list = new ArrayList<TomcatMetrics>();
		if(user!=null){
			
			logger.info("<START> ...");
			TomcatMetrics obj = new TomcatMetrics();
			obj.setDate("2015-07-09 09:00");
			obj.setLogins(5);
			list.add(obj);
			//
			obj = new TomcatMetrics();
			obj.setDate("2015-07-09 13:20");
			obj.setLogins(2);
			list.add(obj);
			logger.info("<END> OK");
		}
					
		return list;  
			
	}	
	
	
}

