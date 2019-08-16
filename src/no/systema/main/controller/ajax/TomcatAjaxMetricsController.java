package no.systema.main.controller.ajax;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
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
import no.systema.main.context.TdsServletContext;
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
	public @ResponseBody List getTomcatMetricsData(@RequestParam String user, HttpSession session){
		String MATCH_STRING_SYMN01_PRIMARY = "symn0J.pgm_primary_login";
		String MATCH_STRING_SYMN01_PRIMARY_OK_LOGIN = "symn0J.pgm_ok_login";
		
		logger.info("Inside getTomcatMetricsData...");
		List<TomcatMetrics> rawListAttempts = new ArrayList<TomcatMetrics>();
		List<TomcatMetrics> rawListSuccessAttempts = new ArrayList<TomcatMetrics>();
		List<TomcatMetrics> outputListSimple = new ArrayList<TomcatMetrics>();
		//used when we want to build a consolidated json list of lists (graphs with several lines)
		List<List<TomcatMetrics>> outputListCompound = new ArrayList<List<TomcatMetrics>>();
		
		if(user!=null){
			
			logger.info("<START> ...");
			
			String path = TdsServletContext.getTdsServletContext().getRealPath("/");
			//logger.info("ServletContext:" + path);
			int pathRootIndex = path.indexOf(SERVLET_CONTEXT_WEBAPPS_ROOT);
			String logFile = null;
			
			if(pathRootIndex!=-1){
				logFile = path.substring(0,pathRootIndex) + RELATIVE_LOGFILE_PATH;
				logger.info("logFile:" + logFile);
				
				try{
					File f = new File(logFile);
					
					String tmp = "";
					TomcatMetrics tomcatMetrics;
					try (BufferedReader b = new BufferedReader(new FileReader(f))) { 
					   String readLine = "";
					   
					   //do some things ...
					   while ((readLine = b.readLine()) != null) {
						   if(readLine!=null && readLine.contains(MATCH_STRING_SYMN01_PRIMARY)){  
								 //logger.info("################:" + readLine);
								 tmp = readLine.substring(0,19);
								 tomcatMetrics = new TomcatMetrics();
								 tomcatMetrics.setDate(tmp);
								 //now to the counter of logins during this same hour
								 //System.out.println("XX" + tmp + "XX");
								 Integer hour = Integer.valueOf(tmp.substring(11,13));
								 tomcatMetrics.setHour(hour);
								 tomcatMetrics.setLogins(1);
								 rawListAttempts.add(tomcatMetrics);
							 
							}else if(readLine!=null && readLine.contains(MATCH_STRING_SYMN01_PRIMARY_OK_LOGIN)){
								 tmp = readLine.substring(0,19);
								 tomcatMetrics = new TomcatMetrics();
								 tomcatMetrics.setDate(tmp);
								 //now to the counter of logins during this same hour
								 //System.out.println("XX" + tmp + "XX");
								 Integer hour = Integer.valueOf(tmp.substring(11,13));
								 tomcatMetrics.setHour(hour);
								 tomcatMetrics.setLogins(1);
								 rawListSuccessAttempts.add(tomcatMetrics);
							}
					   } 
					   
					   b.close();
					}
				}catch(Exception e){
					e.printStackTrace();
	
				}
			}
			
			this.populateOutputList(outputListSimple, rawListAttempts);
			this.populateOutputList(outputListCompound, rawListAttempts, rawListSuccessAttempts);
			
		}
		/*TEST	
		List<TomcatMetrics> newList = new ArrayList<TomcatMetrics>();
		TomcatMetrics obj = new TomcatMetrics();
		obj.setDate("2019-08-16 05:00:56");
		obj.setHour(15);
		obj.setLogins(10);
		newList.add(obj);
		//
		obj = new TomcatMetrics();
		obj.setDate("2019-08-16 07:00:56");
		obj.setHour(15);
		obj.setLogins(5);
		obj.setTotalSum(20);
		newList.add(obj);

		List<List> compundList = new ArrayList<List>();
		compundList.add(outputList);
		compundList.add(newList);
		return compundList;
		*/
		return outputListCompound;  
			
	}	
	/**
	 * This method is used for simple graphs with only one json list
	 * @param outputList
	 * @param rawListAttempts
	 */
	private void populateOutputList(List<TomcatMetrics> outputList, List<TomcatMetrics> rawListAttempts){
		//now wash (filter and accumulate logins per hour) the list
		int sum = 0;
		int totalSum = 0;
		TomcatMetrics newTomcatMetrics = new TomcatMetrics();
		int prevHour = 0;
		
		//================
		//List of Attempts
		//================
		//try to accumulate per hour
		for(TomcatMetrics record: rawListAttempts){
			if(prevHour!=record.getHour()){
				//skip first iteration
				if(prevHour>0){
					//logger.info("###: " + newTomcatMetrics.getHour() + " " + newTomcatMetrics.getLogins());
					outputList.add(newTomcatMetrics);
				}
				//init
				newTomcatMetrics = new TomcatMetrics();
				sum = 0;
			}
			sum = sum + record.getLogins();
			totalSum = totalSum + record.getLogins();
			newTomcatMetrics.setDate(record.getDate());
			newTomcatMetrics.setHour(record.getHour());
			newTomcatMetrics.setLogins(sum);
			//
			prevHour = record.getHour();
			
		}
		//last iteration and only object med totalSum since this is the grand total of all records
		newTomcatMetrics.setTotalSum(totalSum);
		outputList.add(newTomcatMetrics);
		
	}
	
	/**
	 * This method is used when we want a consolidated list of lists
	 * @param outputList
	 * @param rawListAttempts
	 * @param rawListSuccessAttempts
	 */
	private void populateOutputList(List<List<TomcatMetrics>> outputList, List<TomcatMetrics> rawListAttempts, List<TomcatMetrics> rawListSuccessAttempts){
		List<TomcatMetrics> list_01 = new ArrayList<TomcatMetrics>();
		List<TomcatMetrics> list_02 = new ArrayList<TomcatMetrics>();
		
		//now wash (filter and accumulate logins per hour) the list
		int sum = 0;
		int totalSum = 0;
		TomcatMetrics newTomcatMetrics = new TomcatMetrics();
		int prevHour = 0;
		
		//================
		//List of Attempts
		//================
		//try to accumulate per hour
		for(TomcatMetrics record: rawListAttempts){
			if(prevHour!=record.getHour()){
				//skip first iteration
				if(prevHour>0){
					//logger.info("###: " + newTomcatMetrics.getHour() + " " + newTomcatMetrics.getLogins());
					list_01.add(newTomcatMetrics);
				}
				//init
				newTomcatMetrics = new TomcatMetrics();
				sum = 0;
			}
			sum = sum + record.getLogins();
			totalSum = totalSum + record.getLogins();
			newTomcatMetrics.setDate(record.getDate());
			newTomcatMetrics.setHour(record.getHour());
			newTomcatMetrics.setLogins(sum);
			//
			prevHour = record.getHour();
			
		}
		//last iteration and only object med totalSum since this is the grand total of all records
		newTomcatMetrics.setTotalSum(totalSum);
		list_01.add(newTomcatMetrics);
		
		//===========================
		//List of Successful Attempts
		//===========================
		sum = 0;
		totalSum = 0;
		newTomcatMetrics = new TomcatMetrics();
		prevHour = 0;
		//try to accumulate per hour
		for(TomcatMetrics record: rawListSuccessAttempts){
			if(prevHour!=record.getHour()){
				//skip first iteration
				if(prevHour>0){
					//logger.info("###: " + newTomcatMetrics.getHour() + " " + newTomcatMetrics.getLogins());
					list_02.add(newTomcatMetrics);
				}
				//init
				newTomcatMetrics = new TomcatMetrics();
				sum = 0;
			}
			sum = sum + record.getLogins();
			totalSum = totalSum + record.getLogins();
			newTomcatMetrics.setDate(record.getDate());
			newTomcatMetrics.setHour(record.getHour());
			newTomcatMetrics.setLogins(sum);
			//
			prevHour = record.getHour();
			
		}
		//last iteration and only object med totalSum since this is the grand total of all records
		newTomcatMetrics.setTotalSum(totalSum);
		list_02.add(newTomcatMetrics);
		//consolidate lists
		outputList.add(list_01);
		outputList.add(list_02);

	}
	
	
	
	
}

