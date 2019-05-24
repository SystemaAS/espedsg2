package no.systema.z.main.maintenance.controller.arkiv;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
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

import no.systema.jservices.common.dto.ArktxtDto;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.validator.MaintMainArktxtValidator;


/**
 * Arkiv - Vedlikehold arkiv dokumenter
 * 
 * 
 * @author Fredrik Möller
 * @date Mar 6, 2017
 * 
 * 	
 */

@Controller
public class MainMaintenanceArkivArc007Controller {
	private static final Logger logger = Logger.getLogger(MainMaintenanceArkivArc007Controller.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	@RequestMapping(value="mainmaintenancearkiv_arc007.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_arc007(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancearkiv_arc007");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String showUpload = request.getParameter("showUpload");
		
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenancearkiv_arc007");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());

			//Get list
			List<ArktxtDto> list = fetchList(appUser.getUser(),showUpload);
			model.put("list", list);
			model.put("showUpload", showUpload);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			return successView;
			
		}
	}

	@RequestMapping(value="mainmaintenancearkiv_arc007_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_arc007_edit(@ModelAttribute ("record") ArktxtDto recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancearkiv_arc007_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String artype = request.getParameter("artype");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu("INIT");
			logger.info("Inside method: mainmaintenancearkiv_arc007_edit");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_MAIN_MAINTENANCE);
			session.setAttribute(MainMaintenanceConstants.ACTIVE_URL_RPG_MAIN_MAINTENANCE, MainMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
			//--------------
			//UPDATE record
			//--------------
			model.put("view_scanning", "J");
			
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				adjustRecordToValidate(recordToValidate);

				//Validate
				MaintMainArktxtValidator validator = new MaintMainArktxtValidator();
				validator.validate(recordToValidate, bindingResult);
				if(bindingResult.hasErrors()){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					
				}else{
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(MainMaintenanceConstants.MODE_UPDATE);
						dmlRetval = this.updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
					}else{
						//create new
						logger.info(MainMaintenanceConstants.MODE_ADD);
						dmlRetval = this.updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
					}
					
					//check for Update errors
					if( dmlRetval < 0){
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					}else{
						//post successful update operations
						updateId = recordToValidate.getArtype();
						//refresh
						ArktxtDto record = fetchRecord(appUser.getUser(), recordToValidate.getArtype());
						if (record != null) {
							adjustView(model, record);
						}
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					}
				}
				
				
			//DELETE	
			}else if(MainMaintenanceConstants.ACTION_DELETE.equals(action)){
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				
				logger.info(MainMaintenanceConstants.MODE_DELETE);
				dmlRetval = this.updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_DELETE, errMsg);
				
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}else{
					//post successful update operations
					successView = new ModelAndView("redirect:mainmaintenancearkiv_arc007.do?id=ARKTXT");
					
				}
			}else{
				//-------------
				//Fetch record
				//-------------
				ArktxtDto record = new ArktxtDto();
				if(artype!=null && !"".equals(artype)){
					record = fetchRecord(appUser.getUser(), artype);
					if (record != null) {
						adjustView(model, record);
					}
				}
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
			}
			
			
			//populate model
			if(action==null || "".equals(action)){
				action = "doUpdate";
			}
			model.put("action", action);
			model.put("avd", artype);
			model.put("updateId", updateId);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			return successView;
			
		}
	}

	private void adjustView(Map model, ArktxtDto record) {
		if (record.getArtype().startsWith("Z") || record.getArtype().startsWith("*")) {
			model.put("view_scanning", "J");
		} else {
			model.put("view_scanning", "N");
		}
	}
	
	/*
	 * Special handling of arkved. Arkved contains 2*30 fields. Note that its the dao that is delivered to service layer
	 */
	private void adjustRecordToValidate(ArktxtDto dto) {
		StringBuilder arkved = new StringBuilder();
		arkved.append(spaceFiller(dto.getArkved1()));
		arkved.append(spaceFiller(dto.getArkved2()));
		arkved.append(spaceFiller(dto.getArkved3()));
		arkved.append(spaceFiller(dto.getArkved4()));
		arkved.append(spaceFiller(dto.getArkved5()));		
		arkved.append(spaceFiller(dto.getArkved6()));		
		arkved.append(spaceFiller(dto.getArkved7()));		
		arkved.append(spaceFiller(dto.getArkved8()));		
		arkved.append(spaceFiller(dto.getArkved9()));		
		arkved.append(spaceFiller(dto.getArkved10()));		
		arkved.append(spaceFiller(dto.getArkved11()));		
		arkved.append(spaceFiller(dto.getArkved12()));		
		arkved.append(spaceFiller(dto.getArkved13()));		
		arkved.append(spaceFiller(dto.getArkved14()));		
		arkved.append(spaceFiller(dto.getArkved15()));		
		arkved.append(spaceFiller(dto.getArkved16()));		
		arkved.append(spaceFiller(dto.getArkved17()));		
		arkved.append(spaceFiller(dto.getArkved18()));		
		arkved.append(spaceFiller(dto.getArkved19()));		
		arkved.append(spaceFiller(dto.getArkved20()));		
		arkved.append(spaceFiller(dto.getArkved21()));		
		arkved.append(spaceFiller(dto.getArkved22()));		
		arkved.append(spaceFiller(dto.getArkved23()));		
		arkved.append(spaceFiller(dto.getArkved24()));		
		arkved.append(spaceFiller(dto.getArkved25()));		
		arkved.append(spaceFiller(dto.getArkved26()));		
		arkved.append(spaceFiller(dto.getArkved27()));		
		arkved.append(spaceFiller(dto.getArkved28()));		
		arkved.append(spaceFiller(dto.getArkved29()));		
		arkved.append(spaceFiller(dto.getArkved30()));		
		
		dto.setArkved(arkved.toString());
		
		dto.setArtype(dto.getArtype().toUpperCase());
		
	}

	private String spaceFiller(String toFill) {
		String filled = null;
		String TWO_SPACES = "  ";
		
		if (toFill != null && !"".equals(toFill)) {
			filled = toFill;
		} else {
			filled = TWO_SPACES;
		}
		
		return filled;
	}	
	
	
	private List<ArktxtDto> fetchList(String applicationUser, String showUpload) {
		JsonReader<JsonDtoContainer<ArktxtDto>> jsonReader = new JsonReader<JsonDtoContainer<ArktxtDto>>();
		jsonReader.set(new JsonDtoContainer<ArktxtDto>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_ARKTXT_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);
		if (showUpload != null) {
			urlRequestParams.append("&showupload=" + showUpload);
		}

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List<ArktxtDto> list = null;
		if (jsonPayload != null) {
			JsonDtoContainer<ArktxtDto> container = (JsonDtoContainer<ArktxtDto>) jsonReader.get(jsonPayload);
				if (container != null) {
					list = container.getDtoList();
				}
		}
		return list;
	}	
	
	private ArktxtDto fetchRecord(String applicationUser, String artype) {
		JsonReader<JsonDtoContainer<ArktxtDto>> jsonReader = new JsonReader<JsonDtoContainer<ArktxtDto>>();
		jsonReader.set(new JsonDtoContainer<ArktxtDto>());
		ArktxtDto record = new ArktxtDto();
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_ARKTXT_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&artype=" + artype);		
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload="+jsonPayload);
		if (jsonPayload != null) {
			JsonDtoContainer<ArktxtDto> container = (JsonDtoContainer<ArktxtDto>) jsonReader.get(jsonPayload);
			if (container != null) {
				for (ArktxtDto arktxtDto : container.getDtoList()) {
					record = arktxtDto;
				}
			}
		}
		return record;
	}
	
	private int updateRecord(SystemaWebUser appUser, ArktxtDto record, String mode, StringBuffer errMsg){
		int retval = 0;
		JsonReader<JsonDtoContainer<ArktxtDto>> jsonReader = new JsonReader<JsonDtoContainer<ArktxtDto>>();
		jsonReader.set(new JsonDtoContainer<ArktxtDto>());
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_ARKTXT_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mode=" + mode + "&lang=" + appUser.getUsrLang();;
		String urlRequestParams = urlRequestParameterMapper.getUrlParameterValidString((record)); //Note: dao object is used in mapper
		logger.info("Record="+ReflectionToStringBuilder.toString(record));
		logger.info("urlRequestParams="+urlRequestParams);
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	logger.info("jsonPayload="+jsonPayload);
		if (jsonPayload != null) {
			JsonDtoContainer<ArktxtDto> container = (JsonDtoContainer<ArktxtDto>) jsonReader.get(jsonPayload);
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
	
	
}

