package no.systema.transportdisp.util.manager.java.reflect;

import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;

public class TesterReflection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*ReflectionSpecificOrderHeaderMgr mgr = new ReflectionSpecificOrderHeaderMgr();
		JsonTransportDispWorkflowSpecificOrderRecord targetRecord = new JsonTransportDispWorkflowSpecificOrderRecord();
		JsonTransportDispWorkflowSpecificOrderRecord sourceRecord = new JsonTransportDispWorkflowSpecificOrderRecord();
		
		mgr.updateOriginalAttributesOnTargetRecord(targetRecord, sourceRecord);
		*/
		ReflectionUrlStoreMgr mgr = new ReflectionUrlStoreMgr();
		System.out.println(mgr.printProperties("no.systema.transportdisp.url.store.TransportDispUrlDataStore", "text"));
	}

}
