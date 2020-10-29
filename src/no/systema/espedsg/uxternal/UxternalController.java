package no.systema.espedsg.uxternal;

import java.io.*;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.general.UploadFileToArchiveService;
import no.systema.main.service.login.SystemaWebLoginService;
import no.systema.main.url.store.MainUrlDataStore;

import no.systema.main.model.jsonjackson.JsonSystemaUserContainer;
import no.systema.main.model.jsonjackson.general.JsonFileUploadToArchiveValidationContainer;


/**
 * eSpedsg External Controller
 * This controller is used from external systems. Usually from RPG-GUIs.
 * The main difference within all other modules or submodules is that no login will be required as mandatory. 
 * This modules could be used without any login use-case what so ever. E.g. loading a file model dialog ... etc
 * 
 * @author oscardelatorre
 * @date Oct 2020
 * 
 */

@Controller
public class UxternalController {
	
	//SERVICES
	@Autowired 
	private UrlCgiProxyService urlCgiProxyService;
		
	@Autowired 
	private SystemaWebLoginService systemaWebLoginService;
	
	@Autowired
	private UploadFileToArchiveService uploadFileToArchiveService;
	
	
	
	private static final Logger logger = Logger.getLogger(UxternalController.class.getName());
	private String ERROR_STD_TEXT = "Invalid server request";
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="uxternal_uploadfile.do", params="action=doInit", method=RequestMethod.GET)
	public ModelAndView doUploadFile(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("espedsg_uxternal_uploadfile");
		
		String user = request.getParameter("user");
		String avd = request.getParameter("avd");
		String opd = request.getParameter("opd");
		
		if(this.isValidRequest(user, avd, opd)){
			model.put("user", user);	
			model.put("wsavd", avd);
			model.put("wsopd", opd);
			
		}else{
			model.put("errorMessage", this.ERROR_STD_TEXT);
			
		}	
		successView.addObject("model" , model);
    	return successView;
	}
	/**
	 * Actual file saving during the upload event
	 * 
	 * @param file
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="uxternal_uploadfile.do", params="action=doSave", method = RequestMethod.POST)
    public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file, HttpSession session, HttpServletRequest request ) {
		String applicationUser = request.getParameter("applicationUser");
		String avd = request.getParameter("wsavd");
	    String opd = request.getParameter("wsopd");
	    String type = request.getParameter("wstype");
		
	    //check user (should be in session already)
	    if(this.isValidRequest(applicationUser, avd, opd)){
			return "Invalid ...?";
		}else{
			logger.info("User:" + applicationUser);
	        if (!file.isEmpty()) {
        		String fileName = file.getOriginalFilename();
        		logger.info("FILE NAME:" + fileName);
                //validate file
        		JsonFileUploadToArchiveValidationContainer uploadValidationContainer = this.validateFileUpload(fileName, applicationUser);
                //if valid
                if(uploadValidationContainer!=null && "".equals(uploadValidationContainer.getErrMsg())){
	                	// TEST String rootPath = System.getProperty("catalina.home");
                		String rootPath	= uploadValidationContainer.getTmpdir();
                	    File dir = new File(rootPath);
                	    
		        	    try {
			                byte[] bytes = file.getBytes();
			                // Create the file on server
			                File serverFile = new File(dir.getAbsolutePath() + File.separator +  fileName);
			                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			                stream.write(bytes);
			                stream.close();
			                logger.info("Server File Location=" + serverFile.getAbsolutePath());
			                //catch parameters
			                uploadValidationContainer.setWstur(request.getParameter("wstur"));
	        	    		uploadValidationContainer.setWsavd(request.getParameter("wsavd"));
	        	    		uploadValidationContainer.setWsopd(request.getParameter("wsopd"));
	        	    		uploadValidationContainer.setWstype(request.getParameter("wstype"));
	        	    		//this will check if either the wstur or wsavd/wsopd will save the upload
	        	    		uploadValidationContainer = this.saveFileUpload(uploadValidationContainer, fileName, applicationUser);
			                if(uploadValidationContainer!=null && uploadValidationContainer.getErrMsg()==""){
			                		String suffixMsg = "";
			                		if(uploadValidationContainer.getWstur()!=null && !"".equals(uploadValidationContainer.getWstur())){
			                			suffixMsg = "  -->Tur:" + "["+ uploadValidationContainer.getWstur() + "]";
			                		}else{
			                			suffixMsg = "  -->Avd/Opd:" + "["+ uploadValidationContainer.getWsavd() + "/" + uploadValidationContainer.getWsopd() + "]";
			                		}
			                		return "You successfully uploaded file:" + fileName +  suffixMsg;
			                }else{
			                		return "You failed to upload [on MOVE] =" + fileName;
			                }
		        	    } catch (Exception e) {
		            		//run time upload error
		            		String absoluteFileName = rootPath + File.separator + fileName;
		            		return "You failed to upload to:" + fileName + " runtime error:" + e.getMessage();
		            }

                }else{
		        		if(uploadValidationContainer!=null){
		        			//Back-end error message output upon validation
		        			return uploadValidationContainer.getErrMsg();
		        		}else{
		        			return "NULL on upload file validation Object??";
		        		}
		        	}
	        } else {
	            return "You failed to upload an empty file.";
	        }
		}
    }
	
	/**
	 * 
	 * @param fileName
	 * @param applicationUser
	 * @return
	 */
	private JsonFileUploadToArchiveValidationContainer validateFileUpload(String fileName, String applicationUser){
		JsonFileUploadToArchiveValidationContainer uploadValidationContainer = null;
		//prepare the access CGI with RPG back-end
		String BASE_URL = MainUrlDataStore.SYSTEMA_UPLOAD_FILE_VALIDATION_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&wsdokn=" + fileName;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			uploadValidationContainer = this.uploadFileToArchiveService.getFileUploadValidationContainer(jsonPayload);
			logger.info(uploadValidationContainer.getErrMsg());
		}
		return uploadValidationContainer;
	}
	
	/**
	 * 
	 * @param uploadValidationContainer
	 * @param fileName
	 * @param applicationUser
	 * @param userDate
	 * @param userTime
	 * @return
	 */
	private JsonFileUploadToArchiveValidationContainer saveFileUpload(JsonFileUploadToArchiveValidationContainer uploadValidationContainer, String fileName, String applicationUser){
		//prepare the access CGI with RPG back-end
		String BASE_URL = MainUrlDataStore.SYSTEMA_UPLOAD_FILE_AFTER_VALIDATION_APPROVAL_URL;
		String absoluteFileName = uploadValidationContainer.getTmpdir() + fileName;
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + applicationUser);
		//Either TUR or AVD/OPD (order level)... Depending on the caller (Tur-level OR order-level)
		if(uploadValidationContainer.getWstur()!=null && !"".equals(uploadValidationContainer.getWstur())){
			urlRequestParamsKeys.append("&wstur=" + uploadValidationContainer.getWstur());
		}else{
			if(uploadValidationContainer.getWsavd()!=null && !"".equals(uploadValidationContainer.getWsavd())){
				urlRequestParamsKeys.append("&wsavd=" + uploadValidationContainer.getWsavd());
			}
			if(uploadValidationContainer.getWsopd()!=null && !"".equals(uploadValidationContainer.getWsopd())){
				urlRequestParamsKeys.append("&wsopd=" + uploadValidationContainer.getWsopd());
			}
		}
		urlRequestParamsKeys.append("&wstype=" + uploadValidationContainer.getWstype());
		urlRequestParamsKeys.append("&wsdokn=" + absoluteFileName);
		//Timestamp (if applicable)
		/*
		if( (userDate!=null && !"".equals(userDate)) && (userTime!=null && !"".equals(userTime))){
			urlRequestParamsKeys.append("&wsdate=" + userDate + "&wstime=" + userTime);
		}*/
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		//Debug -->
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			uploadValidationContainer = this.uploadFileToArchiveService.getFileUploadValidationContainer(jsonPayload);
			logger.info(uploadValidationContainer.getErrMsg());
		}
		return uploadValidationContainer;
	}
	/**
	 * validation of entry point
	 * @param user
	 * @param avd
	 * @param opd
	 * @return
	 */
	private boolean isValidRequest(String user, String avd, String opd){
		boolean retval = false;
		if(StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(avd) && StringUtils.isNotEmpty(opd)){
			if(checkUser(user)){
				retval = true;
			}
		}
		
		return retval;
	}
	
	/**
	 * Checks user validity in BRIDF
	 * @param applicationUser
	 * @return
	 */
	private boolean checkUser(String applicationUser){
		boolean retval = false;
		//http://localhost:8080/syjservicesbcore/syjsBRIDFDPR.do?user=OSCAR
		String URL = MainUrlDataStore.SYSTEMA_GET_USER_FROM_BRIDF_URL;
		String urlRequestParams = "user="+ applicationUser;
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(URL, urlRequestParams);
    	logger.warn(URL);
    	logger.warn(urlRequestParams);
    	logger.warn(jsonPayload);
    	JsonSystemaUserContainer container = this.systemaWebLoginService.getSystemaUserContainer(jsonPayload);
    	if(container!=null && container.getUser().equals(applicationUser)){
    		retval = true;
    	}
	
    	return retval;	
	}
	
	
}
	
	
	
	

