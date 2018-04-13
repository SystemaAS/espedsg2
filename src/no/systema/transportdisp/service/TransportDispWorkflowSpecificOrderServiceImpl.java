/**
 * 
 */
package no.systema.transportdisp.service;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispWorkflowSpecificOrderMapper;
import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispWorkflowSpecificOrderInvoiceMapper;
import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispWorkflowSpecificOrderLoggingMapper;
import no.systema.transportdisp.mapper.jsonjackson.validationbackend.JsonTransportDispWorkflowSpecificOrderValidationBackendMapper;
import no.systema.transportdisp.mapper.jsonjackson.JsonTransportDispWorkflowSpecificOrderFrisokveiMapper;

import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispCustomerDeliveryAddressContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderArchivedDocsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderMessageNoteContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiGiltighetsListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.frisokvei.JsonTransportDispWorkflowSpecificOrderFrisokveiContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.validationbackend.JsonTransportDispWorkflowSpecificOrderValidationBackendContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 31, 2015
 * 
 * 
 */
public class TransportDispWorkflowSpecificOrderServiceImpl implements TransportDispWorkflowSpecificOrderService {
	private static final Logger logger = Logger.getLogger(JsonTransportDispWorkflowSpecificOrderMapper.class.getName());
	/**
	 * 
	 */
	public JsonTransportDispWorkflowSpecificOrderContainer getContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificOrderContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderMapper mapper = new JsonTransportDispWorkflowSpecificOrderMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonTransportDispWorkflowSpecificOrderMessageNoteContainer getMessageNoteContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificOrderMessageNoteContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderMapper mapper = new JsonTransportDispWorkflowSpecificOrderMapper();
			container = mapper.getMessageNoteContainer(utfPayload);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
		return container;
		
	}
	
	/**
	 * 
	 */
	public JsonTransportDispWorkflowSpecificOrderFraktbrevContainer getFraktbrevContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificOrderFraktbrevContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderMapper mapper = new JsonTransportDispWorkflowSpecificOrderMapper();
			container = mapper.getFraktbrevContainer(utfPayload);
			
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
		return container;
	}
	/**
	 *
	 */
	public JsonTransportDispCustomerDeliveryAddressContainer getDeliveryAddressContainer(String utfPayload){
		JsonTransportDispCustomerDeliveryAddressContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderMapper mapper = new JsonTransportDispWorkflowSpecificOrderMapper();
			container = mapper.getDeliveryAddressContainer(utfPayload);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
		return container;
	}
	
	/**
	 * Invoice
	 */
	public JsonTransportDispWorkflowSpecificOrderInvoiceContainer getOrderInvoiceContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificOrderInvoiceContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderInvoiceMapper mapper = new JsonTransportDispWorkflowSpecificOrderInvoiceMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
		return container;
	}
	/**
	 * 
	 */
	public JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer getOrderInvoiceReadyMarkContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderInvoiceMapper mapper = new JsonTransportDispWorkflowSpecificOrderInvoiceMapper();
			container = mapper.getReadyMarkContainer(utfPayload);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		return container;
	}
	

	/**
	 * Validation Back-end
	 */
	public JsonTransportDispWorkflowSpecificOrderValidationBackendContainer getOrderValidationBackendContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificOrderValidationBackendContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderValidationBackendMapper mapper = new JsonTransportDispWorkflowSpecificOrderValidationBackendMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
		return container;
	}

	/**
	 * renders fraktbrev PDF
	 */
	public JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer getOrderFraktbrevPdfContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderMapper mapper = new JsonTransportDispWorkflowSpecificOrderMapper();
			container = mapper.getFraktbrevPdfContainer(utfPayload);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
		return container;
		
	}
	/**
	 * Gets a list of uploaded docs
	 */
	public JsonTransportDispWorkflowSpecificOrderArchivedDocsContainer getOrderArchivedDocsContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificOrderArchivedDocsContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderMapper mapper = new JsonTransportDispWorkflowSpecificOrderMapper();
			container = mapper.getArchivedDocsContainer(utfPayload);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
		return container;
	}

	/**
	 * Track and trace logging
	 */
	public JsonTransportDispWorkflowSpecificOrderLoggingContainer getOrderLoggingContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificOrderLoggingContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderLoggingMapper mapper = new JsonTransportDispWorkflowSpecificOrderLoggingMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
		return container;
	}
	
	/**
	 * 
	 */
	public JsonTransportDispWorkflowSpecificOrderFrisokveiContainer getOrderFrisokveiContainer(String utfPayload){
		JsonTransportDispWorkflowSpecificOrderFrisokveiContainer container = null;
		try{
			JsonTransportDispWorkflowSpecificOrderFrisokveiMapper mapper = new JsonTransportDispWorkflowSpecificOrderFrisokveiMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
		return container;
	}
	
	
}
