	/**
 * 
 */
package no.systema.transportdisp.validator.backend;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;

//import no.systema.tvinn.sad.model.external.url.UrlTvinnSadTolltariffenObject;
import no.systema.transportdisp.mapper.url.request.UrlRequestParameterMapper;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.frisokvei.JsonTransportDispWorkflowSpecificOrderFrisokveiContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.validationbackend.JsonTransportDispWorkflowSpecificOrderValidationBackendContainer;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificOrderService;

/**
 * Work with Trips - Transport Disponering
 *
 * This Validator is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a caller when needed.
 * 
 * It aims to provide a single-point-of-entry for back-end validator behavior.
 *  
 * 
 * @author oscardelatorre
 * @date Aug 21, 2015
 * 
 * 
 */
public class SpecificOrderValidatorBackend {
	private static final Logger logger = Logger.getLogger(SpecificOrderValidatorBackend.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1000);
	private UrlCgiProxyService urlCgiProxyService;
	private TransportDispWorkflowSpecificOrderService transportDispWorkflowSpecificOrderService;
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	
	
	//required constructor
	public SpecificOrderValidatorBackend(UrlCgiProxyService urlCgiProxyService, TransportDispWorkflowSpecificOrderService transportDispWorkflowSpecificOrderService){
		this.urlCgiProxyService = urlCgiProxyService;
		this.transportDispWorkflowSpecificOrderService = transportDispWorkflowSpecificOrderService;
	}
	
	
	private JsonTransportDispWorkflowSpecificOrderValidationBackendContainer validationOutputContainer = new JsonTransportDispWorkflowSpecificOrderValidationBackendContainer();
	public void setValidationOutputContainer(JsonTransportDispWorkflowSpecificOrderValidationBackendContainer value) {  this.validationOutputContainer = value; }
	public JsonTransportDispWorkflowSpecificOrderValidationBackendContainer getValidationOutputContainer() { return this.validationOutputContainer;}
	
	private JsonTransportDispWorkflowSpecificOrderRecord validationOutputRecord = new JsonTransportDispWorkflowSpecificOrderRecord();
	public void setValidationOutputRecord(JsonTransportDispWorkflowSpecificOrderRecord value) {  this.validationOutputRecord = value; }
	public JsonTransportDispWorkflowSpecificOrderRecord getValidationOutputRecord() { return this.validationOutputRecord;}
	
	private JsonTransportDispWorkflowSpecificOrderFraktbrevRecord validationFraktbrevRecord = new JsonTransportDispWorkflowSpecificOrderFraktbrevRecord();
	public void setValidationFraktbrevRecord(JsonTransportDispWorkflowSpecificOrderFraktbrevRecord value) {  this.validationFraktbrevRecord = value; }
	public JsonTransportDispWorkflowSpecificOrderFraktbrevRecord getValidationFraktbrevRecord() { return this.validationFraktbrevRecord;}

	
	private List<String> validationOutputErrMsgList = new ArrayList<String>();
	public List<String> getValidationOutputErrMsgList() { return this.validationOutputErrMsgList;}
	
	private List<String> validationOutputInfoMsgList = new ArrayList<String>();
	public List<String> getValidationOutputInfoMsgList() { return this.validationOutputInfoMsgList;}
	
	private List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> validationOutputOderLinesList = new ArrayList<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord>();
	public List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> getValidationOutputOderLinesList() { return this.validationOutputOderLinesList;}
	
	
	/**
	 * 
	 * @param appUser
	 * @param record
	 * @param session
	 * @param request
	 */
	public void validateOrder(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, HttpSession session, HttpServletRequest request){
		
		try{
			
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_MAIN_ORDER_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append("&avd=" + recordToValidate.getHeavd());
			urlRequestParamsKeys.append("&opd=" + recordToValidate.getHeopd());
			//Now build the URL string
			String urlRequestParamsOrder = this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
			String urlRequestParams = urlRequestParamsKeys.toString() + urlRequestParamsOrder;
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("BASE_URL:" + BASE_URL);
			logger.info("BASE_PARAMS:" + urlRequestParams.toString());
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			//logger.debug(this.jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
			logger.debug(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			if(jsonPayload!=null){
				JsonTransportDispWorkflowSpecificOrderValidationBackendContainer container = this.transportDispWorkflowSpecificOrderService.getOrderValidationBackendContainer(jsonPayload);
				if(container!=null){
					this.validationOutputContainer = container;
					//(1) Extract the errMsg if needed
					if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
						this.validationOutputErrMsgList = this.parseErrMsg(container.getErrMsg());
						this.validationOutputInfoMsgList = this.parseErrMsg(container.getInfoMsg());
						this.validationOutputContainer.setErrMsgListFromValidationBackend(this.validationOutputErrMsgList);
						this.validationOutputContainer.setInfoMsgListFromValidationBackend(this.validationOutputInfoMsgList);
						
						
					}
					//(2) Extract the return record (for further use at the callers domain...if applicable)
	    			for (JsonTransportDispWorkflowSpecificOrderRecord record: container.getOrdervalidate()){
	    				this.validationOutputRecord = record;
	    				logger.info("[VALIDATION BACK END ERROR] on container:" + container.getErrMsg());
	    			}
	    			
	    			
				}else{
					logger.info("[ERROR FATAL***] <container == NULL after return on AS400 service call ... ?>");
				}
			}
			//---------------------------------
			//Now add validation on item lines
			//---------------------------------
			//this.validateOrderLines(appUser, recordToValidate, request);

			
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
	}
	
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 * @param model
	 * @param session
	 * @return
	 */
	public void validateFriSokvei(SystemaWebUser appUser, String avd, String opd){
		final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_FRISOKVEI_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&avd=" + avd); 
		urlRequestParams.append("&opd=" + opd);
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.debug(this.jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			JsonTransportDispWorkflowSpecificOrderFrisokveiContainer container = this.transportDispWorkflowSpecificOrderService.getOrderFrisokveiContainer(jsonPayload);
			
			if(container!=null){
				logger.info("Inside container:" + container.getErrMsg());
				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
					logger.info("ERROR Frisokvei:" + container.getErrMsg());
					this.validationOutputContainer = new JsonTransportDispWorkflowSpecificOrderValidationBackendContainer();
					this.validationOutputContainer.setHeavd(avd);
					this.validationOutputContainer.setHeopd(opd);
					this.validationOutputContainer.setErrMsg(container.getErrMsg());
				}
			}	
		}

	}
	/**
	 * 
	 * @param errMsgRaw
	 * @return
	 */
	private List<String> parseErrMsg(String errMsgRaw){
		String SEPARATOR_CHARACTER = "§";
		List<String> list = new ArrayList<String>();
		if(errMsgRaw!=null && !"".equals(errMsgRaw)){
			list = Arrays.asList(errMsgRaw.split(SEPARATOR_CHARACTER));
		}
		//we must do this to avoid the fixed size of an Arrays.asList() convertion!
		//the list must be resizable for further adds.
		return new ArrayList<String>(list);
	}
	
	/**
	 * Populate the json-parameter string with the order lines (iterate through all)
	 * Note: Important to send the correct back-end number line (fvlinr)
	 * 
	 * @param appUser
	 * @param recordToValidate
	 * @param request
	 */
	public void  validateOrderLines(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, HttpServletRequest request ){
		String RECORD_SEPARATOR_CHARACTER_RAW = "§";
		String RECORD_SEPARATOR_CHARACTER_REPLACE = " ; ";
		
		logger.info("Inside:validateOrderLines");
		//init lists
		
		//check the total number of lines
		int totalNumberOfLines = TransportDispConstants.CONSTANT_TOTAL_NUMBER_OF_ORDER_LINES; //Default
		if(!"".equals(recordToValidate.getTotalNumberOfLines()) && recordToValidate.getTotalNumberOfLines()!=null){
			try{
				int tmpLimit = Integer.parseInt(recordToValidate.getTotalNumberOfLines());
				if(tmpLimit>totalNumberOfLines){
					totalNumberOfLines = Integer.parseInt(recordToValidate.getTotalNumberOfLines());
				}
			}catch(Exception e){
				//nothing
			}
		}
		JsonTransportDispWorkflowSpecificOrderFraktbrevRecord fraktbrevRecord = null; 
		for(int i=1;i<=totalNumberOfLines;i++){
			String lineNr = request.getParameter("fvlinr_" + i);
			fraktbrevRecord = new JsonTransportDispWorkflowSpecificOrderFraktbrevRecord();
			lineNr = request.getParameter("lineNr_" + i);
		
			//only valid fields (check requirements)
			if(this.validMandatoryFieldsFraktbrev(request, i)){
				 String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_URL;
				 //add URL-parameters
				 StringBuffer urlRequestParamsKeys = new StringBuffer();
				 urlRequestParamsKeys.append("user=" + appUser.getUser());
				 urlRequestParamsKeys.append("&avd=" + recordToValidate.getHeavd());
				 urlRequestParamsKeys.append("&opd=" + recordToValidate.getHeopd());
				 String urlRequestParamsOrderLine = this.getFvUrlRequestParams(request, i, fraktbrevRecord);
				 String urlRequestParams = urlRequestParamsKeys.toString() + urlRequestParamsOrderLine;
				 
				 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				 logger.info("URL: " + BASE_URL);
				 logger.info("URL PARAMS: " + urlRequestParams);
				 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				 
				 if(jsonPayload!=null){ 
					JsonTransportDispWorkflowSpecificOrderFraktbrevContainer fraktbrevContainer = this.transportDispWorkflowSpecificOrderService.getFraktbrevContainer(jsonPayload);
					
					if(fraktbrevContainer!=null){
						if(fraktbrevContainer.getAwblineValidate()!=null){
							for (JsonTransportDispWorkflowSpecificOrderFraktbrevRecord record : fraktbrevContainer.getAwblineValidate()){
								logger.info("ORDER LINE output [fvlinr]:" + record.getFvlinr());
								logger.info("ORDER LINE output [fvlm]:" + record.getFvlm());
								record.setFvlinr(lineNr);
								this.validationOutputOderLinesList.add(record);
							}
						}
						
						if( !"".equals(fraktbrevContainer.getErrMsg()) && fraktbrevContainer.getErrMsg()!=null){
							//Debug
							logger.info("[ERROR] order line:" + fraktbrevContainer.getErrMsg());
							String linjePrefix = "";
							if(!fraktbrevContainer.getErrMsg().contains("Lin.")){ linjePrefix = "Linjenr [" + lineNr +"] "; }
							this.validationOutputErrMsgList.add(linjePrefix + fraktbrevContainer.getErrMsg().replaceAll(RECORD_SEPARATOR_CHARACTER_RAW, RECORD_SEPARATOR_CHARACTER_REPLACE));
						}
						if( !"".equals(fraktbrevContainer.getInfoMsg()) && fraktbrevContainer.getInfoMsg()!=null){
							logger.info("[INFO] order line:" + fraktbrevContainer.getInfoMsg());
							String linjePrefix = "";
							if(!fraktbrevContainer.getInfoMsg().contains("Lin.")){ linjePrefix = "Linjenr [" + lineNr +"] "; }
							this.validationOutputInfoMsgList.add(linjePrefix + fraktbrevContainer.getInfoMsg().replaceAll(RECORD_SEPARATOR_CHARACTER_RAW, RECORD_SEPARATOR_CHARACTER_REPLACE));
						}
					}else{
						logger.info("[ERROR FATAL***] < order line container == NULL after return on AS400 service call ... ?>");
					}
				 }
			}
		}
		//
		this.validationOutputContainer.setErrMsgListFromValidationBackend(this.validationOutputErrMsgList);
		this.validationOutputContainer.setInfoMsgListFromValidationBackend(this.validationOutputInfoMsgList);
		
	}
	/**
	 * 
	 * @param request
	 * @param counter
	 * @return
	 */
	private boolean validMandatoryFieldsFraktbrev(HttpServletRequest request, int counter){
		boolean retval = false;
		String ant = request.getParameter("fvant_" + counter);
		String vkt = request.getParameter("fvvkt_" + counter);
		String desc = request.getParameter("fvvt_" + counter);
		
		if( (ant!=null && !"".equals(ant))  && 
			(vkt!=null && !"".equals(vkt))  && 
			(desc!=null && !"".equals(desc))){
			
			retval = true;
			
		}
		return retval;
	}
	/**
	 * 
	 * @param request
	 * @param counter
	 * @param fraktbrevRecord
	 * @return
	 */
	private String getFvUrlRequestParams(HttpServletRequest request, int counter, JsonTransportDispWorkflowSpecificOrderFraktbrevRecord fraktbrevRecord){
		StringBuffer urlRequestParams = new StringBuffer();
		String fvlinr = request.getParameter("fvlinr_" + counter);
		String lineCounter = request.getParameter("lineNr_" + counter);
		//Required: because of taking care of some return values catched by the Reflection component.
		//Note: without fvlinr we are not able to fill in the return values of a potential calculation from the back-end
		if(fvlinr!=null && !"".equals(fvlinr)){ fraktbrevRecord.setFvlinr(fvlinr);
		}else{ fraktbrevRecord.setFvlinr(lineCounter); }
		
		//logger.info("########A:" + request.getParameter("fvant_" + counter));
		//logger.info("########B:" + request.getParameter("ffenh_" + counter));
		fraktbrevRecord.setFmmrk1(request.getParameter("fmmrk1_" + counter));
		fraktbrevRecord.setFvant(request.getParameter("fvant_" + counter));
		fraktbrevRecord.setFvpakn(request.getParameter("fvpakn_" + counter));
		fraktbrevRecord.setFvvt(request.getParameter("fvvt_" + counter));
		fraktbrevRecord.setFvvkt(request.getParameter("fvvkt_" + counter));
		fraktbrevRecord.setFvvol(request.getParameter("fvvol_" + counter));
		fraktbrevRecord.setFvlm(request.getParameter("fvlm_" + counter));
		fraktbrevRecord.setFvlm2(request.getParameter("fvlm2_" + counter));
		fraktbrevRecord.setFvlen(request.getParameter("fvlen_" + counter));
		fraktbrevRecord.setFvbrd(request.getParameter("fvbrd_" + counter));
		fraktbrevRecord.setFvhoy(request.getParameter("fvhoy_" + counter));
		//farlig goods
		fraktbrevRecord.setFfunnr(request.getParameter("ffunnr_" + counter));
		fraktbrevRecord.setFfembg(request.getParameter("ffembg_" + counter));
		fraktbrevRecord.setFfindx(request.getParameter("ffindx_" + counter));
		
		fraktbrevRecord.setFfantk(request.getParameter("ffantk_" + counter));
		fraktbrevRecord.setFfante(request.getParameter("ffante_" + counter));
		fraktbrevRecord.setFfenh(request.getParameter("ffenh_" + counter));
		
		urlRequestParams.append("&fvlinr=" + fraktbrevRecord.getFvlinr());
		urlRequestParams.append("&fmmrk1=" + fraktbrevRecord.getFmmrk1());
		urlRequestParams.append("&fvant=" + fraktbrevRecord.getFvant());
		urlRequestParams.append("&fvpakn=" + fraktbrevRecord.getFvpakn());
		urlRequestParams.append("&fvvt=" + fraktbrevRecord.getFvvt());
		urlRequestParams.append("&fvvkt=" + fraktbrevRecord.getFvvkt());
		urlRequestParams.append("&fvvol=" + fraktbrevRecord.getFvvol());
		urlRequestParams.append("&fvlm=" + fraktbrevRecord.getFvlm());
		urlRequestParams.append("&fvlm2=" + fraktbrevRecord.getFvlm2());
		urlRequestParams.append("&fvlen=" + fraktbrevRecord.getFvlen());
		urlRequestParams.append("&fvbrd=" + fraktbrevRecord.getFvbrd());
		urlRequestParams.append("&fvhoy=" + fraktbrevRecord.getFvhoy());
		//farlig goods
		urlRequestParams.append("&ffunnr=" + fraktbrevRecord.getFfunnr());
		urlRequestParams.append("&ffembg=" + fraktbrevRecord.getFfembg());
		urlRequestParams.append("&ffindx=" + fraktbrevRecord.getFfindx());
		
		urlRequestParams.append("&ffantk=" + fraktbrevRecord.getFfantk());
		urlRequestParams.append("&ffante=" + fraktbrevRecord.getFfante());
		urlRequestParams.append("&ffenh=" + fraktbrevRecord.getFfenh());
		
		return urlRequestParams.toString();
	}
}
