	/**
 * 
 */
package no.systema.transportdisp.util.manager;

import java.util.*;

import org.apache.log4j.Logger;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesRecord;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;

//import no.systema.tvinn.sad.model.external.url.UrlTvinnSadTolltariffenObject;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripArchivedDocsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripArchivedDocsRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripMessageNoteContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripMessageNoteRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.codes.JsonTransportDispCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.codes.JsonTransportDispCodeRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeParametersContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeParametersRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
import no.systema.transportdisp.util.manager.OppdragTypeParametersMgr;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.service.TransportDispChildWindowService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;


/**
 * Work with Trips - Transport Disponering
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a caller when needed.
 * 
 * It aims to provide a single-point-of-entry for oppdrag (order) type parameters.
 *  
 * 
 * @author oscardelatorre
 * @date Aug 13, 2015
 * 
 * 
 */
public class OppdragTypeParametersMgr {
	private static final Logger logger = Logger.getLogger(OppdragTypeParametersMgr.class.getName());
	private UrlCgiProxyService urlCgiProxyService;
	private TransportDispDropDownListPopulationService transportDispDropDownListPopulationService;
	private static final JsonDebugger jsonDebugger = new JsonDebugger(4000);
	//required constructor (when applicable)
	public OppdragTypeParametersMgr(UrlCgiProxyService urlCgiProxyService, TransportDispDropDownListPopulationService transportDispDropDownListPopulationService){
		this.urlCgiProxyService = urlCgiProxyService;
		this.transportDispDropDownListPopulationService = transportDispDropDownListPopulationService;
	}
	
	/**
	 * Set general order type parameters
	 * @param appUser
	 * @param record
	 * @return
	 */
	public void fetchOppdragTypeParametersAttributes(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderRecord record){
		try{
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_OPPDTYPE_TIME_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			//logger.info("heavd:" + record.getHeavd() + "XX");
			if(record.getHeavd()!=null && !"".equals(record.getHeavd())){
				urlRequestParamsKeys.append("&avd=" + record.getHeavd());
			}
			
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:" + BASE_URL);
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTransportDispOppdragTypeParametersContainer container = this.transportDispDropDownListPopulationService.getOppdragTypeTimeContainer(url);
			for(JsonTransportDispOppdragTypeParametersRecord paramsRecord: container.getStdParametere()){
				record.setOppdragTypeParameters(paramsRecord);
			}
			//logger.info("KLOKKE:" + record.getKlokkeJN());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
