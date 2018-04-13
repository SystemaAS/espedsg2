/**
 * 
 */
package no.systema.transportdisp.model.workflow.order;

import java.util.Collection;

import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
/**
 * @author oscardelatorre
 * @date Jan 26, 2018
 */
public class OrderContactInformationObject {
	
	private String avd;
	public void setAvd(String value){ this.avd = value;}
	public String getAvd(){ return this.avd; }
	
	private String opd;
	public void setOpd(String value){ this.opd = value;}
	public String getOpd(){ return this.opd; }
	
	//Parties (DOKUFE db-table)
		private String ownPartyFbnr = "1";
		public void setOwnPartyFbnr(String value){ this.ownPartyFbnr = value;}
		public String getOwnPartyFbnr(){ return this.ownPartyFbnr; }
		
		private String ownSenderPartId = "CN";
		public void setOwnSenderPartId(String value){ this.ownSenderPartId = value;}
		public String getOwnSenderPartId(){ return this.ownSenderPartId; }
		
		private String ownSenderContactName = null;
		public void setOwnSenderContactName(String value){ this.ownSenderContactName = value;}
		public String getOwnSenderContactName(){ return this.ownSenderContactName; }
		
		private String ownSenderMobile = null;
		public void setOwnSenderMobile(String value){ this.ownSenderMobile = value;}
		public String getOwnSenderMobile(){ return this.ownSenderMobile; }
		
		private String ownSenderEmail = null;
		public void setOwnSenderEmail(String value){ this.ownSenderEmail = value;}
		public String getOwnSenderEmail(){ return this.ownSenderEmail; }
		
		private String ownReceiverPartId = "CZ";
		public void setOwnReceiverPartId(String value){ this.ownReceiverPartId = value;}
		public String getOwnReceiverPartId(){ return this.ownReceiverPartId; }
		
		private String ownReceiverContactName = null;
		public void setOwnReceiverContactName(String value){ this.ownReceiverContactName = value;}
		public String getOwnReceiverContactName(){ return this.ownReceiverContactName; }
		
		private String ownReceiverMobile = null;
		public void setOwnReceiverMobile(String value){ this.ownReceiverMobile = value;}
		public String getOwnReceiverMobile(){ return this.ownReceiverMobile; }
		
		private String ownReceiverEmail = null;
		public void setOwnReceiverEmail(String value){ this.ownReceiverEmail = value;}
		public String getOwnReceiverEmail(){ return this.ownReceiverEmail; }
		//END Parties
	
	
}
