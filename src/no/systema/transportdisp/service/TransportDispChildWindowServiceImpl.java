/**
 * 
 */
package no.systema.transportdisp.service;

import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;

import java.io.PrintWriter;
import java.io.StringWriter;

import no.systema.main.mapper.jsonjackson.general.PostalCodesMapper;
import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispChildWindowMapper;
import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispWorkflowSpecificOrderFrisokveiMapper;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispCustomerContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispLoadUnloadPlacesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispPackingCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispSendSmsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispTollstedCodesContainer;

import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispAvdContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispBilCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispBilNrContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispDriverContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispFileUploadValidationContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispTranspCarrierContainer;

import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispSupplierContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiDocCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiGiltighetsListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 1, 2015
 * 
 * 
 */
public class TransportDispChildWindowServiceImpl implements TransportDispChildWindowService {

	public JsonTransportDispAvdContainer getAvdContainer(String utfPayload) {
		JsonTransportDispAvdContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getAvdContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

	public JsonTransportDispBilCodeContainer getBilCodeContainer(String utfPayload) {
		JsonTransportDispBilCodeContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getBilCodeContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	public JsonTransportDispBilNrContainer getBilNrContainer(String utfPayload) {
		JsonTransportDispBilNrContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getBilNrContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	public JsonTransportDispTranspCarrierContainer getTranspCarrierContainer(String utfPayload) {
		JsonTransportDispTranspCarrierContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getTranspCarrierContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	public JsonTransportDispDriverContainer getDriverContainer(String utfPayload) {
		JsonTransportDispDriverContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getDriverContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * 
	 */
	public JsonPostalCodesContainer getPostalCodesContainer(String utfPayload){
		JsonPostalCodesContainer container = null;
		try{
			PostalCodesMapper mapper = new PostalCodesMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * Customer
	 */
	public JsonTransportDispCustomerContainer getCustomerContainer(String utfPayload){
		JsonTransportDispCustomerContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getCustomerContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * Load/Unload places
	 * 
	 */
	public JsonTransportDispLoadUnloadPlacesContainer getLoadUnloadPlacesContainer(String utfPayload){
		JsonTransportDispLoadUnloadPlacesContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getLoadUnloadPlacesContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * File upload validation
	 * 
	 */
	public JsonTransportDispFileUploadValidationContainer getFileUploadValidationContainer(String utfPayload){
		JsonTransportDispFileUploadValidationContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getFileUploadValidationContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * Dangerous goods
	 */
	public JsonTransportDispDangerousGoodsContainer getDangerousGoodsContainer(String utfPayload){
		JsonTransportDispDangerousGoodsContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getDangerousGoodsContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * Packing codes
	 * 
	 */
	public JsonTransportDispPackingCodesContainer getPackingCodesContainer(String utfPayload){
		JsonTransportDispPackingCodesContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getPackingCodesContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * Tollsted codes
	 */
	public JsonTransportDispTollstedCodesContainer getTollstedCodesContainer(String utfPayload){
		JsonTransportDispTollstedCodesContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getTollstedCodesContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * Supplier reg.
	 */
	public JsonTransportDispSupplierContainer getSupplierContainer(String utfPayload){
		JsonTransportDispSupplierContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getSupplierContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * Gebyrkoder
	 */
	public JsonTransportDispGebyrCodeContainer getGebyrCodeContainer(String utfPayload){
		JsonTransportDispGebyrCodeContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getGebyrContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * Frie søkeveier
	 */
	public JsonTransportDispFrisokveiCodesContainer getFrisokveiCodesContainer(String utfPayload){
		JsonTransportDispFrisokveiCodesContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getFrisokveiContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	/**
	 * Frie søkeveier Doks
	 */
	public JsonTransportDispFrisokveiDocCodesContainer getFrisokveiDocCodesContainer(String utfPayload){
		JsonTransportDispFrisokveiDocCodesContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getFrisokveiDocContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	
	/**
	 * 
	 */
	public JsonTransportDispSendSmsContainer getSendSmsContainer(String utfPayload){
		JsonTransportDispSendSmsContainer container = null;
		try{
			JsonTransportDispChildWindowMapper mapper = new JsonTransportDispChildWindowMapper();
			container = mapper.getSendSmsContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	
	/**
	 * 
	 */
	public JsonTransportDispFrisokveiGiltighetsListContainer getOrderFrisokveiContainerGiltighetsLista(String utfPayload){
		JsonTransportDispFrisokveiGiltighetsListContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderFrisokveiMapper mapper = new JsonTransportDispWorkflowSpecificOrderFrisokveiMapper();
			container = mapper.getContainerGiltighetsLista(utfPayload);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
		}
		
		return container;
	}
	
	
}
