/**
 * 
 */
package no.systema.transportdisp.service;

import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispCustomerContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispLoadUnloadPlacesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispPackingCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispTollstedCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispAvdContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispBilCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispBilNrContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispDriverContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispTranspCarrierContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispFileUploadValidationContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispSupplierContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiDocCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiGiltighetsListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispSendSmsContainer;




/**
 * 
 * @author oscardelatorre
 * @date Apr 1, 2015
 * 
 *
 */
public interface TransportDispChildWindowService {
	public JsonTransportDispAvdContainer getAvdContainer(String utfPayload);
	public JsonTransportDispBilCodeContainer getBilCodeContainer(String utfPayload);
	public JsonTransportDispBilNrContainer getBilNrContainer(String utfPayload);
	public JsonTransportDispDriverContainer getDriverContainer(String utfPayload);
	public JsonTransportDispTranspCarrierContainer getTranspCarrierContainer(String utfPayload);
	public JsonPostalCodesContainer getPostalCodesContainer(String utfPayload);
	public JsonTransportDispCustomerContainer getCustomerContainer(String utfPayload);
	public JsonTransportDispLoadUnloadPlacesContainer getLoadUnloadPlacesContainer(String utfPayload);
	public JsonTransportDispFileUploadValidationContainer getFileUploadValidationContainer(String utfPayload);
	public JsonTransportDispDangerousGoodsContainer getDangerousGoodsContainer(String utfPayload);
	public JsonTransportDispPackingCodesContainer getPackingCodesContainer(String utfPayload);
	public JsonTransportDispTollstedCodesContainer getTollstedCodesContainer(String utfPayload);
	public JsonTransportDispSupplierContainer getSupplierContainer(String utfPayload);
	public JsonTransportDispGebyrCodeContainer getGebyrCodeContainer(String utfPayload);
	public JsonTransportDispFrisokveiCodesContainer getFrisokveiCodesContainer(String utfPayload);
	public JsonTransportDispFrisokveiDocCodesContainer getFrisokveiDocCodesContainer(String utfPayload);
	public JsonTransportDispSendSmsContainer getSendSmsContainer(String utfPayload);
	public JsonTransportDispFrisokveiGiltighetsListContainer getOrderFrisokveiContainerGiltighetsLista(String utfPayload);
	
	
}
