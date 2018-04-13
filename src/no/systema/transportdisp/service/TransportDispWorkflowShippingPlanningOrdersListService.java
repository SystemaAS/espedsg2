/**
 * 
 */
package no.systema.transportdisp.service;

import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer;


/**
 * 
 * @author oscardelatorre
 * @date Apr 11, 2015
 * 
 *
 */
public interface TransportDispWorkflowShippingPlanningOrdersListService {
	public JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer getCurrentOrdersListContainer(String utfPayload);
	public JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer getOpenOrdersListContainer(String utfPayload);
	public JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer getOrderFraktbrevPdfContainer(String utfPayload);
}
