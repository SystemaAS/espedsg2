/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import java.util.Collection;
import lombok.Data;

/**
 * @author oscardelatorre
 * @date Nov 2018
 *
 */
@Data
public class JsonTransportDispWorkflowSpecificTripShipContainer {
	private String user = null;
	private String feavd = null;
	private String fetur = null;
	private String mode = null;
	private String errMsg = null;
	private Collection<JsonTransportDispWorkflowSpecificTripShipRecord> ferryTrips;
	private Collection<JsonTransportDispWorkflowSpecificTripShipRecord> ferryDepartures;
		
}
